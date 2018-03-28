/**
 * 用于数据层与业务层之间数据操作类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.factory;

import cn.sitcat.dao.IStu_gradesDAO;
import cn.sitcat.dao.IStu_infoDAO;
import cn.sitcat.dao.impl.Stu_gradesDAOImpl;
import cn.sitcat.dao.impl.Stu_infoDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IStu_infoDAO getIStu_infoInstance(Connection conn){
        return new Stu_infoDAOImpl(conn);
    }

    public static IStu_gradesDAO getIStu_gradesInstance(Connection conn){
        return new Stu_gradesDAOImpl
                (conn);
    }
}
