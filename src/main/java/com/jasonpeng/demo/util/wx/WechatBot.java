package com.jasonpeng.demo.util.wx;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WechatBot {
    public static void main(String[] args) {
//        try {
//            String apiUrl = "http://127.0.0.1:5000/send_message";
//            String jsonPayload = "{\"user_name\": \"小晶晶\", \"message\": \"这是一个测试消息\"}";
//
//            URL url = new URL(apiUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            conn.setDoOutput(true);
//
//            OutputStream os = conn.getOutputStream();
//            os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
//            os.close();
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == 200) {
//                System.out.println("消息发送成功");
//            } else {
//                System.out.println("消息发送失败，状态码：" + responseCode);
//            }
//        } catch (Exception e) {
//            System.out.println("发送消息时发生错误：" + e.getMessage());
//        }
        try {
            String apiUrl = "http://127.0.0.1:5000/send_message_group";
            String jsonPayload = "{\"group_name\": \"团伙作案\", \"message\": \"这是一个测试消息\"}";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("消息发送成功");
            } else {
                System.out.println("消息发送失败，状态码：" + responseCode);
            }
        } catch (Exception e) {
            System.out.println("发送消息时发生错误：" + e.getMessage());
        }
    }
}
