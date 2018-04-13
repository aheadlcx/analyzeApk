package com.zxt.download2;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.util.an;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadListActivity extends Activity {
    List<f> a;
    List<f> b;
    h c;
    h d;
    private ListView e;
    private ListView f;
    private Context g;
    private Button h;
    private Button i;
    private TextView j;
    private TextView k;
    private Dialog l = null;

    private class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ DownloadListActivity a;

        private a(DownloadListActivity downloadListActivity) {
            this.a = downloadListActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.a.l.show();
        }

        protected Void a(Void... voidArr) {
            for (f fVar : this.a.b) {
                g.a(this.a.g).e(fVar);
                g.a(this.a.g).f(fVar);
            }
            this.a.b.clear();
            return null;
        }

        protected void onCancelled() {
            super.onCancelled();
            if (this.a.l.isShowing()) {
                this.a.l.cancel();
            }
        }

        protected void a(Void voidR) {
            if (this.a.l.isShowing()) {
                this.a.l.cancel();
            }
            this.a.d.notifyDataSetChanged();
            an.a(this.a, this.a.getString(R.string.delvideo_clear_successed), -1).show();
        }
    }

    class b implements b {
        final /* synthetic */ DownloadListActivity a;
        private f b;

        public b(DownloadListActivity downloadListActivity, f fVar) {
            this.a = downloadListActivity;
            this.b = fVar;
        }

        public void a(String str) {
            Log.d("DownloadListActivity", "onDownloadFinish");
            this.a.runOnUiThread(new DownloadListActivity$b$1(this));
        }

        public void b() {
            this.b.a(DownloadState.INITIALIZE);
            this.a.runOnUiThread(new DownloadListActivity$b$2(this));
        }

        public void c() {
            Log.d("DownloadListActivity", "onDownloadPause");
            this.b.a(DownloadState.PAUSE);
            this.a.runOnUiThread(new DownloadListActivity$b$3(this));
        }

        public void d() {
            Log.d("DownloadListActivity", "onDownloadStop");
            this.b.a(DownloadState.PAUSE);
            this.a.runOnUiThread(new DownloadListActivity$b$4(this));
        }

        public void e() {
            Log.d("DownloadListActivity", "onDownloadFail");
            this.b.a(DownloadState.FAILED);
            this.a.runOnUiThread(new DownloadListActivity$b$5(this));
        }

        public void a(int i, int i2, int i3) {
            this.b.a(DownloadState.DOWNLOADING);
            this.b.a(i);
            this.b.b(i2);
            this.b.c(((i / 1024) * 100) / (i2 / 1024));
            this.b.d(i3);
            this.a.runOnUiThread(new DownloadListActivity$b$6(this));
        }
    }

    private void a() {
        try {
            this.a.addAll(g.a(this.g).a());
            this.c.notifyDataSetChanged();
        } catch (Exception e) {
        }
    }

    private void b() {
        new Thread(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.b.addAll(g.a(this.a.g).b());
                    this.a.d.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        }.start();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        setContentView(R.layout.download_list);
        this.g = this;
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.i = (Button) findViewById(R.id.buttonDownloading);
        this.h = (Button) findViewById(R.id.buttonDownloaded);
        this.j = (TextView) findViewById(R.id.back);
        this.k = (TextView) findViewById(R.id.delAll);
        this.l = new Dialog(this, R.style.dialogTheme);
        this.l.setContentView(R.layout.loaddialog_wc);
        ((TextView) this.l.findViewById(R.id.dialog_txt)).setText("正在清除……");
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.b == null || this.a.b.size() != 0) {
                    Builder builder = new Builder(this.a);
                    builder.setTitle(R.string.system_tip);
                    builder.setMessage(R.string.delvideo_message_content);
                    builder.setPositiveButton(R.string.update_btn_sure, new DownloadListActivity$3$1(this));
                    builder.setNegativeButton(R.string.update_btn_cancel, null);
                    if (!this.a.isFinishing()) {
                        builder.show();
                    }
                }
            }
        });
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(true);
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(false);
            }
        });
        this.e = (ListView) findViewById(R.id.downloadingListView);
        this.f = (ListView) findViewById(R.id.downloadedListView);
        a(getIntent().getBooleanExtra("isDownloaded", false));
        this.b = new ArrayList();
        this.d = new h(this, 0, this.b);
        b();
        this.f.setAdapter(this.d);
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Log.d("DownloadListActivity", "arg2" + i + " mDownloadedlist" + this.a.b.size());
                this.a.b((f) this.a.b.get(i));
            }
        });
        this.f.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                new Builder(this.a.g).setItems(new String[]{this.a.g.getString(R.string.download_delete_task_file), this.a.g.getString(R.string.download_share_weixin_friend)}, new DownloadListActivity$8$1(this, i)).create().show();
                return true;
            }
        });
        this.a = new ArrayList();
        this.c = new h(this, 0, this.a);
        a();
        this.e.setAdapter(this.c);
        this.e.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f fVar = (f) this.a.a.get(i);
                switch (fVar.i()) {
                    case PAUSE:
                        Log.i("DownloadListActivity", "PAUSE continue " + fVar.c());
                        g.a(this.a.g).b(fVar);
                        return;
                    case FAILED:
                        Log.i("DownloadListActivity", "FAILED continue " + fVar.c());
                        g.a(this.a.g).b(fVar);
                        return;
                    case DOWNLOADING:
                        Log.i("DownloadListActivity", "DOWNLOADING pause " + fVar.c());
                        g.a(this.a.g).a(fVar, false);
                        return;
                    case FINISHED:
                        this.a.b(fVar);
                        return;
                    case INITIALIZE:
                        g.a(this.a.g).a(fVar, true);
                        return;
                    default:
                        return;
                }
            }
        });
        this.e.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ DownloadListActivity a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                new Builder(this.a.g).setItems(new String[]{this.a.g.getString(R.string.download_delete_task_file), "取消"}, new DownloadListActivity$10$1(this, i)).create().show();
                return true;
            }
        });
        for (f fVar : this.a) {
            if (!fVar.i().equals(DownloadState.FINISHED)) {
                Log.d("DownloadListActivity", "add listener");
                a(fVar);
            }
        }
    }

    private void a(File file) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
        intent.setAction("android.intent.action.SEND");
        intent.setType("video/*");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        startActivity(intent);
    }

    private void a(boolean z) {
        if (z) {
            this.h.setBackgroundResource(R.drawable.title_button_right_select);
            this.i.setBackgroundResource(R.drawable.title_button_left_normal);
            this.h.setTextColor(getResources().getColor(R.color.main_red));
            this.i.setTextColor(getResources().getColor(R.color.White));
            this.f.setVisibility(0);
            this.e.setVisibility(8);
            this.k.setVisibility(0);
            return;
        }
        this.h.setBackgroundResource(R.drawable.title_button_right_normal);
        this.i.setBackgroundResource(R.drawable.title_button_left_select);
        this.h.setTextColor(getResources().getColor(R.color.White));
        this.i.setTextColor(getResources().getColor(R.color.main_red));
        this.f.setVisibility(8);
        this.e.setVisibility(0);
        this.k.setVisibility(8);
    }

    protected void onDestroy() {
        Log.d("DownloadListActivity", "onDestroy!!!!!!!!!!");
        super.onDestroy();
    }

    public void a(f fVar) {
        g.a(this.g).a(fVar, new b(this, fVar));
    }

    protected void onNewIntent(Intent intent) {
        a(intent.getBooleanExtra("isDownloaded", false));
        super.onNewIntent(intent);
    }

    public void b(f fVar) {
        Log.d("DownloadListActivity", fVar.d() + "/" + fVar.c());
        Intent a = d.a(fVar.d() + "/" + fVar.c());
        if (a == null) {
            Toast.makeText(this.g, R.string.download_file_not_exist, 1).show();
        } else {
            this.g.startActivity(a);
        }
    }

    private void c() {
        Log.v("DownloadListActivity", "deleteCache");
        new a().execute(new Void[0]);
    }
}
