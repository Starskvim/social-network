FROM openjdk:11

COPY target/SocialNetwork-0.0.1-SNAPSHOT.jar socialNetwork.jar
EXPOSE 8228

ENTRYPOINT ["java","-jar","/socialNetwork.jar"]