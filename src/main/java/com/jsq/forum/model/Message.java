package com.jsq.forum.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private long id;
    private long from_id;
    private long to_id;
    private String content;
    private Date created_Date;
    private long id_topic;
    private int has_read;


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from_id=" + from_id +
                ", to_id=" + to_id +
                ", content='" + content + '\'' +
                ", created_date=" + created_Date +
                ", id_topic=" + id_topic +
                ", Has_Read=" + has_read +
                '}';
    }
}
