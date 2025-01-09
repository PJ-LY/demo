package com.jasonpeng.demo.util.wx;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WeChatMessageSender {
    public static void sendMessage(String accessToken, String userId, String agentId, String content) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // 构建 JSON 消息体
            String message = "{"
                    + "\"touser\": \"" + userId + "\","
                    + "\"msgtype\": \"text\","
                    + "\"agentid\": \"" + agentId + "\","
                    + "\"text\": {\"content\": \"" + content + "\"},"
                    + "\"safe\":0"
                    + "}";

            // 发送消息
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(message.getBytes(StandardCharsets.UTF_8));
            os.close();

            // 获取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            log.info("Response: " + response);
        } catch (Exception e) {
            log.error("Error sending message: " + e.getMessage());
        }
    }
}
