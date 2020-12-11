CREATE DATABASE HomeHubSql;

USE HomeHubSql;
DROP TABLE user;
CREATE TABLE User(
	userId VARCHAR(40),
    firstname VARCHAR(40) NOT NULL,
    middlename VARCHAR(40),
    lastname VARCHAR(40) NOT NULL,
    password VARCHAR(40),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(40),
    userType VARCHAR(40) NOT NULL,
    PRIMARY KEY(userId)
);
DROP TABLE city;
CREATE TABLE City(
	code VARCHAR(40),
    name VARCHAR(40) NOT NULL,
    state VARCHAR(40) NOT NULL,
    country VARCHAR(40) NOT NULL,
    PRIMARY KEY(code)
);
DROP TABLE Professional;
CREATE TABLE Professional(
	id VARCHAR(40),
    approved BOOLEAN NOT NULL,
    firstname VARCHAR(40) NOT NULL,
    middlename VARCHAR(40),
    lastname VARCHAR(40) NOT NULL,
    city VARCHAR(40) NOT NULL,
    email VARCHAR(255) NOT NULL,
    category VARCHAR(40) NOT NULL,
    phone VARCHAR(40),
    street VARCHAR(40) NOT NULL,
    zip VARCHAR(40) NOT NULL,
    latitude DECIMAL(15,10),
    longitude DECIMAL(15,10),
    image VARCHAR(40),
    PRIMARY KEY(id),
    FOREIGN KEY(city) REFERENCES City(code),
    FOREIGN KEY(category) REFERENCES Service(category)
);
DROP TABLE OrderDetail;
CREATE TABLE OrderDetail(
	orderId VARCHAR(40),
    userId VARCHAR(40),
    professionalId VARCHAR(40),
    serviceId VARCHAR(40),
    street VARCHAR(40),
    zip VARCHAR(40),
    serviceDetails VARCHAR(255),
    addInstructions VARCHAR(255),
    orderdate DATE,
    ordertime TIME,
    servicedate DATE,
    servicetime TIME,
    PRIMARY KEY(orderId),
    FOREIGN KEY(userId) REFERENCES User(userId),
    FOREIGN KEY(professionalId) REFERENCES Professional(id),
    FOREIGN KEY(serviceId) REFERENCES Service(serviceId)
);
DROP TABLE Service;
CREATE TABLE Service(
	serviceId VARCHAR(40),
    category VARCHAR(40),
    serviceName VARCHAR(255),
    PRIMARY KEY(serviceId),
    FOREIGN KEY (category) REFERENCES category(category)
);
CREATE TABLE category(
	category VARCHAR(40),
    PRIMARY KEY(category)
);

CREATE TABLE cost(
	serviceId VARCHAR(40),
    cityCode VARCHAR(40),
    price DOUBLE,
    discount DOUBLE,
    PRIMARY KEY(serviceId,cityCode),
    FOREIGN KEY(serviceId) REFERENCES service(serviceId),
    FOREIGN KEY(cityCode) REFERENCES city(code)
);

USE HomeHubSql;
CREATE TABLE Appointment(
	appointmentId VARCHAR(40),
    userId VARCHAR(40),
    professionalId VARCHAR(40),
    serviceId VARCHAR(40),
    street VARCHAR(40),
    zip VARCHAR(40),
    serviceDetails VARCHAR(255),
    addInstructions VARCHAR(255),
    orderdate DATE,
    ordertime TIME,
    servicedate DATE,
    servicetime TIME,
    creditCard VARCHAR(40),
    serviceStatus VARCHAR(40),
    PRIMARY KEY(appointmentId),
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (professionalId) REFERENCES Professional(id),
    FOREIGN KEY (serviceId) REFERENCES Service(serviceId)
);

DROP TABLE Appointment;