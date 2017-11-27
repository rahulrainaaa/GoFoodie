package com.app.gofoodie;

/**
 * Created by Android on 9/26/2017.
 */

public class OtherGFDB {
}

    /*


        Drop VIEW TelrPaymentDetails;
        Drop VIEW CustomerShortlistedBranchesDetail;
        Drop VIEW CustomerFullDetail;



        create table Admin (
        id int(64) not null auto_increment primary key,
        name varchar(100),
        email varchar(100) NOT NULL UNIQUE,
        password varchar(100),
        phone varchar(100) NOT NULL UNIQUE,
        role ENUM('all') not null,
        table_version int(64) default 1,
        meta varchar(100));

        create table Login (
        login_id int(64) not null auto_increment primary key,
        username varchar(50) NOT NULL UNIQUE,
        email varchar(50) NOT NULL UNIQUE,
        phone varchar(50) NOT NULL UNIQUE,
        password varchar(50),
        register_date varchar(50),
        user_type ENUM('customer', 'restaurant') not null,
        status ENUM('active', 'deactive') not null,
        table_version int(64) default 1,
        meta varchar(100));

        create table Session (
        id int(64) not null auto_increment primary key,
        login_id int(64),
        token varchar(100),
        login_date varchar(100),
        expiry_date varchar(100),
        login_type varchar(100),
        platform varchar(100),
        software varchar(100),
        table_version int(64) default 1,
        foreign key (login_id) references Login(login_id),
        meta varchar(100));

        create table Country (
        id int(64) not null auto_increment primary key,
        country_id int(64),
        country_name varchar(50),
        table_version int(64) default 1,
        meta varchar(100));

        create table City (
        city_id int(64) not null auto_increment primary key,
        country_id int(64),
        state_id int(64),
        city_name varchar(100),
        foreign key (country_id) references Country(id),
        table_version int(64) default 1,
        meta varchar(100));

        create table Location (
        location_id int(64) not null auto_increment primary key,
        city_id int(64),
        loation_name varchar(100),
        foreign key (city_id) references City(city_id),
        table_version int(64) default 1,
        meta varchar(100));

        create table  RestaurantDetail (
        restaurant_id int(64) not null auto_increment primary key,
        login_id int(64),
        name varchar(100),
        about_us varchar(200),
        email varchar(100),
        restaurant_address varchar(200),
        restro_bank_acc varchar(100),
        restro_iban_num varchar(100),
        restro_branch varchar(100),
        traiding_licence_no varchar(100),
        traiding_licence_exp_date varchar(100),
        table_version int(64) default 1,
        foreign key (login_id) references Login(login_id),
        meta varchar(100));

        create table RestaurantBranchLocation (
        branch_id int(64) not null auto_increment primary key,
        location_id int(64),
        restaurant_id int(64),
        branch_name varchar(100),
        description varchar(500),
        tags varchar(100),
        branch_address varchar(100),
        branch_postal_code varchar(30),
        geo_lat decimal(20,20),
        geo_lng decimal(20,20),
        table_version int(64) default 1,
        foreign key (restaurant_id) references RestaurantDetail(restaurant_id),
        foreign key (location_id) references location(location_id),
        meta varchar(100));

        create table CustomerDetail (
        customer_id int(64) not null auto_increment primary key,
        login_id int(64),
        name varchar(100),
        address varchar(100),
        mobile varchar(100),
        mobile_verified ENUM('no','yes') not null,
        mobile2 varchar(100),
        email varchar(100),
        email_verified ENUM('no','yes') not null,
        geo_lat decimal(20,20),
        geo_lng decimal(20,20),
        table_version int(64) default 1,
        foreign key (login_id) references Login(login_id),
        meta varchar(100));

        create table ComboPlan (
        combo_id int(64) not null auto_increment primary key,
        login_id int(64),
        restaurant_id int(64),
        branch_id int(64),
        name varchar(100),
        description varchar(100),
        price decimal(20,3),
        table_version int(64) default 1,
        foreign key (branch_id) references RestaurantBranchLocation(branch_id),
        foreign key (login_id) references Login(login_id),
        foreign key (restaurant_id) references RestaurantDetail(restaurant_id),
        meta varchar(100));

        create table ComboPlanItems (
        combo_item_id int(64) not null auto_increment primary key,
        combo_id int(64),
        name varchar(50),
        options varchar(1000),
        table_version int(64) default 1,
        foreign key (combo_id) references ComboPlan(combo_id),
        meta varchar(100));

        create table ComboOrders (
        order_id int(64) not null auto_increment primary key,
        combo_id int(64),
        order_set_id int,
        customer_id int(64),
        restaurant_id int(64),
        branch_id int(64),
        item_details varchar(500),
        order_date varchar(100),
        modified_date varchar(100),
        start_date varchar(100),
        end_date varchar(100),
        status ENUM('pending', 'rejected', 'accepted', 'completed', 'cancelled') not null,
        delivery_address varchar(200),
        geo_lat decimal(20,10),
        geo_lng decimal(20,10),
        price_paid decimal(20,3),
        admin_price decimal(20,3),
        restaurant_price decimal(20,3),
        logistic_price decimal(20,3),
        other_tax decimal(20,3) ,
        table_version int(64) default 1,
        foreign key (order_set_id) references OrderSet(order_set_id),
        foreign key (branch_id) references RestaurantBranchLocation(branch_id),
        foreign key (restaurant_id) references RestaurantDetail(restaurant_id),
        foreign key (customer_id) references CustomerDetail(customer_id),
        foreign key (combo_id) references ComboPlan(combo_id),
        meta varchar(100));

        create table OrderTimeline (
        order_timeline_id int(64) not null auto_increment primary key,
        order_id int(64),
        datetime varchar(25),
        customer_paid decimal(10,3),
        restaurant_share decimal(10,3),
        admin_share decimal(10,3),
        logistic_share decimal(20,3),
        other_tax decimal(20,3),
        status ENUM('pending', 'delivered', 'cancelled', 'undelivered') not null,
        table_version int(64) default 1,
        foreign key (order_id) references  ComboOrders(order_id),
        meta varchar(100));

        create table RestaurantReviews (
        id int(64) not null auto_increment primary key,
        customer_id int(64),
        restaurant_id int(64),
        branch_id int(64),
        combo_id int(64),
        order_id int(64) unique,
        rating decimal(5,5),
        created_date varchar(100),
        comment varchar(100),
        reviewer varchar(100),
        table_version int(64) default 1,
        foreign key (combo_id) references ComboPlan(combo_id),
        foreign key (order_id) references ComboOrders(order_id),
        foreign key (customer_id) references CustomerDetail(customer_id),
        foreign key (branch_id) references RestaurantBranchLocation(branch_id),
        foreign key (restaurant_id) references RestaurantDetail(restaurant_id),
        meta varchar(100));

        create table RechargePlan (
        plan_id int(64) not null auto_increment primary key,
        name varchar(100),
        description varchar(100),
        pay_amount decimal(10,3),
        get_amount decimal(10,3),
        validity_days int(64),
        status ENUM('visible', 'invisible') not null,
        table_version int(64) default 1,
        meta varchar(100));

        create table Wallet (
        wallet_id int(64) not null auto_increment primary key,
        login_id int(64),
        amount decimal(10,3),
        table_version int(64) default 1,
        foreign key (login_id) references Login(login_id),
        meta varchar(100));

        create table WalletTransactions (
        wallet_transaction_id int(64) not null auto_increment primary key,
        wallet_id int(64),
        datetime varchar(100),
        remarks varchar(100),
        amount decimal(10,3),
        type varchar(100),
        table_version int(64) default 1,
        foreign key (wallet_id) references Wallet(wallet_id),
        meta varchar(100));

        create table PaymentTransactions (
        transaction_id int(64) not null auto_increment primary key,
        wallet_id int(64),
        pg_transaction_id varchar(100),
        pg_response varchar(100),
        transaction_response varchar(100),
        datetime varchar(100),
        remarks varchar(100),
        paid_amount decimal(10,3),
        got_amount decimal(10,3),
        table_version int(64) default 1,
        foreign key (wallet_id) references Wallet(wallet_id),
        meta varchar(100));

        create table SubscribedPlans (
        subscription_id int(64) not null auto_increment primary key,
        plan_id int(64),
        transaction_id int(64),
        customer_id int(64),
        login_id int(64),
        datetime varchar(25),
        expiry_datetime varchar(25),
        table_version int(64) default 1,
        foreign key (login_id) references Login(login_id),
        foreign key (transaction_id) references PaymentTransactions(transaction_id),
        foreign key (plan_id) references RechargePlan(plan_id),
        foreign key (customer_id) references CustomerDetail(customer_id),
        meta varchar(100));

        create table CustomerShortlistedBranches (
        shortlist_id int primary key auto_increment,
        customer_id int,
        branch_id int,
        login_id int,
        foreign key (customer_id) references  CustomerDetail (customer_id),
        foreign key (branch_id) references RestaurantBranchLocation (branch_id)
        foreign key (login_id) references Login (login_id));

        create table CartItem (
        cart_item_id int primary key auto_increment,
        customer_id int,
        branch_id int,
        combo_id int,
        quantity int,
        description varchar (1000),
        foreign key (customer_id) references  CustomerDetail (customer_id),
        foreign key (branch_id) references RestaurantBranchLocation (branch_id),
        foreign key (combo_id) references ComboPlan (combo_id));

        create table OrderSet (
        order_set_id int primary key auto_increment,
        customer_id int,
        start_date varchar(20),
        end_date varchar(20),
        ordering_date varchar(20),
        vacation_flag Enum('true', 'false'),
        foreign key (customer_id) references  CustomerDetail (customer_id));

        create table RestaurantBranchCategories (
        branch_category_id int primary key auto_increment,
        branch_id int,
        cate_id int,
        foreign key (branch_id) references RestaurantBranchLocation (branch_id),
        foreign key (cate_id) references Category (cate_id));

        create table Cuisine (
        cuisine_id int primary key auto_increment,
        cuisine_name varchar(50),
        cate_id int,
        foreign key (cate_id) references Category(cate_id));

        create table Vacation (
        vacation_id int primary key auto_increment,
        customer_id int,w
        order_set_id int,
        from_date varchar(20),
        to_date varchar(20),
        mode Enum ('long', 'short', 'emergency'),
        approval_status Enum ('pending', 'refunded') default 'pending',
        foreign key (customer_id) references CustomerDetail (customer_id),
        foreign key (order_set_id) references OrderSet (order_set_id));

        create table TelrPayment (
        telr_id int primary key auto_increment,
        transaction_id int,
        telr_trace varchar(50),
        telr_status varchar(50),
        telr_avs varchar(50),
        telr_code varchar(50),
        telr_message varchar(50),
        telr_ca_valid varchar(50),
        telr_cardcode varchar(50),
        telr_cardlast4 varchar(50),
        telr_cvv varchar(50),
        telr_tranref varchar(50),
        foreign key (transaction_id) references PaymentTransactions(transaction_id));


        CREATE VIEW TelrPaymentDetails AS
        SELECT
        TelrPayment.telr_id,
        TelrPayment.transaction_id,
        TelrPayment.telr_trace,
        TelrPayment.telr_status,
        TelrPayment.telr_avs,
        TelrPayment.telr_code,
        TelrPayment.telr_message,
        TelrPayment.telr_ca_valid,
        TelrPayment.telr_cardcode,
        TelrPayment.telr_cardlast4,
        TelrPayment.telr_cvv,
        TelrPayment.telr_tranref,
        PaymentTransactions.wallet_id,
        PaymentTransactions.pg_transaction_id,
        PaymentTransactions.pg_response,
        PaymentTransactions.transaction_response,
        PaymentTransactions.datetime,
        PaymentTransactions.remarks,
        PaymentTransactions.paid_amount,
        PaymentTransactions.got_amount
        FROM TelrPayment
        JOIN PaymentTransactions on PaymentTransactions.transaction_id = TelrPayment.transaction_id;





        CREATE VIEW CustomerShortlistedBranchesDetail AS
        SELECT
        RestaurantBranchLocation.branch_id,
        RestaurantDetail.restaurant_id,
        CustomerShortlistedBranches.customer_id,
        COALESCE(AVG( RestaurantReviews.rating ), 0) 'avg_rating',
        COUNT(RestaurantReviews.rating) 'count_rating',
        RestaurantDetail.name,
        RestaurantBranchLocation.branch_name,
        RestaurantBranchLocation.branch_email,
        RestaurantBranchLocation.description,
        RestaurantBranchLocation.profile_icon,
        RestaurantBranchLocation.tags,
        RestaurantBranchLocation.type,
        RestaurantBranchLocation.branch_address,
        RestaurantBranchLocation.branch_postal_code,
        COALESCE(RestaurantBranchLocation.geo_lat, 0),
        COALESCE(RestaurantBranchLocation.geo_lng, 0),
        COALESCE(RestaurantDetail.about_us, '')
        FROM RestaurantBranchLocation
        LEFT JOIN RestaurantReviews ON RestaurantReviews.branch_id = RestaurantBranchLocation.branch_id
        LEFT JOIN CustomerShortlistedBranches ON CustomerShortlistedBranches.branch_id = RestaurantBranchLocation.branch_id
        LEFT JOIN RestaurantDetail ON RestaurantDetail.restaurant_id = RestaurantBranchLocation.restaurant_id
        GROUP BY RestaurantBranchLocation.branch_id
        HAVING CustomerShortlistedBranches.customer_id = 1;






        CREATE View  CustomerFullDetail AS
        SELECT
        Login.login_id, Login.username, Login.email, Login.phone, Login.salt_key, Login.status, Login.register_date, Login.modify_date,
        CustomerDetail.customer_id, CustomerDetail.name, CustomerDetail.address, CustomerDetail.area, CustomerDetail.company_name, CustomerDetail.mobile  as mobile1, CustomerDetail.mobile_verified, CustomerDetail.mobile2, CustomerDetail.email  as email1, CustomerDetail.email_verified, CustomerDetail.email2, CustomerDetail.geo_lat, CustomerDetail.geo_lng, CustomerDetail.how_many_days, CustomerDetail.days_you_want_the_combo, CustomerDetail.veg_nonveg, CustomerDetail.days_no_nonveg,
        Wallet.wallet_id, Wallet.amount, Wallet.wl_id, Wallet.valid_upto
        FROM Login
        JOIN CustomerDetail on  CustomerDetail.login_id = Login.login_id
        JOIN Wallet on  Wallet.login_id = Login.login_id;





     */


