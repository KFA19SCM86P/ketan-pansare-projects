use ewa;
SELECT * FROM stores;

CREATE TABLE `stores` (
  `idStores` int NOT NULL,
  `Street` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Zip-Code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStores`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
