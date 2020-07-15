package com.dbmanager;

import com.pojos.Department;
import com.pojos.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
    Connection con = DBConnection.getConnection();

    public Integer addDepartment(Department department) {
        Integer row = null;
        try {
            String query = "insert into department(department_name) values(?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, department.getName());
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }

    public Integer updateDepartment(Department d) {
        Integer row = null;
        try {
            String updateQuery = "update department set department_name = ? where department_id" +
                    " = ? ";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, d.getName());
            pstmt.setInt(2, d.getId());
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer deleteDepartment(Integer departmentId) {
        Integer row = null;
        try {
            String deleteQuery = "delete from department where department_id = ? ";
            PreparedStatement pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, departmentId);
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public ResultSet searchDepartment(String departmentName) {
        Integer row = null;
        ResultSet rs = null;
        try {
            String searchQuery = "select * from department where department_name = ?";
            PreparedStatement pstmt = con.prepareStatement(searchQuery);
            pstmt.setString(1, departmentName);
            rs = pstmt.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return rs;
    }

    public ResultSet viewAllDepartment() {
        Integer row = null;
        ResultSet rs = null;
        try {
            String searchQuery = "select * from department";
            PreparedStatement pstmt = con.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return rs;

    }

    public Integer addStudent(Student student) {
        Integer row = null;
        try {
            String query = "insert into student(student_name, student_department) values(?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, String.valueOf(student.getDepartment()));
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }

    public Integer updateStudent(Student student) {
        Integer row = null;
        try {
            String updateQuery = "update student set student_name = ? where student_id = ? ";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getId());
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


    public Integer deleteStudent(Integer studentId) {
        Integer row = null;
        try {
            String deleteQuery = "delete from student where student_id = ? ";
            PreparedStatement pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, studentId);
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public ResultSet searchStudent(String studentName) {
        ResultSet rs = null;
        try {
            String searchQuery = "select s.student_id,s.student_name,d.department_name from student s "+
                    "inner join department d on d.department_id = s.student_department where student_name = ? ";
            PreparedStatement pstmt = con.prepareStatement(searchQuery);
            pstmt.setString(1, studentName);
            rs = pstmt.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return rs;
    }

    public ResultSet viewAllStudent() {
        Integer row = null;
        ResultSet rs = null;
        try {
            String query = "select s.student_id,s.student_name,d.department_name from student s "+
                    "inner join department d on d.department_id = s.student_department; ";
            PreparedStatement pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return rs;

    }


}

