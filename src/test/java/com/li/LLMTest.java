package com.li;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {
    private static final Logger log = LoggerFactory.getLogger(LLMTest.class);

    @Test
    public void test() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                // LangChain4j提供的代理服务器，该代理服务器会将演示密钥替换成真实密钥， 再将请求转
                //发给OpenAI API
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
        String response = model.chat("你好");
        System.out.println("rseponse: " + response);
    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringBoot() {
        String response = openAiChatModel.chat("你是谁");
        System.out.println("rseponse: " + response);
    }

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Test
    public void testOllama() {
        String response = ollamaChatModel.chat("你是谁");
        System.out.println("rseponse: " + response);
    }

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testDashScopeQwen() {
        String response = qwenChatModel.chat("你是谁");
        System.out.println("rseponse: " + response);
    }

    @Test
    public void testDashScopeWanx() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("wanx2.1-t2i-plus")
                .build();
        Response<Image> img = wanxImageModel.generate("一只猫正在注视着主人的电脑屏幕");
        System.out.println("img: " + img);
        System.out.println("mimeType: " + img.content().mimeType());
        System.out.println("base64: " + img.content().base64Data());
        System.out.println("url: " + img.content().url());
        // img: Response { content = Image {
        // url = "https://dashscope-result-wlcb-acdr-1.oss-cn-wulanchabu-acdr-1.aliyuncs.com/1d/c0/20250424/44fa57c1/872e7f2f-810a-4a9a-b302-06e95b39ec152700576422.png?Expires=1745552555&OSSAccessKeyId=LTAI5tKPD3TMqf2Lna1fASuh&Signature=TyFKOtp%2BbLEHGGJdrkcpXrPkIZo%3D",
        // base64Data = null,
        // mimeType = null,
        // revisedPrompt = "写实风格宠物摄影，一只毛茸茸的英短猫站在主人桌边，好奇地注视着打开的电脑屏幕。猫咪灰蓝色的毛发柔软顺滑，大大的金色眼睛充满灵动，微微前倾的身体展现出专注神态。屏幕上显示着色彩鲜艳的壁纸，与猫咪形成呼应。桌面整洁有序，摆放着一杯咖啡和绿色植物。室内光线柔和，营造出温馨的生活氛围。近景侧面构图，突出猫咪可爱姿态。"
        // }, tokenUsage = null, finishReason = null, metadata = {} }
        // mimeType: null
        // base64: null
        // url: https://dashscope-result-wlcb-acdr-1.oss-cn-wulanchabu-acdr-1.aliyuncs.com/1d/c0/20250424/44fa57c1/872e7f2f-810a-4a9a-b302-06e95b39ec152700576422.png?Expires=1745552555&OSSAccessKeyId=LTAI5tKPD3TMqf2Lna1fASuh&Signature=TyFKOtp%2BbLEHGGJdrkcpXrPkIZo%3D
    }

    @Test
    public void testDashScopeDS() {
        String response = openAiChatModel.chat("你是谁");
        // openai的规范，可以直接使用，但是baseUrl和api_key需要替换成dashscope的baseUrl和api_key
        System.out.println("rseponse: " + response);
    }
}
