package com.jsq.forum.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Data
public class Answer {
    private long id;
    private String content; //内容
    private boolean useful;
    private Date createdDate; //创建时间
    private String code;   //代码
    private Integer idTopic; //帖子id
    private Integer idUser;  //userId
    public String displayParsedCreatedDate() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(this.createdDate);
    }

    public String displayCode() {
        if (Optional.ofNullable(code).isPresent())
            return Optional.ofNullable(code).get();
        else
            return "";
    }

    public String displayBeginning() {
        return (this.content.length() < 32) ? this.content.concat("...") : this.content.substring(0, 30).concat("...");
    }
}
