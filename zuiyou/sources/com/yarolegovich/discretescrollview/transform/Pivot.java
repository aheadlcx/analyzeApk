package com.yarolegovich.discretescrollview.transform;

import android.view.View;

public class Pivot {
    private int a;
    private int b;

    public enum X {
        LEFT {
            public Pivot create() {
                return new Pivot(0, 0);
            }
        },
        CENTER {
            public Pivot create() {
                return new Pivot(0, -1);
            }
        },
        RIGHT {
            public Pivot create() {
                return new Pivot(0, -2);
            }
        };

        public abstract Pivot create();
    }

    public enum Y {
        TOP {
            public Pivot create() {
                return new Pivot(1, 0);
            }
        },
        CENTER {
            public Pivot create() {
                return new Pivot(1, -1);
            }
        },
        BOTTOM {
            public Pivot create() {
                return new Pivot(1, -2);
            }
        };

        public abstract Pivot create();
    }

    public Pivot(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void a(View view) {
        if (this.a == 0) {
            switch (this.b) {
                case -2:
                    view.setPivotX((float) view.getWidth());
                    return;
                case -1:
                    view.setPivotX(((float) view.getWidth()) * 0.5f);
                    return;
                default:
                    view.setPivotX((float) this.b);
                    return;
            }
        } else if (this.a == 1) {
            switch (this.b) {
                case -2:
                    view.setPivotY((float) view.getHeight());
                    return;
                case -1:
                    view.setPivotY(((float) view.getHeight()) * 0.5f);
                    return;
                default:
                    view.setPivotY((float) this.b);
                    return;
            }
        }
    }
}
