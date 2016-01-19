SEAD 2.0
========

Source repository for SEAD 2.0 C3P-R services. 

Steps to build:
---------------

Move the to root directory and execute following command.

mvn clean install -DskipTests
(Skipping tests will help as there's an intermittent test failure in sead-doi-service)

This should build all micro services.

Steps to deploy on Tomcat:
--------------------------

The services require two running instances of mongoDB, on hosts/ports defined in the sead-pdt default.properties file.

* Copy the following .war files from relevant target directories into TOMCAT_HOME/webapps.

sead-c3pr.war
sead-mm.war
sead-pdt.war
va-workflow.war
metadata-gen.war


* Fix the endpoint URL's in following configuration files under webapps.

sead-mm/WEB-INF/classes/org/sead/matchmaker/default.properties
sead-pdt/WEB-INF/classes/org/seadpdt/util/default.properties
sead-c3pr/WEB-INF/classes/org/sead/api/util/default.properties
sead-doi-service/WEB-INF/classes/org/seadva/services/util/doi.properties
va-workflow/WEB-INF/classes/org/sead/workflow/sead-wf.xml
metadata-gen/WEB-INF/classes/org/seadva/metadatagen/util/Config.properties

* Add a setenv.sh file under bin (if it's not already there) and set the 
-Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true parameter to make
sure the encoded URL requests work fine.

Ex: 
export JAVA_OPTS="-Xss1024m -Xms512m -Xmx4096m -XX:MaxPermSize=2048m -Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true -Dorg.apache.catalina.connector.CoyoteAdapter.ALLOW_BACKSLASH=true"

* If SEAD should run on https, follow the guide below to setup SSL on Tomcat. Make sure that
you use Sun JDK instead of OpenJDK if you need SSL.

Ex:
https://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html

* Start the server.

* Now the API should be accessible through the following URL.

http://host:port/sead-api/..

For more details on how to use the API, please refer to:
https://opensource.ncsa.illinois.edu/confluence/pages/viewpage.action?pageId=71139377
