package com.li.config;

import com.li.store.MongoChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryChatAssistantConfig {
    @Bean
    public ChatMemory chatMemory() { // 会话记忆
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Autowired
    private MongoChatMemoryStore chatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() { // 隔离绘画记忆
        return (memoryId) -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
//                .chatMemoryStore(new InMemoryChatMemoryStore())
                .chatMemoryStore(chatMemoryStore) // 存储会话记忆的实现方式
                .build();
    }
}
