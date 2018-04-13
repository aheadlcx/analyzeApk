package qsbk.app.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import qsbk.app.R;
import qsbk.app.activity.DiggerActivity;
import qsbk.app.model.BaseUser;
import qsbk.app.model.RssArticle;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;

public class DiggerBar extends LinearLayout {
    private static final String a = DiggerBar.class.getSimpleName();
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private List<BaseUser> g;

    private static class a implements OnClickListener {
        private String a;

        a(String str) {
            this.a = str;
        }

        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DiggerActivity.class);
            intent.putExtra("article_id", this.a);
            view.getContext().startActivity(intent);
        }
    }

    public DiggerBar(Context context) {
        super(context);
        a();
    }

    public DiggerBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setOrientation(0);
        setGravity(83);
        Resources resources = getResources();
        this.b = resources.getDimensionPixelSize(R.dimen.subscribe_diggerbar_icon_size);
        this.c = (int) (resources.getDimension(R.dimen.g_txt_small) / resources.getDisplayMetrics().scaledDensity);
        this.d = resources.getColor(R.color.g_txt_small);
        this.e = resources.getColor(R.color.g_txt_smile);
        if (UIHelper.isNightTheme()) {
            setBackgroundColor(getResources().getColor(R.color.night_diggerbar));
            this.d = resources.getColor(R.color.night_diggerbar_txt);
            this.e = resources.getColor(R.color.night_diggerbar_txt);
            return;
        }
        setBackgroundColor(getResources().getColor(R.color.day_diggerbar));
    }

    public void setDiggers(RssArticle rssArticle, boolean z) {
        if (this.f == null) {
            throw new IllegalArgumentException("Article id not set yet.");
        }
        removeAllViews();
        if (rssArticle.actorCount == 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        a(rssArticle, z);
    }

    public void belongTo(String str) {
        this.f = str;
    }

    public String getBelongTo() {
        return this.f;
    }

    private void a(RssArticle rssArticle, boolean z) {
        int i = rssArticle.actorCount;
        List list = rssArticle.actors;
        BaseUser baseUser;
        SpannableString spannableString;
        if (i == 1) {
            baseUser = (BaseUser) list.get(0);
            CharSequence remark = RemarkManager.getRemark(baseUser.getUid());
            if (TextUtils.isEmpty(remark)) {
                spannableString = new SpannableString(baseUser.getLogin());
            } else {
                spannableString = new SpannableString(remark);
            }
            spannableString.setSpan(new bo(this), 0, spannableString.length(), 33);
            addText(spannableString, " 觉得好笑");
            setOnClickListener(new a(this.f));
        } else if (i > 1) {
            SpannableString spannableString2;
            baseUser = (BaseUser) list.get(0);
            BaseUser baseUser2 = (BaseUser) list.get(1);
            CharSequence remark2 = RemarkManager.getRemark(baseUser.getUid());
            if (TextUtils.isEmpty(remark2)) {
                spannableString = new SpannableString(baseUser.getLogin());
            } else {
                spannableString = new SpannableString(remark2);
            }
            spannableString.setSpan(new bp(this), 0, spannableString.length(), 33);
            remark2 = RemarkManager.getRemark(baseUser2.getUid());
            if (TextUtils.isEmpty(remark2)) {
                spannableString2 = new SpannableString(baseUser2.getLogin());
            } else {
                spannableString2 = new SpannableString(remark2);
            }
            spannableString2.setSpan(new bq(this), 0, spannableString2.length(), 33);
            Object format = String.format(" 等 %s 位糗友觉得好笑  ", new Object[]{Integer.valueOf(i)});
            if (spannableString.length() + spannableString2.length() > 16) {
                addText(spannableString, format);
            } else {
                a(spannableString, spannableString2, format);
            }
            setOnClickListener(new a(this.f));
        }
    }

    public void destroy() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) instanceof ImageView) {
                ((ImageView) getChildAt(i)).setImageDrawable(null);
            }
        }
        if (this.g != null) {
            this.g.clear();
        }
    }

    public TextView addText(Object obj, CharSequence charSequence) {
        return addText(obj, charSequence, true);
    }

    public TextView addText(Object obj, CharSequence charSequence, boolean z) {
        Resources resources = getResources();
        View qiushiEmotionTextView = new QiushiEmotionTextView(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 83;
        layoutParams.leftMargin = (int) (resources.getDisplayMetrics().density * 16.0f);
        qiushiEmotionTextView.setLayoutParams(layoutParams);
        qiushiEmotionTextView.setTextSize((float) this.c);
        qiushiEmotionTextView.setTextColor(this.d);
        qiushiEmotionTextView.setBackgroundColor(UIHelper.isNightTheme() ? 0 : -1);
        if (z) {
            qiushiEmotionTextView.setSingleLine(true);
            qiushiEmotionTextView.setEllipsize(TruncateAt.END);
        }
        qiushiEmotionTextView.setGravity(83);
        if (obj instanceof SpannableString) {
            qiushiEmotionTextView.setText((SpannableString) obj);
        } else if (obj instanceof CharSequence) {
            qiushiEmotionTextView.setText((CharSequence) obj);
        } else {
            qiushiEmotionTextView.setText(obj.toString());
        }
        qiushiEmotionTextView.append(charSequence);
        if (charSequence instanceof Spannable) {
            qiushiEmotionTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        addView(qiushiEmotionTextView);
        return qiushiEmotionTextView;
    }

    private TextView a(Object obj, Object obj2, String str) {
        Resources resources = getResources();
        View textView = new TextView(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, (int) (resources.getDisplayMetrics().density * 30.0f));
        layoutParams.gravity = 80;
        layoutParams.leftMargin = (int) (resources.getDisplayMetrics().density * 16.0f);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize((float) this.c);
        textView.setTextColor(this.d);
        textView.setSingleLine(true);
        textView.setGravity(83);
        textView.setEllipsize(TruncateAt.END);
        textView.setBackgroundColor(UIHelper.isNightTheme() ? 0 : -1);
        if (obj instanceof SpannableString) {
            textView.setText((SpannableString) obj);
        } else if (obj instanceof CharSequence) {
            textView.setText((CharSequence) obj);
        } else {
            textView.setText(obj.toString());
        }
        textView.append("、");
        if (obj2 instanceof SpannableString) {
            textView.append((SpannableString) obj2);
        } else if (obj2 instanceof CharSequence) {
            textView.append((CharSequence) obj2);
        } else {
            textView.append(obj2.toString());
        }
        textView.append(str);
        addView(textView);
        return textView;
    }

    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        LinearLayout.LayoutParams generateDefaultLayoutParams = super.generateDefaultLayoutParams();
        generateDefaultLayoutParams.gravity = 83;
        return generateDefaultLayoutParams;
    }
}
