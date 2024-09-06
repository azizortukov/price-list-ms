FROM eclipse-temurin:21-jre

WORKDIR /price-list

COPY target/price-list.jar /price-list

EXPOSE 8080
EXPOSE 5455

CMD ["java", "-jar", "price-list.jar"]