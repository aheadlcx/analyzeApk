package cn.xiaochuankeji.tieba.ui.videomaker.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.sensetime.stmobile.STMobileAuthentificationNative;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class a {
    public static boolean a(Context context, String str) {
        BufferedReader bufferedReader;
        String readLine;
        IOException e;
        String stringBuilder;
        SharedPreferences sharedPreferences;
        Integer num;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        StringBuilder stringBuilder2 = new StringBuilder();
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(str));
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine).append("\n");
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e32 = e4;
                bufferedReader = null;
                try {
                    e32.printStackTrace();
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    if (stringBuilder2.toString().length() == 0) {
                        Log.e("LicenseHelper", "read license data error");
                        return false;
                    }
                    stringBuilder = stringBuilder2.toString();
                    sharedPreferences = context.getSharedPreferences("activate_code_file", 0);
                    readLine = sharedPreferences.getString("activate_code", null);
                    num = new Integer(-1);
                    if (readLine == null) {
                    }
                    Log.e("LicenseHelper", "activeCode: " + (readLine == null));
                    readLine = STMobileAuthentificationNative.generateActiveCodeFromBuffer(context, stringBuilder, stringBuilder.length());
                    if (readLine != null) {
                    }
                    Log.e("LicenseHelper", "generate license error: " + num);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e52) {
                            e52.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            e3222 = e6;
            bufferedReader = null;
            inputStreamReader = null;
            e3222.printStackTrace();
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (stringBuilder2.toString().length() == 0) {
                stringBuilder = stringBuilder2.toString();
                sharedPreferences = context.getSharedPreferences("activate_code_file", 0);
                readLine = sharedPreferences.getString("activate_code", null);
                num = new Integer(-1);
                if (readLine == null) {
                }
                if (readLine == null) {
                }
                Log.e("LicenseHelper", "activeCode: " + (readLine == null));
                readLine = STMobileAuthentificationNative.generateActiveCodeFromBuffer(context, stringBuilder, stringBuilder.length());
                if (readLine != null) {
                }
                Log.e("LicenseHelper", "generate license error: " + num);
                return false;
            }
            Log.e("LicenseHelper", "read license data error");
            return false;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        if (stringBuilder2.toString().length() == 0) {
            Log.e("LicenseHelper", "read license data error");
            return false;
        }
        stringBuilder = stringBuilder2.toString();
        sharedPreferences = context.getSharedPreferences("activate_code_file", 0);
        readLine = sharedPreferences.getString("activate_code", null);
        num = new Integer(-1);
        if (readLine == null && STMobileAuthentificationNative.checkActiveCodeFromBuffer(context, stringBuilder, stringBuilder.length(), readLine, readLine.length()) == 0) {
            Log.e("LicenseHelper", "activeCode: " + readLine);
            return true;
        }
        if (readLine == null) {
        }
        Log.e("LicenseHelper", "activeCode: " + (readLine == null));
        readLine = STMobileAuthentificationNative.generateActiveCodeFromBuffer(context, stringBuilder, stringBuilder.length());
        if (readLine != null || readLine.length() <= 0) {
            Log.e("LicenseHelper", "generate license error: " + num);
            return false;
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("activate_code", readLine);
        edit.apply();
        return true;
    }
}
