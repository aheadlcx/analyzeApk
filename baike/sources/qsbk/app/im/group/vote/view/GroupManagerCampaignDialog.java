package qsbk.app.im.group.vote.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import qsbk.app.R;

public class GroupManagerCampaignDialog extends AlertDialog {
    public GroupManagerCampaignDialog(Context context) {
        super(context);
    }

    public GroupManagerCampaignDialog(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_group_manager_campaign);
    }
}
