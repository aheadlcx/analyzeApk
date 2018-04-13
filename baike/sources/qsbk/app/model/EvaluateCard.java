package qsbk.app.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.EasyRatingBar;

public class EvaluateCard {
    public static final EvaluateCard INSTANCE = new EvaluateCard();
    private static long a = 0;
    private static int b = 15;
    private static String c = null;
    private static int d = 0;
    private static int e = -1;
    private static boolean f = false;

    public static class ViewHolder {
        public EasyRatingBar evaluateBar;
        public TextView evaluateSubmit;
        public ImageView face;
        public TextView message;
        public View ratingGroup;
        public ImageView resultFace;
        public TextView tips;

        public ViewHolder(View view) {
            this.evaluateBar = (EasyRatingBar) view.findViewById(R.id.evaluate_bar);
            this.evaluateSubmit = (TextView) view.findViewById(R.id.evaluate_submit);
            this.message = (TextView) view.findViewById(R.id.evaluate_message);
            this.face = (ImageView) view.findViewById(R.id.evaluate_face);
            this.tips = (TextView) view.findViewById(R.id.evaluate_tips);
            this.ratingGroup = view.findViewById(R.id.evaluate_group);
            this.resultFace = (ImageView) view.findViewById(R.id.evaluate_result);
            this.resultFace.setImageLevel(6);
            this.evaluateBar.setOnRateListener(new j(this));
            this.evaluateSubmit.setOnClickListener(new k(this));
        }

        private void a(int i) {
            this.evaluateSubmit.setEnabled(i != 0);
            this.tips.setText(this.tips.getResources().getStringArray(R.array.evaluate_rating_tip)[i]);
            this.face.setImageLevel(i);
        }

        public void onSubmitted() {
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) ((int) (-14.0f * this.evaluateBar.getResources().getDisplayMetrics().density)), 0.0f);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setDuration(500);
            this.evaluateBar.setEnabled(false);
            this.ratingGroup.setVisibility(4);
            this.resultFace.setVisibility(0);
            this.resultFace.startAnimation(translateAnimation);
            this.message.setText(R.string.evaluate_result);
            this.evaluateSubmit.setEnabled(false);
            this.evaluateSubmit.setText("已提交");
        }

        public void update() {
            if (EvaluateCard.d > 5 || EvaluateCard.d < 0) {
                EvaluateCard.d = 0;
            }
            this.evaluateBar.setRate(EvaluateCard.d);
            if (EvaluateCard.e != -2) {
                this.evaluateBar.setEnabled(true);
                this.ratingGroup.setVisibility(0);
                this.resultFace.setVisibility(4);
                a(EvaluateCard.d);
                this.message.setText(EvaluateCard.c == null ? this.message.getResources().getString(R.string.evaluate_title) : EvaluateCard.c);
                this.evaluateSubmit.setText("提交");
                return;
            }
            this.resultFace.setVisibility(0);
            this.ratingGroup.setVisibility(4);
            this.message.setText(R.string.evaluate_result);
            this.evaluateSubmit.setEnabled(false);
            this.evaluateSubmit.setText("已提交");
        }
    }

    private EvaluateCard() {
    }

    public static boolean isNeedToShow() {
        return e != -1;
    }

    public static int getPosition() {
        return b;
    }

    private static void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("evaluate", 0);
        a = sharedPreferences.getLong("last_time", 0);
        b = sharedPreferences.getInt("position", 15);
        c = sharedPreferences.getString("title", null);
        e = sharedPreferences.getInt("state", -1);
    }

    private static void c(Context context) {
        context.getSharedPreferences("evaluate", 0).edit().putLong("last_time", a).putInt("position", b).putString("title", c).putInt("state", e).apply();
    }

    private static void d(Context context) {
        context.getSharedPreferences("evaluate", 0).edit().clear().apply();
    }

    public static void syncLoadIfNeed(Context context, boolean z) {
        int i = 0;
        if (a == 0) {
            b(context);
        }
        if (System.currentTimeMillis() < a) {
            if (e == -2 || e > 1) {
                e = -1;
                c(context);
            } else if (e >= 0) {
                f = true;
            }
        } else if (!z) {
            d(context);
            if (HttpUtils.netIsAvailable()) {
                e = 0;
                f = false;
                try {
                    Object obj = HttpClient.getIntentce().get("https://api.qiushibaike.com/eval?uuid=" + DeviceUtils.getAndroidId());
                    if (!TextUtils.isEmpty(obj)) {
                        JSONObject jSONObject = new JSONObject(obj);
                        if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                            f = true;
                            if (jSONObject.optInt("flag", 0) != 1) {
                                i = -1;
                            }
                            e = i;
                            a = (e == -1 ? 86400000 : 2592000000L) + System.currentTimeMillis();
                            b = jSONObject.optInt("pos", 15);
                            c = jSONObject.optString("title");
                            c(context);
                        }
                    }
                } catch (QiushibaikeException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static void b(Context context, ViewHolder viewHolder, int i) {
        String str = Constants.USER_EVALUATE;
        HttpTask httpTask = new HttpTask(str, str, new i(context, viewHolder));
        JSONObject jSONObject = new JSONObject();
        try {
            if (QsbkApp.currentUser != null) {
                jSONObject.put("user_id", QsbkApp.currentUser.userId);
            }
            jSONObject.put("uuid", DeviceUtils.getAndroidId());
            jSONObject.put("score", i);
            jSONObject.put("source", "android");
            jSONObject.put("version", Constants.localVersionName);
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put("device_os", "android_" + VERSION.RELEASE);
            httpTask.setStringParams(jSONObject.toString());
            httpTask.execute(new Void[0]);
        } catch (JSONException e) {
            e.printStackTrace();
            e = -2;
            c(context);
            viewHolder.onSubmitted();
        }
    }

    public static View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_evaluate_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        d();
        if (f) {
            f = false;
            e++;
            c(viewGroup.getContext());
        }
        viewHolder.update();
        return view;
    }

    private static void d() {
        if (e == 0) {
            StatService.onEvent(AppContext.getContext(), "evaluate_card", c);
            StatSDK.onEvent(AppContext.getContext(), "evaluate_card", c);
        }
    }
}
