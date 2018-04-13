package com.tencent.wcdb.repair;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.support.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBDumpUtil {
    private static final String TAG = "WCDB.DBDumpUtil";

    public interface ExecuteSqlCallback {
        String preExecute(String str);
    }

    private static native boolean nativeDumpDB(String str, String str2, String str3);

    private static native boolean nativeIsSqlComplete(String str);

    public static boolean doRecoveryDb(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        return doRecoveryDb(sQLiteDatabase, str, str2, str3, null, null, null, true);
    }

    public static boolean doRecoveryDb(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, List<String> list, List<String> list2, ExecuteSqlCallback executeSqlCallback, boolean z) {
        int i;
        int i2;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            Log.w(TAG, "Database is not open");
            return false;
        } else if (!nativeDumpDB(str, str2, str3)) {
            return false;
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(str3));
                sQLiteDatabase.execSQL("PRAGMA foreign_keys=OFF;");
                sQLiteDatabase.beginTransaction();
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                Object obj = null;
                try {
                    HashMap hashMap = new HashMap();
                    Object obj2 = null;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String str4;
                        String tableNameFromSql;
                        String str5;
                        if (obj2 != null) {
                            obj = obj + "\n" + readLine;
                            if (obj.endsWith(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                                if (!nativeIsSqlComplete(obj)) {
                                }
                            }
                        } else if (readLine.startsWith("INSERT") || readLine.startsWith("CREATE TABLE")) {
                            if (readLine.endsWith(VoiceWakeuperAidl.PARAMS_SEPARATE) && nativeIsSqlComplete(readLine)) {
                                str4 = readLine;
                            } else {
                                if (!TextUtils.isEmpty(obj)) {
                                    readLine = obj + "\n" + readLine;
                                }
                                obj2 = 1;
                                obj = readLine;
                            }
                        }
                        Object obj3 = null;
                        if (list2 != null && list2.size() > 0) {
                            tableNameFromSql = getTableNameFromSql(str4);
                            for (String readLine2 : list2) {
                                if (tableNameFromSql.equals(readLine2)) {
                                    obj3 = null;
                                    break;
                                }
                            }
                            i = 1;
                            str5 = str4;
                        } else if (list == null || list.size() <= 0) {
                            tableNameFromSql = null;
                            str5 = str4;
                        } else {
                            tableNameFromSql = getTableNameFromSql(str4);
                            for (String readLine22 : list) {
                                if (tableNameFromSql.equals(readLine22)) {
                                    obj3 = 1;
                                    str5 = "";
                                    break;
                                }
                            }
                            obj3 = null;
                            str5 = str4;
                        }
                        if (obj3 != null) {
                            Log.i(TAG, "filter table %s", tableNameFromSql);
                            obj = str5;
                            obj2 = null;
                        } else {
                            try {
                                if (str5.startsWith("CREATE TABLE")) {
                                    hashMap.put(tableNameFromSql, buildColumnsString(getColumnNamesFromSql(str5)));
                                } else if (str5.startsWith("INSERT INTO")) {
                                    readLine22 = (String) hashMap.get(tableNameFromSql);
                                    if (!TextUtils.isEmpty(readLine22)) {
                                        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
                                        stringBuilder.append("\"").append(tableNameFromSql).append("\"");
                                        CharSequence stringBuilder2 = stringBuilder.toString();
                                        stringBuilder.append(readLine22);
                                        str5 = str5.replace(stringBuilder2, stringBuilder.toString());
                                    }
                                }
                                readLine22 = null;
                                if (executeSqlCallback != null) {
                                    readLine22 = executeSqlCallback.preExecute(str5);
                                }
                                if (TextUtils.isEmpty(readLine22)) {
                                    readLine22 = str5;
                                }
                                i2 = i4 + 1;
                                try {
                                    sQLiteDatabase.execSQL(readLine22);
                                    i = i5 + 1;
                                    if (i >= 100) {
                                        try {
                                            sQLiteDatabase.setTransactionSuccessful();
                                            sQLiteDatabase.endTransaction();
                                            sQLiteDatabase.beginTransaction();
                                            i = 0;
                                        } catch (Exception e) {
                                            i3++;
                                            obj = null;
                                            i5 = i;
                                            i4 = i2;
                                            obj2 = null;
                                        }
                                    }
                                } catch (Exception e2) {
                                    i = i5;
                                    i3++;
                                    obj = null;
                                    i5 = i;
                                    i4 = i2;
                                    obj2 = null;
                                }
                            } catch (Exception e3) {
                                i = i5;
                                i2 = i4;
                                i3++;
                                obj = null;
                                i5 = i;
                                i4 = i2;
                                obj2 = null;
                            }
                            obj = null;
                            i5 = i;
                            i4 = i2;
                            obj2 = null;
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (i4 <= i3) {
                        return false;
                    }
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.setTransactionSuccessful();
                    }
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                    if (z) {
                        File file = new File(str3);
                        if (file.exists()) {
                            file.delete();
                        }
                        file = new File(str);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    Log.i(TAG, "restore : %d , fail:%d ", Integer.valueOf(i4), Integer.valueOf(i3));
                    return true;
                } catch (IOException e5) {
                    Log.w(TAG, "I/O error in read sql file ");
                    if (bufferedReader == null) {
                        return false;
                    }
                    try {
                        bufferedReader.close();
                        return false;
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        return false;
                    }
                } catch (Throwable th) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    }
                }
            } catch (FileNotFoundException e7) {
                Log.w(TAG, "SQL file '%s' not found", str3);
                return false;
            }
        }
    }

    public static String buildColumnsString(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append((String) arrayList.get(i));
            if (i != arrayList.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        String stringBuilder2 = stringBuilder.toString();
        System.out.println(stringBuilder2);
        return stringBuilder2;
    }

    public static String getTableNameFromSql(String str) {
        if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        String[] split = str.split("\\s");
        if (split == null || split.length <= 1) {
            return null;
        }
        return split[2].replaceAll("\"", "");
    }

    public static byte[] readFromFile(String str) {
        Exception e;
        Throwable th;
        File file = new File(str);
        if (file.exists()) {
            FileInputStream fileInputStream;
            try {
                int length = (int) file.length();
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[length];
                    if (fileInputStream.read(bArr) != length) {
                        Log.w(TAG, "readFromFile error, size is not equal, path = %s, file length is %d, count is %d", str, Integer.valueOf(length), Integer.valueOf(fileInputStream.read(bArr)));
                        if (fileInputStream == null) {
                            return null;
                        }
                        try {
                            fileInputStream.close();
                            return null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        Log.e(TAG, "readFromFile failed!");
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                Log.e(TAG, "readFromFile failed!");
                return null;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        Log.w(TAG, "readFromFile error, file is not exit, path = %s", str);
        return null;
    }

    public static ArrayList<String> getColumnNamesFromSql(String str) {
        ArrayList<String> arrayList = new ArrayList();
        String[] split = str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")).trim().split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
            arrayList.add(split[i].substring(0, split[i].indexOf(" ")));
        }
        return arrayList;
    }
}
