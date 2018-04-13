package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import qsbk.app.video.VideoPlayerView;

class v implements OnDismissListener {
    final /* synthetic */ VideoPlayerView a;
    final /* synthetic */ ArticleMoreOperationbar b;

    v(ArticleMoreOperationbar articleMoreOperationbar, VideoPlayerView videoPlayerView) {
        this.b = articleMoreOperationbar;
        this.a = videoPlayerView;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.play();
    }
}
