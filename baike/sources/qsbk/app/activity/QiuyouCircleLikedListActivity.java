package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.QiushiNotificationListFragment;
import qsbk.app.fragments.QiuyouquanNotificationListFragment;
import qsbk.app.utils.ToastAndDialog;

public class QiuyouCircleLikedListActivity extends BaseActionBarActivity {
    public static final String AT_INFO = "_from_at_info_";
    public static final String COMMENT_LIEK = "_from_comemnt_like_";
    public static final String FORWARD = "_forword";
    public static final String KEY_IS_PROMOTE = "_is_promote_";
    public static final String KEY_NEW_COUNT = "_new_count";
    public static final String KEY_SHOW_SMILE_OR_LIKE = "_show_smile_or_like_";
    public static final String KEY_TYPE = "_title";
    public static final int TYPE_QIUSHI = 0;
    public static final int TYPE_QIUYOUQUAN = 1;
    private int a;
    private int b;
    private boolean c;
    private boolean d;
    private boolean e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static Intent gotoQiushiIntent(Context context, int i) {
        return makeIntent(context, 0, i);
    }

    public static Intent gotoQiushiIntent(Context context, int i, boolean z) {
        return makeIntent(context, 0, i, z);
    }

    public static Intent gotoQiushiIntent(Context context, int i, int i2) {
        return makeIntent(context, 0, i, i2);
    }

    public static Intent gotoQiuyouquanIntent(Context context, int i) {
        return makeIntent(context, 1, i);
    }

    public static Intent gotoQiuyouquanIntent(Context context, int i, boolean z) {
        return makeIntent(z, context, 1, i);
    }

    public static Intent makeIntent(Context context, int i, int i2) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        return intent;
    }

    public static Intent makeIntent(boolean z, Context context, int i, int i2) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        intent.putExtra(AT_INFO, z);
        return intent;
    }

    public static Intent makeIntent(boolean z, boolean z2, Context context, int i, int i2) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        intent.putExtra(COMMENT_LIEK, z);
        return intent;
    }

    public static Intent makeForwardsIntent(Context context, int i) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_title", 1);
        intent.putExtra("_new_count", i);
        intent.putExtra(FORWARD, true);
        return intent;
    }

    public static Intent makeIntent(Context context, int i, int i2, boolean z) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        intent.putExtra("_is_promote_", z);
        return intent;
    }

    public static Intent makeIntent(Context context, int i, int i2, int i3) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiuyouCircleLikedListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        if (i3 == 2) {
            intent.putExtra(KEY_SHOW_SMILE_OR_LIKE, 1);
            return intent;
        } else if (i3 == 3) {
            intent.putExtra(KEY_SHOW_SMILE_OR_LIKE, 2);
            return intent;
        } else if (i3 != 5) {
            return intent;
        } else {
            intent.putExtra(KEY_SHOW_SMILE_OR_LIKE, 3);
            return intent;
        }
    }

    public static void gotoQiushi(Context context, int i) {
        if (context != null) {
            context.startActivity(gotoQiushiIntent(context, i));
        }
    }

    public static void gotoQiushi(Context context, int i, int i2) {
        if (context != null) {
            context.startActivity(gotoQiushiIntent(context, i, i2));
        }
    }

    public static void gotoQiuyouquan(Context context, int i) {
        if (context != null) {
            context.startActivity(makeIntent(context, 1, i));
        }
    }

    public static void gotoQiuyouquan(Context context, int i, boolean z) {
        if (context != null) {
            context.startActivity(makeIntent(z, context, 1, i));
        }
    }

    public static void gotoQiuyouquan(Context context, int i, boolean z, boolean z2) {
        if (context != null) {
            context.startActivity(makeIntent(z2, z, context, 1, i));
        }
    }

    public static void gotoQiuyouquanForward(Context context, int i) {
        if (context != null) {
            context.startActivity(makeForwardsIntent(context, i));
        }
    }

    protected String f() {
        return this.b == 0 ? "糗事消息" : "糗友圈消息";
    }

    public void finish() {
        if (isTaskRoot()) {
            startActivity(new Intent(this, MainActivity.class));
        }
        super.finish();
    }

    protected int a() {
        return R.layout.activity_fragment_container;
    }

    protected void a(Bundle bundle) {
        if (bundle == null) {
            setActionbarBackable();
            int intExtra = getIntent().getIntExtra("_new_count", 0);
            this.b = getIntent().getIntExtra("_title", 0);
            boolean booleanExtra = getIntent().getBooleanExtra("_is_promote_", false);
            this.a = getIntent().getIntExtra(KEY_SHOW_SMILE_OR_LIKE, 0);
            this.c = getIntent().getBooleanExtra(AT_INFO, false);
            this.d = getIntent().getBooleanExtra(COMMENT_LIEK, false);
            this.e = getIntent().getBooleanExtra(FORWARD, false);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (this.b == 0) {
                if (booleanExtra) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "恭喜您糗事入围精选！", Integer.valueOf(0)).show();
                }
                if (this.a > 0) {
                    beginTransaction.replace(R.id.container, QiushiNotificationListFragment.newInstance(intExtra, this.a));
                } else {
                    beginTransaction.replace(R.id.container, QiushiNotificationListFragment.newInstance(intExtra));
                }
            } else if (this.b == 1) {
                if (this.c) {
                    beginTransaction.replace(R.id.container, QiuyouquanNotificationListFragment.newInstance(intExtra, false, true));
                } else if (this.d) {
                    beginTransaction.replace(R.id.container, QiuyouquanNotificationListFragment.newInstance(intExtra, false, false, true));
                } else if (this.e) {
                    beginTransaction.replace(R.id.container, QiuyouquanNotificationListFragment.newInstance(intExtra, false, false, false, true));
                } else if (!(this.c || this.d)) {
                    beginTransaction.replace(R.id.container, QiuyouquanNotificationListFragment.newInstance(intExtra, true));
                }
            }
            beginTransaction.commit();
        }
    }
}
