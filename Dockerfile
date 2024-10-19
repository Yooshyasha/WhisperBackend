FROM openjdk:21-jdk-slim AS build
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