package qsbk.app.core.model;

import java.util.List;

public class LoginResponse {
    public List<LoginData> feeds;

    public LoginResponse(List<LoginData> list) {
        this.feeds = list;
    }
}
