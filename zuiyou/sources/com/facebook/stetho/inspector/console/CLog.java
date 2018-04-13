package com.facebook.stetho.inspector.console;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console.ConsoleMessage;
import com.facebook.stetho.inspector.protocol.module.Console.MessageAddedRequest;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;

public class CLog {
    private static final String TAG = "CLog";

    public static void writeToConsole(ChromePeerManager chromePeerManager, MessageLevel messageLevel, MessageSource messageSource, String str) {
        LogRedirector.d(TAG, str);
        ConsoleMessage consoleMessage = new ConsoleMessage();
        consoleMessage.source = messageSource;
        consoleMessage.level = messageLevel;
        consoleMessage.text = str;
        MessageAddedRequest messageAddedRequest = new MessageAddedRequest();
        messageAddedRequest.message = consoleMessage;
        chromePeerManager.sendNotificationToPeers("Console.messageAdded", messageAddedRequest);
    }

    public static void writeToConsole(MessageLevel messageLevel, MessageSource messageSource, String str) {
        ChromePeerManager instanceOrNull = ConsolePeerManager.getInstanceOrNull();
        if (instanceOrNull != null) {
            writeToConsole(instanceOrNull, messageLevel, messageSource, str);
        }
    }
}
