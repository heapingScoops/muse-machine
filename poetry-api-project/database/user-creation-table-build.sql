
DROP TABLE IF EXISTS creations;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
	user_id serial,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200),
	role varchar(25),
	CONSTRAINT PK_users PRIMARY KEY(user_id)
);

CREATE TABLE creations(
	creation_id serial,
	url varchar(200) PRIMARY KEY NOT NULL,
	time_stamp timestamp,
	poem_id int REFERENCES poetry_foundation(id),
	user_id int REFERENCES users(user_id)
);

INSERT INTO users (username, password_hash, role)
VALUES ('user', '$2a$10$8SK.KDaqNuyjYpVnKA.oSetVimTCy9vW0OZ9sGoIL4uN9h57q.vPm', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('user2', '$2a$10$NlwLgaabvCSGrj0.YCsxrOA2xTJXX3gywtkju.ZBU5IoVeT80hxYK', 'ROLE_USER');

INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703811423/img-9suYPEYnigudAdYrSjSFj4ES.png.png', now(), 69, 1)
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703891160/httpsgrammarist.comhomophonesbut-vs-butt-1024x478.png', now(), 1975, 1)
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703891694/img-aVnc28bxLsWrXFcEuYniUw1v.png.png', now(), 10600, 1)
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704234152/img-hnX2DwHLam7AcSGvvKj83LeH.png.png', now(), 2955, 1)
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704234809/img-74NOmb9LZPUaZ5TunXsaMF5i.png.png', now(), 1234, 1)


SELECT * FROM creations
SELECT * FROM users

