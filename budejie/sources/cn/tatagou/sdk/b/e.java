package cn.tatagou.sdk.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.p;
import java.util.LinkedList;
import java.util.List;

public class e {
    private static final String a = e.class.getSimpleName();
    private static volatile e b;

    public static e getInstance() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = new e();
                }
            }
        }
        return b;
    }

    public synchronized void insertSp(Context context, List<Special> list) {
        try {
            SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
            if (openDatabase != null) {
                c.getInstance(TtgSDK.getContext()).getSemaphore().acquire();
                SQLiteStatement compileStatement = openDatabase.compileStatement("insert into special(spId,tbId,onlineTime)values(?,?,?)");
                openDatabase.beginTransaction();
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                for (Special special : list) {
                    if (special != null) {
                        String taobaoId;
                        String id = special.getId();
                        if (special.getItem() != null) {
                            taobaoId = special.getItem().getTaobaoId();
                        } else {
                            try {
                                taobaoId = "0";
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (openDatabase != null) {
                                    openDatabase.endTransaction();
                                    c.getInstance(TtgSDK.getContext()).getSemaphore().release();
                                }
                                c.getInstance(TtgSDK.getContext()).closeDatabase();
                            } catch (Throwable th) {
                                if (openDatabase != null) {
                                    openDatabase.endTransaction();
                                    c.getInstance(TtgSDK.getContext()).getSemaphore().release();
                                }
                                c.getInstance(TtgSDK.getContext()).closeDatabase();
                            }
                        }
                        if (p.isEmpty(id)) {
                            id = "0";
                        }
                        compileStatement.bindString(1, id);
                        if (p.isEmpty(taobaoId)) {
                            taobaoId = "0";
                        }
                        compileStatement.bindString(2, taobaoId);
                        compileStatement.bindLong(3, currentTimeMillis);
                        try {
                            if (compileStatement.executeInsert() >= 0) {
                            }
                        } catch (Throwable e2) {
                            Log.e(a, "insertSp insertSp: " + e2.getMessage(), e2);
                        }
                    }
                }
                openDatabase.setTransactionSuccessful();
            }
            if (openDatabase != null) {
                openDatabase.endTransaction();
                c.getInstance(TtgSDK.getContext()).getSemaphore().release();
            }
            c.getInstance(TtgSDK.getContext()).closeDatabase();
        } catch (Exception e3) {
        }
    }

    public synchronized List<String> querySpId(Context context) {
        List<String> linkedList;
        Cursor cursor = null;
        synchronized (this) {
            linkedList = new LinkedList();
            try {
                SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
                if (openDatabase != null) {
                    try {
                        cursor = openDatabase.rawQuery("select spId from special", null);
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                String string = cursor.getString(cursor.getColumnIndex("spId"));
                                if (!p.isEmpty(string)) {
                                    linkedList.add(string);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (cursor != null) {
                            if (!cursor.isClosed()) {
                                cursor.close();
                            }
                        }
                        c.getInstance(TtgSDK.getContext()).closeDatabase();
                    } catch (Throwable th) {
                        if (!(cursor == null || cursor.isClosed())) {
                            cursor.close();
                        }
                        c.getInstance(TtgSDK.getContext()).closeDatabase();
                    }
                }
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                c.getInstance(TtgSDK.getContext()).closeDatabase();
            } catch (Exception e2) {
            }
        }
        return linkedList;
    }

    public synchronized void delSpByTime(Context context, long j) {
        try {
            SQLiteDatabase openDatabase = c.getInstance(TtgSDK.getContext()).openDatabase();
            if (openDatabase != null) {
                try {
                    c.getInstance(TtgSDK.getContext()).getSemaphore().acquire();
                    openDatabase.beginTransaction();
                    openDatabase.execSQL("delete from special where onlineTime< ?", new Object[]{Long.valueOf(j)});
                    openDatabase.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (openDatabase != null) {
                        openDatabase.endTransaction();
                        c.getInstance(TtgSDK.getContext()).getSemaphore().release();
                    }
                    c.getInstance(TtgSDK.getContext()).closeDatabase();
                } catch (Throwable th) {
                    if (openDatabase != null) {
                        openDatabase.endTransaction();
                        c.getInstance(TtgSDK.getContext()).getSemaphore().release();
                    }
                    c.getInstance(TtgSDK.getContext()).closeDatabase();
                }
            }
            if (openDatabase != null) {
                openDatabase.endTransaction();
                c.getInstance(TtgSDK.getContext()).getSemaphore().release();
            }
            c.getInstance(TtgSDK.getContext()).closeDatabase();
        } catch (Exception e2) {
        }
    }
}
