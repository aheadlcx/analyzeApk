package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.CheckedImageView;
import qsbk.app.widget.CheckedImageView.OnCheckedChangeListener;

class pk implements OnCheckedChangeListener {
    final /* synthetic */ a a;
    final /* synthetic */ c b;

    pk(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public void onCheckedChange(CheckedImageView checkedImageView, boolean z, boolean z2) {
        if (z2 && this.b.a.d.getItemViewType(this.a.a) == 0) {
            ImageInfo imageInfo = (ImageInfo) this.b.a.d.getItem(((a) checkedImageView.getTag()).a);
            if (!checkedImageView.isChecked()) {
                this.b.a.e.remove(imageInfo);
            } else if (this.b.a.e.size() >= ImagesPickerActivity.maxCount || this.b.a.e.contains(imageInfo)) {
                checkedImageView.setChecked(false);
                ToastAndDialog.makeNegativeToast(checkedImageView.getContext(), String.format("最多只能选取%s张图片哦", new Object[]{Integer.valueOf(ImagesPickerActivity.maxCount)}), Integer.valueOf(0)).show();
            } else if (imageInfo.isOverSize()) {
                checkedImageView.setChecked(false);
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("图片不能大于%s", new Object[]{imageInfo.getMaxSize()})).show();
            } else {
                this.b.a.e.add(imageInfo);
            }
            this.b.a.k.setText(this.b.a.e.size() + "");
            if (this.b.a.e.size() > 0) {
                this.b.a.m.setClickable(true);
                this.b.a.m.setTextColor(-1);
                this.b.a.m.setOnClickListener(new pl(this));
                return;
            }
            this.b.a.m.setClickable(false);
            this.b.a.m.setTextColor(-1184275);
        }
    }
}
