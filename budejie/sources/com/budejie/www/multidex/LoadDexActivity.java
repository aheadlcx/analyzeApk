package com.budejie.www.multidex;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.util.aa;

public class LoadDexActivity extends Activity {

    class a extends AsyncTask {
        final /* synthetic */ LoadDexActivity a;

        a(LoadDexActivity loadDexActivity) {
            this.a = loadDexActivity;
        }

        protected Object doInBackground(Object[] objArr) {
            try {
                MultiDex.install(this.a.getApplication());
                aa.b("loadDex", "install finish");
                ((BudejieApplication) this.a.getApplication()).c(this.a.getApplication());
            } catch (Exception e) {
                aa.e("loadDex", e.getLocalizedMessage());
            }
            return null;
        }

        protected void onPostExecute(Object obj) {
            aa.b("loadDex", "get install finish");
            this.a.finish();
            System.exit(0);
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splashscreen);
        new a(this).execute(new Object[0]);
    }

    public void onBackPressed() {
    }
}
