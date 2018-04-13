package org.apache.commons.cli;

public class UnrecognizedOptionException extends ParseException {
    private String option;

    public UnrecognizedOptionException(String str) {
        super(str);
    }

    public UnrecognizedOptionException(String str, String str2) {
        this(str);
        this.option = str2;
    }

    public String getOption() {
        return this.option;
    }
}
