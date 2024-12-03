CREATE SCHEMA IF NOT EXISTS `healog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `healog` ;

-- -----------------------------------------------------
-- Table `healog`.`category`
-- -----------------------------------------------------

-- 카테고리 테이블은 운동의 주요 부위를 분류하기 위함입니다.
-- 하나의 행이 하나의 운동을 의미합니다.
CREATE TABLE IF NOT EXISTS `healog`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `exercise` VARCHAR(45) NOT NULL, -- 운동 명과 
  `exercise_area` VARCHAR(45) NOT NULL, -- 핵심 운동 부위를 저장합니다.
  PRIMARY KEY (`id`),
  UNIQUE INDEX `exercise_UNIQUE` (`exercise` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`user`
-- -----------------------------------------------------
-- user 테이블은 트레이너가 아닌 회원만을 저장하기 위한 테이블입니다.

CREATE TABLE IF NOT EXISTS `healog`.`user` (
  `id` VARCHAR(45) NOT NULL, -- id는 숫자로 임의 접근을 방지하기 위해서 UUID를 이용합니다.
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL, -- email을 이용하여 로그인합니다.
  `password` VARCHAR(100) NOT NULL, -- password를 이용하여 로그인힙니다.
  `salt` VARCHAR(20) NOT NULL,
  `created_at_datetime` DATETIME DEFAULT NOW() NOT NULL,
  `gender` INT NOT NULL,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `trainer_exist` INT NOT NULL DEFAULT '0', -- 비정규화해 둔 것입니다. subscription을 이용해서 채크할 수 있지만 따로 저장하면 효율적일 것이라고 생각했습니다.
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE, -- email을 이용해 로그인하기 위해서 unique를 적용하였습니다.
  UNIQUE INDEX `phonenumber_UNIQUE` (`phonenumber` ASC) VISIBLE) -- 개인 식별을 위해서 전화번호에 unique를 적용하였습니;다.
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`diary`
-- -----------------------------------------------------
-- user의 한 사람의 하루에 하나의 diary를 배정합니다.
-- 누구의 며칠의 다이어리인지 정보를 저장합니다.
CREATE TABLE IF NOT EXISTS `healog`.`diary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `year` INT NOT NULL,
  `month` INT NOT NULL,
  `day` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `condition` TEXT NULL DEFAULT NULL, -- 해당 일에 컨디션을 트레이너에게 전달하기 위해서 text타입의 컨디션을 저장합니다.
  PRIMARY KEY (`year`, `month`, `day`, `user_id`),
  INDEX `fk_diary_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_diary_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`exercise`
-- -----------------------------------------------------
-- 하나의 diary에 여러개의 운동이 연결됩니다.
-- 해당 diary 날짜에 어떤 운동을 얼마의 무게로 몇세트할지를 기록합니다.
CREATE TABLE IF NOT EXISTS `healog`.`exercise` (
  `id` INT NOT NULL AUTO_INCREMENT, 
  `category_id` INT NOT NULL, -- 어떤 운동인지를 카테고리 테이블에서 받아옵니다.
  `diary_id` INT NOT NULL,  -- diary의 id와 연결됩니다.
  `weight` INT NULL DEFAULT NULL,
  `count` INT NULL DEFAULT NULL,
  `set` INT NULL DEFAULT NULL,
  `posture_img` VARCHAR(255) NULL DEFAULT NULL, -- 운동을 완료했을 때 자세를 기록할 수 있도록 합니다.
  `done` INT NOT NULL DEFAULT 0,  -- 운동의 완료 여부를 기록하기 위함입니다. 미완료 0, 완료 1입니다.
  PRIMARY KEY (`id`),
  INDEX `fk_exercise_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_exercise_diary1_idx` (`diary_id` ASC) VISIBLE,
  CONSTRAINT `fk_exercise_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `healog`.`category` (`id`),
  CONSTRAINT `fk_exercise_diary1`
    FOREIGN KEY (`diary_id`)
    REFERENCES `healog`.`diary` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`gym`
-- -----------------------------------------------------
-- 트레이너의 소속 헬스장을 관리하기 위함입니다.
-- 트레이너가 회원가입을 할 때 헬스장을 입력하고
-- 기존에 저장되어있는 헬스장이면 해당 헬스장을 foreign키로 연결하고
-- 없으면 insert하여 연결합니다.
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
-- 트레이너의 정보를 저장하기 위함입니다.
CREATE TABLE IF NOT EXISTS `healog`.`trainer` (
  `id` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `salt` VARCHAR(20) NOT NULL,
  `created_at_datetime` DATETIME NOT NULL DEFAULT NOW(),
  `name` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `bio` TEXT NULL DEFAULT NULL, -- 자기 소개 내용을 text로 저장합니다.
  `img` VARCHAR(45) NULL, -- 자신의 사진을 저장합니다
  `location` INT NOT NULL, -- 자신이 일하는 헬스장을 저장합니다.
  `user_count` INT NOT NULL DEFAULT '0', -- 비정규화입니다. 본인이 담당하고 있는 회원의 수를 저장합니다. 효율성을 위함입니다.
  `gender` INT NOT NULL,
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
-- Table `healog`.`meal`
-- -----------------------------------------------------
-- 하루의 diary의 아침, 점심, 저녁 식단 이미지를 저장하기 위함입니다.
CREATE TABLE IF NOT EXISTS `healog`.`meal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `breakfast_img` VARCHAR(255) NULL DEFAULT NULL,
  `lunch_img` VARCHAR(255) NULL DEFAULT NULL,
  `dinner_img` VARCHAR(255) NULL DEFAULT NULL,
  `diary_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meal_diary1_idx` (`diary_id` ASC) VISIBLE,
  CONSTRAINT `fk_meal_diary1`
    FOREIGN KEY (`diary_id`)
    REFERENCES `healog`.`diary` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------
-- Table `healog`.`subscription`
-- -----------------------------------------------------
-- 회원과 트레이너의 연결을 저장하기 위함입니다.
CREATE TABLE IF NOT EXISTS `healog`.`subscription` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `trainer_id` VARCHAR(45) NOT NULL,
  `category` INT NOT NULL,-- 온라인 구독(0), 오프라인 구독(1)이 저장됩니다.
  `start_date` VARCHAR(45) NOT NULL, -- 구독을 하기 시작한 날짜와
  `end_date` VARCHAR(45) NOT NULL,  -- 구독이 끝나는 날짜가 저장됩니다.
  `valid` INT NOT NULL DEFAULT 1, -- 1이면 기간 안지난, 0이면 기간 지난
  PRIMARY KEY (`id`),
  INDEX `fk_onlinesubscription_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_onlinesubscription_trainer1_idx` (`trainer_id` ASC) VISIBLE,
  CONSTRAINT `fk_onlinesubscription_trainer1`
    FOREIGN KEY (`trainer_id`)
    REFERENCES `healog`.`trainer` (`id`),
  CONSTRAINT `fk_onlinesubscription_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `healog`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mbpersonaltraining4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `healog`.`personaltraining`
-- -----------------------------------------------------
-- PT 일정을 저장하기 위함입니다.
CREATE TABLE IF NOT EXISTS `healog`.`personaltraining` (
  `trainer_id` VARCHAR(45) NOT NULL,
  `diary_id` INT NOT NULL, -- diary의 id를 통해서 pt 날짜를 알 수 있습니다.
  `requestDiary` INT NULL, -- 회원이 수정 날짜를 전송할 수 있습니다.
  `requestState` INT NULL, -- 트레이너의 수정 요청에 대한 처리여부를 표시합니다. 미확인(0), 승인(1), 거절(2)
  `time` INT NOT NULL,
	`requestTime` INT NULL,
  PRIMARY KEY (`diary_id`),
  INDEX `fk_personaltraining_trainer1_idx` (`trainer_id` ASC) VISIBLE,
  INDEX `fk_personaltraining_diary1_idx` (`diary_id` ASC) VISIBLE,
  CONSTRAINT `fk_personaltraining_trainer1`
    FOREIGN KEY (`trainer_id`)
    REFERENCES `healog`.`trainer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personaltraining_diary1`
    FOREIGN KEY (`diary_id`)
    REFERENCES `healog`.`diary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;