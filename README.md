## Приложение стартует на http://localhost:8050/

#### Настройка application.properties:

	"test_db" в строке "spring.datasource.url=jdbc:h2:file:~/test_db;DB_CLOSE_ON_EXIT=FALSE"
	
	логин в spring.datasource.username=root
	
	пароль в spring.datasource.password=
	
#### Просмотр бд: 

	http://localhost:8050/h2-console
	
    Указать: 
	    JDBC URL = test_db
	    User Name = root

## 1. POST localhost:8050/jobs

Входящий формат
("application/xml")
Тестовые данные в файле  src/main/resources/test_data.xml

```
<jobs>
    <job id="16">
        <type>print</type>
        <user>user1</user>
        <device>device1</device>
        <amount>10</amount>
    </job>
        <job id="19">
        <type>scan</type>
        <user>user1</user>
        <device>device1</device>
        <amount>12</amount>
    </job>
        <job id="3">
        <type>fax</type>
        <user>user2</user>
        <device>device1</device>
        <amount>5</amount>
    </job>
</jobs>
```

Возвращаемый результат в формате JSON

{
    "user1": 22,
    "user2": 5
}

## 2.GET localhost:8050/statistics с фильтрацией данных 
#### например 
http://localhost:8050/statistics?user=user1
http://localhost:8050/statistics?user=user1&type=fax
и т.п.

#### Результат в формате JSON

Данные отсортированы в хронологическом порядке
Добавлена фильтрация по датам в формате

timeFrom=21-08-2010%2009:12:07

timeTo=21-08-2010%2009:12:07

где %20 - разделитель

#### Пример запроса 

http://localhost:8050/statistics?timeFrom=21-08-2010%2009:12:07&timeTo=21-08-2022%2009:12:07

## 3. Сборка проекта - скрипт build.sh  запуск  start.sh

