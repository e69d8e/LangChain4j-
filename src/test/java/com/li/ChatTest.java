package com.li;

import com.li.assistant.Assistant;
import com.li.assistant.MemoryChatAssistant;
import com.li.assistant.StreamAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
public class ChatTest {
    @Autowired
    Assistant assistant;
    @Test
    public void test() {
        String res = assistant.chat(3, "你是谁");
        System.out.println(res);
    }
    @Test
    public void test2() {
        String res = assistant.chat(3, "今天的日期是多少");
        System.out.println(res);
    }
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void test3() {
        String res = memoryChatAssistant.chat("你知道我的工作是什么吗");
        System.out.println(res);
    }

    @Test
    public void test4() {
        String res = assistant.chat(1, "你知道我的工作是什么吗");
        System.out.println(res);
    }

    @Test
    public void test5() {
        String res = assistant.chat(10, "计算234+122，34567890的平方根和548573248-38248分别是多少");
        System.out.println(res);
    }

    @Autowired
    private StreamAssistant assistant2;

    @Test
    public void test6() { // 流输出
        Flux<String> res = assistant2.chat(11, "今天的日期是多少");
        res.subscribe(System.out::println);
    }
}
