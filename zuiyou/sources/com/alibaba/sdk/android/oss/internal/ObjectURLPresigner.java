package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import java.net.URI;

public class ObjectURLPresigner {
    private ClientConfiguration conf;
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;

    public ObjectURLPresigner(URI uri, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.endpoint = uri;
        this.credentialProvider = oSSCredentialProvider;
        this.conf = clientConfiguration;
    }

    public String presignConstrainedURL(String str, String str2, long j) throws ClientException {
        String str3;
        OSSFederationToken oSSFederationToken;
        String str4 = "/" + str + "/" + str2;
        String valueOf = String.valueOf((DateUtil.getFixedSkewedTimeMillis() / 1000) + j);
        OSSFederationToken validFederationToken;
        OSSFederationToken oSSFederationToken2;
        if (this.credentialProvider instanceof OSSFederationCredentialProvider) {
            validFederationToken = ((OSSFederationCredentialProvider) this.credentialProvider).getValidFederationToken();
            if (validFederationToken == null) {
                throw new ClientException("Can not get a federation token!");
            }
            oSSFederationToken2 = validFederationToken;
            str3 = str4 + "?security-token=" + validFederationToken.getSecurityToken();
            oSSFederationToken = oSSFederationToken2;
        } else if (this.credentialProvider instanceof OSSStsTokenCredentialProvider) {
            validFederationToken = ((OSSStsTokenCredentialProvider) this.credentialProvider).getFederationToken();
            oSSFederationToken2 = validFederationToken;
            str3 = str4 + "?security-token=" + validFederationToken.getSecurityToken();
            oSSFederationToken = oSSFederationToken2;
        } else {
            str3 = str4;
            oSSFederationToken = null;
        }
        String str5 = "GET\n\n\n" + valueOf + "\n" + str3;
        str3 = "";
        if ((this.credentialProvider instanceof OSSFederationCredentialProvider) || (this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            str3 = OSSUtils.sign(oSSFederationToken.getTempAK(), oSSFederationToken.getTempSK(), str5);
        } else if (this.credentialProvider instanceof OSSPlainTextAKSKCredentialProvider) {
            str3 = OSSUtils.sign(((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeyId(), ((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeySecret(), str5);
        } else if (this.credentialProvider instanceof OSSCustomSignerCredentialProvider) {
            str3 = ((OSSCustomSignerCredentialProvider) this.credentialProvider).signContent(str5);
        } else {
            throw new ClientException("Unknown credentialProvider!");
        }
        str5 = str3.split(":")[0].substring(4);
        String str6 = str3.split(":")[1];
        str3 = this.endpoint.getHost();
        if (!OSSUtils.isCname(str3) || OSSUtils.isInCustomCnameExcludeList(str3, this.conf.getCustomCnameExcludeList())) {
            str3 = str + "." + str3;
        }
        str3 = this.endpoint.getScheme() + "://" + str3 + "/" + HttpUtil.urlEncode(str2, "utf-8") + "?OSSAccessKeyId=" + HttpUtil.urlEncode(str5, "utf-8") + "&Expires=" + valueOf + "&Signature=" + HttpUtil.urlEncode(str6, "utf-8");
        if ((this.credentialProvider instanceof OSSFederationCredentialProvider) || (this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            return str3 + "&security-token=" + HttpUtil.urlEncode(oSSFederationToken.getSecurityToken(), "utf-8");
        }
        return str3;
    }

    public String presignPublicURL(String str, String str2) {
        String host = this.endpoint.getHost();
        if (!OSSUtils.isCname(host) || OSSUtils.isInCustomCnameExcludeList(host, this.conf.getCustomCnameExcludeList())) {
            host = str + "." + host;
        }
        return this.endpoint.getScheme() + "://" + host + "/" + HttpUtil.urlEncode(str2, "utf-8");
    }
}
