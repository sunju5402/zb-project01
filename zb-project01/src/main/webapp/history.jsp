<%@page import="dto.History"%>
<%@page import="java.util.List"%>
<%@page import="service.HistoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<style>
        #history {
            border-collapse: collapse;
            width: 100%; 
        }
        #history td, #history th {
            border: 1px solid black;
            padding: 8px;
        }
        #history tr:nth-child(odd){background-color: #F4F7FC;}
        #history th {
            padding-top: 10px;
            padding-bottom: 10px;
            text-align: center;
            background-color: #BFC9FC;
            color: #3939FB;
        }
    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<div>
	<a href="home.jsp">홈</a> |
	<a href="history.jsp">위치 히스토리 목록</a> |
	<a href="load-wifi.jsp"> Open API 와이파이 정보 가져오기</a>
	<p></p>
</div>
<table id="history">
	<thead>
	    <tr>
	        <th>ID</th>
	        <th>X좌표</th>
	        <th>Y좌표</th>
	        <th>조회일자</th>
	        <th>비고</th>
	    </tr>
	</thead>
	<tbody>
   	<%
   		HistoryService hs = new HistoryService();
	    List<History> list = hs.showHistory();
	    String id = request.getParameter("id");
	    if(id != null) {
	        hs.withrawHistory(Long.parseLong(id));
	 %>
	 <script type="text/javascript">
	    alert("삭제 성공");
	   	location.href="history.jsp"
	 </script>
	 <%
	    }
	        
         if (list != null && list.size() != 0) {
        	 for (History history : list) { %>
        	 <tr>
				<td><%=history.getId()%></td>
				<td><%=history.getLat()%></td>
				<td><%=history.getLnt()%></td>
				<td><%=history.getHistoryDate()%></td>			  
				 <td align="center">
				     <a href="history.jsp?id=<%=history.getId()%>">
				        <button type="button">삭제</button>
				     </a>
				  </td> 
			</tr>
        
     <%      
     		}
        } else {  %>
	          <tr>
	          	<td colspan="5" style="text-align: center;">위치 조회한 히스토리가 없습니다.</td>
	          </tr>
     <%
        }
     %>
    </tbody>
</table>
</body>
</html>