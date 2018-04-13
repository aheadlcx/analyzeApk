package com.tencent.wcdb;

import android.content.ContentValues;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteProgram;
import com.tencent.wcdb.database.SQLiteStatement;
import com.tencent.wcdb.support.Log;
import java.util.HashMap;
import java.util.Map.Entry;

@Deprecated
public class DatabaseUtils$InsertHelper {
    public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
    public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
    private HashMap<String, Integer> mColumns;
    private final SQLiteDatabase mDb;
    private String mInsertSQL = null;
    private SQLiteStatement mInsertStatement = null;
    private SQLiteStatement mPreparedStatement = null;
    private SQLiteStatement mReplaceStatement = null;
    private final String mTableName;

    public DatabaseUtils$InsertHelper(SQLiteDatabase sQLiteDatabase, String str) {
        this.mDb = sQLiteDatabase;
        this.mTableName = str;
    }

    private void buildSQL() throws SQLException {
        Cursor cursor = null;
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(this.mTableName);
        stringBuilder.append(" (");
        CharSequence stringBuilder2 = new StringBuilder(128);
        stringBuilder2.append("VALUES (");
        try {
            cursor = this.mDb.rawQuery("PRAGMA table_info(" + this.mTableName + ")", null);
            this.mColumns = new HashMap(cursor.getCount());
            int i = 1;
            while (cursor.moveToNext()) {
                String string = cursor.getString(1);
                String string2 = cursor.getString(4);
                this.mColumns.put(string, Integer.valueOf(i));
                stringBuilder.append("'");
                stringBuilder.append(string);
                stringBuilder.append("'");
                if (string2 == null) {
                    stringBuilder2.append("?");
                } else {
                    stringBuilder2.append("COALESCE(?, ");
                    stringBuilder2.append(string2);
                    stringBuilder2.append(")");
                }
                stringBuilder.append(i == cursor.getCount() ? ") " : ", ");
                stringBuilder2.append(i == cursor.getCount() ? ");" : ", ");
                i++;
            }
            stringBuilder.append(stringBuilder2);
            this.mInsertSQL = stringBuilder.toString();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private SQLiteStatement getStatement(boolean z) throws SQLException {
        if (z) {
            if (this.mReplaceStatement == null) {
                if (this.mInsertSQL == null) {
                    buildSQL();
                }
                this.mReplaceStatement = this.mDb.compileStatement("INSERT OR REPLACE" + this.mInsertSQL.substring(6));
            }
            return this.mReplaceStatement;
        }
        if (this.mInsertStatement == null) {
            if (this.mInsertSQL == null) {
                buildSQL();
            }
            this.mInsertStatement = this.mDb.compileStatement(this.mInsertSQL);
        }
        return this.mInsertStatement;
    }

    private long insertInternal(ContentValues contentValues, boolean z) {
        long executeInsert;
        this.mDb.beginTransactionNonExclusive();
        try {
            SQLiteProgram statement = getStatement(z);
            statement.clearBindings();
            for (Entry entry : contentValues.valueSet()) {
                DatabaseUtils.bindObjectToProgram(statement, getColumnIndex((String) entry.getKey()), entry.getValue());
            }
            executeInsert = statement.executeInsert();
            this.mDb.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("WCDB.DatabaseUtils", "Error inserting " + contentValues + " into table  " + this.mTableName, new Object[]{e});
            executeInsert = -1;
        } finally {
            this.mDb.endTransaction();
        }
        return executeInsert;
    }

    public int getColumnIndex(String str) {
        getStatement(false);
        Integer num = (Integer) this.mColumns.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("column '" + str + "' is invalid");
    }

    public void bind(int i, double d) {
        this.mPreparedStatement.bindDouble(i, d);
    }

    public void bind(int i, float f) {
        this.mPreparedStatement.bindDouble(i, (double) f);
    }

    public void bind(int i, long j) {
        this.mPreparedStatement.bindLong(i, j);
    }

    public void bind(int i, int i2) {
        this.mPreparedStatement.bindLong(i, (long) i2);
    }

    public void bind(int i, boolean z) {
        this.mPreparedStatement.bindLong(i, z ? 1 : 0);
    }

    public void bindNull(int i) {
        this.mPreparedStatement.bindNull(i);
    }

    public void bind(int i, byte[] bArr) {
        if (bArr == null) {
            this.mPreparedStatement.bindNull(i);
        } else {
            this.mPreparedStatement.bindBlob(i, bArr);
        }
    }

    public void bind(int i, String str) {
        if (str == null) {
            this.mPreparedStatement.bindNull(i);
        } else {
            this.mPreparedStatement.bindString(i, str);
        }
    }

    public long insert(ContentValues contentValues) {
        return insertInternal(contentValues, false);
    }

    public long execute() {
        if (this.mPreparedStatement == null) {
            throw new IllegalStateException("you must prepare this inserter before calling execute");
        }
        long executeInsert;
        try {
            executeInsert = this.mPreparedStatement.executeInsert();
        } catch (SQLException e) {
            Log.e("WCDB.DatabaseUtils", "Error executing InsertHelper with table " + this.mTableName, new Object[]{e});
            executeInsert = -1;
        } finally {
            this.mPreparedStatement = null;
        }
        return executeInsert;
    }

    public void prepareForInsert() {
        this.mPreparedStatement = getStatement(false);
        this.mPreparedStatement.clearBindings();
    }

    public void prepareForReplace() {
        this.mPreparedStatement = getStatement(true);
        this.mPreparedStatement.clearBindings();
    }

    public long replace(ContentValues contentValues) {
        return insertInternal(contentValues, true);
    }

    public void close() {
        if (this.mInsertStatement != null) {
            this.mInsertStatement.close();
            this.mInsertStatement = null;
        }
        if (this.mReplaceStatement != null) {
            this.mReplaceStatement.close();
            this.mReplaceStatement = null;
        }
        this.mInsertSQL = null;
        this.mColumns = null;
    }
}
