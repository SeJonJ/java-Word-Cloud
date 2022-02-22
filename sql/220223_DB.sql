#### 처음 DB 만들때 구조를 잘 만들어야하는 이유를 알았습니다.
#### 고치려니까 미칠것같아요... ###

## Auto increment 초기화
ALTER TABLE hjproject.member AUTO_INCREMENT = 1;
ALTER TABLE mailbox.virtual_users AUTO_INCREMENT = 1;

###### MEMBER DB #########
CREATE TABLE `member` (
	`MEMBER_CODE` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
	`ID` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
	`PASSWD` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
	`SEX` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`EMAIL` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`EMADDRESS` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`RDATE` DATE NOT NULL DEFAULT current_timestamp(),
	PRIMARY KEY (`MEMBER_CODE`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

## 0) 테이블명 변경
RENAME TABLE `member` TO `MEMBER`;

## 1) 전체 컬럼명 변경
ALTER TABLE `MEMBER`
	CHANGE COLUMN `NAME` `MNAME` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci' AFTER `MEMBER_CODE`,
	CHANGE COLUMN `ID` `MID` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci' AFTER `MNAME`,
	CHANGE COLUMN `PASSWD` `MPASSWD` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci' AFTER `MID`,
	CHANGE COLUMN `SEX` `MGENDER` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci' AFTER `MPASSWD`,
	CHANGE COLUMN `EMAIL` `MEMAIL` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci' AFTER `MGENDER`,
	CHANGE COLUMN `EMADDRESS` `MEMADDRESS` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci' AFTER `MEMAIL`,
	CHANGE COLUMN `RDATE` `MRDATE` DATE NOT NULL DEFAULT current_timestamp() AFTER `MEMADDRESS`;


ALTER TABLE `member`CHANGE COLUMN `CODE` `MEMBER_CODE` BIGINT(20) AUTO_INCREMENT;

## 3) passwd 암호화 : 이거하려면 java 코드에서 복호화하는 코드를 넣어야함 -> 현재는 적용 X
ALTER TABLE hjproject.member MODIFY passwd VARCHAR(255) NOT NULL ENCRYPT(NEW.passwd, CONCAT('$6$', SUBSTRING(SHA(RAND()), -16)));
UPDATE hjproject.member SET passwd  = ENCRYPT('new', CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))) WHERE member_code = 3;

########### MAILBOX DB ##########

## 0) 테이블명 수정
RENAME TABLE `virtual_users` TO `VIRTUAL_USERS`;

## 1) ID TO MBOX_CODE : alter table 테이블_이름 change 현재_컬럼_이름 새로운_컬럼_이름 타입;
ALTER TABLE mailbox.virtual_users modify MBOX_CODE INT NOT NULL AUTO_INCREMENT;

## 전체 테이블 수정
ALTER TABLE `VIRTUAL_USERS`
	CHANGE COLUMN `email` `MWEBMAIL` VARCHAR(120) NOT NULL COLLATE 'utf8_general_ci' AFTER `MPASSWD`,
	CHANGE COLUMN `box` `MBOX` VARCHAR(120) NOT NULL COLLATE 'utf8_general_ci' AFTER `MWEBMAIL`,
	DROP INDEX `email`,
	ADD UNIQUE INDEX `email` (`MWEBMAIL`) USING BTREE;


## 2) foreign key 추가 references hjproject.member.membercode
CONSTRAINT `membercode_fk` FOREIGN KEY (`MEMBER_CODE`) REFERENCES `hjproject`.`MEMBER` (`MEMBER_CODE`) ON UPDATE RESTRICT ON DELETE RESTRICT


## 3) member 의 member_code 를 외래키로 가져옴
ALTER TABLE mailbox.virtual_users ADD CONSTRAINT
membercode_fk FOREIGN KEY(MEMBER_CODE) 
REFERENCES hjproject.member(MEMBER_CODE);

ALTER TABLE mailbox.virtual_users DROP FOREIGN KEY membercode_fk;

#############################################

### TRIGGER ##
CREATE OR REPLACE TRIGGER `new_mailuser` AFTER INSERT ON `MEMBER` FOR EACH ROW begin
INSERT INTO mailbox.virtual_users
   (`domain_id`, `password` , `MWEBMAIL`, `MOBX`, `MEMBER_CODE`)
VALUES
   ('1', ENCRYPT(NEW.MPASSWD, CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), CONCAT(NEW.MID, '@mail.hjproject.kro.kr'), NEW.MID, NEW.MEMBER_CODE);
END