package com.app.gofoodie;

public class DeleteTriggers {
}

/*






DELIMITER $$
CREATE TRIGGER trig_delete_Admin
BEFORE DELETE ON Admin
FOR EACH ROW
BEGIN
	insert into log_update_Admin select * from Admin where id = old.id;
	update log_update_Admin set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_CartItem
BEFORE DELETE ON CartItem
FOR EACH ROW
BEGIN
	insert into log_update_CartItem select * from CartItem where cart_item_id = old.cart_item_id;
	update log_update_CartItem set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cart_item_id = old.cart_item_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Category
BEFORE DELETE ON Category
FOR EACH ROW
BEGIN
	insert into log_update_Category select * from Category where cate_id = old.cate_id;
	update log_update_Category set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cate_id = old.cate_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_City
BEFORE DELETE ON City
FOR EACH ROW
BEGIN
	insert into log_update_City select * from City where city_id = old.city_id;
	update log_update_City set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where city_id = old.city_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_ComboOrders
BEFORE DELETE ON ComboOrders
FOR EACH ROW
BEGIN
	insert into log_update_ComboOrders select * from ComboOrders where order_id = old.order_id;
	update log_update_ComboOrders set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_id = old.order_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_ComboPlan
BEFORE DELETE ON ComboPlan
FOR EACH ROW
BEGIN
	insert into log_update_ComboPlan select * from ComboPlan where combo_id = old.combo_id;
	update log_update_ComboPlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_id = old.combo_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_ComboPlanItems
BEFORE DELETE ON ComboPlanItems
FOR EACH ROW
BEGIN
	insert into log_update_ComboPlanItems select * from ComboPlanItems where combo_item_id = old.combo_item_id;
	update log_update_ComboPlanItems set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where combo_item_id = old.combo_item_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Country
BEFORE DELETE ON Country
FOR EACH ROW
BEGIN
	insert into log_update_Country select * from Country where id = old.id;
	update log_update_Country set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Cuisine
BEFORE DELETE ON Cuisine
FOR EACH ROW
BEGIN
	insert into log_update_Cuisine select * from Cuisine where cuisine_id = old.cuisine_id;
	update log_update_Cuisine set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where cuisine_id = old.cuisine_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_CustomerDetail
BEFORE DELETE ON CustomerDetail
FOR EACH ROW
BEGIN
	insert into log_update_CustomerDetail select * from CustomerDetail where customer_id = old.customer_id;
	update log_update_CustomerDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where customer_id = old.customer_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_CustomerShortlistedBranches
BEFORE DELETE ON CustomerShortlistedBranches
FOR EACH ROW
BEGIN
	insert into log_update_CustomerShortlistedBranches select * from CustomerShortlistedBranches where shortlist_id = old.shortlist_id;
	update log_update_CustomerShortlistedBranches set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where shortlist_id = old.shortlist_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Items
BEFORE DELETE ON Items
FOR EACH ROW
BEGIN
	insert into log_update_Items select * from Items where item_id = old.item_id;
	update log_update_Items set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where item_id = old.item_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Location
BEFORE DELETE ON Location
FOR EACH ROW
BEGIN
	insert into log_update_Location select * from Location where area_id = old.area_id;
	update log_update_Location set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where area_id = old.area_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Login
BEFORE DELETE ON Login
FOR EACH ROW
BEGIN
	insert into log_update_Login select * from Login where login_id = old.login_id;
	update log_update_Login set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where login_id = old.login_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_OrderSet
BEFORE DELETE ON OrderSet
FOR EACH ROW
BEGIN
	insert into log_update_OrderSet select * from OrderSet where order_set_id = old.order_set_id;
	update log_update_OrderSet set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where order_set_id = old.order_set_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_PaymentTransactions
BEFORE DELETE ON PaymentTransactions
FOR EACH ROW
BEGIN
	insert into log_update_PaymentTransactions select * from PaymentTransactions where transaction_id = old.transaction_id;
	update log_update_PaymentTransactions set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where transaction_id = old.transaction_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RechargePlan
BEFORE DELETE ON RechargePlan
FOR EACH ROW
BEGIN
	insert into log_update_RechargePlan * from RechargePlan where plan_id = old.plan_id;
	update log_update_RechargePlan set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where plan_id = old.plan_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantBranchCategories
BEFORE DELETE ON RestaurantBranchCategories
FOR EACH ROW
BEGIN
	insert into log_update_RestaurantBranchCategories select * from RestaurantBranchCategories where branch_category_id = old.branch_category_id;
	update log_update_RestaurantBranchCategories set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_category_id = old.branch_category_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantBranchLocation
BEFORE DELETE ON RestaurantBranchLocation
FOR EACH ROW
BEGIN
	insert into log_update_RestaurantBranchLocation select * from RestaurantBranchLocation where branch_id = old.branch_id;
	update log_update_RestaurantBranchLocation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where branch_id = old.branch_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantDetail
BEFORE DELETE ON RestaurantDetail
FOR EACH ROW
BEGIN
	insert into log_update_RestaurantDetail select * from RestaurantDetail where restaurant_id = old.restaurant_id;
	update log_update_RestaurantDetail set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restaurant_id = old.restaurant_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantReviews
BEFORE DELETE ON RestaurantReviews
FOR EACH ROW
BEGIN
	insert into log_update_RestaurantReviews select * from RestaurantReviews where id = old.id;
	update log_update_RestaurantReviews set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_RestaurantType
BEFORE DELETE ON RestaurantType
FOR EACH ROW
BEGIN
	insert into log_update_RestaurantType select * from RestaurantType where restro_type_id = old.restro_type_id;
	update log_update_RestaurantType set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where restro_type_id = old.restro_type_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Session
BEFORE DELETE ON Session
FOR EACH ROW
BEGIN
	insert into log_update_Session select * from Session where id = old.id;
	update log_update_Session set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where id = old.id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_SimpleRecharge
BEFORE DELETE ON SimpleRecharge
FOR EACH ROW
BEGIN
	insert into log_update_SimpleRecharge select * from SimpleRecharge where recharge_id = old.recharge_id;
	update log_update_SimpleRecharge set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where recharge_id = old.recharge_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_SubscribedPlans
BEFORE DELETE ON SubscribedPlans
FOR EACH ROW
BEGIN
	insert into log_update_SubscribedPlans select * from SubscribedPlans where subscription_id = old.subscription_id;
	update log_update_SubscribedPlans set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where subscription_id = old.subscription_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_TelrPayment
BEFORE DELETE ON TelrPayment
FOR EACH ROW
BEGIN
	insert into log_update_TelrPayment select * from TelrPayment where telr_id = old.telr_id;
	update log_update_TelrPayment set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where telr_id = old.telr_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER trig_delete_Vacation
BEFORE DELETE ON Vacation
FOR EACH ROW
BEGIN
	insert into log_update_Vacation select * from Vacation where vacation_id = old.vacation_id;
	update log_update_Vacation set meta_data = concat('LOG: ', curdate(), '_', current_time(), '.') where vacation_id = old.vacation_id and table_version = old.table_version;
	set new.table_version = old.table_version + 1;
END$$
DELIMITER ;























 */