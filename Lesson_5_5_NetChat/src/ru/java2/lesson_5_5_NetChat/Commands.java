package ru.java2.lesson_5_5_NetChat;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Abilis on 04.05.2016.
 */
public class Commands {

    //команда - описание
    private static final Map<String, String> commands = new TreeMap<String, String>();

    static {
        commands.put("!login", " <new_nick> - сменить ник");
        commands.put("!help", " - вывести все доступные команды");
        commands.put("!users", " - показать всех подключенных пользователей");
        commands.put("!exit", " - выйти из чата");
        commands.put("!private", " <nick> - отправить приватное сообщение пользователю <nick>");
        commands.put("!file", " <nick> <file> - отправить файл <file> пользователю <nick>");

    }

    private Commands() {

    }

    public static Map<String, String> getCommands() {
        return commands;
    }

}
