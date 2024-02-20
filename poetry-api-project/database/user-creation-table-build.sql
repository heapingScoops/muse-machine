
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
	creation_id serial PRIMARY KEY NOT NULL,
	url varchar(200),
	time_stamp timestamp,
	poem_id int REFERENCES poetry_foundation(id),
	user_id int REFERENCES users(user_id)
);

INSERT INTO users (username, password_hash, role)
VALUES ('user', '$2a$10$8SK.KDaqNuyjYpVnKA.oSetVimTCy9vW0OZ9sGoIL4uN9h57q.vPm', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('user2', '$2a$10$NlwLgaabvCSGrj0.YCsxrOA2xTJXX3gywtkju.ZBU5IoVeT80hxYK', 'ROLE_USER');

INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703811423/img-9suYPEYnigudAdYrSjSFj4ES.png.png', '2023-12-29', 69, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703891160/httpsgrammarist.comhomophonesbut-vs-butt-1024x478.png', '2023-12-29', 1975, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1703891694/img-aVnc28bxLsWrXFcEuYniUw1v.png.png', '2023-12-29', 10600, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704234152/img-hnX2DwHLam7AcSGvvKj83LeH.png.png', '2024-01-02', 2955, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704234809/img-74NOmb9LZPUaZ5TunXsaMF5i.png.png', '2024-01-02', 1234, 2);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704235700/img-8P7MBriWPStiioXIPGbDYhx3.png.png', '2024-01-02', 9483, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704237036/img-IqNpSTpZZ6W9IxjJ87w34nGn.png.png', '2024-01-02', 2941, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704237074/img-PMvwdZzNpxiMkqXmZkZFuHG0.png.png', '2024-01-02', 8597, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704237515/img-TmjjoEqW7LS15U44CYt94OXk.png.png', '2024-01-02', 446, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704387650/img-ejjTxMelaAfbKxYbFn5LQ5OP.png.png', '2024-01-04', 3489, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704394531/img-ZRVxbNUvRMs0bQ6ZRVCTal97.png.png', '2024-01-04', 2335, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704394610/img-rLd0vA3fxDBkjZr0Ynv6Bq6A.png.png', '2024-01-04', 11189, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704408272/img-UJCukcYfFYkifmujYvB7psHR.png.png', '2024-01-04', 4310, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704408284/img-xZwkzyLZkKTMmDrVl0TsA9xA.png.png', '2024-01-04', 8063, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704409324/img-RprAnENmhebx40D5Qpgkv7w3.png.png', '2024-01-04', 5512, 1);
INSERT INTO creations (url, time_stamp, poem_id, user_id)
VALUES ('https://res.cloudinary.com/djzcnmn12/image/upload/v1704409572/img-eQRZ4Y17afyOFzPVinPqRUs6.png.png', '2024-01-04', 5513, 1);

SELECT * FROM creations
SELECT * FROM users

SELECT creation_id, url, time_stamp, poem_id, user_id FROM creations WHERE user_id = 1;

