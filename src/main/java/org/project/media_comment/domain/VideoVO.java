package org.project.media_comment.domain;

import java.util.Date;

/**
 * Created by hs on 2017-07-22.
 */
public class VideoVO {

    private int video_id;
    private String video_title;
    private int user_id;
    private Date video_post_date;
    private String video_url;
    private String video_content;
    private int video_hit;

    public VideoVO() {
    }

    public String getVideo_content() {
        return video_content;
    }

    public void setVideo_content(String video_content) {
        this.video_content = video_content;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getVideo_post_date() {
        return video_post_date;
    }

    public void setVideo_post_date(Date video_post_date) {
        this.video_post_date = video_post_date;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getVideo_hit() {
        return video_hit;
    }

    public void setVideo_hit(int video_hit) {
        this.video_hit = video_hit;
    }
}
