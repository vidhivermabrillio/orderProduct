package com.brillio.user.model;

import java.util.Date;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "USER_TB")
@ApiModel(value = "User Details", description = "User list with username, password, and isAdmin")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	@ApiModelProperty(name = "Id", value = "Unique User Id", dataType = "int", example = "101")
	private int userId;

	@Column(name = "USER_NAME")
	@ApiModelProperty(name = "name", value = "User name", dataType = "String", example = "John")
	private String userName;

	@Column(name = "PASSWORD")
	@ApiModelProperty(name = "password", value = "User password", dataType = "String", example = "John@123")
	private String password;

	@Column(name = "CREATED_AT")
	@ApiModelProperty(name = "createdAt", value = "User creation time", dataType = "Date", example = "17-05-2021")
	private Date createdAt;

	@Column(name = "IS_ADMIN")
	@ApiModelProperty(name = "isAdmin", value = "User is admin or not", dataType = "boolean", example = "1")
	private boolean isAdmin;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
