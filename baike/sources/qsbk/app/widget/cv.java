package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.UIHelper;

class cv implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ PersonalInfoView b;

    cv(PersonalInfoView personalInfoView, String str) {
        this.b = personalInfoView;
        this.a = str;
    }

    public void onClick(View view) {
        ImageViewer.launch(view.getContext(), new ImageInfo(this.a, MediaFormat.IMAGE_STATIC), UIHelper.getRectOnScreen(PersonalInfoView.a(this.b)));
    }
}
