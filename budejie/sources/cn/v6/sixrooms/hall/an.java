package cn.v6.sixrooms.hall;

import android.support.annotation.NonNull;

final class an implements a {
    final /* synthetic */ am a;

    an(am amVar) {
        this.a = amVar;
    }

    public final void a(@NonNull ProvinceNumBean provinceNumBean) {
        if (!this.a.c.getPid().equals(provinceNumBean.getPid())) {
            for (ProvinceNumBean provinceNumBean2 : this.a.a) {
                if (this.a.c.getPid().equals(provinceNumBean2.getPid())) {
                    provinceNumBean2.setSelect(false);
                    this.a.b.notifyItemChanged(provinceNumBean2.getPosition());
                    break;
                }
            }
            for (ProvinceNumBean provinceNumBean22 : this.a.a) {
                if (provinceNumBean.getPid().equals(provinceNumBean22.getPid())) {
                    provinceNumBean22.setSelect(true);
                    this.a.b.notifyItemChanged(provinceNumBean22.getPosition());
                    this.a.c = provinceNumBean22;
                    if (this.a.d != null) {
                        this.a.d.getData(this.a.c);
                    }
                }
            }
        }
        this.a.dismiss();
    }
}
