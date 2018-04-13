package qsbk.app.adapter;

import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.Article;
import qsbk.app.utils.RemarkManager;

class y implements OnClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ int b;
    final /* synthetic */ ArticleAdapter c;

    y(ArticleAdapter articleAdapter, Article article, int i) {
        this.c = articleAdapter;
        this.a = article;
        this.b = i;
    }

    public void onClick(View view) {
        CharSequence remark = RemarkManager.getRemark(this.a.user_id);
        CharSequence[] charSequenceArr;
        if (this.a.hasQiushiTopic()) {
            if (this.a.anonymous) {
                charSequenceArr = new String[2];
                charSequenceArr[0] = "糗事不好笑";
                charSequenceArr[1] = String.format("不喜欢爆社 #%s", new Object[]{this.a.qiushiTopic.content});
            } else {
                r1 = new String[3];
                r1[1] = String.format("不喜欢爆社 #%s", new Object[]{this.a.qiushiTopic.content});
                String str = "不喜欢 %s 的糗事";
                Object[] objArr = new Object[1];
                if (TextUtils.isEmpty(remark)) {
                    remark = this.a.login;
                }
                objArr[0] = remark;
                r1[2] = String.format(str, objArr);
                Object[] objArr2 = r1;
            }
            new Builder(this.c.k).setItems(charSequenceArr, new z(this)).create().show();
            return;
        }
        if (this.a.anonymous) {
            charSequenceArr = new String[]{"糗事不好笑"};
        } else {
            r1 = new String[2];
            r1[0] = "糗事不好笑";
            str = "不喜欢 %s 的糗事";
            objArr = new Object[1];
            if (TextUtils.isEmpty(remark)) {
                remark = this.a.login;
            }
            objArr[0] = remark;
            r1[1] = String.format(str, objArr);
            objArr2 = r1;
        }
        new Builder(this.c.k).setItems(charSequenceArr, new aa(this)).create().show();
    }
}
