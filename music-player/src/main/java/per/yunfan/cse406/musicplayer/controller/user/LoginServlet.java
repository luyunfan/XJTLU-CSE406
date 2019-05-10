package per.yunfan.cse406.musicplayer.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.model.User;
import per.yunfan.cse406.musicplayer.model.vo.UserVO;
import per.yunfan.cse406.musicplayer.service.UserService;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;
import per.yunfan.cse406.musicplayer.utils.PasswordUtils;
import per.yunfan.cse406.musicplayer.utils.RedisUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * User login servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * User server object
     */
    private UserService userService = UserService
            .instance()
            .getClient("localhost", UserService.port());

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);

    public LoginServlet() throws RemoteException, NotBoundException {
    }

    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a POST request.
     * <p>
     * The HTTP POST method allows the client to send
     * data of unlimited length to the Web server a single time
     * and is useful when posting information such as
     * credit card numbers.
     *
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or output
     * stream object, and finally, write the response data. It's best
     * to include content type and encoding. When using a
     * <code>PrintWriter</code> object to return the response, set the
     * content type before accessing the <code>PrintWriter</code> object.
     *
     * <p>The servlet container must write the headers before committing the
     * response, because in HTTP the headers must be sent before the
     * response body.
     *
     * <p>Where possible, set the Content-Length header (with the
     * {@link ServletResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     *
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     *
     * <p>This method does not need to be either safe or idempotent.
     * Operations requested through POST can have side effects for
     * which the user can be held accountable, for example,
     * updating stored data or buying items online.
     *
     * <p>If the HTTP POST request is incorrectly formatted,
     * <code>doPost</code> returns an HTTP "Bad Request" message.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @see ServletOutputStream
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Optional<UserVO> loginUser = JSONUtils.getJSONObjectByRequest(req, UserVO.class);
        try {
            if (loginUser.isPresent()) { //Json String is right
                UserVO tryLogin = loginUser.get();
                Optional<User> user = userService.login(tryLogin.getUsername(), tryLogin.getPassword());
                if (user.isPresent()) { //Login successful
                    User successUser = user.get();
                    tryLogin.setPassword(PasswordUtils.createToken(successUser.getId()));
                    tryLogin.setStates(JSONUtils.SUCCESS);
                    //key = token, value = id_username
                    RedisUtils.set(tryLogin.getPassword(), (successUser.getId() + "_" + successUser.getUserName()));
                    JSONUtils.writeJSONToResponse(resp, JSONUtils.serializationJSON(tryLogin));
                    LOG.info("User: " + successUser.getUserName() + " login.");
                } else {
                    tryLogin.setStates(JSONUtils.FAILURE);
                    JSONUtils.writeJSONToResponse(resp, JSONUtils.serializationJSON(tryLogin));
                }
            } else {
                JSONUtils.writeJSONToResponse(resp, JSONUtils.serializationJSON(UserVO.FAILURE));
            }
        } catch (RemoteException e) {
            LOG.error("Could not connect to User RMI service! ", e);
            JSONUtils.writeJSONToResponse(resp, JSONUtils.serializationJSON(UserVO.FAILURE));
        }
    }
}
