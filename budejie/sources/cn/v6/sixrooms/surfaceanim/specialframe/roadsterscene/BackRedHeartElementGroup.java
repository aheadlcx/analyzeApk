package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimScene;

public abstract class BackRedHeartElementGroup extends RedHeartElementGroup {
    public BackRedHeartElementGroup(AnimScene animScene) {
        super(animScene);
    }

    public void initRedHeartElements(AnimScene animScene) {
        this.mCarRedHeartElements = new CarRedHeartElement[3];
        this.mCarRedHeartElements[0] = new CarRedHeart1_1Element(animScene);
        this.mCarRedHeartElements[1] = new CarRedHeart2_1Element(animScene);
        this.mCarRedHeartElements[2] = new CarRedHeart3_1Element(animScene);
    }
}
