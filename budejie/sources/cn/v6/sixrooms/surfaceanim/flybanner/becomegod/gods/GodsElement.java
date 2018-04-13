package cn.v6.sixrooms.surfaceanim.flybanner.becomegod.gods;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BaseBecomeGodElement;
import com.budejie.www.R$styleable;

public class GodsElement extends BaseBecomeGodElement {
    public GodsElement(AnimScene animScene) {
        super(animScene);
    }

    protected int[] getFrameControlArray() {
        return new int[]{10, 250, R$styleable.Theme_Custom_forward_icon};
    }

    protected int getGoBitmapResId() {
        return R.drawable.fly_upgradd_god_of_gods_go;
    }
}
