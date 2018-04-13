package com.r0adkll.slidr.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrConfig.Builder;

public class SliderPanel extends FrameLayout {
    private static final int MIN_FLING_VELOCITY = 400;
    private Callback mBottomCallback;
    private SlidrConfig mConfig;
    private View mDecorView;
    private View mDimView;
    private ViewDragHelper mDragHelper;
    private int mEdgePosition;
    private Callback mHorizontalCallback;
    private boolean mIsEdgeTouched;
    private boolean mIsLocked;
    private Callback mLeftCallback;
    private OnPanelSlideListener mListener;
    private Callback mRightCallback;
    private int mScreenHeight;
    private int mScreenWidth;
    private Callback mTopCallback;
    private Callback mVerticalCallback;

    public interface OnPanelSlideListener {
        void onClosed();

        void onOpened();

        void onSlideChange(float f);

        void onStateChanged(int i);
    }

    public SliderPanel(Context context) {
        super(context);
        this.mIsLocked = false;
        this.mIsEdgeTouched = false;
        this.mLeftCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                boolean z;
                if (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mDragHelper.isEdgeTouched(SliderPanel.this.mEdgePosition, i)) {
                    z = true;
                } else {
                    z = false;
                }
                return view.getId() == SliderPanel.this.mDecorView.getId() && z;
            }

            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return SliderPanel.clamp(i, 0, SliderPanel.this.mScreenWidth);
            }

            public int getViewHorizontalDragRange(View view) {
                return SliderPanel.this.mScreenWidth;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int left = view.getLeft();
                int width = (int) (((float) SliderPanel.this.getWidth()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f > 0.0f) {
                    if (Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = SliderPanel.this.mScreenWidth;
                    } else if (left > width) {
                        i = SliderPanel.this.mScreenWidth;
                    }
                } else if (f == 0.0f && left > width) {
                    i = SliderPanel.this.mScreenWidth;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(i, view.getTop());
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float access$500 = 1.0f - (((float) i) / ((float) SliderPanel.this.mScreenWidth));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(access$500);
                }
                SliderPanel.this.applyScrim(access$500);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getLeft() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.mRightCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                boolean z;
                if (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mDragHelper.isEdgeTouched(SliderPanel.this.mEdgePosition, i)) {
                    z = true;
                } else {
                    z = false;
                }
                return view.getId() == SliderPanel.this.mDecorView.getId() && z;
            }

            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return SliderPanel.clamp(i, -SliderPanel.this.mScreenWidth, 0);
            }

            public int getViewHorizontalDragRange(View view) {
                return SliderPanel.this.mScreenWidth;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int left = view.getLeft();
                int width = (int) (((float) SliderPanel.this.getWidth()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f < 0.0f) {
                    if (Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = -SliderPanel.this.mScreenWidth;
                    } else if (left < (-width)) {
                        i = -SliderPanel.this.mScreenWidth;
                    }
                } else if (f == 0.0f && left < (-width)) {
                    i = -SliderPanel.this.mScreenWidth;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(i, view.getTop());
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float abs = 1.0f - (((float) Math.abs(i)) / ((float) SliderPanel.this.mScreenWidth));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(abs);
                }
                SliderPanel.this.applyScrim(abs);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getLeft() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.mTopCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                return view.getId() == SliderPanel.this.mDecorView.getId() && (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mIsEdgeTouched);
            }

            public int clampViewPositionVertical(View view, int i, int i2) {
                return SliderPanel.clamp(i, 0, SliderPanel.this.mScreenHeight);
            }

            public int getViewVerticalDragRange(View view) {
                return SliderPanel.this.mScreenHeight;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int top = view.getTop();
                int height = (int) (((float) SliderPanel.this.getHeight()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f2 > 0.0f) {
                    if (Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = SliderPanel.this.mScreenHeight;
                    } else if (top > height) {
                        i = SliderPanel.this.mScreenHeight;
                    }
                } else if (f2 == 0.0f && top > height) {
                    i = SliderPanel.this.mScreenHeight;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(view.getLeft(), i);
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float abs = 1.0f - (((float) Math.abs(i2)) / ((float) SliderPanel.this.mScreenHeight));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(abs);
                }
                SliderPanel.this.applyScrim(abs);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getTop() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.mBottomCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                return view.getId() == SliderPanel.this.mDecorView.getId() && (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mIsEdgeTouched);
            }

            public int clampViewPositionVertical(View view, int i, int i2) {
                return SliderPanel.clamp(i, -SliderPanel.this.mScreenHeight, 0);
            }

            public int getViewVerticalDragRange(View view) {
                return SliderPanel.this.mScreenHeight;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int top = view.getTop();
                int height = (int) (((float) SliderPanel.this.getHeight()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f2 < 0.0f) {
                    if (Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = -SliderPanel.this.mScreenHeight;
                    } else if (top < (-height)) {
                        i = -SliderPanel.this.mScreenHeight;
                    }
                } else if (f2 == 0.0f && top < (-height)) {
                    i = -SliderPanel.this.mScreenHeight;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(view.getLeft(), i);
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float abs = 1.0f - (((float) Math.abs(i2)) / ((float) SliderPanel.this.mScreenHeight));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(abs);
                }
                SliderPanel.this.applyScrim(abs);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getTop() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.mVerticalCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                return view.getId() == SliderPanel.this.mDecorView.getId() && (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mIsEdgeTouched);
            }

            public int clampViewPositionVertical(View view, int i, int i2) {
                return SliderPanel.clamp(i, -SliderPanel.this.mScreenHeight, SliderPanel.this.mScreenHeight);
            }

            public int getViewVerticalDragRange(View view) {
                return SliderPanel.this.mScreenHeight;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int top = view.getTop();
                int height = (int) (((float) SliderPanel.this.getHeight()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f2 > 0.0f) {
                    if (Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = SliderPanel.this.mScreenHeight;
                    } else if (top > height) {
                        i = SliderPanel.this.mScreenHeight;
                    }
                } else if (f2 < 0.0f) {
                    if (Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = -SliderPanel.this.mScreenHeight;
                    } else if (top < (-height)) {
                        i = -SliderPanel.this.mScreenHeight;
                    }
                } else if (top > height) {
                    i = SliderPanel.this.mScreenHeight;
                } else if (top < (-height)) {
                    i = -SliderPanel.this.mScreenHeight;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(view.getLeft(), i);
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float abs = 1.0f - (((float) Math.abs(i2)) / ((float) SliderPanel.this.mScreenHeight));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(abs);
                }
                SliderPanel.this.applyScrim(abs);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getTop() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.mHorizontalCallback = new Callback() {
            public boolean tryCaptureView(View view, int i) {
                boolean z;
                if (!SliderPanel.this.mConfig.isEdgeOnly() || SliderPanel.this.mDragHelper.isEdgeTouched(SliderPanel.this.mEdgePosition, i)) {
                    z = true;
                } else {
                    z = false;
                }
                return view.getId() == SliderPanel.this.mDecorView.getId() && z;
            }

            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return SliderPanel.clamp(i, -SliderPanel.this.mScreenWidth, SliderPanel.this.mScreenWidth);
            }

            public int getViewHorizontalDragRange(View view) {
                return SliderPanel.this.mScreenWidth;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = 0;
                super.onViewReleased(view, f, f2);
                int left = view.getLeft();
                int width = (int) (((float) SliderPanel.this.getWidth()) * SliderPanel.this.mConfig.getDistanceThreshold());
                int i2 = Math.abs(f2) > SliderPanel.this.mConfig.getVelocityThreshold() ? 1 : 0;
                if (f > 0.0f) {
                    if (Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = SliderPanel.this.mScreenWidth;
                    } else if (left > width) {
                        i = SliderPanel.this.mScreenWidth;
                    }
                } else if (f < 0.0f) {
                    if (Math.abs(f) > SliderPanel.this.mConfig.getVelocityThreshold() && i2 == 0) {
                        i = -SliderPanel.this.mScreenWidth;
                    } else if (left < (-width)) {
                        i = -SliderPanel.this.mScreenWidth;
                    }
                } else if (left > width) {
                    i = SliderPanel.this.mScreenWidth;
                } else if (left < (-width)) {
                    i = -SliderPanel.this.mScreenWidth;
                }
                SliderPanel.this.mDragHelper.settleCapturedViewAt(i, view.getTop());
                SliderPanel.this.invalidate();
            }

            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                super.onViewPositionChanged(view, i, i2, i3, i4);
                float abs = 1.0f - (((float) Math.abs(i)) / ((float) SliderPanel.this.mScreenWidth));
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onSlideChange(abs);
                }
                SliderPanel.this.applyScrim(abs);
            }

            public void onViewDragStateChanged(int i) {
                super.onViewDragStateChanged(i);
                if (SliderPanel.this.mListener != null) {
                    SliderPanel.this.mListener.onStateChanged(i);
                }
                switch (i) {
                    case 0:
                        if (SliderPanel.this.mDecorView.getLeft() == 0) {
                            if (SliderPanel.this.mListener != null) {
                                SliderPanel.this.mListener.onOpened();
                                return;
                            }
                            return;
                        } else if (SliderPanel.this.mListener != null) {
                            SliderPanel.this.mListener.onClosed();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        };
    }

    public SliderPanel(Context context, View view) {
        this(context, view, null);
    }

    public SliderPanel(Context context, View view, SlidrConfig slidrConfig) {
        super(context);
        this.mIsLocked = false;
        this.mIsEdgeTouched = false;
        this.mLeftCallback = /* anonymous class already generated */;
        this.mRightCallback = /* anonymous class already generated */;
        this.mTopCallback = /* anonymous class already generated */;
        this.mBottomCallback = /* anonymous class already generated */;
        this.mVerticalCallback = /* anonymous class already generated */;
        this.mHorizontalCallback = /* anonymous class already generated */;
        this.mDecorView = view;
        if (slidrConfig == null) {
            slidrConfig = new Builder().build();
        }
        this.mConfig = slidrConfig;
        init();
    }

    public void setOnPanelSlideListener(OnPanelSlideListener onPanelSlideListener) {
        this.mListener = onPanelSlideListener;
    }

    private void init() {
        Callback callback;
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        float f = 400.0f * getResources().getDisplayMetrics().density;
        switch (this.mConfig.getPosition()) {
            case LEFT:
                callback = this.mLeftCallback;
                this.mEdgePosition = 1;
                break;
            case RIGHT:
                callback = this.mRightCallback;
                this.mEdgePosition = 2;
                break;
            case TOP:
                callback = this.mTopCallback;
                this.mEdgePosition = 4;
                break;
            case BOTTOM:
                callback = this.mBottomCallback;
                this.mEdgePosition = 8;
                break;
            case VERTICAL:
                callback = this.mVerticalCallback;
                this.mEdgePosition = 12;
                break;
            case HORIZONTAL:
                callback = this.mHorizontalCallback;
                this.mEdgePosition = 3;
                break;
            default:
                callback = this.mLeftCallback;
                this.mEdgePosition = 1;
                break;
        }
        this.mDragHelper = ViewDragHelper.create(this, this.mConfig.getSensitivity(), callback);
        this.mDragHelper.setMinVelocity(f);
        this.mDragHelper.setEdgeTrackingEnabled(this.mEdgePosition);
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        this.mDimView = new View(getContext());
        this.mDimView.setBackgroundColor(this.mConfig.getScrimColor());
        this.mDimView.setAlpha(this.mConfig.getScrimStartAlpha());
        addView(this.mDimView);
        post(new Runnable() {
            public void run() {
                SliderPanel.this.mScreenHeight = SliderPanel.this.getHeight();
            }
        });
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mIsLocked) {
            return false;
        }
        boolean shouldInterceptTouchEvent;
        if (this.mConfig.isEdgeOnly()) {
            this.mIsEdgeTouched = canDragFromEdge(motionEvent);
        }
        try {
            shouldInterceptTouchEvent = this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        } catch (Exception e) {
            shouldInterceptTouchEvent = false;
        }
        if (!shouldInterceptTouchEvent || this.mIsLocked) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mIsLocked) {
            return false;
        }
        try {
            this.mDragHelper.processTouchEvent(motionEvent);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void lock() {
        this.mDragHelper.abort();
        this.mIsLocked = true;
    }

    public void unlock() {
        this.mDragHelper.abort();
        this.mIsLocked = false;
    }

    private boolean canDragFromEdge(MotionEvent motionEvent) {
        boolean z = false;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (this.mConfig.getPosition()) {
            case LEFT:
                if (x >= this.mConfig.getEdgeSize((float) getWidth())) {
                    return false;
                }
                return true;
            case RIGHT:
                if (x <= ((float) getWidth()) - this.mConfig.getEdgeSize((float) getWidth())) {
                    return false;
                }
                return true;
            case TOP:
                if (y >= this.mConfig.getEdgeSize((float) getHeight())) {
                    return false;
                }
                return true;
            case BOTTOM:
                if (y <= ((float) getHeight()) - this.mConfig.getEdgeSize((float) getHeight())) {
                    return false;
                }
                return true;
            case VERTICAL:
                if (y < this.mConfig.getEdgeSize((float) getHeight()) || y > ((float) getHeight()) - this.mConfig.getEdgeSize((float) getHeight())) {
                    z = true;
                }
                return z;
            case HORIZONTAL:
                if (x < this.mConfig.getEdgeSize((float) getWidth()) || x > ((float) getWidth()) - this.mConfig.getEdgeSize((float) getWidth())) {
                    z = true;
                }
                return z;
            default:
                return false;
        }
    }

    public void applyScrim(float f) {
        this.mDimView.setAlpha(((this.mConfig.getScrimStartAlpha() - this.mConfig.getScrimEndAlpha()) * f) + this.mConfig.getScrimEndAlpha());
    }

    public static int clamp(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i3, i));
    }
}
