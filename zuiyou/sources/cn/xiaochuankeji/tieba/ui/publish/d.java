package cn.xiaochuankeji.tieba.ui.publish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import java.util.ArrayList;

public class d implements OnClickListener {
    private View a;
    private ListView b;
    private Context c;
    private a d;
    private ImageView e;
    private b f;

    public interface a {
        void j();
    }

    public class b extends BaseAdapter {
        final /* synthetic */ d a;
        private Context b;
        private ArrayList<String> c = new ArrayList();

        class a {
            final /* synthetic */ b a;
            private TextView b;
            private View c;

            a(b bVar) {
                this.a = bVar;
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(d dVar, Context context, ArrayList<String> arrayList) {
            this.a = dVar;
            this.b = context;
            this.c = arrayList;
        }

        public int getCount() {
            return this.c.size();
        }

        public String a(int i) {
            return (String) this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                a aVar2 = new a(this);
                view = LayoutInflater.from(this.b).inflate(R.layout.view_item_vote_item, viewGroup, false);
                aVar2.b = (TextView) view.findViewById(R.id.tvItemValue);
                aVar2.c = view.findViewById(R.id.ivSeparator);
                view.findViewById(R.id.tvVotePercent).setVisibility(8);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            if (i == getCount() - 1) {
                aVar.c.setVisibility(4);
            } else {
                aVar.c.setVisibility(0);
            }
            aVar.b.setText(a(i));
            return view;
        }
    }

    public d(Context context, View view, OnItemClickListener onItemClickListener) {
        this.c = context;
        this.e = (ImageView) view.findViewById(R.id.ivCancelVote);
        this.e.setOnClickListener(this);
        this.b = (ListView) view.findViewById(R.id.lvVoteItems);
        this.b.setOnItemClickListener(onItemClickListener);
        this.b.setBackgroundResource(R.drawable.preview_vote_list_bg);
        this.a = view;
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            this.f = new b(this, this.c, arrayList);
            this.b.setAdapter(this.f);
            this.a.setVisibility(0);
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivCancelVote:
                this.a.setVisibility(8);
                if (this.d != null) {
                    this.d.j();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
