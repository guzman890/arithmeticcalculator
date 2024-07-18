# Paso 1: Especificar la imagen base
FROM gradle:8.8 AS build

# Paso 2: Establecer el directorio de trabajo
WORKDIR /app

COPY . /app

RUN gradle clean
RUN gradle build

FROM eclipse-temurin:17.0.11_9-jdk-jammy

# Paso 3: Copiar el archivo JAR
COPY --from=build /app/build/libs/arithmeticcalculator-0.0.1-SNAPSHOT.jar /app/arithmeticcalculator.jar

# Paso 4: Exponer el puerto
EXPOSE 8080

# Paso 5: Especificar el comando de ejecución
ENTRYPOINT ["java", "-jar", "/app/arithmeticcalculator.jar"]