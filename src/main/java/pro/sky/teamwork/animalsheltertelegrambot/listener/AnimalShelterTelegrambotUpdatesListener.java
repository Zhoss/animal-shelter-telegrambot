package pro.sky.teamwork.animalsheltertelegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
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
                Long chatId = update.message().chat().id();
                String messageText = update.message().text();
                if ("/start".equals(messageText)) {
                    telegramBot.execute(new SendMessage(chatId, "Привпетствие и\n" +
                            " список команд: /start\n /info"));

                }

                else {
                    telegramBot.execute(new SendMessage(chatId, "Неверная команда"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
