ConanTheDeployer
================

My lab application to test all the new cool stuff out there :-)
A somewhat "real" web application that at the moment is just a few rest interfaces a login page and some ajax to load data from server.

Build system
------------
Gradle (wrapper 1.4)
Plugins:
* Jetty (gradle native)
* selenium 
* h2
* liquibase
* Integration test hook
* jmeter

Shiro
----------
Login & security is managed with shiro. I tried spring security since it made promises about being non-intrusive but I was caught in spring MVC, application contexts and other junk. Way to be non-intrusive!
User/pass/salt is kept in h2 db. Surprised to see that shiro did not provide password hash and salt out of the box, even though they make it very clear that password salting should be used...

h2
--------
A "half" embedded h2 db is used as db. Aka recreate as part of launch process (jettyRun). Mangare db changes with liquibase (also part of launch from jettyRun).

Selenium
--------
Is just a simple Test task, which depends on a daemon jetty instance 

Integration test
--------
Is just a simple Test task, does nothing at the moment

jMeter
--------
Hook to run all jemter test plans in test/resources/load (not tested). Aka , I need to think up some clever test cases 