<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TimeSheet</title>
</head>
<body>
  <form action="/TimeSheetApp/LoginServlet" method="post">
  ユーザーID：<input type="text" name="userId"><br>
  パスワード:<input type="password" name="pass"><br>
  <input type="submit" value="ログイン">
  </form>
<a href="/TimeSheetApp/RegisterServlet">
    <button type="button">新規ユーザー</button>
</a>
</body>
</html>