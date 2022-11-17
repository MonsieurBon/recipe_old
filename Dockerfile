FROM eclipse-temurin:18
RUN mkdir /opt/recipe
COPY target/recipe*.jar /opt/recipe/recipe.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/recipe/recipe.jar"]
