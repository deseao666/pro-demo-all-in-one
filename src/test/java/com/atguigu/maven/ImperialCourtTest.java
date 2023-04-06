package com.atguigu.maven;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ImperialCourtTest {

    private BaseDao<Emp> baseDao = new BaseDao<>();

    @Test
    public  void  testGetsingleBean(){
        String sql ="select  emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from  t_emp where emp_id=?;";
        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);

        System.out.println("emp="+emp);
    }

    @Test
    public  void  testGetBeanList(){

        String sql ="select  emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from  t_emp";

        List<Emp> beanList = baseDao.getBeanList(sql, Emp.class);

        for (Emp emp : beanList){
            System.out.println("emp="+emp);
        }
    }

    @Test
    public void testUpdate(){

        String sql ="update t_emp set  emp_position=? where emp_id=?";

            String empPosition ="emperor";
            String empId = "3";

        int update = baseDao.update(sql, empPosition, empId);

        System.out.println("更改了"+update+"行");
    }

    @Test
    public  void  testGetConnection(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection="+ connection);
        JDBCUtils.releaseConnection(connection);

    }
}
