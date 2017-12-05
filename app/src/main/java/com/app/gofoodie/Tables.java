package com.app.gofoodie;

public class Tables {
}

/*





CREATE TABLE IF NOT EXISTS `Admin` (
  `id` int(64) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `password` varchar(255) default NULL,
  `salt_key` varchar(50) NOT NULL,
  `phone` varchar(100) default NULL,
  `role` enum('all') NOT NULL,
  `logistic_margin` bigint(20) NOT NULL,
  `profit_margin` bigint(20) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
);









CREATE TABLE IF NOT EXISTS `CartItem` (
  `cart_item_id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) default NULL,
  `branch_id` int(11) default NULL,
  `combo_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `description` varchar(1000) default NULL,
  `created` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`cart_item_id`),
  KEY `customer_id` (`customer_id`),
  KEY `branch_id` (`branch_id`),
  KEY `combo_id` (`combo_id`)
);









CREATE TABLE IF NOT EXISTS `Category` (
  `cate_id` int(11) NOT NULL auto_increment,
  `cate_name` varchar(50) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`cate_id`)
) ;








CREATE TABLE IF NOT EXISTS `City` (
  `city_id` int(64) NOT NULL auto_increment,
  `country_id` int(64) default NULL,
  `state_id` int(64) default NULL,
  `city_name` varchar(100) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`city_id`),
  KEY `country_id` (`country_id`)
);






CREATE TABLE IF NOT EXISTS `ComboOrders` (
  `order_id` int(64) NOT NULL auto_increment,
  `co_id` varchar(1000) NOT NULL,
  `order_set_id` int(11) NOT NULL,
  `combo_id` int(64) default NULL,
  `customer_id` int(64) default NULL,
  `restaurant_id` int(64) default NULL,
  `branch_id` int(64) default NULL,
  `item_details` varchar(2500) default NULL,
  `order_date` varchar(100) default NULL,
  `modified_date` varchar(100) default NULL,
  `delivery_date` varchar(100) default NULL,
  `status` enum('pending','accepted','completed','cancelled','rejected','undelivered','vacation','hold') NOT NULL default 'pending',
  `delivery_address` varchar(200) default NULL,
  `area` int(11) NOT NULL,
  `geo_lat` decimal(20,10) default NULL,
  `geo_lng` decimal(20,10) default NULL,
  `price_paid` decimal(20,3) default NULL,
  `admin_price` decimal(20,3) default NULL,
  `restaurant_price` decimal(20,3) default NULL,
  `logistic_price` decimal(20,3) default NULL,
  `other_tax` decimal(20,3) default NULL,
  `modified` datetime NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`order_id`),
  KEY `branch_id` (`branch_id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `customer_id` (`customer_id`),
  KEY `combo_id` (`combo_id`),
  KEY `order_set_id` (`order_set_id`)
) ;









CREATE TABLE IF NOT EXISTS `ComboPlan` (
  `combo_id` int(64) NOT NULL auto_increment,
  `cb_id` varchar(100) NOT NULL,
  `login_id` int(64) default NULL,
  `restaurant_id` int(64) default NULL,
  `branch_id` int(64) default NULL,
  `category_id` int(11) NOT NULL,
  `cuisine_id` int(11) default NULL,
  `name` varchar(100) default NULL,
  `type` enum('veg','nonveg') NOT NULL default 'veg',
  `description` varchar(100) default NULL,
  `image` varchar(100) default NULL,
  `price` decimal(20,3) default NULL,
  `status` enum('accepted','rejected','pending','enabled','disabled','delete') NOT NULL default 'pending',
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `combo_items_id` longtext NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`combo_id`),
  KEY `branch_id` (`branch_id`),
  KEY `login_id` (`login_id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `cuisine_id` (`cuisine_id`)
);






CREATE TABLE IF NOT EXISTS `ComboPlanItems` (
  `combo_item_id` int(64) NOT NULL auto_increment,
  `combo_id` int(64) default NULL,
  `name` varchar(50) default NULL,
  `options` varchar(1000) default NULL,
  `restaurant_id` int(100) NOT NULL,
  `status` enum('enabled','disabled','delete','') NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`combo_item_id`),
  KEY `combo_id` (`combo_id`)
);






CREATE TABLE IF NOT EXISTS `Country` (
  `id` int(64) NOT NULL auto_increment,
  `country_id` int(64) default NULL,
  `country_name` varchar(50) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
);






CREATE TABLE IF NOT EXISTS `Cuisine` (
  `cuisine_id` int(11) NOT NULL auto_increment,
  `cuisine_name` varchar(50) default NULL,
  `cate_id` int(11) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`cuisine_id`),
  KEY `cate_id` (`cate_id`)
);








CREATE TABLE IF NOT EXISTS `CustomerDetail` (
  `customer_id` int(64) NOT NULL auto_increment,
  `login_id` int(64) default NULL,
  `name` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `area` int(11) NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `mobile` varchar(100) default NULL,
  `mobile_verified` enum('no','yes') NOT NULL,
  `mobile2` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `email_verified` enum('no','yes') NOT NULL,
  `email2` varchar(50) NOT NULL,
  `geo_lat` decimal(20,20) default NULL,
  `geo_lng` decimal(20,20) default NULL,
  `shortlisted_restaurant` varchar(255) default NULL,
  `how_many_days` int(1) NOT NULL default '7' COMMENT 'How many days in a week you want the combo ',
  `days_you_want_the_combo` varchar(350) NOT NULL default 'Sun, Mon, Tue, Wed, Thu, Fri, Sat' COMMENT 'Days you want the combo',
  `veg_nonveg` enum('Veg','Nonveg','Both') NOT NULL default 'Both' COMMENT 'Are you veg/nonveg',
  `days_no_nonveg` varchar(350) NOT NULL COMMENT 'Days you don’t want non veg to serve',
  `profile_set` int(11) NOT NULL default '0' COMMENT 'if profile is set show all sidebar in customer admin section',
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`customer_id`),
  KEY `login_id` (`login_id`)
);








CREATE TABLE IF NOT EXISTS `CustomerShortlistedBranches` (
  `shortlist_id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) default NULL,
  `branch_id` int(11) default NULL,
  `login_id` int(11) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`shortlist_id`),
  KEY `customer_id` (`customer_id`),
  KEY `branch_id` (`branch_id`),
  KEY `login_id` (`login_id`)
);













CREATE TABLE IF NOT EXISTS `Items` (
  `item_id` int(11) NOT NULL auto_increment,
  `restaurant_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_options` varchar(255) NOT NULL,
  `status` enum('enabled','disabled','delete','') NOT NULL,
  `modified` datetime NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`item_id`)
);









CREATE TABLE IF NOT EXISTS `Location` (
  `area_id` int(64) NOT NULL auto_increment,
  `city_id` int(64) default NULL,
  `zone_id` int(11) NOT NULL,
  `area_name` varchar(100) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`area_id`),
  KEY `city_id` (`city_id`)
);






CREATE TABLE IF NOT EXISTS `Login` (
  `login_id` int(64) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `phone` varchar(50) default NULL,
  `password` varchar(100) default NULL,
  `salt_key` longtext,
  `register_date` varchar(50) default NULL,
  `user_type` enum('customer','restaurant','social') NOT NULL,
  `status` enum('active','deactive','created') NOT NULL,
  `modify_date` varchar(50) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`login_id`)
) ;








CREATE TABLE IF NOT EXISTS `OrderSet` (
  `order_set_id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) default NULL,
  `order_count` int(11) default NULL,
  `start_date` varchar(20) default NULL,
  `end_date` varchar(20) default NULL,
  `ordering_date` varchar(20) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`order_set_id`),
  KEY `customer_id` (`customer_id`)
) ;






CREATE TABLE IF NOT EXISTS `PaymentTransactions` (
  `transaction_id` int(64) NOT NULL auto_increment,
  `wallet_id` int(64) default NULL,
  `pg_transaction_id` varchar(100) default NULL,
  `pg_response` varchar(100) default NULL,
  `transaction_response` enum('success','fail') default 'fail',
  `datetime` varchar(100) default NULL,
  `remarks` varchar(100) default NULL,
  `paid_amount` decimal(10,3) default NULL,
  `got_amount` decimal(10,3) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`transaction_id`),
  KEY `wallet_id` (`wallet_id`)
);







CREATE TABLE IF NOT EXISTS `RechargePlan` (
  `plan_id` int(64) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `description` longtext,
  `image` varchar(50) NOT NULL,
  `pay_amount` decimal(10,3) default NULL,
  `get_amount` decimal(10,3) default NULL,
  `validity_days` int(64) default NULL,
  `status` enum('visible','invisible') NOT NULL,
  `modify_date` varchar(50) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`plan_id`)
);









CREATE TABLE IF NOT EXISTS `RestaurantBranchCategories` (
  `branch_category_id` int(11) NOT NULL auto_increment,
  `branch_id` int(11) default NULL,
  `cate_id` int(11) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`branch_category_id`),
  KEY `branch_id` (`branch_id`),
  KEY `cate_id` (`cate_id`)
);







CREATE TABLE IF NOT EXISTS `RestaurantBranchLocation` (
  `branch_id` int(64) NOT NULL auto_increment primary key,
  `location_id` int(64) default NULL COMMENT 'area ids from locations table',
  `restaurant_id` int(64) default NULL,
  `branch_name` varchar(100) default NULL,
  `branch_email` varchar(50) NOT NULL,
  `description` varchar(500) default NULL,
  `profile_icon` varchar(255) NOT NULL,
  `tags` varchar(100) default NULL,
  `category_id` varchar(255) NOT NULL,
  `type` enum('Veg','Nonveg','Both') NOT NULL default 'Both',
  `branch_address` varchar(100) default NULL,
  `branch_postal_code` varchar(30) default NULL,
  `geo_lat` varchar(30) default NULL,
  `geo_lng` varchar(30) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`branch_id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `location_id` (`location_id`)
);






CREATE TABLE IF NOT EXISTS `RestaurantDetail` (
  `restaurant_id` int(64) NOT NULL auto_increment,
  `rs_id` varchar(760) NOT NULL COMMENT 'Restaurant Unique Id',
  `login_id` int(64) default NULL,
  `name` varchar(100) default NULL,
  `about_us` longtext,
  `email` varchar(100) default NULL,
  `restro_mobile` bigint(12) default NULL,
  `restaurant_address` mediumtext,
  `area` int(11) NOT NULL,
  `restro_type` varchar(100) NOT NULL,
  `geo_lat` varchar(50) NOT NULL,
  `geo_lng` varchar(50) NOT NULL,
  `restro_fee` int(11) NOT NULL,
  `restro_bank_acc` varchar(100) default NULL,
  `restro_iban_num` varchar(100) default NULL,
  `restro_branch` varchar(100) default NULL,
  `is_featured` int(100) NOT NULL,
  `traiding_licence_no` varchar(100) default NULL,
  `traiding_licence_exp_date` varchar(100) default NULL,
  `document_filename` varchar(1000) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`restaurant_id`),
  UNIQUE KEY `Rs_id` (`rs_id`),
  KEY `login_id` (`login_id`)
);









CREATE TABLE IF NOT EXISTS `RestaurantReviews` (
  `id` int(64) NOT NULL auto_increment,
  `customer_id` int(64) default NULL,
  `restaurant_id` int(64) default NULL,
  `branch_id` int(64) default NULL,
  `combo_id` int(64) default NULL,
  `order_id` int(64) default NULL,
  `rating` int(11) default NULL,
  `created_date` varchar(100) default NULL,
  `comment` varchar(100) default NULL,
  `reviewer` varchar(100) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `order_id` (`order_id`),
  KEY `combo_id` (`combo_id`),
  KEY `customer_id` (`customer_id`),
  KEY `branch_id` (`branch_id`),
  KEY `restaurant_id` (`restaurant_id`)
);





CREATE TABLE IF NOT EXISTS `RestaurantType` (
  `restro_type_id` int(11) NOT NULL auto_increment,
  `restro_type` varchar(50) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`restro_type_id`)
);





CREATE TABLE IF NOT EXISTS `Session` (
  `id` int(64) NOT NULL auto_increment,
  `login_id` int(64) default NULL,
  `token` varchar(100) default NULL,
  `login_date` varchar(100) default NULL,
  `expiry_date` varchar(100) default NULL,
  `login_type` varchar(100) default NULL,
  `platform` varchar(100) default NULL,
  `software` varchar(100) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  KEY `login_id` (`login_id`)
);





CREATE TABLE IF NOT EXISTS `SimpleRecharge` (
  `recharge_id` int(11) NOT NULL auto_increment,
  `amount` int(11) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`recharge_id`)
);









CREATE TABLE IF NOT EXISTS `SubscribedPlans` (
  `subscription_id` int(64) NOT NULL auto_increment,
  `plan_id` int(64) default NULL,
  `transaction_id` int(64) default NULL,
  `customer_id` int(64) default NULL,
  `login_id` int(64) default NULL,
  `datetime` varchar(25) default NULL,
  `expiry_datetime` varchar(25) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`subscription_id`),
  KEY `login_id` (`login_id`),
  KEY `transaction_id` (`transaction_id`),
  KEY `plan_id` (`plan_id`),
  KEY `customer_id` (`customer_id`)
);







CREATE TABLE IF NOT EXISTS `TelrPayment` (
  `telr_id` int(11) NOT NULL auto_increment,
  `transaction_id` int(11) default NULL,
  `telr_trace` varchar(50) default NULL,
  `telr_status` varchar(50) default NULL,
  `telr_avs` varchar(50) default NULL,
  `telr_code` varchar(50) default NULL,
  `telr_message` varchar(50) default NULL,
  `telr_ca_valid` varchar(50) default NULL,
  `telr_cardcode` varchar(50) default NULL,
  `telr_cardlast4` varchar(50) default NULL,
  `telr_cvv` varchar(50) default NULL,
  `telr_tranref` varchar(50) default NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`telr_id`),
  KEY `transaction_id` (`transaction_id`)
);







CREATE TABLE IF NOT EXISTS `Vacation` (
  `vacation_id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) default NULL,
  `order_set_id` int(11) default NULL,
  `from_date` varchar(20) default NULL,
  `to_date` varchar(20) default NULL,
  `comment` varchar(100) default NULL,
  `mode` enum('long','short','emergency') default NULL,
  `approval_status` enum('pending','refunded') default 'pending',
  `datetime` varchar(50) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`vacation_id`),
  KEY `customer_id` (`customer_id`),
  KEY `order_set_id` (`order_set_id`)
);







CREATE TABLE IF NOT EXISTS `Wallet` (
  `wallet_id` int(64) NOT NULL auto_increment,
  `login_id` int(64) default NULL,
  `amount` decimal(10,3) default NULL,
  `valid_upto` varchar(20) NOT NULL COMMENT 'Date of Expiry (YYYYMMDD)',
  `wl_id` varchar(100) NOT NULL,
  `admin_status` enum('Due','Paid') NOT NULL default 'Due',
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`wallet_id`),
  KEY `login_id` (`login_id`)
);






CREATE TABLE IF NOT EXISTS `WalletTransactions` (
  `wallet_transaction_id` int(64) NOT NULL auto_increment,
  `wallet_id` int(64) default NULL,
  `invoice_id` varchar(100) NOT NULL,
  `datetime` varchar(100) default NULL,
  `remarks` varchar(1000) default NULL,
  `amount` decimal(10,3) default NULL,
  `type` enum('debit','credit','admin') default 'credit',
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`wallet_transaction_id`),
  KEY `wallet_id` (`wallet_id`)
);






CREATE TABLE IF NOT EXISTS `Zone` (
  `zone_id` int(11) NOT NULL auto_increment,
  `zone_name` varchar(100) NOT NULL,
  `version` int(11) NOT NULL default '1',
  `meta_data` varchar(200) default NULL,
  PRIMARY KEY  (`zone_id`),
  UNIQUE KEY `zone_name` (`zone_name`)
);





create table DirectTransactions (
transaction_id int not null auto_increment primary key,
customer_id int,
restaurant_id int,
amount double not null default 0.0,
otp int not null,
from_time varchar(30),
to_time varchar(30),
status enum('pending','approved','rejected'),
table_version int not null default 1,
meta_data varchar(200)
);


























































































































 */