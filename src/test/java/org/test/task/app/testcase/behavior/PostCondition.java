package org.test.task.app.testcase.behavior;

import org.junit.Assert;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.ResponseEntity;
import org.test.task.app.domain.dto.ResultContentDto;

@TestComponent
public class PostCondition {

    public void verifyResult(ResponseEntity<ResultContentDto[]> responseEntity) {

        ResultContentDto[] controlResult = new ResultContentDto[]{
                new ResultContentDto() {{
                    setWord("Test5");
                    setCount(2);
                }},
                new ResultContentDto() {{
                    setWord("Test5");
                    setCount(2);
                }},
                new ResultContentDto() {{
                    setWord("Test5");
                    setCount(2);
                }},
                new ResultContentDto() {{
                    setWord("Test1");
                    setCount(1);
                }}};

        ResultContentDto[] body = responseEntity.getBody();

        Assert.assertNotNull(body);

        Assert.assertNotEquals(0, body.length);

        for (int i = 0; i < body.length; i++) {
            boolean b = body[i].equals(controlResult[i]);
            Assert.assertTrue(b);
        }

    }
}
