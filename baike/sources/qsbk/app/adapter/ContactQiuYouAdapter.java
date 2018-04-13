package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.UserChatManager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;

public class ContactQiuYouAdapter extends Adapter {
    public static final int FANS = 0;
    public static final int ITEM_MAX = 2;
    public static final int OTHERS = 1;
    private final boolean a;
    private Context b;
    private List<BaseUserInfo> c;
    private ArrayList<BaseUserInfo> d;
    private int e;
    private LocalBroadcastManager f;
    private boolean g;
    private boolean h;
    private ProgressDialog i;

    class a extends ViewHolder {
        public View divider;
        final /* synthetic */ ContactQiuYouAdapter m;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public CheckBox mCheckView;
        public TextView mFansComeFrom;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public ImageView mUnreplyIV;
        public LinearLayout mUnreplyLin;

        public a(ContactQiuYouAdapter contactQiuYouAdapter, View view) {
            this.m = contactQiuYouAdapter;
            super(view);
            this.mCheckView = (CheckBox) view.findViewById(R.id.check);
            this.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            this.mNameTV = (TextView) view.findViewById(R.id.name);
            this.mGenderAgeLL = (LinearLayout) view.findViewById(R.id.gender_age);
            this.mGenderIV = (ImageView) view.findViewById(R.id.gender);
            this.mAgeTV = (TextView) view.findViewById(R.id.age);
            this.mUnreplyIV = (ImageView) view.findViewById(R.id.unreply);
            this.mUnreplyLin = (LinearLayout) view.findViewById(R.id.unreply_linearlayout);
            this.divider = view.findViewById(R.id.divider);
            this.mCheckView.setVisibility(contactQiuYouAdapter.a ? 0 : 8);
            this.mCheckView.setFocusable(false);
            this.mCheckView.setClickable(false);
            this.mFansComeFrom = (TextView) view.findViewById(R.id.fan_come_from);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onBind(qsbk.app.model.BaseUserInfo r5) {
            /*
            r4 = this;
            r1 = -1;
            r2 = r4.itemView;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x00e0;
        L_0x0009:
            r0 = -14803421; // 0xffffffffff1e1e23 float:-2.101745E38 double:NaN;
        L_0x000c:
            r2.setBackgroundColor(r0);
            r0 = r5.userIcon;
            r2 = r5.userId;
            r0 = qsbk.app.QsbkApp.absoluteUrlOfMediumUserIcon(r0, r2);
            r2 = android.text.TextUtils.isEmpty(r0);
            if (r2 == 0) goto L_0x00e3;
        L_0x001d:
            r0 = r4.mAvatarIV;
            r2 = qsbk.app.utils.UIHelper.getDefaultAvatar();
            r0.setImageResource(r2);
        L_0x0026:
            r0 = r4.mNameTV;
            r2 = r5.userName;
            r0.setText(r2);
            r2 = r4.mCheckView;
            r0 = r5.alreadyInGroup;
            if (r0 != 0) goto L_0x00ea;
        L_0x0033:
            r0 = 1;
        L_0x0034:
            r2.setEnabled(r0);
            r0 = r4.mCheckView;
            r2 = r4.m;
            r2 = r2.d;
            r2 = r2.contains(r5);
            r0.setChecked(r2);
            r2 = r4.divider;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x00ed;
        L_0x004e:
            r0 = -15329253; // 0xffffffffff16181b float:-1.9950936E38 double:NaN;
        L_0x0051:
            r2.setBackgroundColor(r0);
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x0112;
        L_0x005a:
            r0 = "F";
            r1 = r5.gender;
            r0 = r0.equalsIgnoreCase(r1);
            if (r0 == 0) goto L_0x00f2;
        L_0x0064:
            r0 = r4.mGenderIV;
            r1 = 2130839596; // 0x7f02082c float:1.7284207E38 double:1.052774641E-314;
            r0.setImageResource(r1);
            r0 = r4.mAgeTV;
            r1 = r4.m;
            r1 = r1.b;
            r1 = r1.getResources();
            r2 = 2131623950; // 0x7f0e000e float:1.8875066E38 double:1.0531621635E-314;
            r1 = r1.getColor(r2);
            r0.setTextColor(r1);
        L_0x0082:
            r0 = r4.mAgeTV;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = r5.age;
            r1 = r1.append(r2);
            r2 = "";
            r1 = r1.append(r2);
            r1 = r1.toString();
            r0.setText(r1);
            r0 = r5.comeFrom;
            r0 = android.text.TextUtils.isEmpty(r0);
            if (r0 == 0) goto L_0x0144;
        L_0x00a4:
            r0 = r4.mFansComeFrom;
            r1 = "来源：其他";
            r0.setText(r1);
        L_0x00ab:
            r1 = r4.mFansComeFrom;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x01f3;
        L_0x00b3:
            r0 = -12171438; // 0xffffffffff464752 float:-2.6355746E38 double:NaN;
        L_0x00b6:
            r1.setTextColor(r0);
            r0 = r4.m;
            r0 = r0.g;
            if (r0 == 0) goto L_0x0245;
        L_0x00c1:
            r0 = r5.relationship;
            r1 = qsbk.app.model.relationship.Relationship.FRIEND;
            if (r0 != r1) goto L_0x01fd;
        L_0x00c7:
            r1 = r4.mUnreplyIV;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x01f8;
        L_0x00cf:
            r0 = 2130839433; // 0x7f020789 float:1.7283876E38 double:1.0527745606E-314;
        L_0x00d2:
            r1.setImageResource(r0);
            r0 = r4.mUnreplyLin;
            r1 = new qsbk.app.adapter.bn;
            r1.<init>(r4, r5);
            r0.setOnClickListener(r1);
        L_0x00df:
            return;
        L_0x00e0:
            r0 = r1;
            goto L_0x000c;
        L_0x00e3:
            r2 = r4.mAvatarIV;
            qsbk.app.image.FrescoImageloader.displayAvatar(r2, r0);
            goto L_0x0026;
        L_0x00ea:
            r0 = 0;
            goto L_0x0034;
        L_0x00ed:
            r0 = -1184275; // 0xffffffffffededed float:NaN double:NaN;
            goto L_0x0051;
        L_0x00f2:
            r0 = r4.mGenderIV;
            r1 = 2130839602; // 0x7f020832 float:1.728422E38 double:1.052774644E-314;
            r0.setImageResource(r1);
            r0 = r4.mAgeTV;
            r1 = r4.m;
            r1 = r1.b;
            r1 = r1.getResources();
            r2 = 2131623951; // 0x7f0e000f float:1.8875068E38 double:1.053162164E-314;
            r1 = r1.getColor(r2);
            r0.setTextColor(r1);
            goto L_0x0082;
        L_0x0112:
            r0 = "F";
            r2 = r5.gender;
            r0 = r0.equalsIgnoreCase(r2);
            if (r0 == 0) goto L_0x0133;
        L_0x011c:
            r0 = r4.mGenderAgeLL;
            r2 = 2130839594; // 0x7f02082a float:1.7284203E38 double:1.05277464E-314;
            r0.setBackgroundResource(r2);
            r0 = r4.mGenderIV;
            r2 = 2130839593; // 0x7f020829 float:1.72842E38 double:1.0527746397E-314;
            r0.setImageResource(r2);
        L_0x012c:
            r0 = r4.mAgeTV;
            r0.setTextColor(r1);
            goto L_0x0082;
        L_0x0133:
            r0 = r4.mGenderAgeLL;
            r2 = 2130839603; // 0x7f020833 float:1.7284221E38 double:1.0527746446E-314;
            r0.setBackgroundResource(r2);
            r0 = r4.mGenderIV;
            r2 = 2130839601; // 0x7f020831 float:1.7284217E38 double:1.0527746437E-314;
            r0.setImageResource(r2);
            goto L_0x012c;
        L_0x0144:
            r0 = r5.comeFrom;
            r1 = "null";
            r0 = r0.equals(r1);
            if (r0 == 0) goto L_0x0157;
        L_0x014e:
            r0 = r4.mFansComeFrom;
            r1 = "来源：其他";
            r0.setText(r1);
            goto L_0x00ab;
        L_0x0157:
            r0 = r5.comeFrom;
            r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0176 }
            r1.<init>(r0);	 Catch:{ JSONException -> 0x0176 }
            r0 = new qsbk.app.im.IMChatMsgSource;	 Catch:{ JSONException -> 0x0176 }
            r0.<init>();	 Catch:{ JSONException -> 0x0176 }
            r0.parseFromJSONObject(r1);	 Catch:{ JSONException -> 0x0176 }
            r1 = r0.type;	 Catch:{ JSONException -> 0x0176 }
            switch(r1) {
                case 1: goto L_0x016d;
                case 2: goto L_0x0183;
                case 3: goto L_0x018c;
                case 4: goto L_0x0195;
                case 5: goto L_0x019e;
                case 6: goto L_0x01a7;
                case 7: goto L_0x01b9;
                case 8: goto L_0x01b0;
                case 9: goto L_0x01ea;
                default: goto L_0x016b;
            };	 Catch:{ JSONException -> 0x0176 }
        L_0x016b:
            goto L_0x00ab;
        L_0x016d:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：附近糗友";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x0176:
            r0 = move-exception;
            r1 = r4.mFansComeFrom;
            r2 = "来源: 其他";
            r1.setText(r2);
            r0.printStackTrace();
            goto L_0x00ab;
        L_0x0183:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：糗事";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x018c:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：糗事";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x0195:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：其他";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x019e:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：昵称搜索";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01a7:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：附近糗友";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01b0:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：糗友圈";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01b9:
            r1 = r0.valueObj;	 Catch:{ JSONException -> 0x0176 }
            r1 = r1.group_name;	 Catch:{ JSONException -> 0x0176 }
            r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ JSONException -> 0x0176 }
            if (r1 == 0) goto L_0x01cc;
        L_0x01c3:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来自群";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01cc:
            r1 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0176 }
            r2.<init>();	 Catch:{ JSONException -> 0x0176 }
            r3 = "来自群：";
            r2 = r2.append(r3);	 Catch:{ JSONException -> 0x0176 }
            r0 = r0.valueObj;	 Catch:{ JSONException -> 0x0176 }
            r0 = r0.group_name;	 Catch:{ JSONException -> 0x0176 }
            r0 = r2.append(r0);	 Catch:{ JSONException -> 0x0176 }
            r0 = r0.toString();	 Catch:{ JSONException -> 0x0176 }
            r1.setText(r0);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01ea:
            r0 = r4.mFansComeFrom;	 Catch:{ JSONException -> 0x0176 }
            r1 = "来源：直播间";
            r0.setText(r1);	 Catch:{ JSONException -> 0x0176 }
            goto L_0x00ab;
        L_0x01f3:
            r0 = -5329234; // 0xffffffffffaeaeae float:NaN double:NaN;
            goto L_0x00b6;
        L_0x01f8:
            r0 = 2130839432; // 0x7f020788 float:1.7283874E38 double:1.05277456E-314;
            goto L_0x00d2;
        L_0x01fd:
            r0 = r5.relationship;
            r1 = qsbk.app.model.relationship.Relationship.FOLLOW_REPLIED;
            if (r0 == r1) goto L_0x0209;
        L_0x0203:
            r0 = r5.relationship;
            r1 = qsbk.app.model.relationship.Relationship.FOLLOW_UNREPLIED;
            if (r0 != r1) goto L_0x0227;
        L_0x0209:
            r1 = r4.mUnreplyIV;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x0223;
        L_0x0211:
            r0 = 2130839431; // 0x7f020787 float:1.7283872E38 double:1.0527745597E-314;
        L_0x0214:
            r1.setImageResource(r0);
            r0 = r4.mUnreplyLin;
            r1 = new qsbk.app.adapter.bo;
            r1.<init>(r4, r5);
            r0.setOnClickListener(r1);
            goto L_0x00df;
        L_0x0223:
            r0 = 2130839430; // 0x7f020786 float:1.728387E38 double:1.052774559E-314;
            goto L_0x0214;
        L_0x0227:
            r1 = r4.mUnreplyIV;
            r0 = qsbk.app.utils.UIHelper.isNightTheme();
            if (r0 == 0) goto L_0x0241;
        L_0x022f:
            r0 = 2130839429; // 0x7f020785 float:1.7283868E38 double:1.0527745587E-314;
        L_0x0232:
            r1.setImageResource(r0);
            r0 = r4.mUnreplyLin;
            r1 = new qsbk.app.adapter.bp;
            r1.<init>(r4, r5);
            r0.setOnClickListener(r1);
            goto L_0x00df;
        L_0x0241:
            r0 = 2130839428; // 0x7f020784 float:1.7283866E38 double:1.052774558E-314;
            goto L_0x0232;
        L_0x0245:
            r0 = r4.mUnreplyIV;
            r1 = 8;
            r0.setVisibility(r1);
            goto L_0x00df;
            */
            throw new UnsupportedOperationException("Method not decompiled: qsbk.app.adapter.ContactQiuYouAdapter.a.onBind(qsbk.app.model.BaseUserInfo):void");
        }
    }

    class b extends ViewHolder {
        final /* synthetic */ ContactQiuYouAdapter m;

        public b(ContactQiuYouAdapter contactQiuYouAdapter, View view) {
            this.m = contactQiuYouAdapter;
            super(view);
        }
    }

    class c extends ViewHolder {
        public View divider;
        final /* synthetic */ ContactQiuYouAdapter m;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public CheckBox mCheckView;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public TextView mRemarkNameTV;
        public ImageView mUnreplyIV;
        public LinearLayout mUnreplyLin;

        public c(ContactQiuYouAdapter contactQiuYouAdapter, View view) {
            this.m = contactQiuYouAdapter;
            super(view);
            view.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
            this.mCheckView = (CheckBox) view.findViewById(R.id.check);
            this.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            this.mNameTV = (TextView) view.findViewById(R.id.name);
            this.mGenderAgeLL = (LinearLayout) view.findViewById(R.id.gender_age);
            this.mGenderIV = (ImageView) view.findViewById(R.id.gender);
            this.mAgeTV = (TextView) view.findViewById(R.id.age);
            this.mUnreplyIV = (ImageView) view.findViewById(R.id.unreply);
            this.mUnreplyLin = (LinearLayout) view.findViewById(R.id.unreply_linearlayout);
            this.divider = view.findViewById(R.id.divider);
            this.mCheckView.setVisibility(contactQiuYouAdapter.a ? 0 : 8);
            this.mCheckView.setFocusable(false);
            this.mCheckView.setClickable(false);
            this.mRemarkNameTV = (TextView) view.findViewById(R.id.remark_name);
        }

        public void onBind(BaseUserInfo baseUserInfo) {
            boolean z = false;
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.mAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(this.mAvatarIV, absoluteUrlOfMediumUserIcon);
            }
            CharSequence remark = RemarkManager.getRemark(baseUserInfo.userId);
            if (this.m.h) {
                if (TextUtils.isEmpty(remark)) {
                    this.mNameTV.setText(baseUserInfo.userName);
                    this.mRemarkNameTV.setVisibility(8);
                } else {
                    this.mNameTV.setText(remark);
                    this.mRemarkNameTV.setVisibility(0);
                    this.mRemarkNameTV.setText(" (" + baseUserInfo.userName + ")");
                }
            } else if (TextUtils.isEmpty(remark)) {
                this.mNameTV.setText(baseUserInfo.userName);
                this.mRemarkNameTV.setVisibility(8);
            } else {
                this.mNameTV.setText(remark);
                this.mRemarkNameTV.setVisibility(8);
            }
            CheckBox checkBox = this.mCheckView;
            if (!baseUserInfo.alreadyInGroup) {
                z = true;
            }
            checkBox.setEnabled(z);
            this.mCheckView.setChecked(this.m.d.contains(baseUserInfo));
            this.divider.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
            if (!UIHelper.isNightTheme()) {
                if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                    this.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_female_bg);
                    this.mGenderIV.setImageResource(R.drawable.pinfo_female);
                } else {
                    this.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_man_bg);
                    this.mGenderIV.setImageResource(R.drawable.pinfo_male);
                }
                this.mAgeTV.setTextColor(-1);
            } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                this.mGenderIV.setImageResource(R.drawable.pinfo_female_dark);
                this.mAgeTV.setTextColor(this.m.b.getResources().getColor(R.color.age_female));
            } else {
                this.mGenderIV.setImageResource(R.drawable.pinfo_male_dark);
                this.mAgeTV.setTextColor(this.m.b.getResources().getColor(R.color.age_male));
            }
            this.mAgeTV.setText(baseUserInfo.age + "");
            if (!this.m.g) {
                this.mUnreplyIV.setVisibility(8);
            } else if (baseUserInfo.relationship == Relationship.FRIEND) {
                this.mUnreplyIV.setImageResource(UIHelper.isNightTheme() ? R.drawable.my_qiuyou_friend_night : R.drawable.my_qiuyou_friend);
                this.mUnreplyLin.setOnClickListener(new bq(this, baseUserInfo));
            } else if (baseUserInfo.relationship == Relationship.FOLLOW_REPLIED || baseUserInfo.relationship == Relationship.FOLLOW_UNREPLIED) {
                this.mUnreplyIV.setImageResource(UIHelper.isNightTheme() ? R.drawable.my_qiuyou_follewed_night : R.drawable.my_qiuyou_follewed);
                this.mUnreplyLin.setOnClickListener(new br(this, baseUserInfo));
            } else {
                this.mUnreplyIV.setImageResource(UIHelper.isNightTheme() ? R.drawable.my_qiuyou_fan_night : R.drawable.my_qiuyou_fan);
                this.mUnreplyLin.setOnClickListener(new bs(this, baseUserInfo));
            }
        }
    }

    public ContactQiuYouAdapter(Context context, List<BaseUserInfo> list) {
        this(context, list, false);
    }

    public ContactQiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z) {
        this(context, list, z, false);
    }

    public ContactQiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z, boolean z2) {
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = 0;
        this.i = null;
        this.b = context;
        this.c = list;
        this.a = z;
        this.f = LocalBroadcastManager.getInstance(this.b);
        this.g = z2;
    }

    public ContactQiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z, boolean z2, boolean z3) {
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = 0;
        this.i = null;
        this.b = context;
        this.c = list;
        this.a = z;
        this.f = LocalBroadcastManager.getInstance(this.b);
        this.g = z2;
        this.h = z3;
    }

    public boolean isShowRelationship() {
        return this.g;
    }

    public void setShowRelationship(boolean z) {
        this.g = z;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.c.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new a(this, LayoutInflater.from(this.b).inflate(R.layout.layout_qiuyou_new_fans, viewGroup, false));
            case 1:
                return new c(this, LayoutInflater.from(this.b).inflate(R.layout.layout_qiuyou_contact, viewGroup, false));
            default:
                return new b(this, new View(viewGroup.getContext()));
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.c.get(i);
        switch (getItemViewType(i)) {
            case 0:
                ((a) viewHolder).onBind(baseUserInfo);
                return;
            case 1:
                ((c) viewHolder).onBind(baseUserInfo);
                return;
            default:
                return;
        }
    }

    public int getItemViewType(int i) {
        if (((BaseUserInfo) this.c.get(i)).relationship == Relationship.FAN) {
            return 0;
        }
        return 1;
    }

    public void setDatas(List<BaseUserInfo> list) {
        this.c = list;
    }

    public void replaceItem(List<BaseUserInfo> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    public void updateRelationShip(Relationship relationship, String str) {
        if (relationship != null) {
            Object obj;
            for (BaseUserInfo baseUserInfo : this.c) {
                if (baseUserInfo.userId.equals(str) && relationship != baseUserInfo.relationship) {
                    switch (bm.a[relationship.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            this.c.remove(baseUserInfo);
                            int i = 1;
                            break;
                        default:
                            baseUserInfo.relationship = relationship;
                            obj = 1;
                            break;
                    }
                    if (obj != null) {
                        notifyDataSetChanged();
                    }
                }
            }
            obj = null;
            if (obj != null) {
                notifyDataSetChanged();
            }
        }
    }

    public void toggleCheck(int i, View view) {
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.c.get(i);
        if (this.a && !baseUserInfo.alreadyInGroup) {
            boolean z = !this.d.contains(baseUserInfo);
            if (z) {
                this.d.add(baseUserInfo);
            } else {
                this.d.remove(baseUserInfo);
            }
            if (view != null) {
                ((c) view.getTag()).mCheckView.setChecked(z);
            }
        }
    }

    public String getCheckedIds() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.d.size(); i++) {
            stringBuilder.append(((BaseUserInfo) this.d.get(i)).userId);
            stringBuilder.append(',');
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public ArrayList<BaseUserInfo> getChecked() {
        return this.d;
    }

    public void myQiuyouOperation(int i, BaseUserInfo baseUserInfo) {
        this.e = i;
        String str = "";
        str = "";
        str = "";
        String str2;
        if (i == 1) {
            str2 = "再想想";
            a("取消粉TA后，不能发图片。是否取消粉?", str2, "取消粉", new be(this), new bd(this, baseUserInfo, "正在取消粉,请稍后..."));
        } else if (i == 2) {
            str2 = "再想想";
            a("是否取消粉TA?", str2, "取消粉", new bg(this), new bf(this, baseUserInfo, "正在取消粉,请稍后..."));
        } else if (i == 3) {
            str = Constants.REL_FOLLOW;
            Object[] objArr = new Object[1];
            QsbkApp.getInstance();
            objArr[0] = QsbkApp.currentUser.userId;
            str = String.format(str, objArr);
            Map hashMap = new HashMap();
            hashMap.put("uid", baseUserInfo.userId);
            hashMap.put("shake_time", Integer.valueOf(0));
            hashMap.put("shake_count", Integer.valueOf(0));
            a(baseUserInfo.userId, str, hashMap, "正在加粉,请稍后...");
        }
    }

    private void a(String str, String str2, Map<String, Object> map, String str3) {
        this.i = ProgressDialog.show(this.b, null, str3, true, true);
        this.i.setCancelable(true);
        this.i.setCanceledOnTouchOutside(true);
        SimpleHttpTask bkVar = new bk(this, str2, new bh(this, str));
        bkVar.setMapParams(map);
        this.i.setOnCancelListener(new bl(this, bkVar));
        bkVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(Relationship relationship, String str) {
        Intent intent = new Intent("QIU_YOU_RELATION_CHANGED");
        intent.putExtra("userId", str);
        intent.putExtra(ConversationActivity.RELATIONSHIP, relationship);
        this.f.sendBroadcast(intent);
    }

    private void a(int i) {
        Intent intent = new Intent(this.b, InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, i);
        this.b.startActivity(intent);
    }

    private void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0, str);
            jSONObject.put("delsession", jSONArray);
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(new ChatMsg(201, jSONObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && onClickListener != null && onClickListener != null) {
            new Builder(this.b).setCancelable(true).setMessage(str).setPositiveButton(str2, onClickListener).setNegativeButton(str3, onClickListener2).create().show();
        }
    }
}
