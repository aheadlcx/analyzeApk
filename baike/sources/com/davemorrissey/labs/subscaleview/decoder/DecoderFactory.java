package com.davemorrissey.labs.subscaleview.decoder;

public interface DecoderFactory<T> {
    T make() throws IllegalAccessException, InstantiationException;
}
