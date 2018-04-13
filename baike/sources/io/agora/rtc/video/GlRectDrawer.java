package io.agora.rtc.video;

import android.opengl.GLES20;
import io.agora.rtc.video.RendererCommon.GlDrawer;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Map;

public class GlRectDrawer implements GlDrawer {
    private static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    private static final String OES_FRAGMENT_SHADER_STRING = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n";
    private static final String RGB_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(rgb_tex, interp_tc);\n}\n";
    private static final String VERTEX_SHADER_STRING = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private static final String YUV_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\n\nvoid main() {\n  float y = texture2D(y_tex, interp_tc).r;\n  float u = texture2D(u_tex, interp_tc).r - 0.5;\n  float v = texture2D(v_tex, interp_tc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1);\n}\n";
    private FloatBuffer mTexCoordinate = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    private final Map<String, Shader> shaders = new IdentityHashMap();

    private static class Shader {
        public final GlShader glShader;
        public final int texMatrixLocation = this.glShader.getUniformLocation("texMatrix");

        public Shader(String str) {
            this.glShader = new GlShader(GlRectDrawer.VERTEX_SHADER_STRING, str);
        }
    }

    public void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mTexCoordinate = GlUtil.createFloatBuffer(ComputeVertexAttribArray(i4, i5, i6, i7));
        prepareShader(RGB_FRAGMENT_SHADER_STRING, fArr, this.mTexCoordinate);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        drawRectangle(i2, i3, i6, i7);
        GLES20.glBindTexture(3553, 0);
    }

    public void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mTexCoordinate = GlUtil.createFloatBuffer(ComputeVertexAttribArray(i4, i5, i6, i7));
        prepareShader(OES_FRAGMENT_SHADER_STRING, fArr, this.mTexCoordinate);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        drawRectangle(i2, i3, i6, i7);
        GLES20.glBindTexture(36197, 0);
    }

    public void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5) {
        prepareShader(OES_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        drawRectangle(i2, i3, i4, i5);
        GLES20.glBindTexture(36197, 0);
    }

    public void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5) {
        prepareShader(RGB_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        drawRectangle(i2, i3, i4, i5);
        GLES20.glBindTexture(3553, 0);
    }

    public void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4) {
        int i5;
        prepareShader(YUV_FRAGMENT_SHADER_STRING, fArr);
        for (i5 = 0; i5 < 3; i5++) {
            GLES20.glActiveTexture(33984 + i5);
            GLES20.glBindTexture(3553, iArr[i5]);
        }
        drawRectangle(i, i2, i3, i4);
        for (i5 = 0; i5 < 3; i5++) {
            GLES20.glActiveTexture(33984 + i5);
            GLES20.glBindTexture(3553, 0);
        }
    }

    private void drawRectangle(int i, int i2, int i3, int i4) {
        GLES20.glViewport(i, i2, i3, i4);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private void prepareShader(String str, float[] fArr) {
        prepareShader(str, fArr, FULL_RECTANGLE_TEX_BUF);
    }

    private void prepareShader(String str, float[] fArr, FloatBuffer floatBuffer) {
        Shader shader;
        if (this.shaders.containsKey(str)) {
            shader = (Shader) this.shaders.get(str);
        } else {
            shader = new Shader(str);
            this.shaders.put(str, shader);
            shader.glShader.useProgram();
            if (str == YUV_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader.glShader.getUniformLocation("y_tex"), 0);
                GLES20.glUniform1i(shader.glShader.getUniformLocation("u_tex"), 1);
                GLES20.glUniform1i(shader.glShader.getUniformLocation("v_tex"), 2);
            } else if (str == RGB_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader.glShader.getUniformLocation("rgb_tex"), 0);
            } else if (str == OES_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader.glShader.getUniformLocation("oes_tex"), 0);
            } else {
                throw new IllegalStateException("Unknown fragment shader: " + str);
            }
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
        }
        shader.glShader.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
        shader.glShader.setVertexAttribArray("in_tc", 2, floatBuffer);
        shader.glShader.useProgram();
        GLES20.glUniformMatrix4fv(shader.texMatrixLocation, 1, false, fArr, 0);
    }

    private float[] ComputeVertexAttribArray(int i, int i2, int i3, int i4) {
        float f = ((float) i3) / ((float) i4);
        if (((float) i) / ((float) i2) >= f) {
            float f2 = ((((float) i) - (f * ((float) i2))) / 2.0f) / ((float) i);
            return new float[]{f2, 0.0f, 1.0f - f2, 0.0f, f2, 1.0f, 1.0f - f2, 1.0f};
        }
        f2 = ((((float) i2) - (((float) i) / f)) / 2.0f) / ((float) i2);
        return new float[]{0.0f, f2, 1.0f, f2, 0.0f, 1.0f - f2, 1.0f, 1.0f - f2};
    }

    public void release() {
        for (Shader shader : this.shaders.values()) {
            shader.glShader.release();
        }
        this.shaders.clear();
    }
}
