package SyberryBankApplication.Syberry.bot.config;


import SyberryBankApplication.Syberry.bot.service.SyberryBankBot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final SyberryBankBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {

        TelegramBotsApi syberryBankBot = new TelegramBotsApi(DefaultBotSession.class);

        try {

            syberryBankBot.registerBot(bot);
        } catch (TelegramApiException e) {

            System.out.println(e.getMessage());
        }
    }
}
