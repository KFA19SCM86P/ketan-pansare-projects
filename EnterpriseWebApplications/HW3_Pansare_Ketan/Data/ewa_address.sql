use ewa;
select * from address;
CREATE TABLE `address` (
  `order_id` int NOT NULL,
  `u_name` varchar(45) DEFAULT NULL,
  `cstreet` varchar(45) DEFAULT NULL,
  `ccity` varchar(45) DEFAULT NULL,
  `cstate` varchar(45) DEFAULT NULL,
  `czipcode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
