package com.budejie.www.util;

import android.app.Activity;
import android.widget.Toast;
import cn.v6.sixrooms.room.RoomActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.f.a;

class m$1 implements a {
    final /* synthetic */ Activity a;

    m$1(Activity activity) {
        this.a = activity;
    }

    public void a(int i, String str) {
        if ("1".equals(str)) {
            Toast.makeText(this.a, "举报成功", 0).show();
            if (this.a instanceof AuditPostsActivity) {
                TipPopUp.a(this.a, TypeControl.shenhe);
            } else {
                TipPopUp.a(this.a, TypeControl.jubao, TypeControl.jubao);
            }
        } else if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(str)) {
            try {
                throw new Exception("error:举报参数form不正确");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.a, "已举报", 0).show();
            }
        } else if ("0".equals(str)) {
            Toast.makeText(this.a, "已举报", 0).show();
        }
    }

    public void a(int i) {
    }
}
