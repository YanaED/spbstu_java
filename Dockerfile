FROM gradle:8.5-jdk17 AS build

COPY . /srv
COPY settings.gradle /home/gradle
COPY build.gradle /home/gradle

RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /srv

COPY --from=build /srv/build/libs/java_spbstu-0.0.1-SNAPSHOT.jar java_spbstu-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "java_spbstu-0.0.1-SNAPSHOT.jar"]
