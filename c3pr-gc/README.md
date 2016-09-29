Curbee Garbage Collector
---------------
This module is created to delete the test RO requests which have expired PIDs(Permanent Identifiers).

Steps to build:
---------------

1) Modify sead2/c3pr-gc/config/config.properties file

Set the value of all.research.objects property to the link that is used to query all research objects in C3PR API.

2) Run build-standalone.sh.
~~~
./build-standalone.sh.
~~~
It will help user build project and create a shell script bin/c3pr-gc.sh

3) Run bin/c3pr-gc.sh to start garbage collector script. You have to provide the 'config.properties' file path as a parameter.
~~~
nohup ./bin/c3pr-gc.sh config/config.properties > log.txt &
~~~
