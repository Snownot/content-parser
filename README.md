# Сервис синтаксического анализа строк

#### Протокол запроса:

| Метод | Конечная точка | Описание |
| --- | --- | --- |
| POST | /parser/all/ | тело запроса/ответа в фомате JSON |

#### Пример запроса:
{
"words": [
"string"
],
"text": "string"
}

#### Пример ответа:
[
{
"word": "string",
"count": 1
}
]

#### Настройки приложения java/**/application.yml:
domain.name - имя хоста(для конфигурации Swagger)
server.port - порт приложения
Url адрес Swagger ui генерируется по правилу - domain.name:server.port

#### Настройки интеграционного теста test/**/application.yml:
domain.name - имя хоста(для конфигурации Swagger)
server.port - порт приложения
test.requestUri - путь к методу запроса для проверки работоспособности
Url адрес Swagger ui генерируется по правилу - domain.name:server.port

#### Запуск:
mvn clean install - билд проеста с предварительным запуском тестов
java -jar content-parser-1.0.jar - запуск приложения

#### Отчеты:
логи приложения - настройка пути в java/**/application.yml
логи тестов - астройка пути в test/**/application.yml
отчет после тестирования и чек лист располагается - target/surefire-reports/index.html


