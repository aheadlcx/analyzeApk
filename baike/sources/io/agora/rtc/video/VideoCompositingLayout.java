package io.agora.rtc.video;

import io.agora.rtc.internal.Logging;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.core.utils.PictureGetHelper;

public class VideoCompositingLayout {
    public byte[] appData = null;
    public String backgroundColor;
    public int canvasHeight;
    public int canvasWidth;
    public Region[] regions = null;

    public static class Builder {
        public String appData;
        public Canvas canvas = new Canvas();
        public List<Region> regionsList;

        public final int regionCount() {
            if (this.regionsList == null) {
                return 0;
            }
            return this.regionsList.size();
        }

        public Builder setCanvas(int i, int i2) {
            this.canvas.width = i;
            this.canvas.height = i2;
            return this;
        }

        public Builder setCanvas(int i, int i2, String str) {
            if (VideoCompositingLayout.isValidColor(str)) {
                this.canvas.bgColor = str;
            } else {
                Logging.w("VideoCompositingLayout", "unknown color " + str + ", using default bgColor");
            }
            return setCanvas(i, i2);
        }

        public Builder addWindow(Region region) {
            if (this.regionsList == null) {
                this.regionsList = new ArrayList();
            }
            this.regionsList.add(region);
            return this;
        }

        public Builder removeWindowForUid(int i) {
            Iterator it = this.regionsList.iterator();
            while (it.hasNext()) {
                if (((Region) it.next()).uid == i) {
                    it.remove();
                }
            }
            return this;
        }

        public Builder resetWindows(List<Region> list) {
            this.regionsList = list;
            return this;
        }

        public Builder updateAppData(String str) {
            this.appData = str;
            return this;
        }

        public VideoCompositingLayout create() {
            VideoCompositingLayout videoCompositingLayout = new VideoCompositingLayout();
            videoCompositingLayout.canvasWidth = this.canvas.width;
            videoCompositingLayout.canvasHeight = this.canvas.height;
            videoCompositingLayout.backgroundColor = this.canvas.bgColor;
            if (this.regionsList != null && this.regionsList.size() > 0) {
                videoCompositingLayout.regions = (Region[]) this.regionsList.toArray(new Region[this.regionsList.size()]);
            }
            if (this.appData != null) {
                videoCompositingLayout.appData = this.appData.getBytes();
            }
            return videoCompositingLayout;
        }
    }

    public static class Canvas {
        public String bgColor = "#FF0000";
        public int height = PictureGetHelper.IMAGE_SIZE;
        public int width = 320;
    }

    public static class Region {
        public double alpha;
        public double height;
        public int renderMode;
        public int uid;
        public double width;
        public double x;
        public double y;
        public int zOrder;

        public Region uid(int i) {
            this.uid = i;
            return this;
        }

        public Region position(double d, double d2) {
            this.x = d;
            this.y = d2;
            return this;
        }

        public Region size(double d, double d2) {
            this.width = d;
            this.height = d2;
            return this;
        }

        public Region zOrder(int i) {
            this.zOrder = i;
            return this;
        }

        public Region alpha(double d) {
            this.alpha = d;
            return this;
        }

        public Region renderMode(int i) {
            this.renderMode = i;
            return this;
        }
    }

    public static boolean isValidColor(String str) {
        return true;
    }
}
