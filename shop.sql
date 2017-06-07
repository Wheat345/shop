use shop;
drop table if exists product;
drop table if exists transaction;
drop table if exists customer;

CREATE TABLE `customer` (
  `customerid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `productid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `catalog` varchar(100) NOT NULL,
  `format` varchar(500) NOT NULL,
  `cost` varchar(100) NOT NULL,
  `sellprice` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `transaction` (
  `transactionid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customerid` int(10) unsigned NOT NULL,
  `productids` varchar(500) NOT NULL,
  `created` datetime NOT NULL,
  `paymentmethod` varchar(100) NOT NULL,
  `details` varchar(500) NOT NULL,
  PRIMARY KEY (`transactionid`),
  FOREIGN KEY (`customerid`) REFERENCES customer(`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;









