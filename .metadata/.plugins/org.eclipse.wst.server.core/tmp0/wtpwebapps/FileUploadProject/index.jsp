<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="basic1.css">
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>File Upload</title>
</head>
<body>
<s:if test="hasActionErrors()">
<s:actionerror/>
</s:if>
<s:if test="hasActionMessages()">
<s:actionmessage/>
</s:if>
<s:form action="UploadFile.action" namespace="/" method="post" enctype="multipart/form-data" >
<s:file label="Select a file to upload" name="upload" />
<s:submit value="submit" name="submit" />
</s:form>
</body>
</html>