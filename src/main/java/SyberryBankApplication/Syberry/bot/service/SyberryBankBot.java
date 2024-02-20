package SyberryBankApplication.Syberry.bot.service;


import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.enums.SyberryBanks;
import SyberryBankApplication.Syberry.api.service.AlphaBankService;
import SyberryBankApplication.Syberry.api.service.BelarusBankService;
import SyberryBankApplication.Syberry.api.service.NationalBankService;
import SyberryBankApplication.Syberry.bot.model.UserSteps;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SyberryBankBot extends TelegramLongPollingBot {

    private final AlphaBankService alphaBankService;

    private final BelarusBankService belarusBankService;

    private final NationalBankService nationalBankService;

    private Map<Long, UserSteps> usersActionsMap = new HashMap<>();

    @Override
    public String getBotUsername() {
        return Constant.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constant.BOT_TOKEN;
    }

    public SyberryBankBot(AlphaBankService alphaBankService, BelarusBankService belarusBankService, NationalBankService nationalBankService) {
        this.alphaBankService = alphaBankService;
        this.belarusBankService = belarusBankService;
        this.nationalBankService = nationalBankService;

        List<BotCommand> botCommandList = new ArrayList<>();

        botCommandList.add(new BotCommand(Constant.START, "Start"));
        try {

            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {

            System.out.println(e.getMessage());
        }
    }



    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message.hasText()) {

            messageHandler(message);
        }


    }

    private void messageHandler(Message message) {

        if (message.hasText()) {

            if (message.getText().equals(Constant.START)) {

                initBanksKeyboard(message, SyberryBanks.getAllBanks());

                usersActionsMap.put(message.getChatId(), new UserSteps());

                return;
            }

            if (SyberryBanks.getAllBanks().contains(message.getText())){

                usersActionsMap.put(message.getChatId(), new UserSteps(message.getText()));

                initCurrenciesKeyboard(message);

            } else {

                sendSimpleMessage(message, "Указан неверный банк.");

                initBanksKeyboard(message, SyberryBanks.getAllBanks());
            }


        }
    }

    private void initBanksKeyboard(Message message, List<String> buttons){

        sendMessage(message, initKeyboard(buttons), "Выберите один из банков");
    }

    private void initCurrenciesKeyboard(Message message){

        String usersBank = usersActionsMap.get(message.getChatId()).getBankName();

        if (usersBank.equalsIgnoreCase(SyberryBanks.ALPHA_BANK.getName())){

            sendMessage(message, initKeyboard(alphaBankService.getMainCurrencies()), "Alphabank currencies");
        } else if (usersBank.equalsIgnoreCase(SyberryBanks.BELARUS_BANK.getName())){

            sendMessage(message, initKeyboard(belarusBankService.getMainCurrencies()), "BelarusBank currencies");
        } else if (usersBank.equalsIgnoreCase(SyberryBanks.NATIONAL_BANK.getName())){

            sendMessage(message, initKeyboard(nationalBankService.getMainCurrencies()), "National Bank currencies");
        } else {

            sendSimpleMessage(message, "Fatal Error");
        }
    }

    private List<KeyboardRow> initKeyboard(List<String> buttons) {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        if (buttons.size() > 3) {

            for (int i = 0; i < buttons.size(); i++) {

                if (i % 2 == 0){

                    keyboardRows.add(row);

                    row = new KeyboardRow();
                }

                row.add(new KeyboardButton(buttons.get(i)));
            }

        } else {

            for (String button : buttons) {

                row.add(new KeyboardButton(button));
            }
        }

        keyboardRows.add(row);

        return keyboardRows;
    }

    private void sendMessage(Message message, List<KeyboardRow> rows, String text){

        try{
            execute(SendMessage.builder()
                    .text(text)
                    .replyMarkup(ReplyKeyboardMarkup.builder()
                            .keyboard(rows)
                            .resizeKeyboard(true)
                            .build())
                    .chatId(message.getChatId())
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendSimpleMessage(Message message, String text){

        try{
            execute(SendMessage.builder()
                    .text(text)
                    .chatId(message.getChatId())
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
