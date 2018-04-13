package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import okhttp3.aa;
import okhttp3.s;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class ResponseParsers {
    public static final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    public static final class AbortMultipartUploadResponseParser implements ResponseParser<AbortMultipartUploadResult> {
        public AbortMultipartUploadResult parse(aa aaVar) throws IOException {
            try {
                AbortMultipartUploadResult abortMultipartUploadResult = new AbortMultipartUploadResult();
                abortMultipartUploadResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                abortMultipartUploadResult.setStatusCode(aaVar.b());
                abortMultipartUploadResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                return abortMultipartUploadResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class AppendObjectResponseParser implements ResponseParser<AppendObjectResult> {
        public AppendObjectResult parse(aa aaVar) throws IOException {
            try {
                AppendObjectResult appendObjectResult = new AppendObjectResult();
                appendObjectResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                appendObjectResult.setStatusCode(aaVar.b());
                appendObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                String a = aaVar.a(OSSHeaders.OSS_NEXT_APPEND_POSITION);
                if (a != null) {
                    appendObjectResult.setNextPosition(Long.valueOf(a));
                }
                appendObjectResult.setObjectCRC64(aaVar.a(OSSHeaders.OSS_HASH_CRC64_ECMA));
                return appendObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class CompleteMultipartUploadResponseParser implements ResponseParser<CompleteMultipartUploadResult> {
        public CompleteMultipartUploadResult parse(aa aaVar) throws IOException {
            try {
                CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
                if (aaVar.a("Content-Type").equals("application/xml")) {
                    completeMultipartUploadResult = ResponseParsers.parseCompleteMultipartUploadResponseXML(aaVar.g().byteStream());
                } else if (aaVar.g() != null) {
                    completeMultipartUploadResult.setServerCallbackReturnBody(aaVar.g().string());
                }
                completeMultipartUploadResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                completeMultipartUploadResult.setStatusCode(aaVar.b());
                completeMultipartUploadResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return completeMultipartUploadResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class CopyObjectResponseParser implements ResponseParser<CopyObjectResult> {
        public CopyObjectResult parse(aa aaVar) throws IOException {
            try {
                CopyObjectResult access$000 = ResponseParsers.parseCopyObjectResponseXML(aaVar.g().byteStream());
                access$000.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                access$000.setStatusCode(aaVar.b());
                access$000.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return access$000;
            } catch (Throwable e) {
                e.printStackTrace();
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class CreateBucketResponseParser implements ResponseParser<CreateBucketResult> {
        public CreateBucketResult parse(aa aaVar) throws IOException {
            try {
                CreateBucketResult createBucketResult = new CreateBucketResult();
                createBucketResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                createBucketResult.setStatusCode(aaVar.b());
                createBucketResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return createBucketResult;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class DeleteBucketResponseParser implements ResponseParser<DeleteBucketResult> {
        public DeleteBucketResult parse(aa aaVar) throws IOException {
            try {
                DeleteBucketResult deleteBucketResult = new DeleteBucketResult();
                deleteBucketResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                deleteBucketResult.setStatusCode(aaVar.b());
                deleteBucketResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                return deleteBucketResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class DeleteObjectResponseParser implements ResponseParser<DeleteObjectResult> {
        public DeleteObjectResult parse(aa aaVar) throws IOException {
            DeleteObjectResult deleteObjectResult = new DeleteObjectResult();
            try {
                deleteObjectResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                deleteObjectResult.setStatusCode(aaVar.b());
                deleteObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                return deleteObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class GetBucketACLResponseParser implements ResponseParser<GetBucketACLResult> {
        public GetBucketACLResult parse(aa aaVar) throws IOException {
            try {
                GetBucketACLResult access$100 = ResponseParsers.parseGetBucketACLResponse(aaVar.g().byteStream());
                access$100.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                access$100.setStatusCode(aaVar.b());
                access$100.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return access$100;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class GetObjectResponseParser implements ResponseParser<GetObjectResult> {
        public GetObjectResult parse(aa aaVar) throws IOException {
            GetObjectResult getObjectResult = new GetObjectResult();
            getObjectResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
            getObjectResult.setStatusCode(aaVar.b());
            getObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
            getObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(getObjectResult.getResponseHeader()));
            getObjectResult.setContentLength(aaVar.g().contentLength());
            getObjectResult.setObjectContent(aaVar.g().byteStream());
            return getObjectResult;
        }
    }

    public static final class HeadObjectResponseParser implements ResponseParser<HeadObjectResult> {
        public HeadObjectResult parse(aa aaVar) throws IOException {
            HeadObjectResult headObjectResult = new HeadObjectResult();
            try {
                headObjectResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                headObjectResult.setStatusCode(aaVar.b());
                headObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                headObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(headObjectResult.getResponseHeader()));
                return headObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class InitMultipartResponseParser implements ResponseParser<InitiateMultipartUploadResult> {
        public InitiateMultipartUploadResult parse(aa aaVar) throws IOException {
            try {
                InitiateMultipartUploadResult access$300 = ResponseParsers.parseInitMultipartResponseXML(aaVar.g().byteStream());
                access$300.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                access$300.setStatusCode(aaVar.b());
                access$300.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return access$300;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class ListObjectsResponseParser implements ResponseParser<ListObjectsResult> {
        public ListObjectsResult parse(aa aaVar) throws IOException {
            try {
                ListObjectsResult access$200 = ResponseParsers.parseObjectListResponse(aaVar.g().byteStream());
                access$200.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                access$200.setStatusCode(aaVar.b());
                access$200.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return access$200;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class ListPartsResponseParser implements ResponseParser<ListPartsResult> {
        public ListPartsResult parse(aa aaVar) throws IOException {
            try {
                ListPartsResult access$500 = ResponseParsers.parseListPartsResponseXML(aaVar.g().byteStream());
                access$500.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                access$500.setStatusCode(aaVar.b());
                access$500.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                ResponseParsers.safeCloseResponse(aaVar);
                return access$500;
            } catch (Throwable e) {
                throw new IOException(e.getMessage(), e);
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class PutObjectReponseParser implements ResponseParser<PutObjectResult> {
        public PutObjectResult parse(aa aaVar) throws IOException {
            try {
                PutObjectResult putObjectResult = new PutObjectResult();
                putObjectResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                putObjectResult.setStatusCode(aaVar.b());
                putObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                putObjectResult.setETag(ResponseParsers.trimQuotes(aaVar.a("ETag")));
                if (aaVar.g().contentLength() > 0) {
                    putObjectResult.setServerCallbackReturnBody(aaVar.g().string());
                }
                ResponseParsers.safeCloseResponse(aaVar);
                return putObjectResult;
            } catch (Throwable th) {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    public static final class UploadPartResponseParser implements ResponseParser<UploadPartResult> {
        public UploadPartResult parse(aa aaVar) throws IOException {
            try {
                UploadPartResult uploadPartResult = new UploadPartResult();
                uploadPartResult.setRequestId(aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID));
                uploadPartResult.setStatusCode(aaVar.b());
                uploadPartResult.setResponseHeader(ResponseParsers.parseResponseHeader(aaVar));
                uploadPartResult.setETag(ResponseParsers.trimQuotes(aaVar.a("ETag")));
                return uploadPartResult;
            } finally {
                ResponseParsers.safeCloseResponse(aaVar);
            }
        }
    }

    private static CopyObjectResult parseCopyObjectResponseXML(InputStream inputStream) throws ParseException, ParserConfigurationException, IOException, SAXException {
        CopyObjectResult copyObjectResult = new CopyObjectResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("LastModified")) {
                    copyObjectResult.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (nodeName.equals("ETag")) {
                    copyObjectResult.setEtag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return copyObjectResult;
    }

    private static ListPartsResult parseListPartsResponseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        ListPartsResult listPartsResult = new ListPartsResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseObjectListResponse] - " + documentElement.getNodeName());
        List arrayList = new ArrayList();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Bucket")) {
                    listPartsResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Key")) {
                    listPartsResult.setKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("UploadId")) {
                    listPartsResult.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("PartNumberMarker")) {
                    r2 = checkChildNotNullAndGetValue(item);
                    if (r2 != null) {
                        listPartsResult.setPartNumberMarker(Integer.valueOf(r2).intValue());
                    }
                } else if (nodeName.equals("NextPartNumberMarker")) {
                    r2 = checkChildNotNullAndGetValue(item);
                    if (r2 != null) {
                        listPartsResult.setNextPartNumberMarker(Integer.valueOf(r2).intValue());
                    }
                } else if (nodeName.equals("MaxParts")) {
                    r2 = checkChildNotNullAndGetValue(item);
                    if (r2 != null) {
                        listPartsResult.setMaxParts(Integer.valueOf(r2).intValue());
                    }
                } else if (nodeName.equals("IsTruncated")) {
                    r2 = checkChildNotNullAndGetValue(item);
                    if (r2 != null) {
                        listPartsResult.setTruncated(Boolean.valueOf(r2).booleanValue());
                    }
                } else if (nodeName.equals("Part")) {
                    NodeList childNodes2 = item.getChildNodes();
                    PartSummary partSummary = new PartSummary();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node item2 = childNodes2.item(i2);
                        String nodeName2 = item2.getNodeName();
                        if (nodeName2 != null) {
                            String checkChildNotNullAndGetValue;
                            if (nodeName2.equals("PartNumber")) {
                                checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item2);
                                if (checkChildNotNullAndGetValue != null) {
                                    partSummary.setPartNumber(Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                                }
                            } else if (nodeName2.equals("LastModified")) {
                                partSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item2)));
                            } else if (nodeName2.equals("ETag")) {
                                partSummary.setETag(checkChildNotNullAndGetValue(item2));
                            } else if (nodeName2.equals("Size")) {
                                checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item2);
                                if (checkChildNotNullAndGetValue != null) {
                                    partSummary.setSize((long) Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                                }
                            }
                        }
                    }
                    arrayList.add(partSummary);
                }
            }
        }
        listPartsResult.setParts(arrayList);
        return listPartsResult;
    }

    private static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase("Location")) {
                    completeMultipartUploadResult.setLocation(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    completeMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    completeMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("ETag")) {
                    completeMultipartUploadResult.setETag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return completeMultipartUploadResult;
    }

    private static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        InitiateMultipartUploadResult initiateMultipartUploadResult = new InitiateMultipartUploadResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase("UploadId")) {
                    initiateMultipartUploadResult.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    initiateMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    initiateMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return initiateMultipartUploadResult;
    }

    private static OSSObjectSummary parseObjectSummaryXML(NodeList nodeList) throws ParseException {
        OSSObjectSummary oSSObjectSummary = new OSSObjectSummary();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Key")) {
                    oSSObjectSummary.setKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("LastModified")) {
                    oSSObjectSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (nodeName.equals("Size")) {
                    String checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue != null) {
                        oSSObjectSummary.setSize((long) Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                    }
                } else if (nodeName.equals("ETag")) {
                    oSSObjectSummary.setETag(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Type")) {
                    oSSObjectSummary.setType(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("StorageClass")) {
                    oSSObjectSummary.setStorageClass(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return oSSObjectSummary;
    }

    private static String parseCommonPrefixXML(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null && nodeName.equals("Prefix")) {
                return checkChildNotNullAndGetValue(item);
            }
        }
        return "";
    }

    private static GetBucketACLResult parseGetBucketACLResponse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        GetBucketACLResult getBucketACLResult = new GetBucketACLResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseGetBucketACLResponse - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                NodeList childNodes2;
                int i2;
                Node item2;
                String nodeName2;
                if (nodeName.equals("Owner")) {
                    childNodes2 = item.getChildNodes();
                    for (i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        item2 = childNodes2.item(i2);
                        nodeName2 = item2.getNodeName();
                        if (nodeName2 != null) {
                            if (nodeName2.equals("ID")) {
                                getBucketACLResult.setBucketOwnerID(checkChildNotNullAndGetValue(item2));
                            } else if (nodeName2.equals("DisplayName")) {
                                getBucketACLResult.setBucketOwner(checkChildNotNullAndGetValue(item2));
                            }
                        }
                    }
                } else if (nodeName.equals("AccessControlList")) {
                    childNodes2 = item.getChildNodes();
                    for (i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        item2 = childNodes2.item(i2);
                        nodeName2 = item2.getNodeName();
                        if (nodeName2 != null && nodeName2.equals("Grant")) {
                            getBucketACLResult.setBucketACL(checkChildNotNullAndGetValue(item2));
                        }
                    }
                }
            }
        }
        return getBucketACLResult;
    }

    private static ListObjectsResult parseObjectListResponse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        ListObjectsResult listObjectsResult = new ListObjectsResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseObjectListResponse] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Name")) {
                    listObjectsResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Prefix")) {
                    listObjectsResult.setPrefix(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Marker")) {
                    listObjectsResult.setMarker(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Delimiter")) {
                    listObjectsResult.setDelimiter(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("EncodingType")) {
                    listObjectsResult.setEncodingType(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("MaxKeys")) {
                    r3 = checkChildNotNullAndGetValue(item);
                    if (r3 != null) {
                        listObjectsResult.setMaxKeys(Integer.valueOf(r3).intValue());
                    }
                } else if (nodeName.equals(MNSConstants.NEXT_MARKER_TAG)) {
                    listObjectsResult.setNextMarker(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("IsTruncated")) {
                    r3 = checkChildNotNullAndGetValue(item);
                    if (r3 != null) {
                        listObjectsResult.setTruncated(Boolean.valueOf(r3).booleanValue());
                    }
                } else if (nodeName.equals("Contents")) {
                    if (item.getChildNodes() != null) {
                        listObjectsResult.getObjectSummaries().add(parseObjectSummaryXML(item.getChildNodes()));
                    }
                } else if (nodeName.equals("CommonPrefixes") && item.getChildNodes() != null) {
                    r3 = parseCommonPrefixXML(item.getChildNodes());
                    if (r3 != null) {
                        listObjectsResult.getCommonPrefixes().add(r3);
                    }
                }
            }
        }
        return listObjectsResult;
    }

    public static String trimQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("\"")) {
            trim = trim.substring(1);
        }
        if (trim.endsWith("\"")) {
            return trim.substring(0, trim.length() - 1);
        }
        return trim;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> map) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String str : map.keySet()) {
                if (str.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(str, (String) map.get(str));
                } else if (str.equals("Last-Modified") || str.equals("Date")) {
                    objectMetadata.setHeader(str, DateUtil.parseRfc822Date((String) map.get(str)));
                } else if (str.equals("Content-Length")) {
                    objectMetadata.setHeader(str, Long.valueOf((String) map.get(str)));
                } else if (str.equals("ETag")) {
                    objectMetadata.setHeader(str, trimQuotes((String) map.get(str)));
                } else {
                    objectMetadata.setHeader(str, map.get(str));
                }
            }
            return objectMetadata;
        } catch (Throwable e) {
            throw new IOException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public static Map<String, String> parseResponseHeader(aa aaVar) {
        Map<String, String> hashMap = new HashMap();
        s f = aaVar.f();
        for (int i = 0; i < f.a(); i++) {
            hashMap.put(f.a(i), f.b(i));
        }
        return hashMap;
    }

    public static ServiceException parseResponseErrorXML(aa aaVar, boolean z) throws IOException {
        String str;
        String str2;
        String str3;
        String str4;
        SAXException e;
        ParserConfigurationException e2;
        String str5 = null;
        int b = aaVar.b();
        String a = aaVar.a(OSSHeaders.OSS_HEADER_REQUEST_ID);
        if (z) {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
        } else {
            try {
                str = aaVar.g().string();
                try {
                    NodeList childNodes = domFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str))).getDocumentElement().getChildNodes();
                    int i = 0;
                    str4 = null;
                    str3 = null;
                    while (i < childNodes.getLength()) {
                        try {
                            Node item = childNodes.item(i);
                            String nodeName = item.getNodeName();
                            if (nodeName != null) {
                                if (nodeName.equals(MNSConstants.ERROR_CODE_TAG)) {
                                    str4 = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals("Message")) {
                                    str3 = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals(MNSConstants.ERROR_REQUEST_ID_TAG)) {
                                    a = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals(MNSConstants.ERROR_HOST_ID_TAG)) {
                                    str5 = checkChildNotNullAndGetValue(item);
                                }
                            }
                            i++;
                        } catch (SAXException e3) {
                            e = e3;
                        } catch (ParserConfigurationException e4) {
                            e2 = e4;
                        }
                    }
                    aaVar.g().close();
                    str2 = str5;
                } catch (SAXException e5) {
                    e = e5;
                    str4 = null;
                    str3 = null;
                    e.printStackTrace();
                    str2 = str5;
                    return new ServiceException(b, str3, str4, a, str2, str);
                } catch (ParserConfigurationException e6) {
                    e2 = e6;
                    str4 = null;
                    str3 = null;
                    e2.printStackTrace();
                    str2 = str5;
                    return new ServiceException(b, str3, str4, a, str2, str);
                }
            } catch (SAXException e52) {
                e = e52;
                str = null;
                str4 = null;
                str3 = null;
                e.printStackTrace();
                str2 = str5;
                return new ServiceException(b, str3, str4, a, str2, str);
            } catch (ParserConfigurationException e62) {
                e2 = e62;
                str = null;
                str4 = null;
                str3 = null;
                e2.printStackTrace();
                str2 = str5;
                return new ServiceException(b, str3, str4, a, str2, str);
            }
        }
        return new ServiceException(b, str3, str4, a, str2, str);
    }

    public static String checkChildNotNullAndGetValue(Node node) {
        if (node.getFirstChild() != null) {
            return node.getFirstChild().getNodeValue();
        }
        return null;
    }

    public static void safeCloseResponse(aa aaVar) {
        try {
            aaVar.g().close();
        } catch (Exception e) {
        }
    }
}
