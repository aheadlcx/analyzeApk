package cn.v6.sixrooms.surfaceanim.protocol;

public class ElementBean {
    private String a;
    private int b;
    private int c;
    private int d = -1;
    private RenderBean[] e;
    private TextRenderBean[] f;
    private FrameRenderBean[] g;
    private AlphaRenderBean[] h;
    private TranslateRenderBean[] i;
    private ScaleRenderBean[] j;
    private RotateRenderBean[] k;
    private PlanBean[] l;
    private XfermodeBean[] m;

    public XfermodeBean[] getXfermode() {
        return this.m;
    }

    public void setXfermode(XfermodeBean[] xfermodeBeanArr) {
        this.m = xfermodeBeanArr;
    }

    public PlanBean[] getPlan() {
        return this.l;
    }

    public void setPlan(PlanBean[] planBeanArr) {
        this.l = planBeanArr;
    }

    public String getImg() {
        return this.a;
    }

    public void setImg(String str) {
        this.a = str;
    }

    public int getStart() {
        return this.b;
    }

    public void setStart(int i) {
        this.b = i;
    }

    public int getEnd() {
        return this.c;
    }

    public void setEnd(int i) {
        this.c = i;
    }

    public int getMode() {
        return this.d;
    }

    public void setMode(int i) {
        this.d = i;
    }

    public RenderBean[] getHide() {
        return this.e;
    }

    public void setHide(RenderBean[] renderBeanArr) {
        this.e = renderBeanArr;
    }

    public TextRenderBean[] getText() {
        return this.f;
    }

    public void setText(TextRenderBean[] textRenderBeanArr) {
        this.f = textRenderBeanArr;
    }

    public FrameRenderBean[] getFrame() {
        return this.g;
    }

    public void setFrame(FrameRenderBean[] frameRenderBeanArr) {
        this.g = frameRenderBeanArr;
    }

    public AlphaRenderBean[] getAlpha() {
        return this.h;
    }

    public void setAlpha(AlphaRenderBean[] alphaRenderBeanArr) {
        this.h = alphaRenderBeanArr;
    }

    public TranslateRenderBean[] getTranslate() {
        return this.i;
    }

    public void setTranslate(TranslateRenderBean[] translateRenderBeanArr) {
        this.i = translateRenderBeanArr;
    }

    public ScaleRenderBean[] getScale() {
        return this.j;
    }

    public void setScale(ScaleRenderBean[] scaleRenderBeanArr) {
        this.j = scaleRenderBeanArr;
    }

    public RotateRenderBean[] getRotate() {
        return this.k;
    }

    public void setRotate(RotateRenderBean[] rotateRenderBeanArr) {
        this.k = rotateRenderBeanArr;
    }
}
