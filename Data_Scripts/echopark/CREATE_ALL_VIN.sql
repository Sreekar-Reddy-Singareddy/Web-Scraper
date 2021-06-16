CREATE TABLE `ALL_VIN` (
  `vin` varchar(32) NOT NULL,
  `date_added` date NOT NULL DEFAULT '2000-01-01',
  PRIMARY KEY (`vin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

