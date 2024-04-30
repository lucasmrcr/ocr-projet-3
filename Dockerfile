FROM maven:3.9.6-amazoncorretto-21 AS BUILDER

# Copy project files
WORKDIR /app
COPY pom.xml /app
COPY src src

RUN mvn clean package

FROM amazoncorretto:21-alpine

# Run it
WORKDIR /home
COPY --from=BUILDER /app/target/**.jar app.jar
CMD ["/bin/sh", "-c", "java -jar -Dspring.profiles.active=${SPRING_PROFILE} /home/app.jar"]
