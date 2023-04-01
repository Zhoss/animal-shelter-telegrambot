package pro.sky.teamwork.animalsheltertelegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalShelterTelegrambotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;

    private final Logger logger
            = LoggerFactory.getLogger(AnimalShelterTelegrambotUpdatesListener.class);

    public AnimalShelterTelegrambotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Processing update {}", update);
                if (update.callbackQuery() != null) {
                    CallbackQuery callbackQuery = update.callbackQuery();
                    Long chatId = callbackQuery.message().chat().id();
                    String data = callbackQuery.data();

                    switch (data) {
                        case "/help" -> helpCommandHandler(chatId, telegramBot);
                        case "/info" -> infoCommandHandler(chatId, telegramBot);
                        case "/howToTakeADog" -> takeAdogCommandHandler(chatId, telegramBot);
                        case "/report" -> sendReportCommandHandler(chatId, telegramBot);
                        case "/volunteer" -> telegramBot.execute(new SendMessage(chatId,
                                "volunteer"));
                        default -> telegramBot.execute(new SendMessage(chatId,
                                "Неверная команда"));
                    }
                } else {
                    Long chatId = update.message().chat().id();
                    String messageText = update.message().text();
                    if ("/start".equals(messageText)) {
                        telegramBot.execute(new SendMessage(chatId, "Привет! Меня зовут\n" +
                                " AnimalShelterTelegrambot!\n" +
                                " Мы помогаем животным найти хозяина.\n" +
                                "Что Вы хотите узнать?\n" +
                                "\n" +
                                "/help - команды бота"));
                        helpCommandHandler(chatId, telegramBot);
                    } else if ("/help".equals(messageText)) {
                        helpCommandHandler(chatId, telegramBot);
                    } else {
                        telegramBot.execute(new SendMessage(chatId, "Неверная команда"));
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void helpCommandHandler(Long chatId, TelegramBot telegramBot) {
        InlineKeyboardButton inlineKeyboardButton1 =
                new InlineKeyboardButton("Узнать информацию о приюте");
        inlineKeyboardButton1.callbackData("/info");

        InlineKeyboardButton inlineKeyboardButton2 =
                new InlineKeyboardButton("Как взять собаку из приюта");
        inlineKeyboardButton2.callbackData("/howToTakeADog");

        InlineKeyboardButton inlineKeyboardButton3 =
                new InlineKeyboardButton("Прислать отчет о питомце");
        inlineKeyboardButton3.callbackData("/report");

        InlineKeyboardButton inlineKeyboardButton4 =
                new InlineKeyboardButton("Позвать волонтера");
        inlineKeyboardButton4.callbackData("/volunteer");

        List<InlineKeyboardButton> inlineKeyboardButtons = List.of(
                inlineKeyboardButton1,
                inlineKeyboardButton2,
                inlineKeyboardButton3,
                inlineKeyboardButton4
        );

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardButtons.forEach(inlineKeyboardMarkup::addRow);

        SendMessage response = new SendMessage(chatId,
                "Что Вы хотите узнать?");
        response.replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(response);

    }


    public void infoCommandHandler(Long chatId, TelegramBot telegramBot) {
        InlineKeyboardButton inlineKeyboardButton5 =
                new InlineKeyboardButton("История возникновения приюта");
        inlineKeyboardButton5.callbackData("/history");

        InlineKeyboardButton inlineKeyboardButton6 =
                new InlineKeyboardButton("Узнать расписание работы приюта и адрес, схему проезда");
        inlineKeyboardButton6.callbackData("/location");

        InlineKeyboardButton inlineKeyboardButton7 =
                new InlineKeyboardButton("Узнать равила поведения на территории приюта");
        inlineKeyboardButton7.callbackData("/precautions");

        InlineKeyboardButton inlineKeyboardButton8 =
                new InlineKeyboardButton("Оставить контактные данные для связи");
        inlineKeyboardButton8.callbackData("/contacts");

        InlineKeyboardButton inlineKeyboardButton9 =
                new InlineKeyboardButton("Позвать волонтера");
        inlineKeyboardButton9.callbackData("/volunteer");

        InlineKeyboardButton inlineKeyboardButton10 =
                new InlineKeyboardButton("Вернуться");
        inlineKeyboardButton10.callbackData("/help");

        List<InlineKeyboardButton> inlineKeyboardButtons = List.of(
                inlineKeyboardButton5,
                inlineKeyboardButton6,
                inlineKeyboardButton7,
                inlineKeyboardButton8,
                inlineKeyboardButton9,
                inlineKeyboardButton10
        );

        InlineKeyboardMarkup inlineKeyboardMarkup2 = new InlineKeyboardMarkup();
        inlineKeyboardButtons.forEach(inlineKeyboardMarkup2::addRow);

        SendMessage response = new SendMessage(chatId,
                "Информация о приюте");
        response.replyMarkup(inlineKeyboardMarkup2);
        telegramBot.execute(response);
    }

    public void takeAdogCommandHandler(Long chatId, TelegramBot telegramBot) {
        InlineKeyboardButton inlineKeyboardButton11 =
                new InlineKeyboardButton("Узнать правила знакомства с собакой");
        inlineKeyboardButton11.callbackData("/dogrules");

        InlineKeyboardButton inlineKeyboardButton12 =
                new InlineKeyboardButton("Получить список документов");
        inlineKeyboardButton12.callbackData("/documentslist");

        InlineKeyboardButton inlineKeyboardButton13 =
                new InlineKeyboardButton("Транспортировка животного");
        inlineKeyboardButton13.callbackData("/dogmoving");

        InlineKeyboardButton inlineKeyboardButton14 =
                new InlineKeyboardButton("Обустройство дома для щенка");
        inlineKeyboardButton14.callbackData("/yongdogenviroments");

        InlineKeyboardButton inlineKeyboardButton15 =
                new InlineKeyboardButton("Обустройство дома для взрослой собаки");
        inlineKeyboardButton15.callbackData("/olddogenviroments");

        InlineKeyboardButton inlineKeyboardButton16 =
                new InlineKeyboardButton("Обустройство дома для собаки с ограниченными\n" +
                        " возможностями");
        inlineKeyboardButton16.callbackData("/limiteddogenviroments");

        InlineKeyboardButton inlineKeyboardButton17 =
                new InlineKeyboardButton("советы кинолога");
        inlineKeyboardButton17.callbackData("/cynologistadvices");

        InlineKeyboardButton inlineKeyboardButton18 =
                new InlineKeyboardButton("Контакты проверенных кинологов");
        inlineKeyboardButton18.callbackData("/cynologistconacts");

        InlineKeyboardButton inlineKeyboardButton19 =
                new InlineKeyboardButton("Частые причины отказов в выдаче собаки кандидату");
        inlineKeyboardButton19.callbackData("/offtenrefusals");

        InlineKeyboardButton inlineKeyboardButton20 =
                new InlineKeyboardButton("Оставить контактные данные для связи");
        inlineKeyboardButton20.callbackData("/contacts");

        InlineKeyboardButton inlineKeyboardButton21 =
                new InlineKeyboardButton("Позвать волонтера");
        inlineKeyboardButton21.callbackData("/volunteer");

        InlineKeyboardButton inlineKeyboardButton22 =
                new InlineKeyboardButton("Вернуться");
        inlineKeyboardButton22.callbackData("/help");


        List<InlineKeyboardButton> inlineKeyboardButtons = List.of(
                inlineKeyboardButton11,
                inlineKeyboardButton12,
                inlineKeyboardButton13,
                inlineKeyboardButton14,
                inlineKeyboardButton15,
                inlineKeyboardButton16,
                inlineKeyboardButton17,
                inlineKeyboardButton18,
                inlineKeyboardButton19,
                inlineKeyboardButton20,
                inlineKeyboardButton21,
                inlineKeyboardButton22
        );

        InlineKeyboardMarkup inlineKeyboardMarkup2 = new InlineKeyboardMarkup();
        inlineKeyboardButtons.forEach(inlineKeyboardMarkup2::addRow);

        SendMessage response = new SendMessage(chatId,
                "Информация о приюте");
        response.replyMarkup(inlineKeyboardMarkup2);
        telegramBot.execute(response);
    }

    public void sendReportCommandHandler(Long chatId, TelegramBot telegramBot) {
        InlineKeyboardButton inlineKeyboardButton23 =
                new InlineKeyboardButton("Сфотайте на телефон и пришлите фото питомца.");
        inlineKeyboardButton23.callbackData("/photo");

        InlineKeyboardButton inlineKeyboardButton24 =
                new InlineKeyboardButton("Пришлите сегодняшний рацион питомца");
        inlineKeyboardButton24.callbackData("/food");

        InlineKeyboardButton inlineKeyboardButton25 =
                new InlineKeyboardButton("Опишите общее самочуствие и\n" +
                        " привыкание к новому месту питомца");
        inlineKeyboardButton25.callbackData("/health");

        InlineKeyboardButton inlineKeyboardButton26 =
                new InlineKeyboardButton("Опишите изменение в поведении питомца: \n" +
                        "отказ от старых привычек, приобретение новых");
        inlineKeyboardButton26.callbackData("/habits");

        InlineKeyboardButton inlineKeyboardButton27 =
                new InlineKeyboardButton("Вернуться");
        inlineKeyboardButton27.callbackData("/help");

        List<InlineKeyboardButton> inlineKeyboardButtons = List.of(
                inlineKeyboardButton23,
                inlineKeyboardButton24,
                inlineKeyboardButton25,
                inlineKeyboardButton26,
                inlineKeyboardButton27
        );

        InlineKeyboardMarkup inlineKeyboardMarkup2 = new InlineKeyboardMarkup();
        inlineKeyboardButtons.forEach(inlineKeyboardMarkup2::addRow);

        SendMessage response = new SendMessage(chatId,
                "Это ежедневный отчёт о состоянии питомца, который необходимо отправлять в бот\n" +
                        "в течение месяца");
        response.replyMarkup(inlineKeyboardMarkup2);
        telegramBot.execute(response);
    }

}
