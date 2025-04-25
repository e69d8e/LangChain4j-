package com.li.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculationTool {
    @Tool
    public double add(double a, double b) {
        log.info("add a:{},b:{}",a,b);
        return a + b;
    }
    // 开平方
    @Tool
    public double sqrt(@P(value = "被开方的数字", required = true) double a) {
        log.info("sqrt a:{}",a);
        return Math.sqrt(a);
    }
    @Tool(name = "减法", value = "计算两个数的差")
    public double subtract(@P("被减数") double a, @P("减数") double b, @ToolMemoryId int memoryId) {
        log.info("subtract memoryId:{}",memoryId);
        log.info("subtract a:{},b:{}",a,b);
        return a - b;
    }
}
