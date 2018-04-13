package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.BaseAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.cons.b;
import com.baidu.mobstat.Config;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.publish.Publish_Image;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupInfo$MemberInfo;
import qsbk.app.model.GroupInfo$PicInfo;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.GroupNotifier.OnNotifyListener;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.GroupDialog.Builder;
import qsbk.app.widget.GroupLevelHelpDialog;
import qsbk.app.widget.LinearReactiveLayout;
import qsbk.app.widget.Switch;

public class GroupInfoActivity extends BaseActionBarActivity implements OnNotifyListener {
    private static final String a = GroupInfoActivity.class.getSimpleName();
    public static int sActionResult = 0;
    private GroupLevelHelpDialog A;
    private TextView B;
    private View C;
    private TextView D;
    private boolean E;
    private int F = 0;
    private int G;
    private boolean H;
    private int I = UIHelper.getDefaultImageTileBackground();
    private OnCheckedChangeListener J = new lo(this);
    private GroupInfo b;
    private GridView c;
    private LinearReactiveLayout d;
    private GridAdapter e;
    private ProgressDialog f;
    private TextView g;
    private TextView h;
    private TextView i;
    private View j;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private Switch p;
    private Switch q;
    private View r;
    private View s;
    private View t;
    private View u;
    private View v;
    private TextView w;
    private View x;
    private View y;
    private View z;

    public class AvatarsAdapter extends BaseAdapter {
        final /* synthetic */ GroupInfoActivity a;
        private List<GroupInfo$MemberInfo> b;

        public AvatarsAdapter(GroupInfoActivity groupInfoActivity, List<GroupInfo$MemberInfo> list) {
            this.a = groupInfoActivity;
            this.b = list;
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new SimpleDraweeView(viewGroup.getContext());
                view.setScaleType(ScaleType.CENTER_CROP);
                float f = this.a.getResources().getDisplayMetrics().density;
                int i2 = (int) (36.0f * f);
                LayoutParams marginLayoutParams = new MarginLayoutParams(i2, i2);
                marginLayoutParams.rightMargin = (int) (f * 6.0f);
                view.setLayoutParams(marginLayoutParams);
                view.setScaleType(ScaleType.CENTER_CROP);
            } else {
                ImageView imageView = (ImageView) view;
            }
            GroupInfo$MemberInfo groupInfo$MemberInfo = (GroupInfo$MemberInfo) this.b.get(i);
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(groupInfo$MemberInfo.icon, String.valueOf(groupInfo$MemberInfo.uid));
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                view.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(view, absoluteUrlOfMediumUserIcon);
            }
            return view;
        }
    }

    public class GridAdapter extends BaseAdapter {
        final /* synthetic */ GroupInfoActivity a;
        private List<GroupInfo$PicInfo> b;
        private boolean c;

        public GridAdapter(GroupInfoActivity groupInfoActivity, List<GroupInfo$PicInfo> list, boolean z) {
            this.a = groupInfoActivity;
            this.b = list;
            this.c = z;
        }

        public int getCount() {
            int size = this.b == null ? 0 : this.b.size();
            if (!this.c) {
                return size;
            }
            if (size < 8) {
                return size + 1;
            }
            return 8;
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView simpleDraweeView;
            TextView textView;
            int i2 = 0;
            if (view == null) {
                RoundingParams roundingParams;
                Context context = viewGroup.getContext();
                view = new mt(this, context);
                simpleDraweeView = new SimpleDraweeView(context);
                RoundingParams roundingParams2 = ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).getRoundingParams();
                if (roundingParams2 == null) {
                    roundingParams = new RoundingParams();
                } else {
                    roundingParams = roundingParams2;
                }
                roundingParams.setCornersRadius(4.0f * simpleDraweeView.getResources().getDisplayMetrics().density);
                ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(roundingParams);
                simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
                view.addView(simpleDraweeView);
                textView = new TextView(context);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(14);
                layoutParams.setMargins(0, 0, 0, (int) (15.0f * viewGroup.getResources().getDisplayMetrics().density));
                textView.setLayoutParams(layoutParams);
                textView.setTextColor(-1);
                textView.setTextSize(14.0f);
                textView.setText("审核中");
                view.addView(textView);
            } else {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                textView = (TextView) relativeLayout.getChildAt(1);
                simpleDraweeView = (SimpleDraweeView) relativeLayout.getChildAt(0);
            }
            if (i == this.b.size()) {
                textView.setVisibility(4);
                FrescoImageloader.displayImage(simpleDraweeView, FrescoImageloader.getFrescoResUrl(R.drawable.group_info_add_pic));
                simpleDraweeView.setOnClickListener(new mu(this, i));
            } else {
                GroupInfo$PicInfo groupInfo$PicInfo = (GroupInfo$PicInfo) this.b.get(i);
                if (this.a.H) {
                    if (groupInfo$PicInfo.status != 2) {
                        i2 = 4;
                    }
                    textView.setVisibility(i2);
                } else {
                    textView.setVisibility(4);
                }
                FrescoImageloader.displayImage(simpleDraweeView, groupInfo$PicInfo.picUrl, this.a.I);
                simpleDraweeView.setOnClickListener(new mv(this, i));
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Context context, GroupBriefInfo groupBriefInfo) {
        Intent intent = new Intent(context, GroupInfoActivity.class);
        intent.putExtra("group_brief_info", groupBriefInfo);
        context.startActivity(intent);
    }

    public static void launch(Context context, GroupInfo groupInfo) {
        if (QsbkApp.checkLogin(context, true, true)) {
            Intent intent = new Intent(context, GroupInfoActivity.class);
            intent.putExtra("group_info", groupInfo);
            context.startActivity(intent);
        }
    }

    public static void launch(Context context, int i, String str, boolean z) {
        Intent intent = new Intent(context, GroupInfoActivity.class);
        intent.putExtra("group_id", i);
        intent.putExtra("group_name", str);
        intent.putExtra("from_conversation", z);
        context.startActivity(intent);
    }

    public static Pair getUrl(String str) {
        Matcher matcher = Pattern.compile("([0-9]+):(.+)").matcher(str);
        if (matcher.find()) {
            return new Pair(Integer.valueOf(Integer.parseInt(matcher.group(1))), matcher.group(2));
        }
        System.out.println("文件路径格式不正确！");
        return null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        GroupNotifier.register(this);
    }

    protected void onResume() {
        super.onResume();
        if (sActionResult != 0) {
            int i = sActionResult >> 8;
            int i2 = sActionResult & 255;
            sActionResult = 0;
            if (i == 1) {
                this.e.b.remove(i2);
                this.e.notifyDataSetChanged();
                l();
            } else if (i == 2) {
                this.F = i2;
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        }
        if (this.b != null) {
            m();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        GroupNotifier.unregister(this);
    }

    protected boolean d() {
        return true;
    }

    protected String e() {
        return null;
    }

    protected int a() {
        return R.layout.activity_group_info;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        Intent intent = getIntent();
        if (intent != null) {
            this.E = intent.getBooleanExtra("from_conversation", false);
            this.b = (GroupInfo) intent.getSerializableExtra("group_info");
            if (this.b == null) {
                GroupBriefInfo groupBriefInfo = (GroupBriefInfo) intent.getSerializableExtra("group_brief_info");
                if (groupBriefInfo == null) {
                    this.G = intent.getIntExtra("group_id", -1);
                    CharSequence stringExtra = intent.getStringExtra("group_name");
                    if (stringExtra == null) {
                        stringExtra = "";
                    }
                    setTitle(stringExtra);
                    n();
                    return;
                }
                this.b = new GroupInfo(groupBriefInfo);
                this.G = this.b.id;
                m();
                n();
                return;
            }
            this.G = this.b.id;
            m();
            n();
            return;
        }
        n();
    }

    private void g() {
        this.c = (GridView) findViewById(R.id.group_info_grid);
        this.x = findViewById(R.id.group_level);
        this.y = findViewById(R.id.group_members);
        this.z = findViewById(R.id.group_owner);
        this.r = findViewById(R.id.group_rank);
        this.s = findViewById(R.id.group_rank_line);
        this.g = (TextView) findViewById(R.id.group_level_text);
        this.g.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_level_bg_night : R.drawable.group_info_level_bg);
        this.h = (TextView) findViewById(R.id.group_level_require);
        this.h.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.group_info_help_night : R.drawable.group_info_help), null);
        this.i = (TextView) findViewById(R.id.group_members_count);
        this.i.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next), null);
        this.d = (LinearReactiveLayout) findViewById(R.id.group_info_avatars);
        this.j = findViewById(R.id.group_add_member);
        this.j.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_add_night : R.drawable.group_info_add);
        this.k = (ImageView) findViewById(R.id.group_owner_avatar);
        this.l = (TextView) findViewById(R.id.group_owner_name);
        this.m = (TextView) findViewById(R.id.group_owner_term);
        this.n = (TextView) findViewById(R.id.group_rank_num);
        this.o = (TextView) findViewById(R.id.group_describe);
        this.t = findViewById(R.id.group_notify);
        this.p = (Switch) findViewById(R.id.group_notify_switch);
        this.u = findViewById(R.id.group_mute_all);
        this.q = (Switch) findViewById(R.id.group_mute_all_switch);
        this.v = findViewById(R.id.group_action);
        this.w = (TextView) findViewById(R.id.group_action_text);
        this.B = (TextView) findViewById(R.id.group_intru_modify);
        this.C = findViewById(R.id.group_member_level);
        this.D = (TextView) findViewById(R.id.group_member_level_text);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == ApplyForGroupActivity.REQ_APPLY) {
            a(i2);
            n();
        } else if (i == 100) {
            if (i2 == -1 && intent != null) {
                d(intent.getData().toString());
            }
        } else if (i == 101) {
            if (i2 == -1) {
                a("上传图片中，请稍等...");
                k();
            }
        } else if (i == 1 && i2 == 0) {
            LogUtil.d("修改简介失败");
        }
    }

    private void i() {
        if (this.f == null) {
            this.f = new ProgressDialog(this, 3);
            this.f.setProgressStyle(0);
            this.f.setCancelable(false);
        }
    }

    private void a(String str) {
        runOnUiThread(new lz(this, str));
    }

    private void j() {
        if (this.f != null) {
            this.f.cancel();
        }
    }

    private void b(String str) {
        new Builder(this).setMessage(str).setPositiveButton("重试", new mn(this)).setNegativeButton("取消", new mm(this)).show();
    }

    private void c(String str) {
        if (this.F == 0) {
            GroupNotifier.updateGroupInfo(String.valueOf(this.G), null, str);
            GroupNotifier.notify(this.G, 1);
        }
        GroupInfo$PicInfo groupInfo$PicInfo = new GroupInfo$PicInfo();
        groupInfo$PicInfo.picUrl = str;
        groupInfo$PicInfo.status = 2;
        if (this.e.b == null) {
            this.e.b = new ArrayList();
        }
        if (this.F == this.e.b.size()) {
            this.e.b.add(groupInfo$PicInfo);
        } else {
            this.e.b.set(this.F, groupInfo$PicInfo);
        }
        this.e.notifyDataSetChanged();
        l();
    }

    private void k() {
        new HttpTask(Constants.URL_GET_TOKEN_FOR_GROUP, Constants.URL_GET_TOKEN_FOR_GROUP, new mo(this, Uri.fromFile(new File(Publish_Image.getSendImagePath())))).execute(new Void[0]);
    }

    private void a(int i) {
        int i2 = -4486889;
        switch (i) {
            case 0:
                this.v.setOnClickListener(new mp(this));
                this.w.setCompoundDrawablesWithIntrinsicBounds(UIHelper.isNightTheme() ? R.drawable.fans_small_night : R.drawable.fans_small, 0, 0, 0);
                this.w.setText(getResources().getString(R.string.group_not_join));
                this.w.setTextColor(UIHelper.isNightTheme() ? -4486889 : getResources().getColor(R.color.group_info_text_green));
                this.w.setVisibility(0);
                return;
            case 1:
                this.v.setOnClickListener(null);
                this.w.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                this.w.setText(getResources().getString(R.string.group_applied));
                this.w.setTextColor(UIHelper.isNightTheme() ? -12171438 : getResources().getColor(R.color.group_info_text));
                this.w.setVisibility(0);
                return;
            case 2:
                this.v.setOnClickListener(new mq(this));
                this.w.setCompoundDrawablesWithIntrinsicBounds(UIHelper.isNightTheme() ? R.drawable.group_info_chat_night : R.drawable.group_info_chat, 0, 0, 0);
                this.w.setText(getResources().getString(R.string.group_joined));
                TextView textView = this.w;
                if (!UIHelper.isNightTheme()) {
                    i2 = getResources().getColor(R.color.group_info_text_green);
                }
                textView.setTextColor(i2);
                this.w.setVisibility(0);
                return;
            default:
                this.w.setVisibility(8);
                return;
        }
    }

    private void l() {
        int count = ((this.e.getCount() - 1) / 4) + 1;
        int dip2px = UIHelper.dip2px(this, 4.0f);
        count = (count * dip2px) + ((((getResources().getDisplayMetrics().widthPixels - (dip2px * 5)) / 4) * count) + dip2px);
        LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.height = count;
        this.c.setLayoutParams(layoutParams);
    }

    private void m() {
        Iterator it;
        int i;
        GroupInfo$MemberInfo groupInfo$MemberInfo;
        GroupInfo groupInfo = this.b;
        setTitle(groupInfo.name);
        this.H = groupInfo.isOwner;
        if (!this.H && groupInfo.joinStatus == 2) {
            BaseUserInfo member = new GroupMemberManager(groupInfo).getMember(QsbkApp.currentUser.userId);
            if (member != null) {
                this.H = member.isAdmin;
            }
        }
        if (!this.H) {
            it = groupInfo.picList.iterator();
            while (it.hasNext()) {
                if (((GroupInfo$PicInfo) it.next()).status == 2) {
                    it.remove();
                }
            }
        }
        this.e = new GridAdapter(this, groupInfo.picList, this.H);
        this.c.setAdapter(this.e);
        l();
        if (this.H) {
            this.d.setPadding(0, 0, getResources().getDimensionPixelSize(R.dimen.group_info_avatar_width), 0);
            this.j.setVisibility(0);
            this.j.setOnClickListener(new mr(this, groupInfo));
            this.B.setVisibility(0);
            this.B.setOnClickListener(new ms(this, groupInfo));
            this.u.setVisibility(8);
            this.q.setChecked(SharePreferenceUtils.getSharePreferencesBoolValue("mute_all_" + groupInfo.id));
            this.q.setOnCheckedChangeListener(this.J);
        } else {
            this.c.setOnItemClickListener(null);
            this.y.setOnClickListener(null);
            this.B.setVisibility(8);
            this.u.setVisibility(8);
        }
        View view = this.C;
        if (groupInfo.joinStatus != 2 || groupInfo.titles == null) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        this.D.setText(groupInfo.titleEnable ? "开启" : "关闭");
        this.C.setOnClickListener(new lp(this, groupInfo));
        this.g.setText(String.valueOf(groupInfo.level));
        if (groupInfo.level >= 4) {
            this.h.setText("已达到最高级别");
        } else {
            this.h.setText(String.format("距离升级还需%d人", new Object[]{Integer.valueOf(groupInfo.leftOMember)}));
        }
        this.x.setOnClickListener(new lq(this, groupInfo));
        this.y.setOnClickListener(new lr(this, groupInfo));
        this.i.setText(groupInfo.memberNum + "人");
        if (groupInfo.memberList != null) {
            this.d.setAdapter(new AvatarsAdapter(this, groupInfo.memberList));
            it = groupInfo.memberList.iterator();
            while (it.hasNext()) {
                groupInfo$MemberInfo = (GroupInfo$MemberInfo) it.next();
                if (groupInfo$MemberInfo.uid == groupInfo.ownerId) {
                    break;
                }
            }
        }
        groupInfo$MemberInfo = null;
        if (groupInfo.ownedTerm > 0) {
            this.m.setVisibility(0);
            this.m.setText("共连任" + groupInfo.ownedTerm + "期，这期倒数" + groupInfo.leftODay + "天");
        } else {
            this.m.setVisibility(8);
        }
        if (groupInfo$MemberInfo != null) {
            this.z.setVisibility(0);
            CharSequence remark = RemarkManager.getRemark(String.valueOf(groupInfo$MemberInfo.uid));
            if (TextUtils.isEmpty(remark)) {
                this.l.setText(groupInfo$MemberInfo.login);
            } else {
                this.l.setText(remark);
            }
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(groupInfo$MemberInfo.icon, String.valueOf(groupInfo.ownerId));
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.k.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(this.k, absoluteUrlOfMediumUserIcon);
            }
        } else {
            this.z.setVisibility(8);
            this.k.setImageResource(UIHelper.getDefaultAvatar());
        }
        this.z.setOnClickListener(new ls(this, groupInfo));
        if (groupInfo.rank != -1) {
            this.r.setVisibility(0);
            this.r.setOnClickListener(new lt(this, groupInfo));
            this.n.setText("第" + groupInfo.rank + "名");
            this.s.setVisibility(0);
        } else {
            this.r.setVisibility(8);
            this.s.setVisibility(8);
        }
        this.o.setText(groupInfo.description);
        if (groupInfo.joinStatus == 2) {
            this.d.setPadding(0, 0, getResources().getDimensionPixelSize(R.dimen.group_info_avatar_width), 0);
            this.j.setVisibility(0);
            this.j.setOnClickListener(new lu(this, groupInfo));
            this.t.setVisibility(0);
            this.p.setOnCheckedChangeListener(null);
            this.p.setChecked(groupInfo.notifySwitch);
            this.p.setOnCheckedChangeListener(new lv(this));
        } else {
            this.j.setVisibility(8);
            this.d.setPadding(0, 0, 0, 0);
            this.t.setVisibility(8);
        }
        a(groupInfo.joinStatus);
    }

    private void a(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.b.id));
        if (z) {
            hashMap.put("sids", "0");
        } else {
            hashMap.put("sid", "0");
        }
        hashMap.put("time", "86400");
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(z ? Constants.URL_GROUP_MEMBER_MUTE : Constants.URL_GROUP_MEMBER_UNMUTE, new Object[]{Integer.valueOf(this.b.id)}), new lw(this, this, "处理中...", z));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void c(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("sws", this.b.id + Config.TRACE_TODAY_VISIT_SPLIT + (z ? 1 : 0));
        String str = Constants.URL_SET_GROUP_MSG_SWITCH;
        HttpTask httpTask = new HttpTask(str, str, new lx(this, z));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    private void n() {
        showLoading();
        String str = String.format(Constants.URL_GROUP_DETAIL, new Object[]{String.valueOf(this.b == null ? this.G : this.b.id)}) + "?tid=" + (this.b == null ? this.G : this.b.id);
        new HttpTask(str, str, new ly(this)).execute(new Void[0]);
    }

    private void a(Uri uri, String str) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.b.id));
        hashMap.put("pic_urls", this.F + Config.TRACE_TODAY_VISIT_SPLIT + str);
        String format = String.format(Constants.URL_MOTIFY_GROUP_INFO, new Object[]{Integer.valueOf(r1)});
        HttpTask httpTask = new HttpTask(format, format, new ma(this));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    private UploadTaskExecutor a(String str, Uri uri) {
        String str2 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str2, uri, putExtra, new mb(this, uri));
    }

    private void d(String str) {
        Intent intent = new Intent(this, Publish_Image.class);
        intent.putExtra("picpath", str);
        intent.putExtra(Publish_Image.NEED_WATER_MARK, false);
        startActivityForResult(intent, 101);
    }

    protected void onStop() {
        super.onStop();
        if (this.A != null) {
            this.A.dismiss();
            this.A = null;
        }
    }

    public void onGroupNotify(int i, int i2) {
        getMainUIHandler().post(new mc(this, i, i2));
    }

    public void finish() {
        super.finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = true;
        boolean z2 = false;
        MenuItem findItem = menu.findItem(R.id.action_edit_name);
        MenuItem findItem2 = menu.findItem(R.id.action_resign);
        MenuItem findItem3 = menu.findItem(R.id.action_quit);
        MenuItem findItem4 = menu.findItem(R.id.action_report);
        if (this.b == null) {
            findItem.setVisible(false);
            findItem2.setVisible(false);
            findItem3.setVisible(false);
        } else if (this.H) {
            findItem.setVisible(true);
            findItem2.setVisible(this.b.isOwner);
            if (this.b.joinStatus != 2 || this.b.isOwner) {
                z = false;
            }
            findItem3.setVisible(z);
            findItem4.setVisible(false);
        } else {
            findItem.setVisible(false);
            findItem2.setVisible(false);
            if (this.b.joinStatus == 2) {
                z2 = true;
            }
            findItem3.setVisible(z2);
            findItem4.setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_edit_name:
                ChangeGroupNameActivity.launch(this, this.b);
                return true;
            case R.id.action_resign:
                q();
                return true;
            case R.id.action_report:
                t();
                return true;
            case R.id.action_quit:
                s();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void d(boolean z) {
        String format = String.format(Constants.URL_RESIGN, new Object[]{Integer.valueOf(this.G)});
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.G));
        hashMap.put("quit", z ? "1" : "0");
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new md(this, z, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void q() {
        new me(this, this).show();
    }

    private void r() {
        String format = String.format(Constants.URL_QUIT_GROUP, new Object[]{Integer.valueOf(this.G)});
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.G));
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new mi(this, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void s() {
        new Builder(this).setMessage("确定退出群？").setPositiveButton("确定", new mj(this)).setNegativeButton("取消", null).create().show();
    }

    private void e(String str) {
        String format = String.format(Constants.URL_REPORT_GROUP, new Object[]{Integer.valueOf(this.G)});
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.G));
        hashMap.put("reason", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new mk(this, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void t() {
        new AlertDialog.Builder(this).setCancelable(true).setItems(new String[]{"骚扰信息", "欺诈", "政治敏感", "淫秽色情", "其他"}, new ml(this)).create().show();
    }
}
