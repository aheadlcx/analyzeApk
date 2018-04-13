package com.xiaomi.push.service;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.Constants;

public class f extends SQLiteOpenHelper {
    private static final String[] c = new String[]{"name", "TEXT NOT NULL", "appId", "INTEGER NOT NULL", "package_name", "TEXT NOT NULL", "create_time", "INTEGER NOT NULL", "type", "TEXT NOT NULL", "center_longtitude", "TEXT", "center_lantitude", "TEXT", "circle_radius", "REAL", "polygon_point", "TEXT", "coordinate_provider", "TEXT NOT NULL", "current_status", "TEXT NOT NULL"};
    private static final String[] d = new String[]{"message_id", "TEXT NOT NULL", "geo_id", "TEXT NOT NULL", "content", "BLOB NOT NULL", "action", "INTEGER NOT NULL", "deadline", "INTEGER NOT NULL"};
    public final Object a = new Object();
    private final String b = "GeoFenceDatabaseHelper.";

    public f(Context context) {
        super(context, "geofencing.db", null, 1);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE geofence(id TEXT PRIMARY KEY ,");
            for (int i = 0; i < c.length - 1; i += 2) {
                if (i != 0) {
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder.append(c[i]).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(c[i + 1]);
            }
            stringBuilder.append(");");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        } catch (SQLException e) {
            b.d(e.toString());
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE geoMessage(");
            for (int i = 0; i < d.length - 1; i += 2) {
                if (i != 0) {
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder.append(d[i]).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(c[i + 1]);
            }
            stringBuilder.append(",PRIMARY KEY(message_id,geo_id));");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        } catch (SQLException e) {
            b.d(e.toString());
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (this.a) {
            try {
                a(sQLiteDatabase);
                b(sQLiteDatabase);
                b.c("GeoFenceDatabaseHelper. create tables");
            } catch (Throwable e) {
                b.a(e);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
