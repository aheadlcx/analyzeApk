package com.sina.weibo.sdk.statistic;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.AidTask;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

class k {
    private static k a;
    private static CopyOnWriteArrayList<h> b;
    private static Map<String, h> c;
    private static Timer d;
    private static Timer e;
    private static int f = 5;

    public static synchronized k getInstance() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                a = new k();
            }
            kVar = a;
        }
        return kVar;
    }

    private k() {
        b = new CopyOnWriteArrayList();
        c = new HashMap();
        LogUtil.i(WBAgent.TAG, "init handler");
    }

    public void onPageStart(String str) {
        if (!i.ACTIVITY_DURATION_OPEN) {
            h hVar = new h(str);
            hVar.setType(g.FRAGMENT);
            synchronized (c) {
                c.put(str, hVar);
            }
            LogUtil.d(WBAgent.TAG, str + ", " + (hVar.getStartTime() / 1000));
        }
    }

    public void onPageEnd(String str) {
        if (!i.ACTIVITY_DURATION_OPEN) {
            if (c.containsKey(str)) {
                h hVar = (h) c.get(str);
                hVar.setDuration(System.currentTimeMillis() - hVar.getStartTime());
                synchronized (b) {
                    b.add(hVar);
                }
                synchronized (c) {
                    c.remove(str);
                }
                LogUtil.d(WBAgent.TAG, str + ", " + (hVar.getStartTime() / 1000) + ", " + (hVar.getDuration() / 1000));
            } else {
                LogUtil.e(WBAgent.TAG, "please call onPageStart before onPageEnd");
            }
            if (b.size() >= f) {
                synchronized (b) {
                    a(b);
                    b.clear();
                }
            }
        }
    }

    public void onResume(Context context) {
        if (f.getPackageName() == null) {
            f.setPackageName(context.getPackageName());
        }
        if (d == null) {
            d = a(context, 500, i.getUploadInterval());
        }
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        a(context, currentTimeMillis);
        if (i.ACTIVITY_DURATION_OPEN) {
            h hVar = new h(name, currentTimeMillis);
            hVar.setType(g.ACTIVITY);
            synchronized (c) {
                c.put(name, hVar);
            }
        }
        LogUtil.d(WBAgent.TAG, name + ", " + (currentTimeMillis / 1000));
    }

    public void onPause(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        LogUtil.i(WBAgent.TAG, "update last page endtime:" + (currentTimeMillis / 1000));
        h.updateSession(context, null, Long.valueOf(0), Long.valueOf(currentTimeMillis));
        if (i.ACTIVITY_DURATION_OPEN) {
            if (c.containsKey(name)) {
                h hVar = (h) c.get(name);
                hVar.setDuration(currentTimeMillis - hVar.getStartTime());
                synchronized (b) {
                    b.add(hVar);
                }
                synchronized (c) {
                    c.remove(name);
                }
                LogUtil.d(WBAgent.TAG, name + ", " + (hVar.getStartTime() / 1000) + ", " + (hVar.getDuration() / 1000));
            } else {
                LogUtil.e(WBAgent.TAG, "please call onResume before onPause");
            }
            if (b.size() >= f) {
                synchronized (b) {
                    a(b);
                    b.clear();
                }
            }
        }
        a(context);
    }

    public void onEvent(String str, String str2, Map<String, String> map) {
        b bVar = new b(str, str2, map);
        bVar.setType(g.EVENT);
        synchronized (b) {
            b.add(bVar);
        }
        if (map == null) {
            LogUtil.d(WBAgent.TAG, "event--- page:" + str + " ,event name:" + str2);
        } else {
            LogUtil.d(WBAgent.TAG, "event--- page:" + str + " ,event name:" + str2 + " ,extend:" + map.toString());
        }
        if (b.size() >= f) {
            synchronized (b) {
                a(b);
                b.clear();
            }
        }
    }

    public void uploadAppLogs(Context context) {
        long currentTimeMillis = System.currentTimeMillis() - f.getTime(context);
        if (f.getTime(context) <= 0 || currentTimeMillis >= i.MIN_UPLOAD_INTERVAL) {
            j.execute(new l(this, context));
            return;
        }
        a(context, i.MIN_UPLOAD_INTERVAL - currentTimeMillis, 0);
    }

    public void onStop(Context context) {
        a(context);
    }

    private void a(Context context) {
        if (b(context)) {
            synchronized (b) {
                a(b);
                b.clear();
            }
        }
    }

    private boolean b(Context context) {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    LogUtil.i(WBAgent.TAG, "后台:" + runningAppProcessInfo.processName);
                    return true;
                }
                LogUtil.i(WBAgent.TAG, "前台:" + runningAppProcessInfo.processName);
                return false;
            }
        }
        return false;
    }

    public void onKillProcess() {
        LogUtil.i(WBAgent.TAG, "save applogs and close timer and shutdown thread executor");
        synchronized (b) {
            a(b);
        }
        a = null;
        b();
        j.shutDownExecutor();
    }

    private void a(Context context, long j) {
        if (h.isNewSession(context, j)) {
            h hVar = new h(context);
            hVar.setType(g.SESSION_END);
            h hVar2 = new h(context, j);
            hVar2.setType(g.SESSION_START);
            synchronized (b) {
                if (hVar.getEndTime() > 0) {
                    b.add(hVar);
                } else {
                    LogUtil.d(WBAgent.TAG, "is a new install");
                }
                b.add(hVar2);
            }
            LogUtil.d(WBAgent.TAG, "last session--- starttime:" + hVar.getStartTime() + " ,endtime:" + hVar.getEndTime());
            LogUtil.d(WBAgent.TAG, "is a new session--- starttime:" + hVar2.getStartTime());
            return;
        }
        LogUtil.i(WBAgent.TAG, "is not a new session");
    }

    private synchronized void a(CopyOnWriteArrayList<h> copyOnWriteArrayList) {
        j.execute(new m(this, c.getPageLogs(copyOnWriteArrayList)));
    }

    private synchronized String a() {
        String str;
        str = "";
        if (b.size() > 0) {
            synchronized (b) {
                str = c.getPageLogs(b);
                b.clear();
            }
        }
        return str;
    }

    private Timer a(Context context, long j, long j2) {
        Timer timer = new Timer();
        TimerTask nVar = new n(this, context);
        if (j2 == 0) {
            timer.schedule(nVar, j);
        } else {
            timer.schedule(nVar, j, j2);
        }
        return timer;
    }

    private void b() {
        if (d != null) {
            d.cancel();
            d = null;
        }
    }

    public void registerApptoAd(Context context, String str, Map<String, String> map) {
        try {
            a aVar = new a();
            aVar.setType(g.APP_AD_START);
            if (isFirstStartBoolean(context)) {
                aVar.setmEvent_id("1");
            }
            aVar.setmImei(MD5.hexdigest(AidTask.getImei(context)));
            aVar.setmStart_time(System.currentTimeMillis());
            aVar.setmExtend(map);
            Object aid = Utility.getAid(context, str);
            if (TextUtils.isEmpty(aid)) {
                TimerTask oVar = new o(this, context, str, aVar);
                e = new Timer();
                e.schedule(oVar, Config.BPLUS_DELAY_TIME);
                return;
            }
            aVar.setmAid(aid);
            uploadAdlog(context, aVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadAdlog(Context context, a aVar) {
        b.add(aVar);
        j.execute(new p(this, context));
    }

    public static boolean isFirstStartBoolean(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(WBConstants.THIRD_APP_IS_FIRST, 0);
        boolean z = sharedPreferences.getBoolean(WBConstants.THIRD_APP_IS_FIRST_KEY, true);
        if (z) {
            Editor edit = sharedPreferences.edit();
            edit.putBoolean(WBConstants.THIRD_APP_IS_FIRST_KEY, false);
            edit.commit();
        }
        return z;
    }
}
