package com.opelekh.bot;

import com.opelekh.instaUtil.InstagramUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        long chatId = message.getChatId();

        if (message.hasText()
                && message.getText().matches("https://www\\.instagram\\.com/p/(.*)")) {
            InstagramUtil instagramUtil = new InstagramUtil();
            instagramUtil.getImagesUrls(message.getText());
            List<String> imgUrlList = instagramUtil.getImagesUrls(message.getText());
            sendImageFromUrl(chatId, imgUrlList);
        }
    }

    private void sendImageFromUrl(long chatId, List<String> imgUrlList) {
        List<InputMedia> inputMediaList = new ArrayList<>();

        for (String s : imgUrlList)
            inputMediaList.add(new InputMediaPhoto().setMedia(s));

        try {
            execute(new SendMediaGroup(chatId, inputMediaList));
        } catch (TelegramApiException e) {
            e.printStackTrace();
            sendMsg(chatId, "Oops! There was a crap. Check the entered data");
        }
    }

    private void sendMsg(long chatId, String answer) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(chatId)
                .setParseMode("Markdown")
                .setText(answer);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "InstagramSave_bot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("TELEGRAM_TOKEN");
    }
}
