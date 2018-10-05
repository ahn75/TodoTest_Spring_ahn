package org.study.todo.dao;

import java.util.List;

import org.study.todo.model.Member;
// import org.study.todo.model.SaleRecord;
import org.study.todo.model.TodoRecord;

public interface MemberDao {

	public void init();
	
	public void insertMember(Member member) throws Exception;
	
	public boolean existMemberTable();
	
	public void createMemberTable();
	
	public boolean existMemberId(String id);
	
	public List<Member> readMember() throws Exception;
	
	public Member readMember(String id) throws Exception;
	
	public boolean updateMember(Member member) throws Exception;
	
	public List<TodoRecord> listTodo() throws Exception;

}

