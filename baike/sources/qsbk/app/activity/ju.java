package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ju implements OnItemClickListener {
    final /* synthetic */ EditBirthActivity a;

    ju(EditBirthActivity editBirthActivity) {
        this.a = editBirthActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a();
    }
}
