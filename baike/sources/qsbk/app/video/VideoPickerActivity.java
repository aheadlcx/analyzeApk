package qsbk.app.video;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.GridView;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityListener;
import qsbk.app.utils.ToastAndDialog;

@TargetApi(16)
public class VideoPickerActivity extends BaseActionBarActivity {
    public static final int REQUEST_VIDEO_PICK = 4097;
    private static final String a = VideoPickerActivity.class.getSimpleName();
    private ResultActivityListener b = new bb(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launchForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, VideoPickerActivity.class), 4097);
    }

    protected String f() {
        return "本地视频选择";
    }

    protected int a() {
        return R.layout.activity_video_picker;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        j();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void i() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("video/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            startActivityForResult(Intent.createChooser(intent, "选择视频"), 0);
        } catch (ActivityNotFoundException e) {
            ToastAndDialog.makeNegativeToast(this, "Please install a File Manager.", Integer.valueOf(0)).show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (i2 == -1) {
                    Uri data = intent.getData();
                    LogUtil.d("File Uri: " + data.toString());
                    try {
                        String path = FileUtils.getPath(this, data);
                        LogUtil.d("File Path: " + path);
                        if (path != null) {
                            Object obj;
                            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                            try {
                                mediaMetadataRetriever.setDataSource(path);
                                obj = !TextUtils.isEmpty(mediaMetadataRetriever.extractMetadata(17)) ? 1 : null;
                                if (obj != null) {
                                    int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
                                    int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                                    int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                                    if (parseInt < 3000) {
                                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请选择长度大于3秒的视频文件", Integer.valueOf(0)).show();
                                    } else {
                                        ImageInfo imageInfo = new ImageInfo(UriUtil.getUriForFile(new File(path)).toString(), MediaFormat.VIDEO);
                                        imageInfo.width = parseInt2;
                                        imageInfo.height = parseInt3;
                                        VideoEditActivity.launchForResult(this, imageInfo);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                obj = null;
                            }
                            if (obj == null) {
                                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您选择的不是合法的视频文件", Integer.valueOf(0)).show();
                                break;
                            }
                        }
                        return;
                    } catch (Exception e2) {
                        break;
                    }
                }
                break;
            case 1:
                if (i2 == -1) {
                    setResult(-1, intent);
                    finish();
                    break;
                }
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_select:
                i();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void j() {
        GridView gridView = (GridView) findViewById(R.id.gridview);
        CursorAdapter bcVar = new bc(this, this, k());
        gridView.setAdapter(bcVar);
        bcVar.notifyDataSetChanged();
        gridView.setOnItemClickListener(new bd(this, bcVar));
    }

    public void startVideo() {
        a(new Intent(this, VideoRecordActivity.class), this.b);
    }

    private Cursor k() {
        String[] strArr = new String[]{FileDownloadModel.ID, "_data", "title", "mime_type", "_size", "duration", IndexEntry.COLUMN_NAME_HEIGHT, IndexEntry.COLUMN_NAME_WIDTH, "_display_name"};
        Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, "mime_type='video/mp4' AND duration>=3000 AND _size>50000", null, "date_added DESC");
        new MatrixCursor(strArr).addRow(new Object[]{Integer.valueOf(-2147483647), "", "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), ""});
        return new MergeCursor(new Cursor[]{r1, query});
    }

    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
