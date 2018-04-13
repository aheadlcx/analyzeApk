package qsbk.app.nearby.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.nearby.ui.ShakeDialogFragment.OnShakedListener;
import qsbk.app.utils.HttpClient;

public class Shake2FanSomeone {

    public interface Shake2FanSomeoneListener {
        void onLocalSuccess(FanModel fanModel);

        void onServerFailed(FanModel fanModel, int i, String str);

        void onServerSuccess(FanModel fanModel);
    }

    public static class FanAsynTask extends AsyncTask<Void, Void, String> {
        private Shake2FanSomeoneListener a;
        private FanModel b;

        public FanAsynTask(FanModel fanModel, Shake2FanSomeoneListener shake2FanSomeoneListener) {
            this.b = fanModel;
            this.a = shake2FanSomeoneListener;
        }

        protected String a(Void... voidArr) {
            String str = null;
            if (this.b != null) {
                Map hashMap = new HashMap();
                hashMap.put("shake_count", this.b.c + "");
                hashMap.put("shake_time", this.b.d + "");
                if (this.b.b != null) {
                    hashMap.put("come_from", this.b.b);
                }
                hashMap.put("uid", this.b.a);
                try {
                    str = HttpClient.getIntentce().post(String.format(Constants.REL_FOLLOW, new Object[]{QsbkApp.currentUser.userId}), hashMap);
                } catch (QiushibaikeException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }

        protected void a(String str) {
            super.a(str);
            if (str == null || str.length() <= 0) {
                this.a.onServerFailed(this.b, 9999, HttpClient.getLocalErrorStr());
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
                if (i == 0) {
                    this.a.onServerSuccess(this.b);
                } else {
                    this.a.onServerFailed(this.b, i, jSONObject.getString("err_msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class FanModel implements Parcelable {
        public static final Creator<FanModel> CREATOR = new aq();
        private String a;
        private String b;
        private int c;
        private int d;

        public FanModel(String str, String str2, int i, int i2) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
        }

        private FanModel(Parcel parcel) {
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.a = parcel.readString();
            this.b = parcel.readString();
        }

        public String getUid() {
            return this.a;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeString(this.a);
            parcel.writeString(this.b);
        }
    }

    public static class ShakedListenerWrapper implements OnShakedListener {
        private OnShakedListener a;

        public ShakedListenerWrapper(OnShakedListener onShakedListener) {
            this.a = onShakedListener;
        }

        public void onSuccess(int i, int i2) {
            if (this.a != null) {
                this.a.onSuccess(i, i2);
            }
        }
    }

    private Shake2FanSomeone() {
    }

    public static void shake2fan(String str, String str2, ShakeDialogFragment shakeDialogFragment) {
        shake2fan(str, str2, shakeDialogFragment, null);
    }

    public static void shake2fan(String str, String str2, ShakeDialogFragment shakeDialogFragment, Shake2FanSomeoneListener shake2FanSomeoneListener) {
        shake2fan(new FanModel(str, str2, 0, 0), shakeDialogFragment, shake2FanSomeoneListener);
    }

    public static void shake2fan(FanModel fanModel, ShakeDialogFragment shakeDialogFragment, Shake2FanSomeoneListener shake2FanSomeoneListener) {
        shakeDialogFragment.setOnShakedListener(new an(fanModel, shakeDialogFragment.getOnShakedListener(), shake2FanSomeoneListener));
    }

    public static void shake2fan(FanModel fanModel, ShakeDialogFragment shakeDialogFragment, Shake2FanSomeoneListener shake2FanSomeoneListener, FragmentActivity fragmentActivity, int i) {
        shakeDialogFragment.setOnShakedListener(new ap(fanModel, shakeDialogFragment.getOnShakedListener(), new ao(shake2FanSomeoneListener, shakeDialogFragment, fragmentActivity, i)));
    }

    public static void openInfoCompletedActivity(Activity activity, int i) {
        if (activity != null) {
            Intent intent = new Intent(activity, InfoCompleteActivity.class);
            intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
            activity.startActivityForResult(intent, i);
        }
    }

    public static void openInfoCompletedActivity(Fragment fragment, int i) {
        if (fragment != null && fragment.getActivity() != null) {
            Intent intent = new Intent(fragment.getActivity(), InfoCompleteActivity.class);
            intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
            fragment.startActivityForResult(intent, i);
        }
    }
}
