package qsbk.app.thirdparty.net;

import java.io.IOException;
import qsbk.app.thirdparty.ThirdPartyException;

public interface RequestListener {
    void onComplete(String str);

    void onError(ThirdPartyException thirdPartyException);

    void onIOException(IOException iOException);
}
