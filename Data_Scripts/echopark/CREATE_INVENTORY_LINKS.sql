CREATE TABLE `INVENTORY_LINKS` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(256) NOT NULL,
  `last_successful_export` datetime NOT NULL DEFAULT '2000-01-01 00:00:00',
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `link_UNIQUE` (`link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

