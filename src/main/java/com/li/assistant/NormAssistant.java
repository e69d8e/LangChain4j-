package com.li.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

// 在配置文件中配置了多个chatModel，需要指定使用哪个chatModel，如果只配置了一个chatModel，则不需要指定
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "")
public interface NormAssistant {
    String chat(String userInput);
}
