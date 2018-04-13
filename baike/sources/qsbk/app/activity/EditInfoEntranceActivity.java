package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.EditUserInfoAdapter$UserInfoItem;
import qsbk.app.api.StartActivityListenerForClick;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.EditUserInfoItem;

@Deprecated
public class EditInfoEntranceActivity extends BaseActionBarActivity implements OnClickListener {
    public static final String RESP_KEY_USER_INFO_CHANGED = "user_info_changed";
    Handler a = new ke(this);
    private String b;
    private ImageView c;
    private UserHeaderHelper d;
    private LinearLayout e;
    private Map<Integer, EditUserInfoItem> f = new HashMap();
    private boolean g = false;

    protected void onCreate(Bundle bundle) {
        if (QsbkApp.currentUser == null) {
            QsbkApp.currentUser = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
        }
        super.onCreate(bundle);
    }

    protected void onResume() {
        super.onResume();
    }

    private Intent a(Class cls, Serializable serializable) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(REQUEST_KEY.KEY_AUTO_SUBMIT, false);
        if (serializable != null) {
            intent.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, serializable);
        }
        return intent;
    }

    private EditUserInfoItem a(EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem, ViewGroup viewGroup, OnClickListener onClickListener) {
        EditUserInfoItem editUserInfoItem = new EditUserInfoItem(editUserInfoAdapter$UserInfoItem, this, viewGroup);
        editUserInfoItem.setOnEditListener(onClickListener);
        viewGroup.addView(editUserInfoItem.getView());
        return editUserInfoItem;
    }

    private void g() {
        Object a;
        this.f.put(Integer.valueOf(8), a(new EditUserInfoAdapter$UserInfoItem("昵称", QsbkApp.currentUser.userName, "", QsbkApp.currentUser.userName, true), this.e, new StartActivityListenerForClick(a(EditNameActivity.class, QsbkApp.currentUser.userName), this, 8)));
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        EditUserInfoAdapter$UserInfoItem editUserInfoAdapter$UserInfoItem = new EditUserInfoAdapter$UserInfoItem("性别", (String) UserInfo.MAP.get(QsbkApp.currentUser.gender), "", QsbkApp.currentUser.gender, true);
        Intent a2 = a(EditGenderActivity.class, QsbkApp.currentUser.gender);
        if (QsbkApp.currentUser.gender.equalsIgnoreCase(BaseUserInfo.GENDER_UNKONW)) {
            a = a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a2, this, 13));
        } else {
            a = a(editUserInfoAdapter$UserInfoItem, this.e, new kf(this));
        }
        this.f.put(Integer.valueOf(13), a);
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        editUserInfoAdapter$UserInfoItem = new EditUserInfoAdapter$UserInfoItem("年龄", QsbkApp.currentUser.age > 99 ? "99" : QsbkApp.currentUser.age + "", "", Long.valueOf(QsbkApp.currentUser.birthday), true);
        a2 = a(EditBirthActivity.class, Long.valueOf(QsbkApp.currentUser.birthday));
        this.f.put(Integer.valueOf(12), a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a2, this, 12)));
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        this.f.put(Integer.valueOf(11), a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a2, this, 11)));
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        editUserInfoAdapter$UserInfoItem = new EditUserInfoAdapter$UserInfoItem("常出没地", QsbkApp.currentUser.haunt, "", QsbkApp.currentUser.haunt, true);
        a2 = a(EditLocationActivity.class, QsbkApp.currentUser.haunt);
        this.f.put(Integer.valueOf(14), a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a2, this, 14)));
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        this.f.put(Integer.valueOf(9), a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a2, this, 9)));
        this.e.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.e, false));
        this.f.put(Integer.valueOf(10), a(editUserInfoAdapter$UserInfoItem, this.e, new StartActivityListenerForClick(a(EditIntrActivity.class, (Serializable) "没什么好说的"), this, 10)));
    }

    private void i() {
        if (QsbkApp.currentUser != null && !TextUtils.isEmpty(QsbkApp.currentUser.userIcon) && !"null".equals(QsbkApp.currentUser.userIcon.toString())) {
            FrescoImageloader.displayAvatar(this.c, UserHeaderHelper.getIconUrl(QsbkApp.currentUser));
        }
    }

    protected void e() {
        if (QsbkApp.currentUser == null) {
            finish();
        }
        this.c = (ImageView) findViewById(R.id.userIcon);
        this.e = (LinearLayout) findViewById(R.id.set_base_info);
    }

    protected void f() {
        this.c.setOnClickListener(new kg(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                    if (intent == null || intent.getData() == null) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "选择的图片为空").show();
                        return;
                    } else {
                        this.d.doCropPhoto(intent.getData());
                        return;
                    }
                case 2:
                    Object savePickedBitmap = this.d.savePickedBitmap(intent);
                    if (!TextUtils.isEmpty(savePickedBitmap)) {
                        submitAvatar(savePickedBitmap);
                        return;
                    }
                    return;
                case 3:
                    this.d.doCropPhotoWithCaptured();
                    return;
                default:
                    EditUserInfoItem editUserInfoItem = (EditUserInfoItem) this.f.get(Integer.valueOf(i));
                    if (editUserInfoItem != null) {
                        Serializable serializableExtra = intent.getSerializableExtra(REQUEST_KEY.KEY_RETURN_VALUE);
                        if (serializableExtra != null) {
                            a(editUserInfoItem, serializableExtra, i);
                            EditInfoEntranceActivity$EditItemSubmitter editInfoEntranceActivity$EditItemSubmitter = new EditInfoEntranceActivity$EditItemSubmitter(this, i, serializableExtra);
                            editInfoEntranceActivity$EditItemSubmitter.a();
                            if (a(i)) {
                                EditInfoEntranceActivity$EditItemSubmitter.a(editInfoEntranceActivity$EditItemSubmitter);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
            }
        }
    }

    private boolean a(int i) {
        return i == 9 || i == 10 || i == 14 || i == 11;
    }

    private void a(EditUserInfoItem editUserInfoItem, Serializable serializable, int i) {
        editUserInfoItem.getUserInfo().setInnerValue(serializable);
        CharSequence obj = serializable.toString();
        if (i == 12) {
            long longValue = ((Long) serializable).longValue() * 1000;
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(longValue);
            obj = AstrologyUtils.getAge(instance) + "";
        } else if (i == 13) {
            String str = (String) UserInfo.MAP.get(serializable.toString());
        }
        editUserInfoItem.getValueView().setTextColor(getResources().getColor(R.color.g_txt_middle));
        editUserInfoItem.getValueView().setText(obj);
    }

    public void submitAvatar(String str) {
        if (HttpUtils.isNetworkConnected(this)) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在上传头像...").show();
            new kj(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.network_not_connected)).show();
    }

    public HashMap<String, Object> getPostParams() {
        return new HashMap();
    }

    protected int a() {
        return R.layout.activity_edit_info_entrance;
    }

    public void onClick(View view) {
        view.getId();
    }

    public String getCustomTitle() {
        return getString(R.string.my_profile_edit);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.action_feedback:
                gotoFeedbackActivity();
                break;
            case R.id.action_about:
                Intent intent = new Intent(this, About.class);
                intent.putExtra("targetPage", "about");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        if (!TextUtils.isEmpty(this.b)) {
            Intent intent = new Intent();
            intent.putExtra(RESP_KEY_USER_INFO_CHANGED, this.g);
            setResult(-1, intent);
        }
        super.finish();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.d != null) {
            this.d.onSaveInstanceState(bundle);
        }
    }

    protected void a(Bundle bundle) {
        this.d = new UserHeaderHelper(this, bundle);
        this.b = getIntent().getStringExtra("source");
        setActionbarBackable();
        e();
        f();
        g();
        i();
    }

    public void onBackPressed() {
        finish();
    }
}
