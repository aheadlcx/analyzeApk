package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class ba implements OnClickListener {
    final /* synthetic */ ay a;

    ba(ay ayVar) {
        this.a = ayVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent(this.a.a, QiuYouActivity.class);
        intent.putExtra("manage_my_group", true);
        this.a.a.startActivity(intent);
    }
}
