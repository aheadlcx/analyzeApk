package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.SingleArticle;

class h implements OnClickListener {
    final /* synthetic */ IMChatMsgSource a;
    final /* synthetic */ ChatListAdapter b;

    h(ChatListAdapter chatListAdapter, IMChatMsgSource iMChatMsgSource) {
        this.b = chatListAdapter;
        this.a = iMChatMsgSource;
    }

    public void onClick(View view) {
        if (this.a.type != 1) {
            if (this.a.type == 3 || this.a.type == 2) {
                Intent intent = new Intent(this.b.h, SingleArticle.class);
                intent.putExtra("article_id", String.valueOf(this.a.valueObj.artid));
                intent.putExtra("source", "only_article_id");
                this.b.h.startActivity(intent);
            }
        }
    }
}
