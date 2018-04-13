package qsbk.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
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
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.TimeUtils;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.RoundedDrawable;

public class CircleCommentAdapter extends DefaultAdapter<Object> {
    public static final int TYPE_COMMENT = 0;
    public static final int TYPE_SECTION = 1;
    private static final String o = CircleCommentAdapter.class.getSimpleName();
    protected ArrayList<CircleComment> a;
    protected ArrayList<CircleComment> b;
    protected ArrayList<CircleComment> c;
    protected ArrayList<CircleComment> d;
    protected Map<String, Boolean> e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected OnTabSelectListener j;
    private CircleArticle p;
    private String q;
    private ColorStateList r;

    public interface OnTabSelectListener {
        void onCommentTabSelect();
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
        View h;
        View i;
        TextView j;
        TextView k;
        TextView l;
        boolean m;
        View n;
        ProgressBar o;
        ImageView p;
        private final Runnable q = new ar(this);

        public b(View view) {
            this.b = (TextView) view.findViewById(R.id.userName);
            this.c = (TextView) view.findViewById(R.id.content);
            this.c.setTransformationMethod(new MobileTransformationMethod());
            this.d = (ImageView) view.findViewById(R.id.userIcon);
            this.e = view.findViewById(R.id.owner);
            this.f = (TextView) view.findViewById(R.id.like_count);
            this.g = (TextView) view.findViewById(R.id.time);
            this.h = view.findViewById(R.id.divider);
            this.i = view.findViewById(R.id.reply_layout);
            if (UIHelper.isNightTheme()) {
                this.i.setBackgroundColor(Color.parseColor("#17191b"));
            }
            this.j = (TextView) view.findViewById(R.id.reply_userName);
            this.k = (TextView) view.findViewById(R.id.reply_content);
            this.l = (TextView) view.findViewById(R.id.reply_more);
            this.n = view.findViewById(R.id.img_container);
            this.p = (ImageView) view.findViewById(R.id.img);
            this.o = (ProgressBar) view.findViewById(R.id.progress);
            this.l.setOnClickListener(new as(this));
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
            if (this.m) {
                this.l.setText("收起");
                return;
            }
            this.l.setText("展开更多");
            this.k.setMaxLines(4);
            this.k.setMinHeight(0);
            b();
        }

        private void b() {
            this.l.removeCallbacks(this.q);
            this.q.run();
            this.l.post(this.q);
        }
    }

    public CircleCommentAdapter(ArrayList<CircleComment> arrayList, ArrayList<CircleComment> arrayList2, ArrayList<CircleComment> arrayList3, Activity activity, String str, ListView listView) {
        super(null, activity);
        this.a = arrayList;
        this.b = arrayList2;
        this.c = arrayList3;
        this.d = arrayList2;
        this.q = str;
        this.r = activity.getResources().getColorStateList(UIHelper.isNightTheme() ? R.color.cmt_user_name_dark : R.color.cmt_user_name);
        this.l = listView;
        this.e = new HashMap();
    }

    public boolean isNormalPage() {
        return this.d == this.b;
    }

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.j = onTabSelectListener;
    }

    public int getCount() {
        int i;
        int i2 = 0;
        int size = this.a == null ? 0 : this.a.size();
        if (size > 0) {
            i = 0;
        } else {
            i = -1;
        }
        this.f = i;
        if ((this.b == null ? 0 : this.b.size()) + size == 0) {
            this.d = this.b;
            this.g = -1;
            return 0;
        }
        if (this.d != null) {
            i2 = this.d.size();
        }
        this.g = (this.f + size) + 1;
        return i2 + (this.g + 1);
    }

    public void setAllCount(int i) {
        this.h = i;
    }

    public void setOwnerCount(int i) {
        this.i = i;
    }

    public void addAllCount(int i) {
        this.h += i;
    }

    public void addOwnerCount(int i) {
        this.i += i;
    }

    public CircleComment getItem(int i) {
        if (i > this.g) {
            return (CircleComment) this.d.get((i - this.g) - 1);
        }
        if (i <= this.f || i >= this.g) {
            return null;
        }
        return (CircleComment) this.a.get((i - this.f) - 1);
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        return (i == this.f || i == this.g) ? 1 : 0;
    }

    public void setArticleId(String str) {
        this.q = str;
    }

    public void setArticle(CircleArticle circleArticle) {
        this.p = circleArticle;
        if (circleArticle != null) {
            this.q = circleArticle.id;
        }
    }

    private void a(TextView textView, CircleComment circleComment, String str) {
        DebugUtil.debug(o, circleComment.userName + "" + circleComment.role);
        CharSequence charSequence = circleComment.userName;
        CharSequence remark = RemarkManager.getRemark(circleComment.uid);
        if (TextUtils.isEmpty(remark)) {
            textView.setText(charSequence);
        } else {
            textView.setText(remark);
        }
        textView.setTypeface(Typeface.defaultFromStyle(0));
        textView.setTextColor(UIHelper.getNameColorForComment(circleComment.nickStatus));
    }

    private void a(View view, CircleComment circleComment) {
        CharSequence charSequence = circleComment.uid;
        if (this.p == null || this.p.user == null || !TextUtils.equals(charSequence, this.p.user.userId)) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
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

    private void a(TextView textView, CircleComment circleComment, View view, int i) {
        if (circleComment.atInfoTexts == null || circleComment.atInfoTexts.size() <= 0) {
            textView.setText(circleComment.content + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            textView.setMovementMethod(null);
        } else {
            CharSequence spannableString = new SpannableString(circleComment.content);
            for (int i2 = 0; i2 < circleComment.atInfoTexts.size(); i2++) {
                Matcher matcher;
                AtInfo atInfo = (AtInfo) circleComment.atInfoTexts.get(i2);
                try {
                    matcher = Pattern.compile("@" + atInfo.name).matcher(circleComment.content);
                } catch (PatternSyntaxException e) {
                    e.printStackTrace();
                    matcher = null;
                }
                if (matcher != null) {
                    while (matcher.find()) {
                        int start = matcher.start(0);
                        int end = matcher.end(0);
                        spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                        if (textView.getContext() instanceof CircleArticleActivity) {
                            spannableString.setSpan(new ah(this, atInfo, circleComment), start, end, 33);
                        }
                    }
                }
            }
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        Context context = textView.getContext();
        if (context instanceof CircleArticleActivity) {
            textView.setOnClickListener(new ai(this, context, circleComment));
        }
        if (context instanceof CircleArticleActivity) {
            textView.setOnLongClickListener(new aj(this, view, i));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 1) {
            a aVar;
            if (view == null) {
                view = this.n.inflate(R.layout.listitem_comment_section, viewGroup, false);
                aVar = new a(view);
                aVar.b.setOnClickListener(new ak(this));
                aVar.c.setOnClickListener(new al(this));
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (i == this.f) {
                aVar.c.setVisibility(4);
                aVar.b.setText(String.format("热门评论 %d", new Object[]{Integer.valueOf(this.a.size())}));
                aVar.b.setEnabled(false);
                aVar.d.setVisibility(4);
            } else {
                if (this.f == -1) {
                    aVar.d.setVisibility(4);
                } else {
                    aVar.d.setVisibility(0);
                }
                aVar.b.setText(String.format("最新评论 %d", new Object[]{Integer.valueOf(this.h)}));
                aVar.b.setEnabled(true);
                if (this.i != 0) {
                    aVar.c.setText(String.format("楼主 %d", new Object[]{Integer.valueOf(this.i)}));
                    aVar.c.setVisibility(0);
                } else {
                    aVar.c.setVisibility(4);
                }
                if (this.d == this.b) {
                    aVar.b.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_color));
                    aVar.c.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_highlight));
                } else {
                    aVar.c.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_color));
                    aVar.b.setTextColor(UIHelper.getAttrColor(this.k, R.attr.comment_section_text_highlight));
                }
            }
        } else {
            if (view == null) {
                view = this.n.inflate(R.layout.listitem_comment_row, null);
            }
            b viewHolder = b.getViewHolder(view);
            CircleComment item = getItem(i);
            if (i - 1 == this.f || i - 1 == this.g) {
                viewHolder.h.setVisibility(4);
            } else {
                viewHolder.h.setVisibility(0);
            }
            a(viewHolder, item, i, view);
        }
        return view;
    }

    private void a(b bVar, CircleComment circleComment, int i, View view) {
        CharSequence spannableStringBuilder;
        boolean z;
        a(bVar.c, circleComment, view, i);
        a(bVar.b, circleComment, this.q);
        OnClickListener userClickDelegate = new UserClickDelegate(circleComment.uid, new am(this, circleComment));
        bVar.b.setOnClickListener(userClickDelegate);
        bVar.d.setOnClickListener(userClickDelegate);
        a(bVar.e, circleComment);
        a(circleComment.uid, circleComment.icon, bVar.d);
        if (circleComment.reply != null) {
            bVar.i.setVisibility(0);
            bVar.j.setText(circleComment.reply.userName);
            bVar.j.setTextColor(UIHelper.getNameColorForComment(circleComment.reply.nickStatus));
            if (circleComment.reply.hasImage()) {
                spannableStringBuilder = new SpannableStringBuilder(circleComment.reply.content);
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append("[图片]");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.k.getResources().getColor(R.color.blue)), length, spannableStringBuilder.length(), 33);
                spannableStringBuilder.setSpan(new an(this, circleComment), length, spannableStringBuilder.length(), 33);
                bVar.k.setText(spannableStringBuilder);
                bVar.k.setMovementMethod(LinkMovementMethod.getInstance());
                bVar.k.setHighlightColor(0);
            } else {
                bVar.k.setText(circleComment.reply.content);
            }
            if (i != bVar.a) {
                bVar.m = false;
            }
            bVar.a();
        } else {
            bVar.i.setVisibility(8);
        }
        bVar.a = i;
        bVar.g.setText(TimeUtils.getLastLoginStr(((long) circleComment.createTime) * 1000));
        if (circleComment.liked) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (this.e.containsKey(circleComment.id)) {
                z = false;
            } else {
                z = true;
            }
        }
        bVar.f.setEnabled(z);
        if (circleComment.liked && circleComment.likeCount == 0) {
            circleComment.likeCount = 1;
        }
        TextView textView = bVar.f;
        if (circleComment.likeCount == 0) {
            spannableStringBuilder = "";
        } else {
            spannableStringBuilder = String.valueOf(circleComment.likeCount);
        }
        textView.setText(spannableStringBuilder);
        bVar.f.setOnClickListener(new ao(this, circleComment));
        if (circleComment.smallImage != null) {
            bVar.n.setVisibility(0);
            bVar.p.setVisibility(0);
            LayoutParams layoutParams = bVar.p.getLayoutParams();
            int dip2px = UIHelper.dip2px(this.k, 180.0f);
            length = UIHelper.dip2px(this.k, 100.0f);
            if (layoutParams == null) {
                layoutParams = new LayoutParams(0, 0);
            }
            layoutParams.height = circleComment.smallImage.height;
            layoutParams.width = circleComment.smallImage.width;
            if (circleComment.smallImage.isVertical()) {
                if (circleComment.smallImage.height > dip2px) {
                    layoutParams.height = dip2px;
                    layoutParams.width = (int) (((float) dip2px) * circleComment.smallImage.getRatio());
                }
            } else if (circleComment.smallImage.width > length) {
                layoutParams.width = length;
                layoutParams.height = (int) (((float) length) / circleComment.smallImage.getRatio());
            }
            bVar.p.setLayoutParams(layoutParams);
            FrescoImageloader.displayImage(bVar.p, circleComment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
            if ("-1".equals(circleComment.id)) {
                bVar.o.setVisibility(0);
            } else {
                bVar.o.setVisibility(8);
            }
            bVar.p.setOnClickListener(new LoginPermissionClickDelegate(new aq(this, circleComment, bVar), null));
            return;
        }
        bVar.n.setVisibility(8);
        bVar.p.setOnClickListener(null);
        bVar.p.setVisibility(8);
        bVar.o.setVisibility(8);
    }

    public void onDestroy() {
        super.onDestroy();
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
        if (this.e != null) {
            this.e.clear();
        }
    }
}
