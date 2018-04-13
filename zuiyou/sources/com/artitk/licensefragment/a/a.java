package com.artitk.licensefragment.a;

import android.content.Context;
import com.artitk.licensefragment.model.LicenseType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class a {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    public String a(LicenseType licenseType) {
        return a(b(licenseType));
    }

    private String a(int i) {
        Throwable th;
        String str;
        BufferedReader bufferedReader = null;
        InputStream openRawResource = this.a.getResources().openRawResource(i);
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(openRawResource);
            try {
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                        stringBuilder.append('\n');
                    } catch (IOException e) {
                        bufferedReader = bufferedReader2;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        bufferedReader = bufferedReader2;
                        th = th3;
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return stringBuilder.toString();
            } catch (IOException e4) {
                try {
                    str = "";
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        return str;
                    }
                    try {
                        bufferedReader.close();
                        return str;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return str;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e52) {
                            e52.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            inputStreamReader = null;
            str = "";
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                return str;
            }
            bufferedReader.close();
            return str;
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    private int b(LicenseType licenseType) {
        switch (licenseType) {
            case APACHE_LICENSE_20:
                return com.artitk.licensefragment.a.a.apache_license_v20;
            case BSD_3_CLAUSE:
                return com.artitk.licensefragment.a.a.bsd_3_clause;
            case BSD_2_CLAUSE:
                return com.artitk.licensefragment.a.a.bsd_2_clause;
            case GPL_30:
                return com.artitk.licensefragment.a.a.gpl_30;
            case MIT_LICENSE:
                return com.artitk.licensefragment.a.a.mit_license;
            case EPL_10:
                return com.artitk.licensefragment.a.a.epl_v10;
            default:
                throw new IllegalArgumentException();
        }
    }
}
