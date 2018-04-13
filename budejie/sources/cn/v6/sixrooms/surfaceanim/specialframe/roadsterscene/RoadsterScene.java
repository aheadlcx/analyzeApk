package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import com.budejie.www.R$styleable;

public class RoadsterScene extends SpecialScene {
    public static final String CAR = "CarElement";

    public RoadsterScene(SceneParameter sceneParameter) {
        super(sceneParameter);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg;
    }

    protected void initResources() {
    }

    protected void initSceneElement() {
        addElement(CAR, new CarElement(this));
        addElement(new CarMarkElement(this));
        addElement(new FrontLampElement(this));
        addElement(0, new BackLampElement(this));
        addElement(new CarCloudElement(this));
        addElement(new FrontRedHeartElementGroup1(this));
        addElement(new FrontRedHeartElementGroup2(this));
        addElement(new FrontRedHeartElementGroup3(this));
        addElement(new BackRedHeartElementGroup1(this));
        addElement(new BackRedHeartElementGroup2(this));
        addElement(new BackRedHeartElementGroup3(this));
        addElement(new BackRedHeartElementGroup4(this));
        addElement(new BackRedHeartElementGroup5(this));
        addElement(new BackRedHeartElementGroup6(this));
        addElement(new BackRedHeartElementGroup7(this));
        addElement(new BackRedHeartElementGroup8(this));
        addElement(new BackRedHeartElementGroup9(this));
        addElement(new BackRedHeartElementGroup10(this));
        addElement(new BackRedHeartElementGroup11(this));
        addElement(new BackRedHeartElementGroup12(this));
        addElement(new BackRedHeartElementGroup13(this));
        addElement(new BackRedHeartElementGroup14(this));
        addElement(new BackRedHeartElementGroup15(this));
        addElement(new BackRedHeartElementGroup16(this));
        addElement(new BackRedHeartElementGroup17(this));
        addElement(new BackRedHeartElementGroup18(this));
    }
}
