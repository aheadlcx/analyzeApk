package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.a.c;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.util.ScreenUtil;
import java.text.DecimalFormat;

public class ReadProgressLayout extends LinearLayout {
    private static final int PADDING_VALUE = 10;
    private static final int PLUS_WHAT = 273;
    private static final int SUB_WHAT = 165;
    private static final String TAG = "ReadProgressLayout";
    private int count;
    private DecimalFormat decimalFormat;
    private RelativeLayout mBottomLayout;
    private PositionCallback mCallback;
    private Context mContext;
    private Handler mHandler;
    private ImageView mLeftImageView;
    private int mLeftPosition;
    private int mPaddingValue;
    private TextView mPercentTextView;
    private int mProgress;
    private ImageView mRightImageView;
    private int mRightPosition;
    private SeekBar mSeekBar;
    private int mSeekBarWidth;
    private LinearLayout mTitleLayout;
    private TextView mTitleTextView;
    private RelativeLayout mTopLayout;
    private int mTopWidth;
    private ImageView mTriangleImageView;
    private View mView;

    public interface PositionCallback {
        void sendPosition(int i);
    }

    public ReadProgressLayout(Context context) {
        this(context, null);
    }

    public ReadProgressLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReadProgressLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.decimalFormat = new DecimalFormat("#0.00");
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 165:
                        if (ReadProgressLayout.this.mProgress > 0) {
                            ReadProgressLayout.this.mProgress = ReadProgressLayout.this.mProgress - 1;
                            ReadProgressLayout.this.mHandler.sendEmptyMessage(165);
                            break;
                        }
                        break;
                    case 273:
                        if (ReadProgressLayout.this.mProgress < 100) {
                            ReadProgressLayout.this.mProgress = ReadProgressLayout.this.mProgress + 1;
                            ReadProgressLayout.this.mHandler.sendEmptyMessage(273);
                            break;
                        }
                        break;
                }
                ReadProgressLayout.this.mSeekBar.setProgress(ReadProgressLayout.this.mProgress);
            }
        };
        this.mContext = context;
        initView();
        this.mPaddingValue = ScreenUtil.dpToPxInt(10.0f);
    }

    private void initView() {
        this.mView = LayoutInflater.from(this.mContext).inflate(e.book_reader_read_progress_layout, null);
        this.mLeftImageView = (ImageView) this.mView.findViewById(d.book_reader_sub_image_view);
        this.mRightImageView = (ImageView) this.mView.findViewById(d.book_reader_add_image_view);
        this.mSeekBar = (SeekBar) this.mView.findViewById(d.book_reader_progress_seek_bar);
        this.mTitleTextView = (TextView) this.mView.findViewById(d.book_reader_title_text_view);
        this.mPercentTextView = (TextView) this.mView.findViewById(d.book_reader_percent_text_view);
        this.mTopLayout = (RelativeLayout) this.mView.findViewById(d.book_reader_top_layout);
        this.mView.setOnClickListener(null);
        this.mTriangleImageView = (ImageView) this.mView.findViewById(d.book_reader_triangle_image_view);
        this.mBottomLayout = (RelativeLayout) this.mView.findViewById(d.book_reader_progress_bottom_layout);
        this.mTitleLayout = (LinearLayout) this.mView.findViewById(d.book_reader_name_layout);
        addView(this.mView);
        setListener();
    }

    public void changeMode(boolean z) {
        this.mTitleLayout.setBackgroundResource(z ? c.book_reader_title_night_shape : c.book_reader_title_shape);
        this.mBottomLayout.setBackgroundResource(z ? a.book_reader_read_bottom_night_background : a.book_reader_white);
        this.mTitleTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_main_night_color : a.book_reader_main_color));
        this.mPercentTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_main_night_color : a.book_reader_main_color));
        this.mSeekBar.setThumb(getResources().getDrawable(z ? c.book_reader_read_ball_night_selector : c.book_reader_read_ball_selector));
        this.mTriangleImageView.setImageResource(z ? c.book_reader_triangle_night_image : c.book_reader_triangle_day_image);
        this.mLeftImageView.setImageResource(z ? c.book_reader_progress_sub_night_selector : c.book_reader_progress_sub_selector);
        this.mRightImageView.setImageResource(z ? c.book_reader_progress_add_night_selector : c.book_reader_progress_add_selector);
    }

    public void setCount(int i) {
        this.count = i;
    }

    private void setListener() {
        setOnTouchListener(this.mLeftImageView, 165);
        setOnTouchListener(this.mRightImageView, 273);
        this.mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ReadProgressLayout.this.mProgress = i;
                ReadProgressLayout.this.setLayoutPosition(((ReadProgressLayout.this.mSeekBarWidth * i) / 100) + ReadProgressLayout.this.mLeftPosition);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                ReadProgressLayout.this.calculatePosition();
            }
        });
    }

    public void setPercent(float f) {
        this.mPercentTextView.setText(this.decimalFormat.format((double) f) + "%");
    }

    public void setProgress(float f) {
        this.mProgress = (int) f;
        this.mSeekBar.setProgress(this.mProgress);
    }

    public void setTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    private void setOnTouchListener(View view, final int i) {
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        ReadProgressLayout.this.mHandler.sendEmptyMessage(i);
                        break;
                    case 1:
                        ReadProgressLayout.this.mHandler.removeMessages(i);
                        ReadProgressLayout.this.calculatePosition();
                        break;
                }
                return true;
            }
        });
    }

    private void calculatePosition() {
        int i = (int) ((((float) this.mProgress) / 100.0f) * ((float) this.count));
        if (this.mCallback != null) {
            this.mCallback.sendPosition(i);
        }
    }

    public void setCallback(PositionCallback positionCallback) {
        this.mCallback = positionCallback;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mLeftPosition = this.mSeekBar.getLeft();
        this.mRightPosition = this.mSeekBar.getRight();
        this.mTopWidth = this.mTopLayout.getWidth();
        this.mSeekBarWidth = this.mRightPosition - this.mLeftPosition;
        setLayoutPosition(((this.mSeekBarWidth * this.mProgress) / 100) + this.mLeftPosition);
    }

    private void setLayoutPosition(int i) {
        this.mPaddingValue = 0;
        this.mTopLayout.layout((i - (this.mTopWidth / 2)) + this.mPaddingValue, this.mTopLayout.getTop(), ((this.mTopWidth / 2) + i) + this.mPaddingValue, this.mTopLayout.getBottom());
    }
}
