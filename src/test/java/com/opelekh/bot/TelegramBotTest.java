package com.opelekh.bot;

import org.junit.BeforeClass;
import org.junit.Test;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TelegramBotTest  {
    private static TelegramBot bot;

    @BeforeClass
    public static void init() {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot = new TelegramBot());
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void onUpdateReceived() {
        assertEquals("InstagramSave_bot", bot.getBotUsername());
    }
}