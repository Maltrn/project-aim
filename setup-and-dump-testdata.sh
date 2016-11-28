#!/bin/bash
./gradlew startMongoDb
(./bin/go clean bootRun &)> bootrun.log
sleep 5
for try in {1..6} ; do
  grep "Started AIMServer" bootrun.log && break
  echo "server has not been startet yet"
  sleep 10
done
curl 'http://localhost:8080/api/setup'
rm -r ./dumpdir
mkdir ./dumpdir
mongodump --host localhost:27017 -d test -o ./dumpdir
./gradlew stopMongoDb
kill $(ps f |grep 'java -Dorg.gradle.appname=gradlew' | grep -v grep | cut -d ' ' -f 1| tr '\n' ' ')

