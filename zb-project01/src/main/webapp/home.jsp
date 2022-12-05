<%@page import="java.time.LocalDateTime"%>
<%@page import="dto.History"%>
<%@page import="service.*"%>
<%@page import="dto.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
	<style type="text/css">
		table {
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid black;
            padding: 10px;
        }
        tr:nth-child(odd){background-color: #F4F7FC;}
        th {
            padding-top: 10px;
            padding-bottom: 10px;
            text-align: center;
            background-color: #BFC9FC;
            color: #3939FB;
        }
	</style>
</head>
<body>
    <%
    	Double lat = 0.0;
    	Double lnt = 0.0;
    
    	try{
		    lat = Double.parseDouble(request.getParameter("lat"));
		    lnt = Double.parseDouble(request.getParameter("lnt"));
    	} catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <h1>와이파이 정보 구하기</h1>
    <div>
    	<a href="home.jsp">홈</a> | 
     	<a href="history.jsp">위치 히스토리 목록</a> |
     	<a href="load-wifi.jsp"> Open API 와이파이 정보 가져오기</a>
     	<p></p>
    </div>
    
    <form method="get" action="home.jsp">
        <label for="lat">LAT :</label>
	    <input id="lat" type="text" value=<%= lat %>>
	    <label for="lnt">, LNT :</label>
	    <input id="lnt" type="text" value=<%= lnt %>>
        <button type="button" onclick=getMyLocation()>내 위치 가져오기</button>
        <button type="button" onclick=getNearWifiInfo()>근처 Wifi 정보 가져오기</button>
    </form>

    <br/>
    <table>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
            <%
	            HistoryService hs = new HistoryService();
	    	    History history = new History();
	    	    List<WifiInfo> list = null;
	    	    String wifiClick = request.getParameter("wifiClick");
	    	    boolean isClicked = wifiClick != null ? (wifiClick.equals("true") ? true : false) : false;
	    	    
            	if (isClicked) {
	              WifiInfoService ws = new WifiInfoService();
	              list = ws.nearWifiInfo(lat, lnt);
	              
	              //if (list != null) {
	                
	                for (WifiInfo wifi : list) { 
	        %>
	          	      <tr>	
	                      <td><%=wifi.getDistance()%></td>
	                      <td><%=wifi.getX_SWIFI_MGR_NO()%></td>
	                      <td><%=wifi.getX_SWIFI_WRDOFC()%></td>
	                      <td><%=wifi.getX_SWIFI_MAIN_NM()%></td>
	                      <td><%=wifi.getX_SWIFI_ADRES1()%></td>
	                      <td><%=wifi.getX_SWIFI_ADRES2()%></td>
	                      <td><%=wifi.getX_SWIFI_INSTL_FLOOR()%></td>
	                      <td><%=wifi.getX_SWIFI_INSTL_TY()%></td>
	                      <td><%=wifi.getX_SWIFI_INSTL_MBY()%></td>
	                      <td><%=wifi.getX_SWIFI_SVC_SE()%></td>
	                      <td><%=wifi.getX_SWIFI_CMCWR()%></td>
	                      <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%></td>
	                      <td><%=wifi.getX_SWIFI_INOUT_DOOR()%></td>
	                      <td><%=wifi.getX_SWIFI_REMARS3()%></td>
	                      <td><%=wifi.getLAT()%></td>
	                      <td><%=wifi.getLNT()%></td>
	                      <td><%=wifi.getWORK_DTTM()%></td>
	                    </tr>	
	        <%  	}
	              
	                history.setLat(lat);
	                history.setLnt(lnt);
	                history.setHistoryDate(LocalDateTime.now().toString());
	           		hs.saveHistory(history);
                } else { %>
                
           		<tr>
            		<td colspan="17" style="text-align: center;">위치 정보를 입력한 후에 조회해 주세요.</td>
        		</tr>
            
            <%    	
                }
            %>
    </table>
    
	<script>
		function getMyLocation() {
			if (!navigator.geolocation) {
		        alert("GPS를 지원하지 않습니다.");
		    }
		    navigator.geolocation.getCurrentPosition(position, function(error){
		    	console.error(error);	
		    }, {
		    	enableHighAccuracy: false,
		        maximumAge: 0,
		        timeout: Infinity
		    });
		}
		function position(pos) {
	        var lat = pos.coords.latitude;
	        var lnt = pos.coords.longitude;
	        location.href = "home.jsp?lat=" + lat + "&lnt=" + lnt + "&wifiClick=false"; 
	    }
	    function getNearWifiInfo() {
	        const lat = document.getElementById("lat").value;
	        const lnt = document.getElementById("lnt").value;
	        if (lat === "" || lnt === "") {
	            alert("위치정보를 입력하세요");
	            return;
	        }
	        
	       location.href = "home.jsp?lat=" + lat + "&lnt=" + lnt + "&wifiClick=true"; 
	    }
	    
	</script>
</body>
</html>