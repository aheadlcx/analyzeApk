package cn.v6.sixrooms.surfaceanim.specialframe.shakecucumberscene;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;

public class CucumberScene extends SpecialScene {
    public CucumberScene(SceneParameter sceneParameter) {
        super(sceneParameter);
        getSceneParameter().setMaxFrameNum(R$styleable.Theme_Custom_myinfo_night_model_bg);
        if (AnimSceneResManager.getInstance().getContext().getResources().getConfiguration().orientation == 2) {
            this.offsetX = 200;
            this.offsetY = -380;
        }
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_myinfo_night_model_bg;
    }

    protected void initResources() {
    }

    protected void initSceneElement() {
        addElement(new CucumberElement(this));
        addElement(new WordsElement(this));
    }
}
