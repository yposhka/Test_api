## Тестирование API
### Используемые технологии
> В проекте для тестирования API используются три библиотеки: Rest Assured, JUnit, Jackson.
* Язык программирования: Java 17;
* Cистема сборки: Gradle 8.1;
* Библиотека по тестированию: Rest-assured 5.3.0;
* Фреймворк для тестирования: JUnit 4.13.2 и JUnit JUpiter 5.7.0;
* Библиотека для работы с JSON: Jackson 2.16.1.
### Классы и методы
>Ссылка на открытую API - http://85.192.34.140:8080/swagger-ui/index.html#

>files-controller, файл - RequestFiles.java
1. getImage - метод для проверки загрузки картинки;
2. postImage - метод для проверки отправки файла на сервер.
>game-controller-new, файл - RequestGame.java
1. getAllGame - метод для получения массива игр после добавление игры в свой список, получение обновленного массива и сравнения результатов с методом getAllGame;
2. deleteGame - метод для удаления игры по id.
>jwt-authentication-controller, файл - RequestUser.java
1. getToken - метод для получения jwt токена.
>status-codes-controller, файл - RequestStatusCode.java
1. getRequestSuccess - метод для проверки http кода 200;
2. getRequestUnSuccess400 - метод для проверки http кода 400.
>user-controller-new, файл - RequestUser.java
1. postCreateUser - метод для создания пользователя;
2. putPassword - метод для изменения пароля пользователя;
3. getUserInfo - метод для получения информации о пользователе;
4. deleteUser - метод для удаления аккаунта пользователя.

## Работа с SQL
>папка: SQL \
>файл - Проверки.md и файл - Проверки.pdf \
В данных файлах хранятся sql скрипты для проверки БД - Расписание. \
Ссылка на песчницу - https://sql-academy.org/ru/sandbox


### <span style="color:red"> Пометка </span>
Если при запуске проекта сброщик выдает ошибку: Конфликт версии Java и версии Gradle. То нужно будет именить версии Gradle (8.1 или новее). \
Файл - gradle-wrapper.properties Строка - distributionUrl=https\://services.gradle.org/distributions/gradle-8.1-bin.zip
