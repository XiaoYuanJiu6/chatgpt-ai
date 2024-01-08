package net.csdn.bbs.ai.test;

import com.alibaba.fastjson.JSON;
import net.csdn.bbs.domain.ai.IOpenAI;
import net.csdn.bbs.domain.zsxq.IZsxqApi;
import net.csdn.bbs.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import net.csdn.bbs.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRunTest.class)
@ComponentScan(basePackages = "net.csdn.bbs")
public class SpringBootRunTest {
    private Logger logger= LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatgpt-ai.groupId}")
    private String groupId;
    @Value("${chatgpt-ai.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi zsxqApi;

    @Autowired
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException{
       // System.out.println(groupId);
        //System.out.println(cookie);
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        //System.out.println(unAnsweredQuestionsAggregates);
        logger.info("测试结果:{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics=unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics){
            String topicId = topic.getTopic_id();
            System.out.println(topicId);
            String text = topic.getTalk().getText();
            System.out.println(text);
            //System.out.println(topicId + "=====" + text);
        }
    }

//    @Test
//    public void test_openAi() throws IOException{
//        String response = openAI.doChatGPT("水果有哪些");
//        System.out.println(response);
//    }
}
