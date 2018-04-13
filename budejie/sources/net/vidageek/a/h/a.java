package net.vidageek.a.h;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface a<T> {
    Class<T> a();

    Constructor<T> a(Class<?>[] clsArr);

    Field a(String str);

    Method a(String str, Class<?>[] clsArr);

    List<Field> b();
}
