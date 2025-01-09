package com.jasonpeng.demo.util.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class WeChatTokenFetcher {
    public static String getAccessToken(String corpId, String corpSecret) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // 解析返回的 JSON 数据，获取 access_token
            JSONObject jsonResponse = JSONObject.parseObject(response.toString());
            return jsonResponse.getString("access_token");
        } catch (Exception e) {
            log.error("Error fetching access token: " + e.getMessage());
        }
        return null;
    }
}
