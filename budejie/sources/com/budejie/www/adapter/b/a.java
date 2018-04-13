package com.budejie.www.adapter.b;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.util.an;

public class a {
    public static int a = BudejieApplication.g.getResources().getDisplayMetrics().widthPixels;
    public static int b = BudejieApplication.g.getResources().getDisplayMetrics().heightPixels;
    public static int c = BudejieApplication.g.getResources().getDimensionPixelSize(R.dimen.post_margin_left);
    public static int d = (c * 2);
    public static int e = (((a - d) - BudejieApplication.g.getResources().getDimensionPixelOffset(R.dimen.circle_progress_bar_width)) / 2);
    public static int f = an.t(BudejieApplication.g);
    public static int g;

    static {
        if (PostsActivity.k != 0) {
            g = PostsActivity.k;
            return;
        }
        g = (b - f) - (BudejieApplication.g.getResources().getDimensionPixelOffset(R.dimen.navigation_height) * 2);
    }

    public static void a(ImageView imageView, int i) {
        imageView.setScaleType(ScaleType.FIT_XY);
        LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = a - d;
        layoutParams.height = a - d;
        imageView.setLayoutParams(layoutParams);
    }

    public static void a(ImageView imageView, int i, int i2) {
        imageView.setScaleType(ScaleType.FIT_XY);
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            layoutParams.width = -1;
            layoutParams.height = -2;
            imageView.setAdjustViewBounds(true);
        } else {
            layoutParams.width = a - d;
            layoutParams.height = (layoutParams.width * i2) / i;
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static void b(ImageView imageView, int i, int i2) {
        int i3 = a - d;
        imageView.setScaleType(ScaleType.FIT_CENTER);
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else if (i2 >= i) {
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (i2 * i3) / i;
            layoutParams.width = i3;
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static void c(ImageView imageView, int i, int i2) {
        int i3 = (a * 2) / 3;
        int i4 = a / 3;
        imageView.setScaleType(ScaleType.FIT_CENTER);
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            layoutParams.height = i4;
            layoutParams.width = i4;
        } else if (i2 >= i / 2) {
            layoutParams.height = i4;
            layoutParams.width = (i4 * i) / i2;
        } else {
            layoutParams.width = i3;
            layoutParams.height = (i3 * i2) / i;
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static void d(ImageView imageView, int i, int i2) {
        imageView.setScaleType(ScaleType.FIT_CENTER);
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            int i3 = a;
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (a * i2) / i;
            layoutParams.width = a;
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static int e(ImageView imageView, int i, int i2) {
        int i3 = a;
        imageView.setScaleType(ScaleType.FIT_CENTER);
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            imageView.setScaleType(ScaleType.CENTER_CROP);
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (i2 * i3) / i;
            layoutParams.width = i3;
        }
        imageView.setLayoutParams(layoutParams);
        return layoutParams.height;
    }

    public static int a(int i, int i2) {
        int i3 = a;
        if (i == 0 || i2 == 0) {
            return i3;
        }
        return (i3 * i2) / i;
    }

    public static void a(View view, int i, int i2) {
        int i3 = a - d;
        LayoutParams layoutParams = view.getLayoutParams();
        if (i == 0 || i2 == 0 || i2 >= i) {
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (i2 * i3) / i;
            layoutParams.width = i3;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void b(View view, int i, int i2) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (i == 0 || i2 == 0) {
            int i3 = a;
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (a * i2) / i;
            layoutParams.width = a;
        }
        if (layoutParams.height > b - f) {
            layoutParams.height = b - f;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void f(ImageView imageView, int i, int i2) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            int i3 = a;
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (a * i2) / i;
            layoutParams.width = a;
        }
        if (layoutParams.height > b - f) {
            layoutParams.height = b - f;
            imageView.setScaleType(ScaleType.FIT_XY);
        }
        imageView.setLayoutParams(layoutParams);
    }

    public static void g(ImageView imageView, int i, int i2) {
        int i3;
        int i4;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        if (i == 0 || i2 == 0) {
            i3 = a;
            i4 = i3;
        } else {
            i4 = (a * i2) / i;
            i3 = a;
        }
        if (i4 > b - f) {
            i4 = b - f;
        }
        layoutParams.height = a;
        layoutParams.width = (i3 * layoutParams.height) / i4;
        imageView.setLayoutParams(layoutParams);
    }

    public static void c(View view, int i, int i2) {
        int i3 = a;
        LayoutParams layoutParams = view.getLayoutParams();
        if (i == 0 || i2 == 0) {
            layoutParams.height = i3;
            layoutParams.width = i3;
        } else {
            layoutParams.height = (i2 * i3) / i;
            layoutParams.width = i3;
        }
        view.setLayoutParams(layoutParams);
    }

    public static boolean b(int i, int i2) {
        if (i <= 0) {
            return false;
        }
        if (i2 > 4096 && i2 > i) {
            return true;
        }
        if ((i2 <= 4096 || i <= i2 || i >= (a - d) * 2) && ((a - d) * i2) / i <= g * PostsActivity.l) {
            return false;
        }
        return true;
    }
}
