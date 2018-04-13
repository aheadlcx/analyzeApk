package qsbk.app.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

public class UserHeaderHelper {
    public static final int CAMERA_WITH_DATA = 3;
    public static final String KEY_CAPTURE_FILE_PATH = "CAPTURE_FILE_PATH";
    public static final int PHOTO_CROPPED_WITH_DATA = 2;
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    public static final int PHOTO_PICKED_WITH_DATA = 1;
    public final String KEY_TEMP_FILE = "temp_camera_file_name";
    private Activity a;
    private Bitmap b = null;
    public File mCapturedFile;
    public Uri mCropUri;
    public Uri mOutputCropUri;

    public static class UploadAvatarTask extends AsyncTask<String, Void, Pair<Integer, String>> {
        public static final int UPLOAD_FAIL = -1;
        public static final int UPLOAD_OK = 0;
        private String a = null;
        private String b;

        public UploadAvatarTask(String str) {
            this.b = str;
        }

        protected Pair<Integer, String> a(String... strArr) {
            try {
                JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().submit(Constants.UPDATE_AVATAR, new HashMap(), this.b));
                if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) != 0) {
                    return new Pair(Integer.valueOf(-1), jSONObject.getString("err_msg"));
                }
                QsbkApp.currentUser.userIcon = jSONObject.getString("icon");
                QsbkApp.getInstance().setCurrentUserToLocal();
                LogUtil.e("修改成功后，更改本地存储用户头像信息");
                return new Pair(Integer.valueOf(0), "头像上传成功");
            } catch (JSONException e) {
                return new Pair(Integer.valueOf(-1), "数据解析出错");
            } catch (Exception e2) {
                return new Pair(Integer.valueOf(-1), e2.getMessage());
            }
        }
    }

    public UserHeaderHelper(Activity activity, Bundle bundle) {
        this.a = activity;
        if (bundle != null) {
            Object string = bundle.getString("CAPTURE_FILE_PATH");
            if (!TextUtils.isEmpty(string)) {
                this.mCapturedFile = new File(string);
            }
        }
    }

    public static String getIconUrl(BaseUserInfo baseUserInfo) {
        if (baseUserInfo == null) {
            return null;
        }
        return QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
    }

    public static Intent getPhotoPickIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT", null);
        intent.setType("image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", PictureGetHelper.IMAGE_SIZE);
        intent.putExtra("outputY", PictureGetHelper.IMAGE_SIZE);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) == 0 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if (isExternalStorageDocument(uri)) {
            r1 = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT);
            if ("primary".equalsIgnoreCase(r1[0])) {
                return Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + r1[1];
            }
            return null;
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (!isMediaDocument(uri)) {
            return null;
        } else {
            Object obj = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT)[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String str = "_id=?";
            return getDataColumn(context, uri2, "_id=?", new String[]{r1[1]});
        }
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor cursor = null;
        String str2 = "_data";
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query == null) {
                            return str2;
                        }
                        query.close();
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public void onSaveInstanceState(Bundle bundle) {
        Object capturedFilePath = getCapturedFilePath();
        if (!TextUtils.isEmpty(capturedFilePath)) {
            bundle.putString("CAPTURE_FILE_PATH", capturedFilePath);
        }
    }

    private String a() {
        Date date = new java.sql.Date(System.currentTimeMillis());
        return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(date) + ".jpg";
    }

    private Bitmap a(Uri uri) {
        try {
            return BitmapFactory.decodeStream(this.a.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void doCropPhoto(Uri uri) {
        try {
            this.a.startActivityForResult(getCropImageIntent(uri), 2);
        } catch (Exception e) {
            ToastAndDialog.makeNegativeToast(this.a, "拍照出错了", Integer.valueOf(1)).show();
        }
    }

    public Intent getCropImageIntent(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        this.mCropUri = uri;
        if (VERSION.SDK_INT >= 19) {
            this.mCropUri = Uri.fromFile(new File(getPath(QsbkApp.getInstance(), uri)));
            intent.setDataAndType(this.mCropUri, "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", PictureGetHelper.IMAGE_SIZE);
        intent.putExtra("outputY", PictureGetHelper.IMAGE_SIZE);
        intent.putExtra("scale", true);
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        if (this.mCropUri != null) {
            String substring;
            Object sDPath = DeviceUtils.getSDPath();
            if (TextUtils.isEmpty(sDPath)) {
                substring = this.mCropUri.toString().substring("file://".length());
                int lastIndexOf = substring.lastIndexOf(".");
                substring = substring.substring(0, lastIndexOf) + "_crop" + substring.substring(lastIndexOf);
            } else {
                substring = sDPath + "/qsbk/crop.png";
            }
            this.mOutputCropUri = Uri.fromFile(new File(substring));
        }
        intent.putExtra("output", this.mOutputCropUri);
        return intent;
    }

    public void getPicFromContent() {
        try {
            this.a.startActivityForResult(getPhotoPickIntent(), 1);
        } catch (Exception e) {
            ToastAndDialog.makeNegativeToast(this.a, "错误", Integer.valueOf(1)).show();
        }
    }

    public void getPicFromCapture() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            PHOTO_DIR.mkdir();
            this.mCapturedFile = new File(PHOTO_DIR, a());
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(this.mCapturedFile));
            this.a.startActivityForResult(intent, 3);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.a, "没有sd卡", Integer.valueOf(1)).show();
    }

    public Bitmap getPickedBitmap() {
        return this.b;
    }

    public void doCropPhotoWithCaptured() {
        doCropPhoto(Uri.fromFile(this.mCapturedFile));
    }

    public String savePickedBitmap(Intent intent) {
        Bitmap a;
        String str;
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null || extras.get("data") == null) {
                if (intent.getData() != null) {
                    a = a(intent.getData());
                } else if (this.mOutputCropUri != null) {
                    a = a(this.mOutputCropUri);
                } else if (this.mCropUri != null) {
                    a = a(this.mCropUri);
                } else {
                    a = null;
                }
                if (a == null) {
                    return null;
                }
            }
            a = (Bitmap) extras.get("data");
        } else if (this.mOutputCropUri != null) {
            a = a(this.mOutputCropUri);
        } else if (this.mCropUri != null) {
            a = a(this.mCropUri);
        } else {
            a = null;
        }
        this.b = a;
        String str2 = this.a.getCacheDir() + "/medium";
        if (QsbkApp.currentUser != null) {
            str2 = FileUtils.saveDrawable(a, QsbkApp.currentUser.userId, str2, null, true);
            FileUtils.saveDrawable(a, QsbkApp.currentUser.userId, this.a.getCacheDir() + Constants.IMG_CACHE_PATH_AVATAR, null, true);
            str = str2;
        } else {
            str2 = FileUtils.saveDrawable(a, "avatar", str2, null, true);
            FileUtils.saveDrawable(a, "avatar", this.a.getCacheDir() + Constants.IMG_CACHE_PATH_AVATAR, null, true);
            str = str2;
        }
        return str;
    }

    public String getCapturedFilePath() {
        return this.mCapturedFile != null ? this.mCapturedFile.getAbsolutePath() : null;
    }
}
