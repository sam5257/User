#!/bin/sh
current_time=$(date "+%Y.%m.%d-%H.%M.%S")
echo $current_time
cd /Users/sameer/IdeaProjects/User
pwd
mvn package -DskipTests
cd /Users/sameer/Development/apache-tomcat-8.5.50/bin
pwd
sh shutdown.sh
cd  /Users/sameer/Development/apache-tomcat-8.5.50/webapps
pwd
rm -d User
mv User.war User.war.$current_time
mv User.war.$current_time backup
cp /Users/sameer/IdeaProjects/User/target/User.war /Users/sameer/Development/apache-tomcat-8.5.50/webapps
cd /Users/sameer/Development/apache-tomcat-8.5.50/bin
pwd

if [ ! -z "$1" ]
then
  echo "debug mode"
  sh catalina.sh jpda start

else
  echo "normal mode"
  sh startup.sh
fi