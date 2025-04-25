package com.li.controller;

import com.li.assistant.StreamAssistant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Slf4j
@Tag(name = "ChatController")
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final StreamAssistant streamAssistant;
    @Operation(summary = "对话")
    @GetMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestParam("message") @Parameter(description = "用户输入消息") String message) {
        log.info("chat message:{}",message);
        return streamAssistant.chat(0, message);
    }
}
