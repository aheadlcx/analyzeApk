package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.widgets.phone.ExpressionGroup.OnItemClickListener;
import java.util.List;

final class f implements OnItemClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ List b;
    final /* synthetic */ ExpressionKeyboard c;

    f(ExpressionKeyboard expressionKeyboard, int i, List list) {
        this.c = expressionKeyboard;
        this.a = i;
        this.b = list;
    }

    public final void onItemClick(int i) {
        if (this.a == 1) {
            if (LoginUtils.getLoginUserBean() == null) {
                if (this.c.b instanceof BaseFragmentActivity) {
                    ((BaseFragmentActivity) this.c.b).showLoginDialog();
                    return;
                } else if (this.c.t != null) {
                    this.c.t.showLoginDialog();
                    return;
                } else {
                    return;
                }
            } else if (this.c.u != null) {
                if (!(this.c.u[0] == 1 || this.c.u[1] == 1)) {
                    if (this.c.s != null) {
                        this.c.s.onPermissionDeny(this.a);
                        return;
                    }
                    return;
                }
            } else if (this.c.s != null) {
                this.c.s.onPermissionInvalid();
                return;
            } else {
                return;
            }
        } else if (this.a == 2) {
            if (LoginUtils.getLoginUserBean() == null) {
                if (this.c.b instanceof BaseFragmentActivity) {
                    ((BaseFragmentActivity) this.c.b).showLoginDialog();
                    return;
                } else if (this.c.t != null) {
                    this.c.t.showLoginDialog();
                    return;
                } else {
                    return;
                }
            } else if (this.c.v == null) {
                if (this.c.s != null) {
                    this.c.s.onPermissionInvalid();
                    return;
                }
                return;
            } else if (!(this.c.v[0] == 1 || this.c.v[1] == 1 || this.c.v[2] == 1)) {
                if (this.c.s != null) {
                    this.c.s.onPermissionDeny(this.a);
                    return;
                }
                return;
            }
        }
        if (this.c.h != null) {
            this.c.h.selectedSmileyVo((SmileyVo) this.b.get(i));
        }
    }
}
