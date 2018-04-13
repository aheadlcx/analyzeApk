package qsbk.app.thirdparty;

import android.os.Bundle;

public interface ThirdPartyAuthListener {
    void onCancel();

    void onComplete(Bundle bundle);

    void onError(ThirdPartyDialogError thirdPartyDialogError);

    void onThirdPartyException(ThirdPartyException thirdPartyException);
}
