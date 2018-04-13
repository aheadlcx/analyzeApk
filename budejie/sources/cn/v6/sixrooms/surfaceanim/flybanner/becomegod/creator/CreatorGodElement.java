package cn.v6.sixrooms.surfaceanim.flybanner.becomegod.creator;

import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BaseBecomeGodElement;
import com.budejie.www.R$styleable;

public class CreatorGodElement extends BaseBecomeGodElement {
    public CreatorGodElement(AnimScene animScene) {
        super(animScene);
    }

    protected int[] getFrameControlArray() {
        return new int[]{10, 310, R$styleable.Theme_Custom_last_refresh_item_text_theme};
    }

    protected int getGoBitmapResId() {
        return R.drawable.fly_upgrade_creator_god_go;
    }
}
