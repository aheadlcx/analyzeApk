package cn.tatagou.sdk.util;

import android.view.View;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.pojo.TitleBar;
import java.util.List;
import java.util.Map;

public abstract class c {
    public void setSysCfg(Map<String, String> map) {
    }

    public void setPageSelected(int i) {
    }

    public void setTbLogin(int i) {
    }

    public void setClickStatus(boolean z) {
    }

    public void onRcmmSpClickRefresh(View view) {
    }

    public void onRcmmSpTaskExecuteResult(List<Special> list, int i) {
    }

    public void onRcmmSpTaskExecuteResult(boolean z) {
    }

    public void onNormalSpTaskExecutResult(boolean z) {
    }

    public void onTitle(View view, TitleBar titleBar) {
    }

    public void onCheckIdentityResult(String str) {
    }
}
