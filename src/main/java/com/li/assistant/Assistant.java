package com.li.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel", //
        chatMemoryProvider = "chatMemoryProvider") // 使用MemoryChatAssistantConfig中定义的chatMemoryProvider
public interface Assistant {
    @SystemMessage(fromResource = "prompt-template.txt") // 系统提示词
    @UserMessage(fromResource = "user-message-template.txt") // 用户提示词 @V("message") 有@V("message")注解后不要写@UserMessage
    String chat(@MemoryId int memoryId, @V("message") String userMessage);
}
