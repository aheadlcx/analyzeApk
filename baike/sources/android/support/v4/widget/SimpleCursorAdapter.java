package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
    @RestrictTo({Scope.LIBRARY_GROUP})
    protected int[] j;
    @RestrictTo({Scope.LIBRARY_GROUP})
    protected int[] k;
    String[] l;
    private int m = -1;
    private CursorToStringConverter n;
    private ViewBinder o;

    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int i);
    }

    @Deprecated
    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String[] strArr, int[] iArr) {
        super(context, i, cursor);
        this.k = iArr;
        this.l = strArr;
        a(cursor, strArr);
    }

    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String[] strArr, int[] iArr, int i2) {
        super(context, i, cursor, i2);
        this.k = iArr;
        this.l = strArr;
        a(cursor, strArr);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewBinder viewBinder = this.o;
        int length = this.k.length;
        int[] iArr = this.j;
        int[] iArr2 = this.k;
        for (int i = 0; i < length; i++) {
            View findViewById = view.findViewById(iArr2[i]);
            if (findViewById != null) {
                boolean viewValue;
                if (viewBinder != null) {
                    viewValue = viewBinder.setViewValue(findViewById, cursor, iArr[i]);
                } else {
                    viewValue = false;
                }
                if (viewValue) {
                    continue;
                } else {
                    String string = cursor.getString(iArr[i]);
                    if (string == null) {
                        string = "";
                    }
                    if (findViewById instanceof TextView) {
                        setViewText((TextView) findViewById, string);
                    } else if (findViewById instanceof ImageView) {
                        setViewImage((ImageView) findViewById, string);
                    } else {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                    }
                }
            }
        }
    }

    public ViewBinder getViewBinder() {
        return this.o;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        this.o = viewBinder;
    }

    public void setViewImage(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void setViewText(TextView textView, String str) {
        textView.setText(str);
    }

    public int getStringConversionColumn() {
        return this.m;
    }

    public void setStringConversionColumn(int i) {
        this.m = i;
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.n;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursorToStringConverter) {
        this.n = cursorToStringConverter;
    }

    public CharSequence convertToString(Cursor cursor) {
        if (this.n != null) {
            return this.n.convertToString(cursor);
        }
        if (this.m > -1) {
            return cursor.getString(this.m);
        }
        return super.convertToString(cursor);
    }

    private void a(Cursor cursor, String[] strArr) {
        if (cursor != null) {
            int length = strArr.length;
            if (this.j == null || this.j.length != length) {
                this.j = new int[length];
            }
            for (int i = 0; i < length; i++) {
                this.j[i] = cursor.getColumnIndexOrThrow(strArr[i]);
            }
            return;
        }
        this.j = null;
    }

    public Cursor swapCursor(Cursor cursor) {
        a(cursor, this.l);
        return super.swapCursor(cursor);
    }

    public void changeCursorAndColumns(Cursor cursor, String[] strArr, int[] iArr) {
        this.l = strArr;
        this.k = iArr;
        a(cursor, this.l);
        super.changeCursor(cursor);
    }
}
