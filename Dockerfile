FROM tomcat:10-jdk17-openjdk-buster
ADD ./ROOT.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]