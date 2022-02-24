package org.test.task.app.testcase;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.test.task.app.ContentParserApplication;
import org.test.task.app.configuration.TestContentParserConfiguration;
import org.test.task.app.domain.dto.InputContentDto;
import org.test.task.app.domain.dto.ResultContentDto;
import org.test.task.app.testcase.behavior.Action;
import org.test.task.app.testcase.behavior.PostCondition;
import org.test.task.app.testcase.behavior.PreCondition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@TestPropertySource(locations = "classpath:application.yml")
@Import({TestContentParserConfiguration.class, PreCondition.class, PostCondition.class, Action.class})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ContentParserApplication.class})
public class IntegrationContentParserTest extends AbstractTestNGSpringContextTests {

    @Resource
    private PreCondition preCondition;

    @Resource
    private PostCondition postCondition;

    @Resource
    private Action action;

    @BeforeClass
    public void main() {
        ContentParserApplication.main(new String[] {});
    }

    @Test
    public void baseTest() {

        List<String> words = new ArrayList<String>(){{
            add("Test2");
            add("Test1");
            add("Test3");
            add("Test5");
            add("Test5");
            add("Test5");
        }};

        String text = "Test1 Test5 Test5 x";

        InputContentDto responseBody = preCondition.createResponseBody(words, text);

        ResponseEntity<ResultContentDto[]> responseEntity = action.requestExecute(responseBody);

        postCondition.verifyResult(responseEntity);

    }
}
