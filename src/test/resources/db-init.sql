CREATE TABLE rooms (
id bigint NOT NULL AUTO_INCREMENT,
room_type int NOT NULL,
hotel_id bigint NOT NULL,
max_people_number int NOT NULL,
daily_price double NOT NULL,
description varchar(255),

PRIMARY KEY (id));

INSERT INTO rooms VALUES (1, 1, 1, 1, 100.0, 'Regular single room');