package com.bdj.picture.edit.widget;

import android.app.Activity;
import android.widget.HorizontalScrollView;
import com.bdj.picture.edit.EditPictureActivity;
import com.bdj.picture.edit.a.k;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.d;
import com.bdj.picture.edit.util.GPUImageFilterTools;
import com.bdj.picture.edit.util.GPUImageFilterTools.FilterType;
import com.bdj.picture.edit.util.j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b extends c {
    private List<d> d;
    private Map<String, FilterType> e = new HashMap();

    public b(Activity activity) {
        super(activity);
    }

    private void a() {
        this.e.put("Normal", FilterType.NORMAL);
        this.e.put("Contrast", FilterType.CONTRAST);
        this.e.put("Invert", FilterType.INVERT);
        this.e.put("Pixelation", FilterType.PIXELATION);
        this.e.put("Hue", FilterType.HUE);
        this.e.put("Gamma", FilterType.GAMMA);
        this.e.put("Brightness", FilterType.BRIGHTNESS);
        this.e.put("Sepia", FilterType.SEPIA);
        this.e.put("Grayscale", FilterType.GRAYSCALE);
        this.e.put("Sharpness", FilterType.SHARPEN);
        this.e.put("Sobel Edge Detection", FilterType.SOBEL_EDGE_DETECTION);
        this.e.put("3x3 Convolution", FilterType.THREE_X_THREE_CONVOLUTION);
        this.e.put("Emboss", FilterType.EMBOSS);
        this.e.put("Posterize", FilterType.POSTERIZE);
        this.e.put("Grouped filters", FilterType.FILTER_GROUP);
        this.e.put("Saturation", FilterType.SATURATION);
        this.e.put("Exposure", FilterType.EXPOSURE);
        this.e.put("Highlight Shadow", FilterType.HIGHLIGHT_SHADOW);
        this.e.put("Monochrome", FilterType.MONOCHROME);
        this.e.put("Opacity", FilterType.OPACITY);
        this.e.put("RGB", FilterType.RGB);
        this.e.put("White Balance", FilterType.WHITE_BALANCE);
        this.e.put("Vignette", FilterType.VIGNETTE);
        this.e.put("ToneCurve", FilterType.TONE_CURVE);
        this.e.put("Blend (Difference)", FilterType.BLEND_DIFFERENCE);
        this.e.put("Blend (Source Over)", FilterType.BLEND_SOURCE_OVER);
        this.e.put("Blend (Color Burn)", FilterType.BLEND_COLOR_BURN);
        this.e.put("Blend (Color Dodge)", FilterType.BLEND_COLOR_DODGE);
        this.e.put("Blend (Darken)", FilterType.BLEND_DARKEN);
        this.e.put("Blend (Dissolve)", FilterType.BLEND_DISSOLVE);
        this.e.put("Blend (Exclusion)", FilterType.BLEND_EXCLUSION);
        this.e.put("Blend (Hard Light)", FilterType.BLEND_HARD_LIGHT);
        this.e.put("Blend (Lighten)", FilterType.BLEND_LIGHTEN);
        this.e.put("Blend (Add)", FilterType.BLEND_ADD);
        this.e.put("Blend (Divide)", FilterType.BLEND_DIVIDE);
        this.e.put("Blend (Multiply)", FilterType.BLEND_MULTIPLY);
        this.e.put("Blend (Overlay)", FilterType.BLEND_OVERLAY);
        this.e.put("Blend (Screen)", FilterType.BLEND_SCREEN);
        this.e.put("Blend (Alpha)", FilterType.BLEND_ALPHA);
        this.e.put("Blend (Color)", FilterType.BLEND_COLOR);
        this.e.put("Blend (Hue)", FilterType.BLEND_HUE);
        this.e.put("Blend (Saturation)", FilterType.BLEND_SATURATION);
        this.e.put("Blend (Luminosity)", FilterType.BLEND_LUMINOSITY);
        this.e.put("Blend (Linear Burn)", FilterType.BLEND_LINEAR_BURN);
        this.e.put("Blend (Soft Light)", FilterType.BLEND_SOFT_LIGHT);
        this.e.put("Blend (Subtract)", FilterType.BLEND_SUBTRACT);
        this.e.put("Blend (Chroma Key)", FilterType.BLEND_CHROMA_KEY);
        this.e.put("Blend (Normal)", FilterType.BLEND_NORMAL);
        this.e.put("Lookup (Amatorka)", FilterType.LOOKUP_AMATORKA);
        this.e.put("Gaussian Blur", FilterType.GAUSSIAN_BLUR);
        this.e.put("Crosshatch", FilterType.CROSSHATCH);
        this.e.put("Box Blur", FilterType.BOX_BLUR);
        this.e.put("CGA Color Space", FilterType.CGA_COLORSPACE);
        this.e.put("Dilation", FilterType.DILATION);
        this.e.put("Kuwahara", FilterType.KUWAHARA);
        this.e.put("RGB Dilation", FilterType.RGB_DILATION);
        this.e.put("Sketch", FilterType.SKETCH);
        this.e.put("Toon", FilterType.TOON);
        this.e.put("Smooth Toon", FilterType.SMOOTH_TOON);
        this.e.put("Bulge Distortion", FilterType.BULGE_DISTORTION);
        this.e.put("Glass Sphere", FilterType.GLASS_SPHERE);
        this.e.put("Haze", FilterType.HAZE);
        this.e.put("Laplacian", FilterType.LAPLACIAN);
        this.e.put("Non Maximum Suppression", FilterType.NON_MAXIMUM_SUPPRESSION);
        this.e.put("Sphere Refraction", FilterType.SPHERE_REFRACTION);
        this.e.put("Swirl", FilterType.SWIRL);
        this.e.put("Weak Pixel Inclusion", FilterType.WEAK_PIXEL_INCLUSION);
        this.e.put("False Color", FilterType.FALSE_COLOR);
        this.e.put("Color Balance", FilterType.COLOR_BALANCE);
        this.e.put("Levels Min (Mid Adjust)", FilterType.LEVELS_FILTER_MIN);
    }

    public void a(HorizontalScrollView horizontalScrollView, BVType bVType) {
        this.c.a(bVType);
        a();
        if (this.d == null || this.d.isEmpty()) {
            this.d = j.a(this.a, k.plist_filter_material);
        }
        a(horizontalScrollView, this.d);
    }

    public void a(d dVar) {
        this.c.a(dVar);
        ((EditPictureActivity) this.a).a(GPUImageFilterTools.a(this.a, (FilterType) this.e.get(dVar.n())));
    }
}
