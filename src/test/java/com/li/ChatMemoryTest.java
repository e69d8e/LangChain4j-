package com.li;

import com.li.assistant.Assistant;
import com.li.assistant.NormAssistant;
import com.li.assistant.MemoryChatAssistant;
import com.li.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private NormAssistant normAssistant;
    @Test
    public void testChatMemory() {
        String r1 = normAssistant.chat("我是li");
        String r2 = normAssistant.chat("我是谁");
        System.out.println(r1);
        System.out.println(r2);
    }

    @Autowired
    private QwenChatModel qwenChatModel;
    // 实现记忆功能 1
    @Test
    public void testChatMemory2() {
        // 第一轮对话
        UserMessage userMessage1 = UserMessage.userMessage("我是li");
        ChatResponse r1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = r1.aiMessage();
        System.out.println(aiMessage1.text()); //
        // 第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("我是谁");
        ChatResponse r2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));
        AiMessage aiMessage2 = r2.aiMessage();
        System.out.println(aiMessage2.text());
    }
    // 实现记忆功能 2
    @Test
    public void testChatMemory3() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10); // 设置最大消息数
        NormAssistant a = AiServices.builder(NormAssistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        String r1 = a.chat("我是li");
        String r2 = a.chat("我是谁");
        System.out.println(r1 + "\n" + r2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testChatMemory4() {
        String r1 = memoryChatAssistant.chat("我是li");
        String r2 = memoryChatAssistant.chat("我是谁");
        System.out.println(r1 + "\n" + r2);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory5() {
        String r1 = separateChatAssistant.chat(1, "我是li");
        String r2 = separateChatAssistant.chat(2, "我是谁");
        String r3 = separateChatAssistant.chat(1, "我是谁");
        System.out.println(r1);
        System.out.println("-----------------------");
        System.out.println(r2);
        System.out.println("-----------------------");
        System.out.println(r3);
    }

    @Autowired
    private Assistant assistant;

    @Test
    public void testMongoChatMemory() {
        String r1 = assistant.chat(1, "我是li");
        String r2 = assistant.chat(1, "我是谁");
        String r3 = assistant.chat(2, "我是谁");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }
}
