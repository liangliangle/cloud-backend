 FROM openjdk
 ADD ./target/backend-UserServer-1.0.0.jar app.jar
 EXPOSE 3055
 RUN bash -c 'touch /app.jar'
 ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]