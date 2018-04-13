package com.edmodo.cropper.cropwindow.edge;

import android.graphics.Rect;
import android.view.View;
import com.edmodo.cropper.a.a;

public enum Edge {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;
    
    public static final int MIN_CROP_LENGTH_PX = 40;
    private float mCoordinate;

    public void setCoordinate(float f) {
        this.mCoordinate = f;
    }

    public void offset(float f) {
        this.mCoordinate += f;
    }

    public float getCoordinate() {
        return this.mCoordinate;
    }

    public void adjustCoordinate(float f, float f2, Rect rect, float f3, float f4) {
        switch (this) {
            case LEFT:
                this.mCoordinate = adjustLeft(f, rect, f3, f4);
                return;
            case TOP:
                this.mCoordinate = adjustTop(f2, rect, f3, f4);
                return;
            case RIGHT:
                this.mCoordinate = adjustRight(f, rect, f3, f4);
                return;
            case BOTTOM:
                this.mCoordinate = adjustBottom(f2, rect, f3, f4);
                return;
            default:
                return;
        }
    }

    public void adjustCoordinate(float f) {
        float coordinate = LEFT.getCoordinate();
        float coordinate2 = TOP.getCoordinate();
        float coordinate3 = RIGHT.getCoordinate();
        float coordinate4 = BOTTOM.getCoordinate();
        switch (this) {
            case LEFT:
                this.mCoordinate = a.b(coordinate2, coordinate3, coordinate4, f);
                return;
            case TOP:
                this.mCoordinate = a.c(coordinate, coordinate3, coordinate4, f);
                return;
            case RIGHT:
                this.mCoordinate = a.d(coordinate, coordinate2, coordinate4, f);
                return;
            case BOTTOM:
                this.mCoordinate = a.e(coordinate, coordinate2, coordinate3, f);
                return;
            default:
                return;
        }
    }

    public boolean isNewRectangleOutOfBounds(Edge edge, Rect rect, float f) {
        float snapOffset = edge.snapOffset(rect);
        float f2;
        float coordinate;
        float coordinate2;
        float f3;
        switch (this) {
            case LEFT:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    coordinate = BOTTOM.getCoordinate() - snapOffset;
                    coordinate2 = RIGHT.getCoordinate();
                    return isOutOfBounds(f2, a.b(f2, coordinate2, coordinate, f), coordinate, coordinate2, rect);
                } else if (edge.equals(BOTTOM)) {
                    coordinate = (float) rect.bottom;
                    f2 = TOP.getCoordinate() - snapOffset;
                    coordinate2 = RIGHT.getCoordinate();
                    return isOutOfBounds(f2, a.b(f2, coordinate2, coordinate, f), coordinate, coordinate2, rect);
                }
                break;
            case TOP:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    coordinate2 = RIGHT.getCoordinate() - snapOffset;
                    coordinate = BOTTOM.getCoordinate();
                    return isOutOfBounds(a.c(f3, coordinate2, coordinate, f), f3, coordinate, coordinate2, rect);
                } else if (edge.equals(RIGHT)) {
                    coordinate2 = (float) rect.right;
                    f3 = LEFT.getCoordinate() - snapOffset;
                    coordinate = BOTTOM.getCoordinate();
                    return isOutOfBounds(a.c(f3, coordinate2, coordinate, f), f3, coordinate, coordinate2, rect);
                }
                break;
            case RIGHT:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    coordinate = BOTTOM.getCoordinate() - snapOffset;
                    f3 = LEFT.getCoordinate();
                    return isOutOfBounds(f2, f3, coordinate, a.d(f3, f2, coordinate, f), rect);
                } else if (edge.equals(BOTTOM)) {
                    coordinate = (float) rect.bottom;
                    f2 = TOP.getCoordinate() - snapOffset;
                    f3 = LEFT.getCoordinate();
                    return isOutOfBounds(f2, f3, coordinate, a.d(f3, f2, coordinate, f), rect);
                }
                break;
            case BOTTOM:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    coordinate2 = RIGHT.getCoordinate() - snapOffset;
                    f2 = TOP.getCoordinate();
                    return isOutOfBounds(f2, f3, a.e(f3, f2, coordinate2, f), coordinate2, rect);
                } else if (edge.equals(RIGHT)) {
                    coordinate2 = (float) rect.right;
                    f3 = LEFT.getCoordinate() - snapOffset;
                    f2 = TOP.getCoordinate();
                    return isOutOfBounds(f2, f3, a.e(f3, f2, coordinate2, f), coordinate2, rect);
                }
                break;
        }
        return true;
    }

    private boolean isOutOfBounds(float f, float f2, float f3, float f4, Rect rect) {
        return f < ((float) rect.top) || f2 < ((float) rect.left) || f3 > ((float) rect.bottom) || f4 > ((float) rect.right);
    }

    public float snapToRect(Rect rect) {
        float f = this.mCoordinate;
        switch (this) {
            case LEFT:
                this.mCoordinate = (float) rect.left;
                break;
            case TOP:
                this.mCoordinate = (float) rect.top;
                break;
            case RIGHT:
                this.mCoordinate = (float) rect.right;
                break;
            case BOTTOM:
                this.mCoordinate = (float) rect.bottom;
                break;
        }
        return this.mCoordinate - f;
    }

    public float snapOffset(Rect rect) {
        float f;
        float f2 = this.mCoordinate;
        switch (this) {
            case LEFT:
                f = (float) rect.left;
                break;
            case TOP:
                f = (float) rect.top;
                break;
            case RIGHT:
                f = (float) rect.right;
                break;
            case BOTTOM:
                f = (float) rect.bottom;
                break;
            default:
                f = f2;
                break;
        }
        return f - f2;
    }

    public void snapToView(View view) {
        switch (this) {
            case LEFT:
                this.mCoordinate = 0.0f;
                return;
            case TOP:
                this.mCoordinate = 0.0f;
                return;
            case RIGHT:
                this.mCoordinate = (float) view.getWidth();
                return;
            case BOTTOM:
                this.mCoordinate = (float) view.getHeight();
                return;
            default:
                return;
        }
    }

    public static float getWidth() {
        return RIGHT.getCoordinate() - LEFT.getCoordinate();
    }

    public static float getHeight() {
        return BOTTOM.getCoordinate() - TOP.getCoordinate();
    }

    public boolean isOutsideMargin(Rect rect, float f) {
        switch (this) {
            case LEFT:
                if (this.mCoordinate - ((float) rect.left) >= f) {
                    return false;
                }
                return true;
            case TOP:
                if (this.mCoordinate - ((float) rect.top) >= f) {
                    return false;
                }
                return true;
            case RIGHT:
                if (((float) rect.right) - this.mCoordinate >= f) {
                    return false;
                }
                return true;
            case BOTTOM:
                if (((float) rect.bottom) - this.mCoordinate >= f) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public boolean isOutsideFrame(Rect rect) {
        switch (this) {
            case LEFT:
                if (((double) (this.mCoordinate - ((float) rect.left))) >= 0.0d) {
                    return false;
                }
                return true;
            case TOP:
                if (((double) (this.mCoordinate - ((float) rect.top))) >= 0.0d) {
                    return false;
                }
                return true;
            case RIGHT:
                if (((double) (((float) rect.right) - this.mCoordinate)) >= 0.0d) {
                    return false;
                }
                return true;
            case BOTTOM:
                if (((double) (((float) rect.bottom) - this.mCoordinate)) >= 0.0d) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    private static float adjustLeft(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.left) < f2) {
            return (float) rect.left;
        }
        float coordinate;
        if (f >= RIGHT.getCoordinate() - 40.0f) {
            coordinate = RIGHT.getCoordinate() - 40.0f;
        } else {
            coordinate = Float.POSITIVE_INFINITY;
        }
        if ((RIGHT.getCoordinate() - f) / f3 <= 40.0f) {
            f4 = RIGHT.getCoordinate() - (40.0f * f3);
        }
        return Math.min(f, Math.min(coordinate, f4));
    }

    private static float adjustRight(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.right) - f < f2) {
            return (float) rect.right;
        }
        float coordinate;
        if (f <= LEFT.getCoordinate() + 40.0f) {
            coordinate = LEFT.getCoordinate() + 40.0f;
        } else {
            coordinate = Float.NEGATIVE_INFINITY;
        }
        if ((f - LEFT.getCoordinate()) / f3 <= 40.0f) {
            f4 = LEFT.getCoordinate() + (40.0f * f3);
        }
        return Math.max(f, Math.max(coordinate, f4));
    }

    private static float adjustTop(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.top) < f2) {
            return (float) rect.top;
        }
        float coordinate;
        if (f >= BOTTOM.getCoordinate() - 40.0f) {
            coordinate = BOTTOM.getCoordinate() - 40.0f;
        } else {
            coordinate = Float.POSITIVE_INFINITY;
        }
        if ((BOTTOM.getCoordinate() - f) * f3 <= 40.0f) {
            f4 = BOTTOM.getCoordinate() - (40.0f / f3);
        }
        return Math.min(f, Math.min(coordinate, f4));
    }

    private static float adjustBottom(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.bottom) - f < f2) {
            return (float) rect.bottom;
        }
        float coordinate;
        if (f <= TOP.getCoordinate() + 40.0f) {
            coordinate = TOP.getCoordinate() + 40.0f;
        } else {
            coordinate = Float.NEGATIVE_INFINITY;
        }
        if ((f - TOP.getCoordinate()) * f3 <= 40.0f) {
            f4 = TOP.getCoordinate() + (40.0f / f3);
        }
        return Math.max(f, Math.max(f4, coordinate));
    }
}
