package com.app.gofoodie;

public class UpdateTriggers {
}

/*


DROP TRIGGER trig_update_Admin;
DROP TRIGGER trig_update_CartItem;
DROP TRIGGER trig_update_Category;
DROP TRIGGER trig_update_City;
DROP TRIGGER trig_update_ComboOrders;
DROP TRIGGER trig_update_ComboPlan;
DROP TRIGGER trig_update_ComboPlanItems;
DROP TRIGGER trig_update_Country;
DROP TRIGGER trig_update_Cuisine;
DROP TRIGGER trig_update_CustomerDetail;
DROP TRIGGER trig_update_CustomerShortlistedBranches;
DROP TRIGGER trig_update_Items;
DROP TRIGGER trig_update_Location;
DROP TRIGGER trig_update_Login;
DROP TRIGGER trig_update_OrderSet;
DROP TRIGGER trig_update_PaymentTransactions;
DROP TRIGGER trig_update_RechargePlan;
DROP TRIGGER trig_update_RestaurantBranchCategories;
DROP TRIGGER trig_update_RestaurantBranchLocation;
DROP TRIGGER trig_update_RestaurantDetail;
DROP TRIGGER trig_update_RestaurantReviews;
DROP TRIGGER trig_update_RestaurantType;
DROP TRIGGER trig_update_Session;
DROP TRIGGER trig_update_SimpleRecharge;
DROP TRIGGER trig_update_SubscribedPlans;
DROP TRIGGER trig_update_TelrPayment;
DROP TRIGGER trig_update_Vacation;
DROP TRIGGER trig_update_Wallet;
DROP TRIGGER trig_update_WalletTransactions;
DROP TRIGGER trig_update_Zone;




DELIMITER $$
CREATE TRIGGER trig_update_Admin
BEFORE UPDATE ON Admin
FOR EACH ROW
BEGIN
insert into log_update_Admin select * from Admin where id = old.id;
update log_update_Admin set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_CartItem
BEFORE UPDATE ON CartItem
FOR EACH ROW
BEGIN
insert into log_update_CartItem select * from CartItem where cart_item_id = old.cart_item_id;
update log_update_CartItem set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cart_item_id = old.cart_item_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Category
BEFORE UPDATE ON Category
FOR EACH ROW
BEGIN
insert into log_update_Category select * from Category where cate_id = old.cate_id;
update log_update_Category set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cate_id = old.cate_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_City
BEFORE UPDATE ON City
FOR EACH ROW
BEGIN
insert into log_update_City select * from City where city_id = old.city_id;
update log_update_City set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where city_id = old.city_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_ComboOrders
BEFORE UPDATE ON ComboOrders
FOR EACH ROW
BEGIN
insert into log_update_ComboOrders select * from ComboOrders where order_id = old.order_id;
update log_update_ComboOrders set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_id = old.order_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_ComboPlan
BEFORE UPDATE ON ComboPlan
FOR EACH ROW
BEGIN
insert into log_update_ComboPlan select * from ComboPlan where combo_id = old.combo_id;
update log_update_ComboPlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_id = old.combo_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_ComboPlanItems
BEFORE UPDATE ON ComboPlanItems
FOR EACH ROW
BEGIN
insert into log_update_ComboPlanItems select * from ComboPlanItems where combo_item_id = old.combo_item_id;
update log_update_ComboPlanItems set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_item_id = old.combo_item_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Country
BEFORE UPDATE ON Country
FOR EACH ROW
BEGIN
insert into log_update_Country select * from Country where id = old.id;
update log_update_Country set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Cuisine
BEFORE UPDATE ON Cuisine
FOR EACH ROW
BEGIN
insert into log_update_Cuisine select * from Cuisine where cuisine_id = old.cuisine_id;
update log_update_Cuisine set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cuisine_id = old.cuisine_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_CustomerDetail
BEFORE UPDATE ON CustomerDetail
FOR EACH ROW
BEGIN
insert into log_update_CustomerDetail select * from CustomerDetail where customer_id = old.customer_id;
update log_update_CustomerDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where customer_id = old.customer_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_CustomerShortlistedBranches
BEFORE UPDATE ON CustomerShortlistedBranches
FOR EACH ROW
BEGIN
insert into log_update_CustomerShortlistedBranches select * from CustomerShortlistedBranches where shortlist_id = old.shortlist_id;
update log_update_CustomerShortlistedBranches set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where shortlist_id = old.shortlist_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Items
BEFORE UPDATE ON Items
FOR EACH ROW
BEGIN
insert into log_update_Items select * from Items where item_id = old.item_id;
update log_update_Items set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where item_id = old.item_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Location
BEFORE UPDATE ON Location
FOR EACH ROW
BEGIN
insert into log_update_Location select * from Location where area_id = old.area_id;
update log_update_Location set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where area_id = old.area_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Login
BEFORE UPDATE ON Login
FOR EACH ROW
BEGIN
insert into log_update_Login select * from Login where login_id = old.login_id;
update log_update_Login set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where login_id = old.login_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_OrderSet
BEFORE UPDATE ON OrderSet
FOR EACH ROW
BEGIN
insert into log_update_OrderSet select * from OrderSet where order_set_id = old.order_set_id;
update log_update_OrderSet set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_set_id = old.order_set_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_PaymentTransactions
BEFORE UPDATE ON PaymentTransactions
FOR EACH ROW
BEGIN
insert into log_update_PaymentTransactions select * from PaymentTransactions where transaction_id = old.transaction_id;
update log_update_PaymentTransactions set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where transaction_id = old.transaction_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RechargePlan
BEFORE UPDATE ON RechargePlan
FOR EACH ROW
BEGIN
insert into log_update_RechargePlan select * from RechargePlan where plan_id = old.plan_id;
update log_update_RechargePlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where plan_id = old.plan_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RestaurantBranchCategories
BEFORE UPDATE ON RestaurantBranchCategories
FOR EACH ROW
BEGIN
insert into log_update_RestaurantBranchCategories select * from RestaurantBranchCategories where branch_category_id = old.branch_category_id;
update log_update_RestaurantBranchCategories set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_category_id = old.branch_category_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RestaurantBranchLocation
BEFORE UPDATE ON RestaurantBranchLocation
FOR EACH ROW
BEGIN
insert into log_update_RestaurantBranchLocation select * from RestaurantBranchLocation where branch_id = old.branch_id;
update log_update_RestaurantBranchLocation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_id = old.branch_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RestaurantDetail
BEFORE UPDATE ON RestaurantDetail
FOR EACH ROW
BEGIN
insert into log_update_RestaurantDetail select * from RestaurantDetail where restaurant_id = old.restaurant_id;
update log_update_RestaurantDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restaurant_id = old.restaurant_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RestaurantReviews
BEFORE UPDATE ON RestaurantReviews
FOR EACH ROW
BEGIN
insert into log_update_RestaurantReviews select * from RestaurantReviews where id = old.id;
update log_update_RestaurantReviews set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_RestaurantType
BEFORE UPDATE ON RestaurantType
FOR EACH ROW
BEGIN
insert into log_update_RestaurantType select * from RestaurantType where restro_type_id = old.restro_type_id;
update log_update_RestaurantType set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restro_type_id = old.restro_type_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Session
BEFORE UPDATE ON Session
FOR EACH ROW
BEGIN
insert into log_update_Session select * from Session where id = old.id;
update log_update_Session set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_SimpleRecharge
BEFORE UPDATE ON SimpleRecharge
FOR EACH ROW
BEGIN
insert into log_update_SimpleRecharge select * from SimpleRecharge where recharge_id = old.recharge_id;
update log_update_SimpleRecharge set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where recharge_id = old.recharge_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_SubscribedPlans
BEFORE UPDATE ON SubscribedPlans
FOR EACH ROW
BEGIN
insert into log_update_SubscribedPlans select * from SubscribedPlans where subscription_id = old.subscription_id;
update log_update_SubscribedPlans set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where subscription_id = old.subscription_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_TelrPayment
BEFORE UPDATE ON TelrPayment
FOR EACH ROW
BEGIN
insert into log_update_TelrPayment select * from TelrPayment where telr_id = old.telr_id;
update log_update_TelrPayment set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where telr_id = old.telr_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Vacation
BEFORE UPDATE ON Vacation
FOR EACH ROW
BEGIN
insert into log_update_Vacation select * from Vacation where vacation_id = old.vacation_id;
update log_update_Vacation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where vacation_id = old.vacation_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_Wallet
BEFORE UPDATE ON Wallet
FOR EACH ROW
BEGIN
insert into log_update_Wallet select * from Wallet where wallet_id = old.wallet_id;
update log_update_Wallet set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where wallet_id = old.wallet_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_update_WalletTransactions
BEFORE UPDATE ON WalletTransactions
FOR EACH ROW
BEGIN
insert into log_update_WalletTransactions select * from WalletTransactions where wallet_transaction_id = old.wallet_transaction_id;
update log_update_WalletTransactions set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where wallet_transaction_id = old.wallet_transaction_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;





DELIMITER $$
CREATE TRIGGER trig_update_Zone
BEFORE UPDATE ON Zone
FOR EACH ROW
BEGIN
insert into log_update_Zone select * from Zone where zone_id = old.zone_id;
update log_update_Zone set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where zone_id = old.zone_id and version = old.version;
set new.version = old.version + 1;
END$$
DELIMITER ;









 */