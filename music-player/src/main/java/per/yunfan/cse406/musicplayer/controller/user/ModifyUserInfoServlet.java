package per.yunfan.cse406.musicplayer.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.model.vo.UserInfoVO;
import per.yunfan.cse406.musicplayer.service.UserService;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;
import per.yunfan.cse406.musicplayer.utils.Optional;
import per.yunfan.cse406.musicplayer.utils.RedisUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Modify user's information
 */
@WebServlet("/ModifyUser")
public class ModifyUserInfoServlet extends HttpServlet {

    /**
     * LocalDateTime formatter
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");

    /**
     * User server object
     */
    private UserService userService = UserService
            .instance()
            .getClient("localhost", UserService.port());

    public ModifyUserInfoServlet() throws RemoteException, NotBoundException {
    }

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(ModifyUserInfoServlet.class);

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
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     * @see ServletOutputStream
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<UserInfoVO> userInfoVO = JSONUtils.getJSONObjectByRequest(req, UserInfoVO.class);
        if (userInfoVO.isPresent()) {
            UserInfoVO modified = userInfoVO.get();
            Optional<String> idAndUserName = RedisUtils.get(modified.getToken());
            if (!idAndUserName.isPresent()) {
                JSONUtils.writeJSONToResponse(
                        resp,
                        JSONUtils.serializationJSON(
                                UserInfoVO.FAILURE.setErrorInfo("You are not login, illegal operator! ")
                        )
                );
                return;
            }

            char gender = modified.getGender();
            String birthday = modified.getBirthday();
            String introduction = modified.getIntroduction();
            if (gender != 'f' && gender != 'm' && gender != 'n' && gender != 'o') {
                JSONUtils.writeJSONToResponse(
                        resp,
                        JSONUtils.serializationJSON(
                                UserInfoVO.FAILURE.setErrorInfo("User gender format is incorrect! ")
                        )
                );
                return;
            }
            try {
                LocalDate dayOfBirth = LocalDate.parse(birthday, FORMATTER);
                String userName = idAndUserName.get().split("_")[1];
                boolean isSuccessful = userService.modifyUserInfo(userName, gender, dayOfBirth, introduction);
                if (isSuccessful) {
                    modified.setStates(JSONUtils.SUCCESS);
                    JSONUtils.writeJSONToResponse(resp,
                            JSONUtils.serializationJSON(modified)
                    );
                } else {
                    JSONUtils.writeJSONToResponse(
                            resp,
                            JSONUtils.serializationJSON(
                                    UserInfoVO.FAILURE.setErrorInfo("User information are not change! ")
                            )
                    );
                }
            } catch (DateTimeParseException e) {
                JSONUtils.writeJSONToResponse(
                        resp,
                        JSONUtils.serializationJSON(
                                UserInfoVO.FAILURE.setErrorInfo("User birthday format is incorrect! ")
                        )
                );
            } catch (ArrayIndexOutOfBoundsException e) {
                LOG.error("The format of received token from Redis is not correct. token: " + idAndUserName, e);
                JSONUtils.writeJSONToResponse(
                        resp,
                        JSONUtils.serializationJSON(
                                UserInfoVO.FAILURE.setErrorInfo("Server internal error! ")
                        )
                );
            }
        } else {
            JSONUtils.writeJSONToResponse(
                    resp,
                    JSONUtils.serializationJSON(
                            UserInfoVO.FAILURE.setErrorInfo("User information JSON format is incorrect! ")
                    )
            );
        }
    }
}
