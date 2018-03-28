/**
 * 学生成绩操作业务层实现类
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.service.impl;

import cn.sitcat.dbc.DatabaseConnection;
import cn.sitcat.factory.DAOFactory;
import cn.sitcat.service.IStu_gradesService;
import cn.sitcat.PO.Stu_grades;

import java.util.List;
import java.util.Set;

public class Stu_gradesServiceImpl implements IStu_gradesService<String, Stu_grades> {
    //在这个类的对象内部就提供有一个数据库连接类的实例化对象
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(Stu_grades vo) throws Exception {
        try {
            //要增加的学生编号如果不存在，则findById()返回的结果就是null，null表示可以进行新学生数据的保存
            //DAOFactory.getIStu_gradesInstance(this.dbc.getConnection())  getConnection需要接收一个dbcConnection对象
            //返回的是IEmpDAO的实例化对象
            //再调用findById 将传递的vo对象中的stu_id编号取出来进行查询，如果没有则返回null
            Stu_grades grades = new Stu_grades();
            int count = DAOFactory.getIStu_infoInstance(this.dbc.getConnection()).getAllCount("stu_id", vo.getStu_id());
            if (count > 0) {
                grades = (Stu_grades) DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).findById(vo.getStu_id());
                if (grades.getStu_id() == null) {
                    return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).doCreate(vo);
                }
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Stu_grades vo) throws Exception {
        try {
            int count = DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).getAllCount("stu_id", vo.getStu_id());
            if (count > 0) {
                return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).doUpdate(vo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
        return false;
    }

    @Override
    public boolean delete(Set<String> ids) throws Exception {
        try{
            return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Stu_grades get(String id) throws Exception {
        try{
            return (Stu_grades)DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).findById(id);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Stu_grades> list() throws Exception {
        try {
            return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).findAll();
        }catch (Exception e){
            throw e;
        }finally {
            dbc.close();
        }
    }

    @Override
    public int listCount(String column, String keyWord) throws Exception {
        try {
            return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).getAllCount(column,keyWord);
        }catch (Exception e){
            throw e;
        }finally {
            dbc.close();
        }
    }


    @Override
    public List<Object> TotalRank() throws Exception {
        try {
            return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).TotalRank();
        }catch (Exception e){
            throw e;
        }finally {
            dbc.close();
        }
    }

    @Override
    public List<Object> AvgRank() throws Exception {
        try {
            return DAOFactory.getIStu_gradesInstance(this.dbc.getConnection()).AvgRank();
        }catch (Exception e){
            throw e;
        }finally {
            dbc.close();
        }
    }
}