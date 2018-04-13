package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token.CommentType;

public class Comment extends AstNode {
    private CommentType commentType;
    private String value;

    public Comment(int i, int i2, CommentType commentType, String str) {
        super(i, i2);
        this.type = 161;
        this.commentType = commentType;
        this.value = str;
    }

    public CommentType getCommentType() {
        return this.commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getValue() {
        return this.value;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder(getLength() + 10);
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(this.value);
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
