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

    <h1>�ק�</h1>
    <div class="register_input_div">
        <form action="register" method="POST" enctype="multipart/form-data">
            <table>
                <tbody>
                    <tr id="register_input_email">
                        <th>
                            <label for="email">�H�c</label>
                        </th>
                        <td>
                            <input name="email" type="text" placeholder="����" required value="<%=user.getUserEmail()%>">
                        </td>
                    </tr>
                    <tr id="register_input_password">
                        <th>
                            <label for="password">�K�X</label>
                        </th>
                        <td>
                            <input name="password" type="password" placeholder="����" value="<%=user.getUserPassword()%>">
                        </td>
                    </tr>
                    <tr id="register_nickname">
                        <th>
                            <label for="nickname">�ʺ�</label>
                        </th>
                        <td>
                            <input name="nickname" type="text" value="<%=user.getUserNickname()%>">
                        </td>
                    </tr>
                    <tr id="register_intro">
                        <th>
                            <label for="intro">�ۧڤ���</label>
                        </th>
                        <td>
                            <textarea name="intro" id="" cols="30" rows="10"><%=user.getUserIntro()%></textarea>
                        </td>
                    </tr>
                    <tr id="register_avatar">
                        <th>
                            <label for="avatat">�Ϥ�(�j�Y��)</label>
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
            <input type="submit" value="�ק�">
            <input type="reset" value="�M�����">
        </form>
    </div>
    
    <script src="../jquery/jquery-3.7.1.min.js"></script>
    <script src="js/userRegister.js"></script>
</body>

</html>