package org.mozilla.javascript;

class ContextFactory$1GlobalSetterImpl implements ContextFactory$GlobalSetter {
    ContextFactory$1GlobalSetterImpl() {
    }

    public void setContextFactoryGlobal(ContextFactory contextFactory) {
        if (contextFactory == null) {
            contextFactory = new ContextFactory();
        }
        ContextFactory.access$002(contextFactory);
    }

    public ContextFactory getContextFactoryGlobal() {
        return ContextFactory.access$000();
    }
}
