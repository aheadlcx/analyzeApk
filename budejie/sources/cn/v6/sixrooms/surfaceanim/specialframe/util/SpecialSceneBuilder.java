package cn.v6.sixrooms.surfaceanim.specialframe.util;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SpecialSceneBuilder {
    public static SpecialScene createScene(int i) {
        try {
            return (SpecialScene) SpecialSceneConfig.getSpecialScene(i).getConstructor(new Class[]{SceneParameter.class}).newInstance(new Object[]{new SceneParameter()});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    public static SpecialScene createScene(int i, String str) {
        try {
            Constructor constructor = SpecialSceneConfig.getSpecialScene(i).getConstructor(new Class[]{SceneParameter.class});
            new SceneParameter().setResPath(str);
            return (SpecialScene) constructor.newInstance(new Object[]{r1});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        return null;
    }
}
