DROP DATABASE loandb;

CREATE DATABASE loandb;

USE loandb;

CREATE TABLE Admin(
loginId INT NOT NULL,
password varchar(20) NOT NULL);


INSERT INTO Admin (loginId, password)VALUES
(admin, "admin");


CREATE TABLE LoanInfo
(applno VARCHAR(10) PRIMARY KEY,
 purpose VARCHAR(25) NOT NULL,
 amtrequested INT NOT NULL,
 doa VARCHAR(20) NOT NULL,
 bstructure VARCHAR(20) NOT NULL,
 bindicator VARCHAR(20) NOT NULL,
 address VARCHAR(50) NOT NULL,
 email VARCHAR(50) NOT NULL,
 mobile VARCHAR(13) NOT NULL,
 status VARCHAR(20) NOT NULL
);

INSERT INTO LoanInfo(applno,purpose,amtrequest,doa,bstructure,bindicator,address, email, mobile,status) VALUES
(L001,"Personal","5000","17-11-2020","Organisation","Salaried","Banglore","abc@gmail.com","9231475690","In Progress"),
(L002,"Personal","20000","17-11-2020","Individual","Non-Salaried","Banglore","abc1@gmail.com","9288475690","In Progress"),
(L003,"Business","20000","17-11-2020","Individual","Non-Salaried","Hydrabad","xyz@gmail.com","9288475622","In Progress");


CREATE TABLE ApprovedLoan
(applno VARCHAR(10) FOREIGN KEY REFERENCES LoanInfo(applno),
 amotsanctioned INT NOT NULL,
 loanterm INT NOT NULL,
 psd VARCHAR(10),
 lcd VARCHAR(10),
 emi INT);
 
 





