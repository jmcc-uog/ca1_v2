FROM tomcat:10-jdk17-openjdk-buster
ADD ./target/ROOT.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]