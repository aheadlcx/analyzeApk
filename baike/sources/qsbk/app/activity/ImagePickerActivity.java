package qsbk.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

@Deprecated
public class ImagePickerActivity extends FragmentActivity {
    public static final int EXTRA_ALBUM = 2;
    public static final int EXTRA_TAKE_PHOTO = 1;
    public static final String KEY_ACTION = "action";
    private static final String a = ImagePickerActivity.class.getSimpleName();
    private Uri b = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        try {
            int intExtra = intent.getIntExtra("action", -1);
            if (intExtra == -1) {
                Log.e(a, "Action not set, finish automaticlly");
                setResult(0);
                finish();
            }
            switch (intExtra) {
                case 1:
                    this.b = (Uri) intent.getParcelableExtra("output");
                    if (this.b == null) {
                        Log.e(a, "Extra uri should be set by key MediaStore.EXTRA_OUTPUT when action is " + intExtra);
                        finish();
                    }
                    intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra("output", this.b);
                    startActivityForResult(intent, 1);
                    return;
                case 2:
                    intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    intent.setType("image/*");
                    startActivityForResult(intent, 2);
                    return;
                default:
                    Log.e(a, "Unknow action " + intExtra);
                    finish();
                    return;
            }
        } catch (Throwable th) {
            setResult(0);
            finish();
        }
        setResult(0);
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != -1) {
            setResult(0);
            finish();
            return;
        }
        Intent intent2;
        switch (i) {
            case 1:
                intent2 = new Intent();
                intent2.putExtra("output", this.b);
                setResult(-1, intent2);
                return;
            case 2:
                intent2 = new Intent();
                intent2.setData(intent.getData());
                setResult(-1, intent2);
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }
}
