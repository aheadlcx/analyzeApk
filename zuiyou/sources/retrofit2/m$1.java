package retrofit2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class m$1 implements InvocationHandler {
    final /* synthetic */ Class a;
    final /* synthetic */ m b;
    private final j c = j.a();

    m$1(m mVar, Class cls) {
        this.b = mVar;
        this.a = cls;
    }

    public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, objArr);
        }
        if (this.c.a(method)) {
            return this.c.a(method, this.a, obj, objArr);
        }
        n a = this.b.a(method);
        return a.d.a(new h(a, objArr));
    }
}
