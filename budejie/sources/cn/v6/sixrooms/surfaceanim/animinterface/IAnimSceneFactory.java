package cn.v6.sixrooms.surfaceanim.animinterface;

import cn.v6.sixrooms.surfaceanim.AnimScene;

public interface IAnimSceneFactory {
    AnimScene[] generateAnimScene(Object obj);
}
