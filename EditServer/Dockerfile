 FROM open-jre-8
 ADD ./target/backend-EditServer-1.0.0.jar app.jar
 EXPOSE 3054
 RUN bash -c 'touch /app.jar'
 ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]