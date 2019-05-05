package per.yunfan.cse406.musicplayer.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import per.yunfan.cse406.musicplayer.service.MusicService;
import per.yunfan.cse406.musicplayer.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.rmi.RemoteException;

public class RMIServerListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(RMIServerListener.class);

    /**
     * Receives notification that the web application initialization
     * process is starting.
     *
     * <p>All ServletContextListeners are notified of context
     * initialization before any filters or servlets in the web
     * application are initialized.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being initialized
     * @implSpec The default implementation takes no action.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            MusicService.instance().startServer(50071);
            UserService.instance().startServer(50072);
        } catch (RemoteException e) {
            LOG.error("Could not start RMI server, startup failure!", e);
            throw new RuntimeException("Could not start RMI server, startup failure!", e);
        }
    }
}
