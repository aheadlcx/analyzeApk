package qsbk.app.im.CollectEmotion;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.ArrayList;
import qsbk.app.im.LatestUsedCollectionData;

class g implements OnClickListener {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ e b;

    g(e eVar, ArrayList arrayList) {
        this.b = eVar;
        this.a = arrayList;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Object arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.b.a.b.size(); i2++) {
            if (!this.a.contains(Integer.valueOf(((LatestUsedCollectionData) this.b.a.b.get(i2)).id))) {
                arrayList.add(this.b.a.b.get(i2));
            }
        }
        this.b.a.b.clear();
        this.b.a.b.addAll(arrayList);
        this.b.a.deleteDatas(this.a, this.b.a.d);
        this.b.a.d.clear();
        this.b.a.c.notifyDataSetChanged();
        this.b.a.setResult(-1);
        arrayList.clear();
        this.b.a.g.setText(this.b.a.d.size() + "");
        this.b.a.i.setClickable(false);
        dialogInterface.dismiss();
    }
}
