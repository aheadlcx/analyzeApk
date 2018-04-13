package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

@RequiresApi(16)
@RestrictTo({Scope.LIBRARY_GROUP})
class i extends MenuItemWrapperICS {

    class a extends a implements VisibilityListener {
        ActionProvider.VisibilityListener c;
        final /* synthetic */ i d;

        public a(i iVar, Context context, android.view.ActionProvider actionProvider) {
            this.d = iVar;
            super(iVar, context, actionProvider);
        }

        public View onCreateActionView(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        public boolean overridesItemVisibility() {
            return this.a.overridesItemVisibility();
        }

        public boolean isVisible() {
            return this.a.isVisible();
        }

        public void refreshVisibility() {
            this.a.refreshVisibility();
        }

        public void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
            VisibilityListener visibilityListener2;
            this.c = visibilityListener;
            android.view.ActionProvider actionProvider = this.a;
            if (visibilityListener == null) {
                visibilityListener2 = null;
            }
            actionProvider.setVisibilityListener(visibilityListener2);
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            if (this.c != null) {
                this.c.onActionProviderVisibilityChanged(z);
            }
        }
    }

    i(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    a a(android.view.ActionProvider actionProvider) {
        return new a(this, this.a, actionProvider);
    }
}
