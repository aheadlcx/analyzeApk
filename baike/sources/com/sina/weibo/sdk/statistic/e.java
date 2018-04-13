package com.sina.weibo.sdk.statistic;

import android.os.Environment;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class e {
    public static final String ANALYTICS_FILE_NAME = "app_logs";

    public static String getAppLogs(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(str);
    }

    public static String getAppLogPath(String str) {
        String str2 = "";
        str2 = "";
        if (f.getPackageName() != null) {
            str2 = MD5.hexdigest(f.getPackageName()) + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        return a() + "/sina/weibo/.applogs/" + str2 + str + ".txt";
    }

    private static String a() {
        File externalStorageDirectory;
        if (Environment.getExternalStorageState().equals("mounted")) {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        } else {
            externalStorageDirectory = null;
        }
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.toString();
        }
        return null;
    }

    private static String a(String str) {
        IOException e;
        OutOfMemoryError e2;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        File file = new File(str);
        if (!file.isFile() || !file.exists()) {
            return "";
        }
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder((int) file.length());
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                } catch (IOException e3) {
                    e = e3;
                } catch (OutOfMemoryError e4) {
                    e2 = e4;
                    bufferedReader = bufferedReader2;
                }
            }
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                }
            }
        } catch (IOException e6) {
            e = e6;
            bufferedReader2 = null;
            try {
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e7) {
                    }
                }
                return stringBuilder.toString();
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        } catch (OutOfMemoryError e9) {
            e2 = e9;
            try {
                e2.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e10) {
                    }
                }
                return stringBuilder.toString();
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return stringBuilder.toString();
    }

    public static synchronized void writeToFile(String str, String str2, boolean z) {
        FileWriter fileWriter;
        Throwable th;
        synchronized (e.class) {
            if (!TextUtils.isEmpty(str)) {
                LogUtil.i(WBAgent.TAG, "filePath:" + str);
                if (!(str2 == null || str2.length() == 0)) {
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    if (stringBuilder.charAt(0) == '[') {
                        stringBuilder.replace(0, 1, "");
                    }
                    if (stringBuilder.charAt(stringBuilder.length() - 1) != ',') {
                        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), Constants.ACCEPT_TIME_SEPARATOR_SP);
                    }
                    File file = new File(str);
                    FileWriter fileWriter2 = null;
                    try {
                        File parentFile = file.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        if (!file.exists()) {
                            file.createNewFile();
                        } else if (file.lastModified() > 0 && System.currentTimeMillis() - file.lastModified() > 86400000) {
                            z = false;
                        }
                        fileWriter = new FileWriter(file, z);
                        try {
                            fileWriter.write(stringBuilder.toString());
                            fileWriter.flush();
                            if (fileWriter != null) {
                                try {
                                    fileWriter.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e2) {
                            fileWriter2 = fileWriter;
                            if (fileWriter2 != null) {
                                try {
                                    fileWriter2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileWriter != null) {
                                try {
                                    fileWriter.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        if (fileWriter2 != null) {
                            fileWriter2.close();
                        }
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        fileWriter = null;
                        th = th4;
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public static boolean delete(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        file.delete();
        return true;
    }
}
