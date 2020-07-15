package com.main;

import com.dbmanager.DBManager;
import com.pojos.Department;
import com.pojos.Student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class StudentDepartment {
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(r);
    Student student = null;
    DBManager dbManager = new DBManager();
    Scanner scanner = new Scanner(System.in);
    Department department = null;
    String studentName = null;
    Integer studentId = null;
    Integer departmentNo = null;



    public static void main(String args[]) throws Exception{
        Scanner scanner = new Scanner(System.in);
        StudentDepartment studentDepartment = new StudentDepartment();

        System.out.println("<<<<<<< WELCOME TO STUDENT DEPARTMENT MANAGEMENT SYSTEM >>>>>>>");
        Integer choice = null;

        studentDepartment.menu();
    }

    // Student methods
    public void addStudent(){

        System.out.println("********* ADD NEW STUDENT *********\n");

        try {
            System.out.print("    Enter STUDENT Name : ");
            studentName = input.readLine();
            System.out.print("    Enter Department Number : ");
            departmentNo = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        student = new Student();
        //student.setId(studentId);
        student.setName(studentName);
        student.setDepartment(departmentNo);
        if (dbManager.addStudent(student) > 0) {
            System.out.println(studentName + " Added Successfully.");
        } else {
            System.out.println("Error");
        }
    }


    public void menu() throws SQLException {
        Integer choice = null;
        System.out.println("    1. Student");
        System.out.println("    2. Department");
        System.out.print("        Choose Category : ");
        choice = scanner.nextInt();

        if (choice == 1) {
            studentMenu();
        } else if (choice == 2) {
            departmentMenu();
        } else {
            System.out.println("Please choose the correct category (1-2)");
        }

    }

    public void studentMenu() throws SQLException {
        Integer choice = null;
        System.out.println("<<<<<< MANAGE STUDENT >>>>>>\n\n");
        System.out.println("    1. ADD");
        System.out.println("    2. UPDATE");
        System.out.println("    3. DELETE");
        System.out.println("    4. SEARCH");
        System.out.println("    5. VIEW ALL");
        System.out.println("    6. Back");
        System.out.print("\n Enter : ");

        try {
            choice = Integer.parseInt(input.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
            switch (choice) {
                case 1:
                    addStudent();
                    studentMenu();
                    break;
                case 2:
                    updateStudent();
                    studentMenu();
                    break;
                case 3:
                    deleteStudent();
                    studentMenu();
                    break;
                case 4:
                    searchStudent();
                    studentMenu();
                    break;
                case 5:
                    viewAllStudent();
                    studentMenu();
                    break;
                case 6:
                    menu();
                    break;

            }
        } else {
            System.out.println("Please choose b/w 1-6");
        }

    }

    public void departmentMenu() throws SQLException {
        Integer choice = null;
        System.out.println("<<<<<< MANAGE DEPARTMENT >>>>>>\n\n");
        System.out.println("    1. ADD");
        System.out.println("    2. UPDATE");
        System.out.println("    3. DELETE");
        System.out.println("    4. SEARCH");
        System.out.println("    5. VIEW ALL");
        System.out.println("    6. Back");
        System.out.print("\n Enter : ");

        try {
            choice = Integer.parseInt(input.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
            switch (choice) {
                case 1:
                    addDepartment();
                    departmentMenu();
                    break;
                case 2:
                    updateDepartment();
                    departmentMenu();
                    break;
                case 3:
                    deleteDepartment();
                    departmentMenu();
                    break;
                case 4:
                    searchDepartment();
                    departmentMenu();
                    break;
                case 5:
                    viewAllDepartment();
                    departmentMenu();
                    break;
                case 6:
                    menu();
                    break;

            }
        } else {
            System.out.println("Please choose b/w 1-6");
        }
    }

    public void addDepartment() {
        String departmrntName = null;
        System.out.println("********* ADD NEW DEPARTMENT *********\n");
        System.out.print("    Enter Department Name : ");
        try {
            departmrntName = input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        department = new Department();
        department.setId(1);
        department.setName(departmrntName);
        if (dbManager.addDepartment(department) > 0) {
            System.out.println(departmrntName + " Added Successfully.");
        } else {
            System.out.println("Error");
        }
    }

    public void updateDepartment() {
        Integer departmentNumber = null;
        String newDepartmentName = null;
        System.out.println("********* UPDATE EXISTING DEPARTMENT *********\n");

        try {
            System.out.print("    Enter Department Number : ");
            departmentNumber = Integer.parseInt(input.readLine());
            System.out.print("    Enter New Department Name : ");
            newDepartmentName = input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        department = new Department();
        department.setId(departmentNumber);
        department.setName(newDepartmentName);
        if (dbManager.updateDepartment(department) > 0) {
            System.out.println("Department Updated Successfully.");
        } else {
            System.out.println("Error");
        }
    }

    public void deleteDepartment() {
        Integer departmentNumber = null;
        String newDepartmentName = null;
        System.out.println("********* DELETE EXISTING DEPARTMENT *********\n");

        try {
            System.out.print("    Enter Department Number : ");
            departmentNumber = Integer.parseInt(input.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbManager.deleteDepartment(departmentNumber) > 0) {
            System.out.println("Department Updated Successfully.");
        } else {
            System.out.println("Error");
        }
    }

    // view a single department
    public void searchDepartment() throws SQLException {
        String departmentName = null;
        System.out.println("********* SEARCH DEPARTMENT *********\n");

        try {
            System.out.print("    Enter Department Name : ");
            departmentName = input.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbManager.searchDepartment(departmentName) != null) {
            ResultSet rs = dbManager.searchDepartment(departmentName);
            System.out.println("ID      NAME");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "      ");
                System.out.println(rs.getString(2));

            }
            System.out.println();
        } else {
            System.out.println("Error");
        }

    }

    // view all department
    public void viewAllDepartment() throws SQLException {

        System.out.println("********* VIEW ALL DEPARTMENT *********\n");


        if (dbManager.viewAllDepartment() != null) {
            ResultSet rs = dbManager.viewAllDepartment();
            System.out.println("ID      NAME");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "      ");
                System.out.println(rs.getString(2));

            }
            System.out.println();
        } else {
            System.out.println("Error");
        }

    }

    public void updateStudent() {
        Integer studentNumber = null;
        String newStudentName = null;
        System.out.println("********* UPDATE EXISTING STUDENT *********\n");

        try {
            System.out.print("    Enter STUDENT ID : ");
            studentNumber = Integer.parseInt(input.readLine());
            System.out.print("    Enter New STUDENT Name : ");
            newStudentName = input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        student = new Student();
        student.setId(studentNumber);
        student.setName(newStudentName);
        if (dbManager.updateStudent(student) > 0) {
            System.out.println("Student Updated Successfully.");
        } else {
            System.out.println("Error");
        }
    }

    public void deleteStudent() {
        Integer studentNumber = null;
        String newStudentName = null;
        System.out.println("********* DELETE EXISTING STUDENT *********\n");

        try {
            System.out.print("    Enter Student Number : ");
            studentNumber = Integer.parseInt(input.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbManager.deleteStudent(studentNumber) > 0) {
            System.out.println("Student Deleted Successfully.");
        } else {
            System.out.println("Error");
        }
    }

    // view a single department
    public void searchStudent() throws SQLException {
        String studentName = null;
        System.out.println("********* SEARCH STUDENT *********\n");

        try {
            System.out.print("    Enter Student Name : ");
            studentName = input.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbManager.searchStudent(studentName) != null) {
            ResultSet rs = dbManager.searchStudent(studentName);
            System.out.println("ID      NAME    DEPARTMENT");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "      ");
                System.out.print(rs.getString(2) + "      ");
                System.out.println(rs.getString(3));

            }
            System.out.println();
        } else {
            System.out.println("Error");
        }

    }

    // view all department
    public void viewAllStudent() throws SQLException {

        System.out.println("********* VIEW ALL Student *********\n");


        if (dbManager.viewAllStudent() != null) {
            ResultSet rs = dbManager.viewAllStudent();
            System.out.println("ID      NAME    DEPARTMENT");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "      ");
                System.out.print(rs.getString(2) + "      ");
                System.out.println(rs.getString(3) + "      ");

            }
            System.out.println();
        } else {
            System.out.println("Error");
        }

    }
}
