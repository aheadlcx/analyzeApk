package com.zhihu.matisse.internal.ui.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CursorAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.utils.Platform;

public class AlbumsSpinner {
    private static final int MAX_SHOWN_COUNT = 4;
    private CursorAdapter mAdapter;
    private ListPopupWindow mListPopupWindow;
    private OnItemSelectedListener mOnItemSelectedListener;
    private TextView mSelected;

    public AlbumsSpinner(@NonNull Context context) {
        this.mListPopupWindow = new ListPopupWindow(context, null, R.attr.listPopupWindowStyle);
        this.mListPopupWindow.setModal(true);
        this.mListPopupWindow.setWidth(context.getResources().getDisplayMetrics().widthPixels);
        this.mListPopupWindow.setHorizontalOffset(0);
        this.mListPopupWindow.setVerticalOffset(0);
        this.mListPopupWindow.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                AlbumsSpinner.this.onItemSelected(adapterView.getContext(), i);
                if (AlbumsSpinner.this.mOnItemSelectedListener != null) {
                    AlbumsSpinner.this.mOnItemSelectedListener.onItemSelected(adapterView, view, i, j);
                }
            }
        });
        this.mListPopupWindow.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                if (AlbumsSpinner.this.mSelected != null) {
                    AlbumsSpinner.this.mSelected.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down1, 0);
                }
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public void setSelection(Context context, int i) {
        this.mListPopupWindow.setSelection(i);
        onItemSelected(context, i);
    }

    private void onItemSelected(Context context, int i) {
        this.mListPopupWindow.dismiss();
        Cursor cursor = this.mAdapter.getCursor();
        cursor.moveToPosition(i);
        CharSequence displayName = Album.valueOf(cursor).getDisplayName(context);
        if (this.mSelected.getVisibility() == 0) {
            this.mSelected.setText(displayName);
        } else if (Platform.hasICS()) {
            this.mSelected.setAlpha(0.0f);
            this.mSelected.setVisibility(0);
            this.mSelected.setText(displayName);
            this.mSelected.animate().alpha(1.0f).setDuration((long) context.getResources().getInteger(17694722)).start();
        } else {
            this.mSelected.setVisibility(0);
            this.mSelected.setText(displayName);
        }
    }

    public void setAdapter(CursorAdapter cursorAdapter) {
        this.mListPopupWindow.setAdapter(cursorAdapter);
        this.mAdapter = cursorAdapter;
    }

    public void setSelectedTextView(TextView textView) {
        this.mSelected = textView;
        this.mSelected.setVisibility(8);
        this.mSelected.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int i = 4;
                AlbumsSpinner.this.mSelected.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up1, 0);
                int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.album_item_height);
                if (AlbumsSpinner.this.mAdapter.getCount() < 4) {
                    i = AlbumsSpinner.this.mAdapter.getCount();
                }
                AlbumsSpinner.this.mListPopupWindow.setHeight(i * dimensionPixelSize);
                AlbumsSpinner.this.mListPopupWindow.show();
            }
        });
        this.mSelected.setOnTouchListener(this.mListPopupWindow.createDragToOpenListener(this.mSelected));
    }

    public void setPopupAnchorView(View view) {
        this.mListPopupWindow.setAnchorView(view);
    }
}
