package com.atguigu.imperial.court.dao;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * BaseDao所有Dao的父类
 * @param <T> 操作数据库的实体类
 */


public class BaseDao<T> {

    //导入的jar包中的DButils工具库  提供的一个数据库操作类，创建对象
    private QueryRunner runner = new QueryRunner();

    //"..."表示可变数组类型的参数

    /**
     *查询方法，查询单个对象
     * @param sql 执行查询的aql语句
     * @param entityClass 实体类的class对象
     * @param parameters  传给sql语句的参数
     * @return 查询到的实体类对象（查询后的数据结果在类中，再通过类的get方法获取数据）
     */
    public T getSingleBean(String sql,Class<T> entityClass,Object ... parameters){

        try {
            //获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();

            //如果真的出现异常，将编译时异常封装为运行时异常抛出
            throw new RuntimeException(e);
        }


    }

    /**
     * 查询方法，查询多个对象
     * @param sql sql语句
     * @param entityClass 实体类的class对象
     * @param parameters sql参数
     * @return 查询到的实体类对象
     */

    public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters){

        try {
            //获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanListHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();

            //如果真的出现异常，将编译时异常封装为运行时异常抛出
            throw new RuntimeException(e);
        }


    }

    /**
     * 增删改方法，插入、更新或删除
     * @param sql 执行的sql语句
     * @param parameters  sql的参数
     * @return 受影响的行数
     */

    public  int  update(String sql , Object ... parameters){

        try {
            Connection connection = JDBCUtils.getConnection();

            int affectedRowNumbers = runner.update(connection,sql,parameters);

            return affectedRowNumbers;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new  RuntimeException(e);
        }

    }
}
