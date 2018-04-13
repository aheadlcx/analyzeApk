package cz.msebera.android.httpclient.config;

public interface Lookup<I> {
    I lookup(String str);
}
