package qsbk.app.imagepicker;

import android.content.Context;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.CursorLoader;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.model.ImageInfo;

public class ImagePickerManager {
    private ArrayList<ImageFolderInfo> a;
    private int b = 0;
    private List<OnInitCompletedListener> c = new ArrayList();

    public interface OnInitCompletedListener {
        void onCompleted();
    }

    private ImagePickerManager() {
    }

    public static ImagePickerManager newInstance() {
        return new ImagePickerManager();
    }

    public void reset() {
        if (this.b == 2) {
            this.b = 0;
        }
    }

    public void init(Context context, OnInitCompletedListener onInitCompletedListener) {
        if (this.b != 2) {
            if (onInitCompletedListener != null) {
                this.c.add(onInitCompletedListener);
            }
            if (this.b != 1) {
                this.b = 1;
                Context context2 = context;
                CursorLoader cursorLoader = new CursorLoader(context2, Media.EXTERNAL_CONTENT_URI, new String[]{"bucket_id", "bucket_display_name", FileDownloadModel.ID, "_data", "date_modified", "_size", "mime_type", IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT}, null, null, "date_modified DESC");
                cursorLoader.registerListener(0, new a(this));
                cursorLoader.startLoading();
            }
        } else if (onInitCompletedListener != null) {
            onInitCompletedListener.onCompleted();
        }
    }

    public ArrayList<ImageFolderInfo> getImageFolders() {
        return this.a;
    }

    public ImageFolderInfo getImageFolder(int i) {
        return (ImageFolderInfo) this.a.get(i);
    }

    public ImageInfo getImageById(int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ImageInfo byId = ((ImageFolderInfo) it.next()).getById(i);
            if (byId != null) {
                return byId;
            }
        }
        return null;
    }

    public ImageInfo getImageByPath(String str) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ImageInfo byPath = ((ImageFolderInfo) it.next()).getByPath(str);
            if (byPath != null) {
                return byPath;
            }
        }
        return null;
    }

    public int getCount() {
        return this.a.size();
    }
}
