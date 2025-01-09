package com.jasonpeng.demo.util.wx;

import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WechatSendGroupService {
    private static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_msg_template?access_token=";

    public static void sendMessage(String accessToken, String sender, String externalUserIds, String messageContent) {
        try {
            // 构建请求URL
            String urlStr = SEND_MESSAGE_URL + accessToken;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 构建请求体
            String jsonInputString = String.format(
                    "{\"sender\":\"%s\", \"external_userid\":\"%s\", \"msgtype\":\"text\", \"text\":{\"content\":\"%s\"}}",
                    sender, externalUserIds, messageContent
            );

            // 发送请求
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            String responseMessage = connection.getResponseMessage();
            log.info("Response message: " + responseMessage);

            // 获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Failed to send message. Response code: " + responseCode);
            }

        } catch (Exception e) {
            log.error("Error sending message: " + e.getMessage());
        }
    }

    // 示例调用
    public static void main(String[] args) {
        String corpId = "";//企业ID
        String corpSecret = "";
        String accessToken = WeChatTokenFetcher.getAccessToken(corpId, corpSecret);
        String sender = ""; // 替换为发件人的userid
        String externalUserIds = ""; // 替换为外部联系人的userid
        String messageContent = "这是发送的消息内容"; // 替换为你的消息内容

        sendMessage(accessToken, sender, externalUserIds, messageContent);
    }
}
