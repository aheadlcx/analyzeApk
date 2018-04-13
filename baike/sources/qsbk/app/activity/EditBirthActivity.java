package qsbk.app.activity;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.DatePickerDialogFragment;

public class EditBirthActivity extends EditInfoBaseActivity implements OnDateSetListener {
    private static final String c = EditBirthActivity.class.getSimpleName();
    private DatePickerDialogFragment d;
    private long e;
    private ListView f;
    private ArrayAdapter<String> g;
    private String[] h = new String[2];

    public int getLayout() {
        return R.layout.layout_edit_birth;
    }

    public void init() {
        this.f = (ListView) findViewById(R.id.listview);
        this.f.setOnItemClickListener(new ju(this));
    }

    private void a() {
        if (this.d == null) {
            this.d = new DatePickerDialogFragment();
            this.d.setMaxDate(new Date().getTime());
            this.d.setDateSetListener(this);
            this.d.setInitialTime(this.e);
        }
        this.d.show(getSupportFragmentManager(), c);
    }

    public void handleIntent(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.e = serializableExtra != null ? ((Long) serializableExtra).longValue() : 0;
        if (this.e == 0) {
            this.e = -1;
        }
        Calendar instance = Calendar.getInstance();
        if (this.e != -1) {
            this.e *= 1000;
            instance.setTime(new Date(this.e));
        }
        this.h[1] = "星座：" + AstrologyUtils.date2Astrology(instance);
        this.h[0] = "年龄：" + a(AstrologyUtils.getAge(instance));
        this.g = new ArrayAdapter(this, R.layout.simple_list_item_1, this.h);
        this.f.setAdapter(this.g);
    }

    private String a(int i) {
        int max = Math.max(i, 0);
        return String.format(getResources().getString(R.string.age), new Object[]{Integer.valueOf(max)});
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("birthday", Long.valueOf(this.e / 1000));
        return hashMap;
    }

    public String getPostUrl() {
        return String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public void onCancel(View view) {
        b("Edit birth cancel.");
    }

    public boolean onSure(View view) {
        return true;
    }

    public Intent getResultData() {
        Intent intent = new Intent();
        intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.e / 1000);
        return intent;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        int age = AstrologyUtils.getAge(instance);
        if (age < 12 || age > 99) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.age_invalid)).show();
            return;
        }
        this.e = instance.getTimeInMillis();
        this.d.setInitialTime(this.e);
        this.h[0] = "年龄：" + a(AstrologyUtils.getAge(instance));
        this.h[1] = "星座：" + AstrologyUtils.date2Astrology(instance);
        this.g.notifyDataSetChanged();
    }
}
