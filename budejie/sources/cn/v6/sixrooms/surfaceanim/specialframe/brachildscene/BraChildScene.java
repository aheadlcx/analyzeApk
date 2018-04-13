package cn.v6.sixrooms.surfaceanim.specialframe.brachildscene;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import com.budejie.www.R$styleable;

public class BraChildScene extends SpecialScene {
    public BraChildScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_post_loading_icon;
    }

    protected void initResources() {
    }

    protected void initSceneElement() {
        addElement(new ChildElement(this));
        addElement(new SwingBraElement(this));
        addElement(new HeartElement(this));
    }
}
