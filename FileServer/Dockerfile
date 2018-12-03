 FROM openjdk
 ADD ./target/backend-ConfigServer-1.0.0.jar app.jar
 EXPOSE 1201
 RUN bash -c 'touch /app.jar'
 ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]