package per.yunfan.cse406.musicplayer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * JSON utility class
 */
public final class JSONUtils {

    /**
     * Logger object by log4j2
     */
    private static final Logger LOG = LogManager.getLogger(JSONUtils.class);

    /**
     * JSON failure state
     */
    public static String FAILURE = "failure";

    /**
     * JSON success state
     */
    public static String SUCCESS = "success";

    /**
     * Failure JSON String
     */
    public static String FAILURE_JSON = "{\"states\": \"" + FAILURE + "\"' }";

    /**
     * Success JSON String
     */
    public static String SUCCESS_JSON = "{\"states\": \"" + SUCCESS + "\"' }";

    /**
     * Utility class can't create instance
     */
    private JSONUtils() {
    }


    public static <T> Optional<List<T>> getJSONObjectsByRequest(HttpServletRequest req, Class<T> clazz) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        req.getInputStream(),
                        StandardCharsets.UTF_8
                ))) {

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            return Optional.of(deserializationJSONToList(json.toString(), clazz));
        } catch (IOException e) {
            LOG.error("Transform JSON to object list failure !", e);
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getJSONObjectByRequest(HttpServletRequest req, Class<T> clazz) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        req.getInputStream(),
                        StandardCharsets.UTF_8
                ))) {

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            return Optional.of(deserializationJSONToObject(json.toString(), clazz));
        } catch (IOException e) {
            LOG.error("Transform JSON to object failure !", e);
            return Optional.empty();
        }
    }

    public static boolean writeJSONToResponse(HttpServletResponse resp, String jsonString) {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.write(jsonString);
            return true;
        } catch (IOException e) {
            LOG.error("Write JSON to response failure !", e);
            return false;
        }
    }

    public static <T> String serializationJSON(T object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("Serialize JSON failure !", e);
            return null;
        }
    }

    public static <T> List<T> deserializationJSONToList(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, getCollectionType(mapper, List.class, clazz));
    }

    public static <T> T deserializationJSONToObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    /**
     * Get collection Java type
     *
     * @param collectionClass Collection class object
     * @param elementClasses  element type of list
     * @return JavaType
     */
    private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
