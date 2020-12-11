use sampledb;
CREATE TABLE `customerorders` (
  `OrderId` int NOT NULL,
  `userName` varchar(40) NOT NULL,
  `orderName` varchar(40) NOT NULL,
  `orderPrice` double DEFAULT NULL,
  `userAddress` varchar(40) DEFAULT NULL,
  `creditCardNo` varchar(40) DEFAULT NULL,
  `orderTime` varchar(255) DEFAULT NULL,
  `customerAge` varchar(40) DEFAULT NULL,
  `customerCity` varchar(40) DEFAULT NULL,
  `customerStreet` varchar(40) DEFAULT NULL,
  `customerZip` varchar(40) DEFAULT NULL,
  `deliveryType` varchar(40) DEFAULT NULL,
  `deliveryLocation` varchar(40) DEFAULT NULL,
  `expectedDate` varchar(40) DEFAULT NULL,
  `retailerName` varchar(40) DEFAULT NULL,
  `retType` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`userName`,`orderName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use sampledb;
CREATE TABLE `product_accessories` (
  `productName` varchar(20) DEFAULT NULL,
  `accessoriesName` varchar(20) DEFAULT NULL,
  KEY `productName` (`productName`),
  KEY `accessoriesName` (`accessoriesName`),
  CONSTRAINT `product_accessories_ibfk_1` FOREIGN KEY (`productName`) REFERENCES `productdetails` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `product_accessories_ibfk_2` FOREIGN KEY (`accessoriesName`) REFERENCES `productdetails` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use sampledb;
CREATE TABLE `productdetails` (
  `ProductType` varchar(20) DEFAULT NULL,
  `Id` varchar(20) NOT NULL,
  `productName` varchar(40) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productImage` varchar(40) DEFAULT NULL,
  `productManufacturer` varchar(40) DEFAULT NULL,
  `productCondition` varchar(40) DEFAULT NULL,
  `productDiscount` double DEFAULT NULL,
  `productInventory` int DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use sampledb;
CREATE TABLE `registration` (
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `repassword` varchar(40) DEFAULT NULL,
  `usertype` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use sampledb;
CREATE TABLE `transaction` (
  `OrderId` int NOT NULL,
  `userName` varchar(40) NOT NULL,
  `orderName` varchar(40) NOT NULL,
  `orderPrice` double DEFAULT NULL,
  `userAddress` varchar(40) DEFAULT NULL,
  `creditCardNo` varchar(40) DEFAULT NULL,
  `orderTime` varchar(255) DEFAULT NULL,
  `customerAge` varchar(40) DEFAULT NULL,
  `customerCity` varchar(40) DEFAULT NULL,
  `customerStreet` varchar(40) DEFAULT NULL,
  `customerZip` varchar(40) DEFAULT NULL,
  `deliveryType` varchar(40) DEFAULT NULL,
  `deliveryLocation` varchar(40) DEFAULT NULL,
  `expectedDate` varchar(40) DEFAULT NULL,
  `actualDate` varchar(40) DEFAULT NULL,
  `orderOnTime` int DEFAULT NULL,
  `retailerName` varchar(40) DEFAULT NULL,
  `orderReturned` int DEFAULT NULL,
  `reviewRating` int DEFAULT NULL,
  `retType` varchar(40) DEFAULT NULL,
  `customerOccupation` varchar(40) DEFAULT NULL,
  `trackingid` varchar(40) DEFAULT NULL,
  `transactionStatus` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`userName`,`orderName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

use sampledb;
select * from registration;

use sampledb;
select * from transaction;

use sampledb;
select * from customerorders;
