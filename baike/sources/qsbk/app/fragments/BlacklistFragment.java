package qsbk.app.fragments;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.AdapterView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpTask;
import qsbk.app.model.BaseUserInfo;

public class BlacklistFragment extends QiuYouFragment {
    public BlacklistFragment() {
        super(Constants.REL_GET_BLACKLIST, BlacklistFragment.class.getSimpleName());
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        new Builder(getActivity()).setCancelable(true).setMessage("是否确定移出黑名单").setPositiveButton("再想想", new s(this)).setNegativeButton("移出黑名单", new r(this, (BaseUserInfo) this.h.getItem(i - this.c.getHeaderViewsCount()), i)).create().show();
        return true;
    }

    private void a(String str, int i) {
        String str2 = Constants.REL_MOVEOFF_BLACKLIST;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str2 = String.format(str2, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", str);
        HttpTask httpTask = new HttpTask(Constants.REL_MOVEOFF_BLACKLIST, str2, new t(this, i));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }
}
