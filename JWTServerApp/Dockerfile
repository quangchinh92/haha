# Step 1: Use a lightweight JRE base image
FROM eclipse-temurin:21-jdk

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the generated JAR into the container
# (Adjust target/ to build/libs/ if using Gradle)
COPY build/libs/*.jar app.jar

# Step 4: Inform Docker that the app runs on port 8080
EXPOSE 8080

# Step 5: Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]