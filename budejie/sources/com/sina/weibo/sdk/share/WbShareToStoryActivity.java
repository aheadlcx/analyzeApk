package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.sina.weibo.sdk.a.c;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.api.StoryMessage;
import com.sina.weibo.sdk.api.StoryObject;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.web.view.WbSdkProgressBar;

public class WbShareToStoryActivity extends Activity {
    private a a;
    private String b;
    private FrameLayout c;
    private View d;
    private int e = -1;
    private int f = -1;

    private class a extends AsyncTask<StoryMessage, Object, StoryObject> {
        final /* synthetic */ WbShareToStoryActivity a;

        private a(WbShareToStoryActivity wbShareToStoryActivity) {
            this.a = wbShareToStoryActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((StoryMessage[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((StoryObject) obj);
        }

        protected StoryObject a(StoryMessage... storyMessageArr) {
            int i = 0;
            StoryMessage storyMessage = storyMessageArr[0];
            if (storyMessage.b() == null) {
                i = 1;
            }
            Object a = c.a(this.a, i == 1 ? storyMessage.a() : storyMessage.b(), i);
            if (TextUtils.isEmpty(a)) {
                return null;
            }
            StoryObject storyObject = new StoryObject();
            storyObject.a = a;
            storyObject.b = i;
            storyObject.c = b.b().getAppKey();
            storyObject.d = this.a.getPackageName();
            return storyObject;
        }

        protected void a(StoryObject storyObject) {
            super.onPostExecute(storyObject);
            if (storyObject != null) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sinaweibo://story/publish?forceedit=1&finish=true"));
                    intent.putExtra("storyData", storyObject);
                    this.a.startActivity(intent);
                    return;
                } catch (Exception e) {
                    this.a.a(2);
                    return;
                }
            }
            this.a.a(2);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.b = bundle.getString("startActivity");
        } else {
            this.b = getIntent().getStringExtra("startActivity");
        }
        StoryMessage storyMessage = (StoryMessage) getIntent().getParcelableExtra("_weibo_message_stroy");
        if (storyMessage == null) {
            a(2);
        } else if (a(storyMessage)) {
            a();
            b(storyMessage);
        } else {
            a(2);
        }
    }

    private void a() {
        this.e = getIntent().getIntExtra("progressColor", -1);
        this.f = getIntent().getIntExtra("progressId", -1);
        this.c = new FrameLayout(this);
        if (this.f != -1) {
            try {
                this.d = ((LayoutInflater) getSystemService("layout_inflater")).inflate(this.f, null);
            } catch (Exception e) {
                this.d = new WbSdkProgressBar(this);
            }
        } else {
            this.d = new WbSdkProgressBar(this);
            if (this.e != -1) {
                ((WbSdkProgressBar) this.d).setProgressColor(this.e);
            }
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.c.addView(this.d, layoutParams);
        this.c.setBackgroundColor(855638016);
        setContentView(this.c);
    }

    private boolean a(StoryMessage storyMessage) {
        if (storyMessage.c() && b.b(this)) {
            return true;
        }
        return false;
    }

    private void b(StoryMessage storyMessage) {
        if (this.a != null) {
            this.a.cancel(true);
        }
        this.a = new a();
        this.a.execute(new StoryMessage[]{storyMessage});
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra("backType", 0) == 0) {
            a(1);
        } else {
            a(0);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("startActivity", this.b);
    }

    private void a(int i) {
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        try {
            Intent intent = new Intent();
            intent.putExtra("_weibo_resp_errcode", i);
            intent.setFlags(131072);
            intent.setClassName(this, this.b);
            startActivity(intent);
        } catch (Exception e) {
            d.d("weibo sdk", e.toString());
        }
        finish();
    }
}
