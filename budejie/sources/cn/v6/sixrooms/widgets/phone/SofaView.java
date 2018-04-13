package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.ImprovedDialogForSofa;
import cn.v6.sixrooms.utils.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SofaView extends LinearLayout implements OnClickListener {
    Context a;
    private List<InnerClassSofaCrop> b = new ArrayList();
    private RelativeLayout c;
    private RelativeLayout d;
    private RelativeLayout e;
    private RelativeLayout f;
    private ImprovedDialogForSofa g;
    private String h;
    private String i;
    private ForegroundColorSpan j;
    private ForegroundColorSpan k;
    private ForegroundColorSpan l;
    private Map<String, SofaBean> m = new HashMap();
    private int n = 0;
    private OnKickSofaListener o;
    private AbsoluteSizeSpan p;

    public class InnerClassSofaCrop {
        final /* synthetic */ SofaView a;
        private SimpleDraweeView b;
        private StrokeTextView c;
        private Animation d;
        private String e;
        private String f;

        public Animation getAa() {
            return this.d;
        }

        public void setAa(Animation animation) {
            this.d = animation;
        }

        public InnerClassSofaCrop(SofaView sofaView) {
            this.a = sofaView;
        }

        public InnerClassSofaCrop(SofaView sofaView, SimpleDraweeView simpleDraweeView, StrokeTextView strokeTextView) {
            this.a = sofaView;
            this.b = simpleDraweeView;
            this.c = strokeTextView;
        }

        public ImageView getCiv_head() {
            return this.b;
        }

        public void setCiv_head(SimpleDraweeView simpleDraweeView) {
            this.b = simpleDraweeView;
        }

        public StrokeTextView getStv_Name() {
            return this.c;
        }

        public void setStv_Name(StrokeTextView strokeTextView) {
            this.c = strokeTextView;
        }

        public void updateSofa(String str, String str2) {
            this.e = str;
            this.f = str2;
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setAnimationListener(new ap(this, str));
            this.b.startAnimation(alphaAnimation);
        }
    }

    public interface OnKickSofaListener {
        void kickSofa(int i, int i2);

        void onDismisDialog();

        void onShowDialog();
    }

    public SofaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SofaView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.room_sofa, this, true);
        this.a = context;
        this.g = DialogUtils.createSofaDialog(context);
        this.j = new ForegroundColorSpan(Color.parseColor("#f43e26"));
        this.k = new ForegroundColorSpan(Color.parseColor("#a1a1a1"));
        this.l = new ForegroundColorSpan(Color.parseColor("#ed24fa"));
        this.p = new AbsoluteSizeSpan(DensityUtil.dip2px(11.0f));
        this.h = context.getString(R.string.room_sofa_dialog_price);
        this.i = context.getString(R.string.room_sofa_dialog_content);
        this.c = (RelativeLayout) findViewById(R.id.rl_sofaPart1);
        this.c.setOnClickListener(this);
        this.b.add(new InnerClassSofaCrop(this, (SimpleDraweeView) findViewById(R.id.civ_head1), (StrokeTextView) findViewById(R.id.stv_Name1)));
        this.d = (RelativeLayout) findViewById(R.id.rl_sofaPart2);
        this.d.setOnClickListener(this);
        this.b.add(new InnerClassSofaCrop(this, (SimpleDraweeView) findViewById(R.id.civ_head2), (StrokeTextView) findViewById(R.id.stv_Name2)));
        this.e = (RelativeLayout) findViewById(R.id.rl_sofaPart3);
        this.e.setOnClickListener(this);
        this.b.add(new InnerClassSofaCrop(this, (SimpleDraweeView) findViewById(R.id.civ_head3), (StrokeTextView) findViewById(R.id.stv_Name3)));
        this.f = (RelativeLayout) findViewById(R.id.rl_sofaPart4);
        this.f.setOnClickListener(this);
        this.b.add(new InnerClassSofaCrop(this, (SimpleDraweeView) findViewById(R.id.civ_head4), (StrokeTextView) findViewById(R.id.stv_Name4)));
        this.g.setImprovedDialogListener(new ao(this));
    }

    public void initSofa(Map<String, SofaBean> map) {
        if (map != null) {
            this.m = map;
            for (Entry value : this.m.entrySet()) {
                setIconAndName((SofaBean) value.getValue());
            }
        }
    }

    private void setIconAndName(SofaBean sofaBean) {
        int site = sofaBean.getSite();
        String alias = sofaBean.getAlias();
        String pic = sofaBean.getPic();
        LogUtils.d("SofaView", "===================================================");
        LogUtils.d("SofaView", "site: " + site);
        LogUtils.d("SofaView", "alias: " + alias);
        LogUtils.d("SofaView", "===================================================");
        ((InnerClassSofaCrop) this.b.get(site - 1)).updateSofa(pic, alias);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_sofaPart1) {
            a(1);
        } else if (id == R.id.rl_sofaPart2) {
            a(2);
        } else if (id == R.id.rl_sofaPart3) {
            a(3);
        } else if (id == R.id.rl_sofaPart4) {
            a(4);
        }
    }

    private void a(int i) {
        if (GlobleValue.getUserBean() == null) {
            ((RoomActivity) this.a).showLoginDialog();
            return;
        }
        CharSequence spannableStringBuilder;
        this.n = i;
        SofaBean sofaBean = getSofaBean(String.valueOf(i));
        if (sofaBean == null || sofaBean.getNum() <= 0) {
            String str = "当前0个沙发，超过此数量才能成功抢座" + this.h + "。";
            spannableStringBuilder = new SpannableStringBuilder(str);
            spannableStringBuilder.setSpan(this.j, 2, 3, 33);
            spannableStringBuilder.setSpan(this.k, str.indexOf(this.h), str.length(), 33);
            spannableStringBuilder.setSpan(this.p, str.indexOf(this.h), str.length(), 33);
        } else {
            String format = String.format(this.i, new Object[]{sofaBean.getAlias(), Integer.valueOf(sofaBean.getNum()), this.h});
            spannableStringBuilder = new SpannableStringBuilder(format + "。");
            spannableStringBuilder.setSpan(this.l, 0, sofaBean.getAlias().length(), 33);
            spannableStringBuilder.setSpan(this.j, sofaBean.getAlias().length() + 2, String.valueOf(sofaBean.getNum()).length() + (sofaBean.getAlias().length() + 2), 33);
            spannableStringBuilder.setSpan(this.k, format.indexOf(this.h), format.length(), 33);
            spannableStringBuilder.setSpan(this.p, format.indexOf(this.h), format.length(), 33);
        }
        this.g.setImprovedContent(spannableStringBuilder);
        this.g.show();
    }

    public void setOnKickSofaListener(OnKickSofaListener onKickSofaListener) {
        this.o = onKickSofaListener;
    }

    public int getNumOfSeat(String str) {
        SofaBean sofaBean = (SofaBean) this.m.get(str);
        if (sofaBean == null) {
            return 0;
        }
        return sofaBean.getNum();
    }

    public SofaBean getSofaBean(String str) {
        if (this.m != null) {
            return (SofaBean) this.m.get(str);
        }
        return null;
    }

    public void kickSofa(SofaBean sofaBean) {
        this.m.put(sofaBean.getSite(), sofaBean);
        setIconAndName(sofaBean);
    }
}
