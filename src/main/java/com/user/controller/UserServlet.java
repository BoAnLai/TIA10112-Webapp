package com.user.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.user.model.UserJNDIDAO;
import com.user.model.UserService;
import com.user.model.UserVO;

@MultipartConfig
public class UserServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		System.out.println(req.getRequestURL());

		if(req.getServletPath().equals("/user/register")) {
			req.setCharacterEncoding("UTF-8"); // 處理中文檔名
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=Big5");
			PrintWriter out = res.getWriter();
			System.out.println("ContentType="+req.getContentType()); // 測試用

			String saveDirectory = "/user/avatarUploaded";
			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println("realPath="+realPath); // 測試用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists())
				 fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

			Part part = req.getPart("avatar"); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			
			String submittedFilename = part.getSubmittedFileName();
			String filename = new String(submittedFilename.getBytes("ISO-8859-1"), "UTF-8");
			
			String imgSrcPath = req.getContextPath()+saveDirectory + "/" + filename;
			
			if (filename!= null && filename.length()!=0 && part.getContentType()!=null) {
				
				File f = new File(fsaveDirectory, filename);
				out.println("File: " + f);

				// 利用File物件,寫入目地目錄,上傳成功
				part.write(f.toString());
				
				out.println("<br><img src=\""+imgSrcPath+"\">");
				out.println();
				out.println("</PRE>");
			}
			
			UserVO user = new UserVO();
			user.setUserEmail(req.getParameter("email"));
			user.setUserPassword(req.getParameter("password"));
			user.setUserNickname(req.getParameter("nickname"));
			user.setUserIntro(req.getParameter("intro"));
			user.setUserAvatar(imgSrcPath);
			
			UserService userService = new UserService();
			
			try {
				userService.addUser(user);				
			}catch(SQLException e) {;
			}
			
			System.out.println(user);
			res.sendRedirect("userList.jsp");
			
		} //if(/user/register)
		
		if (req.getServletPath().equals("/user/update")) { // 來自listAllEmp.jsp的請求

//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer userId = Integer.valueOf(req.getParameter("userId"));
				
				/***************************2.開始查詢資料****************************************/
				UserJNDIDAO userDAO = new UserJNDIDAO();
				UserVO user = userDAO.findById(userId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("user", user);         // 資料庫取出的empVO物件,存入req
				String url = "/user/userUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		} //if(user/update)
		
		if(req.getServletPath().equals("/user/change")) {
			req.setCharacterEncoding("UTF-8"); // 處理中文檔名
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=Big5");
			String saveDirectory = "/user/avatarUploaded";
			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println("realPath="+realPath); // 測試用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists())
				 fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

			Part part = req.getPart("avatar"); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			
			String submittedFilename = part.getSubmittedFileName();
			String filename = new String(submittedFilename.getBytes("ISO-8859-1"), "UTF-8");
			
			String imgSrcPath = req.getContextPath()+saveDirectory + "/" + filename;
			
			if (filename!= null && filename.length()!=0 && part.getContentType()!=null) {
				File f = new File(fsaveDirectory, filename);
				part.write(f.toString());
			}
			
			UserVO user = new UserVO();
			user.setUserEmail(req.getParameter("email"));
			user.setUserPassword(req.getParameter("password"));
			user.setUserNickname(req.getParameter("nickname"));
			user.setUserIntro(req.getParameter("intro"));
			user.setUserAvatar(imgSrcPath);
			
			UserJNDIDAO userDAO = new UserJNDIDAO();

			userDAO.update(Integer.valueOf(req.getParameter("userId")),user);				
			
			
			System.out.println(user);
			res.sendRedirect("userList.jsp");
			
		} //if(/user/register)
		
	} //doPost 
} //Class
