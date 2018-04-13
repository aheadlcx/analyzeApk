package okhttp3;

import java.io.IOException;

public interface Interceptor {
    Response intercept(Interceptor$Chain interceptor$Chain) throws IOException;
}
