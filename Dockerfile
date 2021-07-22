FROM openjdk:16
ADD target/AssignmentTwo-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "tl-sqlite.jar"]