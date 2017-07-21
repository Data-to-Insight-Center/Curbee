#!/bin/bash

./config_setup.sh && /usr/bin/mongod --quiet -f /etc/mongod.conf && /usr/bin/mongod --quiet -f /etc/mongod_ore.conf && service tomcat8 start && tail -f /opt/tomcat8/logs/catalina.out
