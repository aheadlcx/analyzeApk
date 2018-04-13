package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.NewFan;

class xm implements OnClickListener {
    final /* synthetic */ NewFansActivity a;

    xm(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onClick(View view) {
        NewFan newFan = (NewFan) NewFansActivity.c(this.a).get(NewFansActivity.b(this.a));
        switch (newFan.mSource.type) {
            case 2:
            case 3:
                Intent intent = new Intent(this.a, SingleArticle.class);
                intent.putExtra("article_id", String.valueOf(newFan.mSource.valueObj.artid));
                intent.putExtra("source", "only_article_id");
                this.a.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
