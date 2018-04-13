package retrofit2;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@IgnoreJRERequirement
class j$b extends j {
    j$b() {
    }

    boolean a(Method method) {
        return method.isDefault();
    }

    Object a(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        Constructor declaredConstructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
        declaredConstructor.setAccessible(true);
        return ((Lookup) declaredConstructor.newInstance(new Object[]{cls, Integer.valueOf(-1)})).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }
}
