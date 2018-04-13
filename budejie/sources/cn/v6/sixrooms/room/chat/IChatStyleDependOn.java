package cn.v6.sixrooms.room.chat;

import java.util.List;

public interface IChatStyleDependOn {
    IChatStyleDependOn dependOn(int i);

    List<Integer> getDependOnList();
}
