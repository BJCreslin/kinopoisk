# kinopoisk

Решение к тестовому заданию

1. Консольное приложение расположено в модуле “kinopoisk-console”
2. По умолчанию парсит данные из задания по адресу прописанному в kinopoisk.properties , по
   умолчанию http://www.kinopoisk.ru/top/
3. Если запускать программу несколько раз, через небольшие промежутки времени, алгоритмы Кинопоиска определяют
   автоматический сбор данных, и выдачу прекращают. Для этих случаев у программы есть возможность получения данных из
   сохраненной страницы в src/main/resources/html/250.html. Для этого программу надо запустить с параметром file .
4. Полученные данные программа выводит на экран и сохраняет в базу данных, H2 в файле file:~/data/kinopoisk .
5. Если данные рейтинга за этот день уже есть в базе, то выводится предупреждение “The rating for the film has already
   been received today” и в базу данных не сохраняется.

6. Веб приложение расположено в модуле “kinopoisk-console”
7. представляет собой простой сервлет, на основе Thymeleaf, в нем поле выбора даты и таблица с результатами топ фильмов
   на указанную дату.
8. Количество выводимых фильмов можно поменять в application.properties
9. На сервисном слое использован кэширующий слой, на Encache.
10. В случае если приходящие в post запросе данные с датой ошибочны, то берется сегодняшняя дата.

Тестовое задание для java-разработчика

Необходимо написать консольное приложение, собирающее данные с рейтинга Кинопоиска (http://www.kinopoisk.ru/top/), и
сохраняющего позицию, рейтинг, оригинальное название, год и кол-во проголосовавших людей. Также нужно добавить
соответствующие поля в БД для выборки рейтинга на определенную дату. Запускаться будет через cron. Cоздать простой
сервлет, содержащий страницу с топ-10 фильмов на указанную дату. На ней должно присутствовать поле, где пользователь
может указать дату выборки. При выгрузке данных из СУБД должен быть использован кэширующий слой, чтобы избежать запросов
к базе, каждый раз, когда рейтинг должен быть показан. Используемые средства:

- Spring *
- Hibernate
- любая embedded DB
- любой embedded контейнер сервлетов
- maven
- github Критерии оценки:
   чистый, читаемый, структурируемый код  объектно-ориентированный дизайн  схема базы данных
