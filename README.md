EWallet
-> NotificationService
-> UserService
-> TransactionService
-> WalletService
NS:6061 US:8081 TS:5051 WS:7071

//Mailtrap

//MYSQL CMD ---------------------------------------------------------------------------------------------------
-> mysql -u root -p
-> *******
-> show databases;
-> use ewallet;
-> desc wallet; desc user; desc txn;

//POSTMAN------------------------------------------------------------------------------------------------------
http://localhost:5051/txn/initTxn?reciver= &purpose= &amount=
/txn/initTxn/ (userAuthority) POST
Auth -> Basic Auth : contact/password

http://localhost:8081/user/addUpdate
{
    "name": "Aalok",
    "contact": "123456789",
    "email": "aalok@gmail.com",
    "address": "Nagpur",
    "dob": "03/11/2003",
    "password": "aalok",
    "userIdentifier": "PAN",
    "userIdentifierValue": "ABC12345"
}
http://localhost:8081/user/userDetails
/user/userDetails/ adminAuthority,serviceAuthority
Auth -> Basic Auth : admin/password

//Kafka-------------------------------------------------------------------------------------------------------
go to C:/kafka -> Open cmd
start Zookeeper : .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
start Kafka : .\bin\windows\kafka-server-start.bat .\config\server.properties

//Project------------------------------------------------------------------------------------------------------
Run Severs(Service Applications)
Create topics manually if needed
UserCreation -> create user, send welcome notification, create wallet with base balance, send mail
Txn -> send money with message from user1 to user 2
user1 -> txnService -> user2
             ^
             |
         UserService
    ( to authenticate user)
