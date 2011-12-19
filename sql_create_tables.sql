--
-- The SQL script for creating the tables and adding vehicle-classes 
-- in the booking-system.
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
start BIGINT(30) UNSIGNED,
end BIGINT(30) UNSIGNED);

-- Create reservation table
CREATE TABLE reservation (
id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
customer INTEGER UNSIGNED REFERENCES customer(id),
vehicle INTEGER UNSIGNED REFERENCES vehicle(id),
period INTEGER UNSIGNED REFERENCES period(id));

--
-- Insert test cases
-- 

-- Insert vehicle classes
INSERT INTO vehicleClass VALUES(1, "3-door car");
INSERT INTO vehicleClass VALUES(2, "5-door car");
INSERT INTO vehicleClass VALUES(3, "Station car");
INSERT INTO vehicleClass VALUES(4, "Sports car");
INSERT INTO vehicleClass VALUES(5, "Bicycle");
INSERT INTO vehicleClass VALUES(6, "Motorcycle");

-- Insert vehicles
INSERT INTO vehicle VALUES(1, "A car for the retro collector", "Citroën", "Citroneta pickup truck", 1);
INSERT INTO vehicle VALUES(2, "A great car for a couple on vacation", "Nissan", "Qashai", 2);
INSERT INTO vehicle VALUES(3, "A fiendly car for the family", "Renault", "Grand Scenic", 3);
INSERT INTO vehicle VALUES(4, "The car you never thought you would drive", "Bugatti", "Veyron", 4);
INSERT INTO vehicle VALUES(5, "The bicycle that always works", "Betavus", "Statos", 5);
INSERT INTO vehicle VALUES(6, "A 1261 cc moterbike", "Honda", "ST1300 ABS", 6);

-- Insert customers
INSERT INTO customer VALUES(1, "Anders", "Sand", "anders@sand.net", "+45 26602875", "Paradisæblevej 111, 6830 Nr. Nebel");
INSERT INTO customer VALUES(2, "Dan Witzner", "Hansen", "witzner@itu.dk", "+45 38168842", "IT University of Copenhagen, Glentevej 67, 2400 Copenhaven NV");
INSERT INTO customer VALUES(3, "Lars", "Birkedal", "birkedal@itu.dk", "+45 72185280", "IT University of Copenhagen, Rued Langgaards Vej 7, 2300 Copenhagen S");
INSERT INTO customer VALUES(4, "Douglas", "Adams", "adams@42.com", "-", "Highgate Cemetery, London");
INSERT INTO customer VALUES(5, "Dennis MacAlistair", "Ritchie", "ritchie@c.org", "-", "Berkeley Heights, New Jersey, U.S.");

-- Insert periods
INSERT INTO period VALUES(1, 1325458800000, 1325545200000); -- Jan 2. - Jan 3.
INSERT INTO period VALUES(2, 1325545200000, 1325890800000); -- Jan 3. - Jan 7.
INSERT INTO period VALUES(3, 1325890800000, 1327618800000); -- Jan 7. - Jan 27. 
INSERT INTO period VALUES(4, 1325458800000, 1326582000000); -- Jan 7. - Jan 20.
INSERT INTO period VALUES(5, 1324289410227, 1324463341783); -- Dec 19. - Dec. 21

-- Insert reservations
INSERT INTO reservation VALUES(1, 1, 3, 1);
INSERT INTO reservation VALUES(2, 2, 6, 4);
INSERT INTO reservation VALUES(3, 3, 4, 5);
INSERT INTO reservation VALUES(4, 4, 1, 3);
INSERT INTO reservation VALUES(5, 5, 5, 2);