package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee {

    private int idEmployee;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String idJob;
    private float salary;
    private float commissionPct;
    private int idManager;
    private int idDepartment;
    private float commissionMax;

    public Employee() {
    }

    public Employee(int idEmployee, String firstName, String lastName, String email, String phoneNumber, String hireDate, String idJob, float salary, float commissionPct, int idManager, int idDepartment, float commissionMax) {        
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.idJob = idJob;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.idManager = idManager;
        this.idDepartment = idDepartment;
        this.commissionMax = commissionMax;
    }

    public Employee(ResultSet rs) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.idEmployee = rs.getInt("EMPLOYEE_ID");
            this.firstName = rs.getString("FIRST_NAME");
            this.lastName = rs.getString("LAST_NAME");
            this.email = rs.getString("EMAIL");
            this.phoneNumber = rs.getString("PHONE_NUMBER");
            this.hireDate = sdf.format(rs.getDate("HIRE_DATE"));
            this.idJob = rs.getString("JOB_ID");
            this.salary = rs.getFloat("SALARY");
            this.commissionPct = rs.getFloat("COMMISSION_PCT");
            this.idManager = rs.getInt("MANAGER_ID");
            this.idDepartment = rs.getInt("DEPARTMENT_ID");
//            this.commissionMax = rs.getFloat("COMMISSION_MAX");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(float commissionPct) {
        this.commissionPct = commissionPct;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "Employee{" + "idEmployee=" + idEmployee + ", firstName=" 
                + firstName + ", lastName=" + lastName + ", email=" + email 
                + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate 
                + ", idJob=" + idJob + ", salary=" + salary + ", commissionPct=" 
                + commissionPct + ", idManager=" + idManager + ", idDepartment=" + idDepartment + '}';
    }
}
