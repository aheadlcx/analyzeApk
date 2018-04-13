package javax.validation;

public interface Path extends Iterable<Node> {

    public interface Node {
    }

    public interface BeanNode extends Node {
    }

    public interface ConstructorNode extends Node {
    }

    public interface CrossParameterNode extends Node {
    }

    public interface MethodNode extends Node {
    }

    public interface ParameterNode extends Node {
    }

    public interface PropertyNode extends Node {
    }

    public interface ReturnValueNode extends Node {
    }
}
