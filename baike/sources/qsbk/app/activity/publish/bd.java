package qsbk.app.activity.publish;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;

class bd implements TextWatcher {
    final /* synthetic */ PublishActivity a;

    bd(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int i = 0;
        Object qiushiContent = this.a.b.getQiushiContent();
        int length = TextUtils.isEmpty(qiushiContent) ? 0 : qiushiContent.trim().length();
        ImageView imageView = this.a.a;
        if (length >= 5) {
            i = 1;
        }
        imageView.setImageLevel(i);
        this.a.I();
        this.a.G = this.a.b.getTopic();
    }
}
