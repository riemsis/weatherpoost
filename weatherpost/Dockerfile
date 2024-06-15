# Use an official Java runtime as a parent image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/weather-post-0.0.1-SNAPSHOT.jar /app/weather-post.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container launches
CMD ["java", "-jar", "/app/weather-post.jar"]
