package net.csdn.bbs.domain.zsxq.model.res;

import net.csdn.bbs.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * 结果数据
 */
public class RespData {
    private List<Topics> topics;

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
    public List<Topics> getTopics(){
        return this.topics;
    }
}
