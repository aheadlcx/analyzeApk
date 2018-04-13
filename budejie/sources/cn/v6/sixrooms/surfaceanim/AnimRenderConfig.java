package cn.v6.sixrooms.surfaceanim;

import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;

public class AnimRenderConfig {
    private int a;
    private IAnimSceneFactory b;

    public static class Builder {
        private int a = 24;
        private IAnimSceneFactory b;

        public Builder setFPS(int i) {
            this.a = i;
            return this;
        }

        public Builder setAnimSceneFactory(IAnimSceneFactory iAnimSceneFactory) {
            this.b = iAnimSceneFactory;
            return this;
        }

        public AnimRenderConfig build() {
            return new AnimRenderConfig(this);
        }
    }

    public AnimRenderConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
    }

    public IAnimSceneFactory getAnimSceneFactory() {
        return this.b;
    }

    public int getFPS() {
        return this.a;
    }
}
