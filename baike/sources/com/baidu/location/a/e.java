package com.baidu.location.a;

import android.os.Environment;
import java.io.File;

class e extends Thread {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void run() {
        this.a.a(new File(Environment.getExternalStorageDirectory() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}
