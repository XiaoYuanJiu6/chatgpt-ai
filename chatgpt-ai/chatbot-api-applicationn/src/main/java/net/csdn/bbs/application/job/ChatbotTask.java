package net.csdn.bbs.application.job;

import com.alibaba.fastjson.JSON;
import net.csdn.bbs.domain.ai.IOpenAI;
import net.csdn.bbs.domain.zsxq.IZsxqApi;
import net.csdn.bbs.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import net.csdn.bbs.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class ChatbotTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ChatbotTask.class);

    private String groupName;
    private String groupId;
    private String cookie;
    private String openAiKey;
    private boolean silenced;

    private IZsxqApi zsxqApi;
    private IOpenAI openAI;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IZsxqApi zsxqApi, IOpenAI openAI, boolean silenced) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openAI = openAI;
        this.silenced = silenced;
    }

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("{} 随机打烊中...", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("{} 检索结果：{}", groupName, JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("{} 本次检索未查询到待会答问题", groupName);
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(topics.size() - 1);
            String answer = openAI.doChatGPT(openAiKey, topic.getTalk().getText().trim());
            // 3. 问题回复
            boolean status = zsxqApi.comments(groupId, cookie, topic.getTopic_id(), answer, silenced);
            logger.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            logger.error("{} 自动回答问题异常", groupName, e);
        }
    }

}
//public class ChatGitSchedule {
//    private Logger logger= LoggerFactory.getLogger(ChatGitSchedule.class);
//
//    @Value("${chatgpt-ai.groupId}")
//    private String groupId;
//    @Value("${chatgpt-ai.cookie}")
//    private String cookie;
//
//    @Resource
//    private IZsxqApi zsxqApi;
//
//    @Resource
//    private IOpenAI openAI;
//
//    //表达式：crom.qqe2.com
//    @Scheduled(cron = "0 0/5 * * * * ?")
//    public void run(){
//        try {
//            if (new Random().nextBoolean()) {
//                logger.info("{} 随机打烊中...");
//                return;
//            }
//
//            GregorianCalendar calendar = new GregorianCalendar();
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//            if (hour > 22 || hour < 7) {
//                logger.info("打烊时间不工作，AI 下班了！");
//                return;
//            }
//
//
//            //1.检索问题
//            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates=zsxqApi.queryUnAnsweredQuestionsTopicId(groupId,cookie);
//            logger.info("测试结果:{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
//            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
//            if (null == topics || topics.isEmpty()){
//                logger.info("本次检索未查询到待会回答的问题");
//                return;
//            }
//
//            //2.AI 回答
//            Topics topic= topics.get(0);
//            String answer=openAI.doChatGPT(topic.getTalk().getText().trim());
//            //3.问题答复
//            boolean status = zsxqApi.comments(groupId, cookie, topic.getTopic_id(), answer, false);
//            logger.info(" 编号：{} 问题：{} 回答：{} 状态：{}",  topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
//        }catch (Exception e){
//            logger.error(" 自动回答问题异常", e);
//        }
//    }
//}

