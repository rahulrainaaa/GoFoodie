package com.app.gofoodie;

public class DeleteTriggers {
}

/*


DROP TRIGGER trig_delete_Admin;
DROP TRIGGER trig_delete_CartItem;
DROP TRIGGER trig_delete_Category;
DROP TRIGGER trig_delete_City;
DROP TRIGGER trig_delete_ComboOrders;
DROP TRIGGER trig_delete_ComboPlan;
DROP TRIGGER trig_delete_ComboPlanItems;
DROP TRIGGER trig_delete_Country;
DROP TRIGGER trig_delete_Cuisine;
DROP TRIGGER trig_delete_CustomerDetail;
DROP TRIGGER trig_delete_CustomerShortlistedBranches;
DROP TRIGGER trig_delete_Items;
DROP TRIGGER trig_delete_Location;
DROP TRIGGER trig_delete_Login;
DROP TRIGGER trig_delete_OrderSet;
DROP TRIGGER trig_delete_PaymentTransactions;
DROP TRIGGER trig_delete_RechargePlan;
DROP TRIGGER trig_delete_RestaurantBranchCategories;
DROP TRIGGER trig_delete_RestaurantBranchLocation;
DROP TRIGGER trig_delete_RestaurantDetail;
DROP TRIGGER trig_delete_RestaurantReviews;
DROP TRIGGER trig_delete_RestaurantType;
DROP TRIGGER trig_delete_Session;
DROP TRIGGER trig_delete_SimpleRecharge;
DROP TRIGGER trig_delete_SubscribedPlans;
DROP TRIGGER trig_delete_TelrPayment;
DROP TRIGGER trig_delete_Vacation;
DROP TRIGGER trig_delete_Wallet;
DROP TRIGGER trig_delete_WalletTransactions;
DROP TRIGGER trig_delete_Zone;




DELIMITER $$
CREATE TRIGGER trig_delete_Admin
BEFORE DELETE ON Admin
FOR EACH ROW
BEGIN
insert into log_delete_Admin select * from Admin where id = old.id;
update log_delete_Admin set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_CartItem
BEFORE DELETE ON CartItem
FOR EACH ROW
BEGIN
insert into log_delete_CartItem select * from CartItem where cart_item_id = old.cart_item_id;
update log_delete_CartItem set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cart_item_id = old.cart_item_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Category
BEFORE DELETE ON Category
FOR EACH ROW
BEGIN
insert into log_delete_Category select * from Category where cate_id = old.cate_id;
update log_delete_Category set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cate_id = old.cate_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_City
BEFORE DELETE ON City
FOR EACH ROW
BEGIN
insert into log_delete_City select * from City where city_id = old.city_id;
update log_delete_City set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where city_id = old.city_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_ComboOrders
BEFORE DELETE ON ComboOrders
FOR EACH ROW
BEGIN
insert into log_delete_ComboOrders select * from ComboOrders where order_id = old.order_id;
update log_delete_ComboOrders set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_id = old.order_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_ComboPlan
BEFORE DELETE ON ComboPlan
FOR EACH ROW
BEGIN
insert into log_delete_ComboPlan select * from ComboPlan where combo_id = old.combo_id;
update log_delete_ComboPlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_id = old.combo_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_ComboPlanItems
BEFORE DELETE ON ComboPlanItems
FOR EACH ROW
BEGIN
insert into log_delete_ComboPlanItems select * from ComboPlanItems where combo_item_id = old.combo_item_id;
update log_delete_ComboPlanItems set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_item_id = old.combo_item_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Country
BEFORE DELETE ON Country
FOR EACH ROW
BEGIN
insert into log_delete_Country select * from Country where id = old.id;
update log_delete_Country set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Cuisine
BEFORE DELETE ON Cuisine
FOR EACH ROW
BEGIN
insert into log_delete_Cuisine select * from Cuisine where cuisine_id = old.cuisine_id;
update log_delete_Cuisine set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cuisine_id = old.cuisine_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_CustomerDetail
BEFORE DELETE ON CustomerDetail
FOR EACH ROW
BEGIN
insert into log_delete_CustomerDetail select * from CustomerDetail where customer_id = old.customer_id;
update log_delete_CustomerDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where customer_id = old.customer_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_CustomerShortlistedBranches
BEFORE DELETE ON CustomerShortlistedBranches
FOR EACH ROW
BEGIN
insert into log_delete_CustomerShortlistedBranches select * from CustomerShortlistedBranches where shortlist_id = old.shortlist_id;
update log_delete_CustomerShortlistedBranches set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where shortlist_id = old.shortlist_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Items
BEFORE DELETE ON Items
FOR EACH ROW
BEGIN
insert into log_delete_Items select * from Items where item_id = old.item_id;
update log_delete_Items set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where item_id = old.item_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Location
BEFORE DELETE ON Location
FOR EACH ROW
BEGIN
insert into log_delete_Location select * from Location where area_id = old.area_id;
update log_delete_Location set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where area_id = old.area_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Login
BEFORE DELETE ON Login
FOR EACH ROW
BEGIN
insert into log_delete_Login select * from Login where login_id = old.login_id;
update log_delete_Login set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where login_id = old.login_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_OrderSet
BEFORE DELETE ON OrderSet
FOR EACH ROW
BEGIN
insert into log_delete_OrderSet select * from OrderSet where order_set_id = old.order_set_id;
update log_delete_OrderSet set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_set_id = old.order_set_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_PaymentTransactions
BEFORE DELETE ON PaymentTransactions
FOR EACH ROW
BEGIN
insert into log_delete_PaymentTransactions select * from PaymentTransactions where transaction_id = old.transaction_id;
update log_delete_PaymentTransactions set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where transaction_id = old.transaction_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RechargePlan
BEFORE DELETE ON RechargePlan
FOR EACH ROW
BEGIN
insert into log_delete_RechargePlan select * from RechargePlan where plan_id = old.plan_id;
update log_delete_RechargePlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where plan_id = old.plan_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantBranchCategories
BEFORE DELETE ON RestaurantBranchCategories
FOR EACH ROW
BEGIN
insert into log_delete_RestaurantBranchCategories select * from RestaurantBranchCategories where branch_category_id = old.branch_category_id;
update log_delete_RestaurantBranchCategories set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_category_id = old.branch_category_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantBranchLocation
BEFORE DELETE ON RestaurantBranchLocation
FOR EACH ROW
BEGIN
insert into log_delete_RestaurantBranchLocation select * from RestaurantBranchLocation where branch_id = old.branch_id;
update log_delete_RestaurantBranchLocation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_id = old.branch_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantDetail
BEFORE DELETE ON RestaurantDetail
FOR EACH ROW
BEGIN
insert into log_delete_RestaurantDetail select * from RestaurantDetail where restaurant_id = old.restaurant_id;
update log_delete_RestaurantDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restaurant_id = old.restaurant_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantReviews
BEFORE DELETE ON RestaurantReviews
FOR EACH ROW
BEGIN
insert into log_delete_RestaurantReviews select * from RestaurantReviews where id = old.id;
update log_delete_RestaurantReviews set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantType
BEFORE DELETE ON RestaurantType
FOR EACH ROW
BEGIN
insert into log_delete_RestaurantType select * from RestaurantType where restro_type_id = old.restro_type_id;
update log_delete_RestaurantType set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restro_type_id = old.restro_type_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Session
BEFORE DELETE ON Session
FOR EACH ROW
BEGIN
insert into log_delete_Session select * from Session where id = old.id;
update log_delete_Session set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_SimpleRecharge
BEFORE DELETE ON SimpleRecharge
FOR EACH ROW
BEGIN
insert into log_delete_SimpleRecharge select * from SimpleRecharge where recharge_id = old.recharge_id;
update log_delete_SimpleRecharge set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where recharge_id = old.recharge_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_SubscribedPlans
BEFORE DELETE ON SubscribedPlans
FOR EACH ROW
BEGIN
insert into log_delete_SubscribedPlans select * from SubscribedPlans where subscription_id = old.subscription_id;
update log_delete_SubscribedPlans set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where subscription_id = old.subscription_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_TelrPayment
BEFORE DELETE ON TelrPayment
FOR EACH ROW
BEGIN
insert into log_delete_TelrPayment select * from TelrPayment where telr_id = old.telr_id;
update log_delete_TelrPayment set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where telr_id = old.telr_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Vacation
BEFORE DELETE ON Vacation
FOR EACH ROW
BEGIN
insert into log_delete_Vacation select * from Vacation where vacation_id = old.vacation_id;
update log_delete_Vacation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where vacation_id = old.vacation_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Wallet
BEFORE DELETE ON Wallet
FOR EACH ROW
BEGIN
insert into log_delete_Wallet select * from Wallet where wallet_id = old.wallet_id;
update log_delete_Wallet set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where wallet_id = old.wallet_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_WalletTransactions
BEFORE DELETE ON WalletTransactions
FOR EACH ROW
BEGIN
insert into log_delete_WalletTransactions select * from WalletTransactions where wallet_transaction_id = old.wallet_transaction_id;
update log_delete_WalletTransactions set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where wallet_transaction_id = old.wallet_transaction_id and version = old.version;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trig_delete_Zone
BEFORE DELETE ON Zone
FOR EACH ROW
BEGIN
insert into log_delete_Zone select * from Zone where zone_id = old.zone_id;
update log_delete_Zone set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where zone_id = old.zone_id and version = old.version;
END$$
DELIMITER ;










 */