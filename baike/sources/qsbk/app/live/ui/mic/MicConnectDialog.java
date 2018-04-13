package qsbk.app.live.ui.mic;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.ye.videotools.utils.CameraDetection;
import qsbk.app.ye.videotools.utils.MicrophoneDetection;

public class MicConnectDialog extends BaseDialog implements OnClickListener {
    private Activity c;
    private MicConnectClickListener d;
    private TextView e;
    private TextView f;
    private ImageView g;

    public interface MicConnectClickListener {
        void micConnectClick(int i);
    }

    public MicConnectDialog(Activity activity, MicConnectClickListener micConnectClickListener) {
        super(activity);
        this.c = activity;
        this.d = micConnectClickListener;
    }

    protected int c() {
        return R.layout.live_mic_connect_dialog;
    }

    protected void d() {
        this.e = (TextView) a(R.id.btn_video);
        this.f = (TextView) a(R.id.btn_audio);
        this.g = (ImageView) a(R.id.iv_up);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
    }

    protected void e() {
        this.b.postDelayed(new a(this), 28000);
    }

    protected int a() {
        return 17;
    }

    protected boolean g() {
        return false;
    }

    protected float b() {
        return 0.0f;
    }

    public void onClick(View view) {
        if (this.d == null) {
            return;
        }
        if (view.getId() == R.id.btn_video) {
            if (VERSION.SDK_INT < 23) {
                boolean detect = MicrophoneDetection.detect();
                boolean detect2 = CameraDetection.detect();
                if (detect) {
                    if (!detect2) {
                        ToastUtil.Long("连麦需要您开启拍摄相关权限");
                    }
                } else if (detect2) {
                    ToastUtil.Long("连麦需要您开启录音相关权限");
                } else {
                    ToastUtil.Long("连麦需要您开启拍摄和录音相关权限");
                }
                b(3);
            } else if (PermissionChecker.checkSelfPermission(this.c, "android.permission.CAMERA") != 0) {
                ActivityCompat.requestPermissions(this.c, new String[]{"android.permission.CAMERA"}, 1002);
            } else if (PermissionChecker.checkSelfPermission(this.c, "android.permission.RECORD_AUDIO") != 0) {
                ActivityCompat.requestPermissions(this.c, new String[]{"android.permission.RECORD_AUDIO"}, 1001);
            } else {
                b(3);
            }
        } else if (view.getId() == R.id.btn_audio) {
            if (VERSION.SDK_INT < 23) {
                if (!MicrophoneDetection.detect()) {
                    ToastUtil.Long("连麦需要您开启录音相关权限");
                }
                b(2);
            } else if (PermissionChecker.checkSelfPermission(this.c, "android.permission.RECORD_AUDIO") != 0) {
                ActivityCompat.requestPermissions(this.c, new String[]{"android.permission.RECORD_AUDIO"}, 1001);
            } else {
                b(2);
            }
        } else if (view.getId() == R.id.iv_up) {
            b(4);
        }
    }

    protected void b(int i) {
        this.d.micConnectClick(i);
        this.d = null;
        dismiss();
    }

    public void dismiss() {
        if (this.d != null) {
            this.d.micConnectClick(4);
        }
        super.dismiss();
    }
}
