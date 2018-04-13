package com.ali.auth.third.core.config;

public class Version {
    private int major;
    private int micro;
    private int minor;

    public Version(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.micro = i3;
    }

    public String toString() {
        return this.major + "." + this.minor + "." + this.micro;
    }
}
