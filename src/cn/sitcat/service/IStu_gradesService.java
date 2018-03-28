/**
 * 学生成绩操作业务层实现接口
 * 定义stu_grades表的业务层的执行标准,此类一定要负责数据库的打开与关闭操作
 * 此类可以通过DAOFactory类取得IStu_grades接口对象
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.service;

import cn.sitcat.PO.Stu_grades;

import java.util.Set;
import java.util.List;


public interface IStu_gradesService<K,V> {
    /**
     *实现学生成绩的增加操作，本次操作要调用IStu_grades接口的如下方法：
     * 需要调用IStu_grades.findById()方法，判断要增加数据的id知否已经存在；
     * 如果现在要增加的数据编号不存在则调用IStu_grades.doCreate()方法，返回操作的结果
     * @param vo 包含了要增加数据的VO对象
     * @return 如果增加数据的ID重复或者保存失败返回false，否则返回true
     * @throws Exception SQL执行异常
     */
    public boolean insert(V vo) throws Exception;

    /**
     * 实现学生数据的修改操作，本次要调用IStu_grades.doUpdate()方法，本次修改属于全部内容的修改
     * @param vo 包含了要修改数据的VO对象
     * @return 修改成功返回true，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean update(V vo) throws Exception;

    /**
     * 执行学生成绩的删除操作，可以删除多个学生信息，调用IStu_grades.doRemoveBatch()方法
     * @param ids 包含了全部要删除数据的集合，没有重复数据
     * @return 删除成功返回true，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean delete(Set<K> ids) throws Exception;

    /**
     * 根据学生编号查找学号的成绩信息，调用IStu_grades.findById()方法
     * @param id 要查找的学生编号
     * @return 如果找到了则学生信息以VO对象返回，否则返回null
     * @throws Exception SQL执行异常
     */
    public Stu_grades get(K id) throws Exception;

    /**
     * 查询全部学生信息，调用IStu_grades.finaAll()方法
     * @return 查询结果以List集合的形式返回，如果没有数据则集合的长度为0
     * @throws Exception SQL执行异常
     */
    public List<V> list() throws Exception;

    /**
     * 按条件统计学生成绩数量
     * @param column
     * @param keyWord
     * @return 返回统计总数
     * @throws Exception SQL执行异常
     */
    public int listCount(String column,String keyWord) throws Exception;

    /**
     * 将学生成绩按照总分排名
     * @return 返回学生总分排名。包含学号，姓名，成绩，平均分，总分。按总分排名
     * @throws Exception SQL执行异常
     */
    public List<Object> TotalRank() throws Exception;

    /**
     * 将学生成绩按照平均分排名
     * @return 返回学生平均分排名。包含学号，姓名，成绩，平均分，总分。按平均分排名
     * @throws Exception SQL执行异常
     */
    public List<Object> AvgRank() throws Exception;
}


