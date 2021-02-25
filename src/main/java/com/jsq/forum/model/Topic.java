package com.jsq.forum.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class Topic {
    private long id;
    private String category;
    private String content;
    private String code;
    private Date createdDate;
    private String title;
    private Integer idUser;
    private User user;
    private List<Answer> answers;

    public String displayParsedCreatedDate(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(this.createdDate);
    }
    public String displayCode() {
        if (Optional.ofNullable(code).isPresent())
            return Optional.ofNullable(code).get();
        else
            return "";
    }

}
