package qsbk.app.imagepicker;

import android.database.Cursor;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.imagepicker.ImagePickerManager.OnInitCompletedListener;
import qsbk.app.model.ImageInfo;

class b extends Thread {
    final /* synthetic */ Cursor a;
    final /* synthetic */ a b;

    b(a aVar, Cursor cursor) {
        this.b = aVar;
        this.a = cursor;
    }

    public void run() {
        super.run();
        ArrayList arrayList = new ArrayList();
        if (this.a != null) {
            this.b.a.b = 2;
            ImageFolderInfo imageFolderInfo = new ImageFolderInfo(-1, "相册");
            arrayList.add(imageFolderInfo);
            while (this.a.moveToNext() && !this.a.isClosed()) {
                int i = this.a.getInt(0);
                String string = this.a.getString(this.a.getColumnIndex("bucket_display_name"));
                int i2 = this.a.getInt(this.a.getColumnIndex(FileDownloadModel.ID));
                Object string2 = this.a.getString(this.a.getColumnIndex("_data"));
                int i3 = this.a.getInt(this.a.getColumnIndex("_size"));
                String string3 = this.a.getString(this.a.getColumnIndex("mime_type"));
                int i4 = this.a.getInt(this.a.getColumnIndex(IndexEntry.COLUMN_NAME_WIDTH));
                int i5 = this.a.getInt(this.a.getColumnIndex(IndexEntry.COLUMN_NAME_HEIGHT));
                if (!TextUtils.isEmpty(string2)) {
                    ImageFolderInfo imageFolderInfo2;
                    ImageFolderInfo imageFolderInfo3;
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        imageFolderInfo2 = (ImageFolderInfo) it.next();
                        if (imageFolderInfo2.getId() == i) {
                            break;
                        }
                    }
                    imageFolderInfo2 = null;
                    if (imageFolderInfo2 == null) {
                        imageFolderInfo2 = new ImageFolderInfo(i, string);
                        arrayList.add(imageFolderInfo2);
                        imageFolderInfo3 = imageFolderInfo2;
                    } else {
                        imageFolderInfo3 = imageFolderInfo2;
                    }
                    ImageInfo imageInfo = new ImageInfo(i2, UriUtil.getUriForFile(new File(string2)).toString(), string3, i4, i5);
                    imageInfo.size = i3;
                    imageFolderInfo3.addImage(imageInfo);
                    if (i3 > 16384) {
                        imageFolderInfo.addImage(imageInfo);
                    }
                }
            }
            this.b.a.a = arrayList;
            for (OnInitCompletedListener onCompleted : this.b.a.c) {
                onCompleted.onCompleted();
            }
            this.b.a.c.clear();
        }
    }
}
