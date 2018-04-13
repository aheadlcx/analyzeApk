package qsbk.app.activity;

import android.widget.ImageView;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import cz.msebera.android.httpclient.HttpStatus;
import qsbk.app.R;

class UserGuideActivity$c extends UserGuideActivity$a {
    final /* synthetic */ UserGuideActivity b;
    public ImageView mLittle1 = ((ImageView) this.mView.findViewById(R.id.little_1));
    public ImageView mRingpull = ((ImageView) this.mView.findViewById(R.id.ringpull));
    public ImageView mSeat = ((ImageView) this.mView.findViewById(R.id.seat));
    public ImageView mSmallBlack = ((ImageView) this.mView.findViewById(R.id.small_balck));
    public ImageView mSmallYellow = ((ImageView) this.mView.findViewById(R.id.small_yellow));

    public UserGuideActivity$c(UserGuideActivity userGuideActivity) {
        this.b = userGuideActivity;
        super(userGuideActivity, R.layout.layout_user_guide_2);
        invisibleViews();
    }

    public void invisibleViews() {
        this.mRingpull.setVisibility(4);
        this.mSeat.setVisibility(4);
        this.mSmallYellow.setVisibility(4);
        this.mSmallBlack.setVisibility(4);
        this.mLittle1.setVisibility(4);
    }

    public void visibleViews() {
        this.mRingpull.setVisibility(0);
        this.mSeat.setVisibility(0);
        this.mSmallYellow.setVisibility(0);
        this.mSmallBlack.setVisibility(0);
        this.mLittle1.setVisibility(0);
    }

    public void moveForwardIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = this.mScreenWidth - this.mRingpull.getLeft();
        a(this.mRingpull, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mSeat.getLeft();
        a(this.mSeat, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mSmallYellow.getLeft();
        a(this.mSmallYellow, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mSmallBlack.getLeft();
        a(this.mSmallBlack, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mLittle1.getLeft();
        a(this.mLittle1, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
    }

    public void moveForwardOut() {
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        iArr[0] = (-this.mRingpull.getRight()) - 10;
        a(this.mRingpull, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mSeat.getRight()) - 10;
        a(this.mSeat, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mSmallYellow.getRight()) - 10;
        a(this.mSmallYellow, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mSmallBlack.getRight()) - 10;
        a(this.mSmallBlack, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mLittle1.getRight()) - 10;
        a(this.mLittle1, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        this.mView.postDelayed(new aec(this), 415);
    }

    public void moveBackIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = (-this.mRingpull.getRight()) - 10;
        a(this.mRingpull, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mSeat.getRight()) - 10;
        a(this.mSeat, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mSmallYellow.getRight()) - 10;
        a(this.mSmallYellow, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mSmallBlack.getRight()) - 10;
        a(this.mSmallBlack, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mLittle1.getRight()) - 10;
        a(this.mLittle1, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    public void moveBackOut() {
    }
}
