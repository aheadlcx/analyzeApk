package cn.v6.sixrooms.surfaceanim.flybanner.becomegod.god;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BaseBecomeGodElement;
import com.budejie.www.R$styleable;

public class GodElement extends BaseBecomeGodElement {
    public GodElement(AnimScene animScene) {
        super(animScene);
    }

    protected int[] getFrameControlArray() {
        return new int[]{10, R$styleable.Theme_Custom_posts_state_bg, 200};
    }

    protected int getGoBitmapResId() {
        return R.drawable.fly_upgrade_god_go;
    }
}
