package com.app.gofoodie;

public class gfdb {
}


/*




 ALTER TABLE Admin                       ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE CartItem                    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Category                    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE City                        ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE ComboOrders                 ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE ComboPlan                   ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE ComboPlanItems              ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Country                     ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Cuisine                     ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE CustomerDetail              ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE CustomerShortlistedBranches ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Items                       ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Location                    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Login                       ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE OrderSet                    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE PaymentTransactions         ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RechargePlan                ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RestaurantBranchCategories  ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RestaurantBranchLocation    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RestaurantDetail            ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RestaurantReviews           ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE RestaurantType              ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Session                     ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE SimpleRecharge              ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE SubscribedPlans             ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE TelrPayment                 ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Vacation                    ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Wallet                      ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE WalletTransactions          ADD version INT NOT NULL DEFAULT 1;
 ALTER TABLE Zone                        ADD version INT NOT NULL DEFAULT 1;





 ALTER TABLE Admin                       ADD meta_data VARCHAR(200);
 ALTER TABLE CartItem                    ADD meta_data VARCHAR(200);
 ALTER TABLE Category                    ADD meta_data VARCHAR(200);
 ALTER TABLE City                        ADD meta_data VARCHAR(200);
 ALTER TABLE ComboOrders                 ADD meta_data VARCHAR(200);
 ALTER TABLE ComboPlan                   ADD meta_data VARCHAR(200);
 ALTER TABLE ComboPlanItems              ADD meta_data VARCHAR(200);
 ALTER TABLE Country                     ADD meta_data VARCHAR(200);
 ALTER TABLE Cuisine                     ADD meta_data VARCHAR(200);
 ALTER TABLE CustomerDetail              ADD meta_data VARCHAR(200);
 ALTER TABLE CustomerShortlistedBranches ADD meta_data VARCHAR(200);
 ALTER TABLE Items                       ADD meta_data VARCHAR(200);
 ALTER TABLE Location                    ADD meta_data VARCHAR(200);
 ALTER TABLE Login                       ADD meta_data VARCHAR(200);
 ALTER TABLE OrderSet                    ADD meta_data VARCHAR(200);
 ALTER TABLE PaymentTransactions         ADD meta_data VARCHAR(200);
 ALTER TABLE RechargePlan                ADD meta_data VARCHAR(200);
 ALTER TABLE RestaurantBranchCategories  ADD meta_data VARCHAR(200);
 ALTER TABLE RestaurantBranchLocation    ADD meta_data VARCHAR(200);
 ALTER TABLE RestaurantDetail            ADD meta_data VARCHAR(200);
 ALTER TABLE RestaurantReviews           ADD meta_data VARCHAR(200);
 ALTER TABLE RestaurantType              ADD meta_data VARCHAR(200);
 ALTER TABLE Session                     ADD meta_data VARCHAR(200);
 ALTER TABLE SimpleRecharge              ADD meta_data VARCHAR(200);
 ALTER TABLE SubscribedPlans             ADD meta_data VARCHAR(200);
 ALTER TABLE TelrPayment                 ADD meta_data VARCHAR(200);
 ALTER TABLE Vacation                    ADD meta_data VARCHAR(200);
 ALTER TABLE Wallet                      ADD meta_data VARCHAR(200);
 ALTER TABLE WalletTransactions          ADD meta_data VARCHAR(200);
 ALTER TABLE Zone                        ADD meta_data VARCHAR(200);






 ALTER TABLE Admin                       DROP version;
 ALTER TABLE CartItem                    DROP version;
 ALTER TABLE Category                    DROP version;
 ALTER TABLE City                        DROP version;
 ALTER TABLE ComboOrders                 DROP version;
 ALTER TABLE ComboPlan                   DROP version;
 ALTER TABLE ComboPlanItems              DROP version;
 ALTER TABLE Country                     DROP version;
 ALTER TABLE Cuisine                     DROP version;
 ALTER TABLE CustomerDetail              DROP version;
 ALTER TABLE CustomerShortlistedBranches DROP version;
 ALTER TABLE Items                       DROP version;
 ALTER TABLE Location                    DROP version;
 ALTER TABLE Login                       DROP version;
 ALTER TABLE OrderSet                    DROP version;
 ALTER TABLE PaymentTransactions         DROP version;
 ALTER TABLE RechargePlan                DROP version;
 ALTER TABLE RestaurantBranchCategories  DROP version;
 ALTER TABLE RestaurantBranchLocation    DROP version;
 ALTER TABLE RestaurantDetail            DROP version;
 ALTER TABLE RestaurantReviews           DROP version;
 ALTER TABLE RestaurantType              DROP version;
 ALTER TABLE Session                     DROP version;
 ALTER TABLE SimpleRecharge              DROP version;
 ALTER TABLE SubscribedPlans             DROP version;
 ALTER TABLE TelrPayment                 DROP version;
 ALTER TABLE Vacation                    DROP version;
 ALTER TABLE Wallet                      DROP version;
 ALTER TABLE WalletTransactions          DROP version;
 ALTER TABLE Zone                        DROP version;





 ALTER TABLE Admin                       DROP meta_data;
 ALTER TABLE CartItem                    DROP meta_data;
 ALTER TABLE Category                    DROP meta_data;
 ALTER TABLE City                        DROP meta_data;
 ALTER TABLE ComboOrders                 DROP meta_data;
 ALTER TABLE ComboPlan                   DROP meta_data;
 ALTER TABLE ComboPlanItems              DROP meta_data;
 ALTER TABLE Country                     DROP meta_data;
 ALTER TABLE Cuisine                     DROP meta_data;
 ALTER TABLE CustomerDetail              DROP meta_data;
 ALTER TABLE CustomerShortlistedBranches DROP meta_data;
 ALTER TABLE Items                       DROP meta_data;
 ALTER TABLE Location                    DROP meta_data;
 ALTER TABLE Login                       DROP meta_data;
 ALTER TABLE OrderSet                    DROP meta_data;
 ALTER TABLE PaymentTransactions         DROP meta_data;
 ALTER TABLE RechargePlan                DROP meta_data;
 ALTER TABLE RestaurantBranchCategories  DROP meta_data;
 ALTER TABLE RestaurantBranchLocation    DROP meta_data;
 ALTER TABLE RestaurantDetail            DROP meta_data;
 ALTER TABLE RestaurantReviews           DROP meta_data;
 ALTER TABLE RestaurantType              DROP meta_data;
 ALTER TABLE Session                     DROP meta_data;
 ALTER TABLE SimpleRecharge              DROP meta_data;
 ALTER TABLE SubscribedPlans             DROP meta_data;
 ALTER TABLE TelrPayment                 DROP meta_data;
 ALTER TABLE Vacation                    DROP meta_data;
 ALTER TABLE Wallet                      DROP meta_data;
 ALTER TABLE WalletTransactions          DROP meta_data;
 ALTER TABLE Zone                        DROP meta_data;






CREATE TABLE log_update_Admin                       AS SELECT * FROM Admin                       ;
CREATE TABLE log_update_CartItem                    AS SELECT * FROM CartItem                    ;
CREATE TABLE log_update_Category                    AS SELECT * FROM Category                    ;
CREATE TABLE log_update_City                        AS SELECT * FROM City                        ;
CREATE TABLE log_update_ComboOrders                 AS SELECT * FROM ComboOrders                 ;
CREATE TABLE log_update_ComboPlan                   AS SELECT * FROM ComboPlan                   ;
CREATE TABLE log_update_ComboPlanItems              AS SELECT * FROM ComboPlanItems              ;
CREATE TABLE log_update_Country                     AS SELECT * FROM Country                     ;
CREATE TABLE log_update_Cuisine                     AS SELECT * FROM Cuisine                     ;
CREATE TABLE log_update_CustomerDetail              AS SELECT * FROM CustomerDetail              ;
CREATE TABLE log_update_CustomerShortlistedBranches AS SELECT * FROM CustomerShortlistedBranches ;
CREATE TABLE log_update_Items                       AS SELECT * FROM Items                       ;
CREATE TABLE log_update_Location                    AS SELECT * FROM Location                    ;
CREATE TABLE log_update_Login                       AS SELECT * FROM Login                       ;
CREATE TABLE log_update_OrderSet                    AS SELECT * FROM OrderSet                    ;
CREATE TABLE log_update_PaymentTransactions         AS SELECT * FROM PaymentTransactions         ;
CREATE TABLE log_update_RechargePlan                AS SELECT * FROM RechargePlan                ;
CREATE TABLE log_update_RestaurantBranchCategories  AS SELECT * FROM RestaurantBranchCategories  ;
CREATE TABLE log_update_RestaurantBranchLocation    AS SELECT * FROM RestaurantBranchLocation    ;
CREATE TABLE log_update_RestaurantDetail            AS SELECT * FROM RestaurantDetail            ;
CREATE TABLE log_update_RestaurantReviews           AS SELECT * FROM RestaurantReviews           ;
CREATE TABLE log_update_RestaurantType              AS SELECT * FROM RestaurantType              ;
CREATE TABLE log_update_Session                     AS SELECT * FROM Session                     ;
CREATE TABLE log_update_SimpleRecharge              AS SELECT * FROM SimpleRecharge              ;
CREATE TABLE log_update_SubscribedPlans             AS SELECT * FROM SubscribedPlans             ;
CREATE TABLE log_update_TelrPayment                 AS SELECT * FROM TelrPayment                 ;
CREATE TABLE log_update_Vacation                    AS SELECT * FROM Vacation                    ;
CREATE TABLE log_update_Wallet                      AS SELECT * FROM Wallet                      ;
CREATE TABLE log_update_WalletTransactions          AS SELECT * FROM WalletTransactions          ;
CREATE TABLE log_update_Zone                        AS SELECT * FROM Zone                        ;






CREATE TABLE log_delete_Admin                       AS SELECT * FROM Admin                       ;
CREATE TABLE log_delete_CartItem                    AS SELECT * FROM CartItem                    ;
CREATE TABLE log_delete_Category                    AS SELECT * FROM Category                    ;
CREATE TABLE log_delete_City                        AS SELECT * FROM City                        ;
CREATE TABLE log_delete_ComboOrders                 AS SELECT * FROM ComboOrders                 ;
CREATE TABLE log_delete_ComboPlan                   AS SELECT * FROM ComboPlan                   ;
CREATE TABLE log_delete_ComboPlanItems              AS SELECT * FROM ComboPlanItems              ;
CREATE TABLE log_delete_Country                     AS SELECT * FROM Country                     ;
CREATE TABLE log_delete_Cuisine                     AS SELECT * FROM Cuisine                     ;
CREATE TABLE log_delete_CustomerDetail              AS SELECT * FROM CustomerDetail              ;
CREATE TABLE log_delete_CustomerShortlistedBranches AS SELECT * FROM CustomerShortlistedBranches ;
CREATE TABLE log_delete_Items                       AS SELECT * FROM Items                       ;
CREATE TABLE log_delete_Location                    AS SELECT * FROM Location                    ;
CREATE TABLE log_delete_Login                       AS SELECT * FROM Login                       ;
CREATE TABLE log_delete_OrderSet                    AS SELECT * FROM OrderSet                    ;
CREATE TABLE log_delete_PaymentTransactions         AS SELECT * FROM PaymentTransactions         ;
CREATE TABLE log_delete_RechargePlan                AS SELECT * FROM RechargePlan                ;
CREATE TABLE log_delete_RestaurantBranchCategories  AS SELECT * FROM RestaurantBranchCategories  ;
CREATE TABLE log_delete_RestaurantBranchLocation    AS SELECT * FROM RestaurantBranchLocation    ;
CREATE TABLE log_delete_RestaurantDetail            AS SELECT * FROM RestaurantDetail            ;
CREATE TABLE log_delete_RestaurantReviews           AS SELECT * FROM RestaurantReviews           ;
CREATE TABLE log_delete_RestaurantType              AS SELECT * FROM RestaurantType              ;
CREATE TABLE log_delete_Session                     AS SELECT * FROM Session                     ;
CREATE TABLE log_delete_SimpleRecharge              AS SELECT * FROM SimpleRecharge              ;
CREATE TABLE log_delete_SubscribedPlans             AS SELECT * FROM SubscribedPlans             ;
CREATE TABLE log_delete_TelrPayment                 AS SELECT * FROM TelrPayment                 ;
CREATE TABLE log_delete_Vacation                    AS SELECT * FROM Vacation                    ;
CREATE TABLE log_delete_Wallet                      AS SELECT * FROM Wallet                      ;
CREATE TABLE log_delete_WalletTransactions          AS SELECT * FROM WalletTransactions          ;
CREATE TABLE log_delete_Zone                        AS SELECT * FROM Zone                        ;







TRUNCATE TABLE Admin                       ;
TRUNCATE TABLE CartItem                    ;
TRUNCATE TABLE Category                    ;
TRUNCATE TABLE City                        ;
TRUNCATE TABLE ComboOrders                 ;
TRUNCATE TABLE ComboPlan                   ;
TRUNCATE TABLE ComboPlanItems              ;
TRUNCATE TABLE Country                     ;
TRUNCATE TABLE Cuisine                     ;
TRUNCATE TABLE CustomerDetail              ;
TRUNCATE TABLE CustomerShortlistedBranches ;
TRUNCATE TABLE Items                       ;
TRUNCATE TABLE Location                    ;
TRUNCATE TABLE Login                       ;
TRUNCATE TABLE OrderSet                    ;
TRUNCATE TABLE PaymentTransactions         ;
TRUNCATE TABLE RechargePlan                ;
TRUNCATE TABLE RestaurantBranchCategories  ;
TRUNCATE TABLE RestaurantBranchLocation    ;
TRUNCATE TABLE RestaurantDetail            ;
TRUNCATE TABLE RestaurantReviews           ;
TRUNCATE TABLE RestaurantType              ;
TRUNCATE TABLE Session                     ;
TRUNCATE TABLE SimpleRecharge              ;
TRUNCATE TABLE SubscribedPlans             ;
TRUNCATE TABLE TelrPayment                 ;
TRUNCATE TABLE Vacation                    ;
TRUNCATE TABLE Wallet                      ;
TRUNCATE TABLE WalletTransactions          ;
TRUNCATE TABLE Zone                        ;






TRUNCATE TABLE log_update_Admin                       ;
TRUNCATE TABLE log_update_CartItem                    ;
TRUNCATE TABLE log_update_Category                    ;
TRUNCATE TABLE log_update_City                        ;
TRUNCATE TABLE log_update_ComboOrders                 ;
TRUNCATE TABLE log_update_ComboPlan                   ;
TRUNCATE TABLE log_update_ComboPlanItems              ;
TRUNCATE TABLE log_update_Country                     ;
TRUNCATE TABLE log_update_Cuisine                     ;
TRUNCATE TABLE log_update_CustomerDetail              ;
TRUNCATE TABLE log_update_CustomerShortlistedBranches ;
TRUNCATE TABLE log_update_Items                       ;
TRUNCATE TABLE log_update_Location                    ;
TRUNCATE TABLE log_update_Login                       ;
TRUNCATE TABLE log_update_OrderSet                    ;
TRUNCATE TABLE log_update_PaymentTransactions         ;
TRUNCATE TABLE log_update_RechargePlan                ;
TRUNCATE TABLE log_update_RestaurantBranchCategories  ;
TRUNCATE TABLE log_update_RestaurantBranchLocation    ;
TRUNCATE TABLE log_update_RestaurantDetail            ;
TRUNCATE TABLE log_update_RestaurantReviews           ;
TRUNCATE TABLE log_update_RestaurantType              ;
TRUNCATE TABLE log_update_Session                     ;
TRUNCATE TABLE log_update_SimpleRecharge              ;
TRUNCATE TABLE log_update_SubscribedPlans             ;
TRUNCATE TABLE log_update_TelrPayment                 ;
TRUNCATE TABLE log_update_Vacation                    ;
TRUNCATE TABLE log_update_Wallet                      ;
TRUNCATE TABLE log_update_WalletTransactions          ;
TRUNCATE TABLE log_update_Zone                        ;






TRUNCATE TABLE log_delete_Admin                       ;
TRUNCATE TABLE log_delete_CartItem                    ;
TRUNCATE TABLE log_delete_Category                    ;
TRUNCATE TABLE log_delete_City                        ;
TRUNCATE TABLE log_delete_ComboOrders                 ;
TRUNCATE TABLE log_delete_ComboPlan                   ;
TRUNCATE TABLE log_delete_ComboPlanItems              ;
TRUNCATE TABLE log_delete_Country                     ;
TRUNCATE TABLE log_delete_Cuisine                     ;
TRUNCATE TABLE log_delete_CustomerDetail              ;
TRUNCATE TABLE log_delete_CustomerShortlistedBranches ;
TRUNCATE TABLE log_delete_Items                       ;
TRUNCATE TABLE log_delete_Location                    ;
TRUNCATE TABLE log_delete_Login                       ;
TRUNCATE TABLE log_delete_OrderSet                    ;
TRUNCATE TABLE log_delete_PaymentTransactions         ;
TRUNCATE TABLE log_delete_RechargePlan                ;
TRUNCATE TABLE log_delete_RestaurantBranchCategories  ;
TRUNCATE TABLE log_delete_RestaurantBranchLocation    ;
TRUNCATE TABLE log_delete_RestaurantDetail            ;
TRUNCATE TABLE log_delete_RestaurantReviews           ;
TRUNCATE TABLE log_delete_RestaurantType              ;
TRUNCATE TABLE log_delete_Session                     ;
TRUNCATE TABLE log_delete_SimpleRecharge              ;
TRUNCATE TABLE log_delete_SubscribedPlans             ;
TRUNCATE TABLE log_delete_TelrPayment                 ;
TRUNCATE TABLE log_delete_Vacation                    ;
TRUNCATE TABLE log_delete_Wallet                      ;
TRUNCATE TABLE log_delete_WalletTransactions          ;
TRUNCATE TABLE log_delete_Zone                        ;












 */