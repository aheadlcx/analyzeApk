package qsbk.app.activity;

import android.widget.ImageView;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import cz.msebera.android.httpclient.HttpStatus;
import qsbk.app.R;

class UserGuideActivity$d extends UserGuideActivity$a {
    final /* synthetic */ UserGuideActivity b;
    public ImageView mLittles = ((ImageView) this.mView.findViewById(R.id.littles));
    public ImageView mMirror = ((ImageView) this.mView.findViewById(R.id.mirror));
    public ImageView mPaper = ((ImageView) this.mView.findViewById(R.id.paper));
    public ImageView mStinkpot = ((ImageView) this.mView.findViewById(R.id.stinkpot));
    public ImageView mTowel = ((ImageView) this.mView.findViewById(R.id.towel));

    public UserGuideActivity$d(UserGuideActivity userGuideActivity) {
        this.b = userGuideActivity;
        super(userGuideActivity, R.layout.layout_user_guide_3);
        invisibleViews();
    }

    public void invisibleViews() {
        this.mTowel.setVisibility(4);
        this.mStinkpot.setVisibility(4);
        this.mPaper.setVisibility(4);
        this.mMirror.setVisibility(4);
        this.mLittles.setVisibility(4);
    }

    public void visibleViews() {
        this.mTowel.setVisibility(0);
        this.mStinkpot.setVisibility(0);
        this.mPaper.setVisibility(0);
        this.mMirror.setVisibility(0);
        this.mLittles.setVisibility(0);
    }

    public void moveForwardIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = this.mScreenWidth - this.mTowel.getLeft();
        a(this.mTowel, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mStinkpot.getLeft();
        a(this.mStinkpot, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mPaper.getLeft();
        a(this.mPaper, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mMirror.getLeft();
        a(this.mMirror, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mLittles.getLeft();
        a(this.mLittles, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
    }

    public void moveForwardOut() {
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        iArr[0] = (-this.mTowel.getRight()) - 10;
        a(this.mTowel, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mStinkpot.getRight()) - 10;
        a(this.mStinkpot, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mPaper.getRight()) - 10;
        a(this.mPaper, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mMirror.getRight()) - 10;
        a(this.mMirror, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mLittles.getRight()) - 10;
        a(this.mLittles, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        this.mView.postDelayed(new aed(this), 415);
    }

    public void moveBackIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = (-this.mTowel.getRight()) - 10;
        a(this.mTowel, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mStinkpot.getRight()) - 10;
        a(this.mStinkpot, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mPaper.getRight()) - 10;
        a(this.mPaper, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mMirror.getRight()) - 10;
        a(this.mMirror, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mLittles.getRight()) - 10;
        a(this.mLittles, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    public void moveBackOut() {
    }
}
