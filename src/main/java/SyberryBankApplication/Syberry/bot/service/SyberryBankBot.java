package SyberryBankApplication.Syberry.bot.service;


import SyberryBankApplication.Syberry.api.constant.Constant;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class SyberryBankBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return Constant.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constant.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
