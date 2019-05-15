package per.yunfan.cse406.musicplayer.test.controller;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import per.yunfan.cse406.musicplayer.controller.user.*;
import per.yunfan.cse406.musicplayer.listener.RMIServerListener;
import per.yunfan.cse406.musicplayer.model.vo.CommentVO;
import per.yunfan.cse406.musicplayer.model.vo.UserInfoVO;
import per.yunfan.cse406.musicplayer.model.vo.UserVO;
import per.yunfan.cse406.musicplayer.test.WrappedServletInputStream;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserServletTest {

    @BeforeAll
    public static void startRMIServer() {
        new RMIServerListener().contextInitialized(null);
    }

    @Test
    public void testSignIn() throws Exception {

        SignInServlet servlet = new SignInServlet();

        UserVO signIn = new UserVO()
                .setUsername("Roland")
                .setPassword("TestPass");
        String json = JSONUtils.serializeJSON(signIn);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
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

        UserVO result = JSONUtils.deserializationJSONToObject(finalResult, UserVO.class);
        assertEquals(result.getStates(), JSONUtils.SUCCESS);
        System.out.println("Result: " + finalResult);
    }

    @Test
    public void testLogin() throws Exception {
        String pass = "32dd23d";
        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Frank", PasswordUtils.encryptedByMD5(pass));

        LoginServlet servlet = new LoginServlet();

        UserVO login = new UserVO()
                .setUsername("Frank")
                .setPassword(pass);
        String json = JSONUtils.serializeJSON(login);
        assert json != null;

        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
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

        UserVO result = JSONUtils.deserializationJSONToObject(finalResult, UserVO.class);
        assertEquals(result.getStates(), JSONUtils.SUCCESS);
        System.out.println("Result: " + finalResult);

    }

    @Test
    public void testModifyUserInfo() throws Exception {
        String pass = "32dd23d";
        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Frank", PasswordUtils.encryptedByMD5(pass));

        String token = PasswordUtils.createToken(1);
        RedisUtils.set(token, "1_Frank");

        ModifyUserInfoServlet servlet = new ModifyUserInfoServlet();

        UserInfoVO modifyInfo = new UserInfoVO()
                .setToken(token)
                .setGender('m')
                .setBirthday("1926-08-17")
                .setIntroduction("Interesting");

        String json = JSONUtils.serializeJSON(modifyInfo);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
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

        UserInfoVO result = JSONUtils.deserializationJSONToObject(finalResult, UserInfoVO.class);
        System.out.println("Result: " + finalResult);
        assertEquals(result.getStates(), JSONUtils.SUCCESS);

    }

    @Test
    public void testLogout() throws Exception {
        String token = PasswordUtils.createToken(1);
        RedisUtils.set(token, "1_Frank");

        RedisUtils.get(token)
                .ifPresent(tokenFromRedis -> assertEquals(tokenFromRedis, "1_Frank"))
                .elseDo(Assertions::fail);

        LogoutServlet servlet = new LogoutServlet();

        UserVO modifyInfo = new UserVO()
                .setToken(token);

        String json = JSONUtils.serializeJSON(modifyInfo);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
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

        UserVO result = JSONUtils.deserializationJSONToObject(finalResult, UserVO.class);
        System.out.println("Result: " + finalResult);
        assertEquals(result.getStates(), JSONUtils.SUCCESS);
        RedisUtils.get(token)
                .ifPresent(tokenFromRedis -> fail());
    }

    @Test
    public void testGetUserInfoByComment() throws Exception {
        String pass = "32dd23d";
        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        String sqlUpdate = "UPDATE user SET gender = ?, birthday = ?, introduction = ? WHERE username = ?;";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Bob", PasswordUtils.encryptedByMD5(pass));
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sqlUpdate, "m", LocalDate.now(), "Test", "Bob");

        String token = PasswordUtils.createToken(1);
        System.out.println("Set token: " + RedisUtils.set(token, "1_Bob"));

        GetUserInfoByComment servlet = new GetUserInfoByComment();

        CommentVO commentVO = new CommentVO()
                .setUsername("Bob")
                .setToken(token);

        String json = JSONUtils.serializeJSON(commentVO);
        assert json != null;
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletConfig config = EasyMock.createMock(ServletConfig.class);
        ServletContext context = EasyMock.createMock(ServletContext.class);

        EasyMock.expect(config.getServletContext()).andReturn(context).anyTimes();
        EasyMock.expect(request.getInputStream()).andReturn(new WrappedServletInputStream(stream)).anyTimes();
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

        UserInfoVO result = JSONUtils.deserializationJSONToObject(finalResult, UserInfoVO.class);
        System.out.println("Result: " + finalResult);
        assertEquals(result.getStates(), JSONUtils.SUCCESS);


    }

    @AfterAll
    public static void clear() throws SQLException {
        String sql = "DELETE FROM user WHERE username = ?";
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Roland");
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Frank");
        JDBCUtils.executeUpdate(JDBCUtils.getConnection(), sql, "Bob");
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
}
