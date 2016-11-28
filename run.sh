#!/bin/bash
# Start MongoDB
/usr/bin/mongod --quiet --config /etc/mongod.conf &
# Start Backend
java -jar ./build/libs/project-aim.jar &
# Start Frontend
cd frontend
npm start