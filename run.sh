#!/bin/bash
# Start Backend
sudo service start mongod &
java -jar ./build/libs/project-aim.jar &
# Start Frontend
cd frontend
npm start