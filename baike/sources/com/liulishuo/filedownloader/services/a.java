package com.liulishuo.filedownloader.services;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class a implements FileDownloadDatabase {
    public static final String TABLE_NAME = "filedownloader";
    private final SQLiteDatabase a = new b(FileDownloadHelper.getAppContext()).getWritableDatabase();
    private final SparseArray<FileDownloadModel> b = new SparseArray();

    public a() {
        a();
    }

    private void a() {
        String targetFilePath;
        long currentTimeMillis = System.currentTimeMillis();
        Cursor rawQuery = this.a.rawQuery("SELECT * FROM filedownloader", null);
        Object arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            try {
                FileDownloadModel fileDownloadModel = new FileDownloadModel();
                fileDownloadModel.setId(rawQuery.getInt(rawQuery.getColumnIndex(FileDownloadModel.ID)));
                fileDownloadModel.setUrl(rawQuery.getString(rawQuery.getColumnIndex("url")));
                fileDownloadModel.setPath(rawQuery.getString(rawQuery.getColumnIndex("path")), rawQuery.getShort(rawQuery.getColumnIndex(FileDownloadModel.PATH_AS_DIRECTORY)) == (short) 1);
                fileDownloadModel.setStatus((byte) rawQuery.getShort(rawQuery.getColumnIndex("status")));
                fileDownloadModel.setSoFar(rawQuery.getLong(rawQuery.getColumnIndex(FileDownloadModel.SOFAR)));
                fileDownloadModel.setTotal(rawQuery.getLong(rawQuery.getColumnIndex("total")));
                fileDownloadModel.setErrMsg(rawQuery.getString(rawQuery.getColumnIndex(FileDownloadModel.ERR_MSG)));
                fileDownloadModel.setETag(rawQuery.getString(rawQuery.getColumnIndex("etag")));
                fileDownloadModel.setFilename(rawQuery.getString(rawQuery.getColumnIndex(FileDownloadModel.FILENAME)));
                if (fileDownloadModel.getStatus() == (byte) 3 || fileDownloadModel.getStatus() == (byte) 2 || fileDownloadModel.getStatus() == (byte) -1 || (fileDownloadModel.getStatus() == (byte) 1 && fileDownloadModel.getSoFar() > 0)) {
                    fileDownloadModel.setStatus((byte) -2);
                }
                targetFilePath = fileDownloadModel.getTargetFilePath();
                if (targetFilePath == null) {
                    arrayList.add(Integer.valueOf(fileDownloadModel.getId()));
                } else {
                    File file = new File(targetFilePath);
                    if (fileDownloadModel.getStatus() == (byte) -2 && c.isBreakpointAvailable(fileDownloadModel.getId(), fileDownloadModel, fileDownloadModel.getPath(), null)) {
                        File file2 = new File(fileDownloadModel.getTempFilePath());
                        if (!file2.exists() && file.exists()) {
                            boolean renameTo = file.renameTo(file2);
                            if (FileDownloadLog.NEED_LOG) {
                                FileDownloadLog.d(this, "resume from the old no-temp-file architecture [%B], [%s]->[%s]", Boolean.valueOf(renameTo), file.getPath(), file2.getPath());
                            }
                        }
                    }
                    if (fileDownloadModel.getStatus() == (byte) 1 && fileDownloadModel.getSoFar() <= 0) {
                        arrayList.add(Integer.valueOf(fileDownloadModel.getId()));
                    } else if (!c.isBreakpointAvailable(fileDownloadModel.getId(), fileDownloadModel)) {
                        arrayList.add(Integer.valueOf(fileDownloadModel.getId()));
                    } else if (file.exists()) {
                        arrayList.add(Integer.valueOf(fileDownloadModel.getId()));
                    } else {
                        this.b.put(fileDownloadModel.getId(), fileDownloadModel);
                    }
                }
            } catch (Throwable th) {
                rawQuery.close();
                FileDownloadUtils.markConverted(FileDownloadHelper.getAppContext());
                if (arrayList.size() > 0) {
                    String join = TextUtils.join(", ", arrayList);
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.d(this, "delete %s", join);
                    }
                    this.a.execSQL(FileDownloadUtils.formatString("DELETE FROM %s WHERE %s IN (%s);", TABLE_NAME, FileDownloadModel.ID, join));
                }
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "refresh data %d , will delete: %d consume %d", Integer.valueOf(this.b.size()), Integer.valueOf(arrayList.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            }
        }
        rawQuery.close();
        FileDownloadUtils.markConverted(FileDownloadHelper.getAppContext());
        if (arrayList.size() > 0) {
            targetFilePath = TextUtils.join(", ", arrayList);
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "delete %s", targetFilePath);
            }
            this.a.execSQL(FileDownloadUtils.formatString("DELETE FROM %s WHERE %s IN (%s);", TABLE_NAME, FileDownloadModel.ID, targetFilePath));
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "refresh data %d , will delete: %d consume %d", Integer.valueOf(this.b.size()), Integer.valueOf(arrayList.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public FileDownloadModel find(int i) {
        return (FileDownloadModel) this.b.get(i);
    }

    public void insert(FileDownloadModel fileDownloadModel) {
        this.b.put(fileDownloadModel.getId(), fileDownloadModel);
        this.a.insert(TABLE_NAME, null, fileDownloadModel.toContentValues());
    }

    public void update(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.w(this, "update but model == null!", new Object[0]);
        } else if (find(fileDownloadModel.getId()) != null) {
            this.b.remove(fileDownloadModel.getId());
            this.b.put(fileDownloadModel.getId(), fileDownloadModel);
            this.a.update(TABLE_NAME, fileDownloadModel.toContentValues(), "_id = ? ", new String[]{String.valueOf(fileDownloadModel.getId())});
        } else {
            insert(fileDownloadModel);
        }
    }

    public void update(List<FileDownloadModel> list) {
        if (list == null) {
            FileDownloadLog.w(this, "update a download list, but list == null!", new Object[0]);
            return;
        }
        this.a.beginTransaction();
        try {
            for (FileDownloadModel fileDownloadModel : list) {
                if (find(fileDownloadModel.getId()) != null) {
                    this.b.remove(fileDownloadModel.getId());
                    this.b.put(fileDownloadModel.getId(), fileDownloadModel);
                    this.a.update(TABLE_NAME, fileDownloadModel.toContentValues(), "_id = ? ", new String[]{String.valueOf(fileDownloadModel.getId())});
                } else {
                    this.b.put(fileDownloadModel.getId(), fileDownloadModel);
                    this.a.insert(TABLE_NAME, null, fileDownloadModel.toContentValues());
                }
            }
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    public boolean remove(int i) {
        this.b.remove(i);
        if (this.a.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(i)}) != 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.b.clear();
        this.a.delete(TABLE_NAME, null, null);
    }

    public void updateConnected(FileDownloadModel fileDownloadModel, long j, String str, String str2) {
        fileDownloadModel.setStatus((byte) 2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf((byte) 2));
        if (fileDownloadModel.getTotal() != j) {
            fileDownloadModel.setTotal(j);
            contentValues.put("total", Long.valueOf(j));
        }
        String eTag = fileDownloadModel.getETag();
        if (!((str == null || str.equals(eTag)) && (eTag == null || eTag.equals(str)))) {
            fileDownloadModel.setETag(str);
            contentValues.put("etag", str);
        }
        if (fileDownloadModel.isPathAsDirectory() && fileDownloadModel.getFilename() == null && str2 != null) {
            fileDownloadModel.setFilename(str2);
            contentValues.put(FileDownloadModel.FILENAME, str2);
        }
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updateProgress(FileDownloadModel fileDownloadModel, long j) {
        fileDownloadModel.setStatus((byte) 3);
        fileDownloadModel.setSoFar(j);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf((byte) 3));
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updateError(FileDownloadModel fileDownloadModel, Throwable th, long j) {
        String th2 = th.toString();
        fileDownloadModel.setStatus((byte) -1);
        fileDownloadModel.setErrMsg(th2);
        fileDownloadModel.setSoFar(j);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.ERR_MSG, th2);
        contentValues.put("status", Byte.valueOf((byte) -1));
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updateRetry(FileDownloadModel fileDownloadModel, Throwable th) {
        String th2 = th.toString();
        fileDownloadModel.setStatus((byte) 5);
        fileDownloadModel.setErrMsg(th2);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FileDownloadModel.ERR_MSG, th2);
        contentValues.put("status", Byte.valueOf((byte) 5));
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updateComplete(FileDownloadModel fileDownloadModel, long j) {
        fileDownloadModel.setStatus((byte) -3);
        fileDownloadModel.setSoFar(j);
        fileDownloadModel.setTotal(j);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf((byte) -3));
        contentValues.put("total", Long.valueOf(j));
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updatePause(FileDownloadModel fileDownloadModel, long j) {
        fileDownloadModel.setStatus((byte) -2);
        fileDownloadModel.setSoFar(j);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf((byte) -2));
        contentValues.put(FileDownloadModel.SOFAR, Long.valueOf(j));
        a(fileDownloadModel.getId(), contentValues);
    }

    public void updatePending(FileDownloadModel fileDownloadModel) {
        fileDownloadModel.setStatus((byte) 1);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Byte.valueOf((byte) 1));
        a(fileDownloadModel.getId(), contentValues);
    }

    private void a(int i, ContentValues contentValues) {
        this.a.update(TABLE_NAME, contentValues, "_id = ? ", new String[]{String.valueOf(i)});
    }
}
