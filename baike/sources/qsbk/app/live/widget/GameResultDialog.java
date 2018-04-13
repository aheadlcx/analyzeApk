package qsbk.app.live.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import java.util.List;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.adapter.BestBetAdapter;
import qsbk.app.live.model.BestBetResult;

public class GameResultDialog extends BaseDialog {
    private ImageView c;
    private RecyclerView d;
    private BestBetAdapter e;
    private LinearLayoutManager f;
    private List<BestBetResult> g;
    private int h;

    public GameResultDialog(Context context, List<BestBetResult> list, int i) {
        super(context);
        this.g = list;
        this.h = i;
    }

    protected int c() {
        if (this.h == 4) {
            return R.layout.live_dialog_bestbet_fanfanle;
        }
        return R.layout.live_dialog_bestbet;
    }

    protected void d() {
        i();
        this.d = (RecyclerView) findViewById(R.id.recyclerview);
        this.c = (ImageView) findViewById(R.id.iv_close);
    }

    protected void e() {
        this.f = new LinearLayoutManager(getContext());
        this.e = new BestBetAdapter(getContext(), this.g, this.h);
        this.d.setLayoutManager(this.f);
        this.d.setAdapter(this.e);
        this.d.setItemAnimator(new DefaultItemAnimator());
        this.d.setHasFixedSize(true);
        this.c.setOnClickListener(new ch(this));
    }

    public void show() {
        super.show();
        this.b.postDelayed(new ci(this), 4000);
    }

    protected int a() {
        return 48;
    }

    public void setItems(List<BestBetResult> list) {
        this.g = list;
        this.e.notifyDataSetChanged();
    }
}
