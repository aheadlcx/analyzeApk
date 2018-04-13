package qsbk.app.live.ui.helper;

import qsbk.app.core.model.User;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.model.LiveGlobalRedEnvelopesMessage;

public interface LiveMessageListener {
    boolean isMessageOverload();

    boolean isMessageOverloadOrLowDevice();

    void onAnimAvatarClick(User user);

    void onFamilyEnterEffect(LiveEnterMessage liveEnterMessage);

    void onGlobalBarrageClicked(LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage);

    void onShowSmallGiftAnim(LiveGiftMessage liveGiftMessage);

    void onShowSpecialAnimWhenMeetACertainNumber(int i, int i2, long j);
}
