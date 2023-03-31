package pro.sky.teamwork.animalsheltertelegrambot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import com.pengrad.telegrambot.request.SetMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalShelterTelegrambotConfiguration {
@Value("${telegram.bot.token}")
    private String token;
@Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
    bot.execute(new DeleteMyCommands());
    bot.execute(new SetMyCommands(
            new BotCommand("/start", "Вывод основного меню Этап 0"),
            new BotCommand("/help","Узнать информацию о приюте")));
        return bot;
    }
}
