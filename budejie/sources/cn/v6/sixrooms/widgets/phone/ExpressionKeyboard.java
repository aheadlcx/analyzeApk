package cn.v6.sixrooms.widgets.phone;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.ExpressionPagerAdapter;
import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.utils.phone.PhoneSmileyParser;
import java.util.ArrayList;
import java.util.List;

public class ExpressionKeyboard extends RelativeLayout implements OnClickListener {
    Runnable a = new h(this);
    private Context b;
    private View c;
    private ImageView d;
    private ImageView e;
    private ViewPager f;
    private PhoneSmileyParser g;
    private OnOperateListener h;
    private Handler i;
    public boolean interrupt = false;
    private ExpressionPagerAdapter j;
    private ArrayList<ArrayList<PageSmily>> k;
    private ImageView l;
    private ImageView m;
    private LinearLayout n;
    private ImageView o;
    private TextView p;
    private Dialog q;
    private OnFinishButtonClickListener r;
    private OnPermissionDenyListener s;
    private BaseFragmentActivity t;
    private int[] u;
    private int[] v;
    private int w;

    public interface OnPermissionDenyListener {
        void onPermissionDeny(int i);

        void onPermissionInvalid();
    }

    public interface OnOperateListener {
        void closeKeyboard();

        void deleteSmileyVo(PhoneSmileyParser phoneSmileyParser);

        void openKeyboard();

        void selectedSmileyVo(SmileyVo smileyVo);

        void sendChatInfo();
    }

    public interface OnFinishButtonClickListener {
        void onFinishButtonClicked(View view);
    }

    public class PageSmily {
        View a;
        List<SmileyVo> b;
        final /* synthetic */ ExpressionKeyboard c;

        public PageSmily(ExpressionKeyboard expressionKeyboard, View view, List<SmileyVo> list) {
            this.c = expressionKeyboard;
            this.a = view;
            this.b = list;
        }

        public View getView() {
            return this.a;
        }

        public void setView(View view) {
            this.a = view;
        }

        public List<SmileyVo> getSmilys() {
            return this.b;
        }

        public void setSmilys(List<SmileyVo> list) {
            this.b = list;
        }
    }

    public ExpressionKeyboard(Context context) {
        super(context);
        this.b = context;
        this.c = View.inflate(context, R.layout.phone_room_expression_page, this);
        a();
        b();
        c();
        this.i = new Handler();
    }

    public ExpressionKeyboard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        this.c = View.inflate(context, R.layout.phone_room_expression_page, this);
        a();
        b();
        c();
        this.i = new Handler();
    }

    public void setBaseFragmentActivity(BaseFragmentActivity baseFragmentActivity) {
        this.t = baseFragmentActivity;
    }

    public void disableGuardExpress() {
        this.m.setVisibility(8);
    }

    public void disableExpress() {
        this.m.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void disableFinishButton() {
        this.p.setVisibility(8);
    }

    private void a() {
        this.n = (LinearLayout) this.c.findViewById(R.id.ll_dots);
        this.d = (ImageView) this.c.findViewById(R.id.iv_dot_second);
        this.d.setEnabled(false);
        this.e = (ImageView) this.c.findViewById(R.id.iv_dot_third);
        this.e.setEnabled(false);
        this.f = (ViewPager) this.c.findViewById(R.id.viewPager);
        this.f.setOffscreenPageLimit(4);
        this.l = (ImageView) this.c.findViewById(R.id.iv_expression_default);
        this.m = (ImageView) this.c.findViewById(R.id.iv_expression_second);
        this.o = (ImageView) this.c.findViewById(R.id.iv_expression_vip);
        this.p = (TextView) this.c.findViewById(R.id.retransmit_expression_ok);
        this.l.setEnabled(false);
        this.m.setEnabled(true);
        this.o.setEnabled(true);
    }

    private void b() {
        this.g = PhoneSmileyParser.getInstance(V6Coop.getInstance().getContext());
        this.k = new ArrayList();
        for (int i = 0; i < this.g.getGroupCount(); i++) {
            this.k.add(a(i));
        }
        this.j = new ExpressionPagerAdapter((ArrayList) this.k.get(0));
        this.f.setAdapter(this.j);
    }

    public void changeSmilyGroup(int i) {
        int i2 = 0;
        this.f.removeAllViews();
        this.j.setExpressionViews((ArrayList) this.k.get(i));
        this.j.notifyDataSetChanged();
        this.f.setCurrentItem(0);
        int size = ((ArrayList) this.k.get(i)).size();
        int childCount = this.n.getChildCount();
        DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
        if (size < childCount) {
            while (i2 < childCount - size) {
                this.n.getChildAt(size + i2).setVisibility(8);
                i2++;
            }
            return;
        }
        int i3;
        for (i3 = 0; i3 < childCount; i3++) {
            this.n.getChildAt(i3).setVisibility(0);
        }
        for (i3 = 0; i3 < size - childCount; i3++) {
            View imageView = new ImageView(this.b);
            imageView.setBackgroundDrawable(this.b.getResources().getDrawable(R.drawable.phone_dots_selector));
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(((int) displayMetrics.density) * 10, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setEnabled(false);
            this.n.addView(imageView);
        }
    }

    private ArrayList<PageSmily> a(int i) {
        ArrayList<PageSmily> arrayList = new ArrayList();
        List list = this.g.getdivisData(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            View view;
            List list2 = (List) list.get(i2);
            ExpressionGroup expressionGroup;
            switch (i) {
                case 0:
                    expressionGroup = (ExpressionGroup) View.inflate(this.b, R.layout.phone_activity_first_expression_group, null);
                    expressionGroup.setDeleteSmileyListener(new e(this));
                    SmileyVo smileyVo = new SmileyVo();
                    smileyVo.setResID(R.drawable.phone_but_delete_expression_selector);
                    smileyVo.setType(2);
                    list2.add(smileyVo);
                    break;
                case 1:
                    expressionGroup = (ExpressionGroup) View.inflate(this.b, R.layout.phone_activity_second_expression_group, null);
                    break;
                case 2:
                    expressionGroup = (ExpressionGroup) View.inflate(this.b, R.layout.phone_activity_vip_expression_group, null);
                    break;
                default:
                    view = null;
                    break;
            }
            view.setOnItemClickListener(new f(this, i, list2));
            arrayList.add(new PageSmily(this, view, list2));
        }
        return arrayList;
    }

    private void c() {
        this.p.setOnClickListener(this);
        this.f.setOnPageChangeListener(new g(this));
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_expression_default) {
            this.l.setEnabled(false);
            this.m.setEnabled(true);
            this.o.setEnabled(true);
            this.w = 0;
        } else if (id == R.id.iv_expression_second) {
            this.m.setEnabled(false);
            this.l.setEnabled(true);
            this.o.setEnabled(true);
            this.w = 1;
        } else if (id == R.id.iv_expression_vip) {
            this.o.setEnabled(false);
            this.l.setEnabled(true);
            this.m.setEnabled(true);
            this.w = 2;
        } else if (id == R.id.retransmit_expression_ok && this.r != null) {
            this.r.onFinishButtonClicked(view);
        }
        changeSmilyGroup(this.w);
    }

    public void setOnOperateListener(OnOperateListener onOperateListener) {
        this.h = onOperateListener;
    }

    public OnFinishButtonClickListener getOnFinishButtonClickLister() {
        return this.r;
    }

    public void setOnFinishButtonClickLister(OnFinishButtonClickListener onFinishButtonClickListener) {
        this.r = onFinishButtonClickListener;
    }

    public OnPermissionDenyListener getPermissionListener() {
        return this.s;
    }

    public void setOnPermissionListener(OnPermissionDenyListener onPermissionDenyListener) {
        this.s = onPermissionDenyListener;
    }

    public void dismissKeyboardDialog() {
        if (this.q != null) {
            this.q.dismiss();
        }
    }

    public int[] getGuardPermissonGroup() {
        return this.u;
    }

    public void setGuardPermissonGroup(int[] iArr) {
        this.u = iArr;
    }

    public int[] getVipPermissonGroup() {
        return this.v;
    }

    public void setVipPermissonGroup(int[] iArr) {
        this.v = iArr;
    }

    static /* synthetic */ void a(ExpressionKeyboard expressionKeyboard, int i) {
        for (int i2 = 0; i2 < expressionKeyboard.n.getChildCount(); i2++) {
            if (i2 == i) {
                expressionKeyboard.n.getChildAt(i2).setEnabled(true);
            } else {
                expressionKeyboard.n.getChildAt(i2).setEnabled(false);
            }
        }
    }
}
