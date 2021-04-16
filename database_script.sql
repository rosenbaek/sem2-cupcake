-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cupcake
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cupcake` ;

-- -----------------------------------------------------
-- Schema cupcake
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cupcake` DEFAULT CHARACTER SET utf8 ;
USE `cupcake` ;

-- -----------------------------------------------------
-- Table `cupcake`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake`.`users` ;

CREATE TABLE IF NOT EXISTS `cupcake`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NULL DEFAULT 'customer',
  `balance` FLOAT NULL DEFAULT 0.0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake`.`orders` ;

CREATE TABLE IF NOT EXISTS `cupcake`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `totalPrice` FLOAT NULL,
  `status` VARCHAR(45) NULL,
  `timestamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  INDEX `fk_orders_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `cupcake`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake`.`toppings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake`.`toppings` ;

CREATE TABLE IF NOT EXISTS `cupcake`.`toppings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake`.`bottoms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake`.`bottoms` ;

CREATE TABLE IF NOT EXISTS `cupcake`.`bottoms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake`.`cupcake`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake`.`cupcake` ;

CREATE TABLE IF NOT EXISTS `cupcake`.`cupcake` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `toppings_id` INT NOT NULL,
  `bottoms_id` INT NOT NULL,
  `quantity` INT NULL,
  `orders_id` INT NULL,
  PRIMARY KEY (`id`, `toppings_id`, `bottoms_id`),
  INDEX `fk_cupcake_toppings1_idx` (`toppings_id` ASC) VISIBLE,
  INDEX `fk_cupcake_bottoms1_idx` (`bottoms_id` ASC) VISIBLE,
  INDEX `fk_cupcake_orders1_idx` (`orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_cupcake_toppings1`
    FOREIGN KEY (`toppings_id`)
    REFERENCES `cupcake`.`toppings` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cupcake_bottoms1`
    FOREIGN KEY (`bottoms_id`)
    REFERENCES `cupcake`.`bottoms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cupcake_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `cupcake`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
