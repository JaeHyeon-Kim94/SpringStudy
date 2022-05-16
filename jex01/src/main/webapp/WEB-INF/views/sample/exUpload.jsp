<%--
  Created by IntelliJ IDEA.
  User: daxxx
  Date: 2022-05-16
  Time: 오후 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
    <form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
        <div>
            <input type='file' name="files">
        </div>
        <div>
            <input type='file' name="files">
        </div>
        <div>
            <input type='file' name="files">
        </div>
        <div>
            <input type='file' name="files">
        </div>
        <div>
            <input type='file' name="files">
        </div>
        <div>
            <input type='submit'>
        </div>
    </form>
</body>
</html>
