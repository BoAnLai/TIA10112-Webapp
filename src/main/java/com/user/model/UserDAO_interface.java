package com.user.model;

import java.sql.SQLException;
import java.util.*;

public interface UserDAO_interface {
          public void insert(UserVO userVO) throws SQLException;
          public void update(Integer userId,UserVO userVO);
          public UserVO findById(Integer userId);
          public List<UserVO> getAll();
          public List<UserVO> getByEmail(String userEmail);
          public List<UserVO> getByIdentity(String identity);
          public List<UserVO> getByCompanyName(String userCompanyName);
          public List<UserVO> getByLastIp(String userLastIp);
          public List<UserVO> getByNickname(String userNickname);

          public void updateLastLogin(Integer userId);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          
}
