package per.yunfan.cse406.musicplayer.controller.music;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.model.vo.MusicVO;
import per.yunfan.cse406.musicplayer.service.MusicService;
import per.yunfan.cse406.musicplayer.utils.JSONUtils;

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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/GetAllMusic")
public class GetAllMusicServlet extends HttpServlet {

    /**
     * Music server object
     */
    private MusicService musicService = MusicService
            .instance()
            .getClient("localhost", MusicService.port());

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(GetAllMusicServlet.class);


    public GetAllMusicServlet() throws RemoteException, NotBoundException {
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

        Map<Integer, String> allMusic = musicService.getAllMusicInformation();

        if (allMusic == null || allMusic.isEmpty()) {
            JSONUtils.writeJSONToResponse(
                    resp,
                    JSONUtils.serializeJSON(Collections.singletonList(MusicVO.FAILURE))
            );
        } else {
            List<MusicVO> result = allMusic.entrySet()
                    .stream()
                    .map(idAndName -> new MusicVO(
                            idAndName.getValue(),
                            idAndName.getKey() + "",
                            JSONUtils.SUCCESS))
                    .collect(Collectors.toList());

            JSONUtils.writeJSONToResponse(
                    resp,
                    JSONUtils.serializeJSON(result)
            );
        }
    }
}
