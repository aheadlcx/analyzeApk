package qsbk.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import qsbk.app.Constants;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.DebugUtil;

public class DiggerFragment extends QiuYouFragment {
    public static final String KEY_ARTICLE_ID = "article_id";
    private static final String l = DiggerFragment.class.getSimpleName();
    private String m;

    public DiggerFragment() {
        super(Constants.REL_GET_FRIENDS, DiggerFragment.class.getSimpleName());
    }

    public static DiggerFragment newInstance(String str) {
        DiggerFragment diggerFragment = new DiggerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("article_id", str);
        diggerFragment.setArguments(bundle);
        return diggerFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getArguments() != null) {
            this.m = getArguments().getString("article_id");
        }
    }

    protected void a() {
        DebugUtil.debug(l, "loadData page:" + this.f + "  url:" + this.e);
        if (this.g) {
            new HttpTask("qiuyou", b(), new ch(this)).execute(new Void[0]);
        }
    }

    protected String b() {
        return String.format(Constants.MAINPAGE_ACTORS, new Object[]{this.m, Integer.valueOf(this.f)});
    }
}
