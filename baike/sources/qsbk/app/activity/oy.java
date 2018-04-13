package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.ToastAndDialog;

class oy implements OnItemClickListener {
    final /* synthetic */ ImagesPickerActivity a;

    oy(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.d.getItemViewType(i) == 0) {
            a aVar = (a) view.getTag();
            ImageInfo imageInfo = (ImageInfo) this.a.d.getItem(i);
            if (aVar.c.isChecked()) {
                aVar.c.setChecked(false);
                this.a.e.remove(imageInfo);
            } else if (this.a.e.size() >= ImagesPickerActivity.maxCount || this.a.e.contains(imageInfo)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("最多只能选取%s张图片哦", new Object[]{Integer.valueOf(ImagesPickerActivity.maxCount)}), Integer.valueOf(0)).show();
            } else if (imageInfo.isOverSize()) {
                aVar.c.setChecked(false);
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("图片不能大于%s", new Object[]{imageInfo.getMaxSize()}), Integer.valueOf(0)).show();
            } else if (ImageInfo.isUrlFile(imageInfo.getImageUrl())) {
                aVar.c.setChecked(true);
                this.a.e.add(imageInfo);
            } else {
                aVar.c.setChecked(false);
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("图片文件不存在%s", new Object[]{imageInfo.getMaxSize()}), Integer.valueOf(0)).show();
            }
            this.a.k.setText(this.a.e.size() + "");
            if (this.a.e.size() > 0) {
                this.a.m.setClickable(true);
                this.a.m.setTextColor(-1);
                this.a.m.setOnClickListener(new oz(this));
                return;
            }
            this.a.m.setClickable(false);
            this.a.m.setTextColor(-1184275);
        }
    }
}
