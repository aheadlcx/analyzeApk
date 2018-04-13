package com.tencent.tinker.loader.hotplug;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.iflytek.aiui.AIUIConstant;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.AuthActivity;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.umeng.analytics.b.g;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import tv.danmaku.ijk.media.player.IjkMediaPlayer$OnNativeInvokeListener;

public final class IncrementComponentManager {
    private static final String TAG = "Tinker.IncrementCompMgr";
    private static final int TAG_ACTIVITY = 0;
    private static final int TAG_PROVIDER = 2;
    private static final int TAG_RECEIVER = 3;
    private static final int TAG_SERVICE = 1;
    private static final AttrTranslator<ActivityInfo> sActivityInfoAttrTranslator = new AttrTranslator<ActivityInfo>() {
        void a(Context context, int i, XmlPullParser xmlPullParser) {
            if (i == 0) {
                try {
                    if (xmlPullParser.getEventType() != 2 || !"activity".equals(xmlPullParser.getName())) {
                        throw new IllegalStateException("unexpected xml parser state when parsing incremental component manifest.");
                    }
                } catch (Throwable e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        void a(Context context, int i, String str, String str2, ActivityInfo activityInfo) {
            int i2 = 0;
            if ("name".equals(str)) {
                if (str2.charAt(i2) == '.') {
                    activityInfo.name = context.getPackageName() + str2;
                } else {
                    activityInfo.name = str2;
                }
            } else if ("parentActivityName".equals(str)) {
                if (VERSION.SDK_INT < 16) {
                    return;
                }
                if (str2.charAt(i2) == '.') {
                    activityInfo.parentActivityName = context.getPackageName() + str2;
                } else {
                    activityInfo.parentActivityName = str2;
                }
            } else if ("exported".equals(str)) {
                activityInfo.exported = "true".equalsIgnoreCase(str2);
            } else if ("launchMode".equals(str)) {
                activityInfo.launchMode = a(str2);
            } else if ("theme".equals(str)) {
                activityInfo.theme = context.getResources().getIdentifier(str2, g.P, context.getPackageName());
            } else if ("uiOptions".equals(str)) {
                if (VERSION.SDK_INT >= 14) {
                    activityInfo.uiOptions = Integer.decode(str2).intValue();
                }
            } else if ("permission".equals(str)) {
                activityInfo.permission = str2;
            } else if ("taskAffinity".equals(str)) {
                activityInfo.taskAffinity = str2;
            } else if ("multiprocess".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 1;
                }
            } else if ("finishOnTaskLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 2;
                }
            } else if ("clearTaskOnLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 4;
                }
            } else if ("noHistory".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 128;
                }
            } else if ("alwaysRetainTaskState".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 8;
                }
            } else if ("stateNotNeeded".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 16;
                }
            } else if ("excludeFromRecents".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 32;
                }
            } else if ("allowTaskReparenting".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 64;
                }
            } else if ("finishOnCloseSystemDialogs".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 256;
                }
            } else if ("showOnLockScreen".equals(str) || "showForAllUsers".equals(str)) {
                if (VERSION.SDK_INT >= 23 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags = ShareReflectUtil.getValueOfStaticIntField(ActivityInfo.class, "FLAG_SHOW_FOR_ALL_USERS", i2) | activityInfo.flags;
                }
            } else if ("immersive".equals(str)) {
                if (VERSION.SDK_INT >= 18 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 2048;
                }
            } else if ("hardwareAccelerated".equals(str)) {
                if (VERSION.SDK_INT >= 11 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 512;
                }
            } else if ("documentLaunchMode".equals(str)) {
                if (VERSION.SDK_INT >= 21) {
                    activityInfo.documentLaunchMode = Integer.decode(str2).intValue();
                }
            } else if ("maxRecents".equals(str)) {
                if (VERSION.SDK_INT >= 21) {
                    activityInfo.maxRecents = Integer.decode(str2).intValue();
                }
            } else if ("configChanges".equals(str)) {
                activityInfo.configChanges = Integer.decode(str2).intValue();
            } else if ("windowSoftInputMode".equals(str)) {
                activityInfo.softInputMode = Integer.decode(str2).intValue();
            } else if ("persistableMode".equals(str)) {
                if (VERSION.SDK_INT >= 21) {
                    activityInfo.persistableMode = Integer.decode(str2).intValue();
                }
            } else if ("allowEmbedded".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags = ShareReflectUtil.getValueOfStaticIntField(ActivityInfo.class, "FLAG_ALLOW_EMBEDDED", i2) | activityInfo.flags;
                }
            } else if ("autoRemoveFromRecents".equals(str)) {
                if (VERSION.SDK_INT >= 21 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 8192;
                }
            } else if ("relinquishTaskIdentity".equals(str)) {
                if (VERSION.SDK_INT >= 21 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 4096;
                }
            } else if ("resumeWhilePausing".equals(str)) {
                if (VERSION.SDK_INT >= 21 && "true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 16384;
                }
            } else if ("screenOrientation".equals(str)) {
                activityInfo.screenOrientation = b(str2);
            } else if ("label".equals(str)) {
                try {
                    i2 = context.getResources().getIdentifier(str2, "string", IncrementComponentManager.sPackageName);
                } catch (Throwable th) {
                }
                if (i2 != 0) {
                    activityInfo.labelRes = i2;
                } else {
                    activityInfo.nonLocalizedLabel = str2;
                }
            } else if ("icon".equals(str)) {
                try {
                    activityInfo.icon = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                } catch (Throwable th2) {
                }
            } else if ("banner".equals(str)) {
                if (VERSION.SDK_INT >= 20) {
                    try {
                        activityInfo.banner = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                    } catch (Throwable th3) {
                    }
                }
            } else if ("logo".equals(str)) {
                try {
                    activityInfo.logo = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                } catch (Throwable th4) {
                }
            }
        }

        private int a(String str) {
            if ("standard".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("singleTop".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("singleTask".equalsIgnoreCase(str)) {
                return 2;
            }
            if ("singleInstance".equalsIgnoreCase(str)) {
                return 3;
            }
            Log.w(IncrementComponentManager.TAG, "Unknown launchMode: " + str);
            return 0;
        }

        private int b(String str) {
            if ("unspecified".equalsIgnoreCase(str)) {
                return -1;
            }
            if ("behind".equalsIgnoreCase(str)) {
                return 3;
            }
            if ("landscape".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("portrait".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("reverseLandscape".equalsIgnoreCase(str)) {
                return 8;
            }
            if ("reversePortrait".equalsIgnoreCase(str)) {
                return 9;
            }
            if ("sensorLandscape".equalsIgnoreCase(str)) {
                return 6;
            }
            if ("sensorPortrait".equalsIgnoreCase(str)) {
                return 7;
            }
            if ("sensor".equalsIgnoreCase(str)) {
                return 4;
            }
            if ("fullSensor".equalsIgnoreCase(str)) {
                return 10;
            }
            if ("nosensor".equalsIgnoreCase(str)) {
                return 5;
            }
            if (AIUIConstant.USER.equalsIgnoreCase(str)) {
                return 2;
            }
            if (VERSION.SDK_INT >= 18 && "fullUser".equalsIgnoreCase(str)) {
                return 13;
            }
            if (VERSION.SDK_INT >= 18 && "locked".equalsIgnoreCase(str)) {
                return 14;
            }
            if (VERSION.SDK_INT >= 18 && "userLandscape".equalsIgnoreCase(str)) {
                return 11;
            }
            if (VERSION.SDK_INT < 18 || !"userPortrait".equalsIgnoreCase(str)) {
                return -1;
            }
            return 12;
        }
    };
    private static final Map<String, ActivityInfo> sClassNameToActivityInfoMap = new HashMap();
    private static final Map<String, IntentFilter> sClassNameToIntentFilterMap = new HashMap();
    private static Context sContext = null;
    private static volatile boolean sInitialized = false;
    private static String sPackageName = null;

    private static abstract class AttrTranslator<T_RESULT> {
        abstract void a(Context context, int i, String str, String str2, T_RESULT t_result);

        private AttrTranslator() {
        }

        final void a(Context context, int i, XmlPullParser xmlPullParser, T_RESULT t_result) {
            a(context, i, xmlPullParser);
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i2 = 0; i2 < attributeCount; i2++) {
                if ("android".equals(xmlPullParser.getAttributePrefix(i2))) {
                    a(context, i, xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2), t_result);
                }
            }
        }

        void a(Context context, int i, XmlPullParser xmlPullParser) {
        }
    }

    public static synchronized boolean init(Context context, ShareSecurityCheck shareSecurityCheck) throws IOException {
        boolean z;
        synchronized (IncrementComponentManager.class) {
            if (shareSecurityCheck.getMetaContentMap().containsKey(EnvConsts.INCCOMPONENT_META_FILE)) {
                Context context2 = context;
                while (context2 instanceof ContextWrapper) {
                    context = ((ContextWrapper) context2).getBaseContext();
                    if (context == null) {
                        break;
                    }
                    context2 = context;
                }
                sContext = context2;
                sPackageName = context2.getPackageName();
                Closeable stringReader = new StringReader((String) shareSecurityCheck.getMetaContentMap().get(EnvConsts.INCCOMPONENT_META_FILE));
                try {
                    XmlPullParser newPullParser = Xml.newPullParser();
                    newPullParser.setInput(stringReader);
                    for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                        switch (eventType) {
                            case 2:
                                String name = newPullParser.getName();
                                if (!"activity".equalsIgnoreCase(name)) {
                                    if (!(NotificationCompat.CATEGORY_SERVICE.equalsIgnoreCase(name) || SocialConstants.PARAM_RECEIVER.equalsIgnoreCase(name) || !g.as.equalsIgnoreCase(name))) {
                                        break;
                                    }
                                }
                                ActivityInfo parseActivity = parseActivity(context2, newPullParser);
                                sClassNameToActivityInfoMap.put(parseActivity.name, parseActivity);
                                break;
                            default:
                                break;
                        }
                    }
                    sInitialized = true;
                    SharePatchFileUtil.closeQuietly(stringReader);
                    z = true;
                } catch (Throwable e) {
                    throw new IOException(e);
                } catch (Throwable th) {
                    SharePatchFileUtil.closeQuietly(stringReader);
                }
            } else {
                Log.i(TAG, "package has no incremental component meta, skip init.");
                z = false;
            }
        }
        return z;
    }

    private static synchronized ActivityInfo parseActivity(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ActivityInfo activityInfo;
        synchronized (IncrementComponentManager.class) {
            activityInfo = new ActivityInfo();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            activityInfo.applicationInfo = applicationInfo;
            activityInfo.packageName = sPackageName;
            activityInfo.processName = applicationInfo.processName;
            activityInfo.launchMode = 0;
            activityInfo.permission = applicationInfo.permission;
            activityInfo.screenOrientation = -1;
            activityInfo.taskAffinity = applicationInfo.taskAffinity;
            if (VERSION.SDK_INT >= 11 && (applicationInfo.flags & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0) {
                activityInfo.flags |= 512;
            }
            if (VERSION.SDK_INT >= 21) {
                activityInfo.documentLaunchMode = 0;
            }
            if (VERSION.SDK_INT >= 14) {
                activityInfo.uiOptions = applicationInfo.uiOptions;
            }
            sActivityInfoAttrTranslator.a(context, 0, xmlPullParser, activityInfo);
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                } else if (!(next == 3 || next == 4)) {
                    String name = xmlPullParser.getName();
                    if ("intent-filter".equalsIgnoreCase(name)) {
                        parseIntentFilter(context, activityInfo.name, xmlPullParser);
                    } else if ("meta-data".equalsIgnoreCase(name)) {
                        parseMetaData(context, activityInfo, xmlPullParser);
                    }
                }
            }
        }
        return activityInfo;
    }

    private static synchronized void parseIntentFilter(Context context, String str, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        synchronized (IncrementComponentManager.class) {
            IntentFilter intentFilter = new IntentFilter();
            Object attributeValue = xmlPullParser.getAttributeValue(null, "priority");
            if (!TextUtils.isEmpty(attributeValue)) {
                intentFilter.setPriority(Integer.decode(attributeValue).intValue());
            }
            if (!TextUtils.isEmpty(xmlPullParser.getAttributeValue(null, "autoVerify"))) {
                try {
                    ShareReflectUtil.findMethod(IntentFilter.class, "setAutoVerify", Boolean.TYPE).invoke(intentFilter, new Object[]{Boolean.valueOf("true".equalsIgnoreCase(attributeValue))});
                } catch (Throwable th) {
                }
            }
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                    sClassNameToIntentFilterMap.put(str, intentFilter);
                } else if (!(next == 3 || next == 4)) {
                    String name = xmlPullParser.getName();
                    if (AuthActivity.ACTION_KEY.equals(name)) {
                        name = xmlPullParser.getAttributeValue(null, "name");
                        if (name != null) {
                            intentFilter.addAction(name);
                        }
                    } else if ("category".equals(name)) {
                        name = xmlPullParser.getAttributeValue(null, "name");
                        if (name != null) {
                            intentFilter.addCategory(name);
                        }
                    } else if ("data".equals(name)) {
                        name = xmlPullParser.getAttributeValue(null, "mimeType");
                        if (name != null) {
                            try {
                                intentFilter.addDataType(name);
                            } catch (Throwable e) {
                                throw new XmlPullParserException("bad mimeType", xmlPullParser, e);
                            }
                        }
                        name = xmlPullParser.getAttributeValue(null, "scheme");
                        if (name != null) {
                            intentFilter.addDataScheme(name);
                        }
                        if (VERSION.SDK_INT >= 19) {
                            name = xmlPullParser.getAttributeValue(null, "ssp");
                            if (name != null) {
                                intentFilter.addDataSchemeSpecificPart(name, 0);
                            }
                            name = xmlPullParser.getAttributeValue(null, "sspPrefix");
                            if (name != null) {
                                intentFilter.addDataSchemeSpecificPart(name, 1);
                            }
                            name = xmlPullParser.getAttributeValue(null, "sspPattern");
                            if (name != null) {
                                intentFilter.addDataSchemeSpecificPart(name, 2);
                            }
                        }
                        name = xmlPullParser.getAttributeValue(null, "host");
                        String attributeValue2 = xmlPullParser.getAttributeValue(null, IjkMediaPlayer$OnNativeInvokeListener.ARG_PORT);
                        if (name != null) {
                            intentFilter.addDataAuthority(name, attributeValue2);
                        }
                        name = xmlPullParser.getAttributeValue(null, AIUIConstant.RES_TYPE_PATH);
                        if (name != null) {
                            intentFilter.addDataPath(name, 0);
                        }
                        name = xmlPullParser.getAttributeValue(null, "pathPrefix");
                        if (name != null) {
                            intentFilter.addDataPath(name, 1);
                        }
                        name = xmlPullParser.getAttributeValue(null, "pathPattern");
                        if (name != null) {
                            intentFilter.addDataPath(name, 2);
                        }
                    }
                    skipCurrentTag(xmlPullParser);
                }
            }
            sClassNameToIntentFilterMap.put(str, intentFilter);
        }
    }

    private static synchronized void parseMetaData(Context context, ActivityInfo activityInfo, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        synchronized (IncrementComponentManager.class) {
            ClassLoader classLoader = IncrementComponentManager.class.getClassLoader();
            Object attributeValue = xmlPullParser.getAttributeValue(null, "name");
            String attributeValue2 = xmlPullParser.getAttributeValue(null, "value");
            if (!TextUtils.isEmpty(attributeValue)) {
                if (activityInfo.metaData == null) {
                    activityInfo.metaData = new Bundle(classLoader);
                }
                activityInfo.metaData.putString(attributeValue, attributeValue2);
            }
        }
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    private static synchronized void ensureInitialized() {
        synchronized (IncrementComponentManager.class) {
            if (sInitialized) {
            } else {
                throw new IllegalStateException("Not initialized!!");
            }
        }
    }

    public static boolean isIncrementActivity(String str) {
        ensureInitialized();
        return str != null && sClassNameToActivityInfoMap.containsKey(str);
    }

    public static ActivityInfo queryActivityInfo(String str) {
        ensureInitialized();
        return str != null ? (ActivityInfo) sClassNameToActivityInfoMap.get(str) : null;
    }

    public static ResolveInfo resolveIntent(Intent intent) {
        Object className;
        int i;
        ensureInitialized();
        IntentFilter intentFilter = null;
        int i2 = 0;
        ComponentName component = intent.getComponent();
        int i3;
        if (component != null) {
            className = component.getClassName();
            if (sClassNameToActivityInfoMap.containsKey(className)) {
                i3 = 0;
            } else {
                className = null;
                i3 = -1;
            }
            i = i3;
        } else {
            String str = null;
            i = -1;
            for (Entry entry : sClassNameToIntentFilterMap.entrySet()) {
                Object obj;
                int i4;
                IntentFilter intentFilter2;
                String str2 = (String) entry.getKey();
                IntentFilter intentFilter3 = (IntentFilter) entry.getValue();
                i3 = intentFilter3.match(intent.getAction(), intent.getType(), intent.getScheme(), intent.getData(), intent.getCategories(), TAG);
                if (i3 == -3 || i3 == -4 || i3 == -2 || i3 == -1) {
                    obj = null;
                } else {
                    obj = 1;
                }
                int priority = intentFilter3.getPriority();
                if (obj == null || priority <= i) {
                    i4 = i2;
                    intentFilter2 = intentFilter;
                    str2 = str;
                    priority = i;
                } else {
                    int i5 = i3;
                    intentFilter2 = intentFilter3;
                    i4 = i5;
                }
                i2 = i4;
                intentFilter = intentFilter2;
                str = str2;
                i = priority;
            }
            String str3 = str;
        }
        if (className == null) {
            return null;
        }
        ResolveInfo resolveInfo = new ResolveInfo();
        resolveInfo.activityInfo = (ActivityInfo) sClassNameToActivityInfoMap.get(className);
        resolveInfo.filter = intentFilter;
        resolveInfo.match = i2;
        resolveInfo.priority = i;
        resolveInfo.resolvePackageName = sPackageName;
        resolveInfo.icon = resolveInfo.activityInfo.icon;
        resolveInfo.labelRes = resolveInfo.activityInfo.labelRes;
        return resolveInfo;
    }

    private IncrementComponentManager() {
        throw new UnsupportedOperationException();
    }
}
