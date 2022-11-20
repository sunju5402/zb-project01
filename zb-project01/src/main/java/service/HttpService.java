package service;

import java.util.*;
import com.google.gson.*;
import dto.WifiInfo;
import okhttp3.*;

public class HttpService {
	private final String url = "http://openapi.seoul.go.kr:8088/684a41426273756e37354846486458/json/TbPublicWifiInfo/";
	private final OkHttpClient client = new OkHttpClient();
	
	public List<WifiInfo> loadWifi() throws Exception {
		List<WifiInfo> list = new ArrayList<>();
//		JsonObject data = null;
		int totalCount = getTotalCount();
		
		for (int i = 1; i <= 1; i++) {
			String apiUrl = url + (i * 1000 - 999) + "/" + (i * 1000);
//			Request request = new Request.Builder()
//					.url(apiUrl)
//					.get()
//					.build();
//			Response response = client.newCall(request).execute();
//			JsonArray rowArray = null;
			Request.Builder builder = new Request.Builder().url(apiUrl).get();
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			JsonArray rowArray = null;
			
			//통신 성공시 Json 파일 파싱해오기
			if (response.isSuccessful()) {
//				data = JsonParser.parseString(response.body().string()).getAsJsonObject();
//				JsonObject result = data.getAsJsonObject("TbPublicWifiInfo");
//				rowArray = result.get("row").getAsJsonArray();
				rowArray = getInfoArray(response.body().string());
			}
			
			for (int j = 0; j < rowArray.size(); j++) {
				Gson gson = new Gson();
				WifiInfo wifi = gson.fromJson(rowArray.get(i), WifiInfo.class);
				list.add(wifi);
			}
		}
		
		return list;
	}
	
	public JsonArray getInfoArray(String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		return result.get("row").getAsJsonArray();
	}
	
	public int getTotalCount() throws Exception {
		String apiUrl = url + "1/1";
		Request.Builder builder = new Request.Builder().url(apiUrl).get();
		Request request = builder.build();
		Response response = client.newCall(request).execute();
		
		int total = 0;
		//통신 성공시 Json 파일 파싱 해오기
		if (response.isSuccessful()) {
			String responseBody = response.body().string();
			total = getTotal(responseBody);
		}
		
		return total;
	}
	
	//Json 파일에서 전체 정보 양 파싱
	public int getTotal(String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		return result.get("list_total_count").getAsInt();
	}	
}
