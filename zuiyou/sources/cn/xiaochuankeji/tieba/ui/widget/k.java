package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicTypeJson;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class k extends Dialog implements OnClickListener {
    private ListView a;
    private TextView b;
    private TextView c;
    private LinearLayout d;
    private LinkedHashMap<String, String> e = new LinkedHashMap();
    private ArrayList<String> f = new ArrayList();
    private b g;
    private TextView h;
    private EditText i;
    private Post j;

    class a extends BaseAdapter {
        public LinkedHashMap<String, String> a;
        final /* synthetic */ k b;

        public a(k kVar, LinkedHashMap<String, String> linkedHashMap) {
            this.b = kVar;
            this.a = linkedHashMap;
        }

        public int getCount() {
            return this.a.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view != null) {
                cVar = (c) view.getTag();
            } else {
                c cVar2 = new c(this.b, this.b.getContext());
                view = cVar2.f_();
                cVar = cVar2;
            }
            String str = (String) this.a.keySet().toArray()[i];
            String str2 = (String) this.a.get(str);
            if (!(i != 1 || this.b.j == null || this.b.j._topic == null || TextUtils.isEmpty(this.b.j._topic._topicName))) {
                str2 = str2 + "：" + this.b.j._topic._topicName;
            }
            cVar.a(str, str2);
            view.setTag(cVar);
            return view;
        }
    }

    public interface b {
        void a(ArrayList<String> arrayList, String str);
    }

    class c extends j {
        final /* synthetic */ k a;
        private TextView b;
        private ImageView c;
        private String d;

        protected c(k kVar, Context context) {
            this.a = kVar;
            super(context);
        }

        protected View a(LayoutInflater layoutInflater) {
            return layoutInflater.inflate(R.layout.tedium_post_listview_item, null);
        }

        protected void a(View view) {
            this.b = (TextView) view.findViewById(R.id.tvContent);
            this.c = (ImageView) view.findViewById(R.id.ivCheck);
        }

        public void a(String str, String str2) {
            this.b.setText(str2);
            this.d = str;
        }

        public String c() {
            return this.d;
        }

        public boolean d() {
            return this.c.isSelected();
        }

        public void a(boolean z) {
            this.c.setSelected(z);
        }
    }

    public k(Context context) {
        super(context);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(17170445);
        View inflate = View.inflate(getContext(), R.layout.tedium_post_view_container, null);
        this.a = (ListView) inflate.findViewById(R.id.lvContent);
        this.b = (TextView) inflate.findViewById(R.id.tvCancel);
        this.c = (TextView) inflate.findViewById(R.id.tvConfirm);
        this.d = (LinearLayout) inflate.findViewById(R.id.llContent);
        this.h = (TextView) inflate.findViewById(R.id.tvTitle);
        this.i = (EditText) inflate.findViewById(R.id.dialog_edittext);
        setContentView(inflate);
    }

    public void a(LinkedHashMap<String, String> linkedHashMap, b bVar) {
        a(null, linkedHashMap, bVar);
    }

    public void a(Post post, LinkedHashMap<String, String> linkedHashMap, b bVar) {
        this.j = post;
        this.g = bVar;
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            this.e = (LinkedHashMap) linkedHashMap.clone();
        }
        a();
        if (post == null || post._topic == null) {
            this.i.setVisibility(8);
        } else {
            a(post._topic._topicID);
        }
    }

    public void a() {
        setCanceledOnTouchOutside(true);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        if (this.e.size() != 0) {
            this.a.setAdapter(new a(this, this.e));
            this.a.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    c cVar = (c) view.getTag();
                    if (cVar.d()) {
                        cVar.a(false);
                        this.a.f.remove(cVar.c());
                        return;
                    }
                    cVar.a(true);
                    this.a.f.add(cVar.c());
                }
            });
            this.i.setOnEditorActionListener(new OnEditorActionListener(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        this.a.c();
                    }
                    return false;
                }
            });
        }
    }

    public void show() {
        this.f.clear();
        super.show();
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((double) e.b()) * 0.84d);
        window.setAttributes(attributes);
    }

    public void b() {
        dismiss();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                b();
                return;
            case R.id.tvConfirm:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        if (this.f.size() == 0 && TextUtils.isEmpty(this.i.getText())) {
            g.c("请选择一个屏蔽理由");
            return;
        }
        if (this.g != null) {
            this.g.a(this.f, this.i.getText().toString());
        }
        b();
    }

    private void a(long j) {
        new cn.xiaochuankeji.tieba.api.topic.b().c(j).a(rx.a.b.a.a()).b(new rx.j<TopicTypeJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TopicTypeJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(TopicTypeJson topicTypeJson) {
                if (!TextUtils.isEmpty(topicTypeJson.typeName) && this.a.e != null && this.a.a != null && this.a.e.size() > 0) {
                    this.a.e.put("4", topicTypeJson.typeName);
                    ((a) this.a.a.getAdapter()).notifyDataSetChanged();
                }
            }
        });
    }
}
