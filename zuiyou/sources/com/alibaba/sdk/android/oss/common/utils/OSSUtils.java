package com.alibaba.sdk.android.oss.common.utils;

import android.util.Base64;
import android.util.Pair;
import android.webkit.MimeTypeMap;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.auth.HmacSHA1Signature;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartETag;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class OSSUtils {
    private static final List<String> SIGNED_PARAMTERS = Arrays.asList(new String[]{RequestParameters.SUBRESOURCE_ACL, RequestParameters.SUBRESOURCE_UPLOADS, RequestParameters.SUBRESOURCE_LOCATION, RequestParameters.SUBRESOURCE_CORS, RequestParameters.SUBRESOURCE_LOGGING, RequestParameters.SUBRESOURCE_WEBSITE, RequestParameters.SUBRESOURCE_REFERER, RequestParameters.SUBRESOURCE_LIFECYCLE, RequestParameters.SUBRESOURCE_DELETE, RequestParameters.SUBRESOURCE_APPEND, RequestParameters.UPLOAD_ID, RequestParameters.PART_NUMBER, RequestParameters.SECURITY_TOKEN, RequestParameters.POSITION, RequestParameters.RESPONSE_HEADER_CACHE_CONTROL, RequestParameters.RESPONSE_HEADER_CONTENT_DISPOSITION, RequestParameters.RESPONSE_HEADER_CONTENT_ENCODING, RequestParameters.RESPONSE_HEADER_CONTENT_LANGUAGE, RequestParameters.RESPONSE_HEADER_CONTENT_TYPE, RequestParameters.RESPONSE_HEADER_EXPIRES, RequestParameters.X_OSS_PROCESS});

    private enum MetadataDirective {
        COPY("COPY"),
        REPLACE("REPLACE");
        
        private final String directiveAsString;

        private MetadataDirective(String str) {
            this.directiveAsString = str;
        }

        public String toString() {
            return this.directiveAsString;
        }
    }

    public static void populateRequestMetadata(Map<String, String> map, ObjectMetadata objectMetadata) {
        if (objectMetadata != null) {
            Map rawMetadata = objectMetadata.getRawMetadata();
            if (rawMetadata != null) {
                for (Entry entry : rawMetadata.entrySet()) {
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
            rawMetadata = objectMetadata.getUserMetadata();
            if (rawMetadata != null) {
                for (Entry entry2 : rawMetadata.entrySet()) {
                    Object obj = (String) entry2.getKey();
                    Object obj2 = (String) entry2.getValue();
                    if (obj != null) {
                        obj = obj.trim();
                    }
                    if (obj2 != null) {
                        obj2 = obj2.trim();
                    }
                    map.put(obj, obj2);
                }
            }
        }
    }

    public static void populateListObjectsRequestParameters(ListObjectsRequest listObjectsRequest, Map<String, String> map) {
        if (listObjectsRequest.getPrefix() != null) {
            map.put(RequestParameters.PREFIX, listObjectsRequest.getPrefix());
        }
        if (listObjectsRequest.getMarker() != null) {
            map.put(RequestParameters.MARKER, listObjectsRequest.getMarker());
        }
        if (listObjectsRequest.getDelimiter() != null) {
            map.put(RequestParameters.DELIMITER, listObjectsRequest.getDelimiter());
        }
        if (listObjectsRequest.getMaxKeys() != null) {
            map.put(RequestParameters.MAX_KEYS, Integer.toString(listObjectsRequest.getMaxKeys().intValue()));
        }
        if (listObjectsRequest.getEncodingType() != null) {
            map.put(RequestParameters.ENCODING_TYPE, listObjectsRequest.getEncodingType());
        }
    }

    public static void populateCopyObjectHeaders(CopyObjectRequest copyObjectRequest, Map<String, String> map) {
        map.put(OSSHeaders.COPY_OBJECT_SOURCE, "/" + copyObjectRequest.getSourceBucketName() + "/" + HttpUtil.urlEncode(copyObjectRequest.getSourceKey(), "utf-8"));
        addDateHeader(map, OSSHeaders.COPY_OBJECT_SOURCE_IF_MODIFIED_SINCE, copyObjectRequest.getModifiedSinceConstraint());
        addDateHeader(map, OSSHeaders.COPY_OBJECT_SOURCE_IF_UNMODIFIED_SINCE, copyObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(map, OSSHeaders.COPY_OBJECT_SOURCE_IF_MATCH, copyObjectRequest.getMatchingETagConstraints());
        addStringListHeader(map, OSSHeaders.COPY_OBJECT_SOURCE_IF_NONE_MATCH, copyObjectRequest.getNonmatchingEtagConstraints());
        addHeader(map, OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION, copyObjectRequest.getServerSideEncryption());
        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            map.put(OSSHeaders.COPY_OBJECT_METADATA_DIRECTIVE, MetadataDirective.REPLACE.toString());
            populateRequestMetadata(map, newObjectMetadata);
        }
        removeHeader(map, "Content-Length");
    }

    public static String buildXMLFromPartEtagList(List<PartETag> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<CompleteMultipartUpload>\n");
        for (PartETag partETag : list) {
            stringBuilder.append("<Part>\n");
            stringBuilder.append("<PartNumber>" + partETag.getPartNumber() + "</PartNumber>\n");
            stringBuilder.append("<ETag>" + partETag.getETag() + "</ETag>\n");
            stringBuilder.append("</Part>\n");
        }
        stringBuilder.append("</CompleteMultipartUpload>\n");
        return stringBuilder.toString();
    }

    public static void addHeader(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    public static void addDateHeader(Map<String, String> map, String str, Date date) {
        if (date != null) {
            map.put(str, DateUtil.formatRfc822Date(date));
        }
    }

    public static void addStringListHeader(Map<String, String> map, String str, List<String> list) {
        if (list != null && !list.isEmpty()) {
            map.put(str, join(list));
        }
    }

    public static void removeHeader(Map<String, String> map, String str) {
        if (str != null && map.containsKey(str)) {
            map.remove(str);
        }
    }

    public static String join(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : list) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
            obj = null;
        }
        return stringBuilder.toString();
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    public static String buildCanonicalizedResource(String str, String str2, Map<String, String> map) {
        String str3;
        if (str == null && str2 == null) {
            str3 = "/";
        } else if (str2 == null) {
            str3 = "/" + str + "/";
        } else {
            str3 = "/" + str + "/" + str2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        if (map != null) {
            String[] strArr = (String[]) map.keySet().toArray(new String[map.size()]);
            Arrays.sort(strArr);
            char c = '?';
            for (String str4 : strArr) {
                if (SIGNED_PARAMTERS.contains(str4)) {
                    stringBuilder.append(c);
                    stringBuilder.append(str4);
                    String str5 = (String) map.get(str4);
                    if (!isEmptyString(str5)) {
                        stringBuilder.append("=").append(str5);
                    }
                    c = '&';
                }
            }
        }
        return stringBuilder.toString();
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

    public static String populateMapToBase64JsonString(Map<String, String> map) {
        return Base64.encodeToString(new JSONObject(map).toString().getBytes(), 2);
    }

    public static String sign(String str, String str2, String str3) {
        try {
            return "OSS " + str + ":" + new HmacSHA1Signature().computeSignature(str2, str3).trim();
        } catch (Throwable e) {
            throw new IllegalStateException("Compute signature failed!", e);
        }
    }

    public static boolean isCname(String str) {
        for (String endsWith : OSSConstants.DEFAULT_CNAME_EXCLUDE_LIST) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInCustomCnameExcludeList(String str, List<String> list) {
        for (String toLowerCase : list) {
            if (str.endsWith(toLowerCase.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static void assertTrue(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean validateBucketName(String str) {
        if (str == null) {
            return false;
        }
        String str2 = "^[a-z0-9][a-z0-9_\\-]{2,62}$";
        return str.matches("^[a-z0-9][a-z0-9_\\-]{2,62}$");
    }

    public static void ensureBucketNameValid(String str) {
        if (!validateBucketName(str)) {
            throw new IllegalArgumentException("The bucket name is invalid. \nA bucket name must: \n1) be comprised of lower-case characters, numbers or dash(-); \n2) start with lower case or numbers; \n3) be between 3-63 characters long. ");
        }
    }

    public static boolean validateObjectKey(String str) {
        if (str == null || str.length() <= 0 || str.length() > 1023) {
            return false;
        }
        try {
            str.getBytes("utf-8");
            char[] toCharArray = str.toCharArray();
            char c = toCharArray[0];
            if (c == '/' || c == '\\') {
                return false;
            }
            for (char c2 : toCharArray) {
                if (c2 != '\t' && c2 < ' ') {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    public static void ensureObjectKeyValid(String str) {
        if (!validateObjectKey(str)) {
            throw new IllegalArgumentException("The object key is invalid. \nAn object name should be: \n1) between 1 - 1023 bytes long when encoded as UTF-8 \n2) cannot contain LF or CR or unsupported chars in XML1.0, \n3) cannot begin with \"/\" or \"\\\".");
        }
    }

    public static boolean doesRequestNeedObjectKey(OSSRequest oSSRequest) {
        if ((oSSRequest instanceof ListObjectsRequest) || (oSSRequest instanceof CreateBucketRequest) || (oSSRequest instanceof DeleteBucketRequest) || (oSSRequest instanceof GetBucketACLRequest)) {
            return false;
        }
        return true;
    }

    public static void ensureRequestValid(OSSRequest oSSRequest, RequestMessage requestMessage) {
        ensureBucketNameValid(requestMessage.getBucketName());
        if (doesRequestNeedObjectKey(oSSRequest)) {
            ensureObjectKeyValid(requestMessage.getObjectKey());
        }
        if (oSSRequest instanceof CopyObjectRequest) {
            ensureObjectKeyValid(((CopyObjectRequest) oSSRequest).getDestinationKey());
        }
    }

    public static String determineContentType(String str, String str2, String str3) {
        if (str != null) {
            return str;
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        if (str2 != null) {
            str = singleton.getMimeTypeFromExtension(str2.substring(str2.lastIndexOf(46) + 1));
            if (str != null) {
                return str;
            }
        }
        if (str3 != null) {
            str = singleton.getMimeTypeFromExtension(str3.substring(str3.lastIndexOf(46) + 1));
            if (str != null) {
                return str;
            }
        }
        return OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
    }

    public static void signRequest(RequestMessage requestMessage) throws IOException {
        Pair pair = null;
        if (!requestMessage.isAuthorizationRequired()) {
            return;
        }
        if (requestMessage.getCredentialProvider() == null) {
            throw new IllegalStateException("当前CredentialProvider为空！！！\n1. 请检查您是否在初始化OSSService时设置CredentialProvider;\n2. 如果您bucket为公共权限，请确认获取到Bucket后已经调用Bucket中接口声明ACL;");
        }
        OSSFederationToken oSSFederationToken;
        Object obj;
        Object obj2;
        OSSCredentialProvider credentialProvider = requestMessage.getCredentialProvider();
        OSSFederationToken validFederationToken;
        if (credentialProvider instanceof OSSFederationCredentialProvider) {
            validFederationToken = ((OSSFederationCredentialProvider) credentialProvider).getValidFederationToken();
            if (validFederationToken == null) {
                OSSLog.logE("Can't get a federation token");
                throw new IOException("Can't get a federation token");
            } else {
                requestMessage.getHeaders().put(OSSHeaders.OSS_SECURITY_TOKEN, validFederationToken.getSecurityToken());
                oSSFederationToken = validFederationToken;
            }
        } else if (credentialProvider instanceof OSSStsTokenCredentialProvider) {
            validFederationToken = ((OSSStsTokenCredentialProvider) credentialProvider).getFederationToken();
            requestMessage.getHeaders().put(OSSHeaders.OSS_SECURITY_TOKEN, validFederationToken.getSecurityToken());
            oSSFederationToken = validFederationToken;
        } else {
            oSSFederationToken = null;
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
            if (str4.toLowerCase().startsWith(OSSHeaders.OSS_PREFIX)) {
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
        String buildCanonicalizedResource = buildCanonicalizedResource(requestMessage.getBucketName(), requestMessage.getObjectKey(), requestMessage.getParameters());
        str42 = String.format("%s\n%s\n%s\n%s\n%s%s", new Object[]{httpMethod, obj, obj2, str, str42, buildCanonicalizedResource});
        str = "---initValue---";
        if ((credentialProvider instanceof OSSFederationCredentialProvider) || (credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            str = sign(oSSFederationToken.getTempAK(), oSSFederationToken.getTempSK(), str42);
        } else if (credentialProvider instanceof OSSPlainTextAKSKCredentialProvider) {
            str = sign(((OSSPlainTextAKSKCredentialProvider) credentialProvider).getAccessKeyId(), ((OSSPlainTextAKSKCredentialProvider) credentialProvider).getAccessKeySecret(), str42);
        } else if (credentialProvider instanceof OSSCustomSignerCredentialProvider) {
            str = ((OSSCustomSignerCredentialProvider) credentialProvider).signContent(str42);
        }
        OSSLog.logD("signed content: " + str42 + "   \n ---------   signature: " + str);
        requestMessage.getHeaders().put("Authorization", str);
    }
}
