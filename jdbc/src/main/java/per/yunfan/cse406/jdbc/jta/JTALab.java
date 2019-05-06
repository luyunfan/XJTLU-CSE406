package per.yunfan.cse406.jdbc.jta;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JTA (Java Transaction API) Demo代码
 */
public class JTALab {

    public static void main(String[] args) {

        XADataSource xads1 = getMysqlXADS("jdbc:mysql://10.7.1.127/HongfeiFan",
                3306,
                "HongfeiFan",
                "123"
        );

        XADataSource xads2 = getMysqlXADS("jdbc:mysql://10.7.1.127/HongfeiFan",
                3306,
                "HongfeiFan",
                "123"
        ); //这里可以是两台服务器之间的操作

        XAConnection xaconn1 = null, xaconn2 = null;
        Connection conn1 = null, conn2 = null;
        Statement stmt1 = null, stmt2 = null;
        XAResource xares1 = null, xares2 = null;

        MysqlXid xid1 = new MysqlXid(new byte[]{0x01}, new byte[]{0x57}, 0);
        MysqlXid xid2 = new MysqlXid(new byte[]{0x01}, new byte[]{0x58}, 0);

        try {
            xaconn1 = xads1.getXAConnection();
            conn1 = xaconn1.getConnection();
            stmt1 = conn1.createStatement();
            xares1 = xaconn1.getXAResource();

            xaconn2 = xads2.getXAConnection();
            conn2 = xaconn2.getConnection();
            stmt2 = conn2.createStatement();
            xares2 = xaconn2.getXAResource();

            conn1.setAutoCommit(false);
            conn2.setAutoCommit(false);

            xares1.start(xid1, XAResource.TMNOFLAGS);
            stmt1.execute("update t_account1 set balance = balance + 100 where id = 1");
            xares1.end(xid1, XAResource.TMSUCCESS);

            xares2.start(xid2, XAResource.TMNOFLAGS);
            stmt2.execute("update t_account2 set balance = balance - 100 where id = 1");
            xares2.end(xid2, XAResource.TMSUCCESS);

            int ret1 = xares1.prepare(xid1);
            int ret2 = xares2.prepare(xid2);

            if (ret1 == XAResource.XA_OK && ret2 == XAResource.XA_OK) {
                xares1.commit(xid1, false);
                xares2.commit(xid2, false);
                System.out.println("OK!");
            } else {
                xares1.rollback(xid1);
                xares2.rollback(xid2);
            }
        } catch (SQLException e) {
            try {
                if (xares1 != null && xares2 != null) {
                    xares1.rollback(xid1);
                    xares2.rollback(xid2);
                }
            } catch (XAException xae) {
                xae.printStackTrace();
            }
        } catch (XAException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (conn1 != null) {
                    conn1.close();
                }
                if (conn2 != null) {
                    conn2.close();
                }
                if (xaconn1 != null) {
                    xaconn1.close();
                }
                if (xaconn2 != null) {
                    xaconn2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取 MySQL XADataSource对象
     *
     * @param url  数据库 URL
     * @param port 数据库端口
     * @param user 数据库用户名
     * @param pass 数据库密码
     * @return MySQL XADataSource对象
     */
    private static XADataSource getMysqlXADS(String url, int port, String user, String pass) {
        MysqlXADataSource xads = new MysqlXADataSource();
        xads.setUrl(url);
        xads.setPort(port);
        xads.setUser(user);
        xads.setPassword(pass);
        return xads;
    }
}
