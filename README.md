# SPbSTU Java Backend Application

## 📦 Установка и запуск

### 🔧 Предварительные требования

- **Java 17+**  
- **Gradle** (используется Gradle Wrapper)
- **Docker** (для контейнеризации приложения)
- **Git** (для клонирования репозитория)

### ✅ 1. Установка Java 17

1. Скачайте Temurin 17 для Windows по [ссылке](https://adoptium.net/download/)
2. Убедитесь, что существует `JAVA_HOME`:
    - Win + S → «Система» → «Доп. параметры системы» → «Переменные среды»
    - Должна быть переменная `JAVA_HOME`, путь вида:
      `C:\Program Files\Eclipse Adoptium\jdk-17.x.x_x`
    - `Path` должен содержать: `%JAVA_HOME%\bin`

3. Проверьте установку:
    ```bash
    java -version
    ```

---

### ✅ 2. Установка Docker

1. Скачайте Docker Desktop по [ссылке](https://www.docker.com/products/docker-desktop)
2. Установите и перезагрузитесь
3. Проверьте:
    ```bash
    docker --version
    ```

---

### 🚀 Шаги по развертыванию

1. **Клонируйте репозиторий:**

   ```bash
   git clone https://github.com/YanaED/spbstu_java.git
   cd spbstu_java
   ```

2. **Соберите проект с помощью Gradle:**

   ```bash
   ./gradlew build
   ```

3. **Создайте Docker-контейнеры:**

   ```bash
   docker-compose up -d --build
   ```

4. **Запустите сервер:**

   ```bash
   ./gradlew bootRun
   ```

   Приложение будет доступно по адресу: [http://localhost:8080](http://localhost:8080)

---

## 🌐 REST API

### 👤 UserController

- `POST /users/users` – регистрация пользователя
- `GET /users/users/login?username={username}` – вход по имени пользователя

### 📋 TaskController

- `GET /tasks?userId={id}&pending={true|false}` – получить список задач пользователя (с фильтром по "ожидающим")
- `POST /tasks` – создать задачу
- `DELETE /tasks/{id}` – удалить задачу по ID

### 🔔 NotificationController

- `GET /notifications?userId={id}` – получить все уведомления пользователя
- `GET /notifications/pending?userId={id}` – получить только "ожидающие" уведомления пользователя

---

## 🧪 Тестирование API

Для тестирования API можно использовать инструменты, такие как:

- [Postman](https://www.postman.com/)
- [curl](https://curl.se/)

Пример запроса с использованием `curl`:

```bash
curl http://localhost:8080/tasks?userId=1
```

---

## 🛠️ Технологии

- **Java 17**
- **Spring Boot**
- **Gradle**
- **Docker**