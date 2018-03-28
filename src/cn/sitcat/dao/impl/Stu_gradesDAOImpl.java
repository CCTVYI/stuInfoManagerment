/**
 * 学生成绩表数据层实现类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.dao.impl;

import cn.sitcat.dao.IStu_gradesDAO;
import cn.sitcat.PO.Stu_grades;
import cn.sitcat.PO.Stu_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Stu_gradesDAOImpl implements IStu_gradesDAO<Double, Stu_grades> {
    private Connection conn;
    private PreparedStatement pstmt;

    public Stu_gradesDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Stu_grades vo) throws Exception {
        String sql = "insert into stu_grades(stu_id,chinese,math,english,avg,count) values(?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, vo.getStu_id());
        this.pstmt.setDouble(2, vo.getChinese());
        this.pstmt.setDouble(3, vo.getMath());
        this.pstmt.setDouble(4, vo.getEnglish());
        double count = vo.getChinese() + vo.getMath() + vo.getEnglish();
        double avg = count / 3;
        this.pstmt.setDouble(5, avg);
        this.pstmt.setDouble(6, count);
        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Stu_grades vo) throws Exception {
        String sql = "update Stu_grades set chinese=?,math=?,english=?,avg=?,count=? where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setDouble(1, vo.getChinese());
        this.pstmt.setDouble(2, vo.getMath());
        this.pstmt.setDouble(3, vo.getEnglish());
        double count = vo.getChinese() + vo.getMath() + vo.getEnglish();
        double avg = count / 3;
        this.pstmt.setDouble(4, avg);
        this.pstmt.setDouble(5, count);
        this.pstmt.setString(6, vo.getStu_id());
        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        if (ids == null || ids.size() == 0) {//没有要删除的数据
            return false;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete from stu_grades where stu_id in(");
        Iterator<String> iter = ids.iterator();
        while (iter.hasNext()) {
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length() - 1, sql.length()).append(")");
        this.pstmt = this.conn.prepareStatement(sql.toString());
        //执行返回的数据量和传入的数据量相同 表示删除成功
        return this.pstmt.executeUpdate() == ids.size();
    }

    @Override
    public Stu_grades findById(String id) throws Exception {
        Stu_grades vo = new Stu_grades();
        String sql = "select stu_id,chinese,math,english,avg,count from stu_grades where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            vo.setStu_id(rs.getString(1));
            vo.setChinese(rs.getDouble(2));
            vo.setMath(rs.getDouble(3));
            vo.setEnglish(rs.getDouble(4));
            vo.setAvg(rs.getDouble(5));
            vo.setCount(rs.getDouble(6));
        }
        return vo;
    }

    @Override
    public List<Stu_grades> findAll() throws Exception {
        List<Stu_grades> all = new ArrayList<>();
        String sql = "select stu_id,chinese,math,english,avg,count from stu_grades";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Stu_grades vo = new Stu_grades();
            vo = new Stu_grades();
            vo.setStu_id(rs.getString(1));
            vo.setChinese(rs.getDouble(2));
            vo.setMath(rs.getDouble(3));
            vo.setEnglish(rs.getDouble(4));
            vo.setAvg(rs.getDouble(5));
            vo.setCount(rs.getDouble(6));
            all.add(vo);//保存集合
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "select count(stu_id) from Stu_grades where " + column + " like ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return null;
    }

    @Override
    public List<Object> TotalRank() throws Exception {
        List<Object> all = new ArrayList<>();
        String sql = "select * from( " +
                "select i.stu_id,i.stu_name,i.stu_age,i.stu_sex,i.stu_class,g.chinese,g.math,g.english,g.avg,g.count " +
                "from stu_info i,stu_grades g " +
                "where i.stu_id=g.stu_id order by g.count desc )";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery(sql);
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

    @Override
    public List<Object> AvgRank() throws Exception {
        List<Object> all = new ArrayList<>();
        String sql = "select * from( " +
                "select i.stu_id,i.stu_name,i.stu_age,i.stu_sex,i.stu_class,g.chinese,g.math,g.english,g.avg,g.count " +
                "from stu_info i,stu_grades g " +
                "where i.stu_id=g.stu_id order by g.avg desc )";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery(sql);
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

