package com.tencent.open.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.tencent.open.utils.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class f extends SQLiteOpenHelper {
    protected static final String[] a = new String[]{PayPWDUniversalActivity.KEY};
    protected static f b;

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (b == null) {
                b = new f(d.a());
            }
            fVar = b;
        }
        return fVar;
    }

    public f(Context context) {
        super(context, "sdk_report.db", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS via_cgi_report");
        onCreate(sQLiteDatabase);
    }

    public synchronized List<Serializable> a(String str) {
        List<Serializable> list;
        InputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        Object obj;
        ObjectInputStream objectInputStream2;
        Throwable th;
        Cursor cursor;
        List<Serializable> synchronizedList = Collections.synchronizedList(new ArrayList());
        if (TextUtils.isEmpty(str)) {
            list = synchronizedList;
        } else {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            if (readableDatabase == null) {
                list = synchronizedList;
            } else {
                ObjectInputStream objectInputStream3 = null;
                Cursor query;
                try {
                    query = readableDatabase.query("via_cgi_report", null, "type = ?", new String[]{str}, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() > 0) {
                                query.moveToFirst();
                                do {
                                    byteArrayInputStream = new ByteArrayInputStream(query.getBlob(query.getColumnIndex("blob")));
                                    try {
                                        objectInputStream = new ObjectInputStream(byteArrayInputStream);
                                        try {
                                            obj = (Serializable) objectInputStream.readObject();
                                            if (objectInputStream != null) {
                                                try {
                                                    objectInputStream.close();
                                                } catch (IOException e) {
                                                }
                                            }
                                            try {
                                                byteArrayInputStream.close();
                                            } catch (IOException e2) {
                                            }
                                        } catch (Exception e3) {
                                            objectInputStream2 = objectInputStream;
                                            if (objectInputStream2 != null) {
                                                try {
                                                    objectInputStream2.close();
                                                } catch (IOException e4) {
                                                }
                                            }
                                            try {
                                                byteArrayInputStream.close();
                                                obj = null;
                                            } catch (IOException e5) {
                                                obj = null;
                                            }
                                            if (obj != null) {
                                                synchronizedList.add(obj);
                                            }
                                            if (!query.moveToNext()) {
                                                if (query != null) {
                                                    query.close();
                                                }
                                                if (null != null) {
                                                    try {
                                                        objectInputStream3.close();
                                                    } catch (IOException e6) {
                                                        e6.printStackTrace();
                                                    }
                                                }
                                                if (readableDatabase != null) {
                                                    readableDatabase.close();
                                                }
                                                list = synchronizedList;
                                                return list;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    } catch (Exception e7) {
                                        objectInputStream2 = null;
                                        if (objectInputStream2 != null) {
                                            objectInputStream2.close();
                                        }
                                        byteArrayInputStream.close();
                                        obj = null;
                                        if (obj != null) {
                                            synchronizedList.add(obj);
                                        }
                                        if (query.moveToNext()) {
                                            if (query != null) {
                                                query.close();
                                            }
                                            if (null != null) {
                                                objectInputStream3.close();
                                            }
                                            if (readableDatabase != null) {
                                                readableDatabase.close();
                                            }
                                            list = synchronizedList;
                                            return list;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        objectInputStream = null;
                                    }
                                    if (obj != null) {
                                        synchronizedList.add(obj);
                                    }
                                } while (query.moveToNext());
                            }
                        } catch (Exception e8) {
                            th = e8;
                            cursor = query;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (null != null) {
                        objectInputStream3.close();
                    }
                    if (readableDatabase != null) {
                        readableDatabase.close();
                    }
                } catch (Exception e9) {
                    th = e9;
                    cursor = null;
                    try {
                        com.tencent.open.a.f.b("openSDK_LOG.ReportDatabaseHelper", "getReportItemFromDB has exception.", th);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (null != null) {
                            try {
                                objectInputStream3.close();
                            } catch (IOException e62) {
                                e62.printStackTrace();
                            }
                        }
                        if (readableDatabase != null) {
                            readableDatabase.close();
                        }
                        list = synchronizedList;
                        return list;
                    } catch (Throwable th5) {
                        th = th5;
                        query = cursor;
                        if (query != null) {
                            query.close();
                        }
                        if (null != null) {
                            try {
                                objectInputStream3.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                        if (readableDatabase != null) {
                            readableDatabase.close();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    if (null != null) {
                        objectInputStream3.close();
                    }
                    if (readableDatabase != null) {
                        readableDatabase.close();
                    }
                    throw th;
                }
                list = synchronizedList;
            }
        }
        return list;
        throw th;
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e11) {
            }
        }
        try {
            byteArrayInputStream.close();
        } catch (IOException e12) {
        }
        throw th;
        byteArrayInputStream.close();
        throw th;
    }

    public synchronized void a(String str, List<Serializable> list) {
        OutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        Throwable th;
        ObjectOutputStream objectOutputStream2 = null;
        synchronized (this) {
            int size = list.size();
            if (size != 0) {
                int i = size <= 20 ? size : 20;
                if (!TextUtils.isEmpty(str)) {
                    b(str);
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    if (writableDatabase != null) {
                        writableDatabase.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        for (int i2 = 0; i2 < i; i2++) {
                            Serializable serializable = (Serializable) list.get(i2);
                            if (serializable != null) {
                                contentValues.put("type", str);
                                byteArrayOutputStream = new ByteArrayOutputStream(512);
                                try {
                                    ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(byteArrayOutputStream);
                                    try {
                                        objectOutputStream3.writeObject(serializable);
                                        if (objectOutputStream3 != null) {
                                            try {
                                                objectOutputStream3.close();
                                            } catch (IOException e) {
                                            }
                                        }
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (IOException e2) {
                                        }
                                    } catch (IOException e3) {
                                        objectOutputStream = objectOutputStream3;
                                        if (objectOutputStream != null) {
                                            try {
                                                objectOutputStream.close();
                                            } catch (IOException e4) {
                                            }
                                        }
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (IOException e5) {
                                        }
                                        contentValues.put("blob", byteArrayOutputStream.toByteArray());
                                        writableDatabase.insert("via_cgi_report", null, contentValues);
                                        contentValues.clear();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        objectOutputStream2 = objectOutputStream3;
                                    }
                                } catch (IOException e6) {
                                    objectOutputStream = null;
                                    if (objectOutputStream != null) {
                                        objectOutputStream.close();
                                    }
                                    byteArrayOutputStream.close();
                                    contentValues.put("blob", byteArrayOutputStream.toByteArray());
                                    writableDatabase.insert("via_cgi_report", null, contentValues);
                                    contentValues.clear();
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                                try {
                                    contentValues.put("blob", byteArrayOutputStream.toByteArray());
                                    writableDatabase.insert("via_cgi_report", null, contentValues);
                                } catch (Exception e7) {
                                    com.tencent.open.a.f.e("openSDK_LOG.ReportDatabaseHelper", "saveReportItemToDB has exception.");
                                    writableDatabase.endTransaction();
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                } catch (Throwable th4) {
                                    writableDatabase.endTransaction();
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                }
                            }
                            contentValues.clear();
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                    }
                }
            }
        }
        return;
        if (objectOutputStream2 != null) {
            try {
                objectOutputStream2.close();
            } catch (IOException e8) {
            }
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e9) {
        }
        throw th;
        byteArrayOutputStream.close();
        throw th;
        throw th;
    }

    public synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    writableDatabase.delete("via_cgi_report", "type = ?", new String[]{str});
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } catch (Throwable e) {
                    com.tencent.open.a.f.b("openSDK_LOG.ReportDatabaseHelper", "clearReportItem has exception.", e);
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } catch (Throwable th) {
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                }
            }
        }
    }
}
