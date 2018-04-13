package cn.v6.sixrooms.surfaceanim.specialframe.yachtscene;

import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.specialframe.util.ScalePxUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;

public class YachtScene extends SpecialScene {
    public YachtScene(SceneParameter sceneParameter) {
        super(sceneParameter);
        if (AnimSceneResManager.getInstance().getContext().getResources().getConfiguration().orientation == 2) {
            this.offsetX = 420;
            this.offsetY = -210;
        }
        ScalePxUtil.setOffset(this.offsetX, this.offsetY);
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_follows_shape_item_bg;
    }

    protected void initResources() {
    }

    protected void initSceneElement() {
        addElement(new TreeElement(this));
        addElement(new SunElement(this));
        addElement(new LeafElement(this));
        addElement(new StarsElement(this));
        addElement(new CloudElement(this));
        addElement(new IslandElement(this));
        addElement(new BindingBalloonsElement(this));
        addElement(new YachtElement(this));
        addElement(new LooseBalloonsElement(this));
    }
}
