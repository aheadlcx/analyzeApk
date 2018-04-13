package qsbk.app.nearby.ui;

import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.baidu.mobstat.Config;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.MobileBrand;
import qsbk.app.nearby.ui.MobileBrandEditorActivity.MobileBrandEditorAdapter;
import qsbk.app.utils.ToastAndDialog;

class am implements HttpCallBack {
    final /* synthetic */ MobileBrandEditorActivity a;

    am(MobileBrandEditorActivity mobileBrandEditorActivity) {
        this.a = mobileBrandEditorActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = 0;
        if (Constants.URL_MOBILE_BRAND_LIST.equalsIgnoreCase(str)) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                List linkedList = new LinkedList();
                String toLowerCase = Build.MODEL.toLowerCase();
                JSONArray optJSONArray = jSONObject.optJSONArray("magic_list");
                while (i < optJSONArray.length()) {
                    linkedList.add(new MobileBrand(optJSONArray.optString(i), toLowerCase + Config.TRACE_TODAY_VISIT_SPLIT + i));
                    i++;
                }
                this.a.c = new MobileBrandEditorAdapter(this.a, linkedList);
                this.a.a.setAdapter(this.a.c);
            } else {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, jSONObject.optString("err_msg"), Integer.valueOf(0)).show();
            }
            this.a.findViewById(R.id.loadingbar).setVisibility(8);
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.findViewById(R.id.loadingbar).setVisibility(8);
    }
}
