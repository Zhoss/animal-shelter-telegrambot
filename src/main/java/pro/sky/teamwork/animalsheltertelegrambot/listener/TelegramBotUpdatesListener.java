package pro.sky.teamwork.animalsheltertelegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.teamwork.animalsheltertelegrambot.commands.Commands;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.teamwork.animalsheltertelegrambot.commands.Commands.GREET_MSG;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final Commands commands;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.commands = new Commands();
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
                Message message = update.message();
                if (message.text().equals(Commands.START_COMMAND)) {
                    logger.info("Command's been received: " + Commands.START_COMMAND);
                    sendMessage(showChatId(message), GREET_MSG);
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;

    }
    private Long showChatId(Message message) {
        return message.chat().id();
    }
    private void sendMessage(Long chatId, String messageContent) {
        SendMessage sendMessage = new SendMessage(chatId, messageContent);
        telegramBot.execute(sendMessage);
    }
}
