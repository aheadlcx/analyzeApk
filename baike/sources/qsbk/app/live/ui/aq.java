package qsbk.app.live.ui;

import qsbk.app.core.model.User;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveMessageBase;

class aq extends Builder {
    final /* synthetic */ int a;
    final /* synthetic */ User b;
    final /* synthetic */ LiveBaseActivity c;

    aq(LiveBaseActivity liveBaseActivity, int i, int i2, User user) {
        this.c = liveBaseActivity;
        this.a = i2;
        this.b = user;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        LiveMessageBase createSilentMessage;
        super.onPositiveActionClicked(dialogFragment);
        if (this.a == 1) {
            createSilentMessage = LiveMessage.createSilentMessage(this.c.ax.getOriginId(), this.b.getOrigin(), this.b.getOriginId());
        } else {
            createSilentMessage = LiveMessage.createSilentCancelMessage(this.c.ax.getOriginId(), this.b.getOrigin(), this.b.getOriginId());
        }
        this.c.sendLiveMessageAndRefreshUI(createSilentMessage);
    }

    public void onNegativeActionClicked(DialogFragment dialogFragment) {
        super.onNegativeActionClicked(dialogFragment);
    }
}
