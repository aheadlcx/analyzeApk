package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

class h extends FragmentContainer {
    final /* synthetic */ Fragment a;

    h(Fragment fragment) {
        this.a = fragment;
    }

    @Nullable
    public View onFindViewById(int i) {
        if (this.a.mView != null) {
            return this.a.mView.findViewById(i);
        }
        throw new IllegalStateException("Fragment does not have a view");
    }

    public boolean onHasView() {
        return this.a.mView != null;
    }

    public Fragment instantiate(Context context, String str, Bundle bundle) {
        return this.a.mHost.instantiate(context, str, bundle);
    }
}
