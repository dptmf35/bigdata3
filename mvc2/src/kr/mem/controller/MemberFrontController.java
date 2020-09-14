package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;

public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		//1. 어떤 요청인지 파악하는 작업 ->
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);
		String ctxPath = request.getContextPath(); // ---- > mvc1
		System.out.println(ctxPath);
		//client 요청 명령
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);
		//각 요청에따라 처리하기!
		Controller controller = null;
		String nextView = null;
		HandlerMapping mappings =new HandlerMapping();
		
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		
		//핸들러 매핑(Handler mapping)
		// list.do --> MemberListCon
		// insert.do --> MemberInsertCon
		// insertForm.do --> MemberInsertCon
		// delete.do --> MemberdeleteCon
		
	
//		if(command.equals("/list.do")) {
//			controller = new MemberListController();
//			//memberlistcon에서 넘겨받은 view 주소
//			nextView = controller.requestHandle(request,response);
//		}else if(command.equals("/insert.do")) {
//			controller = new MemberInsertController();
//			nextView = controller.requestHandle(request, response);
//		}else if(command.equals("/insertForm.do")) {
//			//response.sendRedirect("member/member.html");
//			controller = new MemberInsertFormController();
//			nextView = controller.requestHandle(request, response);
//		}else if(command.equals("/delete.do")) {
//			controller = new MemberDeleteController();
//			nextView = controller.requestHandle(request, response);
//		}
	//-----------------------------------------------
	//값이 있으면 redirect, 없으면 forwarding
		
		
		//view 페이지 연동
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) { //해당하는 문자열이 없으면 -1 반환
				String[] sp = nextView.split(":"); //sp[0] : sp[1]
				response.sendRedirect(sp[1]);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
	
	}

}
