package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
    private final a a;

    interface a {
        boolean isLongpressEnabled();

        boolean onTouchEvent(MotionEvent motionEvent);

        void setIsLongpressEnabled(boolean z);

        void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener);
    }

    static class b implements a {
        private static final int j = ViewConfiguration.getLongPressTimeout();
        private static final int k = ViewConfiguration.getTapTimeout();
        private static final int l = ViewConfiguration.getDoubleTapTimeout();
        final OnGestureListener a;
        OnDoubleTapListener b;
        boolean c;
        boolean d;
        MotionEvent e;
        private int f;
        private int g;
        private int h;
        private int i;
        private final Handler m;
        private boolean n;
        private boolean o;
        private boolean p;
        private MotionEvent q;
        private boolean r;
        private float s;
        private float t;
        private float u;
        private float v;
        private boolean w;
        private VelocityTracker x;

        private class a extends Handler {
            final /* synthetic */ b a;

            a(b bVar) {
                this.a = bVar;
            }

            a(b bVar, Handler handler) {
                this.a = bVar;
                super(handler.getLooper());
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.a.onShowPress(this.a.e);
                        return;
                    case 2:
                        this.a.a();
                        return;
                    case 3:
                        if (this.a.b == null) {
                            return;
                        }
                        if (this.a.c) {
                            this.a.d = true;
                            return;
                        } else {
                            this.a.b.onSingleTapConfirmed(this.a.e);
                            return;
                        }
                    default:
                        throw new RuntimeException("Unknown message " + message);
                }
            }
        }

        public b(Context context, OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.m = new a(this, handler);
            } else {
                this.m = new a(this);
            }
            this.a = onGestureListener;
            if (onGestureListener instanceof OnDoubleTapListener) {
                setOnDoubleTapListener((OnDoubleTapListener) onGestureListener);
            }
            a(context);
        }

        private void a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            } else if (this.a == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            } else {
                this.w = true;
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                this.h = viewConfiguration.getScaledMinimumFlingVelocity();
                this.i = viewConfiguration.getScaledMaximumFlingVelocity();
                this.f = scaledTouchSlop * scaledTouchSlop;
                this.g = scaledDoubleTapSlop * scaledDoubleTapSlop;
            }
        }

        public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
            this.b = onDoubleTapListener;
        }

        public void setIsLongpressEnabled(boolean z) {
            this.w = z;
        }

        public boolean isLongpressEnabled() {
            return this.w;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int i;
            int action = motionEvent.getAction();
            if (this.x == null) {
                this.x = VelocityTracker.obtain();
            }
            this.x.addMovement(motionEvent);
            boolean z = (action & 255) == 6;
            int actionIndex = z ? motionEvent.getActionIndex() : -1;
            int pointerCount = motionEvent.getPointerCount();
            float f = 0.0f;
            float f2 = 0.0f;
            for (i = 0; i < pointerCount; i++) {
                if (actionIndex != i) {
                    f2 += motionEvent.getX(i);
                    f += motionEvent.getY(i);
                }
            }
            actionIndex = z ? pointerCount - 1 : pointerCount;
            f2 /= (float) actionIndex;
            f /= (float) actionIndex;
            boolean hasMessages;
            float yVelocity;
            float xVelocity;
            switch (action & 255) {
                case 0:
                    if (this.b != null) {
                        hasMessages = this.m.hasMessages(3);
                        if (hasMessages) {
                            this.m.removeMessages(3);
                        }
                        if (this.e == null || this.q == null || !hasMessages || !a(this.e, this.q, motionEvent)) {
                            this.m.sendEmptyMessageDelayed(3, (long) l);
                        } else {
                            this.r = true;
                            actionIndex = (this.b.onDoubleTap(this.e) | 0) | this.b.onDoubleTapEvent(motionEvent);
                            this.s = f2;
                            this.u = f2;
                            this.t = f;
                            this.v = f;
                            if (this.e != null) {
                                this.e.recycle();
                            }
                            this.e = MotionEvent.obtain(motionEvent);
                            this.o = true;
                            this.p = true;
                            this.c = true;
                            this.n = false;
                            this.d = false;
                            if (this.w) {
                                this.m.removeMessages(2);
                                this.m.sendEmptyMessageAtTime(2, (this.e.getDownTime() + ((long) k)) + ((long) j));
                            }
                            this.m.sendEmptyMessageAtTime(1, this.e.getDownTime() + ((long) k));
                            return actionIndex | this.a.onDown(motionEvent);
                        }
                    }
                    actionIndex = 0;
                    this.s = f2;
                    this.u = f2;
                    this.t = f;
                    this.v = f;
                    if (this.e != null) {
                        this.e.recycle();
                    }
                    this.e = MotionEvent.obtain(motionEvent);
                    this.o = true;
                    this.p = true;
                    this.c = true;
                    this.n = false;
                    this.d = false;
                    if (this.w) {
                        this.m.removeMessages(2);
                        this.m.sendEmptyMessageAtTime(2, (this.e.getDownTime() + ((long) k)) + ((long) j));
                    }
                    this.m.sendEmptyMessageAtTime(1, this.e.getDownTime() + ((long) k));
                    return actionIndex | this.a.onDown(motionEvent);
                case 1:
                    this.c = false;
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    if (this.r) {
                        hasMessages = this.b.onDoubleTapEvent(motionEvent) | 0;
                    } else if (this.n) {
                        this.m.removeMessages(3);
                        this.n = false;
                        hasMessages = false;
                    } else if (this.o) {
                        hasMessages = this.a.onSingleTapUp(motionEvent);
                        if (this.d && this.b != null) {
                            this.b.onSingleTapConfirmed(motionEvent);
                        }
                    } else {
                        VelocityTracker velocityTracker = this.x;
                        int pointerId = motionEvent.getPointerId(0);
                        velocityTracker.computeCurrentVelocity(1000, (float) this.i);
                        yVelocity = velocityTracker.getYVelocity(pointerId);
                        xVelocity = velocityTracker.getXVelocity(pointerId);
                        hasMessages = (Math.abs(yVelocity) > ((float) this.h) || Math.abs(xVelocity) > ((float) this.h)) ? this.a.onFling(this.e, motionEvent, xVelocity, yVelocity) : false;
                    }
                    if (this.q != null) {
                        this.q.recycle();
                    }
                    this.q = obtain;
                    if (this.x != null) {
                        this.x.recycle();
                        this.x = null;
                    }
                    this.r = false;
                    this.d = false;
                    this.m.removeMessages(1);
                    this.m.removeMessages(2);
                    return hasMessages;
                case 2:
                    if (this.n) {
                        return false;
                    }
                    xVelocity = this.s - f2;
                    yVelocity = this.t - f;
                    if (this.r) {
                        return false | this.b.onDoubleTapEvent(motionEvent);
                    }
                    if (this.o) {
                        i = (int) (f2 - this.u);
                        int i2 = (int) (f - this.v);
                        i = (i * i) + (i2 * i2);
                        if (i > this.f) {
                            hasMessages = this.a.onScroll(this.e, motionEvent, xVelocity, yVelocity);
                            this.s = f2;
                            this.t = f;
                            this.o = false;
                            this.m.removeMessages(3);
                            this.m.removeMessages(1);
                            this.m.removeMessages(2);
                        } else {
                            hasMessages = false;
                        }
                        if (i > this.f) {
                            this.p = false;
                        }
                        return hasMessages;
                    } else if (Math.abs(xVelocity) < 1.0f && Math.abs(yVelocity) < 1.0f) {
                        return false;
                    } else {
                        boolean onScroll = this.a.onScroll(this.e, motionEvent, xVelocity, yVelocity);
                        this.s = f2;
                        this.t = f;
                        return onScroll;
                    }
                case 3:
                    b();
                    return false;
                case 5:
                    this.s = f2;
                    this.u = f2;
                    this.t = f;
                    this.v = f;
                    c();
                    return false;
                case 6:
                    this.s = f2;
                    this.u = f2;
                    this.t = f;
                    this.v = f;
                    this.x.computeCurrentVelocity(1000, (float) this.i);
                    int actionIndex2 = motionEvent.getActionIndex();
                    actionIndex = motionEvent.getPointerId(actionIndex2);
                    f2 = this.x.getXVelocity(actionIndex);
                    float yVelocity2 = this.x.getYVelocity(actionIndex);
                    for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                        if (actionIndex != actionIndex2) {
                            int pointerId2 = motionEvent.getPointerId(actionIndex);
                            if ((this.x.getYVelocity(pointerId2) * yVelocity2) + (this.x.getXVelocity(pointerId2) * f2) < 0.0f) {
                                this.x.clear();
                                return false;
                            }
                        }
                    }
                    return false;
                default:
                    return false;
            }
        }

        private void b() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.x.recycle();
            this.x = null;
            this.r = false;
            this.c = false;
            this.o = false;
            this.p = false;
            this.d = false;
            if (this.n) {
                this.n = false;
            }
        }

        private void c() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.r = false;
            this.o = false;
            this.p = false;
            this.d = false;
            if (this.n) {
                this.n = false;
            }
        }

        private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.p || motionEvent3.getEventTime() - motionEvent2.getEventTime() > ((long) l)) {
                return false;
            }
            int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            if ((x * x) + (y * y) < this.g) {
                return true;
            }
            return false;
        }

        void a() {
            this.m.removeMessages(3);
            this.d = false;
            this.n = true;
            this.a.onLongPress(this.e);
        }
    }

    static class c implements a {
        private final GestureDetector a;

        public c(Context context, OnGestureListener onGestureListener, Handler handler) {
            this.a = new GestureDetector(context, onGestureListener, handler);
        }

        public boolean isLongpressEnabled() {
            return this.a.isLongpressEnabled();
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.a.onTouchEvent(motionEvent);
        }

        public void setIsLongpressEnabled(boolean z) {
            this.a.setIsLongpressEnabled(z);
        }

        public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
            this.a.setOnDoubleTapListener(onDoubleTapListener);
        }
    }

    public GestureDetectorCompat(Context context, OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public GestureDetectorCompat(Context context, OnGestureListener onGestureListener, Handler handler) {
        if (VERSION.SDK_INT > 17) {
            this.a = new c(context, onGestureListener, handler);
        } else {
            this.a = new b(context, onGestureListener, handler);
        }
    }

    public boolean isLongpressEnabled() {
        return this.a.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a.onTouchEvent(motionEvent);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.a.setIsLongpressEnabled(z);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.a.setOnDoubleTapListener(onDoubleTapListener);
    }
}
