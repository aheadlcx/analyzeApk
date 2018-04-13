package qsbk.app.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.List;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.SingleArticleLevel;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.ChatMsg;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.ArticleImage;
import qsbk.app.model.RssArticle;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

public class QiushiNotificationItemView extends RelativeLayout {
    private ChatMsg a;
    private Article b;
    private UserInfo c;
    private UserInfo d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i = "";
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private ImageView n;
    private ImageView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private ImageView s;
    private int t = 0;
    private int u;
    private List<ChatMsg> v;
    private int w;

    public interface Jump {
        public static final String JUMP_ARTICLE = "article";
        public static final String JUMP_COMMENT = "comment";
    }

    protected static class TimeUtil {
        protected TimeUtil() {
        }

        static String a(long j) {
            long abs = Math.abs(System.currentTimeMillis() - j) / 1000;
            String str = "";
            if (abs <= 10) {
                return "刚刚";
            }
            if (abs < 60) {
                return abs + "秒前";
            }
            if (abs < 3600) {
                return (abs / 60) + "分钟前";
            }
            if (abs < 86400) {
                return (abs / 3600) + "小时前";
            }
            if (abs < 2592000) {
                return (abs / 86400) + "天前";
            }
            if (abs < 31536000) {
                return (abs / 2592000) + "个月前";
            }
            return (abs / 31536000) + "年前";
        }
    }

    public static class UserInfo {
        public String icon;
        public String id;
        public String name;

        public UserInfo(JSONObject jSONObject) {
            this.name = jSONObject.optString(QsbkDatabase.LOGIN);
            this.icon = jSONObject.optString("icon");
            this.id = jSONObject.optString("id");
        }

        public UserInfo(String str, String str2, String str3) {
            this.id = str;
            this.name = str2;
            this.icon = str3;
        }
    }

    private static class a implements OnClickListener {
        private Article a;
        private int b;
        private UserInfo c;
        private String d;
        private String e;

        a(Article article, int i, String str) {
            this.a = article;
            this.b = i;
            this.e = str;
        }

        a(Article article, int i, UserInfo userInfo, String str, String str2) {
            this(article, i, str2);
            this.c = userInfo;
            this.d = str;
        }

        public void onClick(View view) {
            if (this.a != null) {
                Context context = view.getContext();
                Intent intent = new Intent(context, this.b > 0 ? SingleArticleLevel.class : SingleArticle.class);
                try {
                    intent.putExtra("FROM_MSG", true);
                    intent.putExtra("ARTICLEJSON", this.a.toJSONObject().toString());
                    if (!TextUtils.isEmpty(this.e) && "promote".equals(this.e)) {
                        intent.putExtra("_FROM_PROMOTE_", true);
                    }
                    if (TextUtils.equals(Type.QIUSHI_COMMENT, this.e) || TextUtils.equals(Type.QIUSHI_COMMENT_AT, this.e)) {
                        if (this.c != null) {
                            intent.putExtra(SingleArticleLevel.COMMENT_UID, this.c.id);
                            intent.putExtra(SingleArticleLevel.COMMENT_USER_NAME, this.c.name);
                        }
                        if (!TextUtils.isEmpty(this.d)) {
                            intent.putExtra(SingleArticleLevel.COMMENT_CONTENT, this.d);
                        }
                    }
                    intent.putExtra(SingleArticleLevel.COMMENT_FLOOR, this.b);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public QiushiNotificationItemView(Context context) {
        super(context);
    }

    public QiushiNotificationItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QiushiNotificationItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        if (this.k == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.qiushi_notification_item, this, true);
            setBackgroundColor(UIHelper.isNightTheme() ? Color.parseColor("#000000") : Color.parseColor("#ffffff"));
            this.k = (TextView) findViewById(R.id.description);
            this.l = (TextView) findViewById(R.id.time);
            this.n = (ImageView) findViewById(R.id.thumb_image);
            this.o = (ImageView) findViewById(R.id.play);
            this.m = (TextView) findViewById(R.id.thumb_text);
            this.m.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.qiushi_notification_thumb_text_bg_night : R.drawable.qiushi_notification_thumb_text_bg);
            this.j = (ImageView) findViewById(R.id.icon);
            this.p = (TextView) findViewById(R.id.name);
            this.q = (TextView) findViewById(R.id.like_num_textview);
            this.r = (ImageView) findViewById(R.id.like_image);
            this.s = (ImageView) findViewById(R.id.gif_tag);
        }
    }

    private void a(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void b() {
        CharSequence remark = RemarkManager.getRemark(this.c.id);
        String str;
        SpannableString spannableString;
        Drawable drawable;
        ImageSpan imageSpan;
        if (this.t == 2) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name + "等");
            } else {
                this.p.setText(remark + "等");
            }
            str = "[笑脸]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.qiushi_like);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText(this.f);
        } else if (this.t == 3) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name + "等");
            } else {
                this.p.setText(remark + "等");
            }
            str = "[赞]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText(this.f);
        } else if (this.t == 5) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name + "等");
            } else {
                this.p.setText(remark + "等");
            }
            str = "[赞]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText("@了你");
        } else if (TextUtils.equals(Type.QIUSHI_SMILE, this.h)) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name);
            } else {
                this.p.setText(remark);
            }
            str = "[笑脸]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.qiushi_like);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText(this.f);
        } else if (TextUtils.equals(Type.QIUSHI_COMMENT_LIKE, this.h)) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name);
            } else {
                this.p.setText(remark);
            }
            str = "[赞]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.qiuyoucircle_like_on);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText(this.f);
        } else if (TextUtils.equals(Type.QIUSHI_COMMENT_AT, this.h)) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name);
            } else {
                this.p.setText(remark);
            }
            this.k.setText(this.f);
        } else if (TextUtils.equals(Type.QIUSHI_AT_USERS, this.h)) {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name);
            } else {
                this.p.setText(remark);
            }
            str = "[@]";
            spannableString = new SpannableString(str);
            drawable = getResources().getDrawable(R.drawable.at_day);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3d3d42")), 0, 0, 33);
            spannableString.setSpan(imageSpan, 0, str.length() + 0, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), str.length() + 0, str.length(), 33);
            this.k.setText(this.f);
        } else {
            if (TextUtils.isEmpty(remark)) {
                this.p.setText(this.c.name);
            } else {
                this.p.setText(remark);
            }
            this.k.setText(this.f);
        }
    }

    private void c() {
        if (this.b != null) {
            a();
            if (this.b != null) {
                b();
                this.l.setText(TimeUtil.a(this.a.time));
                this.s.setVisibility(8);
                if (this.t == 2 || this.t == 3 || this.t == 5) {
                    if (this.t == 2) {
                        this.j.setImageResource(UIHelper.isNightTheme() ? R.drawable.qiushi_smile_head_night : R.drawable.qiushi_smile_head);
                    } else if (this.t == 3) {
                        this.j.setImageResource(UIHelper.isNightTheme() ? R.drawable.liked_night : R.drawable.liked);
                    } else if (this.t == 5) {
                        this.j.setImageResource(UIHelper.isNightTheme() ? R.drawable.at_night : R.drawable.at_day);
                    }
                    Drawable drawable = this.j.getDrawable();
                    if (drawable != null) {
                        this.j.setImageDrawable(RoundedDrawable.fromDrawable(drawable));
                    }
                    a(this.m, 8);
                    a(this.n, 8);
                    a(this.q, 0);
                    if (this.u > 0) {
                        a(this.r, 8);
                        CharSequence charSequence = this.u + "";
                        if (this.u > 99) {
                            charSequence = "99+";
                        }
                        this.q.setText(charSequence);
                    } else {
                        a(this.q, 8);
                        a(this.r, 0);
                        this.r.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
                    }
                    this.j.setOnClickListener(null);
                    setOnClickListener(new dp(this));
                    a(this.o, 8);
                    a(this.m, 8);
                    a(this.n, 8);
                    return;
                }
                a(this.q, 8);
                a(this.r, 8);
                e();
                CharSequence spannableStringBuilder;
                QiushiTopicImageSpan qiushiTopicImageSpan;
                Object obj;
                if (TextUtils.isEmpty(this.h) || !TextUtils.equals(Type.QIUSHI_COMMENT_LIKE, this.h) || TextUtils.isEmpty(this.i)) {
                    if (this.b.isGIFArticle()) {
                        a(this.o, 8);
                        a(this.s, 0);
                        a(this.m, 8);
                        a(this.n, 0);
                    } else if (this.b.isVideoArticle()) {
                        this.o.setImageResource(R.drawable.im_qiushi_push_play);
                        a(this.o, 0);
                        a(this.m, 8);
                        a(this.n, 0);
                    } else if (this.b.isWordsOnly()) {
                        this.o.setImageDrawable(null);
                        a(this.o, 8);
                        a(this.m, 0);
                        a(this.n, 8);
                        this.n.setImageDrawable(null);
                        if (this.b.qiushiTopic != null) {
                            spannableStringBuilder = new SpannableStringBuilder();
                            spannableStringBuilder.append("搜");
                            qiushiTopicImageSpan = new QiushiTopicImageSpan(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                            qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(getContext(), 5.0f));
                            qiushiTopicImageSpan.setmPaint(this.m.getPaint());
                            spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                            obj = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.b.qiushiTopic.content;
                            spannableStringBuilder.append(obj);
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, obj.length() + 1, 33);
                            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.b.content);
                            this.m.setText(spannableStringBuilder);
                            if ("article".equals(this.g)) {
                                if (TextUtils.isEmpty(this.h) || !"promote".equals(this.h)) {
                                    this.m.setOnClickListener(new a(this.b, 0, this.h));
                                } else {
                                    this.m.setOnClickListener(new a(this.b, 0, this.h));
                                }
                            } else if ("comment".equals(this.g)) {
                                this.m.setOnClickListener(new a(this.b, this.e, this.c, this.f, this.h));
                            } else {
                                this.m.setOnClickListener(null);
                            }
                            this.m.setMovementMethod(LinkMovementMethod.getInstance());
                        } else {
                            this.m.setText(this.b.content);
                        }
                    } else {
                        this.o.setImageDrawable(null);
                        a(this.o, 8);
                        a(this.m, 8);
                        a(this.n, 0);
                    }
                    if (!this.b.isWordsOnly()) {
                        if (this.b.imageInfos.size() > 0) {
                            a(this.n, ((ArticleImage) this.b.imageInfos.get(0)).getImageUrl());
                        } else {
                            a(this.n, QsbkApp.absoluteUrlOfSmallContentImage(this.b.id, this.b.image));
                        }
                    }
                } else {
                    this.o.setImageDrawable(null);
                    a(this.o, 8);
                    a(this.m, 0);
                    a(this.n, 8);
                    this.n.setImageDrawable(null);
                    if (this.b.qiushiTopic != null) {
                        spannableStringBuilder = new SpannableStringBuilder();
                        spannableStringBuilder.append("搜");
                        qiushiTopicImageSpan = new QiushiTopicImageSpan(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                        qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(getContext(), 5.0f));
                        qiushiTopicImageSpan.setmPaint(this.m.getPaint());
                        spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                        obj = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.b.qiushiTopic.content;
                        spannableStringBuilder.append(obj);
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, obj.length() + 1, 33);
                        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.i);
                        this.m.setText(spannableStringBuilder);
                        this.m.setMovementMethod(LinkMovementMethod.getInstance());
                        if ("article".equals(this.g)) {
                            if (TextUtils.isEmpty(this.h) || !"promote".equals(this.h)) {
                                this.m.setOnClickListener(new a(this.b, 0, this.h));
                            } else {
                                this.m.setOnClickListener(new a(this.b, 0, this.h));
                            }
                        } else if ("comment".equals(this.g)) {
                            this.m.setOnClickListener(new a(this.b, this.e, this.c, this.f, this.h));
                        } else {
                            this.m.setOnClickListener(null);
                        }
                    } else {
                        this.m.setText(this.i);
                    }
                }
                if ("article".equals(this.g)) {
                    if (TextUtils.isEmpty(this.h) || !"promote".equals(this.h)) {
                        setOnClickListener(new a(this.b, 0, this.h));
                    } else {
                        setOnClickListener(new a(this.b, 0, this.h));
                    }
                } else if ("comment".equals(this.g)) {
                    setOnClickListener(new a(this.b, this.e, this.c, this.f, this.h));
                } else {
                    setOnClickListener(null);
                }
                this.j.setOnClickListener(new UserClickDelegate(this.c.id, this.c.name, this.c.icon, new UserInfoOnClickListener(this.c.id, this.c.name, this.c.icon)));
            }
        }
    }

    private void d() {
        this.j.setImageDrawable(RoundedDrawable.fromDrawable(this.j.getResources().getDrawable(UIHelper.getDefaultAvatar())));
    }

    private void e() {
        if (this.c == null || TextUtils.isEmpty(this.c.icon)) {
            d();
            return;
        }
        Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(this.c.icon, this.c.id);
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            d();
        } else {
            FrescoImageloader.displayAvatar(this.j, absoluteUrlOfMediumUserIcon);
        }
    }

    private void a(ImageView imageView, String str) {
        if (imageView != null && str != null) {
            this.n.setImageDrawable(null);
            FrescoImageloader.displayImage(imageView, str, TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE));
        }
    }

    public void setData(ChatMsg chatMsg) {
        if (chatMsg != this.a) {
            this.a = chatMsg;
            if (this.a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.a.data);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("jump_data");
                    this.h = jSONObject2.optString("m_type");
                    this.g = jSONObject.getString("jump");
                    this.f = jSONObject.optString("d");
                    if ("article".equals(this.g) || "comment".equals(this.g)) {
                        Log.e(QiushiNotificationItemView.class.getSimpleName(), "article " + jSONObject2);
                        this.b = new RssArticle(jSONObject2);
                        this.e = jSONObject2.optInt("jump_to_level");
                    }
                    jSONObject = jSONObject2.optJSONObject("from");
                    if (jSONObject != null) {
                        this.c = new UserInfo(jSONObject);
                    } else {
                        this.c = new UserInfo(this.a.from, this.a.getFromNick(), this.a.getFromIcon());
                    }
                    jSONObject = jSONObject2.optJSONObject("user");
                    if (jSONObject != null) {
                        this.d = new UserInfo(jSONObject);
                    } else {
                        this.d = new UserInfo(this.a.from, this.a.getFromNick(), this.a.getFromIcon());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            c();
        }
    }

    public void setData(ChatMsg chatMsg, List<ChatMsg> list, int i) {
        this.w = i;
        this.v = list;
        if (chatMsg != this.a) {
            this.a = chatMsg;
            if (this.a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.a.data);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("jump_data");
                    this.h = jSONObject2.optString("m_type");
                    this.g = jSONObject.getString("jump");
                    this.f = jSONObject.optString("d");
                    this.i = jSONObject2.optString("comment_content");
                    if ("article".equals(this.g) || "comment".equals(this.g)) {
                        this.b = new RssArticle(jSONObject2);
                        this.e = jSONObject2.optInt("jump_to_level");
                    }
                    jSONObject = jSONObject2.optJSONObject("from");
                    if (jSONObject == null || TextUtils.isEmpty(jSONObject.toString()) || jSONObject.toString().equals("{}")) {
                        this.c = new UserInfo(this.a.from, this.a.getFromNick(), this.a.getFromIcon());
                    } else {
                        this.c = new UserInfo(jSONObject);
                    }
                    jSONObject = jSONObject2.optJSONObject("user");
                    if (jSONObject != null) {
                        this.d = new UserInfo(jSONObject);
                    } else {
                        this.d = new UserInfo(this.a.from, this.a.getFromNick(), this.a.getFromIcon());
                    }
                    this.t = chatMsg.showType;
                    this.u = chatMsg.totalLikeNum;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            c();
        }
    }
}
