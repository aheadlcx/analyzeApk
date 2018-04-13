package com.bdj.picture.edit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.f;
import com.bdj.picture.edit.util.i;

public class MainActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(e.activity_main);
        ImageView imageView = (ImageView) findViewById(d.image_view);
        Log.d("Environment", "Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
        imageView.setImageBitmap(i.b(this, Environment.getExternalStorageDirectory() + "/a.jpg"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(f.main, menu);
        return true;
    }
}
