package per.yunfan.cse406.musicplayer.test.controller;

import javazoom.jl.player.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.controller.music.AddCommentServlet;
import per.yunfan.cse406.musicplayer.controller.music.GetAllMusicServlet;
import per.yunfan.cse406.musicplayer.controller.music.GetMusicCommentsServlet;
import per.yunfan.cse406.musicplayer.controller.music.PlayServlet;
import per.yunfan.cse406.musicplayer.listener.RMIServerListener;
import per.yunfan.cse406.musicplayer.model.vo.CommentVO;
import per.yunfan.cse406.musicplayer.model.vo.MusicVO;
import per.yunfan.cse406.musicplayer.test.WrappedServletInputStream;
import per.yunfan.cse406.musicplayer.test.WrappedServletOutputStream;
import per.yunfan.cse406.musicplayer.utils.JDBCUtils;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;
import per.yunfan.cse406.musicplayer.utils.PasswordUtils;
import per.yunfan.cse406.musicplayer.utils.RedisUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicServletTest {

    @BeforeAll
    public static void startRMIServer() {
        new RMIServerListener().contextInitialized(null);
    }

    @Test
    public void testGetAllMusicAndComment() throws Exception {
        GetAllMusicServlet servlet = new GetAllMusicServlet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getMethod()).andReturn("POST").anyTimes();
        EasyMock.expect(response.getWriter()).andReturn(new PrintWriter(out, true)).anyTimes();
        response.setContentType("application/json;charset=utf-8");
        EasyMock.expectLastCall();
        response.setCharacterEncoding("UTF-8");
        EasyMock.expectLastCall();

        EasyMock.replay(request);
        EasyMock.replay(response);
        EasyMock.replay(config);
        EasyMock.replay(context);

        servlet.init(config);

        runDoPost(servlet, request, response);

        String finalResult = new String(out.toByteArray(), Charset.forName("UTF-8"));

        List<MusicVO> result = JSONUtils.deserializeJSONToList(finalResult, MusicVO.class);
        for (MusicVO musicVO : result) {
            System.out.println("Result: " + musicVO);
        }

        MusicVO musicVO = result.get(0);

        String pass = "32dd23d";
        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Frank", PasswordUtils.encryptedByMD5(pass));

        String token = PasswordUtils.createToken(1);
        RedisUtils.set(token, "1_Frank");

        CommentVO comment = new CommentVO()
                .setUsername("Frank")
                .setMusicId(Integer.parseInt(musicVO.getPlayId()))
                .setToken(token)
                .setContent("This is a test comment");

        AddCommentServlet servletComment = new AddCommentServlet();

        String json = JSONUtils.serializeJSON(comment);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream outComment = new ByteArrayOutputStream();

        HttpServletRequest requestComment = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse responseComment = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig configComment = EasyMock.createMock(ServletConfig.class);
        ServletContext contextComment = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(configComment.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(requestComment.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
        EasyMock.expect(requestComment.getMethod()).andReturn("POST").anyTimes();
        EasyMock.expect(responseComment.getWriter()).andReturn(new PrintWriter(outComment, true)).anyTimes();
        responseComment.setContentType("application/json;charset=utf-8");
        EasyMock.expectLastCall();
        responseComment.setCharacterEncoding("UTF-8");
        EasyMock.expectLastCall();

        EasyMock.replay(requestComment);
        EasyMock.replay(responseComment);
        EasyMock.replay(configComment);
        EasyMock.replay(contextComment);

        servletComment.init(configComment);

        runDoPost(servletComment, requestComment, responseComment);

        String responseJson = new String(outComment.toByteArray(), Charset.forName("UTF-8"));
        assertEquals(responseJson, JSONUtils.SUCCESS_JSON);
        System.out.println(responseJson);

        GetMusicCommentsServlet allCommentsServlet = new GetMusicCommentsServlet();

        String musicVOJson = JSONUtils.serializeJSON(musicVO);
        assert musicVOJson != null;
        ByteArrayInputStream streamGetComment = new ByteArrayInputStream(musicVOJson.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream outGetComment = new ByteArrayOutputStream();

        HttpServletRequest requestGetComment = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse responseGetComment = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig configGetComment = EasyMock.createMock(ServletConfig.class);
        ServletContext contextGetComment = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(configGetComment.getServletContext()).andReturn(contextGetComment).anyTimes();
        EasyMock.expect(requestGetComment.getInputStream()).andReturn(new WrappedServletInputStream(streamGetComment)).anyTimes();
        EasyMock.expect(requestGetComment.getMethod()).andReturn("POST").anyTimes();
        EasyMock.expect(responseGetComment.getWriter()).andReturn(new PrintWriter(outGetComment, true)).anyTimes();
        responseGetComment.setContentType("application/json;charset=utf-8");
        EasyMock.expectLastCall();
        responseGetComment.setCharacterEncoding("UTF-8");
        EasyMock.expectLastCall();

        EasyMock.replay(requestGetComment);
        EasyMock.replay(responseGetComment);
        EasyMock.replay(configGetComment);
        EasyMock.replay(contextGetComment);

        allCommentsServlet.init(configGetComment);

        runDoPost(allCommentsServlet, requestGetComment, responseGetComment);

        String responseComments = new String(outGetComment.toByteArray(), Charset.forName("UTF-8"));

        List<CommentVO> finalComments = JSONUtils.deserializeJSONToList(responseComments, CommentVO.class);

        System.out.println(finalComments);
        assertEquals(finalComments.get(0).getContent(), "This is a test comment");


    }

    @Test
    public void testPlay() throws Exception {
        MusicVO musicVO = new MusicVO()
                .setPlayId("8");

        String json = JSONUtils.serializeJSON(musicVO);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));

        PlayServlet servlet = new PlayServlet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
        EasyMock.expect(request.getMethod()).andReturn("POST").anyTimes();
        EasyMock.expect(response.getOutputStream()).andReturn(new WrappedServletOutputStream(out)).anyTimes();
        response.setContentType("audio/mp3");
        EasyMock.expectLastCall();
        response.setCharacterEncoding("UTF-8");
        EasyMock.expectLastCall();

        EasyMock.replay(request);
        EasyMock.replay(response);
        EasyMock.replay(config);
        EasyMock.replay(context);

        servlet.init(config);

        runDoPost(servlet, request, response);

        Player player = new Player(new ByteArrayInputStream(out.toByteArray()));
        player.play(60);
    }


    /**
     * Run doPost method in servlet by Java refection
     *
     * @param servlet Target servlet
     * @param req     HttpServletRequest object
     * @param resp    HttpServletResponse object
     */
    private void runDoPost(HttpServlet servlet,
                           HttpServletRequest req,
                           HttpServletResponse resp) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = servlet.getClass()
                .getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        method.setAccessible(true);
        method.invoke(servlet, req, resp);
    }


    @AfterAll
    public static void clear() throws SQLException {
        String sql = "DELETE FROM user WHERE username = ?;";
        String sqlComment = "DELETE FROM comment WHERE username = ?;";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Frank");
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sqlComment, "Frank");
    }
}
