<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.user.model.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>


<%
	UserVO user = (UserVO) request.getAttribute("user");
%>

    <h1>修改</h1>
    <div class="register_input_div">
        <form action="register" method="POST" enctype="multipart/form-data">
            <table>
                <tbody>
                    <tr id="register_input_email">
                        <th>
                            <label for="email">信箱</label>
                        </th>
                        <td>
                            <input name="email" type="text" placeholder="必填" required value="<%=user.getUserEmail()%>">
                        </td>
                    </tr>
                    <tr id="register_input_password">
                        <th>
                            <label for="password">密碼</label>
                        </th>
                        <td>
                            <input name="password" type="password" placeholder="必填" value="<%=user.getUserPassword()%>">
                        </td>
                    </tr>
                    <tr id="register_nickname">
                        <th>
                            <label for="nickname">暱稱</label>
                        </th>
                        <td>
                            <input name="nickname" type="text" value="<%=user.getUserNickname()%>">
                        </td>
                    </tr>
                    <tr id="register_intro">
                        <th>
                            <label for="intro">自我介紹</label>
                        </th>
                        <td>
                            <textarea name="intro" id="" cols="30" rows="10"><%=user.getUserIntro()%></textarea>
                        </td>
                    </tr>
                    <tr id="register_avatar">
                        <th>
                            <label for="avatat">圖片(大頭照)</label>
                        </th>
                        <td>
                            <input name="avatar" type="file" value="<%=user.getUserAvatar()%>">
                            <div class="imgPreviewDiv" hidden>
                                <img class="imgPreviewPlaceholder" src="https://via.placeholder.com/300x200" alt="">
                                <img class="imgUpload" src="" alt="" hidden>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="修改">
            <input type="reset" value="清除資料">
        </form>
    </div>
    
    <script src="../jquery/jquery-3.7.1.min.js"></script>
    <script src="js/userRegister.js"></script>
</body>

</html>