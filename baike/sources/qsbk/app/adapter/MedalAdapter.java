package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.model.Medal;
import qsbk.app.utils.UIHelper;

public class MedalAdapter extends BaseImageAdapter {
    private boolean a = false;

    class a {
        ImageView a;
        TextView b;
        TextView c;
        TextView d;
        ProgressBar e;
        View f;
        final /* synthetic */ MedalAdapter g;

        a(MedalAdapter medalAdapter, View view) {
            this.g = medalAdapter;
            this.a = (ImageView) view.findViewById(R.id.medal_avatar);
            this.b = (TextView) view.findViewById(R.id.medal_name_and_level);
            this.c = (TextView) view.findViewById(R.id.medal_describe);
            this.d = (TextView) view.findViewById(R.id.medal_progress_text);
            this.e = (ProgressBar) view.findViewById(R.id.medal_progress_bar);
            this.f = view.findViewById(R.id.medal_divider);
        }
    }

    public MedalAdapter(Activity activity, ArrayList<Object> arrayList, ListView listView, boolean z) {
        super(arrayList, activity);
        this.l = listView;
        this.a = z;
    }

    public int getCount() {
        return this.m.size();
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null || !(view.getTag() instanceof a)) {
            view = this.n.inflate(R.layout.medal_item, null);
            a aVar2 = new a(this, view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        Medal medal = (Medal) this.m.get(i);
        Drawable drawable = aVar.a.getResources().getDrawable(a(i));
        aVar.a.setImageDrawable(drawable);
        if (!TextUtils.isEmpty(medal.icon)) {
            a(aVar.a, medal.icon, drawable, drawable);
        }
        view.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        aVar.b.setText(medal.getMedalTitle());
        aVar.b.setTextColor(UIHelper.isNightTheme() ? -8683381 : -12171706);
        aVar.c.setText(medal.describe);
        aVar.c.setTextColor(UIHelper.isNightTheme() ? -12171438 : -4671304);
        if (this.a) {
            aVar.d.setText(medal.getProgressText());
            aVar.d.setTextColor(UIHelper.isNightTheme() ? -12169122 : -4276546);
            aVar.e.setMax(medal.total);
            aVar.e.setProgress(medal.current);
        } else {
            aVar.d.setVisibility(8);
            aVar.e.setVisibility(8);
        }
        aVar.f.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        return view;
    }

    private int a(int i) {
        if (i <= 0) {
            return R.drawable.medal_empty_1;
        }
        switch (i % 6) {
            case 0:
                return R.drawable.medal_empty_1;
            case 1:
                return R.drawable.medal_empty_2;
            case 2:
                return R.drawable.medal_empty_3;
            case 3:
                return R.drawable.medal_empty_4;
            case 4:
                return R.drawable.medal_empty_5;
            case 5:
                return R.drawable.medal_empty_6;
            default:
                return R.drawable.medal_empty_1;
        }
    }
}
