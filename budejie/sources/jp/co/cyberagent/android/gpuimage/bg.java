package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class bg extends ab {
    private int g;
    private float h;

    public bg() {
        this(1.0f);
    }

    public bg(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }");
        this.h = f;
    }

    public void a() {
        super.a();
        this.g = GLES20.glGetUniformLocation(l(), "saturation");
    }

    public void c_() {
        super.c_();
        a(this.h);
    }

    public void a(float f) {
        this.h = f;
        a(this.g, this.h);
    }
}
