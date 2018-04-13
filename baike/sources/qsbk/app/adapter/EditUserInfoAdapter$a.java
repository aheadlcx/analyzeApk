package qsbk.app.adapter;

import android.view.View;
import android.widget.TextView;
import qsbk.app.R;

class EditUserInfoAdapter$a {
    private TextView a;
    private TextView b;

    private EditUserInfoAdapter$a() {
    }

    static EditUserInfoAdapter$a a(View view) {
        if (view.getTag() != null && (view.getTag() instanceof EditUserInfoAdapter$a)) {
            return (EditUserInfoAdapter$a) view.getTag();
        }
        EditUserInfoAdapter$a editUserInfoAdapter$a = new EditUserInfoAdapter$a();
        editUserInfoAdapter$a.a = (TextView) view.findViewById(R.id.description);
        editUserInfoAdapter$a.b = (TextView) view.findViewById(R.id.value);
        return editUserInfoAdapter$a;
    }
}
