DROP TABLE IF EXISTS ConanUser;
CREATE TABLE ConanUser (
	username varchar(200),
	password varchar(200),
	salt varchar(200)
);

DROP TABLE IF EXISTS ConanRole;
CREATE TABLE ConanRole (
	id int primary key,
	username varchar(200),
	role varchar(200)
);

DROP TABLE IF EXISTS ConanPermission;
CREATE TABLE ConanPermission (
	id int primary key,
	role varchar(200),
	permission varchar(200)
);

DROP TABLE IF EXISTS DeployedApp;
CREATE TABLE DeployedApp (
	id bigint primary key,
	name varchar(200),
	version varchar(100),
	host varchar(200),
);