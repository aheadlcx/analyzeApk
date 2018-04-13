package com.meizu.cloud.pushsdk;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.MessageV2Handler;
import com.meizu.cloud.pushsdk.handler.impl.MessageV3Handler;
import com.meizu.cloud.pushsdk.handler.impl.RegisterMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.ThroughMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.UnRegisterMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.fileupload.LogUploadMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.notification.NotificationClickMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.notification.NotificationDeleteMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.notification.NotificationStateMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.PushSwitchStatusHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.ReceiveNotifyMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.RegisterStatusHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.SubScribeAliasStatusHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.SubScribeTagsStatusHandler;
import com.meizu.cloud.pushsdk.handler.impl.platform.UnRegisterStatusHandler;
import com.meizu.cloud.pushsdk.handler.impl.schedule.ScheduleNotificationHandler;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PushMessageProxy {
    private static final String TAG = "PushMessageProxy";
    static volatile PushMessageProxy singleton = null;
    private Context context;
    private Map<Integer, MessageHandler> managerHashMap;
    private Map<String, AbstractAppLogicListener> messageListenerMap;

    public class DefaultPushMessageListener extends AbstractAppLogicListener {
        public void onMessage(Context context, Intent intent) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onMessage(context, intent);
                }
            }
        }

        public void onRegister(Context context, String str) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onRegister(context, str);
                }
            }
        }

        public void onMessage(Context context, String str) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onMessage(context, str);
                }
            }
        }

        public void onMessage(Context context, String str, String str2) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onMessage(context, str, str2);
                }
            }
        }

        public void onUnRegister(Context context, boolean z) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onUnRegister(context, z);
                }
            }
        }

        public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onUpdateNotificationBuilder(pushNotificationBuilder);
                }
            }
        }

        public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onPushStatus(context, pushSwitchStatus);
                }
            }
        }

        public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onRegisterStatus(context, registerStatus);
                }
            }
        }

        public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onUnRegisterStatus(context, unRegisterStatus);
                }
            }
        }

        public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onSubTagsStatus(context, subTagsStatus);
                }
            }
        }

        public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onSubAliasStatus(context, subAliasStatus);
                }
            }
        }

        public void onNotificationClicked(Context context, String str, String str2, String str3) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onNotificationClicked(context, str, str2, str3);
                }
            }
        }

        public void onNotificationArrived(Context context, String str, String str2, String str3) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onNotificationArrived(context, str, str2, str3);
                }
            }
        }

        public void onNotificationDeleted(Context context, String str, String str2, String str3) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onNotificationDeleted(context, str, str2, str3);
                }
            }
        }

        public void onNotifyMessageArrived(Context context, String str) {
            for (Entry value : PushMessageProxy.this.messageListenerMap.entrySet()) {
                AbstractAppLogicListener abstractAppLogicListener = (AbstractAppLogicListener) value.getValue();
                if (abstractAppLogicListener != null) {
                    abstractAppLogicListener.onNotifyMessageArrived(context, str);
                }
            }
        }
    }

    public PushMessageProxy(Context context) {
        this(context, null);
    }

    public PushMessageProxy(Context context, List<MessageHandler> list) {
        this(context, list, null);
    }

    public PushMessageProxy(Context context, List<MessageHandler> list, AbstractAppLogicListener abstractAppLogicListener) {
        this.managerHashMap = new HashMap();
        this.messageListenerMap = null;
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.context = context.getApplicationContext();
        this.messageListenerMap = new HashMap();
        AbstractAppLogicListener defaultPushMessageListener = new DefaultPushMessageListener();
        if (list == null) {
            addHandler(new MessageV3Handler(context, defaultPushMessageListener));
            addHandler(new MessageV2Handler(context, defaultPushMessageListener));
            addHandler(new ThroughMessageHandler(context, defaultPushMessageListener));
            addHandler(new NotificationClickMessageHandler(context, defaultPushMessageListener));
            addHandler(new RegisterMessageHandler(context, defaultPushMessageListener));
            addHandler(new UnRegisterMessageHandler(context, defaultPushMessageListener));
            addHandler(new NotificationDeleteMessageHandler(context, defaultPushMessageListener));
            addHandler(new PushSwitchStatusHandler(context, defaultPushMessageListener));
            addHandler(new RegisterStatusHandler(context, defaultPushMessageListener));
            addHandler(new UnRegisterStatusHandler(context, defaultPushMessageListener));
            addHandler(new SubScribeAliasStatusHandler(context, defaultPushMessageListener));
            addHandler(new SubScribeTagsStatusHandler(context, defaultPushMessageListener));
            addHandler(new ScheduleNotificationHandler(context, defaultPushMessageListener));
            addHandler(new ReceiveNotifyMessageHandler(context, defaultPushMessageListener));
            addHandler(new NotificationStateMessageHandler(context, defaultPushMessageListener));
            addHandler(new LogUploadMessageHandler(context, defaultPushMessageListener));
            return;
        }
        addHandler((List) list);
    }

    public static PushMessageProxy with(Context context) {
        if (singleton == null) {
            synchronized (PushMessageProxy.class) {
                if (singleton == null) {
                    a.a(TAG, "PushMessageProxy init");
                    singleton = new PushMessageProxy(context);
                }
            }
        }
        return singleton;
    }

    public PushMessageProxy receiverListener(String str, AbstractAppLogicListener abstractAppLogicListener) {
        this.messageListenerMap.put(str, abstractAppLogicListener);
        return this;
    }

    public PushMessageProxy unReceiverListener(String str) {
        this.messageListenerMap.put(str, null);
        return this;
    }

    public PushMessageProxy addHandler(MessageHandler messageHandler) {
        this.managerHashMap.put(Integer.valueOf(messageHandler.getProcessorType()), messageHandler);
        return this;
    }

    public PushMessageProxy addHandler(List<MessageHandler> list) {
        if (list == null) {
            throw new IllegalArgumentException("messageManagerList must not be null.");
        }
        for (MessageHandler addHandler : list) {
            addHandler(addHandler);
        }
        return this;
    }

    public void processMessage(Intent intent) {
        a.d(TAG, "is onMainThread " + isOnMainThread());
        try {
            a.a(TAG, "receive action " + intent.getAction() + " method " + intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD));
            if (intent != null) {
                for (Entry value : this.managerHashMap.entrySet()) {
                    if (((MessageHandler) value.getValue()).sendMessage(intent)) {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            a.d(TAG, "processMessage error " + e.getMessage());
        }
    }

    protected boolean isOnMainThread() {
        return Thread.currentThread() == this.context.getMainLooper().getThread();
    }
}
