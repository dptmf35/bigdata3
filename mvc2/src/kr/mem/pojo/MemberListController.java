package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller{

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		
		//객체 바인딩
		request.setAttribute("list", list);
		
		//포워딩하기위한 view 페이지를 넘김
//		return "/WEB-INF/views/member/memberList.jsp"; //"memberList.jsp"
		return "member/memberList.jsp"; //"memberList.jsp"
	}

	
}
