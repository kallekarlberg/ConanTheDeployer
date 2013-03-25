
insert into conanUser (username,password,salt) 
	values ('conan','+zBGVxS9PZfUraocJvJOCL52tCnIyWZ1iftMrWtseXo=','T0xml94S2C7x9Xt7aAhbuQ==');
insert into conanUser (username,password,salt) 
	values ('gracie','3R/tmmR/3TUOLEbH1yQCRdmw51sgnjCjun8H3EoFgiQ=','QCfiIq+Su2e5BerDtdwQbw==');
insert into conanUser (username,password,salt) 
	values ('bombata','PYT5z4O4bge2rHatebnwhVULreXl1739MoWvkf9u2HY=','qmpItQcl9uvyqoUqJNFGNg==');

insert into conanRole (id,username,role) values (1,'conan', 'admin');
insert into conanRole (id,username,role) values (2,'conan', 'rm');
insert into conanRole (id,username,role) values (3,'conan', 'user');
insert into conanRole (id,username,role) values (4,'gracie', 'rm');
insert into conanRole (id,username,role) values (5,'gracie', 'user');
insert into conanRole (id,username,role) values (6,'bombata', 'user');

insert into conanPermission (id,role,permission) values (1,'admin', 'leverage');

insert into DeployedApp (id,name,who,when,version,host,environment) 
	values (1,'MyApp', 'jenkins','2013-01-05 12:50:50.0','1.0.0','gandalf','dev');
insert into DeployedApp (id,name,who,when,version,host,environment) 
	values (2,'MyApp', 'kkarlberg','2013-01-08 15:50:50.0','1.0.0','frodo','qa');
insert into DeployedApp (id,name,who,when,version,host,environment) 
	values (3,'FundTransferMatcher','pelle','2013-02-10 09:10:50.0','3.2.0','smaug','prod');
insert into DeployedApp (id,name,who,when,version,host,environment) 
	values (4,'DummyOfflineBank', 'hacker','2013-03-05 08:40:50.0','1.0.3','dev03','systest');