package ua.qalight.telegrambot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.qalight.telegrambot.service.TelegarmBot;

@Component
public class BotInitializer {

    private final TelegarmBot telegarmBot;

    @Autowired
    public BotInitializer(TelegarmBot telegarmBot) {
        this.telegarmBot = telegarmBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi botsApi =new TelegramBotsApi(DefaultBotSession.class);

        botsApi.registerBot(telegarmBot);
    }
}
