<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="file.do"  enctype="multipart/form-data">
			<input type="hidden" name="op"  value="file">
			<td><input  type="file" name="file"  id="file" placeholder="选择图片">
			<input type="submit" value="上传"  ">
			</td>
		</form>
	
	
	
	</div>
</body>
</html>