package per.yunfan.cse406.musicplayer.model.vo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WithTokenTest {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(WithTokenTest.class);


    @Test
    public void testCommentVOToken() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        CommentVO commentVO = new CommentVO(
                "user1",
                "content",
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                1,
                "tokenString"
        );
        String str = JSONUtils.serializationJSON(commentVO);
        LOG.info("JSON String: " + str);

        CommentVO deserialize = JSONUtils
                .deserializationJSONToObject(str, CommentVO.class);
        LOG.info("Deserialize object: " + deserialize);
    }
}
