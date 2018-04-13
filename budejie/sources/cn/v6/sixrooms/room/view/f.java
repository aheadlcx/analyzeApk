package cn.v6.sixrooms.room.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class f implements OnItemClickListener {
    final /* synthetic */ MiniGameDialog a;

    f(MiniGameDialog miniGameDialog) {
        this.a = miniGameDialog;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.f.setSeclection(i);
        this.a.f.notifyDataSetChanged();
    }
}
