package org.study.todo.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.study.todo.dao.MemberDao;
import org.study.todo.model.Member;
import org.study.todo.model.TodoRecord;
import org.study.todo.model.TodoUpdate;

/**
 * 해당 클라스는 스프링 컨트롤러로서 기본적인 웹 기능을 맵핑하고 있다
 * 
 *
 * @author jitaek
 *
 */

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	MemberDao dao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("initBinder called...");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "sale_date", 
				new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "member_id", new MemberIdEditor());
	}
	
	/**
	 * 해당 함수는 회원등록 요청처리 함수
	 * 
	 * 
	 * @return 회원등록 페이지 뷰 이름
	 * 
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		logger.info("/register get called...");
		
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public String registerProc(Member member, String member_pw1, String member_pw2) {
		logger.info("/register post called: " + member);
				
		if (member_pw1.length() > 0 && member_pw1.equals(member_pw2)) {
			member.setMember_pw(member_pw1);
			logger.info("member: " + member + "(" + member_pw1 + ", " + member_pw2 +")");
			
			try {
				dao.insertMember(member);
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail2";
			}
			
		} else {
			logger.info("register post: pw incorrect");
			return "fail";
		}

	}
	
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	@ResponseBody
	public String idCheck(String id) {
		logger.info("idCheck: " + id);
		
		if (dao.existMemberId(id)) {
			return "not available";
		} else {
			return "available";
		}

	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET) 
	public String list(Model model) {
		List<Member> list = null;
		try {
			list = dao.readMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String id, Model model) {
		logger.info("update get: id=" + id);
		Member member = null;
		try {
			member = dao.readMember(id);
			model.addAttribute("member", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "updateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateProc(Member member) {
		logger.info("update post: member=" + member);
		
		try {
			if (dao.updateMember(member)) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "fail2";
	}


	@RequestMapping(value="/todoList", method=RequestMethod.GET)
	public String saleList(Model model) {
		try {
			List<TodoRecord> list = dao.listTodo();
			model.addAttribute("todo", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "todoList";
	}

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		logger.info("login get....");
		return "loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(String member_id, String member_pw, Model model) {
		logger.info("login post....");
		
		try {
			Member member = dao.readMember(member_id);
			if (member != null && member.getMember_pw().equals(member_pw)) {
				model.addAttribute("user", member.getMember_name());
			} else {
				model.addAttribute("error", "주어진 정보가 맞지 않습니다.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(String id, Model model) {
		logger.info("update get: id=" + id);
		Member member = null;
		try {
			member = dao.readMember(id);
			model.addAttribute("member", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "todoInsertForm";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertProc(@Valid TodoUpdate update, BindingResult result, Model model) {
		logger.info("todo update: " + update.toString());
		
		if (result.hasErrors()) {
			logger.info("binding errors occurred...");
			
			return "insertForm";
		}
		
		return "redirect:/todoList";
	}
	
}

