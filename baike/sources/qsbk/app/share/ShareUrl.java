package qsbk.app.share;

public class ShareUrl {
    public static final String GRAPH_ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=token&display=mobile&client_id=%s&scope=%s&redirect_uri=%s&status_userip=%s&status_os=%s&status_machine=%s&status_version=%s";
    public static String currentWeibo = "";
    public static final String qqZoneUrl = "https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=100251437&redirect_uri=http://com.qsbk.app&scope=get_user_info,add_share,add_t,add_pic_t&display=mobile";
    public static final String renrenUrl = "https://graph.renren.com/oauth/authorize?client_id=262eefb6906e412d848887f29b2939a9&response_type=token&redirect_uri=http://graph.renren.com/oauth/login_success.html&display=touch";
    public static final String sinaUrl = "https://api.weibo.com/oauth2/authorize?response_type=token&client_id=1383617092&redirect_uri=http://sina.qsbk.app&display=mobile";
    public static String weiboUrl = "";
}
