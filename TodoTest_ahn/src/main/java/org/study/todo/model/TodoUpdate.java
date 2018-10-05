package org.study.todo.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;


public class TodoUpdate {
	
	@NotNull(message="아이디 입력")
	private String member_id;
	
	
	@NotNull(message="날짜 입력")
	private Date todo_date;

	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public Date getSale_date() {
		return todo_date;
	}

	public void setSale_date(Date todo_date) {
		this.todo_date = todo_date;
	}

	@Override
	public String toString() {
		return "TodoUpdate [member_id=" + member_id + ", todo_date=" + todo_date + "]";
	}

}
