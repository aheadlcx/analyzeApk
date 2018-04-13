package qsbk.app.utils;

import android.os.Environment;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import qsbk.app.QsbkApp;

public class MiUiUtils {

    public static class BuildProperties {
        private final Properties a = new Properties();

        private BuildProperties() throws IOException {
            this.a.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        }

        public static BuildProperties newInstance() throws IOException {
            return new BuildProperties();
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        public boolean containsValue(Object obj) {
            return this.a.containsValue(obj);
        }

        public Set<Entry<Object, Object>> entrySet() {
            return this.a.entrySet();
        }

        public String getProperty(String str) {
            return this.a.getProperty(str);
        }

        public String getProperty(String str, String str2) {
            return this.a.getProperty(str, str2);
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public Enumeration<Object> keys() {
            return this.a.keys();
        }

        public Set<Object> keySet() {
            return this.a.keySet();
        }

        public int size() {
            return this.a.size();
        }

        public Collection<Object> values() {
            return this.a.values();
        }
    }

    public static boolean isMIUI() {
        try {
            BuildProperties newInstance = BuildProperties.newInstance();
            if (newInstance.getProperty("ro.miui.ui.version.code", null) == null && newInstance.getProperty("ro.miui.ui.version.name", null) == null && newInstance.getProperty("ro.miui.internal.storage", null) == null) {
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isMiUiSystemPushEnable() {
        return MiPushClient.shouldUseMIUIPush(QsbkApp.mContext);
    }
}
