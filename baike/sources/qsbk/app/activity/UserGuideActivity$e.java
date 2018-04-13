package qsbk.app.activity;

import android.widget.ImageView;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import cz.msebera.android.httpclient.HttpStatus;
import qsbk.app.R;
import qsbk.app.utils.gif.GifView;

class UserGuideActivity$e extends UserGuideActivity$a {
    final /* synthetic */ UserGuideActivity b;
    public ImageView mBed = ((ImageView) this.mView.findViewById(R.id.bed));
    public ImageView mCabinet = ((ImageView) this.mView.findViewById(R.id.cabinet));
    public ImageView mQuilt = ((ImageView) this.mView.findViewById(R.id.quilt));
    public ImageView mRollPaper = ((ImageView) this.mView.findViewById(R.id.roll_paper));
    public GifView mShit = ((GifView) this.mView.findViewById(R.id.sleep_shit));
    public ImageView mWindow = ((ImageView) this.mView.findViewById(R.id.window));

    public UserGuideActivity$e(UserGuideActivity userGuideActivity) {
        this.b = userGuideActivity;
        super(userGuideActivity, R.layout.layout_user_guide_4);
        this.mShit.setGifImage((int) R.drawable.user_guide_shit);
        invisibleViews();
    }

    public void invisibleViews() {
        this.mWindow.setVisibility(4);
        this.mBed.setVisibility(4);
        this.mCabinet.setVisibility(4);
        this.mRollPaper.setVisibility(4);
        this.mQuilt.setVisibility(4);
        this.mShit.setVisibility(8);
    }

    public void visibleViews() {
        this.mWindow.setVisibility(0);
        this.mBed.setVisibility(0);
        this.mCabinet.setVisibility(0);
        this.mRollPaper.setVisibility(0);
        this.mQuilt.setVisibility(0);
    }

    public void moveForwardIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = this.mScreenWidth - this.mWindow.getLeft();
        a(this.mWindow, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mBed.getLeft();
        a(this.mBed, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mCabinet.getLeft();
        a(this.mCabinet, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mRollPaper.getLeft();
        a(this.mRollPaper, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mQuilt.getLeft();
        a(this.mQuilt, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        this.mShit.setVisibility(0);
    }

    public void moveForwardOut() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        iArr[0] = (-this.mWindow.getRight()) - 10;
        a(this.mWindow, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mBed.getRight()) - 10;
        a(this.mBed, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mCabinet.getRight()) - 10;
        a(this.mCabinet, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mRollPaper.getRight()) - 10;
        a(this.mRollPaper, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mQuilt.getRight()) - 10;
        a(this.mQuilt, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        this.mView.postDelayed(new aee(this), 415);
    }

    public void moveBackIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = (-this.mWindow.getRight()) - 10;
        a(this.mWindow, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mBed.getRight()) - 10;
        a(this.mBed, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mCabinet.getRight()) - 10;
        a(this.mCabinet, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mRollPaper.getRight()) - 10;
        a(this.mRollPaper, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mQuilt.getRight()) - 10;
        a(this.mQuilt, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    public void moveBackOut() {
    }
}
