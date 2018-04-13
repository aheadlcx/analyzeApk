package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.CacheControl$Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request$Builder;
import okhttp3.Response;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.im.ChatMsgVoiceData;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeRecord;
import qsbk.app.model.LaiseeVoice;
import qsbk.app.utils.Util;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class LaiseeDetailActivity extends BaseActionBarActivity implements PtrListener {
    PtrLayout a;
    ListView b;
    TextView c;
    a d;
    ArrayList e = new ArrayList();
    Laisee f;
    EncryptHttpTask g;
    EncryptHttpTask h;
    VoiceManager i;
    c j;
    private int k = 1;
    private boolean l;
    private View m;

    static class VoiceManager {
        public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 10000;
        public static final int DEFAULT_HTTP_READ_TIMEOUT = 20000;
        private static final String a = (Environment.getExternalStorageDirectory() + File.separator + LogUtils.DEFAULT_TAG + File.separator + Laisee.SUB_TYPE_VOICE);
        private OkHttpClient b;

        public interface VoiceCallback {
            void onFaiure(String str, String str2, Object obj);

            void onProgress(String str, long j, long j2, Object obj);

            void onStart(String str, Object obj);

            void onSuccess(String str, String str2, Object obj);
        }

        public static String getDir() {
            File file = new File(a);
            if (!file.exists()) {
                file.mkdirs();
            } else if (!file.isDirectory()) {
                file.delete();
                file.mkdirs();
            }
            return a;
        }

        public static String getPath(String str) {
            String dir = getDir();
            return dir + File.separator + getFileKey(str);
        }

        public static String getFileKey(String str) {
            if (str == null) {
                return "0";
            }
            return String.valueOf(str.hashCode());
        }

        public static void forceRename(File file, File file2) {
            FileOutputStream fileOutputStream;
            IOException e;
            Throwable th;
            FileInputStream fileInputStream = null;
            if (file2.exists()) {
                file2.delete();
            }
            if (!file.renameTo(file2)) {
                FileInputStream fileInputStream2;
                try {
                    fileInputStream2 = new FileInputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                        try {
                            byte[] bArr = new byte[SupportMenu.USER_MASK];
                            while (true) {
                                int read = fileInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.flush();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                }
                            }
                        } catch (IOException e4) {
                            e = e4;
                            fileInputStream = fileInputStream2;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                file.delete();
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream2 = fileInputStream;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e7) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e9) {
                        e = e9;
                        fileOutputStream = null;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        file.delete();
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e10) {
                    e = e10;
                    fileOutputStream = null;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    file.delete();
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    fileInputStream2 = null;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                file.delete();
            }
        }

        private HttpURLConnection a(String str) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.connect();
            return httpURLConnection;
        }

        private InputStream b(String str) throws IOException {
            HttpURLConnection a = a(str);
            int i = 0;
            while (a.getResponseCode() / 100 == 3 && i < 3) {
                a = a(a.getHeaderField(HttpHeaders.LOCATION));
                i++;
            }
            return new BufferedInputStream(a.getInputStream(), SupportMenu.USER_MASK);
        }

        private InputStream c(String str) throws IOException {
            if (this.b == null) {
                return b(str);
            }
            Response execute = this.b.newCall(new Request$Builder().cacheControl(new CacheControl$Builder().noStore().build()).url(str).get().build()).execute();
            int code = execute.code();
            if (execute.isSuccessful()) {
                return execute.body().byteStream();
            }
            throw new IOException("Code:" + code);
        }

        public String getPathIfDownload(String str) {
            String path = getPath(str);
            return new File(path).exists() ? path : null;
        }

        public void download(String str, VoiceCallback voiceCallback, Object obj) {
            new qq(this, voiceCallback, str, obj).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    class a extends BaseImageAdapter {
        final /* synthetic */ LaiseeDetailActivity a;

        public a(LaiseeDetailActivity laiseeDetailActivity, ArrayList<Object> arrayList, Activity activity) {
            this.a = laiseeDetailActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            LaiseeRecord laiseeRecord = (LaiseeRecord) getItem(i);
            if (laiseeRecord.isVoiceRecord()) {
                d dVar;
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.layout_laisee_voice_recorder_item, viewGroup, false);
                    dVar = new d(this.a, view);
                    view.setTag(dVar);
                } else {
                    dVar = (d) view.getTag();
                }
                dVar.bindRecord(laiseeRecord);
            } else {
                b bVar;
                int i2;
                if (view == null) {
                    view = LayoutInflater.from(this.k).inflate(R.layout.layout_laisee_recorder_item, viewGroup, false);
                    bVar = new b(this.a, view);
                    view.setTag(bVar);
                } else {
                    bVar = (b) view.getTag();
                }
                bVar.a.setText(laiseeRecord.userName);
                bVar.b.setText(laiseeRecord.getTimeString());
                bVar.c.setText(laiseeRecord.money + " 元");
                a(laiseeRecord.userId, laiseeRecord.icon, bVar.d);
                TextView textView = bVar.e;
                if (laiseeRecord.isLucky()) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                textView.setVisibility(i2);
            }
            return view;
        }

        protected void a(String str, String str2, ImageView imageView) {
            b(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
        }
    }

    class b {
        TextView a;
        TextView b;
        TextView c;
        ImageView d;
        TextView e;
        final /* synthetic */ LaiseeDetailActivity f;

        public b(LaiseeDetailActivity laiseeDetailActivity, View view) {
            this.f = laiseeDetailActivity;
            this.a = (TextView) view.findViewById(R.id.name);
            this.b = (TextView) view.findViewById(R.id.time);
            this.c = (TextView) view.findViewById(R.id.money);
            this.d = (ImageView) view.findViewById(R.id.avatar);
            this.e = (TextView) view.findViewById(R.id.lucky);
        }
    }

    private class c {
        OnCompletionListener a;
        final /* synthetic */ LaiseeDetailActivity b;
        private final String c;
        private MediaPlayer d;
        private String e;

        private c(LaiseeDetailActivity laiseeDetailActivity) {
            this.b = laiseeDetailActivity;
            this.c = c.class.getSimpleName();
            this.d = new MediaPlayer();
            this.e = null;
        }

        private boolean a(String str) {
            return TextUtils.equals(this.e, str);
        }

        private void a(String str, OnCompletionListener onCompletionListener) {
            if (this.d.isPlaying()) {
                Log.i(this.c, "playing return ");
                a();
                if (this.a != null) {
                    this.a.onCompletion(this.d);
                }
                if (a(str)) {
                    Log.i(this.c, "currentplay return ");
                    return;
                }
            }
            this.e = str;
            try {
                a();
                this.a = onCompletionListener;
                this.d.setAudioStreamType(3);
                this.d.setDataSource(str);
                this.d.prepare();
                this.d.setOnCompletionListener(onCompletionListener);
                this.d.start();
                Log.i(this.c, "playing ");
            } catch (Exception e) {
                e.printStackTrace();
                this.a.onCompletion(this.d);
                a();
            }
        }

        private void a() {
            try {
                this.d.stop();
                this.d.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class d {
        int a;
        TextView b;
        TextView c;
        TextView d;
        ImageView e;
        View f;
        ImageView g;
        TextView h;
        ProgressBar i;
        View j;
        final /* synthetic */ LaiseeDetailActivity k;

        public d(LaiseeDetailActivity laiseeDetailActivity, View view) {
            this.k = laiseeDetailActivity;
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.time);
            this.d = (TextView) view.findViewById(R.id.money);
            this.e = (ImageView) view.findViewById(R.id.avatar);
            this.j = view.findViewById(R.id.lucky);
            this.f = view.findViewById(R.id.voice_layout);
            this.h = (TextView) view.findViewById(R.id.voice_duration);
            this.g = (ImageView) view.findViewById(R.id.voice_sign);
            this.g.setBackgroundResource(R.drawable.bg_voice_record_play);
            this.i = (ProgressBar) view.findViewById(R.id.voice_progress);
            this.i.setVisibility(8);
        }

        public void bindRecord(LaiseeRecord laiseeRecord) {
            this.b.setText(laiseeRecord.userName);
            this.c.setText(laiseeRecord.getTimeString());
            this.d.setText(laiseeRecord.money + " 元");
            LaiseeDetailActivity.a(laiseeRecord.userId, laiseeRecord.icon, this.e);
            this.j.setVisibility(laiseeRecord.isLucky() ? 0 : 8);
            this.h.setText(ChatMsgVoiceData.formatDuration(laiseeRecord.voiceDuration));
            this.f.setOnClickListener(new qr(this, laiseeRecord));
            a(this.f, laiseeRecord.voiceDuration);
        }

        private void a(String str) {
            this.g.setBackgroundResource(0);
            this.g.setBackgroundResource(R.drawable.bg_voice_record_play);
            AnimationDrawable animationDrawable = (AnimationDrawable) this.g.getBackground();
            this.k.j.a(str, new qt(this, animationDrawable));
            animationDrawable.start();
        }

        private void a(View view, int i) {
            if (view != null && i != 0) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.width = LaiseeDetailActivity.b((long) i);
                view.setLayoutParams(layoutParams);
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, Laisee laisee) {
        Intent intent = new Intent(context, LaiseeDetailActivity.class);
        intent.putExtra("laisee", laisee);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected static void a(String str, String str2, ImageView imageView) {
        FrescoImageloader.displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    private static int b(long j) {
        long min = Math.min(j, 20);
        if (min <= 10) {
            return Util.dp((float) ((Math.max(0, min - 2) * 9) + 100));
        }
        return Util.dp((float) ((((min / 10) + 7) * 9) + 100));
    }

    protected String f() {
        return "糗百红包";
    }

    protected int a() {
        return R.layout.activity_laisee_detail;
    }

    protected boolean f_() {
        return false;
    }

    protected int e_() {
        return 0;
    }

    protected void a(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.f = (Laisee) intent.getSerializableExtra("laisee");
        }
        if (this.f == null) {
            finish();
            return;
        }
        getSupportActionBar().setTitle(this.f.title);
        setActionbarBackable();
        this.c = (TextView) findViewById(R.id.join_activity);
        this.c.setVisibility(8);
        this.a = (PtrLayout) findViewById(R.id.ptr);
        this.a.setRefreshEnable(false);
        this.a.setLoadMoreEnable(true);
        this.a.setPtrListener(this);
        this.b = (ListView) findViewById(R.id.listview);
        this.d = new a(this, this.e, this);
        i();
        a(this.f);
        this.i = new VoiceManager();
        this.j = new c();
    }

    private View g() {
        if (this.f.isActivityLaisee()) {
            return LayoutInflater.from(this).inflate(R.layout.layout_laisee_detail_header, null);
        }
        if (this.f instanceof LaiseeVoice) {
            return LayoutInflater.from(this).inflate(R.layout.layout_laisee_voice_header, null);
        }
        return LayoutInflater.from(this).inflate(R.layout.layout_laisee_normal_header, null);
    }

    void a(View view) {
        CharSequence format;
        int i;
        int i2 = 8;
        TextView textView = (TextView) view.findViewById(R.id.amount);
        TextView textView2 = (TextView) view.findViewById(R.id.content);
        TextView textView3 = (TextView) view.findViewById(R.id.tip);
        view.findViewById(R.id.amount_container).setVisibility(TextUtils.isEmpty(this.f.myMoney) ? 8 : 0);
        View findViewById = view.findViewById(R.id.in_wallet);
        if (findViewById != null) {
            int i3;
            if (TextUtils.isEmpty(this.f.myMoney)) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            findViewById.setVisibility(i3);
            findViewById.setOnClickListener(new qk(this));
        }
        textView.setText(this.f.myMoney);
        if (!this.f.isP2P()) {
            format = String.format("已领取%d/%d个，共%.2f/%.2f元", new Object[]{Integer.valueOf(this.f.gotCount), Integer.valueOf(this.f.totalCount), Double.valueOf(this.f.gotMoney), Double.valueOf(this.f.totalMoney)});
        } else if (this.f.gotCount == 0) {
            format = String.format("红包金额%.2f元，等待对方领取", new Object[]{Double.valueOf(this.f.totalMoney)});
        } else {
            format = String.format("%d个红包，共%.2f元", new Object[]{Integer.valueOf(this.f.gotCount), Double.valueOf(this.f.totalMoney)});
        }
        textView3.setText(format);
        if (this.f.totalMoney == 0.0d) {
            i = 8;
        } else {
            i = 0;
        }
        textView3.setVisibility(i);
        textView2.setText(this.f.content);
        if (!TextUtils.isEmpty(this.f.content)) {
            i2 = 0;
        }
        textView2.setVisibility(i2);
        View findViewById2 = view.findViewById(R.id.send_too);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new ql(this));
        }
        if (this.f.isActivityLaisee()) {
            textView = (TextView) view.findViewById(R.id.link);
            if (!TextUtils.isEmpty(this.f.circleTopicId)) {
                textView.setVisibility(0);
                textView.setText(this.f.circleArticleCnt);
                textView.setOnClickListener(new qm(this));
            }
        } else if (this.f instanceof LaiseeVoice) {
            textView2 = (TextView) view.findViewById(R.id.name);
            a(this.f.sendUser.getUid() + "", this.f.sendUser.getIcon(), (ImageView) view.findViewById(R.id.avatar));
            textView2.setText(this.f.sendUser.getLogin());
            this.m.setBackgroundResource(R.color.red_laisee);
            findViewById(R.id.content_container).setVisibility(0);
        } else {
            textView2 = (TextView) view.findViewById(R.id.name);
            a(this.f.sendUser.getUid() + "", this.f.sendUser.getIcon(), (ImageView) view.findViewById(R.id.avatar));
            textView2.setText(this.f.sendUser.getLogin());
        }
    }

    private void i() {
        this.c.setVisibility(TextUtils.isEmpty(this.f.circleTopicId) ? 8 : 0);
        this.c.setOnClickListener(new qn(this));
        if (!TextUtils.isEmpty(this.f.type)) {
            if (this.m == null) {
                this.m = g();
                this.b.addHeaderView(this.m);
                this.b.setAdapter(this.d);
            }
            a(this.m);
        }
    }

    private void a(Laisee laisee) {
        if (laisee != null) {
            this.g = new EncryptHttpTask(null, String.format(Constants.LAISEE_DETAIL, new Object[]{laisee.id}), new qo(this));
            Map hashMap = new HashMap();
            hashMap.put("secret", laisee.secret);
            this.g.setMapParams(hashMap);
            this.g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void b(Laisee laisee) {
        if (!this.l) {
            this.h = new EncryptHttpTask(null, String.format(Constants.LAISEE_RECORDS, new Object[]{laisee.id}), new qp(this));
            Map hashMap = new HashMap();
            hashMap.put("secret", laisee.secret);
            hashMap.put(ParamKey.PAGE, Integer.valueOf(this.k));
            if (this.k == 1) {
                hashMap.put("mine", Integer.valueOf(this.f.got));
            }
            this.h.setMapParams(hashMap);
            this.h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            this.l = true;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.g == null || this.g.isCancelled())) {
            this.g.cancel(true);
        }
        if (this.h != null && !this.h.isCancelled()) {
            this.h.cancel(true);
        }
    }

    public void onRefresh() {
        a(this.f);
        b(this.f);
    }

    public void onLoadMore() {
        b(this.f);
    }
}
