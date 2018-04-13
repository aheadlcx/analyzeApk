package com.budejie.www.bean;

import java.io.Serializable;

public class CommentItemForNews implements Serializable {
    public CommentAudio audio = new CommentAudio();
    public String content;
    public String ctime;
    public String data_id;
    public CommentImage gif = new CommentImage();
    public int hate_count;
    public String id;
    public CommentImage image = new CommentImage();
    public int like_count;
    public int status;
    public String type;
    public User user = new User();
    public CommentVideo video = new CommentVideo();

    public boolean commentIsDelete() {
        if (this.status == 1 || this.status == 4) {
            return true;
        }
        return false;
    }
}
