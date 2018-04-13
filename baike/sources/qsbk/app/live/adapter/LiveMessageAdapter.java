package qsbk.app.live.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.List;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.TextLengthFilter;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveLoveMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveRemindMessage;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.live.utils.FontUtils;
import qsbk.app.live.utils.ThemeUtils;
import qsbk.app.live.widget.FamilyLevelView;

public class LiveMessageAdapter extends Adapter<ViewHolder> {
    private static final Property<MutableForegroundColorSpan, Integer> w = new q(Integer.class, "MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY");
    private Context a;
    private List<LiveMessage> b;
    private boolean c;
    private Drawable[] d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private float q = 1.0f;
    private float r = 1.0f;
    private final String s;
    private final ForegroundColorSpan t;
    private final ForegroundColorSpan u;
    private final ForegroundColorSpan v;
    private OnItemClickLitener x;

    private class MutableForegroundColorSpan extends ForegroundColorSpan {
        public final Creator<MutableForegroundColorSpan> CREATOR = new w(this);
        final /* synthetic */ LiveMessageAdapter a;
        private int b;

        public MutableForegroundColorSpan(LiveMessageAdapter liveMessageAdapter, int i) {
            this.a = liveMessageAdapter;
            super(i);
            this.b = i;
        }

        public MutableForegroundColorSpan(LiveMessageAdapter liveMessageAdapter, Parcel parcel) {
            this.a = liveMessageAdapter;
            super(parcel);
            this.b = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(getForegroundColor());
        }

        public void setForegroundColor(int i) {
            this.b = i;
        }

        public int getForegroundColor() {
            return Color.argb(Color.alpha(this.b), Color.red(this.b), Color.green(this.b), Color.blue(this.b));
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int i);

        void onItemLongClick(View view, int i);
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView tvComment;

        public ViewHolder(View view) {
            super(view);
            this.tvComment = (TextView) view.findViewById(R.id.tv_content);
        }
    }

    private class a extends ReplacementSpan {
        Drawable a;
        int b;
        int c;
        int d;
        int e;
        boolean f = false;
        int g;
        Typeface h;
        int i = 0;
        final /* synthetic */ LiveMessageAdapter j;

        public a(LiveMessageAdapter liveMessageAdapter, int i, int i2, int i3, int i4, int i5, boolean z, int i6, Typeface typeface) {
            this.j = liveMessageAdapter;
            this.a = ContextCompat.getDrawable(AppUtils.getInstance().getAppContext(), i);
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = z;
            this.g = i6;
            this.h = typeface;
        }

        public a(LiveMessageAdapter liveMessageAdapter, Drawable drawable, int i, int i2, int i3, int i4, boolean z, int i5, Typeface typeface) {
            this.j = liveMessageAdapter;
            this.a = drawable;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = z;
            this.g = i5;
            this.h = typeface;
        }

        public a setPaddingTop(int i) {
            this.i = i;
            return this;
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            return this.b;
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            int i6 = i5 - i3;
            if (this.a != null) {
                this.a.setBounds(new Rect((int) f, ((i6 - this.c) / 2) + i3, ((int) f) + this.b, (((i6 - this.c) / 2) + i3) + this.c));
                this.a.draw(canvas);
            }
            if (this.d > 0) {
                paint.setTextSize((float) this.d);
                paint.setColor(this.e);
                String substring = charSequence.toString().substring(i, i2);
                i6 = ((((i6 / 2) + i3) - ((paint.getFontMetricsInt().ascent + paint.getFontMetricsInt().descent) / 2)) + 1) + this.i;
                if (this.h != null) {
                    paint.setTypeface(this.h);
                }
                if (this.f) {
                    paint.setTextAlign(Align.CENTER);
                    canvas.drawText(substring, ((float) (this.b / 2)) + f, (float) i6, paint);
                    return;
                }
                canvas.drawText(substring, ((float) this.g) + f, (float) i6, paint);
            }
        }
    }

    private class b extends ReplacementSpan {
        final /* synthetic */ LiveMessageAdapter a;
        private String b;
        private String c;

        public b(LiveMessageAdapter liveMessageAdapter, String str, String str2) {
            this.a = liveMessageAdapter;
            this.b = str;
            this.c = str2;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, @Nullable FontMetricsInt fontMetricsInt) {
            return (int) (((float) WindowUtils.dp2Px(39)) * this.a.r);
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Drawable drawable = ContextCompat.getDrawable(AppUtils.getInstance().getAppContext(), FamilyLevelView.getFamilyLevelBgResource(Integer.parseInt(this.b)));
            int dp2Px = (int) (((float) WindowUtils.dp2Px(15)) * this.a.r);
            int dp2Px2 = (int) (((float) WindowUtils.dp2Px(39)) * this.a.r);
            int i6 = i5 - i3;
            if (drawable != null) {
                drawable.setBounds(new Rect((int) f, ((i6 - dp2Px) / 2) + i3, dp2Px2 + ((int) f), dp2Px + (((i6 - dp2Px) / 2) + i3)));
                drawable.draw(canvas);
            }
            dp2Px = (int) (((float) WindowUtils.sp2px(this.a.a, 8.0f)) * this.a.r);
            paint.setTextSize((float) ((int) (((float) WindowUtils.sp2px(this.a.a, 9.0f)) * this.a.r)));
            paint.setColor(-1);
            dp2Px2 = (((i6 / 2) + i3) - ((paint.getFontMetricsInt().ascent + paint.getFontMetricsInt().descent) / 2)) + 1;
            canvas.drawText(this.b, (((float) WindowUtils.dp2Px(this.b.length() <= 1 ? 7 : 3)) * this.a.r) + f, (float) dp2Px2, paint);
            paint.setTextSize((float) dp2Px);
            canvas.drawText(this.c, (((float) WindowUtils.dp2Px(19)) * this.a.r) + f, (float) dp2Px2, paint);
        }
    }

    public LiveMessageAdapter(Context context, List<LiveMessage> list, boolean z) {
        this.a = context;
        this.b = list;
        this.c = z;
        this.s = AppUtils.getInstance().getUserInfoProvider().getUserName();
        this.e = WindowUtils.dp2Px(13);
        this.f = WindowUtils.dp2Px(9);
        this.g = WindowUtils.dp2Px(3);
        this.h = WindowUtils.dp2Px(19);
        this.i = WindowUtils.dp2Px(24);
        this.j = WindowUtils.dp2Px(28);
        this.k = WindowUtils.dp2Px(36);
        this.p = WindowUtils.dp2Px(14);
        this.l = WindowUtils.dp2Px(36);
        this.m = WindowUtils.dp2Px(18);
        this.n = WindowUtils.dp2Px(14);
        this.o = WindowUtils.dp2Px(8);
        this.t = new ForegroundColorSpan(context.getResources().getColor(R.color.transparent_70_percent_white));
        this.u = new ForegroundColorSpan(context.getResources().getColor(R.color.yellow_ffec8a));
        this.v = new ForegroundColorSpan(context.getResources().getColor(R.color.color_768eff));
        a();
    }

    private void a() {
        if (this.d == null) {
            TypedArray obtainTypedArray = this.a.getResources().obtainTypedArray(ThemeUtils.getLoveThemeArrayResId());
            this.d = new Drawable[obtainTypedArray.length()];
            for (int i = 0; i < obtainTypedArray.length(); i++) {
                this.d[i] = ContextCompat.getDrawable(AppUtils.getInstance().getAppContext(), obtainTypedArray.getResourceId(i, R.drawable.live_favor_1));
            }
            obtainTypedArray.recycle();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolder(LayoutInflater.from(this.a).inflate(R.layout.live_message_item, viewGroup, false));
        }
        return new ViewHolder(LayoutInflater.from(this.a).inflate(R.layout.live_remind_message_item, viewGroup, false));
    }

    public int getItemViewType(int i) {
        if (this.b == null || !((LiveMessage) this.b.get(i)).isRemindMessage()) {
            return 0;
        }
        return 1;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        LiveMessage liveMessage = (LiveMessage) this.b.get(i);
        if (liveMessage.isRemindMessage()) {
            viewHolder.tvComment.setText(((LiveRemindMessage) liveMessage).getContent());
            viewHolder.itemView.setOnClickListener(new r(this, viewHolder, i));
            return;
        }
        String a;
        String str;
        String str2;
        String str3;
        String str4;
        int length;
        int length2;
        int i2;
        int b;
        if (liveMessage.getMessageType() == 13) {
            this.p = (int) (((float) WindowUtils.dp2Px(12)) * this.r);
        } else {
            this.p = (int) (((float) WindowUtils.dp2Px(14)) * this.r);
        }
        String str5 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        String str6 = "";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        String userName = liveMessage.getUserName();
        if (liveMessage.isCommentMessage() || liveMessage.isBarrageMessage()) {
            userName = userName + ": ";
        }
        String str10 = "";
        str10 = "";
        Object obj = (!a(liveMessage) || TextUtils.isEmpty(userName)) ? null : 1;
        if (obj != null) {
            a = a(userName);
        } else {
            a = "";
        }
        String content = liveMessage.getContent();
        int userLevel = liveMessage.getUserLevel();
        List list;
        if (obj != null) {
            str6 = Integer.toString(userLevel);
            userName = str10 + str6 + str5;
            if (liveMessage.isUserAdmin()) {
                str7 = AppUtils.getInstance().getAppContext().getString(R.string.admin_label);
                userName = userName + str7 + str5;
            }
            if (!TextUtils.isEmpty(liveMessage.getMessageBadge()) && liveMessage.getMessageFamilyLevel() > 0) {
                str8 = String.valueOf(liveMessage.getMessageFamilyLevel());
                str9 = liveMessage.getMessageBadge();
                userName = userName + str8 + str9 + str5;
            }
            List<String> userLabel = liveMessage.getUserLabel();
            if (userLabel != null) {
                str10 = userName;
                for (String userName2 : userLabel) {
                    str10 = str10 + userName2 + str5;
                }
            } else {
                str10 = userName2;
            }
            userName2 = str10 + a + str5;
            list = userLabel;
            str = str9;
            str2 = str8;
            str3 = str7;
            str4 = str6;
        } else {
            userName2 = str10;
            list = null;
            str = str9;
            str2 = str8;
            str3 = str7;
            str4 = str6;
        }
        CharSequence charSequence = userName2 + content;
        if (liveMessage.isLoveMessage()) {
            charSequence = charSequence + str5 + str5;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (str4.length() != 0) {
            int i3;
            int i4 = userLevel < 100 ? this.j : this.k;
            this.g = (int) (((float) (userLevel < 10 ? WindowUtils.dp2Px(4) : WindowUtils.dp2Px(3))) * this.r);
            int length3 = (userLevel < 100 ? this.h : this.i) - (str4.length() * this.g);
            if (userLevel < 280) {
                i3 = this.e;
            } else {
                i3 = (int) (((float) this.e) * 1.37f);
            }
            spannableStringBuilder.setSpan(new a(this, LevelHelper.getDrawableByLevel(userLevel), i4, i3, this.f, LevelHelper.getLevelColor(userLevel), false, length3, FontUtils.getBloggerSansFontBoldItalic()), 0, str4.length() + 0, 18);
            length = (str4.length() + str5.length()) + 0;
        } else {
            length = 0;
        }
        if (str3.length() != 0) {
            spannableStringBuilder.setSpan(new a(this, R.drawable.live_admin_bg, this.e, this.e, 0, -1, true, 0, null), length, str3.length() + length, 18);
            length += str3.length() + str5.length();
        }
        if (str.length() != 0) {
            spannableStringBuilder.setSpan(new b(this, str2, str), length, (str2.length() + length) + str.length(), 18);
            length2 = ((str2.length() + str.length()) + str5.length()) + length;
        } else {
            length2 = length;
        }
        if (r15 != null) {
            i2 = length2;
            for (String str11 : r15) {
                spannableStringBuilder.setSpan(new a(this, ConfigInfoUtil.instance().getTitleDrawable(str11), (int) (((double) this.e) * ConfigInfoUtil.instance().getTitleRatio(str11)), this.e, 0, -1, true, 0, null), i2, str11.length() + i2, 18);
                i2 = (str11.length() + str5.length()) + i2;
            }
        } else {
            i2 = length2;
        }
        if (a.length() != 0) {
            spannableStringBuilder.setSpan(this.t, i2, a.length() + i2, 18);
            i2 += a.length() + str5.length();
        }
        int[] messageColors = liveMessage.getMessageColors();
        if (messageColors == null || messageColors.length <= 0) {
            b = b(liveMessage.getMessageType());
        } else {
            b = messageColors[0];
        }
        MutableForegroundColorSpan mutableForegroundColorSpan = new MutableForegroundColorSpan(this, b);
        spannableStringBuilder.setSpan(mutableForegroundColorSpan, i2, content.length() + i2, 18);
        if ((liveMessage.isCommentMessage() || liveMessage.isBarrageMessage()) && !TextUtils.isEmpty(this.s)) {
            if (content.startsWith("@" + this.s)) {
                spannableStringBuilder.setSpan(this.u, i2, ("@" + this.s).length() + i2, 18);
            }
        }
        i2 += content.length() + str5.length();
        if (liveMessage.isLoveMessage()) {
            spannableStringBuilder.setSpan(new a(this, a(((LiveLoveMessage) liveMessage).getLoveColor()), this.e, this.e, 0, 0, true, 0, null), i2, str5.length() + i2, 18);
        }
        if (messageColors == null || messageColors.length <= 1) {
            viewHolder.tvComment.setText(spannableStringBuilder);
        } else {
            ObjectAnimator ofInt = ObjectAnimator.ofInt(mutableForegroundColorSpan, w, messageColors);
            ofInt.setDuration(1000);
            ofInt.setEvaluator(new ArgbEvaluator());
            ofInt.addUpdateListener(new s(this, viewHolder, spannableStringBuilder));
            ofInt.setRepeatCount(1);
            ofInt.addListener(new t(this, mutableForegroundColorSpan, b, viewHolder, spannableStringBuilder));
            ofInt.start();
        }
        viewHolder.tvComment.setTextSize(0, (float) this.p);
        viewHolder.tvComment.setAlpha(this.q);
        if (liveMessage.isCommentMessage()) {
            viewHolder.itemView.setOnLongClickListener(new u(this, liveMessage));
        } else {
            viewHolder.itemView.setOnLongClickListener(null);
        }
        viewHolder.itemView.setOnClickListener(new v(this, i));
    }

    private boolean a(LiveMessage liveMessage) {
        return (liveMessage.isEmptyMessage() || liveMessage.isConnectMessage() || liveMessage.isSystemMessage() || liveMessage.getMessageType() == 17 || liveMessage.getMessageType() == 18 || liveMessage.getMessageType() == 21 || liveMessage.getMessageType() == 51 || liveMessage.getMessageType() == 25) ? false : true;
    }

    private Drawable a(int i) {
        int i2 = 0;
        if (i > 0 && i <= this.d.length) {
            i2 = i - 1;
        }
        return this.d[i2];
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        int chineseCount = TextLengthFilter.getChineseCount(str);
        int i = 8;
        if (length + chineseCount > 16) {
            if (length == chineseCount) {
                str = str.substring(0, 8);
            } else {
                if (length < 8) {
                    i = length;
                }
                chineseCount = 0;
                int i2 = 0;
                while (chineseCount < i) {
                    if ("abcdefghijklmnopqrstuvwsxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(Character.toString(str.charAt(chineseCount)))) {
                        i2 += 2;
                        chineseCount++;
                    } else {
                        i2++;
                    }
                    chineseCount++;
                }
                if (i2 <= length) {
                    length = i2;
                }
                str = str.substring(0, length);
            }
        }
        return str + "";
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.b.size();
    }

    private int b(int i) {
        int i2;
        switch (i) {
            case 1:
            case 24:
                i2 = R.color.white;
                break;
            case 2:
            case 3:
            case 5:
                i2 = R.color.green_54fbff;
                break;
            case 4:
            case 7:
            case 10:
            case 12:
            case 41:
                i2 = R.color.transparent_70_percent_white;
                break;
            case 6:
                i2 = R.color.yellow_FFED3D;
                break;
            case 13:
            case 17:
            case 18:
            case 21:
            case 25:
                i2 = R.color.color_FFF13E;
                break;
            case 48:
                i2 = R.color.color_FF6F24;
                break;
            default:
                i2 = R.color.yellow_ffec8a;
                break;
        }
        return this.a.getResources().getColor(i2);
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.x = onItemClickLitener;
    }

    public void setFontSizeRate(float f) {
        this.p = (int) (((float) this.p) * f);
        this.e = (int) (((float) this.e) * f);
        this.f = (int) (((float) this.f) * f);
        this.j = (int) (((float) this.j) * f);
        this.k = (int) (((float) this.k) * f);
        this.h = (int) (((float) this.h) * f);
        this.i = (int) (((float) this.i) * f);
        this.r *= f;
        notifyDataSetChanged();
    }

    public void setAlpha(float f) {
        this.q = f;
        notifyDataSetChanged();
    }
}
