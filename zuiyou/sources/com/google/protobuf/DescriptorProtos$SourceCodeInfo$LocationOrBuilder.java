package com.google.protobuf;

import java.util.List;

public interface DescriptorProtos$SourceCodeInfo$LocationOrBuilder extends MessageOrBuilder {
    String getLeadingComments();

    ByteString getLeadingCommentsBytes();

    String getLeadingDetachedComments(int i);

    ByteString getLeadingDetachedCommentsBytes(int i);

    int getLeadingDetachedCommentsCount();

    List<String> getLeadingDetachedCommentsList();

    int getPath(int i);

    int getPathCount();

    List<Integer> getPathList();

    int getSpan(int i);

    int getSpanCount();

    List<Integer> getSpanList();

    String getTrailingComments();

    ByteString getTrailingCommentsBytes();

    boolean hasLeadingComments();

    boolean hasTrailingComments();
}
