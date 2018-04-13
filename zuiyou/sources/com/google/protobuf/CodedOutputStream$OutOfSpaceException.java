package com.google.protobuf;

import java.io.IOException;

public class CodedOutputStream$OutOfSpaceException extends IOException {
    private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
    private static final long serialVersionUID = -6947486886997889499L;

    CodedOutputStream$OutOfSpaceException() {
        super(MESSAGE);
    }

    CodedOutputStream$OutOfSpaceException(String str) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str);
    }

    CodedOutputStream$OutOfSpaceException(Throwable th) {
        super(MESSAGE, th);
    }

    CodedOutputStream$OutOfSpaceException(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
    }
}
