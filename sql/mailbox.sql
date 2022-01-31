## domains 인서트문
INSERT INTO `mailbox`.`virtual_domains`
   (`name`)
VALUES
   ('supser');


## 테스트를 위한 users 인서트문
INSERT INTO `mailbox`.`virtual_users`
   (`id`, `domain_id`, `password` , `email`, `box`)
VALUES
   ('1', ENCRYPT('test', CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), 'test', 'test');


## virtual_user 업데이트문
UPDATE virtual_users SET PASSWORD = ENCRYPT('test', CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))) WHERE id = 1;



## postfix 설정 확인 쿼리
SELECT * FROM virtual_users WHERE email='이메일 주소';

SELECT box FROM virtual_users WHERE email='이메일 주소';

SELECT box FROM virtual_users WHERE email='이메일 주소';

## dovecot 패스워드 설정 query
SELECT email as user, password FROM virtual_users WHERE email='이메일 주소';




