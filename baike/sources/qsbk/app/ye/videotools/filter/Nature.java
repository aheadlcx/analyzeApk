package qsbk.app.ye.videotools.filter;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.baidu.location.BDLocation;
import com.facebook.imageutils.JfifUtil;
import cz.msebera.android.httpclient.HttpStatus;
import io.agora.rtc.Constants;
import qsbk.app.BuildConfig;
import qsbk.app.activity.ApplyForGroupActivity;
import qsbk.app.activity.ApplyForOwnerActivity;
import qsbk.app.activity.GroupNoticeDetailActivity;
import qsbk.app.api.BigCoverHelper;
import qsbk.app.cafe.plugin.SharePlugin;
import qsbk.app.core.web.plugin.embed.ChargePlugin;

public class Nature extends VideoFilter {
    public static final int[] mGreenColorMap = new int[]{0, 1, 3, 4, 5, 7, 8, 9, 11, 12, 13, 15, 16, 17, 19, 20, 21, 23, 24, 25, 27, 28, 29, 31, 32, 33, 35, 36, 37, 39, 40, 41, 43, 44, 45, 47, 48, 49, 51, 52, 53, 55, 56, 57, 59, 60, 61, 62, 64, 65, 66, 68, 69, 70, 72, 73, 74, 75, 77, 78, 79, 81, 82, 83, 84, 86, 87, 88, 89, 91, 92, 93, 94, 96, 97, 98, 99, 101, 102, 103, 104, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 118, Constants.WARN_SET_CLIENT_ROLE_NOT_AUTHORIZED, 120, 121, 122, 123, 125, 126, 127, 128, 129, 130, 132, SharePlugin.SHARE_REQ, 134, 135, 136, 137, 138, 139, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, ApplyForGroupActivity.REQ_APPLY, ApplyForOwnerActivity.REQ_APPLY, GroupNoticeDetailActivity.REQ_CODE, 159, BigCoverHelper.REQCODE_CAREMA, 161, 162, 163, 164, 165, 166, BDLocation.TypeServerError, 168, 169, 170, 171, 172, 173, 174, 175, 176, BuildConfig.VERSION_CODE, 178, 179, 180, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 189, 190, 191, JfifUtil.MARKER_SOFn, 193, 194, 194, 195, 196, 197, 198, ChargePlugin.CHAREG_CODE, ChargePlugin.CHAREG_CODE, 200, 201, 202, 203, 203, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_RESET_CONTENT, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_MULTI_STATUS, JfifUtil.MARKER_RST0, 209, 209, 210, 211, 212, 212, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, JfifUtil.MARKER_EOI, JfifUtil.MARKER_EOI, JfifUtil.MARKER_SOS, 219, 220, 220, 221, 222, 222, 223, 224, 224, JfifUtil.MARKER_APP1, 226, 226, 227, 228, 228, 229, 230, 230, 231, 232, 232, 233, 233, 234, 235, 235, 236, 237, 237, 238, 239, 239, 240, 240, 241, 242, 242, 243, 243, 244, 245, 245, 246, 247, 247, 248, 248, 249, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 251, 251, 252, 253, 253, 254, 254, 255};
    public static final int[] mRedColorMap = new int[]{0, 1, 3, 4, 5, 7, 8, 9, 11, 13, 15, 16, 17, 19, 20, 21, 23, 24, 25, 27, 28, 29, 31, 32, 33, 35, 36, 39, 40, 41, 43, 44, 45, 47, 48, 49, 51, 52, 53, 55, 56, 57, 59, 60, 61, 62, 65, 66, 68, 69, 70, 72, 73, 74, 75, 77, 78, 79, 81, 82, 83, 84, 86, 87, 88, 89, 91, 93, 94, 96, 97, 98, 99, 101, 102, 103, 104, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 118, Constants.WARN_SET_CLIENT_ROLE_NOT_AUTHORIZED, 120, 121, 122, 123, 125, 126, 128, 129, 130, 132, SharePlugin.SHARE_REQ, 134, 135, 136, 137, 138, 139, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, ApplyForGroupActivity.REQ_APPLY, ApplyForOwnerActivity.REQ_APPLY, GroupNoticeDetailActivity.REQ_CODE, 159, BigCoverHelper.REQCODE_CAREMA, 161, 162, 163, 164, 165, 166, BDLocation.TypeServerError, 168, 169, 170, 171, 172, 173, 174, 175, 176, BuildConfig.VERSION_CODE, 178, 179, 180, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 189, 190, 191, JfifUtil.MARKER_SOFn, 193, 194, 194, 195, 196, 197, 197, 198, ChargePlugin.CHAREG_CODE, ChargePlugin.CHAREG_CODE, 200, 201, 202, 203, 203, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_RESET_CONTENT, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_MULTI_STATUS, JfifUtil.MARKER_RST0, 209, 209, 210, 211, 212, 212, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, JfifUtil.MARKER_EOI, JfifUtil.MARKER_EOI, JfifUtil.MARKER_EOI, JfifUtil.MARKER_SOS, 219, 220, 220, 221, 222, 222, 223, 224, 224, JfifUtil.MARKER_APP1, 226, 226, 227, 228, 228, 229, 230, 230, 230, 231, 232, 232, 233, 233, 234, 235, 235, 236, 237, 237, 238, 239, 239, 240, 240, 240, 241, 242, 242, 243, 243, 244, 245, 245, 246, 247, 247, 248, 248, 249, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 251, 251, 252, 253, 253, 254, 254, 255};

    public Nature(Context context) {
        this.type = 1;
        this.mWidth1 = 256;
        this.mHeight1 = 1;
        this.mTexture1 = new int[(this.mWidth1 * this.mHeight1)];
        for (int i = 0; i < 256; i++) {
            this.mTexture1[i] = (((mRedColorMap[i] << 16) | (mGreenColorMap[i] << 8)) | mGreenColorMap[i]) | -16777216;
        }
    }
}
