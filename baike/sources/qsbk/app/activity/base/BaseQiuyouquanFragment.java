package qsbk.app.activity.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.widget.ArticleMoreOperationbar;

public class BaseQiuyouquanFragment extends BaseFragment implements ShareUtils$OnCircleShareStartListener {
    public void showMoreOperationActivity(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle != null) {
                if (i2 == 12) {
                    downloadArticle(circleArticle);
                } else {
                    ArticleMoreOperationbar.handleShare(i2, (Fragment) this, circleArticle);
                }
            }
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        showMoreOperationActivity(circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        if (ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY.equals(str)) {
            ArticleMoreOperationbar.showOperation(getActivity(), circleArticle);
        } else {
            ShareUtils.openShareDialog(this, 1, circleArticle, str, view);
        }
    }

    public void downloadArticle(CircleArticle circleArticle) {
    }
}
