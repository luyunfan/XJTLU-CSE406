package per.yunfan.cse406.musicplayer.test.utils;

import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.model.vo.CommentVO;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JSONUtilsTest {

    @Test
    public void failureTest() {
        String error = "3rgfwef";
        try {
            CommentVO commentVO = JSONUtils.deserializationJSONToObject(error, CommentVO.class);
        } catch (IOException e) {
            System.out.println("Failure Test pass");
        }
    }

    @Test
    public void testCommentVO() throws IOException {
        CommentVO comment = new CommentVO();
        System.out.println(JSONUtils.serializeJSON(comment));
        comment.setUsername("User1")
                .setMusicId(1)
                .setContent("content");
        System.out.println(JSONUtils.serializeJSON(comment));
        String withTokenJson = "{\"token\":\"wdfhwioedjw\",\"musicId\":10}";
        CommentVO deserialize = JSONUtils.deserializationJSONToObject(withTokenJson, CommentVO.class);
        assertNotNull(deserialize);

    }
}
