package com.li.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory") // 使用MemoryChatAssistantConfig中定义的chatMemory
public interface MemoryChatAssistant { // 会话记忆
    @UserMessage(fromResource = "user-message-template.txt")
    String chat(@V("message") String userInput);
}
