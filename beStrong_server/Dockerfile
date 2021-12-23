#FROM openjdk:11


#COPY target/beStrong_server-0.0.1-SNAPSHOT.jar beStrong_server-0.0.1-SNAPSHOT.jar



#CMD ["java", "-jar", "beStrong_server-0.0.1-SNAPSHOT.jar"]

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/beStrong_server-0.0.1-SNAPSHOT.jar /usr/local/lib/beStrong_server.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/beStrong_server.jar"]
