package pro.sky.teamwork.animalsheltertelegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
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
                        case "/info" -> telegramBot.execute(new SendMessage(chatId,
                                "/info"));
                        case "/howToTakeADog" -> telegramBot.execute(new SendMessage(chatId,
                                "/howToTakeADog"));
                        case "/report" -> telegramBot.execute(new SendMessage(chatId,
                                "report"));
                        case "/volunteer" -> telegramBot.execute(new SendMessage(chatId,
                                "volunteer"));
                        default -> telegramBot.execute(new SendMessage(chatId,
                                "Неверная команда"));
                    }
                }
                Long chatId = update.message().chat().id();
                String messageText = update.message().text();
                if ("/start".equals(messageText)) {
                    telegramBot.execute(new SendMessage(chatId, "Привет! Меня зовут\n" +
                            " AnimalShelterTelegrambot!\n" +
                            " Мы помогаем милым животным найти любящего хозяина.\n" +
                            "О чем ты хочешь узнать?\n" +
                            "\n" +
                            "/help - команды бота"));
                } else if ("/help".equals(messageText)) {
                    infoCommandHandler(chatId, telegramBot);
                } else {
                    telegramBot.execute(new SendMessage(chatId, "Неверная команда"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void infoCommandHandler(Long chatId, TelegramBot telegramBot) {
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
                "/help - команды бота");
        response.replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(response);


//        process();

    }
}
