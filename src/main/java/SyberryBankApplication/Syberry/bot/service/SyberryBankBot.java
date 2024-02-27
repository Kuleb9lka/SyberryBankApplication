package SyberryBankApplication.Syberry.bot.service;


import SyberryBankApplication.Syberry.api.constant.Constant;
import SyberryBankApplication.Syberry.api.dto.alphabank.AlphaBankRateDto;
import SyberryBankApplication.Syberry.api.dto.belarusbank.BelarusBankRateDto;
import SyberryBankApplication.Syberry.api.dto.nbrb.NationalBankRateDto;
import SyberryBankApplication.Syberry.api.enums.SyberryBanks;
import SyberryBankApplication.Syberry.api.service.AlphaBankService;
import SyberryBankApplication.Syberry.api.service.BelarusBankService;
import SyberryBankApplication.Syberry.api.service.NationalBankService;
import SyberryBankApplication.Syberry.bot.model.UserSteps;
import lombok.SneakyThrows;
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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SyberryBankBot extends TelegramLongPollingBot {

    private final AlphaBankService alphaBankService;

    private final BelarusBankService belarusBankService;

    private final NationalBankService nationalBankService;

    private final Map<Long, UserSteps> usersMap = new HashMap<>();

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

    @SneakyThrows
    private void messageHandler(Message message) {

        if (message.hasText()) {

            if (message.getText().equals(Constant.START)) {

                initBanksKeyboard(message);

                usersMap.put(message.getChatId(), new UserSteps());

                return;
            }

            UserSteps currentUser = usersMap.get(message.getChatId());

            if (SyberryBanks.getAllBanks().contains(message.getText())) {

                currentUser.setBankName(message.getText());

                currentUser.setStepsCounter(1);

                initCurrenciesKeyboard(message);

                return;
            }

            if (currentUser.getStepsCounter() == 1) {

                String bankName = currentUser.getBankName();

                if (bankName.equalsIgnoreCase(SyberryBanks.ALPHA_BANK.getName())) {

                    if (alphaBankService.getMainCurrencies().contains(message.getText())) {

                        showAlphaBankRate(message);
                    }

                } else if (bankName.equalsIgnoreCase(SyberryBanks.BELARUS_BANK.getName())) {

                    if (belarusBankService.getMainCurrencies().contains(message.getText())) {

                        showBelarusBankRate(message);
                    }

                } else if (bankName.equalsIgnoreCase(SyberryBanks.NATIONAL_BANK.getName())) {

                    if (nationalBankService.getMainCurrencies().contains(message.getText())) {

                        currentUser.setCurrencyName(message.getText());

                        initDateKeyboard(message);

                        currentUser.setStepsCounter(2);
                    }
                }
            }

            if (message.getText().equalsIgnoreCase(Constant.ON_TODAY) && currentUser.getBankName().equals(SyberryBanks.NATIONAL_BANK.getName())) {

                NationalBankRateDto rateByCurrName = nationalBankService.getRateByCurrName(currentUser.getCurrencyName());

                sendSimpleMessage(message, rateByCurrName.toString());
            }

            if (message.getText().equals(Constant.ON_OTHER_DAY)) {

                sendSimpleMessage(message, "Введите дату в формате ддММгггг");
            }

            Matcher matcher = Pattern.compile("[\\d{8}]").matcher(message.getText());

            if (matcher.find()) {

                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");

                Date date = format.parse(message.getText());

                if (currentUser.getBankName().equals(SyberryBanks.NATIONAL_BANK.getName())) {

                    NationalBankRateDto rateByCurrNameOnDate = nationalBankService.getRateByCurrNameOnDate(currentUser.getCurrencyName(), date);

                    sendSimpleMessage(message, rateByCurrNameOnDate.toString());
                }
            }

            if (message.getText().equals(Constant.BACK)) {

                if (currentUser.getStepsCounter() == 0){

                    initBanksKeyboard(message);
                } else if (currentUser.getStepsCounter() == 1){

                    initBanksKeyboard(message);

                    currentUser.setStepsCounter(0);
                } else if(currentUser.getStepsCounter() == 2){

                    initCurrenciesKeyboard(message);

                    currentUser.setStepsCounter(1);
                }
            }
        }
    }

    private void showBelarusBankRate(Message message) {

        try {

            BelarusBankRateDto rateByCurrName = belarusBankService.getRateByCurrName(message.getText());

            sendSimpleMessage(message, rateByCurrName.toString());
        } catch (Exception e) {

            sendSimpleMessage(message, e.getMessage());
        }
    }

    private void showAlphaBankRate(Message message) {

        try {
            AlphaBankRateDto rateByCurrName = alphaBankService.getRateByCurrName(message.getText());

            sendSimpleMessage(message, rateByCurrName.toString());
        } catch (Exception e) {
            sendSimpleMessage(message, e.getMessage());
        }
    }

    private void initDateKeyboard(Message message) {

        List<String> datesButtons = new ArrayList<>();

        datesButtons.add(Constant.ON_TODAY);
        datesButtons.add(Constant.ON_OTHER_DAY);

        sendMessage(message, initKeyboard(datesButtons), "Выберите дату");
    }

    private void initBanksKeyboard(Message message) {

        sendMessage(message, initKeyboard(SyberryBanks.getAllBanks()), "Выберите один из банков");
    }

    private void initCurrenciesKeyboard(Message message) {

        String usersBank = usersMap.get(message.getChatId()).getBankName();

        if (usersBank.equalsIgnoreCase(SyberryBanks.ALPHA_BANK.getName())) {

            sendMessage(message, initKeyboard(alphaBankService.getMainCurrencies()), "Валюты Альфа-Банка");
        } else if (usersBank.equalsIgnoreCase(SyberryBanks.BELARUS_BANK.getName())) {

            sendMessage(message, initKeyboard(belarusBankService.getMainCurrencies()), "Валюты Беларусбанка");
        } else if (usersBank.equalsIgnoreCase(SyberryBanks.NATIONAL_BANK.getName())) {

            sendMessage(message, initKeyboard(nationalBankService.getMainCurrencies()), "Валюты Нацонального Банка РБ");
        } else {

            sendSimpleMessage(message, "Введене неверная информация");
        }
    }

    private List<KeyboardRow> initKeyboard(List<String> buttons) {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        if (buttons.size() > 3) {

            for (int i = 0; i < buttons.size(); i++) {

                if (i % 2 == 0) {

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

        row.add(new KeyboardButton(Constant.BACK));

        keyboardRows.add(row);

        return keyboardRows;
    }

    private void sendMessage(Message message, List<KeyboardRow> rows, String text) {

        try {
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

    private void sendSimpleMessage(Message message, String text) {

        try {
            execute(SendMessage.builder()
                    .text(text)
                    .chatId(message.getChatId())
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
