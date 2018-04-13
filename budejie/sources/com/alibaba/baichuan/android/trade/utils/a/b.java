package com.alibaba.baichuan.android.trade.utils.a;

import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.constants.MessageConstants;
import com.alibaba.baichuan.android.trade.utils.i;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class b {
    private static Map a = new HashMap();
    private static ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private static final a c = new a();
    private static final a d = new a();
    private static volatile a e;
    private static volatile a f;
    private static final Object g = new Object();

    static {
        c.a = 1;
        c.c = "未在消息文件中找到 id 为 {0} 的消息";
        c.d = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        c.b = "E";
        d.a = 2;
        d.c = "检索消息时发生如下错误 {0}";
        d.d = "请检查所依赖的 SDK 项目，或若是手动拷贝 SDK 至当前开发应用所在项目，请检查是否漏拷文件 res/values 下文件";
        d.b = "E";
    }

    private static a a(int i) {
        if (e == null) {
            synchronized (g) {
                if (e == null) {
                    e = b(1);
                    if (e == null) {
                        e = c;
                    }
                }
            }
        }
        try {
            a aVar = (a) e.clone();
            aVar.c = MessageFormat.format(aVar.c, new Object[]{String.valueOf(i)});
            return aVar;
        } catch (CloneNotSupportedException e) {
            return e;
        }
    }

    public static a a(int i, Object... objArr) {
        try {
            b.readLock().lock();
            a aVar = (a) a.get(Integer.valueOf(i));
            if (aVar == null) {
                b.readLock().unlock();
                b.writeLock().lock();
                aVar = b(i);
                if (aVar != null) {
                    a.put(Integer.valueOf(i), aVar);
                }
                b.readLock().lock();
                b.writeLock().unlock();
            }
            if (aVar == null) {
                aVar = a(i);
                b.readLock().unlock();
                return aVar;
            } else if (objArr.length == 0) {
                b.readLock().unlock();
                return aVar;
            } else {
                aVar = (a) aVar.clone();
                aVar.c = MessageFormat.format(aVar.c, objArr);
                b.readLock().unlock();
                return aVar;
            }
        } catch (Exception e) {
            AlibcLogger.e("AlibcMessageUtils", e.toString());
            return b(e.getMessage());
        } catch (Throwable th) {
            b.writeLock().unlock();
        }
    }

    public static a a(String str) {
        int i = MessageConstants.PAY_COMMON_ERROR;
        try {
            switch (Integer.parseInt(str)) {
                case 4000:
                    i = 805;
                    break;
                case ErrorCode.SERVER_METHODNOTFOUND /*6001*/:
                    i = 806;
                    break;
                case ErrorCode.SERVER_PARAMMISSING /*6002*/:
                    i = MessageConstants.PAY_NETWORK_FAILED;
                    break;
                case 8000:
                    i = 804;
                    break;
                case CommonInts.GT_ERROR /*10002*/:
                    i = MessageConstants.PAY_SDK_FAILED;
                    break;
            }
        } catch (Exception e) {
            AlibcLogger.e("AlibcMessageUtils", "fail to parse the response code " + str);
        }
        return a.a(i, str);
    }

    private static a b(int i) {
        try {
            int a = i.a(AlibcContext.context, "string", "alisdk_message_" + i + "_message");
            if (a == 0) {
                return null;
            }
            a aVar = new a();
            aVar.a = i;
            aVar.c = AlibcContext.context.getResources().getString(a);
            a = i.a(AlibcContext.context, "string", "alisdk_message_" + i + "_action");
            if (a != 0) {
                aVar.d = AlibcContext.context.getResources().getString(a);
            } else {
                aVar.d = "";
            }
            a = i.a(AlibcContext.context, "string", "alisdk_message_" + i + "_type");
            if (a != 0) {
                aVar.b = AlibcContext.context.getResources().getString(a);
            } else {
                aVar.b = "I";
            }
            return aVar;
        } catch (Exception e) {
            AlibcLogger.e("kernel", "Fail to get message of the code " + i + ", the error message is " + e.getMessage());
            return null;
        }
    }

    private static a b(String str) {
        if (f == null) {
            synchronized (g) {
                if (f == null) {
                    f = b(2);
                    if (f == null) {
                        f = d;
                    }
                }
            }
        }
        try {
            a aVar = (a) f.clone();
            aVar.c = MessageFormat.format(aVar.c, new Object[]{str});
            return aVar;
        } catch (CloneNotSupportedException e) {
            return f;
        }
    }
}
