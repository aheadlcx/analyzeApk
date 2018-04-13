package qsbk.app.core.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import qsbk.app.core.R;
import qsbk.app.core.utils.stat.BaiduStatistics;

public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(getLayoutId(), null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initView();
        initData();
    }

    public View findViewById(int i) {
        return getView().findViewById(i);
    }

    public void onResume() {
        super.onResume();
        BaiduStatistics.fragmentPageOnResume(this);
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    public void onPause() {
        super.onPause();
        BaiduStatistics.fragmentPageOnPause(this);
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    protected void setTitle(String str) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(str);
        }
    }

    protected <T extends View> T $(int i) {
        return findViewById(i);
    }
}
