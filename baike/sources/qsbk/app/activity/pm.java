package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.ImageInfo;

class pm implements OnItemClickListener {
    final /* synthetic */ ImagesPickerForCollectActivity a;

    pm(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
        this.a = imagesPickerForCollectActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.d.getItemViewType(i) == 0) {
            a aVar = (a) view.getTag();
            ImageInfo imageInfo = (ImageInfo) this.a.d.getItem(i);
        }
    }
}
