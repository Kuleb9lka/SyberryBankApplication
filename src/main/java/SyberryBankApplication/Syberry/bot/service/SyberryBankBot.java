package SyberryBankApplication.Syberry.bot.service;


import SyberryBankApplication.Syberry.api.constant.Constant;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class SyberryBankBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return Constant.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constant.BOT_TOKEN;
    }

    public SyberryBankBot() {

        List<BotCommand> botCommandList = new ArrayList<>();

        botCommandList.add(new BotCommand("/start", "Start"));
        try{

            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {



    }
}
