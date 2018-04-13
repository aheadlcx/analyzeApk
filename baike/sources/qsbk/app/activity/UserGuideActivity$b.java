package qsbk.app.activity;

import android.widget.ImageView;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import cz.msebera.android.httpclient.HttpStatus;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;

class UserGuideActivity$b extends UserGuideActivity$a {
    final /* synthetic */ UserGuideActivity b;
    public ImageView mAdBoard = ((ImageView) this.mView.findViewById(R.id.ad_board));
    public ImageView mLittle2 = ((ImageView) this.mView.findViewById(R.id.little_2));
    public ImageView mStationBoard = ((ImageView) this.mView.findViewById(R.id.station_board));
    public ImageView mStool = ((ImageView) this.mView.findViewById(R.id.stool));
    public ImageView mTotoro = ((ImageView) this.mView.findViewById(R.id.totoro));

    public UserGuideActivity$b(UserGuideActivity userGuideActivity) {
        this.b = userGuideActivity;
        super(userGuideActivity, R.layout.layout_user_guide_1);
    }

    public void moveForwardIn() {
        DebugUtil.debug(UserGuideActivity.a(), "move in");
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = this.mScreenWidth - this.mAdBoard.getLeft();
        a(this.mAdBoard, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mStationBoard.getLeft();
        a(this.mStationBoard, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mTotoro.getLeft();
        a(this.mTotoro, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mLittle2.getLeft();
        a(this.mLittle2, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        r0[0] = this.mScreenWidth - this.mStool.getLeft();
        a(this.mStool, r0, iArr, ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
    }

    public void moveForwardOut() {
        DebugUtil.debug(UserGuideActivity.a(), "move off");
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        iArr[0] = (-this.mAdBoard.getRight()) - 10;
        a(this.mAdBoard, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mStationBoard.getRight()) - 10;
        a(this.mStationBoard, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mTotoro.getRight()) - 10;
        a(this.mTotoro, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mLittle2.getRight()) - 10;
        a(this.mLittle2, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        iArr[0] = (-this.mStool.getRight()) - 10;
        a(this.mStool, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        this.mView.postDelayed(new aeb(this), 415);
    }

    public void invisibleViews() {
        this.mAdBoard.setVisibility(4);
        this.mStationBoard.setVisibility(4);
        this.mTotoro.setVisibility(4);
        this.mLittle2.setVisibility(4);
        this.mStool.setVisibility(4);
    }

    public void visibleViews() {
        this.mAdBoard.setVisibility(0);
        this.mStationBoard.setVisibility(0);
        this.mTotoro.setVisibility(0);
        this.mLittle2.setVisibility(0);
        this.mStool.setVisibility(0);
    }

    public void moveBackIn() {
        visibleViews();
        r0 = new int[2];
        int[] iArr = new int[]{0, 0};
        iArr[1] = 0;
        r0[0] = (-this.mAdBoard.getRight()) - 10;
        a(this.mAdBoard, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mStationBoard.getRight()) - 10;
        a(this.mStationBoard, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mTotoro.getRight()) - 10;
        a(this.mTotoro, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mLittle2.getRight()) - 10;
        a(this.mLittle2, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        r0[0] = (-this.mStool.getRight()) - 10;
        a(this.mStool, r0, iArr, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    public void moveBackOut() {
    }
}
