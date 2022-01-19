#FROM openjdk:11


#COPY target/beStrong_server-0.0.1-SNAPSHOT.jar beStrong_server-0.0.1-SNAPSHOT.jar



#CMD ["java", "-jar", "beStrong_server-0.0.1-SNAPSHOT.jar"]

#
# Build stage
#

FROM openjdk:11.0.13-jdk-slim

COPY target/beStrong_server-0.0.1-SNAPSHOT.jar beStrong_server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/beStrong_server-0.0.1-SNAPSHOT.jar"]
