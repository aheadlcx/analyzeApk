package qsbk.app.im;

import java.util.LinkedList;
import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

public class ChatGroup {
    private static List<ChatGroup> a = null;
    public String id;
    public String name;

    public ChatGroup(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    public static List<ChatGroup> getAllGroups() {
        if (a == null) {
            a = new LinkedList();
            a.add(new ChatGroup("1", "友际无限"));
        }
        return a;
    }

    public static ChatGroup getGroupById(String str) {
        return (ChatGroup) ArrayUtils.findFirst(a, new e(str));
    }
}
