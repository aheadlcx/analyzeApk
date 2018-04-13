package com.budejie.www.activity.detail;

import android.os.AsyncTask;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class c$15 extends a<String> {
    final /* synthetic */ c a;

    c$15(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
    }

    public void a(String str) {
        super.onSuccess(str);
        new AsyncTask<String, String, CommentItem>(this) {
            final /* synthetic */ c$15 a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((CommentItem) obj);
            }

            protected void a(CommentItem commentItem) {
                c.a(this.a.a).a(c.h(this.a.a));
            }

            protected CommentItem a(String... strArr) {
                CommentItem commentItem;
                Exception e;
                String str = strArr[0];
                try {
                    if (c.ai(this.a.a) != -1) {
                        commentItem = (CommentItem) c.h(this.a.a).get(c.ai(this.a.a));
                    } else {
                        commentItem = null;
                    }
                    if (commentItem != null) {
                        try {
                            commentItem = z.a(str);
                            if (c.ai(this.a.a) == 0) {
                                commentItem.setIsnew(true);
                                commentItem.setTagIsShow(true);
                            }
                            c.b(this.a.a, commentItem);
                            c.h(this.a.a).remove(c.ai(this.a.a));
                            c.h(this.a.a).add(c.ai(this.a.a), commentItem);
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            return commentItem;
                        }
                    }
                } catch (Exception e3) {
                    Exception exception = e3;
                    commentItem = null;
                    e = exception;
                    e.printStackTrace();
                    return commentItem;
                }
                return commentItem;
            }
        }.execute(new String[]{str});
    }
}
