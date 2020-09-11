FROM tomcat

COPY /target/forum-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
