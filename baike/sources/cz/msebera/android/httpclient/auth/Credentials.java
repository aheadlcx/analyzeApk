package cz.msebera.android.httpclient.auth;

import java.security.Principal;

public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
