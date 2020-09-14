package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberContentController implements Controller{

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		MemberVO vo = dao.memberContent(num);
		//memberContent.jsp 로 이동시켜 (객체 바인딩!)
		request.setAttribute("vo", vo);
		
		
		return "member/memberContent.jsp";
	}

}
