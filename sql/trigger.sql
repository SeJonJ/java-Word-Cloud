
## 트리거 생성 ##
## Tip : Delimiter는 일종의 '구문 문자'로 C나 JAVA의 ';'(세미콜론)라고 한다.
즉, 문법의 끝을 나타내는 역할을 한다.

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



## 트리거 확인 / 삭제
SHOW TRIGGERS;
DROP TRIGGER new_mailuser;


