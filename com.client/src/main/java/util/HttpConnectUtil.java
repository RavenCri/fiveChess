package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpConnectUtil {
		public static String requestHTTP(String host,Map<String,String> map,String type){
			StringBuilder param = new StringBuilder();
			if(map!=null) {
				param.append("?");
				for(Map.Entry<String, String> entry: map.entrySet()) {
					
					try {
						//  账号有可能为中文，编码为utf-8
						param.append(entry.getKey()+"="+URLEncoder.encode(entry.getValue(),"utf-8")+"&");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				param.deleteCharAt(param.length()-1);
			}
			
			try {
				URL url = new URL(host+param.toString());
			
				System.out.println(host+param.toString());
				if(type.equalsIgnoreCase("POST")) {
					// 打开HTTP连接对象
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true); // 设置该连接是可以输出的
					connection.setDoInput(true);      //需要输入
					connection.setRequestMethod("POST"); // 设置请求方式
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
					connection.setRequestProperty("Charset", "UTF-8");
					if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){ 
						//获取到字符输入流
						BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
						String line = null;
						
						StringBuilder result = new StringBuilder();	
						while ((line = br.readLine()) != null) { // 读取数据
							 result.append(line + "\n"); 
						}
						//连接断开
						connection.disconnect();
						//返回接收的数据
						return result.toString();
					}
				}else if (type.equalsIgnoreCase("GET")) {
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
			return "error";
		}
}
