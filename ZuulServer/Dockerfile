 FROM openjdk
 ADD ./target/backend-ZuulServer-1.0.0.jar app.jar
 EXPOSE 10002
 RUN bash -c 'touch /app.jar'
 ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]