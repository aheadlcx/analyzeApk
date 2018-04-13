package cn.v6.sixrooms.surfaceanim.specialframe.weddingscene;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;

public class WeddingScene extends SpecialScene {
    public static final String FLOWER = "FlowerElement";
    public static final String FLOWER_HEART = "FlowerHeartElement";
    private PointI a;
    private PointI b;
    private PointI c;
    private PointI d;
    private PointI e;
    private PointI f;
    private PointI g;
    private boolean h = false;
    private float i;

    public WeddingScene(SceneParameter sceneParameter) {
        super(sceneParameter);
        if (AnimSceneResManager.getInstance().getContext().getResources().getConfiguration().orientation == 2) {
            this.offsetX = 420;
            this.offsetY = 0;
        }
    }

    protected int initMaxFrames() {
        return R$styleable.Theme_Custom_label_list_search_icon;
    }

    protected void initResources() {
    }

    private static PointI a(int i, int i2) {
        return new PointI(AnimSceneResManager.getInstance().getScalePx(i), AnimSceneResManager.getInstance().getScalePx(i2));
    }

    public boolean render(Canvas canvas) {
        if (this.h) {
            canvas.scale(0.75f, 0.75f, this.i, 0.0f);
        }
        return super.render(canvas);
    }

    protected void initSceneElement() {
        this.a = a(540, 748);
        this.b = a(488, -100);
        this.c = a(640, R$styleable.Theme_Custom_new_detail_plate_color);
        this.d = a(R$styleable.Theme_Custom_pp_btn_cancle_attention, -100);
        this.e = a(436, 500);
        this.f = a(R$styleable.Theme_Custom_share_dialog_cancel_text_background, -182);
        this.g = a(772, -182);
        int[] screenSize = AnimSceneResManager.getInstance().getScreenSize();
        if (screenSize[0] > screenSize[1]) {
            this.h = true;
            this.i = (float) (screenSize[0] / 2);
        }
        AnimSceneElement linearHeartElement = new LinearHeartElement(this, 127, 151);
        linearHeartElement.initParameter(this.a, this.b, 255, 100, 0.0f, 450.0f);
        AnimSceneElement linearHeartElement2 = new LinearHeartElement(this, 139, R$styleable.Theme_Custom_search_label_edit_color_theme);
        linearHeartElement2.initParameter(this.a, this.b, 255, 100, 0.0f, 363.0f);
        AnimSceneElement linearHeartElement3 = new LinearHeartElement(this, 151, R$styleable.Theme_Custom_label_subscribe_number_text_color);
        linearHeartElement3.initParameter(this.a, this.b, 255, 100, 0.0f, 450.0f);
        AnimSceneElement linearHeartElement4 = new LinearHeartElement(this, 167, R$styleable.Theme_Custom_bt2_color_state);
        linearHeartElement4.initParameter(this.a, this.b, 255, 30, -5.0f, 365.0f);
        AnimSceneElement linearHeartElement5 = new LinearHeartElement(this, 128, R$styleable.Theme_Custom_attention_right);
        linearHeartElement5.initParameter(this.a, this.g, 255, 20, 0.0f, -10.0f);
        AnimSceneElement linearHeartElement6 = new LinearHeartElement(this, R$styleable.Theme_Custom_send_btn_text_color, R$styleable.Theme_Custom_cancel_subscribe_name_text_color);
        linearHeartElement6.initParameter(this.a, this.f, 255, 0, -5.0f, -10.0f);
        AnimSceneElement linearHeartElement7 = new LinearHeartElement(this, R$styleable.Theme_Custom_promptly_attention, 178);
        linearHeartElement7.initParameter(this.a, this.g, 255, 20, 5.0f, -5.0f);
        AnimSceneElement linearHeartElement8 = new LinearHeartElement(this, R$styleable.Theme_Custom_search_label_edit_color_theme, 200);
        linearHeartElement8.initParameter(this.a, this.f, 255, 20, -5.0f, 5.0f);
        AnimSceneElement linearHeartElement9 = new LinearHeartElement(this, 128, 151);
        linearHeartElement9.initParameter(this.a, this.g, 255, 0, 0.0f, -180.0f);
        AnimSceneElement linearHeartElement10 = new LinearHeartElement(this, R$styleable.Theme_Custom_send_btn_text_color, 167);
        linearHeartElement10.initParameter(this.a, this.g, 255, 0, 0.0f, 120.0f);
        AnimSceneElement linearHeartElement11 = new LinearHeartElement(this, R$styleable.Theme_Custom_promptly_attention, R$styleable.Theme_Custom_item_view_color);
        linearHeartElement11.initParameter(this.a, this.g, 255, 0, 0.0f, -180.0f);
        AnimSceneElement linearHeartElement12 = new LinearHeartElement(this, R$styleable.Theme_Custom_search_label_edit_color_theme, R$styleable.Theme_Custom_posts_state_bg);
        linearHeartElement12.initParameter(this.a, this.f, 255, 0, -5.0f, 180.0f);
        AnimSceneElement linearHeartElement13 = new LinearHeartElement(this, R$styleable.Theme_Custom_new_item_login_qq_bg, R$styleable.Theme_Custom_bottom_navigation_text);
        linearHeartElement13.initParameter(this.c, this.d, 200, 0, 10.0f, -80.0f);
        linearHeartElement13 = new LinearHeartElement(this, R$styleable.Theme_Custom_myinfo_night_model_bg, 169);
        linearHeartElement13.initParameter(this.e, this.d, 200, 0, -20.0f, 60.0f);
        linearHeartElement13 = new LinearHeartElement(this, 154, 177);
        linearHeartElement13.initParameter(this.c, this.d, 200, 0, -20.0f, 60.0f);
        linearHeartElement13 = new LinearHeartElement(this, R$styleable.Theme_Custom_label_subscribe_bg_gd_color, R$styleable.Theme_Custom_bt2_color_state);
        linearHeartElement13.initParameter(this.e, this.g, 200, 0, -20.0f, 90.0f);
        addElement(linearHeartElement);
        addElement(linearHeartElement5);
        addElement(linearHeartElement9);
        addElement(linearHeartElement13);
        addElement(linearHeartElement2);
        addElement(linearHeartElement6);
        addElement(linearHeartElement10);
        addElement(linearHeartElement13);
        addElement(linearHeartElement3);
        addElement(linearHeartElement7);
        addElement(linearHeartElement11);
        addElement(linearHeartElement13);
        addElement(linearHeartElement4);
        addElement(linearHeartElement8);
        addElement(linearHeartElement12);
        addElement(linearHeartElement13);
        addElement(FLOWER, new FlowerElement(this));
        addElement(new CurveHeartElement(this));
        addElement(new FlowerHeartElement(this, 65, 81));
        addElement(getAnimSceneElements().size() - 3, FLOWER_HEART, new FlowerHeartElement(this, 82, R$styleable.Theme_Custom_label_list_search_icon));
        addElement(getAnimSceneElements().size() - 4, new PersonElement(this));
        addElement(new StarElement(this, 60, 104, 0, 0));
        addElement(new StarElement(this, 71, R$styleable.Theme_Custom_choose_contact_item_divider_color, -150, -850));
        addElement(new StarElement(this, 71, R$styleable.Theme_Custom_choose_contact_item_divider_color, 500, -850));
        addElement(new StarElement(this, 71, R$styleable.Theme_Custom_choose_contact_item_divider_color, 500, -250));
        addElement(new PetalElement(this, 63, R$styleable.Theme_Custom_label_list_search_icon));
    }
}
