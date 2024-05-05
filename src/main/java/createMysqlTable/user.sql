CREATE DATABASE IF NOT EXISTS oasis;
USE oasis;
DROP TABLE IF EXISTS user;

set auto_increment_offset=1;
set auto_increment_increment=1; 

CREATE TABLE user (
	user_id INT AUTO_INCREMENT NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_identity ENUM('REGULAR', 'COMPANY', 'ADMINISTRATOR') NOT NULL,
    user_company_name VARCHAR(50) ,
    user_register_date DATE NOT NULL,
    user_last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_last_ip VARCHAR(39) NOT NULL,
    user_nickname VARCHAR(20),
    user_avatar TINYTEXT,
    user_intro TINYTEXT,
    
    CONSTRAINT user_primary_key PRIMARY KEY (user_id)
);

INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('mlaitw@gmail.com','0000','ADMINISTRATOR', null, '2024-04-17', '2024-04-17 15:30:45', '127.0.0.1', 'Mike', 'C:\Users\T14 Gen 3\Downloads\resource\image\cat.jpg', 'Hi, I play video game since 12. I love Slay the Spire from Mega Crit and Hollow Knight from Team Cherry!');
    
INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('superman@yahoo.com.tw','1111','REGULAR', null, '2024-04-18', '2024-04-17 15:30:45', '127.0.0.1', '超人', 'https://battlecats.2of3.net/img/Units/uni507_f00.png?20200710','');
    
INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('stranger@gmail.com','2222','REGULAR', null, '2024-04-17', '2024-04-17 15:30:45', '127.0.0.1', '路人甲', 'https://i.pravatar.cc/300', '');

INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('clietSupport@riot.com','3333','COMPANY', 'RIOT', '2024-04-17', '2024-04-17 15:30:45', '127.0.0.1', '拳頭', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYY9axR7mBZRU56b4lkCKEbkkMEynJU2r2SNaZpUi4r3iAXE62q8IFhjHMk-_1OBdcuNo&usqp=CAU', '');

INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('clientSupprt@klei.com','4444','COMPANY', 'Klei', '2024-04-17', '2024-04-17 15:30:45', '127.0.0.1', 'klei', 'https://s.yimg.com/os/creatr-uploaded-images/2020-06/1896af50-af7e-11ea-bff7-199b5e22ebd1', '');

INSERT INTO user (user_email, user_password, user_identity, user_company_name
		, user_register_date, user_last_login, user_last_ip, user_nickname, user_avatar, user_intro)  
	VALUES ('teamcherry@gmail.com','5555','COMPANY', 'Team Cherry', '2024-04-17', '2024-04-17 15:30:45', '127.0.0.1', 'cherry', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLqKg6N8MiX0BuL2_rr4wopwty-4pkfgdT9td-Pn8mACLjIHOi_lKKdvrSLfRwO1XOpXY&usqp=CAU', '');

SELECT * FROM user;