package com.li;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

@SpringBootTest
public class RAGTest {
    @Test
    public void testReadDocument() {
        // 使用FileSystemDocumentLoader读取指定目录下的知识库文档
        // 并使用默认的文档解析器TextDocumentParser对文档进行解析
        Document document = FileSystemDocumentLoader.loadDocument("D:/test.txt");
        System.out.println(document.text());
        System.out.println("-----------------------");
        // 从一个目录中加载所有文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:/test", new TextDocumentParser());
        for (Document doc : documents) {
            System.out.println(doc.text());
        }
        // 从一个目录中加载所有的.txt文档
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        List<Document> documents2 = FileSystemDocumentLoader.loadDocuments("E:/knowledge",
                pathMatcher, new TextDocumentParser());
        // 从一个目录及其子目录中加载所有文档
        List<Document> documents3 = FileSystemDocumentLoader
                        .loadDocumentsRecursively("E:/knowledge", new
                        TextDocumentParser());
    }
    @Test
    public void testParsePDF() {
//        Document document = FileSystemDocumentLoader
//                .loadDocument("E:/资料/尚硅谷-Java+大模型-硅谷小智（医疗版）.pdf",
//                        new ApachePdfBoxDocumentParser());
//        System.out.println(document);
    }
}
