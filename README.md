# Телеграм-бот приюта для домашних животных
Данное приложение - это телеграм-бот, который сможет отвечать на популярные вопросы людей о том,что
нужно знать и уметь, чтобы забрать животное из приюта.  
Так же этот телеграм-бот контролирует по отчётам новых хозяев животного, как оно приспосабливается
к новым хозяевам и новым условиям жизни.
# Описание работы Телеграм-бота
Функционал приложения разбит на этапы


**Этап 0. Определение запроса. *Это входная точка общения бота с пользователем***.
- Кандидат в усыновители заходит в чат с ботом и нажимает команду `/start`
- Бот приветствует нового кандидата, рассказывает о себе (приюте) и выдаёт список команд (меню ссылок):
    - `/Узнать информацию о приюте` (этап 1). При выборе этой команды выводится новый список команд
      (меню ссылок) данного этапа.
    - `/Как взять собаку из приюта` (этап 2). При выборе этой команды выводится новый список команд
      (меню ссылок) данного этапа.
    - `/Прислать отчет о питомце` (этап 3). При выборе этой команды выводится новый список команд
      (меню ссылок) данного этапа.
    - `/Позвать волонтера`. При выборе этой команды посылается сообщения в общий чат волонтёров с просьбой связаться
  с данным кандидатом по телефону.
Вернуться в данный этап кандидат может нажав на кнопку `/вернуться` на любом этапе. В этом случае бот выводит список команд
без рассказа о себе (приюте).


  **Этап 1. На этот этап кандидат попадает после нажатия команды `/узнать информацию о приюте`.** Бот приветствует кандидата.
  Выдаётся следующий список команд (меню ссылок)
- `/История возникновения приюта`. При выборе этой команды на экран выводится дата возникновения приюта, сколько 
животных принято в приют, сколько животных нашли своих новых хозяев (можно по годам) и другая информация.
- `/Узнать расписание работы приюта и адрес, схему проезда`. При выборе этой команды на экран выводится расписание
работы приюта, его адрес и схема проезда (опционально).
- `/Узнать равила поведения на территории приюта`. При выборе этой команды на экран выводится правила техники безопасности
на территории приюта
- `/Оставить контактные данные для связи`. При выборе этой команды бот запрашивает имя кандидата, кандидат отправляет
своё имя боту, затем бот заправшивает номер телефона кандидата, кандидат отправляет номер для связи, после чего
эти данные заносятся в БД приложения, а бот выводит сообщение 'Наши волонтёры скоро свяжутся с Вами.'
- `/Позвать волонтера`. При выборе этой команды посылается сообщения в общий чат волонтёров с просьбой связаться
  с данным кандидатом по телефону.
- `/вернуться`. При выборе данной команды бот выводит список команд Этапа 0 без рассказа о себе (приюте).

**Этап 2. На этот этап кандидат попадает после нажатия команды `/Как взять собаку из приюта`.** Бот приветствует кандидата.
Выдаётся следующий список команд (меню ссылок)
- `/Узнать правила знакомства с собакой`. При выборе этой команды бот выдаёт правила знакомства с собакой до того,
как можно забрать ее из приюта.
- `/Получить список документов`. При выборе этой команды бот выдаёт список документов, необходимых для того,
чтобы взять собаку из приюта.
- `/Транспортировка животного`. При выборе этой команды бот выдаёт список рекомендаций по транспортировке животного.
- `/Обустройство дома для щенка`. При выборе этой команды бот выдаёт список рекомендаций по обустройству дома для щенка.
- `/Обустройство дома для взрослой собаки`.При выборе этой команды бот выдаёт список рекомендаций по обустройству дома
для взрослой собаки.
- `/Обустройство дома для собаки с ограниченными возможностями`. При выборе этой команды бот выдаёт список рекомендаций
по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение).
- `/советы кинолога`. При выборе этой команды бот выдаёт советы кинолога по первичному общению с собакой.
- `/Контакты проверенных кинологов`. При выборе этой команды бот выдаёт рекомендации по проверенным кинологам
для дальнейшего обращения к ним.
- `/Частые причины отказов в выдаче собаки кандидату`. При выборе этой команды бот выдаёт список причин, почему могут
отказать и не дать забрать собаку из приюта.
- `/Оставить контактные данные для связи`. При выборе этой команды бот запрашивает имя кандидата, кандидат отправляет
  своё имя боту, затем бот заправшивает номер телефона кандидата, кандидат отправляет номер для связи, после чего
  эти данные заносятся в БД приложения, а бот выводит сообщение 'Наши волонтёры скоро свяжутся с Вами.'
- `/Позвать волонтера`. При выборе этой команды посылается сообщения в общий чат волонтёров с просьбой связаться
  с данным кандидатом по телефону.
- `/вернуться`. При выборе данной команды бот выводит список команд Этапа 0 без рассказа о себе (приюте).

**Этап 3. На этот этап кандидат попадает после нажатия команды `/Прислать отчет о питомце`.**  
Бот выдаёт сообщение: `"Это ежедневный отчёт о состоянии питомца, который необходимо отправлять в бот
в течение месяца. Сфотайте на телефон и пришлите фото питомца."` После проверки, что отправлено фото,
бот выдаёт сообщение: `"Пришлите сегодняшний рацион питомца."` После проверки, что отправлен текст,
бот выдаёт сообщение: `"Опишите общее самочуствие и привыкание к новому месту питомца."` После
проверки, что отправлен текст, бот выдаёт сообщение: `"Опишите изменение в поведении питомца: 
отказ от старых привычек, приобретение новых."` После проверки, что отправлен текст, бот выдаёт
сообщение: `"Информация получена."` Данные, полученные от кандидата, сохраняются в БД приюта.

**Работа волонтёра**  
- Раз в два-три дня волонтеры отсматривают все присланные отчеты, путём ГЕТ-запроса через браузер.  
В случае, если усыновитель недолжным образом заполнял отчет, волонтер через бота может дать обратную
связь в стандартной форме:  
```
"Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. Пожалуйста,
 подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны
 самолично проверять условия содержания собаки".
```

- В базу новых усыновителей кандидат попадает через волонтера, который его туда заносит
путём ГЕТ-запроса через браузер.
- Волонтёр определяет, прошёл ли усыновитель испытательный срок

**Работа бота**  
- Ежедневное напоминание кандидату о необходимости отправить отчёт.
- В случае, если проходит более 2 дней без отчётов от усыновителя, то отправлять запрос волонтеру на
связь с усыновителем.
- Как только период в 30 дней заканчивается, волонтеры принимают решение о том, остается собака
  у хозяина или нет. Испытательный срок может быть пройден, может быть продлен на любое количество дней,
  а может быть не пройден.
- Если усыновитель прошел испытательный срок, то бот поздравляет его стандартным сообщением.
- Если усыновителю было назначено дополнительное время испытательного срока, то бот сообщает ему
и указывает количество дополнительных дней.
- Если усыновитель не прошел испытательный срок, то бот уведомляет его об этом и дает инструкции
по дальнейшим шагам.

[//]: # (## Запуск приложения)
# Авторы проекта
Жосан Денис  
Завалин Игорь  
Клочко Виталий  
Щербаков Дмитрий

[//]: # (## Демо проекта)
[//]: # (## Как стать соавтором проекта, или содействовать в его продвижении)
[//]: # (## Кодекс поведения в проекте)
[//]: # (## Куда обратиться в случае обнаружения багов в коде, или проблем с безопасностью приложения)
[//]: # (## Под какой лицензией распространяется приложение)
# Технологии, использованные в проекте.
Язык и окружение: Java 17, Spring Boot  
Тестирование:   
Прочее: 