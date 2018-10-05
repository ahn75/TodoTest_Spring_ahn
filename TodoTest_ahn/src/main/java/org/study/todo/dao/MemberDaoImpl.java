package org.study.todo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.todo.model.Member;
import org.study.todo.model.TodoRecord;

@Repository
public class MemberDaoImpl implements MemberDao {

	private boolean isInit = false;
	
	private static final String namespace="org.study.mapper.MemberMapper";
	@Autowired
	SqlSession session;
	
	@Override
	public void insertMember(Member member) throws Exception{
		if (isInit == false) {
			init();
		}
		
		session.insert(namespace + ".insertMember", member);
	}

	
	@Override
	public void init() {
		if (existMemberTable() == false) {
			createMemberTable();
		}
		
		isInit = true;
	}
	

	@Override
	public boolean existMemberTable() {
		int count = session.selectOne(namespace + ".existMemberTable");
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	@Override
	public void createMemberTable() {
		session.update(namespace + ".createMemberTable");
	}
	
	@Override
	public boolean existMemberId(String id) {
		int count = session.selectOne(namespace + ".existMemberId", id);
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public List<Member> readMember() throws Exception {
		return session.selectList(namespace + ".readMember");
	}

	@Override
	public Member readMember(String id) throws Exception {
		return session.selectOne(namespace + ".readMemberOne", id);
	}
	
	@Override
	public boolean updateMember(Member member) throws Exception {
		int count = session.update(namespace + ".updateMember", member);
		
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<TodoRecord> listTodo() throws Exception {
		List<TodoRecord> list = session.selectList(namespace + ".todoList");
		
		return list;
	}
	
}

