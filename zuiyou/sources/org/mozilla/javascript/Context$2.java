package org.mozilla.javascript;

class Context$2 implements Context$ClassShutterSetter {
    final /* synthetic */ Context this$0;

    Context$2(Context context) {
        this.this$0 = context;
    }

    public void setClassShutter(ClassShutter classShutter) {
        Context.access$002(this.this$0, classShutter);
    }

    public ClassShutter getClassShutter() {
        return Context.access$000(this.this$0);
    }
}
