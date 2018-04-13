package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

final class e extends EqualeOP<ChatGroup> {
    final /* synthetic */ String a;

    e(String str) {
        this.a = str;
    }

    public boolean test(ChatGroup chatGroup) {
        return chatGroup.id.equals(this.a);
    }
}
