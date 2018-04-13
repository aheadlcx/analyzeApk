package qsbk.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;

public class FilePreference {
    private final File a;
    private JSONObject b;

    public enum CommitType {
        IMMEDIATE,
        MANUAL
    }

    public FilePreference(String str) {
        if (a(str)) {
            String sDPath = DeviceUtils.getSDPath();
            if (sDPath == null || sDPath.length() == 0) {
                sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
            }
            File file = new File(sDPath + File.separator + "qsbk/data");
            if (!file.exists()) {
                file.mkdirs();
            }
            this.a = new File(file, str);
            return;
        }
        throw new IllegalArgumentException("Name of preference can't be empty or contain char in '<>/\\|:\"*?'");
    }

    public static FilePreference get(String str) {
        return new FilePreference(str);
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < "<>/\\|:\"*?".length(); i++) {
            if (str.indexOf("<>/\\|:\"*?".charAt(i)) != -1) {
                return false;
            }
        }
        return true;
    }

    private String a() throws Exception {
        BufferedReader bufferedReader;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a != null && this.a.exists()) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.a)));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return stringBuilder.toString();
    }

    private void b(String str) throws IOException {
        Throwable th;
        if (!this.a.exists()) {
            this.a.createNewFile();
        }
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.a)));
            try {
                bufferedWriter.write(str);
                bufferedWriter.flush();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    private void b() {
        try {
            this.b = new JSONObject(a());
        } catch (Exception e) {
            this.b = new JSONObject();
        }
    }

    private void c() {
        try {
            b(this.b.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void d() {
        if (this.b == null) {
            b();
        }
    }

    public String getString(String str, String str2) {
        d();
        return this.b.optString(str, str2);
    }

    public int getInt(String str, int i) {
        d();
        return this.b.optInt(str, i);
    }

    public long getLong(String str, long j) {
        d();
        return this.b.optLong(str, j);
    }

    public boolean getBoolean(String str, boolean z) {
        d();
        return this.b.optBoolean(str, z);
    }

    public void set(String str, Object obj, CommitType commitType) {
        d();
        try {
            this.b.put(str, obj);
            if (commitType == CommitType.IMMEDIATE) {
                c();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
