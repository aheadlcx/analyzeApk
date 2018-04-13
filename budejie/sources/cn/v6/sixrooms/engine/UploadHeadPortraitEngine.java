package cn.v6.sixrooms.engine;

import android.graphics.Bitmap;
import cn.v6.sixrooms.bitmap.utils.FileHelper;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.alipay.sdk.sys.a;
import com.facebook.common.util.UriUtil;
import com.tencent.open.SocialConstants;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

public class UploadHeadPortraitEngine {
    protected static final String TAG = UploadHeadPortraitEngine.class.getSimpleName();
    private CallBack a;
    private double b = 1048576.0d;
    private double c = 550.0d;
    private double d = 980.0d;
    protected String padapi = "coop-mobile-uploadPic.php";

    public interface CallBack {
        void error(int i);

        void errorString(String str, String str2);

        void resultInfo(String str);
    }

    public UploadHeadPortraitEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void sendPic(String str, String str2, String str3) {
        sendBytePic(FileHelper.pathToByte(str, this.d, this.c, this.b), str2, str3);
    }

    public void sendPic(Bitmap bitmap, String str, String str2) {
        sendBytePic(FileHelper.bitmapToByte(bitmap, this.b), str, str2);
    }

    public void sendBytePic(byte[] bArr, String str, String str2) {
        Exception e;
        if (bArr == null) {
            this.a.error(CommonInts.PIC_UPLOADING_ERROR_CODE);
            return;
        }
        MultipartEntity multipartEntity = new MultipartEntity();
        try {
            multipartEntity.addPart(SocialConstants.PARAM_ACT, new StringBody("userPic"));
            multipartEntity.addPart(a.k, new StringBody("1.3"));
            multipartEntity.addPart("encpass", new StringBody(str));
            multipartEntity.addPart("logiuid", new StringBody(str2));
            multipartEntity.addPart(UriUtil.LOCAL_FILE_SCHEME, new ByteArrayBody(bArr, "image/jpeg", "tempHeadPortrait.jpg"));
            multipartEntity.addPart("type", new StringBody(UriUtil.LOCAL_FILE_SCHEME));
            NetworkServiceSingleton.createInstance().uploadFileOrPic(new bg(this), UrlStrs.URL_INDEX_INFO + "?padapi=" + this.padapi, multipartEntity);
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            this.a.error(CommonInts.PIC_COMPRESS_ERROR_CODE);
            e.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e = e3;
            this.a.error(CommonInts.PIC_COMPRESS_ERROR_CODE);
            e.printStackTrace();
        }
    }
}
