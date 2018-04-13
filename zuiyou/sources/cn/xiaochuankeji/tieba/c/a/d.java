package cn.xiaochuankeji.tieba.c.a;

public class d extends n {
    private final int c;

    public d(int i) {
        super(new j("precision mediump float;\nuniform sampler2D sTexture;\nvarying vec2 vTextureCoord;\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n\nvoid main(){\n   vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n   int multiplier = 0;\n   vec2 blurStep = vec2(0,0);\n   vec2 blurCoordinates[9];   for(int i = 0; i < 9; i++) {\n     multiplier = (i - 4);\n     blurStep = float(multiplier) * singleStepOffset;\n     blurCoordinates[i] = vTextureCoord.xy + blurStep;\n   }\n   vec3 sum = vec3(0,0,0);\n   vec4 color = texture2D(sTexture, blurCoordinates[4]);\n   sum += texture2D(sTexture, blurCoordinates[0]).rgb * 0.05;\n   sum += texture2D(sTexture, blurCoordinates[1]).rgb * 0.09;\n   sum += texture2D(sTexture, blurCoordinates[2]).rgb * 0.12;\n   sum += texture2D(sTexture, blurCoordinates[3]).rgb * 0.15;\n   sum += color.rgb * 0.18;\n   sum += texture2D(sTexture, blurCoordinates[5]).rgb * 0.15;\n   sum += texture2D(sTexture, blurCoordinates[6]).rgb * 0.12;\n   sum += texture2D(sTexture, blurCoordinates[7]).rgb * 0.09;\n   sum += texture2D(sTexture, blurCoordinates[8]).rgb * 0.05;\n   gl_FragColor = vec4(sum, color.a);\n}\n"), new j("precision mediump float;\nuniform sampler2D sTexture;\nvarying vec2 vTextureCoord;\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n\nvoid main(){\n   vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n   int multiplier = 0;\n   vec2 blurStep = vec2(0,0);\n   vec2 blurCoordinates[9];   for(int i = 0; i < 9; i++) {\n     multiplier = (i - 4);\n     blurStep = float(multiplier) * singleStepOffset;\n     blurCoordinates[i] = vTextureCoord.xy + blurStep;\n   }\n   vec3 sum = vec3(0,0,0);\n   vec4 color = texture2D(sTexture, blurCoordinates[4]);\n   sum += texture2D(sTexture, blurCoordinates[0]).rgb * 0.05;\n   sum += texture2D(sTexture, blurCoordinates[1]).rgb * 0.09;\n   sum += texture2D(sTexture, blurCoordinates[2]).rgb * 0.12;\n   sum += texture2D(sTexture, blurCoordinates[3]).rgb * 0.15;\n   sum += color.rgb * 0.18;\n   sum += texture2D(sTexture, blurCoordinates[5]).rgb * 0.15;\n   sum += texture2D(sTexture, blurCoordinates[6]).rgb * 0.12;\n   sum += texture2D(sTexture, blurCoordinates[7]).rgb * 0.09;\n   sum += texture2D(sTexture, blurCoordinates[8]).rgb * 0.05;\n   gl_FragColor = vec4(sum, color.a);\n}\n"));
        this.c = i;
    }

    public void a(int i, int i2) {
        super.a(i / this.c, i2 / this.c);
    }
}
