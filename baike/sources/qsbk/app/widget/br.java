package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.SimpleWebActivity;

class br implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ EditorLinkView b;

    br(EditorLinkView editorLinkView, String str) {
        this.b = editorLinkView;
        this.a = str;
    }

    public void onClick(View view) {
        SimpleWebActivity.launch(this.b.getContext(), (String) EditorLinkView.a(this.b).get(this.a), false);
    }
}
