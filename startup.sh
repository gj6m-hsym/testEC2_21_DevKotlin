#!/bin/bash

# front end
if [ -a /home/appdata/nuxtTest/server/index.mjs ]; then
    node /home/appdata/nuxtTest/server/index.mjs &
    sleep 5
    curl http://localhost:3000 > /dev/null 2>&1
    if [ "$?" != "0" ]; then
        echo not running: 3000
        exit 1
    fi
fi

# back end
if [ -a /home/appdata/demo-0.0.1-SNAPSHOT.jar ]; then
    java -jar /home/appdata/demo-0.0.1-SNAPSHOT.jar &
    sleep 800
    curl http://localhost:8080 > /dev/null 2>&1
    if [ "$?" != "0" ]; then
        echo not running: 8080
        exit 1
    fi
fi

# common
echo password | sudo -S /usr/sbin/nginx -g 'daemon off;' -c /etc/nginx/nginx.conf > /dev/null 2>&1
if [ "$?" != "0" ]; then
    echo not running: 80
    exit 1
fi
exit 0