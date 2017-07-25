Curbee Packaging with Docker
======================================

Pre-requisits
---------------
Docker Version 17

Build Package
---------------
~~~
docker build -t <repository_name>:<tag>
~~~
ex:
~~~
docker build -t seadrepo/seadpkg:v1 .
~~~

Run Package
---------------
~~~
docker run -d -p <host_port>:<container_port> <repository_name>:<tag>
~~~
ex:
~~~
docker run -d -p 8080:8080 seadrepo/seadpkg:v1
~~~
You can specify the configuration parameters using -e flag. 
~~~
docker run -d -p 8080:8080 -e GOOGLE_API_KEY=<google_api_key> -e CLOWDER_USER=<clowder_username> seadrepo/seadpkg:v1
~~~
Curbee Docker container can be configured with the following parameters;
~~~

# "people" Providers Configuration
# configuration parameters of "people" profile providers
GOOGLE_API_KEY - API key of the Google
LINKEDIN_API_KEY - API key of the Linked-In

# Clowder Configuration
# if Clowder[https://sead2.ncsa.illinois.edu/] is used to publish datasets to Curbee
CLOWDER_USER - Clowder Username
CLOWDER_PW - Clowder Password

# DOI Service Configuration
# if built-in DOI generation service is used
DOI_SHOULDER_PROD - DOI Shoulder for permanent DOIs
DOI_SHOULDER_TEST - DOI Shoulder for test/temporary DOIs
DOI_USER - Username of EZID[https://ezid.cdlib.org/]
DOI_PWD - Password of EZID

# DataONE Member Node API Configuration
# if datasets needs to be cataloged in DataONE[https://www.dataone.org/about]
# example configuration : https://cn.dataone.org/cn/v1/node/urn:node:SEAD
DATAONE_NODE_IDENTIFIER
DATAONE_CONTACT_SUBJECT
DATAONE_BASE_URL
# synchronization schedule
DATAONE_SYNC_YEAR - default '*'
DATAONE_SYNC_MONTH - default '*'
DATAONE_SYNC_DAYM - default '*'
DATAONE_SYNC_DAYW - default '?'
DATAONE_SYNC_HOUR - default '*'
DATAONE_SYNC_MIN - default '0/3'
DATAONE_SYNC_SEC - default '45'
# email credentials - email to notify DataONE MN API related errors
DATAONE_EMAIL_USERNAME
DATAONE_EMAIL_PASSWORD
~~~

Deployments
---------------

Curbee docker image is hosted in the Docker Hub.

~~~
https://hub.docker.com/r/d2isead/curbee/
~~~

Curbee application is created in NDS Labs Workbench(https://www.workbench.nationaldataservice.org) using the Curbee docker image.

~~~
https://www.workbench.nationaldataservice.org/#/store
Documentation : https://nationaldataservice.atlassian.net/wiki/display/NDSC/Curbee
~~~
