package qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleArticleActivity.ToReplyInfo;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;

public class QiuyouCircleNotificationItemView extends RelativeLayout {
    private ChatMsg a;
    private CircleArticle b;
    private UserInfo c;
    private String d;
    private String e;
    private String f;
    private int g;
    private ImageView h;
    private TextView i;
    public boolean isAtInfo;
    public boolean isCommentLike;
    public boolean isForwards;
    public boolean isLike;
    private TextView j;
    private TextView k;
    private ImageView l;
    private String m;
    private String n;
    private TextView o;
    private TextView p;
    private ImageView q;
    private ImageView r;
    private List<ChatMsg> s;
    private int t;

    public interface Jump {
        public static final String JUMP_ARTICLE = "article";
        public static final String JUMP_COMMENT = "comment";
    }

    public interface Type {
        public static final String QIUSHI_AT_USERS = "at_user";
        public static final String QIUSHI_COMMENT = "s_comment";
        public static final String QIUSHI_COMMENT_AT = "AT";
        public static final String QIUSHI_COMMENT_LIKE = "s_comment_like";
        public static final String QIUSHI_COMMENT_LIKE_TOTAL = "comment_like";
        public static final String QIUSHI_COMMENT_TOTAL = "comment";
        public static final String QIUSHI_HOUR_HOT = "hour_hot";
        public static final String QIUSHI_PASS = "";
        public static final String QIUSHI_PREFER = "prefer";
        public static final String QIUSHI_PROMOTE = "promote";
        public static final String QIUSHI_SMILE = "s_up";
        public static final String QIUSHI_SMILE_TOTAL = "up";
        public static final String QIUSHI_VIDEO_LOOP = "loop";
        public static final String TYPE_AT_INFO_TOTAL = "circle_at_info";
        public static final String TYPE_COMENT_LIKE = "circle_comment_like";
        public static final String TYPE_COMMENT = "comment";
        public static final String TYPE_COMMENT_AT = "comment_at";
        public static final String TYPE_FORWARD = "forward";
        public static final String TYPE_LIKE = "like";
    }

    public static class UserInfo {
        public String icon;
        public String id;
        public String name;

        public UserInfo(JSONObject jSONObject) {
            this.name = jSONObject.optString(QsbkDatabase.LOGIN);
            this.icon = jSONObject.optString("icon");
            this.id = jSONObject.optString("id");
            if (TextUtils.isEmpty(this.id)) {
                this.id = jSONObject.optString("uid");
            }
        }

        public UserInfo(String str, String str2, String str3) {
            this.id = str;
            this.name = str2;
            this.icon = str3;
        }
    }

    protected static class UserInfoOnClickListener implements OnClickListener {
        String a = null;
        String b = null;
        String c = null;

        public UserInfoOnClickListener(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public void onClick(View view) {
            if (QsbkApp.currentUser == null) {
                return;
            }
            if (QsbkApp.currentUser.userId.equals(this.a)) {
                MyInfoActivity.launch(view.getContext());
                return;
            }
            MyInfoActivity.launch(view.getContext(), this.a, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, this.a, "来自糗友圈"));
        }
    }

    private static class a implements OnClickListener {
        private CircleArticle a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;

        a(CircleArticle circleArticle, String str, String str2, String str3, String str4, String str5) {
            this.a = circleArticle;
            this.b = str;
            this.c = str2;
            this.e = str4;
            this.d = str3;
            this.f = str5;
        }

        public void onClick(View view) {
            if (this.a != null) {
                Context context = view.getContext();
                if (Type.TYPE_COMENT_LIKE.equals(this.f) || Type.TYPE_LIKE.equals(this.f)) {
                    CircleArticleActivity.launch(context, this.a.id, false);
                } else if (TextUtils.isEmpty(this.b)) {
                    CircleArticleActivity.launch(context, this.a.id, false);
                } else {
                    ToReplyInfo toReplyInfo = new ToReplyInfo();
                    toReplyInfo.commentId = this.b;
                    toReplyInfo.commentContent = this.c;
                    toReplyInfo.uid = this.d;
                    toReplyInfo.userName = this.e;
                    CircleArticleActivity.launch(context, this.a, toReplyInfo);
                }
            }
        }
    }

    public QiuyouCircleNotificationItemView(Context context) {
        super(context);
    }

    public QiuyouCircleNotificationItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QiuyouCircleNotificationItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        if (this.i == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.qiuyoucircle_notification_item, this, true);
            setBackgroundColor(UIHelper.isNightTheme() ? Color.parseColor("#000000") : Color.parseColor("#ffffff"));
            this.i = (TextView) findViewById(R.id.description);
            this.j = (TextView) findViewById(R.id.time);
            this.q = (ImageView) findViewById(R.id.play);
            this.l = (ImageView) findViewById(R.id.thumb_image);
            this.k = (TextView) findViewById(R.id.thumb_text);
            this.k.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.qiushi_notification_thumb_text_bg_night : R.drawable.qiushi_notification_thumb_text_bg);
            this.h = (ImageView) findViewById(R.id.icon);
            this.o = (TextView) findViewById(R.id.name);
            this.p = (TextView) findViewById(R.id.like_num_textview);
            this.r = (ImageView) findViewById(R.id.like_image);
        }
    }

    private void a(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void b() {
        int i = R.drawable.qiuyoucircle_like_on_night;
        CharSequence remark = RemarkManager.getRemark(this.c.id);
        String str;
        SpannableString spannableString;
        Resources resources;
        Drawable drawable;
        ImageSpan imageSpan;
        if (this.isLike) {
            if (TextUtils.isEmpty(remark)) {
                this.o.setText(this.c.name + "等");
            } else {
                this.o.setText(remark + "等");
            }
            str = "[赞]";
            spannableString = new SpannableString(str);
            resources = getResources();
            if (!UIHelper.isNightTheme()) {
                i = R.drawable.qiuyoucircle_like_on;
            }
            drawable = resources.getDrawable(i);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.i.setText(this.d);
        } else if (this.isAtInfo) {
            if (TextUtils.isEmpty(remark)) {
                this.o.setText(this.c.name + "等");
            } else {
                this.o.setText(remark + "等");
            }
            String str2 = "[@]";
            r2 = new SpannableString(str2);
            drawable = getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.at_night : R.drawable.at_day);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            r3 = new ImageSpan(drawable);
            r2.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            r2.setSpan(r3, 0, str2.length() + 0, 33);
            r2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str2.length() + 0, str2.length(), 33);
            this.i.setText("@了你");
        } else if (this.isCommentLike) {
            if (TextUtils.isEmpty(remark)) {
                this.o.setText(this.c.name + "等");
            } else {
                this.o.setText(remark + "等");
            }
            str = "[赞]";
            spannableString = new SpannableString(str);
            resources = getResources();
            if (!UIHelper.isNightTheme()) {
                i = R.drawable.qiuyoucircle_like_on;
            }
            drawable = resources.getDrawable(i);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.i.setText(this.d);
        } else if (this.isForwards) {
            if (TextUtils.isEmpty(remark)) {
                this.o.setText(this.c.name + "等");
            } else {
                this.o.setText(remark + "等");
            }
            this.i.setText(this.d);
        } else if (!this.isAtInfo && !this.isLike && !this.isCommentLike) {
            if ("comment".equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                this.i.setText(this.d);
            } else if (Type.TYPE_COMMENT_AT.equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                this.i.setText("回复 " + this.m + "：" + this.d);
            } else if (Type.TYPE_LIKE.equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                r0 = "[赞]";
                r2 = new SpannableString(r0);
                r1 = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
                r1.setBounds(0, 0, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
                r3 = new ImageSpan(r1);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
                r2.setSpan(r3, 0, r0.length() + 0, 33);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), r0.length() + 0, r0.length(), 33);
                this.i.setText(this.d);
            } else if (Type.TYPE_AT_INFO_TOTAL.equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                r0 = "[赞]";
                r2 = new SpannableString(r0);
                r1 = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
                r1.setBounds(0, 0, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
                r3 = new ImageSpan(r1);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
                r2.setSpan(r3, 0, r0.length() + 0, 33);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), r0.length() + 0, r0.length(), 33);
                this.i.setText(this.d);
            } else if (Type.TYPE_COMENT_LIKE.equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                r0 = "[赞]";
                r2 = new SpannableString(r0);
                r1 = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
                r1.setBounds(0, 0, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
                r3 = new ImageSpan(r1);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
                r2.setSpan(r3, 0, r0.length() + 0, 33);
                r2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), r0.length() + 0, r0.length(), 33);
                this.i.setText(this.d);
            } else if (Type.TYPE_FORWARD.equals(this.f)) {
                if (TextUtils.isEmpty(remark)) {
                    this.o.setText(this.c.name);
                } else {
                    this.o.setText(remark);
                }
                this.i.setText(this.d);
            }
        }
    }

    private void c() {
        int i = R.drawable.group_info_next;
        if (this.b != null) {
            a();
            if (this.b != null) {
                b();
                this.j.setText(TimeUtil.a(this.a.time));
                if (this.isLike) {
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.liked_night : R.drawable.liked);
                } else if (this.isAtInfo) {
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.at_night : R.drawable.at_day);
                } else if (this.isCommentLike) {
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.liked_night : R.drawable.liked);
                } else if (this.isForwards) {
                    this.h.setImageResource(UIHelper.isNightTheme() ? R.drawable.liked_night : R.drawable.forward);
                }
                Drawable drawable = this.h.getDrawable();
                if (drawable != null) {
                    this.h.setImageDrawable(RoundedDrawable.fromDrawable(drawable));
                }
                CharSequence charSequence;
                if (this.isLike) {
                    a(this.k, 8);
                    a(this.l, 8);
                    a(this.p, 0);
                    a(this.q, 8);
                    if (this.g > 0) {
                        a(this.r, 8);
                        charSequence = this.g + "";
                        if (this.g > 99) {
                            charSequence = "99+";
                        }
                        this.p.setText(charSequence);
                    } else {
                        this.p.setVisibility(8);
                        a(this.r, 0);
                        this.r.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
                    }
                    this.h.setOnClickListener(null);
                    setOnClickListener(new ea(this));
                } else if (this.isAtInfo) {
                    a(this.k, 8);
                    a(this.l, 8);
                    a(this.p, 0);
                    if (this.g > 0) {
                        a(this.r, 8);
                        charSequence = this.g + "";
                        if (this.g > 99) {
                            charSequence = "99+";
                        }
                        this.p.setText(charSequence);
                    } else {
                        this.p.setVisibility(8);
                        a(this.r, 0);
                        r0 = this.r;
                        if (UIHelper.isNightTheme()) {
                            i = R.drawable.group_info_next_night;
                        }
                        r0.setImageResource(i);
                    }
                    this.h.setOnClickListener(null);
                    setOnClickListener(new eb(this));
                } else if (this.isCommentLike) {
                    a(this.k, 8);
                    a(this.l, 8);
                    a(this.p, 0);
                    if (this.g > 0) {
                        a(this.r, 8);
                        charSequence = this.g + "";
                        if (this.g > 99) {
                            charSequence = "99+";
                        }
                        this.p.setText(charSequence);
                    } else {
                        this.p.setVisibility(8);
                        a(this.r, 0);
                        r0 = this.r;
                        if (UIHelper.isNightTheme()) {
                            i = R.drawable.group_info_next_night;
                        }
                        r0.setImageResource(i);
                    }
                    this.h.setOnClickListener(null);
                    setOnClickListener(new ec(this));
                } else if (this.isForwards) {
                    a(this.k, 8);
                    a(this.l, 8);
                    a(this.p, 0);
                    if (this.g > 0) {
                        a(this.r, 8);
                        charSequence = this.g + "";
                        if (this.g > 99) {
                            charSequence = "99+";
                        }
                        this.p.setText(charSequence);
                    } else {
                        this.p.setVisibility(8);
                        a(this.r, 0);
                        r0 = this.r;
                        if (UIHelper.isNightTheme()) {
                            i = R.drawable.group_info_next_night;
                        }
                        r0.setImageResource(i);
                    }
                    this.h.setOnClickListener(null);
                    setOnClickListener(new ed(this));
                } else if (!this.isLike && !this.isAtInfo && !this.isCommentLike && !this.isForwards) {
                    e();
                    if (Type.TYPE_COMENT_LIKE.equals(this.f)) {
                        a(this.k, 0);
                        a(this.l, 8);
                        a(this.p, 8);
                        this.l.setImageDrawable(null);
                        this.k.setText(this.b.content);
                    } else if (!this.b.hasImage()) {
                        a(this.k, 0);
                        a(this.l, 8);
                        a(this.p, 8);
                        this.l.setImageDrawable(null);
                        this.k.setText(this.b.content);
                    } else if (this.b.isVideoArticle()) {
                        this.q.setImageResource(R.drawable.im_qiushi_push_play);
                        a(this.q, 0);
                        a(this.k, 8);
                        a(this.l, 0);
                        a(this.p, 8);
                        a(this.l, ((PicUrl) this.b.picUrls.get(0)).url);
                    } else {
                        a(this.k, 8);
                        a(this.l, 0);
                        a(this.p, 8);
                        a(this.l, ((PicUrl) this.b.picUrls.get(0)).url);
                    }
                    a(this.r, 8);
                    this.h.setOnClickListener(new UserClickDelegate(this.c.id, this.c.name, this.c.icon, new UserInfoOnClickListener(this.c.id, this.c.name, this.c.icon)));
                    setOnClickListener(new a(this.b, this.n, this.i.getText().toString(), this.c.id, this.c.name, this.f));
                }
            }
        }
    }

    private void d() {
        this.h.setImageDrawable(RoundedDrawable.fromDrawable(this.h.getResources().getDrawable(UIHelper.getDefaultAvatar())));
    }

    private void e() {
        if (this.c == null || TextUtils.isEmpty(this.c.icon)) {
            d();
            return;
        }
        FrescoImageloader.displayAvatar(this.h, QsbkApp.absoluteUrlOfMediumUserIcon(this.c.icon, this.c.id));
    }

    private void a(ImageView imageView, String str) {
        if (imageView != null && str != null) {
            this.l.setImageDrawable(null);
            FrescoImageloader.displayImage(imageView, str, TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE));
        }
    }

    public void setData(ChatMsg chatMsg, List<ChatMsg> list, int i) {
        this.t = i;
        this.s = list;
        if (chatMsg != this.a) {
            this.a = chatMsg;
            LogUtil.e(chatMsg.toString());
            if (this.a != null) {
                try {
                    boolean z;
                    JSONObject jSONObject = new JSONObject(this.a.data);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("jump_data");
                    if (chatMsg.mType == 1) {
                        this.f = Type.TYPE_LIKE;
                    } else if (chatMsg.mType == 2) {
                        this.f = Type.TYPE_COMMENT_AT;
                    } else if (chatMsg.mType == 3) {
                        this.f = "comment";
                    } else if (chatMsg.mType == 101) {
                        this.f = Type.TYPE_AT_INFO_TOTAL;
                    } else if (chatMsg.mType == 105) {
                        this.f = Type.TYPE_COMENT_LIKE;
                    } else if (chatMsg.mType == 106) {
                        this.f = Type.TYPE_FORWARD;
                    }
                    this.e = jSONObject.getString("jump");
                    this.d = jSONObject.optString("d");
                    this.b = (CircleArticle) CircleArticle.parseJson(jSONObject2);
                    this.m = jSONObject2.optString("re_name");
                    jSONObject = jSONObject2.optJSONObject("from");
                    this.n = jSONObject2.optString("comment_id");
                    if (jSONObject != null) {
                        this.c = new UserInfo(jSONObject);
                    } else {
                        this.c = new UserInfo(this.a.from, this.a.getFromNick(), this.a.getFromIcon());
                    }
                    if (chatMsg.showType == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isLike = z;
                    if (chatMsg.showType == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isAtInfo = z;
                    if (chatMsg.showType == 6) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isCommentLike = z;
                    if (chatMsg.showType == 7) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isForwards = z;
                    this.g = chatMsg.totalLikeNum;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            c();
        }
    }
}
