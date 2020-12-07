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
-- Lay du lieu tu bang products
SELECT * FROM `products`;
-- Lay du lieu tu bang categories
SELECT * FROM `categories`;