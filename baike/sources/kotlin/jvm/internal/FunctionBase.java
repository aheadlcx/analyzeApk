package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Function;

public interface FunctionBase extends Serializable, Function {
    int getArity();
}
