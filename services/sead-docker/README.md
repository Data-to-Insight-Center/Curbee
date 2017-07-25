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
# configuration parameters for "people" profile providers
GOOGLE_API_KEY - API key of the Google
LINKEDIN_API_KEY - API key of the Linked-In

# configuration parameters of Clowder
CLOWDER_USER - Clowder Username
CLOWDER_PW - Clowder Password

# configuration parameters of DOI service
DOI_SHOULDER_PROD
DOI_SHOULDER_TEST
DOI_USER
DOI_PWD

# configuration parameters of DataONE Member Node API
DATAONE_NODE_IDENTIFIER
DATAONE_CONTACT_SUBJECT
DATAONE_BASE_URL
#synchronization schedule
DATAONE_SYNC_YEAR - default '*'
DATAONE_SYNC_MONTH - default '*'
DATAONE_SYNC_DAYM - default '*'
DATAONE_SYNC_DAYW - default '?'
DATAONE_SYNC_HOUR - default '*'
DATAONE_SYNC_MIN - default '0/3'
DATAONE_SYNC_SEC - default '45'
#email credentials
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
~~~
