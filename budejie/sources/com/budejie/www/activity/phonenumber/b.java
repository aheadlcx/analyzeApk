package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.c.m;

public class b extends Fragment implements OnClickListener {
    private Activity a;
    private m b;
    private String c;
    private View d;
    private TextView e;
    private Button f;
    private a g;

    public interface a {
        void a();
    }

    public static b a(CharSequence charSequence) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("phone", charSequence);
        bVar.setArguments(bundle);
        return bVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.g = (a) activity;
            this.a = activity;
            this.b = new m(this.a);
            this.c = getArguments().getString("phone");
            return;
        }
        throw new RuntimeException("host activity must implement PhoneChangePressInteface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.change_phone_layout, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.d = getView();
        this.e = (TextView) this.d.findViewById(R.id.phone_num);
        this.e.setText("您的手机号是：" + this.c);
        this.f = (Button) this.d.findViewById(R.id.change_phone);
        this.f.setOnClickListener(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View view) {
        if (R.id.change_phone == view.getId()) {
            this.g.a();
        }
    }
}
