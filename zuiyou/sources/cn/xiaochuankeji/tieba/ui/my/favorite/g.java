package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.AbsListView.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.c.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;

public class g extends FrameLayout {
    private Context a;
    private TextView b;
    private TextView c;
    private ImageView d;

    public g(Context context) {
        super(context);
        this.a = context;
        b();
    }

    private void b() {
        setDescendantFocusability(393216);
        LayoutInflater.from(this.a).inflate(R.layout.view_item_favorite, this);
        setLayoutParams(new LayoutParams(-1, a.a(49.0f, this.a)));
        this.b = (TextView) findViewById(R.id.tvName);
        this.c = (TextView) findViewById(R.id.tvIntroduce);
        this.d = (ImageView) findViewById(R.id.ivIcon);
    }

    public void setData(Favorite favorite) {
        this.b.setText(favorite.getName());
        this.c.setText(favorite.getPostCount() + "条内容");
    }

    public void a() {
    }
}
