package per.yunfan.cse406.musicplayer.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * Utility class can't create instance
     */
    private JSONUtils() {
    }

    public static <T> Optional<List<T>> getJSONObjectByRequest(HttpServletRequest req, Class<T> clazz) {
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
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(json.toString(), getCollectionType(mapper, List.class, clazz)));
        } catch (IOException e) {
            LOG.error("Transform JSON to Object failure !", e);
            return Optional.empty();
        }
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
