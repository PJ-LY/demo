package com.jasonpeng.demo.util.wx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WXMain {
    public static void main(String[] args) {
        String corpId = "";//企业ID
        String corpSecret = "";
        String agentId = "1000013"; // 应用的 agentId 1000002
        String userId = ""; // 企业微信用户的 userId，绑定到个人微信
        String content = "Hello, this is a test message from your Java application!";

        // 获取 access token
        String accessToken = WeChatTokenFetcher.getAccessToken(corpId, corpSecret);

        // 发送消息
        if (accessToken != null) {
            WeChatMessageSender.sendMessage(accessToken, userId, agentId, content);
        } else {
            log.error("Failed to fetch access token.");
        }
    }
}
