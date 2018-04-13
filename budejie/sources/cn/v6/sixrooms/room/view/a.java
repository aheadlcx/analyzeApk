package cn.v6.sixrooms.room.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewSwitcher.ViewFactory;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.view.CustomSofaView.InnerClassSofaCrop;
import com.facebook.drawee.view.SimpleDraweeView;

final class a implements ViewFactory {
    final /* synthetic */ CustomSofaView a;
    final /* synthetic */ InnerClassSofaCrop b;

    a(InnerClassSofaCrop innerClassSofaCrop, CustomSofaView customSofaView) {
        this.b = innerClassSofaCrop;
        this.a = customSofaView;
    }

    public final View makeView() {
        return (SimpleDraweeView) LayoutInflater.from(this.b.a.b).inflate(R.layout.demo_layout, null);
    }
}
