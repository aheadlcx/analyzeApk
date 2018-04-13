package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

class jr implements OnClickListener {
    final /* synthetic */ CreateNewGroupActivity a;

    jr(CreateNewGroupActivity createNewGroupActivity) {
        this.a = createNewGroupActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        this.a.startActivityForResult(intent, 0);
    }
}
