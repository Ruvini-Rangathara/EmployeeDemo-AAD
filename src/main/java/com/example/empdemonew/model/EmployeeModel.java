package com.example.empdemonew.model;

import com.example.empdemonew.dto.EmployeeDto;
import com.example.empdemonew.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeModel {
    public static boolean saveEmployeeToDatabase(EmployeeDto employeeDto){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(employeeDto);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public static boolean updateEmployeeDatabase(EmployeeDto employeeDto){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(employeeDto);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public static boolean deleteEmployeeDatabase(EmployeeDto employeeDto){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(employeeDto);
            transaction.commit();

            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public static EmployeeDto searchEmployee(String id){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            EmployeeDto employeeDto = session.get(EmployeeDto.class, id);
            transaction.commit();

            return employeeDto;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
