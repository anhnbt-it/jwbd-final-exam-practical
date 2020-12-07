CREATE DATABASE jwbdfinalexam_db;
USE jwbdfinalexam_db;

-- Tao bang categories
CREATE TABLE `categories` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);
-- Chen du lieu mau vao bang categories
INSERT INTO `categories` (`name`) VALUES ('Phone'), ('Television'), ('Computer');

-- Tao bang users
CREATE TABLE `products` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `qty` TINYINT NULL DEFAULT 0,
    `color` VARCHAR(50) NOT NULL,
    `desc` TEXT NOT NULL,
    `category_id` INT UNSIGNED NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- Them khoa ngoai cho cot category_id
ALTER TABLE `products`
	ADD CONSTRAINT `products_category_id_fk` 
		FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
-- Chen du lieu mau vao products
INSERT INTO `products` VALUES (1,'iPhone 11',799.00,12,'Purple, Yellow, Green','Lorem...',1,'2020-12-07 08:06:54','2020-12-07 08:06:54'),(2,'iPhone 11 Pro',1100.00,12,'Green, Black, White','Lorem...',1,'2020-12-07 08:13:07','2020-12-07 08:13:07'),(3,'iPhone X',749.00,13,'Black, Blue','Lorem...',1,'2020-12-07 08:27:48','2020-12-07 08:27:48'),(4,'Android Tivi Sony 4K 49 inch KD-49X7500H',1000.00,5,'Black','Lorem...',2,'2020-12-07 08:28:29','2020-12-07 08:28:29'),(5,'Samsung Galaxy Note 20 Ultra 5G',420.00,10,'Prism white, Prism blue, Ceramic black','Lorem...',1,'2020-12-07 08:29:28','2020-12-07 08:29:28');
-- Lay du lieu tu bang products
SELECT * FROM `products`;
-- Lay du lieu tu bang categories
SELECT * FROM `categories`;
