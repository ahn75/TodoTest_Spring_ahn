package org.study.todo.model;

import java.sql.Date;

public class TodoRecord {

	private String member_id;
	private String member_name;
	private String product_todo;
	private Date todo_date;
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getProduct_todo() {
		return product_todo;
	}
	public void setProduct_todo(String product_todo) {
		this.product_todo = product_todo;
	}
	public Date getTodo_date() {
		return todo_date;
	}
	public void setTodo_date(Date todo_date) {
		this.todo_date = todo_date;
	}
	
	
	@Override
	public String toString() {
		return "TodoRecord [member_id=" + member_id + ", member_name=" + member_name + ", product_todo=" + product_todo
				+ ", todo_date=" + todo_date + "]";
	}
	
	
	
}
