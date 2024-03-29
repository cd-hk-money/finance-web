FROM openjdk:11-jre-slim
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8060
CMD ["java", "-jar", "app.jar"]