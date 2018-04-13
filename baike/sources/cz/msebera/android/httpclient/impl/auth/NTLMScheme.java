package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.InvalidCredentialsException;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.auth.NTCredentials;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

@NotThreadSafe
public class NTLMScheme extends AuthSchemeBase {
    private final NTLMEngine a;
    private a b;
    private String c;

    enum a {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme(NTLMEngine nTLMEngine) {
        Args.notNull(nTLMEngine, "NTLM engine");
        this.a = nTLMEngine;
        this.b = a.UNINITIATED;
        this.c = null;
    }

    public NTLMScheme() {
        this(new NTLMEngineImpl());
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public String getParameter(String str) {
        return null;
    }

    public String getRealm() {
        return null;
    }

    public boolean isConnectionBased() {
        return true;
    }

    protected void a(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        this.c = charArrayBuffer.substringTrimmed(i, i2);
        if (this.c.isEmpty()) {
            if (this.b == a.UNINITIATED) {
                this.b = a.CHALLENGE_RECEIVED;
            } else {
                this.b = a.FAILED;
            }
        } else if (this.b.compareTo(a.MSG_TYPE1_GENERATED) < 0) {
            this.b = a.FAILED;
            throw new MalformedChallengeException("Out of sequence NTLM response message");
        } else if (this.b == a.MSG_TYPE1_GENERATED) {
            this.b = a.MSG_TYPE2_RECEVIED;
        }
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        try {
            NTCredentials nTCredentials = (NTCredentials) credentials;
            if (this.b == a.FAILED) {
                throw new AuthenticationException("NTLM authentication failed");
            }
            String generateType1Msg;
            if (this.b == a.CHALLENGE_RECEIVED) {
                generateType1Msg = this.a.generateType1Msg(nTCredentials.getDomain(), nTCredentials.getWorkstation());
                this.b = a.MSG_TYPE1_GENERATED;
            } else if (this.b == a.MSG_TYPE2_RECEVIED) {
                generateType1Msg = this.a.generateType3Msg(nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getDomain(), nTCredentials.getWorkstation(), this.c);
                this.b = a.MSG_TYPE3_GENERATED;
            } else {
                throw new AuthenticationException("Unexpected state: " + this.b);
            }
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            if (isProxy()) {
                charArrayBuffer.append("Proxy-Authorization");
            } else {
                charArrayBuffer.append("Authorization");
            }
            charArrayBuffer.append(": NTLM ");
            charArrayBuffer.append(generateType1Msg);
            return new BufferedHeader(charArrayBuffer);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    public boolean isComplete() {
        return this.b == a.MSG_TYPE3_GENERATED || this.b == a.FAILED;
    }
}
