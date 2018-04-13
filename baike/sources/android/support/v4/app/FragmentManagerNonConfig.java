package android.support.v4.app;

import java.util.List;

public class FragmentManagerNonConfig {
    private final List<Fragment> a;
    private final List<FragmentManagerNonConfig> b;

    FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2) {
        this.a = list;
        this.b = list2;
    }

    List<Fragment> a() {
        return this.a;
    }

    List<FragmentManagerNonConfig> b() {
        return this.b;
    }
}
