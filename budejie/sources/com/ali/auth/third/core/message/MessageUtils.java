package com.ali.auth.third.core.message;

import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.ResourceUtils;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageUtils {
    private static Map<Integer, Message> cachedMessageMetas = new HashMap();
    private static final Object defaultMessageLoadLock = new Object();
    private static final Message defaultMessageNotFoundMessage = new Message();
    private static final Message defaultUnknownErrorMessage = new Message();
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Message messageNotFoundMessage;
    private static Message unknownErrorMessage;

    static {
        defaultMessageNotFoundMessage.code = 1;
        defaultMessageNotFoundMessage.message = "未在消息文件中找到 id 为 {0} 的消息";
        defaultMessageNotFoundMessage.action = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        defaultMessageNotFoundMessage.type = "E";
        defaultUnknownErrorMessage.code = 2;
        defaultUnknownErrorMessage.message = "检索消息时发生如下错误 {0}";
        defaultUnknownErrorMessage.action = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        defaultUnknownErrorMessage.type = "E";
    }

    public static Message createMessage(int i, Object... objArr) {
        try {
            Message loadMessage;
            lock.readLock().lock();
            Message message = (Message) cachedMessageMetas.get(Integer.valueOf(i));
            if (message == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                loadMessage = loadMessage(i);
                if (loadMessage != null) {
                    cachedMessageMetas.put(Integer.valueOf(i), loadMessage);
                }
                lock.readLock().lock();
                lock.writeLock().unlock();
            } else {
                loadMessage = message;
            }
            if (loadMessage == null) {
                message = createMessageNotFoundMessage(i);
                lock.readLock().unlock();
                return message;
            } else if (objArr.length == 0) {
                lock.readLock().unlock();
                return loadMessage;
            } else {
                message = (Message) loadMessage.clone();
                message.message = MessageFormat.format(loadMessage.message, objArr);
                lock.readLock().unlock();
                return message;
            }
        } catch (Exception e) {
            return createUnknownErrorMessage(e.getMessage());
        } catch (Throwable th) {
            lock.writeLock().unlock();
        }
    }

    public static String getMessageContent(int i, Object... objArr) {
        try {
            lock.readLock().lock();
            Message message = (Message) cachedMessageMetas.get(Integer.valueOf(i));
            if (message == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                message = loadMessage(i);
                if (message != null) {
                    cachedMessageMetas.put(Integer.valueOf(i), message);
                }
                lock.readLock().lock();
                lock.writeLock().unlock();
            }
            String str;
            if (message == null) {
                str = createMessageNotFoundMessage(i).message;
                lock.readLock().unlock();
                return str;
            }
            str = MessageFormat.format(message.message, objArr);
            lock.readLock().unlock();
            return str;
        } catch (Exception e) {
            return createUnknownErrorMessage(e.getMessage()).message;
        } catch (Throwable th) {
            lock.writeLock().unlock();
        }
    }

    private static Message createUnknownErrorMessage(String str) {
        if (unknownErrorMessage == null) {
            synchronized (defaultMessageLoadLock) {
                if (unknownErrorMessage == null) {
                    unknownErrorMessage = loadMessage(2);
                    if (unknownErrorMessage == null) {
                        unknownErrorMessage = defaultUnknownErrorMessage;
                    }
                }
            }
        }
        try {
            Message message = (Message) unknownErrorMessage.clone();
            message.message = MessageFormat.format(message.message, new Object[]{str});
            return message;
        } catch (CloneNotSupportedException e) {
            return unknownErrorMessage;
        }
    }

    private static Message createMessageNotFoundMessage(int i) {
        if (messageNotFoundMessage == null) {
            synchronized (defaultMessageLoadLock) {
                if (messageNotFoundMessage == null) {
                    messageNotFoundMessage = loadMessage(1);
                    if (messageNotFoundMessage == null) {
                        messageNotFoundMessage = defaultMessageNotFoundMessage;
                    }
                }
            }
        }
        try {
            Message message = (Message) messageNotFoundMessage.clone();
            message.message = MessageFormat.format(message.message, new Object[]{String.valueOf(i)});
            return message;
        } catch (CloneNotSupportedException e) {
            return messageNotFoundMessage;
        }
    }

    private static Message loadMessage(int i) {
        try {
            int identifier = ResourceUtils.getIdentifier(KernelContext.getApplicationContext(), "string", "auth_sdk_message_" + i + "_message");
            if (identifier == 0) {
                return null;
            }
            Message message = new Message();
            message.code = i;
            message.message = KernelContext.getApplicationContext().getResources().getString(identifier);
            identifier = ResourceUtils.getIdentifier(KernelContext.getApplicationContext(), "string", "auth_sdk_message_" + i + "_action");
            if (identifier != 0) {
                message.action = KernelContext.getApplicationContext().getResources().getString(identifier);
            } else {
                message.action = "";
            }
            identifier = ResourceUtils.getIdentifier(KernelContext.getApplicationContext(), "string", "auth_sdk_message_" + i + "_type");
            if (identifier != 0) {
                message.type = KernelContext.getApplicationContext().getResources().getString(identifier);
            } else {
                message.type = "I";
            }
            return message;
        } catch (Exception e) {
            SDKLogger.e("kernel", "Fail to get message of the code " + i + ", the error message is " + e.getMessage());
            return null;
        }
    }
}
