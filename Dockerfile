# Этап 1: Билд приложения
FROM gradle:8.2.1-jdk21 AS build

ENV JAVA_HOME /usr/local/openjdk-21
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -version

# Устанавливаем рабочую директорию для сборки
WORKDIR /app

# Копируем исходный код и файлы сборки в контейнер
COPY src/main/kotlin/com/yooshyasha/WhisperBackend/controller /app

# Собираем проект
RUN gradle build --no-daemon

# Этап 2: Создание минимального образа для запуска приложения
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию для приложения
WORKDIR /app

# Копируем скомпилированный .jar файл из этапа билда
COPY --from=build /app/build/libs/WhisperBackend-1.0.jar /app/WhisperBackend-1.0.jar

# Запускаем приложение
CMD ["java", "-jar", "/app/WhisperBackend-1.0.jar"]