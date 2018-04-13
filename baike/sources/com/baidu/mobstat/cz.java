package com.baidu.mobstat;

import java.io.File;
import java.security.MessageDigest;

public class cz {
    public static String a(byte[] bArr) {
        try {
            return cy.b(MessageDigest.getInstance("SHA-256"), bArr);
        } catch (Throwable e) {
            db.b(e);
            return "";
        }
    }

    public static String a(File file) {
        try {
            return cy.b(MessageDigest.getInstance("SHA-256"), file);
        } catch (Throwable e) {
            db.b(e);
            return "";
        }
    }
}
