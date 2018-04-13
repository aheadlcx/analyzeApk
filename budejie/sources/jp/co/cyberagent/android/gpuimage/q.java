package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class q extends ab {
    private float g;
    private int h;
    private float i;
    private int j;

    public q() {
        this(0.03f, 0.003f);
    }

    public q(float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform highp float crossHatchSpacing;\nuniform highp float lineWidth;\nconst highp vec3 W = vec3(0.2125, 0.7154, 0.0721);\nvoid main()\n{\nhighp float luminance = dot(texture2D(inputImageTexture, textureCoordinate).rgb, W);\nlowp vec4 colorToDisplay = vec4(1.0, 1.0, 1.0, 1.0);\nif (luminance < 1.00)\n{\nif (mod(textureCoordinate.x + textureCoordinate.y, crossHatchSpacing) <= lineWidth)\n{\ncolorToDisplay = vec4(0.0, 0.0, 0.0, 1.0);\n}\n}\nif (luminance < 0.75)\n{\nif (mod(textureCoordinate.x - textureCoordinate.y, crossHatchSpacing) <= lineWidth)\n{\ncolorToDisplay = vec4(0.0, 0.0, 0.0, 1.0);\n}\n}\nif (luminance < 0.50)\n{\nif (mod(textureCoordinate.x + textureCoordinate.y - (crossHatchSpacing / 2.0), crossHatchSpacing) <= lineWidth)\n{\ncolorToDisplay = vec4(0.0, 0.0, 0.0, 1.0);\n}\n}\nif (luminance < 0.3)\n{\nif (mod(textureCoordinate.x - textureCoordinate.y - (crossHatchSpacing / 2.0), crossHatchSpacing) <= lineWidth)\n{\ncolorToDisplay = vec4(0.0, 0.0, 0.0, 1.0);\n}\n}\ngl_FragColor = colorToDisplay;\n}\n");
        this.g = f;
        this.i = f2;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "crossHatchSpacing");
        this.j = GLES20.glGetUniformLocation(l(), "lineWidth");
    }

    public void c_() {
        super.c_();
        a(this.g);
        b(this.i);
    }

    public void a(float f) {
        float j;
        if (j() != 0) {
            j = 1.0f / ((float) j());
        } else {
            j = 4.8828125E-4f;
        }
        if (f < j) {
            this.g = j;
        } else {
            this.g = f;
        }
        a(this.h, this.g);
    }

    public void b(float f) {
        this.i = f;
        a(this.j, this.i);
    }
}
