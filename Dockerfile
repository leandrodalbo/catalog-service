FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR workingspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog.jar
ENTRYPOINT ["java", "-jar", "catalog.jar"]