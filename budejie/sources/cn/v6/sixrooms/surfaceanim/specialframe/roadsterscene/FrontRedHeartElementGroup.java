package cn.v6.sixrooms.surfaceanim.specialframe.roadsterscene;

import cn.v6.sixrooms.surfaceanim.AnimScene;

public abstract class FrontRedHeartElementGroup extends RedHeartElementGroup {
    public FrontRedHeartElementGroup(AnimScene animScene) {
        super(animScene);
    }

    public void initRedHeartElements(AnimScene animScene) {
        this.mCarRedHeartElements = new CarRedHeartElement[3];
        this.mCarRedHeartElements[0] = new CarRedHeart1Element(animScene);
        this.mCarRedHeartElements[1] = new CarRedHeart2Element(animScene);
        this.mCarRedHeartElements[2] = new CarRedHeart3Element(animScene);
    }
}
