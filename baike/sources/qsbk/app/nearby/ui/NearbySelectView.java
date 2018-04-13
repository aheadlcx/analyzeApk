package qsbk.app.nearby.ui;

import android.view.View;
import android.widget.Checkable;
import android.widget.RadioGroup;
import qsbk.app.R;

public class NearbySelectView {
    public static final String GENDER_ALL = "ALL";
    public static final String GENDER_FEMALE = "F";
    public static final String GENDER_MALE = "M";
    public static final int TIME_15MIN = 15;
    public static final int TIME_1DAY = 1440;
    public static final int TIME_3DAY = 4320;
    public static final int TIME_60MIN = 60;
    private View a = null;
    private RadioGroup b = null;
    private RadioGroup c = null;

    public NearbySelectView(View view) {
        this.a = view;
        this.b = (RadioGroup) view.findViewById(R.id.id_gender_group);
        this.c = (RadioGroup) view.findViewById(R.id.id_time_group);
    }

    public String getGender() {
        int checkedRadioButtonId = this.b.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.id_female) {
            return "F";
        }
        if (checkedRadioButtonId == R.id.id_male) {
            return "M";
        }
        return GENDER_ALL;
    }

    public void setGender(String str) {
        if ("M".equals(str)) {
            ((Checkable) this.b.findViewById(R.id.id_male)).setChecked(true);
        } else if ("F".equals(str)) {
            ((Checkable) this.b.findViewById(R.id.id_female)).setChecked(true);
        } else {
            ((Checkable) this.b.findViewById(R.id.id_all)).setChecked(true);
        }
    }

    public int getTimeInMinute() {
        int checkedRadioButtonId = this.c.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.id_60min) {
            return 60;
        }
        if (checkedRadioButtonId == R.id.id_1day) {
            return TIME_1DAY;
        }
        if (checkedRadioButtonId == R.id.id_3day) {
            return TIME_3DAY;
        }
        return 15;
    }

    public void setTimeInMinute(int i) {
        if (i == 60) {
            ((Checkable) this.c.findViewById(R.id.id_60min)).setChecked(true);
        } else if (i == TIME_1DAY) {
            ((Checkable) this.c.findViewById(R.id.id_1day)).setChecked(true);
        } else if (i == TIME_3DAY) {
            ((Checkable) this.c.findViewById(R.id.id_3day)).setChecked(true);
        } else {
            ((Checkable) this.c.findViewById(R.id.id_15min)).setChecked(true);
        }
    }
}
