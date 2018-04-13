package com.b.a.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    public static SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences("hianalytics_" + str + "_" + context.getPackageName(), 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, org.json.JSONObject r5, java.lang.String r6) {
        /*
        r1 = d(r4, r6);
        r0 = 0;
        r2 = 0;
        r0 = r4.openFileOutput(r1, r2);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x002d, all -> 0x0039 }
        r1 = r5.toString();	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x002d, all -> 0x004d }
        r2 = "UTF-8";
        r1 = r1.getBytes(r2);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x002d, all -> 0x004d }
        r0.write(r1);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x002d, all -> 0x004d }
        r0.flush();	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x002d, all -> 0x004d }
        if (r0 == 0) goto L_0x0020;
    L_0x001d:
        r0.close();	 Catch:{ IOException -> 0x0048 }
    L_0x0020:
        return;
    L_0x0021:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0020;
    L_0x0024:
        r0.close();	 Catch:{ IOException -> 0x0028 }
        goto L_0x0020;
    L_0x0028:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x002d:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0020;
    L_0x0030:
        r0.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x0020;
    L_0x0034:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x0039:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x003d:
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        r1.close();	 Catch:{ IOException -> 0x0043 }
    L_0x0042:
        throw r0;
    L_0x0043:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0042;
    L_0x0048:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x004d:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.a.c.a(android.content.Context, org.json.JSONObject, java.lang.String):void");
    }

    public static JSONObject b(Context context, String str) {
        FileInputStream openFileInput;
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        JSONException e;
        Exception e2;
        Throwable th;
        BufferedReader bufferedReader2;
        try {
            openFileInput = context.openFileInput(d(context, str));
            try {
                bufferedReader2 = new BufferedReader(new InputStreamReader(openFileInput, "UTF-8"));
                try {
                    StringBuffer stringBuffer = new StringBuffer("");
                    while (true) {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    if (stringBuffer.length() == 0) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        if (openFileInput == null) {
                            return null;
                        }
                        try {
                            openFileInput.close();
                            return null;
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return null;
                        }
                    }
                    JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    return jSONObject;
                } catch (FileNotFoundException e5) {
                    bufferedReader = bufferedReader2;
                    fileInputStream = openFileInput;
                } catch (IOException e6) {
                } catch (JSONException e7) {
                    e = e7;
                } catch (Exception e8) {
                    e2 = e8;
                }
            } catch (FileNotFoundException e9) {
                bufferedReader = null;
                fileInputStream = openFileInput;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    return null;
                }
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException e3222) {
                    e3222.printStackTrace();
                    return null;
                }
            } catch (IOException e10) {
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e32222) {
                        e32222.printStackTrace();
                    }
                }
                if (openFileInput != null) {
                    return null;
                }
                try {
                    openFileInput.close();
                    return null;
                } catch (IOException e322222) {
                    e322222.printStackTrace();
                    return null;
                }
            } catch (JSONException e11) {
                e = e11;
                bufferedReader2 = null;
                try {
                    e.printStackTrace();
                    c(context, str);
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3222222) {
                            e3222222.printStackTrace();
                        }
                    }
                    if (openFileInput != null) {
                        return null;
                    }
                    try {
                        openFileInput.close();
                        return null;
                    } catch (IOException e32222222) {
                        e32222222.printStackTrace();
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e322222222) {
                            e322222222.printStackTrace();
                        }
                    }
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e3222222222) {
                            e3222222222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e12) {
                e2 = e12;
                bufferedReader2 = null;
                e2.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e32222222222) {
                        e32222222222.printStackTrace();
                    }
                }
                if (openFileInput != null) {
                    return null;
                }
                try {
                    openFileInput.close();
                    return null;
                } catch (IOException e322222222222) {
                    e322222222222.printStackTrace();
                    return null;
                }
            } catch (Throwable th3) {
                bufferedReader2 = null;
                th = th3;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e13) {
            bufferedReader = null;
            fileInputStream = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                return null;
            }
            fileInputStream.close();
            return null;
        } catch (IOException e14) {
            bufferedReader2 = null;
            openFileInput = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (JSONException e15) {
            e = e15;
            bufferedReader2 = null;
            openFileInput = null;
            e.printStackTrace();
            c(context, str);
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (Exception e16) {
            e2 = e16;
            bufferedReader2 = null;
            openFileInput = null;
            e2.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (Throwable th32) {
            bufferedReader2 = null;
            openFileInput = null;
            th = th32;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (openFileInput != null) {
                openFileInput.close();
            }
            throw th;
        }
    }

    public static void c(Context context, String str) {
        context.deleteFile(d(context, str));
    }

    private static String d(Context context, String str) {
        return "hianalytics_" + str + "_" + context.getPackageName();
    }
}
