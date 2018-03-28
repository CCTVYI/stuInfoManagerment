/**
 * 学生成绩表数据层实现接口
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.dao;

import java.util.List;
import java.util.Set;


/**
 * 定义公共的DAO操作接口标准，基本的功能包括：增加，修改全部，删除数据，根据编号查询，查询全部，分页显示，数据统计。
 * @param <K> 表示要操作的逐主键类型，数据类型，由子接口实现
 * @param <V> 表示要操作的VO类型，返回类型。由子接口实现。
 */
public interface IStu_gradesDAO<K,V> {
    /**
     * 实现数据的增加操作
     * @param vo 包含了要增加数据的VO对象
     * @return 数据保存成功返回true，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean doCreate(V vo) throws Exception;

    /**
     * 实现数据的修改操作 本次修改是根据id进行全部字段数据的修改
     * @param vo 包含了要修改数据的成绩，一定要提供ID内容
     * @return 数据修改成功返回true，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean doUpdate(V vo) throws Exception;

    /**
     * 执行数据的批量删除操作，所有要删除的数据以Set集合的形式保存
     * @param ids 包含了要删除数据的ID，不包含重复内容
     * @return 删除成功返回true(删除的数据个数要与删除的数据个数相同)，否则返回false
     * @throws Exception SQL执行异常
     */
    public boolean doRemoveBatch(Set<String> ids) throws Exception;

    /**
     * 根据学生编号查询只指定的学生成绩
     * @param id 要查询的学生编号
     * @return 如果学生成绩存在，则将数据以vo类对象的形式返回，如果学生数据不存在，则返回null
     * @throws Exception SQL执行异常
     */
    public V findById(String id) throws Exception;

    /**
     * 查询指定数据表的全部记录，并且以集合的形式返回
     * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用List集合返回，如果没有数据
     * 那么集合的长度为0（size() == 0,不是null）
     * @throws Exception SQL执行异常
     */
    public List<V> findAll() throws Exception;


    /**
     * 进行模糊查询数据量的统计,如果表中没有记录统计的结果就是0
     * @param column 要进行模糊查询的数据列
     * @param keyWord ，模糊查询的关键字
     * @return 返回表中的数据量，如果没有数据返回0
     * @throws Exception SQL执行异常
     */
    public Integer getAllCount(String column,String keyWord) throws Exception;

    /**
     * 将学生成绩按照总分排名
     * @return 返回学生总分排名。包含学号，姓名，成绩，平均分，总分。按总分排名
     * @throws Exception SQL执行异常
     */
    public   List<Object> TotalRank() throws Exception;

    /**
     * 将学生成绩按照平均分排名
     * @return 返回学生平均分排名。包含学号，姓名，成绩，平均分，总分。按平均分排名
     * @throws Exception SQL执行异常
     */
    public   List<Object> AvgRank() throws Exception;
}

