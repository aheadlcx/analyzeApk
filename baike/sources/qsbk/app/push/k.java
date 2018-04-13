package qsbk.app.push;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import qsbk.app.utils.LogUtil;

final class k implements LoggerInterface {
    k() {
    }

    public void setTag(String str) {
    }

    public void log(String str, Throwable th) {
        LogUtil.d("xmpush:" + str);
    }

    public void log(String str) {
        LogUtil.d("xmpush:" + str);
    }
}
