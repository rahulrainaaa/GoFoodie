package com.app.gofoodie.utility;

@SuppressWarnings("unused")
class View {
}


/*



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
        HAVING CustomerShortlistedBranches.customer_id = 36;






        CREATE View  CustomerFullDetail AS
        SELECT
        Login.login_id, Login.username, Login.email, Login.phone, Login.salt_key, Login.status, Login.register_date, Login.modify_date,
        CustomerDetail.customer_id, CustomerDetail.name, CustomerDetail.address, CustomerDetail.area, CustomerDetail.company_name, CustomerDetail.mobile  as mobile1, CustomerDetail.mobile_verified, CustomerDetail.mobile2, CustomerDetail.email  as email1, CustomerDetail.email_verified, CustomerDetail.email2, CustomerDetail.geo_lat, CustomerDetail.geo_lng, CustomerDetail.how_many_days, CustomerDetail.days_you_want_the_combo, CustomerDetail.veg_nonveg, CustomerDetail.days_no_nonveg,
        Wallet.wallet_id, Wallet.amount, Wallet.wl_id, Wallet.valid_upto
        FROM Login
        JOIN CustomerDetail on  CustomerDetail.login_id = Login.login_id
        JOIN Wallet on  Wallet.login_id = Login.login_id;









 */