package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.TimeUtils;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.Comment;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserInfoClickListener;
import qsbk.app.widget.RoundedDrawable;

public class SingleArticleAdapter extends DefaultAdapter<Object> {
    public static final int TYPE_COMMENT = 0;
    public static final int TYPE_SECTION = 1;
    private static final String i = SingleArticleAdapter.class.getSimpleName();
    protected ArrayList<Comment> a;
    protected ArrayList<Comment> b;
    protected ArrayList<Comment> c;
    protected Map<String, Boolean> d = new HashMap();
    protected int e;
    protected OnTabSelectListener f;
    protected int g;
    protected int h;
    public ArrayList<Comment> hotComments;
    public int hotSectionPosition;
    private String j;
    private Article o;
    private int p = UIHelper.getNameColorForComment(0);
    private OnMoreClickListener q;

    public interface OnMoreClickListener {
        void onMoreClick(View view, Comment comment, int i);
    }

    public interface OnTabSelectListener {
        void onTabChange(boolean z);
    }

    private static class a {
        int a;
        TextView b;
        TextView c;
        View d;

        public a(View view) {
            this.b = (TextView) view.findViewById(R.id.title);
            this.c = (TextView) view.findViewById(R.id.sub_title);
            this.d = view.findViewById(R.id.divider);
        }
    }

    private static class b {
        int a;
        TextView b;
        TextView c;
        ImageView d;
        View e;
        TextView f;
        TextView g;
        TextView h;
        View i;
        View j;
        TextView k;
        TextView l;
        TextView m;
        boolean n;
        View o;
        ProgressBar p;
        ImageView q;
        private final Runnable r = new dm(this);

        public b(View view) {
            this.b = (TextView) view.findViewById(R.id.userName);
            this.c = (TextView) view.findViewById(R.id.content);
            this.c.setTransformationMethod(new MobileTransformationMethod());
            this.d = (ImageView) view.findViewById(R.id.userIcon);
            this.e = view.findViewById(R.id.owner);
            this.f = (TextView) view.findViewById(R.id.like_count);
            this.g = (TextView) view.findViewById(R.id.time);
            this.h = (TextView) view.findViewById(R.id.floor);
            this.o = view.findViewById(R.id.img_container);
            this.q = (ImageView) view.findViewById(R.id.img);
            this.p = (ProgressBar) view.findViewById(R.id.progress);
            this.i = view.findViewById(R.id.divider);
            this.j = view.findViewById(R.id.reply_layout);
            if (UIHelper.isNightTheme()) {
                this.j.setBackgroundColor(Color.parseColor("#17191b"));
            }
            this.k = (TextView) view.findViewById(R.id.reply_userName);
            this.l = (TextView) view.findViewById(R.id.reply_content);
            this.m = (TextView) view.findViewById(R.id.reply_more);
            this.m.setOnClickListener(new dn(this));
        }

        public static b getViewHolder(View view) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof b)) {
                return (b) tag;
            }
            b bVar = new b(view);
            view.setTag(bVar);
            return bVar;
        }

        private void a() {
            if (this.n) {
                this.m.setText("收起");
                return;
            }
            this.m.setText("展开更多");
            this.l.setMaxLines(4);
            b();
        }

        private void b() {
            this.m.removeCallbacks(this.r);
            this.r.run();
            this.m.post(this.r);
        }
    }

    public SingleArticleAdapter(ArrayList<Comment> arrayList, ArrayList<Comment> arrayList2, ArrayList<Comment> arrayList3, Activity activity, String str) {
        super(null, activity);
        this.j = str;
        this.hotComments = arrayList;
        this.a = arrayList2;
        this.b = arrayList3;
        this.c = arrayList2;
    }

    public static boolean shouldSetTagWhenSetClickSpanForTextView() {
        LogUtil.e("sdkint;" + VERSION.SDK_INT);
        return VERSION.SDK_INT < 19;
    }

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.f = onTabSelectListener;
    }

    public boolean isNormalPage() {
        return this.a == this.c;
    }

    public int getCount() {
        int i;
        int i2 = 0;
        int size = this.hotComments == null ? 0 : this.hotComments.size();
        if (size > 0) {
            i = 0;
        } else {
            i = -1;
        }
        this.hotSectionPosition = i;
        if ((this.a == null ? 0 : this.a.size()) + size == 0) {
            this.c = this.a;
            this.e = -1;
            return 0;
        }
        if (this.c != null) {
            i2 = this.c.size();
        }
        this.e = (this.hotSectionPosition + size) + 1;
        return i2 + (this.e + 1);
    }

    public void setAllCount(int i) {
        this.g = i;
    }

    public void setOwnerCount(int i) {
        this.h = i;
    }

    public void addAllCount(int i) {
        this.g += i;
    }

    public void addOwnerCount(int i) {
        this.h += i;
    }

    public Comment getItem(int i) {
        if (i > this.e) {
            return (Comment) this.c.get((i - this.e) - 1);
        }
        if (i <= this.hotSectionPosition || i >= this.e) {
            return null;
        }
        return (Comment) this.hotComments.get((i - this.hotSectionPosition) - 1);
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        return (i == this.hotSectionPosition || i == this.e) ? 1 : 0;
    }

    public void setArticleId(String str) {
        this.j = str;
    }

    public void setArticle(Article article) {
        this.o = article;
        if (article != null) {
            this.j = article.id;
        }
    }

    private void a(TextView textView, Comment comment, String str) {
        DebugUtil.debug(i, comment.userName + "" + comment.role);
        CharSequence charSequence = comment.userName;
        CharSequence remark = RemarkManager.getRemark(comment.uid);
        if (TextUtils.isEmpty(remark)) {
            textView.setText(charSequence);
        } else {
            textView.setText(remark);
        }
        textView.setTypeface(Typeface.defaultFromStyle(0));
        textView.setTextColor(this.p);
        if ("63443".equalsIgnoreCase(comment.uid)) {
            textView.setOnClickListener(null);
        } else {
            textView.setOnClickListener(new UserInfoClickListener(comment.uid, comment.userName, comment.icon, str));
        }
    }

    private void a(View view, Comment comment) {
        CharSequence charSequence = comment.uid;
        CharSequence charSequence2 = this.o != null ? this.o.user_id : null;
        if (TextUtils.isEmpty(charSequence2)) {
            charSequence2 = "63443";
        }
        if (this.o == null || !TextUtils.equals(charSequence, r0)) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }

    private void a(ImageView imageView, Comment comment, String str) {
        String str2 = comment.userName;
        if (TextUtils.isEmpty(str2) || "null".equalsIgnoreCase(str2)) {
            imageView.setOnClickListener(null);
        } else if (QsbkApp.currentUser != null && str2.equalsIgnoreCase(QsbkApp.currentUser.userName)) {
            imageView.setOnClickListener(null);
        } else if ("63443".equalsIgnoreCase(comment.uid)) {
            imageView.setOnClickListener(null);
        } else {
            imageView.setOnClickListener(new UserInfoClickListener(comment.uid, comment.userName, comment.icon, str));
        }
    }

    private void a(String str, String str2, ImageView imageView) {
        String absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(str2, str);
        if ("63443".equals(str)) {
            imageView.setImageDrawable(RoundedDrawable.fromDrawable(imageView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
        } else {
            FrescoImageloader.displayAvatar(imageView, absoluteUrlOfMediumUserIcon);
        }
    }

    private void a(TextView textView, Comment comment) {
        Object obj;
        String str = comment.content;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.startsWith("回复 ")) {
            obj = str;
            int i = 3;
            while (i < obj.length()) {
                char charAt = obj.charAt(i);
                if (charAt < '0' || charAt > '9') {
                    if (i <= 3 || charAt != '楼' || i + 1 >= obj.length() || obj.charAt(i + 1) != '：') {
                        break;
                    }
                    obj = obj.substring(i + 2);
                }
                i++;
            }
        } else {
            obj = str;
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
        if (comment.atInfoTexts == null || comment.atInfoTexts.size() <= 0) {
            textView.setText(obj + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            textView.setMovementMethod(null);
            return;
        }
        for (int i2 = 0; i2 < comment.atInfoTexts.size(); i2++) {
            Matcher matcher;
            AtInfo atInfo = (AtInfo) comment.atInfoTexts.get(i2);
            try {
                matcher = Pattern.compile("@" + atInfo.name).matcher(obj);
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
                matcher = null;
            }
            if (matcher != null) {
                while (matcher.find()) {
                    int start = matcher.start(0);
                    int end = matcher.end(0);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                    if (!(this.k instanceof CircleTopicActivity)) {
                        spannableStringBuilder.setSpan(new df(this, atInfo, comment), start, end, 33);
                    }
                }
            }
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setMoreClickListener(OnMoreClickListener onMoreClickListener) {
        this.q = onMoreClickListener;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 1) {
            a aVar;
            if (view == null) {
                view = this.n.inflate(R.layout.listitem_comment_section, viewGroup, false);
                aVar = new a(view);
                aVar.b.setOnClickListener(new dg(this));
                aVar.c.setOnClickListener(new dh(this));
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (i == this.hotSectionPosition) {
                aVar.c.setVisibility(4);
                aVar.b.setText(String.format("热门评论 %d", new Object[]{Integer.valueOf(this.hotComments.size())}));
                aVar.b.setEnabled(false);
                aVar.d.setVisibility(4);
            } else {
                if (this.hotSectionPosition == -1) {
                    aVar.d.setVisibility(4);
                } else {
                    aVar.d.setVisibility(0);
                }
                aVar.b.setText(String.format("最新评论 %d", new Object[]{Integer.valueOf(this.g)}));
                aVar.b.setEnabled(true);
                if (this.h != 0) {
                    aVar.c.setText(String.format("楼主 %d", new Object[]{Integer.valueOf(this.h)}));
                    aVar.c.setVisibility(0);
                } else {
                    aVar.c.setVisibility(4);
                }
                if (this.c == this.a) {
                    aVar.b.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_color));
                    aVar.c.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_highlight));
                } else {
                    aVar.c.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_color));
                    aVar.b.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_highlight));
                }
            }
            if (!UIHelper.isNightTheme() && this.hotComments.size() > 0) {
                if (i <= this.hotComments.size()) {
                    view.setBackgroundColor(UIHelper.getColor(R.color.white));
                } else {
                    view.setBackgroundColor(UIHelper.getColor(R.color.single_article_cmt_item_bg));
                }
            }
        } else {
            if (view == null) {
                view = this.n.inflate(R.layout.listitem_comment_row, null);
            }
            if (!(view == null || UIHelper.isNightTheme() || this.hotComments.size() <= 0)) {
                if (i <= this.hotComments.size()) {
                    view.setBackgroundColor(UIHelper.getColor(R.color.white));
                } else {
                    view.setBackgroundColor(UIHelper.getColor(R.color.single_article_cmt_item_bg));
                }
            }
            b viewHolder = b.getViewHolder(view);
            a(viewHolder, getItem(i), i);
            if (i - 1 == this.hotSectionPosition || i - 1 == this.e) {
                viewHolder.i.setVisibility(4);
            } else {
                viewHolder.i.setVisibility(0);
            }
        }
        return view;
    }

    private void a(b bVar, Comment comment, int i) {
        CharSequence spannableStringBuilder;
        boolean z;
        a(bVar.c, comment);
        a(bVar.b, comment, this.j);
        a(bVar.e, comment);
        a(bVar.d, comment, this.j);
        a(comment.uid, comment.icon, bVar.d);
        if (comment.reply != null) {
            CharSequence charSequence;
            bVar.j.setVisibility(0);
            bVar.k.setText(comment.reply.userName);
            bVar.k.setTextColor(this.p);
            String str = comment.reply.content;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if (str.startsWith("回复 ")) {
                charSequence = str;
                int i2 = 3;
                while (i2 < charSequence.length()) {
                    char charAt = charSequence.charAt(i2);
                    if (charAt < '0' || charAt > '9') {
                        if (i2 <= 3 || charAt != '楼' || i2 + 1 >= charSequence.length() || charSequence.charAt(i2 + 1) != '：') {
                            break;
                        }
                        charSequence = charSequence.substring(i2 + 2);
                    }
                    i2++;
                }
            } else {
                charSequence = str;
            }
            if (comment.reply.smallImage != null) {
                spannableStringBuilder = new SpannableStringBuilder(charSequence);
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append("[图片]");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.k.getResources().getColor(R.color.blue)), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new di(this, comment), length, spannableStringBuilder.length(), 33);
                bVar.l.setText(spannableStringBuilder);
                bVar.l.setMovementMethod(LinkMovementMethod.getInstance());
                bVar.l.setHighlightColor(0);
            } else {
                bVar.l.setText(charSequence);
            }
            if (i != bVar.a) {
                bVar.n = false;
            }
            bVar.a();
        } else {
            bVar.j.setVisibility(8);
        }
        bVar.a = i;
        bVar.g.setText(TimeUtils.getLastLoginStr(((long) comment.createAt) * 1000));
        if (comment.floor > 0) {
            bVar.h.setVisibility(0);
            bVar.h.setText(String.format("%d楼", new Object[]{Integer.valueOf(comment.floor)}));
        } else {
            bVar.h.setVisibility(8);
        }
        if (comment.liked) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (this.d.containsKey(comment.id)) {
                z = false;
            } else {
                z = true;
            }
        }
        bVar.f.setEnabled(z);
        if (comment.liked && comment.likeCount == 0) {
            comment.likeCount = 1;
        }
        TextView textView = bVar.f;
        if (comment.likeCount == 0) {
            spannableStringBuilder = "";
        } else {
            spannableStringBuilder = String.valueOf(comment.likeCount);
        }
        textView.setText(spannableStringBuilder);
        bVar.f.setOnClickListener(new dj(this, comment));
        if (comment.smallImage != null) {
            bVar.o.setVisibility(0);
            bVar.q.setVisibility(0);
            LayoutParams layoutParams = bVar.q.getLayoutParams();
            length = UIHelper.dip2px(this.k, 180.0f);
            int dip2px = UIHelper.dip2px(this.k, 100.0f);
            if (layoutParams == null) {
                layoutParams = new LayoutParams(0, 0);
            }
            layoutParams.height = comment.smallImage.height;
            layoutParams.width = comment.smallImage.width;
            if (comment.smallImage.isVertical()) {
                if (comment.smallImage.height > length) {
                    layoutParams.height = length;
                    layoutParams.width = (int) (((float) length) * comment.smallImage.getRatio());
                }
            } else if (comment.smallImage.width > dip2px) {
                layoutParams.width = dip2px;
                layoutParams.height = (int) (((float) dip2px) / comment.smallImage.getRatio());
            }
            bVar.q.setLayoutParams(layoutParams);
            FrescoImageloader.displayImage(bVar.q, comment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
            if ("-1".equals(comment.id)) {
                bVar.p.setVisibility(0);
            } else {
                bVar.p.setVisibility(8);
            }
            bVar.q.setOnClickListener(new LoginPermissionClickDelegate(new dl(this, comment, bVar), null));
            return;
        }
        bVar.o.setVisibility(8);
        bVar.q.setOnClickListener(null);
        bVar.q.setVisibility(8);
        bVar.p.setVisibility(8);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.hotComments != null) {
            this.hotComments.clear();
        }
        if (this.a != null) {
            this.a.clear();
        }
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
        if (this.d != null) {
            this.d.clear();
        }
    }
}
