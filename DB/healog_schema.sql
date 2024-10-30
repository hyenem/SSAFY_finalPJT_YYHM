CREATE SCHEMA IF NOT EXISTS `healog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `healog` ;

-- -----------------------------------------------------
-- Table `healog`.`gym`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`gym` ;

CREATE TABLE IF NOT EXISTS `healog`.`gym` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`trainer` ;

CREATE TABLE IF NOT EXISTS `healog`.`trainer` (
  `id` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `created_at_datetime` DATETIME NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `bio` TEXT NULL DEFAULT NULL,
  `img` VARCHAR(45) NOT NULL,
  `location` INT NOT NULL,
  `user_count` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  INDEX `gym_id` (`location` ASC) VISIBLE,
  CONSTRAINT `gym_id`
    FOREIGN KEY (`location`)
    REFERENCES `healog`.`gym` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`user` ;

CREATE TABLE IF NOT EXISTS `healog`.`user` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `created_at_datetime` DATETIME NOT NULL,
  `gender` INT NOT NULL,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `trainer_exist` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phonenumber_UNIQUE` (`phonenumber` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`offlinesubscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`offlinesubscription` ;

CREATE TABLE IF NOT EXISTS `healog`.`offlinesubscription` (
  `id` INT NOT NULL,
  `category` INT NOT NULL,
  `start_date` VARCHAR(45) NOT NULL,
  `end_date` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `trainer_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_offlinesubscription_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_offlinesubscription_trainer1_idx` (`trainer_id` ASC) VISIBLE,
  CONSTRAINT `fk_offlinesubscription_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_offlinesubscription_trainer1`
    FOREIGN KEY (`trainer_id`)
    REFERENCES `healog`.`trainer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`onlinesubscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`onlinesubscription` ;

CREATE TABLE IF NOT EXISTS `healog`.`onlinesubscription` (
  `id` INT NOT NULL,
  `category` INT NOT NULL,
  `start_date` VARCHAR(45) NOT NULL,
  `end_date` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `trainer_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_onlinesubscription_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_onlinesubscription_trainer1_idx` (`trainer_id` ASC) VISIBLE,
  CONSTRAINT `fk_onlinesubscription_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_onlinesubscription_trainer1`
    FOREIGN KEY (`trainer_id`)
    REFERENCES `healog`.`trainer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`dairy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`dairy` ;

CREATE TABLE IF NOT EXISTS `healog`.`dairy` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `year` INT NOT NULL,
  `month` INT NOT NULL,
  `day` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `condition` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dairy_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_dairy_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`category` ;

CREATE TABLE IF NOT EXISTS `healog`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `exercise` VARCHAR(45) NOT NULL,
  `exercise_area` VARCHAR(45) NOT NULL,
  `equipment_used` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `exercise_UNIQUE` (`exercise` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`exercise` ;

CREATE TABLE IF NOT EXISTS `healog`.`exercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_id` INT NOT NULL,
  `weight` INT NULL,
  `count` VARCHAR(45) NULL,
  `set` VARCHAR(45) NULL,
  `posture_img` VARCHAR(45) NULL,
  `dairy_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_exercise_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_exercise_dairy1_idx` (`dairy_id` ASC) VISIBLE,
  CONSTRAINT `fk_exercise_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `healog`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_exercise_dairy1`
    FOREIGN KEY (`dairy_id`)
    REFERENCES `healog`.`dairy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`menstrauting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`menstrauting` ;

CREATE TABLE IF NOT EXISTS `healog`.`menstrauting` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `start_date` VARCHAR(45) NOT NULL,
  `end_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_menstrauting_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_menstrauting_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`meal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`meal` ;

CREATE TABLE IF NOT EXISTS `healog`.`meal` (
  `id` INT NOT NULL,
  `breakfast_img` VARCHAR(45) NULL,
  `lunch_img` VARCHAR(45) NULL,
  `diner_img` VARCHAR(45) NULL,
  `dairy_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_dairy1_idx` (`dairy_id` ASC) VISIBLE,
  CONSTRAINT `fk_meal_dairy1`
    FOREIGN KEY (`dairy_id`)
    REFERENCES `healog`.`dairy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healog`.`feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healog`.`feedback` ;

CREATE TABLE IF NOT EXISTS `healog`.`feedback` (
  `id` INT NOT NULL,
  `comment` TEXT NULL,
  `dairy_id` INT NOT NULL,
  PRIMARY KEY (`id`, `dairy_id`),
  INDEX `fk_feedback_dairy1_idx` (`dairy_id` ASC) VISIBLE,
  CONSTRAINT `fk_feedback_dairy1`
    FOREIGN KEY (`dairy_id`)
    REFERENCES `healog`.`dairy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
