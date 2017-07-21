#!/bin/bash

##############################################

###### Generating PDT properties files #######

##############################################

if [ -z "$GOOGLE_API_KEY" ]; then
    GOOGLE_API_KEY="google_api_key"
fi
if [ -z "$LINKEDIN_API_KEY" ]; then
    LINKEDIN_API_KEY="linkedin_api_key"
fi

touch sead-pdt-googleplusprovider.properties
echo "google.api_key=$GOOGLE_API_KEY" > /con-data/configs/sead-pdt-googleplusprovider.properties
touch sead-pdt-linkedinprovider.properties
echo "linkedin.api_key=$LINKEDIN_API_KEY" > /con-data/configs/sead-pdt-linkedinprovider.properties

cp /con-data/configs/sead-pdt-googleplusprovider.properties  /opt/tomcat8/webapps/sead-pdt/WEB-INF/classes/googleplusprovider.properties
cp /con-data/configs/sead-pdt-linkedinprovider.properties /opt/tomcat8/webapps/sead-pdt/WEB-INF/classes/linkedinprovider.properties


##############################################

#### Generating C3PR API properties file ####

##############################################

if [ -z "$CLOWDER_USER" ]; then
    CLOWDER_USER="clowder_user"
fi
if [ -z "$CLOWDER_PW" ]; then
    CLOWDER_PW="clowder_pw"
fi

touch sead-c3pr-default.properties
echo "pdt.url=http://localhost:8080/sead-pdt" > /con-data/configs/sead-c3pr-default.properties
echo "curBee.url=http://localhost:8080/va-workflow" >> /con-data/configs/sead-c3pr-default.properties
echo "matchmaker.url=http://localhost:8080/sead-mm" >> /con-data/configs/sead-c3pr-default.properties
echo "metadatagen.url=http://localhost:8080/metadata-gen" >> /con-data/configs/sead-c3pr-default.properties
echo "doi.service.url=http://localhost:8080/sead-doi-service" >> /con-data/configs/sead-c3pr-default.properties
echo "sead.dataone.url=http://localhost:8080/sead/rest/mn/v1/" >> /con-data/configs/sead-c3pr-default.properties
echo "clowder.user=$CLOWDER_USER" >> /con-data/configs/sead-c3pr-default.properties
echo "clowder.pw=$CLOWDER_PW" >> /con-data/configs/sead-c3pr-default.properties

cp /con-data/configs/sead-c3pr-default.properties  /opt/tomcat8/webapps/sead-c3pr/WEB-INF/classes/org/sead/api/util/default.properties


##############################################

### Generating DOI Service properties file ###

##############################################

if [ -z "$DOI_SHOULDER_PROD" ]; then
    DOI_SHOULDER_PROD="doi_shoulder_prod"
fi
if [ -z "$DOI_SHOULDER_TEST" ]; then
    DOI_SHOULDER_TEST="doi_shoulder_test"
fi
if [ -z "$DOI_USER" ]; then
    DOI_USER="doi_user"
fi
if [ -z "$DOI_PWD" ]; then
    DOI_PWD="doi_pwd"
fi

touch sead-doi-service-doi.properties
echo "ezid.url=https://ezid.cdlib.org/" > /con-data/configs/sead-doi-service-doi.properties
echo "doi.shoulder.prod=$DOI_SHOULDER_PROD" >> /con-data/configs/sead-doi-service-doi.properties
echo "doi.shoulder.test=$DOI_SHOULDER_TEST" >> /con-data/configs/sead-doi-service-doi.properties
echo "doi.user=$DOI_USER" >> /con-data/configs/sead-doi-service-doi.properties
echo "doi.pwd=$DOI_PWD" >> /con-data/configs/sead-doi-service-doi.properties

cp /con-data/configs/sead-doi-service-doi.properties /opt/tomcat8/webapps/sead-doi-service/WEB-INF/classes/org/seadva/services/util/doi.properties


##############################################

## Generating DataONE MN API properties file ##

##############################################

if [ -z "$DATAONE_NODE_IDENTIFIER" ]; then
    DATAONE_NODE_IDENTIFIER="dataone_node_identifier"
fi
if [ -z "$DATAONE_CONTACT_SUBJECT" ]; then
    DATAONE_CONTACT_SUBJECT="dataone_contact_subject"
fi
if [ -z "$DATAONE_BASE_URL" ]; then
    DATAONE_BASE_URL="dataone_base_url"
fi
if [ -z "$DATAONE_SYNC_YEAR" ]; then
    DATAONE_SYNC_YEAR="*"
fi
if [ -z "$DATAONE_SYNC_MONTH" ]; then
    DATAONE_SYNC_MONTH="*"
fi
if [ -z "$DATAONE_SYNC_DAYM" ]; then
    DATAONE_SYNC_DAYM="*"
fi
if [ -z "$DATAONE_SYNC_DAYW" ]; then
    DATAONE_SYNC_DAYW="?"
fi
if [ -z "$DATAONE_SYNC_HOUR" ]; then
    DATAONE_SYNC_HOUR="*"
fi
if [ -z "$DATAONE_SYNC_MIN" ]; then
    DATAONE_SYNC_MIN="0/3"
fi
if [ -z "$DATAONE_SYNC_SEC" ]; then
    DATAONE_SYNC_SEC="45"
fi
if [ -z "$DATAONE_EMAIL_USERNAME" ]; then
    DATAONE_EMAIL_USERNAME="dataone_email_username"
fi
if [ -z "$DATAONE_EMAIL_PASSWORD" ]; then
    DATAONE_EMAIL_PASSWORD="dataone_email_password"
fi

touch sead-dataone-default.properties
echo "mongo.host=localhost" > /con-data/configs/sead-dataone-default.properties
echo "mongo.port=27017" >> /con-data/configs/sead-dataone-default.properties
echo "dataone.db.name=sead-dataone" >> /con-data/configs/sead-dataone-default.properties
echo "node.identifier=$DATAONE_NODE_IDENTIFIER" >> /con-data/configs/sead-dataone-default.properties
echo "contact.subject=$DATAONE_CONTACT_SUBJECT" >> /con-data/configs/sead-dataone-default.properties
echo "base.url=$DATAONE_BASE_URL" >> /con-data/configs/sead-dataone-default.properties
echo "#Synchronization schedule" >> /con-data/configs/sead-dataone-default.properties
echo "year=$DATAONE_SYNC_YEAR" >> /con-data/configs/sead-dataone-default.properties
echo "month=$DATAONE_SYNC_MONTH" >> /con-data/configs/sead-dataone-default.properties
echo "day.of.month=$DATAONE_SYNC_DAYM" >> /con-data/configs/sead-dataone-default.properties
echo "day.of.week=$DATAONE_SYNC_DAYW" >> /con-data/configs/sead-dataone-default.properties
echo "hour=$DATAONE_SYNC_HOUR" >> /con-data/configs/sead-dataone-default.properties
echo "minute=$DATAONE_SYNC_MIN" >> /con-data/configs/sead-dataone-default.properties
echo "second=$DATAONE_SYNC_SEC" >> /con-data/configs/sead-dataone-default.properties
echo "#Email credentials" >> /con-data/configs/sead-dataone-default.properties
echo "email.username=$DATAONE_EMAIL_USERNAME" >> /con-data/configs/sead-dataone-default.properties
echo "email.password=$DATAONE_EMAIL_PASSWORD" >> /con-data/configs/sead-dataone-default.properties

cp /con-data/configs/sead-dataone-default.properties /opt/tomcat8/webapps/sead/WEB-INF/classes/org/sead/va/dataone/util/default.properties
