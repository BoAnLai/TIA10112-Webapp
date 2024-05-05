package com.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.UserVO.Identity;

public class UserJDBCDAO implements UserDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/oasis?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO user (user_email,user_password,user_identity,user_company_name"
			+ ",user_register_date,user_last_login,user_last_ip,user_nickname,user_avatar,user_intro) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	@Override
	public void insert(UserVO userVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, userVO.getUserEmail());
			pstmt.setString(2, userVO.getUserPassword());
			pstmt.setString(3, userVO.getUserIdentity().toString());
			pstmt.setString(4, userVO.getUserCompanyName());
			pstmt.setDate(5, userVO.getUserRegisterDate());
			pstmt.setTimestamp(6, userVO.getUserLastLogin());
			pstmt.setString(7, userVO.getUserLastIp());
			pstmt.setString(8, userVO.getUserNickname());
			pstmt.setString(9, userVO.getUserAvatar());
			pstmt.setString(10, userVO.getUserIntro());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	private static final String UPDATE_STMT = 
			"UPDATE user SET user_email=?,user_password=?,user_identity=?,user_company_name=?"
			+ ",user_register_date=?,user_last_login=?,user_last_ip=?,user_nickname=?,user_avatar=?,user_intro=?"
			+ "WHERE user_id = ?";
	@Override
	public void update(Integer userId,UserVO userVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, userVO.getUserEmail());
			pstmt.setString(2, userVO.getUserPassword());
			pstmt.setString(3, userVO.getUserIdentity().toString());
			pstmt.setString(4, userVO.getUserCompanyName());
			pstmt.setDate(5, userVO.getUserRegisterDate());
			pstmt.setTimestamp(6, userVO.getUserLastLogin());
			pstmt.setString(7, userVO.getUserLastIp());
			pstmt.setString(8, userVO.getUserNickname());
			pstmt.setString(9, userVO.getUserAvatar());
			pstmt.setString(10, userVO.getUserIntro());
			pstmt.setInt(11, userId);
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
	private static final String FIND_BY_USER_ID_STMT = "SELECT * FROM user WHERE user_id = ?";
	@Override
	public UserVO findById(Integer userId) {
		// TODO Auto-generated method stub

		UserVO userVO = new UserVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(FIND_BY_USER_ID_STMT);

			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			rs.next();
			userVO.setUserId(userId);
			userVO.setUserEmail(rs.getString(2));
			userVO.setUserPassword(rs.getString(3));
			userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
			userVO.setUserCompanyName(rs.getString(5));
			userVO.setUserRegisterDate(rs.getDate(6));
			userVO.setUserLastLogin(rs.getTimestamp(7));
			userVO.setUserLastIp(rs.getString(8));
			userVO.setUserNickname(rs.getString(9));
			userVO.setUserAvatar(rs.getString(10));
			userVO.setUserIntro(rs.getString(11));
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}
	
	private static final String FIND_BY_USER_EMAIL_STMT = "SELECT * FROM user WHERE user_email LIKE ?";
	@Override
	public List<UserVO> getByEmail(String userEmail) {
		// TODO Auto-generated method stub
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(FIND_BY_USER_EMAIL_STMT);

			String likeTerm = "%" + userEmail + "%";
			pstmt.setString(1, likeTerm);
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}
	
	private static final String GET_ALL_STMT = "SELECT * FROM user";
	@Override
 	public List<UserVO> getAll() {
		// TODO Auto-generated method stub
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}
	
	private static final String GET_USER_IDENTITY_STMT = "SELECT * FROM user WHERE user_identity= ? ";
	@Override
	public List<UserVO> getByIdentity(String identity) {

		// TODO Auto-generated method stub
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_USER_IDENTITY_STMT);
			pstmt.setString(1,identity);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}
	
	private static final String GET_USER_COMPANY_NAME_STMT = "SELECT * FROM user WHERE user_company_name LIKE ?";
	@Override
	public List<UserVO> getByCompanyName(String userCompanyName) {
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_USER_COMPANY_NAME_STMT);
			
			String likeTerm = "%"+userCompanyName+"%";
			pstmt.setString(1,likeTerm);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}
	
	private String GET_USER_LAST_IP_STMT = "SELECT * FROM user WHERE user_last_ip LIKE ?";
	@Override
	public List<UserVO> getByLastIp(String userLastIp) {
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_USER_LAST_IP_STMT);
			
			String likeTerm = "%"+userLastIp+"%";
			pstmt.setString(1,likeTerm);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}

	private String GET_USER_NICKNAME_STMT = "SELECT * FROM user WHERE user_nickname LIKE ?";
	@Override
	public List<UserVO> getByNickname(String userNickname) {
		List<UserVO> userList = new ArrayList();
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_USER_NICKNAME_STMT);
			
			String likeTerm = "%"+userNickname+"%";
			pstmt.setString(1,likeTerm);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				userVO = new UserVO();
				
				userVO.setUserId(rs.getInt(1));
				userVO.setUserEmail(rs.getString(2));
				userVO.setUserPassword(rs.getString(3));
				userVO.setUserIdentity(Identity.valueOf(rs.getString(4)));
				userVO.setUserCompanyName(rs.getString(5));
				userVO.setUserRegisterDate(rs.getDate(6));
				userVO.setUserLastLogin(rs.getTimestamp(7));
				userVO.setUserLastIp(rs.getString(8));
				userVO.setUserNickname(rs.getString(9));
				userVO.setUserAvatar(rs.getString(10));
				userVO.setUserIntro(rs.getString(11));
				
				userList.add(userVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userList;
	}
	
	private String UPDATE_LAST_LOGIN_STMT = "UPDATE user SET user_last_login = CURRENT_TIMESTAMP WHERE user_id = ?";
	@Override
	public void updateLastLogin(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE_LAST_LOGIN_STMT);
			
			pstmt.setInt(1,userId);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		UserJDBCDAO dao = new UserJDBCDAO();
//		
//		UserVO userAdd = new UserVO("11111@gmail.com","123456",Identity.REGULAR,"",Date.valueOf("2024-04-19"),Timestamp.valueOf("2024-04-19 11:22:33"),"127.0.0.1","GGenius","C:\\Users\\T14 Gen 3\\Downloads\\resource\\image\\donkey.jpg","this is user introduction");
//		dao.insert(userAdd);
//		
//		UserVO userUpdate = new UserVO("test123@gmail.com","123456",Identity.REGULAR,"",Date.valueOf("2024-04-19"),Timestamp.valueOf("2024-04-19 11:22:33"),"127.0.0.1","GGenius","C:\\Users\\T14 Gen 3\\Downloads\\resource\\image\\donkey.jpg","this is user introduction");
//		dao.update(3,userUpdate);
//		
//		UserVO userFoundById = dao.findByUserId(1);
//		System.out.println(userFoundById);
//
//		List<UserVO> userFoundByEmail = dao.getByEmail("yahoo");
//		System.out.println(userFoundByEmail);
//		
//		List<UserVO> userList = dao.getAll();
//		System.out.println(userList);
//		
//		List<UserVO> userListRegular = dao.getUserIdentity("REGULAR");
//		System.out.println(userListRegular);
//		
//		List<UserVO> userListCompanyName = dao.getUserCompanyName("ga C");
//		System.out.println(userListCompanyName);
//
//		List<UserVO> userListLastIp = dao.getUserLastIp(".0.1");
//		System.out.println(userListLastIp);
//		
//		List<UserVO> userListNickname = dao.getByNickname("genius");
//		System.out.println(userListNickname);
//	
		dao.updateLastLogin(5);
		
		System.out.println("===main done===");
	}
}