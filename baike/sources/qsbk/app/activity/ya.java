package qsbk.app.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.baidu.mobstat.Config;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.Article;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.ImageControlView.OnOperationSelectListener;

class ya implements OnOperationSelectListener {
    final /* synthetic */ NewImageViewer a;

    ya(NewImageViewer newImageViewer) {
        this.a = newImageViewer;
    }

    public void onSupportSelect(View view, boolean z) {
        Article g = this.a.b;
        g.vote_up++;
        if (z) {
            g = this.a.b;
            g.vote_down++;
        }
        this.a.a("up", this.a.b.id);
        this.a.g.setText(this.a.b.getDisplayLaugth() + "", true);
        QiushiArticleBus.updateArticle(this.a.b, null);
    }

    public void onUnSupportSelect(View view, boolean z) {
        Article g = this.a.b;
        g.vote_down--;
        if (z) {
            g = this.a.b;
            g.vote_up--;
        }
        this.a.a(Config.DEVICE_NAME, this.a.b.id);
        this.a.g.setText(this.a.b.getDisplayLaugth() + "", true);
        QiushiArticleBus.updateArticle(this.a.b, null);
    }

    public void onCommentSelect(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a, SingleArticle.class);
        intent.putExtra("article_id", this.a.l);
        intent.putExtra("article", this.a.b);
        intent.putExtra("showKeyboard", true);
        this.a.startActivity(intent);
        this.a.finish();
    }

    public void onShareSelect(View view) {
        boolean z = this.a.i.share != null && this.a.i.share.getTag().equals("active");
        ShareUtils.openShareDialog(this.a, 1, z, this.a.b);
    }

    public void onSaveSelect(View view) {
        if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现SD卡,保存失败！", Integer.valueOf(0)).show();
        } else if (this.a.e() && (view instanceof ImageView)) {
            ((ImageView) view).setImageResource(R.drawable.icon_save_active);
        }
    }
}
