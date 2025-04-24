package com.li;

import com.li.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
public class MongoDBTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("你是谁");
        mongoTemplate.insert(chatMessages);
    }
    @Test
    public void testFind() {
        List<ChatMessages> all = mongoTemplate.findAll(ChatMessages.class); // 查询所有
        System.out.println(all);
        System.out.println(mongoTemplate.findById("6809e3a7a639536c1728c84e", ChatMessages.class)); // 通过id查询
    }
    @Test
    public void testUpdate() {
        Criteria cr = Criteria.where("_id").is("6809e3a7a639536c1728c84e");
        Query query = new Query(cr); // mongodb.core.query
        Update update = new Update();
        update.set("content", "你是谁？");
        // 修改或者新增，如果找不到，则新增
        mongoTemplate.updateFirst(query, update, ChatMessages.class);
    }
    @Test
    public void testDelete() {
        Criteria cr = Criteria.where("_id").is("6809e3a7a639536c1728c84e");
        Query query = new Query(cr);
        mongoTemplate.remove(query, ChatMessages.class);
    }

}
