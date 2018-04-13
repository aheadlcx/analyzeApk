package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NTLMScheme implements AuthScheme {
    private static final int FAILED = Integer.MAX_VALUE;
    private static final int INITIATED = 1;
    private static final Log LOG;
    private static final int TYPE1_MSG_GENERATED = 2;
    private static final int TYPE2_MSG_RECEIVED = 3;
    private static final int TYPE3_MSG_GENERATED = 4;
    private static final int UNINITIATED = 0;
    static Class class$org$apache$commons$httpclient$auth$NTLMScheme;
    private String ntlmchallenge;
    private int state;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$auth$NTLMScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.NTLMScheme");
            class$org$apache$commons$httpclient$auth$NTLMScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$NTLMScheme;
        }
        LOG = LogFactory.getLog(class$);
    }

    public NTLMScheme() {
        this.ntlmchallenge = null;
        this.state = 0;
    }

    public NTLMScheme(String str) throws MalformedChallengeException {
        this.ntlmchallenge = null;
        processChallenge(str);
    }

    public void processChallenge(String str) throws MalformedChallengeException {
        if (AuthChallengeParser.extractScheme(str).equalsIgnoreCase(getSchemeName())) {
            int indexOf = str.indexOf(32);
            if (indexOf != -1) {
                this.ntlmchallenge = str.substring(indexOf, str.length()).trim();
                this.state = 3;
                return;
            }
            this.ntlmchallenge = "";
            if (this.state == 0) {
                this.state = 1;
                return;
            } else {
                this.state = Integer.MAX_VALUE;
                return;
            }
        }
        throw new MalformedChallengeException(new StringBuffer().append("Invalid NTLM challenge: ").append(str).toString());
    }

    public boolean isComplete() {
        return this.state == 4 || this.state == Integer.MAX_VALUE;
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public String getRealm() {
        return null;
    }

    public String getID() {
        return this.ntlmchallenge;
    }

    public String getParameter(String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("Parameter name may not be null");
    }

    public boolean isConnectionBased() {
        return true;
    }

    public static String authenticate(NTCredentials nTCredentials, String str) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
        if (nTCredentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        return new StringBuffer().append("NTLM ").append(new NTLM().getResponseFor(str, nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getHost(), nTCredentials.getDomain())).toString();
    }

    public static String authenticate(NTCredentials nTCredentials, String str, String str2) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
        if (nTCredentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        NTLM ntlm = new NTLM();
        ntlm.setCredentialCharset(str2);
        return new StringBuffer().append("NTLM ").append(ntlm.getResponseFor(str, nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getHost(), nTCredentials.getDomain())).toString();
    }

    public String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(Credentials, String, String)");
        try {
            return authenticate((NTCredentials) credentials, this.ntlmchallenge);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for NTLM authentication: ").append(credentials.getClass().getName()).toString());
        }
    }

    public String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException {
        LOG.trace("enter NTLMScheme.authenticate(Credentials, HttpMethod)");
        if (this.state == 0) {
            throw new IllegalStateException("NTLM authentication process has not been initiated");
        }
        try {
            String type1Message;
            NTCredentials nTCredentials = (NTCredentials) credentials;
            NTLM ntlm = new NTLM();
            ntlm.setCredentialCharset(httpMethod.getParams().getCredentialCharset());
            if (this.state == 1 || this.state == Integer.MAX_VALUE) {
                type1Message = ntlm.getType1Message(nTCredentials.getHost(), nTCredentials.getDomain());
                this.state = 2;
            } else {
                type1Message = ntlm.getType3Message(nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getHost(), nTCredentials.getDomain(), ntlm.parseType2Message(this.ntlmchallenge));
                this.state = 4;
            }
            return new StringBuffer().append("NTLM ").append(type1Message).toString();
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for NTLM authentication: ").append(credentials.getClass().getName()).toString());
        }
    }
}
