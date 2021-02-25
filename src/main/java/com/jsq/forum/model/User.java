package com.jsq.forum.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import lombok.Data;


@Data
public class User{
	private Long id; //用户的userId
	private String username; //用户名
	private String password;//用户密码
	private String introduction;//用户的介绍
	private Date createdDate;//用户的注册时间

	public String displayContentOfOptional() {
		if (Optional.ofNullable(introduction).isPresent())
			return Optional.ofNullable(introduction).get();
		else
			return "";
	}

	public String displayParsedDate() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(this.createdDate);
	}
}
