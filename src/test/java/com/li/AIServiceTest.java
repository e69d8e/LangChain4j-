package com.li;

import com.li.assistant.NormAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {
    @Autowired
    private QwenChatModel qwenChatModel;


    // 方式一
    @Test
    public void testChat() {
        NormAssistant normAssistant = AiServices.create(NormAssistant.class, qwenChatModel);
//       == AiServices.builder(Assistant.class).chatLanguageModel(qwenChatModel).build();
        String response = normAssistant.chat("你好");
        System.out.println(response);
    }

    @Autowired // 方式二
    private NormAssistant normAssistant;
    @Test
    public void testAssistant() {
        String response = normAssistant.chat("你好");
        System.out.println(response);
    }
}
