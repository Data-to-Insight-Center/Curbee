#
# Java 8 & Tomcat 8 & Dockerfile
#

# Build (or rebuild) using
# docker build -t d2i/mm-docker-tomcat .

# pull base image.
FROM ubuntu:latest

# maintainer details
MAINTAINER D2I "d2i@indiana.edu"

WORKDIR /opt

# update source  
RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe"> /etc/apt/sources.list  
RUN apt-get update \
  && apt-get install -y wget zip \
  && apt-get -y install curl \
  && rm -rf /var/lib/apt/lists/* 
  
# install mongoDB
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
RUN echo "deb [ arch=amd64,arm64 ] http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.4.list
RUN apt-get update \
  && apt-get install -y mongodb-org
  
# Install JDK 8  
RUN cd /tmp &&  curl -L 'http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie; gpw_e24=Dockerfile' | tar -xz  
RUN mkdir -p /usr/lib/jvm  
RUN mv /tmp/jdk1.8.0_131/ /usr/lib/jvm/java-8-oracle/  
  
# Set Oracle JDK 8 as default Java  
RUN update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-8-oracle/bin/java 300     
RUN update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-8-oracle/bin/javac 300     
  
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle/
  
# Install tomcat8  
RUN cd /tmp && curl -L 'https://archive.apache.org/dist/tomcat/tomcat-8/v8.0.32/bin/apache-tomcat-8.0.32.tar.gz' | tar -xz  
RUN mv /tmp/apache-tomcat-8.0.32/ /opt/tomcat8/  
  
ENV CATALINA_HOME /opt/tomcat8  
ENV PATH $PATH:$CATALINA_HOME/bin
  
ADD tomcat8.sh /etc/init.d/tomcat8  
RUN chmod 755 /etc/init.d/tomcat8

#copy config file and change the configuration settings
RUN mkdir -p -m 755 /con-data/wars/
WORKDIR /con-data/wars
ADD resources/wars/* /con-data/wars/
RUN mkdir -p -m 755 /con-data/configs/
ADD resources/configs/* /con-data/configs/

RUN cd /con-data/wars/
RUN unzip sead-pdt.war -d sead-pdt
RUN unzip sead-mm.war -d sead-mm
RUN unzip va-workflow.war -d va-workflow
RUN unzip sead-c3pr.war -d sead-c3pr
RUN unzip metadata-gen.war -d metadata-gen
RUN unzip sead-doi-service.war -d sead-doi-service
RUN unzip sead-dataone.war -d sead

RUN cp ../configs/sead-pdt-default.properties sead-pdt/WEB-INF/classes/org/seadpdt/util/default.properties
RUN cp ../configs/sead-pdt-googleplusprovider.properties sead-pdt/WEB-INF/classes/googleplusprovider.properties
RUN cp ../configs/sead-pdt-linkedinprovider.properties sead-pdt/WEB-INF/classes/linkedinprovider.properties
RUN cp ../configs/sead-mm-default.properties sead-mm/WEB-INF/classes/org/sead/matchmaker/default.properties
RUN cp ../configs/va-workflow-sead-wf.xml  va-workflow/WEB-INF/classes/org/sead/workflow/sead-wf.xml 
RUN cp ../configs/sead-c3pr-default.properties  sead-c3pr/WEB-INF/classes/org/sead/api/util/default.properties 
RUN cp ../configs/metadata-gen-Config.properties metadata-gen/WEB-INF/classes/org/seadva/metadatagen/util/Config.properties
RUN cp ../configs/sead-doi-service-doi.properties sead-doi-service/WEB-INF/classes/org/seadva/services/util/doi.properties
RUN cp ../configs/sead-dataone-default.properties sead/WEB-INF/classes/org/sead/va/dataone/util/default.properties


EXPOSE 8080

# Run MongoDB in two ports
ADD resources/configs/mongod.conf /etc/
ADD resources/configs/mongod_ore.conf /etc/
RUN mkdir -p -m 755 /var/log/mongodb/ 
RUN mkdir -p -m 755 /data/db
RUN mkdir -p -m 755 /data/db_ore
RUN mkdir -p -m 755 /var/run/mongodb/ 

RUN cd /con-data/wars/
RUN cp -r  sead-pdt /opt/tomcat8/webapps/
RUN cp -r  sead-mm /opt/tomcat8/webapps/
RUN cp -r  va-workflow /opt/tomcat8/webapps/
RUN cp -r  sead-c3pr /opt/tomcat8/webapps/
RUN cp -r  metadata-gen /opt/tomcat8/webapps/
RUN cp -r  sead-doi-service /opt/tomcat8/webapps/
RUN cp -r  sead /opt/tomcat8/webapps/

COPY docker_script.sh docker_script.sh
COPY config_setup.sh config_setup.sh
RUN chmod +x docker_script.sh
RUN chmod +x config_setup.sh
RUN cat docker_script.sh
ENTRYPOINT ["./docker_script.sh"]
