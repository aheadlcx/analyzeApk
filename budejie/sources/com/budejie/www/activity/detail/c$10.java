package com.budejie.www.activity.detail;

import android.os.AsyncTask;
import com.budejie.www.bean.CommentVoiceToWordsItem;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class c$10 extends a<String> {
    final /* synthetic */ c a;

    c$10(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        c.s(this.a).setVisibility(0);
        c.X(this.a).setVisibility(0);
        c.Y(this.a).setVisibility(8);
        c.Z(this.a).setVisibility(8);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        c.X(this.a).setVisibility(8);
        c.Y(this.a).setVisibility(8);
        c.Z(this.a).setVisibility(0);
        c.aa(this.a).setText("语音转换失败");
    }

    public void a(String str) {
        super.onSuccess(str);
        new AsyncTask<String, String, CommentVoiceToWordsItem>(this) {
            final /* synthetic */ c$10 a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((CommentVoiceToWordsItem) obj);
            }

            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void a(CommentVoiceToWordsItem commentVoiceToWordsItem) {
                if (c.X(this.a.a) != null) {
                    c.X(this.a.a).setVisibility(8);
                }
                c.Y(this.a.a).setVisibility(0);
                if (commentVoiceToWordsItem == null) {
                    c.Y(this.a.a).setVisibility(8);
                    c.Z(this.a.a).setVisibility(0);
                    c.aa(this.a.a).setText("语音转换失败");
                } else if (commentVoiceToWordsItem.getCode() == null || !commentVoiceToWordsItem.getCode().equals("1")) {
                    c.Y(this.a.a).setVisibility(8);
                    c.Z(this.a.a).setVisibility(0);
                    CharSequence charSequence = "语音转换失败";
                    if (commentVoiceToWordsItem.getInfo() != null && commentVoiceToWordsItem.getInfo().length() > 0) {
                        charSequence = commentVoiceToWordsItem.getInfo();
                    }
                    c.aa(this.a.a).setText(charSequence);
                } else {
                    c.Y(this.a.a).setText(commentVoiceToWordsItem.getMsg());
                }
            }

            protected CommentVoiceToWordsItem a(String... strArr) {
                CommentVoiceToWordsItem commentVoiceToWordsItem = null;
                try {
                    commentVoiceToWordsItem = z.b(strArr[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return commentVoiceToWordsItem;
            }
        }.execute(new String[]{str});
    }
}
