package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.R;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;

class afg implements OnItemLongClickListener {
    final /* synthetic */ VideoImmersionActivity a;

    afg(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.n.getAdapter().getItem(i) instanceof Article) {
            if (!UIHelper.isNightTheme()) {
                View findViewById = view.findViewById(R.id.layerMask);
                if (findViewById != null) {
                    findViewById.setVisibility(0);
                }
            }
            this.a.c = view;
            this.a.b = (Article) this.a.n.getAdapter().getItem(i);
            if (view.getTag() != null) {
                this.a.d = ((VideoImmersionCell) view.getTag()).collection_icon;
            }
            this.a.j();
        }
        return true;
    }
}
