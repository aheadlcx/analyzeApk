package qsbk.app.im.emotion;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.image.FrescoImageloader;

public class EmotionShowerHelper {
    private static final String a = EmotionShowerHelper.class.getSimpleName();

    private EmotionShowerHelper() {
    }

    public static void show(ChatMsg chatMsg, ImageView imageView) {
        if (chatMsg.type == 5) {
            if (TextUtils.isEmpty(chatMsg.data)) {
                Log.e(a, "ChatMsgEmotionData is null.");
                return;
            }
            ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData(chatMsg.data);
            if (chatMsgEmotionData.hasLocal()) {
                imageView.setImageResource(chatMsgEmotionData.localResource);
                return;
            }
            ChatMsgEmotionData localChatMsgEmotionData = EmotionManager.getInstance().getLocalChatMsgEmotionData(chatMsgEmotionData.namespace, chatMsgEmotionData.key);
            if (localChatMsgEmotionData == null || !localChatMsgEmotionData.hasLocal()) {
                FrescoImageloader.displayImage(imageView, chatMsgEmotionData.url);
                return;
            }
            ChatMsgEmotionData.copyValue(localChatMsgEmotionData, chatMsgEmotionData);
            imageView.setImageResource(chatMsgEmotionData.localResource);
        }
    }
}
