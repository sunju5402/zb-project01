package service;

import java.util.*;
import com.google.gson.*;
import dto.WifiInfo;
import okhttp3.*;

public class HttpService {
	private final String url = "http://openapi.seoul.go.kr:8088/684a41426273756e37354846486458/json/TbPublicWifiInfo/";
	private final OkHttpClient client = new OkHttpClient();
	private int totalWifi = 0; 
	
	public List<WifiInfo> loadWifi() throws Exception {
		List<WifiInfo> list = new ArrayList<>();
		JsonObject jo = null;
		
		// data 요청은 한번에 1000건을 넘을 수 없음.
		// db에 넣을 수 있는 양이 약 10000건 정도.
		for (int i = 1; i <= 10; i++) {
			String apiUrl = url + (i * 1000 - 999) + "/" + (i * 1000);
			Request request = new Request.Builder()
					.url(apiUrl)
					.get()
					.build();
			Response response = client.newCall(request).execute();
			JsonArray rowArray = null;
				
			if (response.isSuccessful()) {
				jo = JsonParser.parseString(response.body().string()).getAsJsonObject();
				JsonObject result = jo.getAsJsonObject("TbPublicWifiInfo");
				rowArray = result.get("row").getAsJsonArray();
				totalWifi = result.get("list_total_count").getAsInt();
			}
			
			for (int j = 0; j < rowArray.size(); j++) {
				Gson gson = new Gson();
				WifiInfo wifi = gson.fromJson(rowArray.get(j), WifiInfo.class);
				list.add(wifi);
			}
		}
		
		return list;
	}
	
	// wifi 총 개수 
	public int getTotalWifi() {
		return totalWifi;
	}
}
