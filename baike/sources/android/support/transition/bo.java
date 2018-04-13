package android.support.transition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
class bo extends bw implements bq {
    bo(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    static bo a(ViewGroup viewGroup) {
        return (bo) bw.b(viewGroup);
    }

    public void add(@NonNull View view) {
        this.a.add(view);
    }

    public void remove(@NonNull View view) {
        this.a.remove(view);
    }
}
