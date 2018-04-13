package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class pa implements OnItemClickListener {
    final /* synthetic */ ImagesPickerActivity a;

    pa(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.j();
        this.a.a(i);
    }
}
