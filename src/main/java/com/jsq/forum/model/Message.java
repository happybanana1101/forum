package com.jsq.forum.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private long id;
    private long from_id;
    private long to_id;
    private String content;
    private Date created_date;
    private long id_topic;
    private boolean has_read;
}
