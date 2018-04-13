package com.alibaba.sdk.android.mns.common;

import android.util.Pair;
import com.alibaba.sdk.android.common.auth.CredentialProvider;
import com.alibaba.sdk.android.common.auth.CustomSignerCredentialProvider;
import com.alibaba.sdk.android.common.auth.FederationCredentialProvider;
import com.alibaba.sdk.android.common.auth.FederationToken;
import com.alibaba.sdk.android.common.auth.HmacSHA1Signature;
import com.alibaba.sdk.android.common.auth.PlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.common.auth.StsTokenCredentialProvider;
import com.alibaba.sdk.android.common.utils.HttpUtil;
import com.alibaba.sdk.android.mns.internal.RequestMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MNSUtils {
    public static void assertTrue(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    public static String paramToQueryString(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (obj == null) {
                stringBuilder.append("&");
            }
            stringBuilder.append(HttpUtil.urlEncode(str2, str));
            if (!isEmptyString(str3)) {
                stringBuilder.append("=").append(HttpUtil.urlEncode(str3, str));
            }
            obj = null;
        }
        return stringBuilder.toString();
    }

    public static void signRequest(RequestMessage requestMessage) throws IOException {
        Pair pair = null;
        if (requestMessage.getCredentialProvider() == null) {
            throw new IllegalStateException("当前CredentialProvider为空！！！");
        }
        FederationToken federationToken;
        Object obj;
        Object obj2;
        CredentialProvider credentialProvider = requestMessage.getCredentialProvider();
        FederationToken validFederationToken;
        if (credentialProvider instanceof FederationCredentialProvider) {
            validFederationToken = ((FederationCredentialProvider) credentialProvider).getValidFederationToken();
            if (validFederationToken == null) {
                MNSLog.logE("Can't get a federation token");
                throw new IOException("Can't get a federation token");
            } else {
                requestMessage.getHeaders().put(MNSHeaders.MNS_SECURITY_TOKEN, validFederationToken.getSecurityToken());
                federationToken = validFederationToken;
            }
        } else if (credentialProvider instanceof StsTokenCredentialProvider) {
            validFederationToken = ((StsTokenCredentialProvider) credentialProvider).getFederationToken();
            requestMessage.getHeaders().put(MNSHeaders.MNS_SECURITY_TOKEN, validFederationToken.getSecurityToken());
            federationToken = validFederationToken;
        } else {
            federationToken = null;
        }
        String httpMethod = requestMessage.getMethod().toString();
        String str = (String) requestMessage.getHeaders().get("Content-MD5");
        if (str == null) {
            obj = "";
        } else {
            String str2 = str;
        }
        str = (String) requestMessage.getHeaders().get("Content-Type");
        if (str == null) {
            obj2 = "";
        } else {
            String str3 = str;
        }
        str = (String) requestMessage.getHeaders().get("Date");
        List<Pair> arrayList = new ArrayList();
        for (String str4 : requestMessage.getHeaders().keySet()) {
            if (str4.toLowerCase().startsWith(MNSHeaders.MNS_PREFIX)) {
                arrayList.add(new Pair(str4.toLowerCase(), requestMessage.getHeaders().get(str4)));
            }
        }
        Collections.sort(arrayList, new Comparator<Pair<String, String>>() {
            public int compare(Pair<String, String> pair, Pair<String, String> pair2) {
                return ((String) pair.first).compareTo((String) pair2.first);
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair pair2 : arrayList) {
            if (pair == null) {
                stringBuilder.append(((String) pair2.first) + ":" + ((String) pair2.second));
            } else if (((String) pair.first).equals(pair2.first)) {
                stringBuilder.append("," + ((String) pair2.second));
            } else {
                stringBuilder.append("\n" + ((String) pair2.first) + ":" + ((String) pair2.second));
            }
            pair = pair2;
        }
        String str42 = stringBuilder.toString();
        if (!isEmptyString(str42)) {
            str42 = str42.trim() + "\n";
        }
        String resourcePath = requestMessage.getResourcePath();
        str42 = String.format("%s\n%s\n%s\n%s\n%s%s", new Object[]{httpMethod, obj, obj2, str, str42, resourcePath});
        MNSLog.logI(str42);
        str = "---initValue---";
        if ((credentialProvider instanceof FederationCredentialProvider) || (credentialProvider instanceof StsTokenCredentialProvider)) {
            str = sign(federationToken.getTempAK(), federationToken.getTempSK(), str42);
        } else if (credentialProvider instanceof PlainTextAKSKCredentialProvider) {
            str = sign(((PlainTextAKSKCredentialProvider) credentialProvider).getAccessKeyId(), ((PlainTextAKSKCredentialProvider) credentialProvider).getAccessKeySecret(), str42);
        } else if (credentialProvider instanceof CustomSignerCredentialProvider) {
            str = ((CustomSignerCredentialProvider) credentialProvider).signContent(str42);
        }
        MNSLog.logD("signed content: " + str42.replaceAll("\n", "@") + "   ---------   signature: " + str);
        requestMessage.getHeaders().put("Authorization", str);
    }

    public static String sign(String str, String str2, String str3) {
        try {
            return "MNS " + str + ":" + new HmacSHA1Signature().computeSignature(str2, str3).trim();
        } catch (Throwable e) {
            throw new IllegalStateException("Compute signature failed!", e);
        }
    }
}
