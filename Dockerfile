# Этап 1: Билд приложения
FROM ghcr.io/railwayapp/nixpacks:ubuntu-1722297819 AS build

# Установка Java
RUN apt-get update && apt-get install -y openjdk-21-jdk

RUN java -version


WORKDIR /app/
COPY . /app/.

RUN ./gradlew clean build -x check -x test

# Этап 2: Создание минимального образа для запуска приложения
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию для приложения
WORKDIR /app

# Копируем скомпилированный .jar файл из этапа билда
COPY --from=build /app/build/libs/WhisperBackend-1.0.jar /app/WhisperBackend-1.0.jar

# Запускаем приложение
CMD ["java", "-jar", "/app/WhisperBackend-1.0.jar"]