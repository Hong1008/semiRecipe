<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator" %>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@page import="java.io.File, java.util.List, java.io.IOException" %>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@page import="org.apache.commons.fileupload.FileItem" %>
<%@page import="org.apache.commons.io.output.DeferredFileOutputStream" %>
<%@page import="org.apache.http.protocol.HttpContext" %>
<%@page import="java.sql.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
<%
	// multipart로 전송되었는가 체크
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if(isMultipart){
		//설정단계
		File temproraryDir = new File("c:/tmp");	// 업로드된 파일의 임시저장 폴더
		
		String realDir = "C:/Users/user1/git/semiRecipe/WebContent/ckeditor/upload/";	// 실제 저장될 파일 경로
		String sFunc = request.getParameter("CKEditorFuncNum");
		String realUrl = request.getParameter("realUrl");
		
		// 디스크 기반의 파일 아이템 팩토리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1*1024*1024); // 최대 메모리 크기
		factory.setRepository(temproraryDir);	// 임시저장폴더 연결
		
		// 구현단계
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(10*1024*1024);	// 최대 업로드 크기
		List<FileItem> items = upload.parseRequest(request);	// 이 부분에서 파일이 생성
		Iterator iter = items.iterator();
		
		while(iter.hasNext()){	
			FileItem fileItem = (FileItem)iter.next();
			
			if(fileItem.isFormField()){ // File 컴포넌트가 아닌 일반 컴포넌트인 경우
				out.println(fileItem.getFieldName()+"="+fileItem.getString("UTF-8")+"<br/>");
			}else{
				if(fileItem.getSize()>0){	// 파일이 업로드되었나 안되었나 체크
					String fieldName = fileItem.getFieldName();
					String fileName = fileItem.getName();
					String contentType = fileItem.getContentType();
					boolean isInMemery = fileItem.isInMemory();
					long sizeInBytes = fileItem.getSize();
					
					String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());	// 현재시간
					
					int i = -1;
					i = fileName.lastIndexOf(".");	// 파일 확장자 위치
					String realFileName = now + fileName.substring(i, fileName.length());	// 현재시간과 확장자 합치기
					
					File oldFile = new File(realDir + fileName);	// 원본파일 불러오기
					File newFile = new File(realDir + realFileName);		// 현재 날짜 파일명
					
					oldFile.renameTo(newFile);	// 파일명 변경
					
				//	out.println("[realDir] : "+realDir + "<br/>");
				//	out.println("[fieldName] : "+fieldName + "<br/>");
				//	out.println("[FileName] : "+realFileName + "<br/>");
				//	out.println("[contentType] : "+contentType + "<br/>");
					
				//	out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + sFunc + ", '"+ realUrl + fileName + "', '완료');</script>");

				
				//	out.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
					out.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+realDir+realFileName+"\"}");
				//	out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + sFunc + ", '"+ realUrl + fileName + "', '완료');</script>");
					
				
				
					try{
						File uploadedFile = new File(realDir, realFileName);
						fileItem.write(uploadedFile);		// 실제 디렉토리에 카피
					}catch(IOException ex){
						out.println("error :"+ex+"<br/>");
					}finally {
						fileItem.delete();	
					}
				}
			}
		}
		
	}else{
		// out.println("인코딩 타입이 multipart/form-data가 아님.");
	}

%>
</body>
</html>