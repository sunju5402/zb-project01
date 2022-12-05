# zb-project01
# 설명 & 기능
 "내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발"
 - 서울시 공공와이파이 서비스로부터 open api 와이파이 정보 가져와 몇개인지 표시하기
 - 그 정보들을 db에 저장하기 
 - javascript를 이용하여 내 위치 가져오기
 - 위도와 경도를 이용해 20개의 근처 wifi 정보 가져오기
 - 근처 와이파이 정보를 보기를 누를 때마다 위치 히스토리 목록에 x좌표, y좌표, 조회일자 등록하기

# 구현
- src/main/java
  - dto
    - History : 위치 히스토리에 저장할 데이터공간(id, 위도, 경도, 조회일자) 
    - WifiInfo : open api로 불러온 와이파이 정보 저장할 데이터공간
  - sevice
    - ConnectionService : 와이파이와 히스토리에 쓰일 db를 연결하기 위한 connection 서비스
    - HttpService : httpClient 처리를 위해서 okhttp3 이용, 
                    Json 처리를 위한 gson 이용하여 자동으로 wifiInfo에 데이터 담는 기능,
                    openapi를 통해 받아온 wifi 총 개수 알아오는 기능
    - WifiInfoService : 와이파이 정보 db에 저장, 와이파이 정보 db에서 가져오기
    - HistoryService : 위치를 이용해 근처 와이파이를 조회한 정보 db에 등록, db에서 정보 가져오기, db에서 정보 삭제하기
    
- src/main/webapp
  - home.jsp
    - 홈 화면, 와이파이 정보가져오기, 히스토리 목록가기, 내 위치 불러오기, 근처 와이파이 보기
    - 근처 와이파이 보기를 클릭했을 때, 파라미터로 wifiClick을 보내 ture면 목록 나열, false면 위치 정보 입력 후 조회
  - load-wifi.jsp
    - openapi를 통해 와이파이 정보 몇개인지 가져와 화면에 보여주기
  - history.jsp
    -  db에 저장된 history 목록 보여주기
    -  삭제를 누르면 "삭제 성공" 이라는 문구와 함께 히스토리 목록에서 삭제

# 개발환경
 - Java(JDK 11)
 - Eclipse
 - Tomcat v9.0
 - gson
 - okhttp3
 - sqlite-jdbc
 - lombok
 - HTML
 - CSS
 - JS
