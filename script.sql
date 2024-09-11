CREATE USER 'basic'@'%' IDENTIFIED BY 'rest';
CREATE DATABASE basicrest;
GRANT ALL PRIVILEGES ON basicrest.* TO 'basic'@'%';
USE basicrest;

CREATE TABLE Users (
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  username VARCHAR(255),
  password VARCHAR(255),
  date_naiss CHAR(10)
) ENGINE innodb;

CREATE TABLE Classified (
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  info VARCHAR(255)
) ENGINE innodb;

INSERT INTO Users(username, password, date_naiss) VALUES ("machinchose", "abcdef", "10/10/2010"), ("trucmuche", "12345", "24/09/2021");
INSERT INTO Classified(info) VALUES ("secret"), ("trés secret"), ("ultra secret");