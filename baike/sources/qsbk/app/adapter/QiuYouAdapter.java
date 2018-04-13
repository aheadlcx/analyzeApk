package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.nearby.ui.InfoCompleteActivity;

public class QiuYouAdapter extends BaseAdapter {
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

    class a {
        final /* synthetic */ QiuYouAdapter a;
        public View divider;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public CheckBox mCheckView;
        public TextView mFansComeFrom;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public ImageView mUnreplyIV;
        public LinearLayout mUnreplyLin;

        a(QiuYouAdapter qiuYouAdapter) {
            this.a = qiuYouAdapter;
        }
    }

    class b {
        final /* synthetic */ QiuYouAdapter a;
        public View divider;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public CheckBox mCheckView;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public TextView mRemarkNameTV;
        public ImageView mUnreplyIV;
        public LinearLayout mUnreplyLin;

        b(QiuYouAdapter qiuYouAdapter) {
            this.a = qiuYouAdapter;
        }
    }

    public QiuYouAdapter(Context context, List<BaseUserInfo> list) {
        this(context, list, false);
    }

    public QiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z) {
        this(context, list, z, false);
    }

    public QiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z, boolean z2) {
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

    public QiuYouAdapter(Context context, List<BaseUserInfo> list, boolean z, boolean z2, boolean z3) {
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

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
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
                    switch (cu.a[relationship.ordinal()]) {
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
                ((b) view.getTag()).mCheckView.setChecked(z);
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r10, android.view.View r11, android.view.ViewGroup r12) {
        /*
        r9 = this;
        r4 = -14803421; // 0xffffffffff1e1e23 float:-2.101745E38 double:NaN;
        r6 = -15329253; // 0xffffffffff16181b float:-1.9950936E38 double:NaN;
        r5 = -1;
        r2 = 8;
        r1 = 0;
        r0 = r9.getItemViewType(r10);
        switch(r0) {
            case 0: goto L_0x028c;
            case 1: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        return r11;
    L_0x0012:
        if (r11 == 0) goto L_0x001c;
    L_0x0014:
        r0 = r11.getTag();
        r0 = r0 instanceof qsbk.app.adapter.QiuYouAdapter.b;
        if (r0 != 0) goto L_0x01a0;
    L_0x001c:
        r3 = new qsbk.app.adapter.QiuYouAdapter$b;
        r3.<init>(r9);
        r0 = r9.b;
        r0 = android.view.LayoutInflater.from(r0);
        r7 = 2130903541; // 0x7f0301f5 float:1.7413903E38 double:1.052806234E-314;
        r11 = r0.inflate(r7, r12, r1);
        r0 = 2131756292; // 0x7f100504 float:1.9143487E38 double:1.053227549E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.CheckBox) r0;
        r3.mCheckView = r0;
        r0 = 2131755553; // 0x7f100221 float:1.9141989E38 double:1.053227184E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mAvatarIV = r0;
        r0 = 2131755304; // 0x7f100128 float:1.9141483E38 double:1.053227061E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mNameTV = r0;
        r0 = 2131755554; // 0x7f100222 float:1.914199E38 double:1.0532271846E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.LinearLayout) r0;
        r3.mGenderAgeLL = r0;
        r0 = 2131755555; // 0x7f100223 float:1.9141993E38 double:1.053227185E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mGenderIV = r0;
        r0 = 2131755364; // 0x7f100164 float:1.9141605E38 double:1.0532270907E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mAgeTV = r0;
        r0 = 2131756821; // 0x7f100715 float:1.914456E38 double:1.0532278105E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mUnreplyIV = r0;
        r0 = 2131756820; // 0x7f100714 float:1.9144558E38 double:1.05322781E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.LinearLayout) r0;
        r3.mUnreplyLin = r0;
        r0 = 2131755561; // 0x7f100229 float:1.9142005E38 double:1.053227188E-314;
        r0 = r11.findViewById(r0);
        r3.divider = r0;
        r7 = r3.mCheckView;
        r0 = r9.a;
        if (r0 == 0) goto L_0x019d;
    L_0x0095:
        r0 = r1;
    L_0x0096:
        r7.setVisibility(r0);
        r0 = r3.mCheckView;
        r0.setFocusable(r1);
        r0 = r3.mCheckView;
        r0.setClickable(r1);
        r0 = 2131756819; // 0x7f100713 float:1.9144556E38 double:1.0532278096E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mRemarkNameTV = r0;
        r11.setTag(r3);
    L_0x00b1:
        r0 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r0 == 0) goto L_0x01a9;
    L_0x00b7:
        r0 = r4;
    L_0x00b8:
        r11.setBackgroundColor(r0);
        r0 = r9.c;
        r0 = r0.get(r10);
        r0 = (qsbk.app.model.BaseUserInfo) r0;
        r4 = r0.userIcon;
        r7 = r0.userId;
        r4 = qsbk.app.QsbkApp.absoluteUrlOfMediumUserIcon(r4, r7);
        r7 = android.text.TextUtils.isEmpty(r4);
        if (r7 == 0) goto L_0x01ac;
    L_0x00d1:
        r4 = r3.mAvatarIV;
        r7 = qsbk.app.utils.UIHelper.getDefaultAvatar();
        r4.setImageResource(r7);
    L_0x00da:
        r4 = r0.userId;
        r4 = qsbk.app.utils.RemarkManager.getRemark(r4);
        r7 = r9.h;
        if (r7 == 0) goto L_0x01c1;
    L_0x00e4:
        r7 = android.text.TextUtils.isEmpty(r4);
        if (r7 != 0) goto L_0x01b3;
    L_0x00ea:
        r7 = r3.mNameTV;
        r7.setText(r4);
        r4 = r3.mRemarkNameTV;
        r4.setVisibility(r1);
        r4 = r3.mRemarkNameTV;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = " (";
        r7 = r7.append(r8);
        r8 = r0.userName;
        r7 = r7.append(r8);
        r8 = ")";
        r7 = r7.append(r8);
        r7 = r7.toString();
        r4.setText(r7);
    L_0x0114:
        r4 = r3.mCheckView;
        r7 = r0.alreadyInGroup;
        if (r7 != 0) goto L_0x011b;
    L_0x011a:
        r1 = 1;
    L_0x011b:
        r4.setEnabled(r1);
        r1 = r3.mCheckView;
        r4 = r9.d;
        r4 = r4.contains(r0);
        r1.setChecked(r4);
        r4 = r3.divider;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x01e5;
    L_0x0131:
        r1 = r6;
    L_0x0132:
        r4.setBackgroundColor(r1);
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0206;
    L_0x013b:
        r1 = "F";
        r4 = r0.gender;
        r1 = r1.equalsIgnoreCase(r4);
        if (r1 == 0) goto L_0x01ea;
    L_0x0145:
        r1 = r3.mGenderIV;
        r4 = 2130839596; // 0x7f02082c float:1.7284207E38 double:1.052774641E-314;
        r1.setImageResource(r4);
        r1 = r3.mAgeTV;
        r4 = r9.b;
        r4 = r4.getResources();
        r5 = 2131623950; // 0x7f0e000e float:1.8875066E38 double:1.0531621635E-314;
        r4 = r4.getColor(r5);
        r1.setTextColor(r4);
    L_0x015f:
        r1 = r3.mAgeTV;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r0.age;
        r4 = r4.append(r5);
        r5 = "";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r1.setText(r4);
        r1 = r9.g;
        if (r1 == 0) goto L_0x0285;
    L_0x017d:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FRIEND;
        if (r1 != r2) goto L_0x023d;
    L_0x0183:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0238;
    L_0x018b:
        r1 = 2130839433; // 0x7f020789 float:1.7283876E38 double:1.0527745606E-314;
    L_0x018e:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cn;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x019d:
        r0 = r2;
        goto L_0x0096;
    L_0x01a0:
        r0 = r11.getTag();
        r0 = (qsbk.app.adapter.QiuYouAdapter.b) r0;
        r3 = r0;
        goto L_0x00b1;
    L_0x01a9:
        r0 = r5;
        goto L_0x00b8;
    L_0x01ac:
        r7 = r3.mAvatarIV;
        qsbk.app.image.FrescoImageloader.displayAvatar(r7, r4);
        goto L_0x00da;
    L_0x01b3:
        r4 = r3.mNameTV;
        r7 = r0.userName;
        r4.setText(r7);
        r4 = r3.mRemarkNameTV;
        r4.setVisibility(r2);
        goto L_0x0114;
    L_0x01c1:
        r7 = r9.h;
        if (r7 != 0) goto L_0x0114;
    L_0x01c5:
        r7 = android.text.TextUtils.isEmpty(r4);
        if (r7 != 0) goto L_0x01d7;
    L_0x01cb:
        r7 = r3.mNameTV;
        r7.setText(r4);
        r4 = r3.mRemarkNameTV;
        r4.setVisibility(r2);
        goto L_0x0114;
    L_0x01d7:
        r4 = r3.mNameTV;
        r7 = r0.userName;
        r4.setText(r7);
        r4 = r3.mRemarkNameTV;
        r4.setVisibility(r2);
        goto L_0x0114;
    L_0x01e5:
        r1 = -1184275; // 0xffffffffffededed float:NaN double:NaN;
        goto L_0x0132;
    L_0x01ea:
        r1 = r3.mGenderIV;
        r4 = 2130839602; // 0x7f020832 float:1.728422E38 double:1.052774644E-314;
        r1.setImageResource(r4);
        r1 = r3.mAgeTV;
        r4 = r9.b;
        r4 = r4.getResources();
        r5 = 2131623951; // 0x7f0e000f float:1.8875068E38 double:1.053162164E-314;
        r4 = r4.getColor(r5);
        r1.setTextColor(r4);
        goto L_0x015f;
    L_0x0206:
        r1 = "F";
        r4 = r0.gender;
        r1 = r1.equalsIgnoreCase(r4);
        if (r1 == 0) goto L_0x0227;
    L_0x0210:
        r1 = r3.mGenderAgeLL;
        r4 = 2130839594; // 0x7f02082a float:1.7284203E38 double:1.05277464E-314;
        r1.setBackgroundResource(r4);
        r1 = r3.mGenderIV;
        r4 = 2130839593; // 0x7f020829 float:1.72842E38 double:1.0527746397E-314;
        r1.setImageResource(r4);
    L_0x0220:
        r1 = r3.mAgeTV;
        r1.setTextColor(r5);
        goto L_0x015f;
    L_0x0227:
        r1 = r3.mGenderAgeLL;
        r4 = 2130839603; // 0x7f020833 float:1.7284221E38 double:1.0527746446E-314;
        r1.setBackgroundResource(r4);
        r1 = r3.mGenderIV;
        r4 = 2130839601; // 0x7f020831 float:1.7284217E38 double:1.0527746437E-314;
        r1.setImageResource(r4);
        goto L_0x0220;
    L_0x0238:
        r1 = 2130839432; // 0x7f020788 float:1.7283874E38 double:1.05277456E-314;
        goto L_0x018e;
    L_0x023d:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FOLLOW_REPLIED;
        if (r1 == r2) goto L_0x0249;
    L_0x0243:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FOLLOW_UNREPLIED;
        if (r1 != r2) goto L_0x0267;
    L_0x0249:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0263;
    L_0x0251:
        r1 = 2130839431; // 0x7f020787 float:1.7283872E38 double:1.0527745597E-314;
    L_0x0254:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cv;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x0263:
        r1 = 2130839430; // 0x7f020786 float:1.728387E38 double:1.052774559E-314;
        goto L_0x0254;
    L_0x0267:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0281;
    L_0x026f:
        r1 = 2130839429; // 0x7f020785 float:1.7283868E38 double:1.0527745587E-314;
    L_0x0272:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cw;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x0281:
        r1 = 2130839428; // 0x7f020784 float:1.7283866E38 double:1.052774558E-314;
        goto L_0x0272;
    L_0x0285:
        r0 = r3.mUnreplyIV;
        r0.setVisibility(r2);
        goto L_0x0011;
    L_0x028c:
        if (r11 == 0) goto L_0x0296;
    L_0x028e:
        r0 = r11.getTag();
        r0 = r0 instanceof qsbk.app.adapter.QiuYouAdapter.a;
        if (r0 != 0) goto L_0x0402;
    L_0x0296:
        r3 = new qsbk.app.adapter.QiuYouAdapter$a;
        r3.<init>(r9);
        r0 = r9.b;
        r0 = android.view.LayoutInflater.from(r0);
        r7 = 2130903543; // 0x7f0301f7 float:1.7413907E38 double:1.052806235E-314;
        r11 = r0.inflate(r7, r12, r1);
        r0 = 2131756292; // 0x7f100504 float:1.9143487E38 double:1.053227549E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.CheckBox) r0;
        r3.mCheckView = r0;
        r0 = 2131755553; // 0x7f100221 float:1.9141989E38 double:1.053227184E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mAvatarIV = r0;
        r0 = 2131755304; // 0x7f100128 float:1.9141483E38 double:1.053227061E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mNameTV = r0;
        r0 = 2131755554; // 0x7f100222 float:1.914199E38 double:1.0532271846E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.LinearLayout) r0;
        r3.mGenderAgeLL = r0;
        r0 = 2131755555; // 0x7f100223 float:1.9141993E38 double:1.053227185E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mGenderIV = r0;
        r0 = 2131755364; // 0x7f100164 float:1.9141605E38 double:1.0532270907E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mAgeTV = r0;
        r0 = 2131756821; // 0x7f100715 float:1.914456E38 double:1.0532278105E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r3.mUnreplyIV = r0;
        r0 = 2131756820; // 0x7f100714 float:1.9144558E38 double:1.05322781E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.LinearLayout) r0;
        r3.mUnreplyLin = r0;
        r0 = 2131755561; // 0x7f100229 float:1.9142005E38 double:1.053227188E-314;
        r0 = r11.findViewById(r0);
        r3.divider = r0;
        r7 = r3.mCheckView;
        r0 = r9.a;
        if (r0 == 0) goto L_0x03ff;
    L_0x030f:
        r0 = r1;
    L_0x0310:
        r7.setVisibility(r0);
        r0 = r3.mCheckView;
        r0.setFocusable(r1);
        r0 = r3.mCheckView;
        r0.setClickable(r1);
        r0 = 2131756823; // 0x7f100717 float:1.9144564E38 double:1.0532278115E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r3.mFansComeFrom = r0;
        r11.setTag(r3);
    L_0x032b:
        r0 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r0 == 0) goto L_0x040b;
    L_0x0331:
        r11.setBackgroundColor(r4);
        r0 = r9.c;
        r0 = r0.get(r10);
        r0 = (qsbk.app.model.BaseUserInfo) r0;
        r4 = r0.userIcon;
        r7 = r0.userId;
        r4 = qsbk.app.QsbkApp.absoluteUrlOfMediumUserIcon(r4, r7);
        r7 = android.text.TextUtils.isEmpty(r4);
        if (r7 == 0) goto L_0x040e;
    L_0x034a:
        r4 = r3.mAvatarIV;
        r7 = qsbk.app.utils.UIHelper.getDefaultAvatar();
        r4.setImageResource(r7);
    L_0x0353:
        r4 = r3.mNameTV;
        r7 = r0.userName;
        r4.setText(r7);
        r4 = r3.mCheckView;
        r7 = r0.alreadyInGroup;
        if (r7 != 0) goto L_0x0361;
    L_0x0360:
        r1 = 1;
    L_0x0361:
        r4.setEnabled(r1);
        r1 = r3.mCheckView;
        r4 = r9.d;
        r4 = r4.contains(r0);
        r1.setChecked(r4);
        r1 = r3.divider;
        r4 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r4 == 0) goto L_0x0415;
    L_0x0377:
        r1.setBackgroundColor(r6);
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0436;
    L_0x0380:
        r1 = "F";
        r4 = r0.gender;
        r1 = r1.equalsIgnoreCase(r4);
        if (r1 == 0) goto L_0x041a;
    L_0x038a:
        r1 = r3.mGenderIV;
        r4 = 2130839596; // 0x7f02082c float:1.7284207E38 double:1.052774641E-314;
        r1.setImageResource(r4);
        r1 = r3.mAgeTV;
        r4 = r9.b;
        r4 = r4.getResources();
        r5 = 2131623950; // 0x7f0e000e float:1.8875066E38 double:1.0531621635E-314;
        r4 = r4.getColor(r5);
        r1.setTextColor(r4);
    L_0x03a4:
        r1 = r3.mAgeTV;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r0.age;
        r4 = r4.append(r5);
        r5 = "";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r1.setText(r4);
        r1 = r0.comeFrom;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0468;
    L_0x03c6:
        r1 = r3.mFansComeFrom;
        r4 = "来源：其他";
        r1.setText(r4);
    L_0x03cd:
        r4 = r3.mFansComeFrom;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0517;
    L_0x03d5:
        r1 = -12171438; // 0xffffffffff464752 float:-2.6355746E38 double:NaN;
    L_0x03d8:
        r4.setTextColor(r1);
        r1 = r9.g;
        if (r1 == 0) goto L_0x0569;
    L_0x03df:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FRIEND;
        if (r1 != r2) goto L_0x0521;
    L_0x03e5:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x051c;
    L_0x03ed:
        r1 = 2130839433; // 0x7f020789 float:1.7283876E38 double:1.0527745606E-314;
    L_0x03f0:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cx;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x03ff:
        r0 = r2;
        goto L_0x0310;
    L_0x0402:
        r0 = r11.getTag();
        r0 = (qsbk.app.adapter.QiuYouAdapter.a) r0;
        r3 = r0;
        goto L_0x032b;
    L_0x040b:
        r4 = r5;
        goto L_0x0331;
    L_0x040e:
        r7 = r3.mAvatarIV;
        qsbk.app.image.FrescoImageloader.displayAvatar(r7, r4);
        goto L_0x0353;
    L_0x0415:
        r6 = -1184275; // 0xffffffffffededed float:NaN double:NaN;
        goto L_0x0377;
    L_0x041a:
        r1 = r3.mGenderIV;
        r4 = 2130839602; // 0x7f020832 float:1.728422E38 double:1.052774644E-314;
        r1.setImageResource(r4);
        r1 = r3.mAgeTV;
        r4 = r9.b;
        r4 = r4.getResources();
        r5 = 2131623951; // 0x7f0e000f float:1.8875068E38 double:1.053162164E-314;
        r4 = r4.getColor(r5);
        r1.setTextColor(r4);
        goto L_0x03a4;
    L_0x0436:
        r1 = "F";
        r4 = r0.gender;
        r1 = r1.equalsIgnoreCase(r4);
        if (r1 == 0) goto L_0x0457;
    L_0x0440:
        r1 = r3.mGenderAgeLL;
        r4 = 2130839594; // 0x7f02082a float:1.7284203E38 double:1.05277464E-314;
        r1.setBackgroundResource(r4);
        r1 = r3.mGenderIV;
        r4 = 2130839593; // 0x7f020829 float:1.72842E38 double:1.0527746397E-314;
        r1.setImageResource(r4);
    L_0x0450:
        r1 = r3.mAgeTV;
        r1.setTextColor(r5);
        goto L_0x03a4;
    L_0x0457:
        r1 = r3.mGenderAgeLL;
        r4 = 2130839603; // 0x7f020833 float:1.7284221E38 double:1.0527746446E-314;
        r1.setBackgroundResource(r4);
        r1 = r3.mGenderIV;
        r4 = 2130839601; // 0x7f020831 float:1.7284217E38 double:1.0527746437E-314;
        r1.setImageResource(r4);
        goto L_0x0450;
    L_0x0468:
        r1 = r0.comeFrom;
        r4 = "null";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x047b;
    L_0x0472:
        r1 = r3.mFansComeFrom;
        r4 = "来源：其他";
        r1.setText(r4);
        goto L_0x03cd;
    L_0x047b:
        r1 = r0.comeFrom;
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x049a }
        r4.<init>(r1);	 Catch:{ JSONException -> 0x049a }
        r1 = new qsbk.app.im.IMChatMsgSource;	 Catch:{ JSONException -> 0x049a }
        r1.<init>();	 Catch:{ JSONException -> 0x049a }
        r1.parseFromJSONObject(r4);	 Catch:{ JSONException -> 0x049a }
        r4 = r1.type;	 Catch:{ JSONException -> 0x049a }
        switch(r4) {
            case 1: goto L_0x0491;
            case 2: goto L_0x04a7;
            case 3: goto L_0x04b0;
            case 4: goto L_0x04b9;
            case 5: goto L_0x04c2;
            case 6: goto L_0x04cb;
            case 7: goto L_0x04dd;
            case 8: goto L_0x04d4;
            case 9: goto L_0x050e;
            default: goto L_0x048f;
        };	 Catch:{ JSONException -> 0x049a }
    L_0x048f:
        goto L_0x03cd;
    L_0x0491:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：附近糗友";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x049a:
        r1 = move-exception;
        r4 = r3.mFansComeFrom;
        r5 = "来源: 其他";
        r4.setText(r5);
        r1.printStackTrace();
        goto L_0x03cd;
    L_0x04a7:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：糗事";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04b0:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：糗事";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04b9:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：其他";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04c2:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：昵称搜索";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04cb:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：附近糗友";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04d4:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：糗友圈";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04dd:
        r4 = r1.valueObj;	 Catch:{ JSONException -> 0x049a }
        r4 = r4.group_name;	 Catch:{ JSONException -> 0x049a }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ JSONException -> 0x049a }
        if (r4 == 0) goto L_0x04f0;
    L_0x04e7:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来自群";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x04f0:
        r4 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x049a }
        r5.<init>();	 Catch:{ JSONException -> 0x049a }
        r6 = "来自群：";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x049a }
        r1 = r1.valueObj;	 Catch:{ JSONException -> 0x049a }
        r1 = r1.group_name;	 Catch:{ JSONException -> 0x049a }
        r1 = r5.append(r1);	 Catch:{ JSONException -> 0x049a }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x049a }
        r4.setText(r1);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x050e:
        r1 = r3.mFansComeFrom;	 Catch:{ JSONException -> 0x049a }
        r4 = "来源：直播间";
        r1.setText(r4);	 Catch:{ JSONException -> 0x049a }
        goto L_0x03cd;
    L_0x0517:
        r1 = -5329234; // 0xffffffffffaeaeae float:NaN double:NaN;
        goto L_0x03d8;
    L_0x051c:
        r1 = 2130839432; // 0x7f020788 float:1.7283874E38 double:1.05277456E-314;
        goto L_0x03f0;
    L_0x0521:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FOLLOW_REPLIED;
        if (r1 == r2) goto L_0x052d;
    L_0x0527:
        r1 = r0.relationship;
        r2 = qsbk.app.model.relationship.Relationship.FOLLOW_UNREPLIED;
        if (r1 != r2) goto L_0x054b;
    L_0x052d:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0547;
    L_0x0535:
        r1 = 2130839431; // 0x7f020787 float:1.7283872E38 double:1.0527745597E-314;
    L_0x0538:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cy;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x0547:
        r1 = 2130839430; // 0x7f020786 float:1.728387E38 double:1.052774559E-314;
        goto L_0x0538;
    L_0x054b:
        r2 = r3.mUnreplyIV;
        r1 = qsbk.app.utils.UIHelper.isNightTheme();
        if (r1 == 0) goto L_0x0565;
    L_0x0553:
        r1 = 2130839429; // 0x7f020785 float:1.7283868E38 double:1.0527745587E-314;
    L_0x0556:
        r2.setImageResource(r1);
        r1 = r3.mUnreplyLin;
        r2 = new qsbk.app.adapter.cz;
        r2.<init>(r9, r0);
        r1.setOnClickListener(r2);
        goto L_0x0011;
    L_0x0565:
        r1 = 2130839428; // 0x7f020784 float:1.7283866E38 double:1.052774558E-314;
        goto L_0x0556;
    L_0x0569:
        r0 = r3.mUnreplyIV;
        r0.setVisibility(r2);
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.adapter.QiuYouAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public void myQiuyouOperation(int i, BaseUserInfo baseUserInfo) {
        this.e = i;
        String str = "";
        str = "";
        str = "";
        String str2;
        if (i == 1) {
            str2 = "再想想";
            a("取消粉TA后，不能发图片。是否取消粉?", str2, "取消粉", new db(this), new da(this, baseUserInfo, "正在取消粉,请稍后..."));
        } else if (i == 2) {
            str2 = "再想想";
            a("是否取消粉TA?", str2, "取消粉", new co(this), new dc(this, baseUserInfo, "正在取消粉,请稍后..."));
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
        SimpleHttpTask csVar = new cs(this, str2, new cp(this, str));
        csVar.setMapParams(map);
        this.i.setOnCancelListener(new ct(this, csVar));
        csVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(JSONObject jSONObject, String str) {
        Intent intent = new Intent("QIU_YOU_RELATION_CHANGED");
        intent.putExtra("userId", str);
        Object optString = jSONObject.optString(ConversationActivity.RELATIONSHIP);
        if (!TextUtils.isEmpty(optString)) {
            intent.putExtra(ConversationActivity.RELATIONSHIP, Relationship.valueOf(optString.toUpperCase(Locale.US)));
            this.f.sendBroadcast(intent);
        }
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
