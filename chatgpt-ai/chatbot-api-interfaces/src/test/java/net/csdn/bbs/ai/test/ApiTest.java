package net.csdn.bbs.ai.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient =HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

//        get.addHeader("cookie","sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjZGQyNDMyNjc1Yi0wNTgyNWE1MWU0NTNmZmMtNGM2NTdiNTgtMTM5NTM5Ni0xOGNkZDI0MzI2ODExNjQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%7D; zsxq_access_token=016E4B54-1587-2B40-C1CF-017E6CEAAB5E_73EEDF7C69E3A2B2; zsxqsessionid=5bcc671eb19ebd7e356140ddadbdd73b; abtest_env=product");
        get.addHeader("cookie","sajssdk_2015_cross_new_user=1; zsxq_access_token=016E4B54-1587-2B40-C1CF-017E6CEAAB5E_73EEDF7C69E3A2B2; zsxqsessionid=5bcc671eb19ebd7e356140ddadbdd73b; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184544148185252%22%2C%22first_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjZGQyNDMyNjc1Yi0wNTgyNWE1MWU0NTNmZmMtNGM2NTdiNTgtMTM5NTM5Ni0xOGNkZDI0MzI2ODExNjQiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxODQ1NDQxNDgxODUyNTIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184544148185252%22%7D%2C%22%24device_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%7D");
        get.addHeader("Content-Type","application/json;charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(get);
        System.out.println(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res= EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void comments() throws IOException{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/211422881145851/comments");
        post.addHeader("cookie","sajssdk_2015_cross_new_user=1; zsxq_access_token=016E4B54-1587-2B40-C1CF-017E6CEAAB5E_73EEDF7C69E3A2B2; zsxqsessionid=5bcc671eb19ebd7e356140ddadbdd73b; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184544148185252%22%2C%22first_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjZGQyNDMyNjc1Yi0wNTgyNWE1MWU0NTNmZmMtNGM2NTdiNTgtMTM5NTM5Ni0xOGNkZDI0MzI2ODExNjQiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxODQ1NDQxNDgxODUyNTIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184544148185252%22%7D%2C%22%24device_id%22%3A%2218cdd2432675b-05825a51e453ffc-4c657b58-1395396-18cdd2432681164%22%7D");
        post.addHeader("Content-Type","application/json;charset=utf-8");

        String paramJson="{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"西瓜\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res= EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        //post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");
        post.addHeader("Authorization", "a9c7f791e5fe14b04d185520c7941d3b ");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
