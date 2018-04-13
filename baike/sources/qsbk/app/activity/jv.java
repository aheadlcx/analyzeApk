package qsbk.app.activity;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import qsbk.app.R;

class jv implements OnCheckedChangeListener {
    final /* synthetic */ EditGenderActivity a;

    jv(EditGenderActivity editGenderActivity) {
        this.a = editGenderActivity;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i != this.a.e) {
            this.a.e = i;
            if (this.a.e == R.id.gender_female) {
                this.a.d = "F";
            } else if (this.a.e == R.id.gender_male) {
                this.a.d = "M";
            }
            this.a.a();
        }
    }
}
