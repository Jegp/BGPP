--
-- The SQL script for creating the tables in the vehicle booking-system.
--
-- Create customer table
CREATE TABLE customer (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
firstName char(50),
lastName char(50),
email char(50),
phone char(20),
address char(100));

-- Create vehicleClass table
CREATE TABLE vehicleClass (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
description char(200));

-- Create vehicle table
CREATE TABLE vehicle (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
description char(200),
manufacturer char(50),
model char(50),
vehicleClass INTEGER UNSIGNED REFERENCES vehicleClass(id));

-- Create period table
CREATE TABLE period (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
start BIGINT UNSIGNED,
end BIGINT UNSIGNED);

-- Create reservation table
CREATE TABLE reservation (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
customer INTEGER UNSIGNED REFERENCES customer(id),
vehicle INTEGER UNSIGNED REFERENCES vehicle(id),
period INTEGER UNSIGNED REFERENCES period(id));