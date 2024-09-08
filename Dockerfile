FROM eclipse-temurin:21-jre

WORKDIR /price-list

COPY target/price-list-ms-1.0.1.jar /price-list

EXPOSE 8080
EXPOSE 5432

CMD ["java", "-jar", "price-list-ms-1.0.1.jar"]