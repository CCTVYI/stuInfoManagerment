/**
 * 学生信息表数据层实现类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.sitcat.dao.IStu_infoDAO;
import cn.sitcat.PO.Stu_grades;
import cn.sitcat.PO.Stu_info;


public class Stu_infoDAOImpl implements IStu_infoDAO<String,Stu_info> {
    private Connection conn;
    private PreparedStatement pstmt;

    public Stu_infoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Stu_info vo) throws Exception {
        String sql = "insert into stu_info(stu_id,stu_name,stu_age,stu_sex,stu_class) values(?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, vo.getStu_id());
        this.pstmt.setString(2, vo.getStu_name());
        this.pstmt.setInt(3, vo.getAge());
        this.pstmt.setString(4, vo.getSex());
        this.pstmt.setString(5, vo.getStu_class());
        boolean statu= this.pstmt.executeUpdate() > 0;

        sql="insert into stu_grades values(?,0,0,0,0,0)";
        this.pstmt.setString(1, vo.getStu_id());
        return statu;
    }

    @Override
    public boolean doUpdate(Stu_info vo) throws Exception {
        String sql = "update stu_info set stu_name=?,stu_age=?,stu_sex=?,stu_class=? where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, vo.getStu_name());
        this.pstmt.setInt(2, vo.getAge());
        this.pstmt.setString(3, vo.getSex());
        this.pstmt.setString(4, vo.getStu_class());
        this.pstmt.setString(5, vo.getStu_id());
        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        if (ids == null || ids.size() == 0) {//没有要删除的数据
            return false;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete from stu_info where stu_id in(");
        Iterator<String> iter = ids.iterator();
        while (iter.hasNext()) {
            sql.append("'").append(iter.next()).append("'").append(",");
        }
        sql.delete(sql.length() - 1, sql.length()).append(")");
        this.pstmt = this.conn.prepareStatement(sql.toString());
        //执行返回的数据量和传入的数据量相同 表示删除成功
        return this.pstmt.executeUpdate() == ids.size();
    }

    @Override
    public Stu_info findById(String id) throws Exception {
        Stu_info vo = new Stu_info();
        String sql = "select stu_id,stu_name,stu_age,stu_sex,stu_class from stu_info where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, id);
        ResultSet rs = this.pstmt.executeQuery();

        if (rs.next()) {vo = new Stu_info();

            vo.setStu_id(rs.getString(1));
            vo.setStu_name(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setStu_class(rs.getString(5));
        }
        return vo;
    }

    @Override
    public List<Stu_info> findAll() throws Exception {
        List<Stu_info> all = new ArrayList<>();
        String sql = "select stu_id,stu_name,stu_age,stu_sex,stu_class from stu_info";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()) {
            Stu_info vo = new Stu_info();
            vo.setStu_id(rs.getString(1));
            vo.setStu_name(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setStu_class(rs.getString(5));
            all.add(vo);//保存集合
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "select count(stu_id) from stu_info where " + column + " like ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, keyWord);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            int a = rs.getInt(1);
            return rs.getInt(1);
        }
        return null;
    }

    @Override
    public List<Object> findStuDetails(String id) throws Exception {
        List<Object> all = new ArrayList<>();
        String sql = "select * from( " +
                "select i.stu_id,i.stu_name,i.stu_age,i.stu_sex,i.stu_class,g.chinese,g.math,g.english,g.avg,g.count " +
                "from stu_info i,stu_grades g " +
                "where i.stu_id=? and i.stu_id=g.stu_id)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, id);
        ResultSet rs = this.pstmt.executeQuery();

        while (rs.next()) {
            Stu_info info = new Stu_info();
            info.setStu_id(rs.getString(1));
            info.setStu_name(rs.getString(2));
            info.setAge(rs.getInt(3));
            info.setSex(rs.getString(4));
            info.setStu_class(rs.getString(5));
            all.add(info);

            Stu_grades grades = new Stu_grades();
            grades.setChinese(rs.getDouble(6));
            grades.setMath(rs.getDouble(7));
            grades.setEnglish(rs.getDouble(8));
            grades.setAvg(rs.getDouble(9));
            grades.setCount(rs.getDouble(10));
            all.add(grades);
        }
        return all;
    }
}

