package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.pojo.Feedback;
import cn.tatagou.sdk.util.p;
import java.util.List;

public class b extends a<Feedback> {
    private static final String e = b.class.getSimpleName();

    class a {
        TextView a;
        TextView b;
        TextView c;
        RelativeLayout d;
        TextView e;
        TextView f;
        final /* synthetic */ b g;

        a(b bVar) {
            this.g = bVar;
        }
    }

    public b(Activity activity, List<Feedback> list) {
        super(activity, (List) list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.ttg_feedback_list_item, viewGroup, false);
            aVar.a = (TextView) view.findViewById(R.id.tv_time);
            aVar.b = (TextView) view.findViewById(R.id.tv_question);
            aVar.c = (TextView) view.findViewById(R.id.tv_asked);
            aVar.d = (RelativeLayout) view.findViewById(R.id.rl_answer);
            aVar.e = (TextView) view.findViewById(R.id.tv_vline);
            aVar.f = (TextView) view.findViewById(R.id.tv_answer);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        Feedback feedback = (Feedback) this.c.get(i);
        if (p.isEmpty(feedback.getReplyContent())) {
            aVar.d.setVisibility(8);
            aVar.e.setVisibility(8);
        } else {
            aVar.d.setVisibility(0);
            aVar.e.setVisibility(0);
            aVar.f.setText(feedback.getReplyContent());
        }
        aVar.a.setText(p.isEmpty(feedback.getCreateTime()) ? "" : feedback.getCreateTime());
        if (feedback.getType() != null) {
            aVar.b.setText(feedback.getType().getType());
        } else {
            aVar.b.setText("提问");
        }
        aVar.c.setText(feedback.getContent());
        return view;
    }
}
