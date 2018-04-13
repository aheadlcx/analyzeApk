package qsbk.app.activity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.AstrologyUtils;

public class EditInfoEntranceActivity$EditItemSubmitter {
    SimpleCallBack a;
    SimpleHttpTask b = new SimpleHttpTask(getURL(), this.a);
    final /* synthetic */ EditInfoEntranceActivity c;
    public int requestCode;
    public Serializable value;

    public EditInfoEntranceActivity$EditItemSubmitter(EditInfoEntranceActivity editInfoEntranceActivity, int i, Serializable serializable) {
        this.c = editInfoEntranceActivity;
        this.requestCode = i;
        this.value = serializable;
        this.a = new kk(this, editInfoEntranceActivity);
        this.b.setMapParams(getPostParams());
    }

    public String getURL() {
        return String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> hashMap = new HashMap();
        if (this.requestCode == 12) {
            hashMap.put("birthday", Long.valueOf(((Long) this.value).longValue()));
        } else if (this.requestCode == 13) {
            hashMap.put("gender", this.value.toString());
        } else if (this.requestCode == 9) {
            hashMap.put("hobby", this.value.toString());
        } else if (this.requestCode == 10) {
            hashMap.put("introduce", this.value.toString());
        } else if (this.requestCode == 14) {
            hashMap.put("location", this.value.toString());
        } else if (this.requestCode == 8) {
            hashMap.put(QsbkDatabase.LOGIN, this.value.toString());
        } else if (this.requestCode == 11) {
            hashMap.put("signature", this.value.toString());
        }
        return hashMap;
    }

    void a() {
        this.b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void b() {
        switch (this.requestCode) {
            case 8:
                QsbkApp.currentUser.userName = this.value.toString();
                break;
            case 12:
                QsbkApp.currentUser.birthday = (long) ((Integer) this.value).intValue();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(QsbkApp.currentUser.birthday * 1000);
                QsbkApp.currentUser.age = AstrologyUtils.getAge(instance);
                break;
            case 13:
                QsbkApp.currentUser.gender = this.value.toString();
                break;
            case 14:
                QsbkApp.currentUser.haunt = this.value.toString();
                break;
        }
        QsbkApp.getInstance().setCurrentUserToLocal();
        EditInfoEntranceActivity.a(this.c, true);
    }
}
