package cn.v6.sixrooms.hall;

final class ac implements BaseViewable {
    final /* synthetic */ HostsNearbyFragment a;

    ac(HostsNearbyFragment hostsNearbyFragment) {
        this.a = hostsNearbyFragment;
    }

    public final void getData(Object obj) {
        if (obj instanceof ProvinceNumBean) {
            ProvinceNumBean provinceNumBean = (ProvinceNumBean) obj;
            this.a.f = provinceNumBean.getPid();
            this.a.g.setText(provinceNumBean.getTitle());
            this.a.a(true, false);
        }
    }
}
