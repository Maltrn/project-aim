#!/bin/bash
# Start MongoDB
/usr/bin/mongod --quiet --config /etc/mongod.conf &
# Import testdata
mongorestore --host localhost:27017 --db test ./dumpdir/test
# Start Backend
java -jar ./build/libs/project-aim.jar
# Start Frontend
#cd frontend
#npm start
