FROM openjdk:11-jre

RUN apt-get update -y 
RUN apt-get install -y libfreetype6
        
#CHANGE THIS
COPY ./target/vetc-1.0.0-SNAPSHOT.jar /opt/app.jar 
    
EXPOSE 8080

ENV TZ=Asia/Ho_Chi_Minh

ENTRYPOINT ["java","-jar","/opt/app.jar"]
