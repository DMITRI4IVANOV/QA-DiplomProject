# Дипломный проект профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API
Банка.

## Документация

[Дипломное задание](https://github.com/netology-code/qa-diploma)

[План автоматизации тестирования веб-формы сервиса покупки туров ](https://github.com/DMITRI4IVANOV/QA-DiplomProject/blob/main/docs/Plan.md)

## Запуск приложения

Перед запуском необходимо выполнить следующие предусловия. Если у вас уже есть необходимое ПО, то понадобится только п.1 и запуск Docker.

*Предусловия:*
1. Необходимо склонировать репозиторий или скачать архив по [ссылке](https://github.com/DMITRI4IVANOV/QA-DiplomProject.git). Или воспользоваться VCS Git, встроенной в
   IntelliJ IDEA.
2. Установить и запустить Docker Desktop. Это можно сделать [здесь](https://docs.docker.com/get-docker/) в зависимости от операционной системы. Дополнительные инструкции по установке Docker [ссылке](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md)
3. Открыть проект в IntelliJ IDEA

### Запуск

1. Запустить необходимые базы данных (MySQL и PostgreSQL), а также NodeJS. Параметры для запуска хранятся в
   файле `docker-compose.yml`. Для запуска необходимо ввести в терминале команду:

> * `docker-compose up -d`

2. В новой вкладке терминала ввести следующую команду в зависимости от базы данных

> * `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar ./artifacts/aqa-shop.jar` - для MySQL
> * `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar ./artifacts/aqa-shop.jar` - для PostgreSQL

3. Проверка работающих контейнеров:

> * `docker ps`

4. Приложение должно запуститься по адресу

> * `http://localhost:8080/`
> ## Тесты
### Страница "Путешествие дня"
1. Тестирование кнопки "Купить"
2. Тестирование кнопки "Купить в кредит"
### Страница "Оплата по карте"
3. Ввод валидных значений по 'Карта1'
4. Ввод валидных значений по 'Карта2' - `баг`[Issues1](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/1)
5. Не вводить данные в поля
6. Ввод данных с невалидными значениями 16 цифр в поле 'Номер карты'
7. Ввод данных с невалидными значениями 15 цифра в поле 'Номер карты'
8. Ввод данных с невалидными значениями 17 цифра в поле 'Номер карты'
9. Проверка всплывающих окон при вводе данных с невалидными значениями в поле 'Номер карты' - `баг` [Issues2](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/2)
10. Ввод данных с валидными значениями (от 1 до 9) в поле 'Месяц'
11. Ввод данных с невалидными значениями в поле 'Месяц'
12. Ввод данных с невалидными значениями (от 00 до предыдущего год) в поле 'Год'
13. Ввод данных с невалидными значениями (текущий год, месяц прошедший) в поле 'Год'
14. Ввод данных с невалидными значениями (с +6 лет к текущему до 99) в поле 'Год'
15. Ввод данных с невалидными значениями (Имя и Фамилию на кириллице) в поле 'Владелец' - `баг`  [Issues3](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/3)
16. Ввод данных с невалидными значениями (набор букв на латинице) в поле 'Владелец' - `баг` [Issues4](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/4)
17. Ввод данных с невалидными значениями (цифры и спецсимволы) в поле 'Владелец' - `баг` [Issues5](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/5)
18. Ввод данных с невалидными значениями (1 или 2 символа) в поле 'CVC/CVV'
### Страница "Кредит по данным карты"
19. Ввод валидных значений по 'Карта1'
20. Ввод валидных значений по 'Карта2' - `баг`[Issues6](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/6) 
21. Не вводить данные в поля
22. Ввод данных с невалидными значениями 16 цифр в поле 'Номер карты'
23. Ввод данных с невалидными значениями 15 цифра в поле 'Номер карты'
24. Ввод данных с невалидными значениями 17 цифра в поле 'Номер карты'
25. Проверка всплывающих окон при вводе данных с невалидными значениями в поле 'Номер карты' - `баг` [Issues7](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/7)
26. Ввод данных с валидными значениями (от 1 до 9) в поле 'Месяц'
27. Ввод данных с невалидными значениями в поле 'Месяц'
28. Ввод данных с невалидными значениями (от 00 до предыдущего год) в поле 'Год'
29. Ввод данных с невалидными значениями (текущий год, месяц прошедший) в поле 'Год'
30. Ввод данных с невалидными значениями (с +6 лет к текущему до 99) в поле 'Год'
31. Ввод данных с невалидными значениями (Имя и Фамилию на кириллице) в поле 'Владелец' - `баг` [Issues8](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/8)
32. Ввод данных с невалидными значениями (набор букв на латинице) в поле 'Владелец' - `баг` [Issues9](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/9)
33. Ввод данных с невалидными значениями (цифры и спецсимволы) в поле 'Владелец' - `баг` [Issues10](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/10)
34. Ввод данных с невалидными значениями (1 или 2 символа) в поле 'CVC/CVV'

* Неправильное название города на странице- `баг`[Issues11](https://github.com/DMITRI4IVANOV/QA-DiplomProject/issues/11)


