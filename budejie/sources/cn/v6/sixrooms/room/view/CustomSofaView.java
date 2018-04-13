package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CustomSofaView extends LinearLayout implements OnClickListener {
    private List<InnerClassSofaCrop> a = new ArrayList();
    private Context b;
    private Map<String, SofaBean> c = new HashMap();
    private CustomSofaView$OnSeatClickListener d;

    public class InnerClassSofaCrop {
        final /* synthetic */ CustomSofaView a;
        private CustomImageSwitcher b;
        private ImageView c;
        private String d;
        private String e;

        public InnerClassSofaCrop(CustomSofaView customSofaView) {
            this.a = customSofaView;
        }

        public InnerClassSofaCrop(CustomSofaView customSofaView, CustomImageSwitcher customImageSwitcher, ImageView imageView) {
            this.a = customSofaView;
            this.b = customImageSwitcher;
            this.c = imageView;
            this.b.setInAnimation(customSofaView.b, R.anim.room_sofa_in_anim);
            this.b.setOutAnimation(customSofaView.b, R.anim.room_sofa_out_anim);
            this.b.setFactory(new a(this, customSofaView));
        }

        public CustomImageSwitcher getCiv_head() {
            return this.b;
        }

        public void setCiv_head(CustomImageSwitcher customImageSwitcher) {
            this.b = customImageSwitcher;
        }

        public ImageView getSofaSubscrip() {
            return this.c;
        }

        public void setSofaSubscrip(ImageView imageView) {
            this.c = imageView;
        }

        public void updateSofa(String str, String str2) {
            this.d = str;
            this.e = str2;
            if (this.b != null) {
                this.b.setImageURI(Uri.parse(this.d));
                if (TextUtils.isEmpty(this.e)) {
                    this.c.setVisibility(4);
                } else {
                    this.c.setVisibility(0);
                }
            }
        }
    }

    public CustomSofaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CustomSofaView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.room_custom_sofa, this, true);
        this.b = context;
        this.a.add(new InnerClassSofaCrop(this, (CustomImageSwitcher) findViewById(R.id.room_sofa1), (ImageView) findViewById(R.id.room_sofa_subscript1)));
        this.a.add(new InnerClassSofaCrop(this, (CustomImageSwitcher) findViewById(R.id.room_sofa2), (ImageView) findViewById(R.id.room_sofa_subscript2)));
        this.a.add(new InnerClassSofaCrop(this, (CustomImageSwitcher) findViewById(R.id.room_sofa3), (ImageView) findViewById(R.id.room_sofa_subscript3)));
        this.a.add(new InnerClassSofaCrop(this, (CustomImageSwitcher) findViewById(R.id.room_sofa4), (ImageView) findViewById(R.id.room_sofa_subscript4)));
        findViewById(R.id.room_sofa_layout1).setOnClickListener(this);
        findViewById(R.id.room_sofa_layout2).setOnClickListener(this);
        findViewById(R.id.room_sofa_layout3).setOnClickListener(this);
        findViewById(R.id.room_sofa_layout4).setOnClickListener(this);
    }

    public void initSofa(Map<String, SofaBean> map) {
        if (map != null) {
            this.c = map;
            for (Entry value : this.c.entrySet()) {
                setIconAndName((SofaBean) value.getValue());
            }
        }
    }

    private void setIconAndName(SofaBean sofaBean) {
        int site = sofaBean.getSite();
        String alias = sofaBean.getAlias();
        String pic = sofaBean.getPic();
        LogUtils.e("CustomSofaView", "===================================================");
        LogUtils.e("CustomSofaView", "site: " + site);
        LogUtils.e("CustomSofaView", "alias: " + alias);
        LogUtils.e("CustomSofaView", "===================================================");
        ((InnerClassSofaCrop) this.a.get(site - 1)).updateSofa(pic, alias);
    }

    public int getNumOfSeat(String str) {
        SofaBean sofaBean = (SofaBean) this.c.get(str);
        if (sofaBean == null) {
            return 0;
        }
        return sofaBean.getNum();
    }

    public SofaBean getSofaBean(String str) {
        if (this.c != null) {
            return (SofaBean) this.c.get(str);
        }
        return null;
    }

    public void kickSofa(SofaBean sofaBean) {
        this.c.put(sofaBean.getSite(), sofaBean);
        setIconAndName(sofaBean);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.room_sofa_layout1) {
            if (this.d != null) {
                this.d.onSeatClick(0);
            }
        } else if (id == R.id.room_sofa_layout2) {
            if (this.d != null) {
                this.d.onSeatClick(1);
            }
        } else if (id == R.id.room_sofa_layout3) {
            if (this.d != null) {
                this.d.onSeatClick(2);
            }
        } else if (id == R.id.room_sofa_layout4 && this.d != null) {
            this.d.onSeatClick(3);
        }
    }

    public void setOnSeatClickListener(CustomSofaView$OnSeatClickListener customSofaView$OnSeatClickListener) {
        this.d = customSofaView$OnSeatClickListener;
    }
}
