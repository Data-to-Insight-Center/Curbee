Curbee Monitoring Scripts
======================================

SEAD Services Performance Monitor
---------------

This script is scheduled to run everyday at 11pm to check the response time of the SEAD services.</br>
Run the following command to start this script;
~~~
sh start_cron.sh
~~~

SEAD MongoDB Monitor
---------------

This script is scheduled to run in every 3 hours to check up/down status of the MongoDB.</br>
Run the following command to start this script;
~~~
sh start_mongo_cron.sh
~~~
