package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.StateSet;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.api.BigCoverHelper;

public class ResourceManager {
    private static final String a = ResourceManager.class.getName();
    private static final String[] b = new String[]{"drawable-xxhdpi", "drawable-xhdpi", "drawable-hdpi", "drawable-mdpi", "drawable-ldpi", "drawable"};

    public static String getString(Context context, String str, String str2, String str3) {
        Locale language = getLanguage();
        if (Locale.SIMPLIFIED_CHINESE.equals(language) || ("zh".equals(language.getLanguage()) && language.getCountry().contains("CN"))) {
            return str2;
        }
        if (Locale.TRADITIONAL_CHINESE.equals(language) || ("zh".equals(language.getLanguage()) && language.getCountry().contains("TW"))) {
            return str3;
        }
        return str;
    }

    public static Drawable getDrawable(Context context, String str) {
        return a(context, a(context, str), false);
    }

    public static Drawable getNinePatchDrawable(Context context, String str) {
        return a(context, a(context, str), true);
    }

    public static Locale getLanguage() {
        Locale locale = Locale.getDefault();
        if (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) {
            return locale;
        }
        return (locale.getLanguage().equals("zh") && (locale.getCountry().contains("CN") || locale.getCountry().contains("TW"))) ? locale : Locale.ENGLISH;
    }

    private static String a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(a, "id is NOT correct!");
            return null;
        }
        String a = a(context);
        LogUtil.d(a, "find Appropriate path...");
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        while (i < b.length) {
            if (b[i].equals(a)) {
                i2 = i;
            }
            String str2 = b[i] + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
            if (b(context, str2)) {
                if (i2 != i) {
                    if (i2 >= 0) {
                        break;
                    }
                    i3 = i;
                } else {
                    return str2;
                }
            }
            i++;
        }
        i = -1;
        if (i3 <= 0 || i <= 0) {
            if (i3 <= 0 || i >= 0) {
                if (i3 >= 0 || i <= 0) {
                    LogUtil.e(a, "Not find the appropriate path for drawable");
                    i3 = -1;
                } else {
                    i3 = i;
                }
            }
        } else if (Math.abs(i2 - i) <= Math.abs(i2 - i3)) {
            i3 = i;
        }
        if (i3 >= 0) {
            return b[i3] + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
        }
        LogUtil.e(a, "Not find the appropriate path for drawable");
        return null;
    }

    private static Drawable a(Context context, String str, boolean z) {
        IOException e;
        InputStream inputStream;
        Throwable th;
        InputStream open;
        try {
            Drawable ninePatchDrawable;
            open = context.getAssets().open(str);
            if (open != null) {
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(open);
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    if (z) {
                        ninePatchDrawable = new NinePatchDrawable(new Resources(context.getAssets(), displayMetrics, context.getResources().getConfiguration()), decodeStream, decodeStream.getNinePatchChunk(), new Rect(0, 0, 0, 0), null);
                    } else {
                        decodeStream.setDensity(displayMetrics.densityDpi);
                        ninePatchDrawable = new BitmapDrawable(context.getResources(), decodeStream);
                    }
                } catch (IOException e2) {
                    e = e2;
                    inputStream = open;
                    try {
                        e.printStackTrace();
                        if (inputStream != null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
            }
            ninePatchDrawable = null;
            if (open == null) {
                return ninePatchDrawable;
            }
            try {
                open.close();
                return ninePatchDrawable;
            } catch (IOException e42) {
                e42.printStackTrace();
                return ninePatchDrawable;
            }
        } catch (IOException e5) {
            e3 = e5;
            inputStream = null;
            e3.printStackTrace();
            if (inputStream != null) {
                return null;
            }
            inputStream.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    private static boolean b(Context context, String str) {
        boolean z = false;
        if (!(context == null || TextUtils.isEmpty(str))) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(str);
                LogUtil.d(a, "file [" + str + "] existed");
                z = true;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                LogUtil.d(a, "file [" + str + "] NOT existed");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    private static String a(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i <= 120) {
            return "drawable-ldpi";
        }
        if (i > 120 && i <= BigCoverHelper.REQCODE_CAREMA) {
            return "drawable-mdpi";
        }
        if (i > BigCoverHelper.REQCODE_CAREMA && i <= 240) {
            return "drawable-hdpi";
        }
        if (i <= 240 || i > 320) {
            return "drawable-xxhdpi";
        }
        return "drawable-xhdpi";
    }

    public static int dp2px(Context context, int i) {
        return (int) (((double) (context.getResources().getDisplayMetrics().density * ((float) i))) + 0.5d);
    }

    public static ColorStateList createColorStateList(int i, int i2) {
        int[] iArr = new int[]{i2, i2, i2, i};
        int[][] iArr2 = new int[4][];
        iArr2[0] = new int[]{16842919};
        iArr2[1] = new int[]{16842913};
        iArr2[2] = new int[]{16842908};
        iArr2[3] = StateSet.WILD_CARD;
        return new ColorStateList(iArr2, iArr);
    }

    public static StateListDrawable createStateListDrawable(Context context, String str, String str2) {
        Drawable ninePatchDrawable;
        Drawable ninePatchDrawable2;
        if (str.indexOf(".9") > -1) {
            ninePatchDrawable = getNinePatchDrawable(context, str);
        } else {
            ninePatchDrawable = getDrawable(context, str);
        }
        if (str2.indexOf(".9") > -1) {
            ninePatchDrawable2 = getNinePatchDrawable(context, str2);
        } else {
            ninePatchDrawable2 = getDrawable(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, ninePatchDrawable2);
        stateListDrawable.addState(new int[]{16842913}, ninePatchDrawable2);
        stateListDrawable.addState(new int[]{16842908}, ninePatchDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, ninePatchDrawable);
        return stateListDrawable;
    }

    public static StateListDrawable createStateListDrawable(Context context, String str, String str2, String str3) {
        Drawable ninePatchDrawable;
        Drawable ninePatchDrawable2;
        Drawable ninePatchDrawable3;
        if (str.indexOf(".9") > -1) {
            ninePatchDrawable = getNinePatchDrawable(context, str);
        } else {
            ninePatchDrawable = getDrawable(context, str);
        }
        if (str3.indexOf(".9") > -1) {
            ninePatchDrawable2 = getNinePatchDrawable(context, str3);
        } else {
            ninePatchDrawable2 = getDrawable(context, str3);
        }
        if (str2.indexOf(".9") > -1) {
            ninePatchDrawable3 = getNinePatchDrawable(context, str2);
        } else {
            ninePatchDrawable3 = getDrawable(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842913}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842908}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842766}, ninePatchDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, ninePatchDrawable);
        return stateListDrawable;
    }

    public static String readCountryFromAsset(Context context, String str) {
        IOException e;
        String str2 = "";
        String str3;
        try {
            InputStream open = context.getAssets().open(str);
            if (open == null) {
                return str2;
            }
            DataInputStream dataInputStream = new DataInputStream(open);
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            str3 = new String(bArr, "UTF-8");
            try {
                open.close();
                return str3;
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            str3 = str2;
            e = iOException;
            e.printStackTrace();
            return str3;
        }
    }
}
