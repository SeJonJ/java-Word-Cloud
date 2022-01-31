
## 트리거 생성 ##
delimiter //
create or replace trigger new_mailuser
after insert on hjproject.member
for each row
begin
INSERT INTO mailbox.virtual_users
   (`domain_id`, `password` , `email`, `box`)
VALUES
   ('1', ENCRYPT(NEW.passwd, CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), CONCAT(NEW.id, '@mail.hjproject.kro.kr'), NEW.id);
END //



## 여기서 부터는 설명
## Delimiter는 일종의 '구문 문자'로 C나 JAVA의 ';'(세미콜론)라고 한다.
즉, 문법의 끝을 나타내는 역할을 한다. END 후에 delimiter 뒤에 나온 기호를 붙여주자
delimiter //

## 만드는 방법 + 트리거 이름 작성 : create or replace 즉 없으면 새로 만들고, 있으면 대체
트리거 이름은 new_mailuser
create or replace trigger new_mailuser

## 언제 실행되는지 작성 : [DB명].[table명] 에 insert 된 후 즉, hjproject.member에 내용이 입력된 후
after insert on hjproject.member

## 몇 번이나 실행되는지? : 여기서는 each row 즉 매 행마다(DB에는 내용이 추가되면 한 행이 추가됨)
for each row

## trigger 시작문 : begin 으로 시작
begin

## trigger 로 실행하고 싶은 SQL 구문 : 트리거가 작동하면 아래의 SQL문이 실행됨
## mailbox.virtual_user 에 domain_id 는 1, password 는 NEW.passwd에 기존의 암호화 방식으로,
email은 NEW.id에 @mail.hjproject.kro.kr 을 붙여서, box 이름은 NEW.id

INSERT INTO mailbox.virtual_users
   (`domain_id`, `password` , `email`, `box`)
VALUES
   ('1', ENCRYPT(NEW.passwd, CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), CONCAT(NEW.id, '@mail.hjproject.kro.kr'), NEW.id);

## 끝날때 쓰는 문장 : END //
END //



## 트리거 확인 / 삭제
SHOW TRIGGERS;
DROP TRIGGER new_mailuser;


