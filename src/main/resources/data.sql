DROP TABLE IF EXISTS users;

CREATE TABLE users (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              email VARCHAR(250) NOT NULL,
                              password VARCHAR(250) NOT NULL
);
CREATE TABLE todolists (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       description VARCHAR(250) NOT NULL,
                       user_id INT,
                       is_done BOOLEAN,
                       foreign key (user_id) references users(id)
);