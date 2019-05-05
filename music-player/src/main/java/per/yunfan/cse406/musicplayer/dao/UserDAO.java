package per.yunfan.cse406.musicplayer.dao;

import per.yunfan.cse406.musicplayer.dao.user.UserDAOImpl;

public interface UserDAO {

    static UserDAO instance() {
        return UserDAOImpl.INSTANCE;
    }
}
