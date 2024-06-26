FROM maven:3.8.1-openjdk-17-slim AS builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/nikon-backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
