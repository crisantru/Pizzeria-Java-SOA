package ws;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.Connect;
import pojos.Country;
import pojos.Department;
import pojos.Employee;
import pojos.Job;
import pojos.JobHistory;
import pojos.Location;
import pojos.Region;

public class Services {

    private Connect conn;
  
    public Services() {
    }

    private void openConnection() {
        conn = new Connect("localhost", "XE", "HR", "Nocturnal2802");
    }

    private void closeConnection() {
        conn.close();
    }
    
    
    
    //**********SERVICES REGION
    public Region getRegion(int idRegion) {
        Region res = null;
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM REGIONS WHERE REGION_ID = " + idRegion);
            if (rs != null && rs.next()) {
                res = new Region(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Region getRegionN(String regionName) {
        Region res = null;
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM REGIONS WHERE REGION_NAME = " + "'" + regionName + "'");
            if (rs != null && rs.next()) {
                res = new Region(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public List<Region> getAllRegion() {
        
        List<Region> res = new ArrayList<Region>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM REGIONS");
            while (rs != null && rs.next()) {
                res.add(new Region(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public void deleteRegion(int idRegion) {
        openConnection();
        conn.execute("DELETE FROM REGIONS WHERE REGION_ID = " + idRegion);
        closeConnection();
    }

    public boolean insertRegion(int idRegion, String regionName) {
        System.out.println("Entra a insertar");
        boolean res = false;
        openConnection();
        String q = "INSERT INTO REGIONS(REGION_ID,REGION_NAME) VALUES(" + idRegion + ",'" + regionName + "')";
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res = true;
            return res;
        }
        
        return res;
    }

    public boolean updateRegion(int idRegion, String regionName) {
        boolean res = false;
        openConnection();
        String q = "UPDATE REGIONS SET REGION_NAME='" + regionName + "' WHERE REGION_ID = " + idRegion;
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res= true;
            return res;
        }
        
        return res;
    }
    
    //**********SERVICES COUNTRY
    public Country getCountry(String idCountry){
        Country res = null;
        
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM COUNTRIES WHERE COUNTRY_ID = " +
                    "'" + idCountry + "'");
            if (rs != null && rs.next()) {
                res = new Country(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Country getCountryName(String countryName){
        Country res = null;
        
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM COUNTRIES WHERE COUNTRY_NAME = " +
                    "'" + countryName + "'");
            if (rs != null && rs.next()) {
                res = new Country(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public List<Country> getAllCountry(){
        List<Country> res = new ArrayList<Country>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM COUNTRIES");
            while (rs != null && rs.next()) {
                res.add(new Country(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public boolean insertCountry(String idCountry, String countryName, int idRegion) {
        System.out.println("Entra a insertar");
        openConnection();
        boolean res=false;
        
        String q = "INSERT INTO COUNTRIES(COUNTRY_ID, COUNTRY_NAME, REGION_ID) VALUES(" +
                "'" + idCountry + "', " + "'" + countryName + "', " + idRegion + ")";
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res = true;
            return res;
        }
        
        return res;
    }
     
    public void deleteCountry(String idCountry) {
        openConnection();
        String q = "DELETE FROM COUNTRIES WHERE COUNTRY_ID = '" +
                idCountry + "'";
        System.out.println(q);
        conn.execute("DELETE FROM COUNTRIES WHERE COUNTRY_ID = '" +
                idCountry + "'");
        closeConnection();
    }
    
    public boolean updateCountry(String idCountry, String countryName, int idRegion) {
        boolean res = false;
        openConnection();
        String q = "UPDATE COUNTRIES SET COUNTRY_NAME='" + countryName + "', " +
                "REGION_ID=" + idRegion + " WHERE COUNTRY_ID='" + idCountry + "'";
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res = true;
            return res;
        }
        
        return res;
    }
    
    //**********SERVICES DEPARTMENT
    public Department getDepartment(int idDepartment){
        Department res = null;
        
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = " +
                    "'" + idDepartment + "'");
            if (rs != null && rs.next()) {
                res = new Department(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
     
    public List<Department> getAllDepartment(){
        List<Department> res = new ArrayList<Department>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM DEPARTMENTS");
            while (rs != null && rs.next()) {
                res.add(new Department(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public boolean deleteDepartment(int idDepartment) {
        openConnection();
        boolean res = false;
        
        Statement st = conn.execute("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = " + idDepartment);
        closeConnection();
        
        if(st != null){
            res = true;
            return res;
        }
        
        return res;
    }
    
    public boolean insertDepartment(int idDepartment, String departmentName, int idManager, int idLocation) {
        
        openConnection();
        boolean res = false;
        
        String q = "INSERT INTO DEPARTMENTS(DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID) VALUES(" 
                + idDepartment + ",'" + departmentName + "'," 
                + (idManager == 0 ? "null" : idManager) + "," 
                + idLocation + ")";
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res = true;
            return res;
        }
        
        return res;
    }
    
    public void updateDepartment(int idDepartment, String departmentName, int idManager, int idLocation) {
        openConnection();
        String q = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME='" + departmentName + "',MANAGER_ID=" + idManager + ",LOCATION_ID=" + idLocation + " WHERE DEPARTMENT_ID = " + idDepartment;
        System.out.println(q);
        conn.execute(q);
        closeConnection();
    }
    
    //*********SERVICES JOB
    public Job getJob(String idJob){
        Job res = null;
        
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM JOBS WHERE JOB_ID = " +
                    "'" + idJob + "'");
            if (rs != null && rs.next()) {
                res = new Job(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Job getJobName(String jobTitle){
      Job res = null;

      try {
          openConnection();
          ResultSet rs = conn.query("SELECT * FROM JOBS WHERE JOB_TITLE = " +
                  "'" + jobTitle + "'");
          if (rs != null && rs.next()) {
              res = new Job(rs);
          }
          closeConnection();
      } catch (SQLException ex) {
          Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
      }
      return res;
    }
    
    public List<Job> getAllJob(){
        List<Job> res = new ArrayList<Job>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM JOBS");
            while (rs != null && rs.next()) {
                res.add(new Job(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public boolean insertJob(String idJob, String jobTitle, int minSalary, int maxSalary) {
        openConnection();
        boolean res = false;
        
        String q = "INSERT INTO JOBS(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) VALUES(" +
                "'" + idJob + "', " + "'" + jobTitle + "', " + minSalary + ","+ maxSalary + ")";
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res=true;
            return res;
        }
        
        return res;
    }
    
    public void deleteJob(String idJob) {
        openConnection();
        String q = "DELETE FROM JOBS WHERE JOB_ID = '" +
                idJob + "'";
        System.out.println(q);
        conn.execute("DELETE FROM JOBS WHERE JOB_ID = '" +
                idJob + "'");
        closeConnection();
    }
    
    public void updateJob(String idJob, String jobTitle, int minSalary, int maxSalary) {
        openConnection();
        String q = "UPDATE JOBS SET JOB_TITLE='" + jobTitle + "', " + "MIN_SALARY=" + minSalary +
                ", MAX_SALARY=" + maxSalary + " WHERE JOB_ID='" + idJob + "'";
        System.out.println(q);
        conn.execute(q);
        closeConnection();
    }
    
    //SERVICES LOCATION 
    public Location getLocation(int idLocation) {
        Location res = null;
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM LOCATIONS WHERE LOCATION_ID = " + idLocation);
            if (rs != null && rs.next()) {
                res = new Location(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public List<Location> getAllLocation() {
        List<Location> res = new ArrayList<Location>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM LOCATIONS");
            while (rs != null && rs.next()) {
                res.add(new Location(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void deleteLocation(int idLocation) {
        openConnection();
        conn.execute("DELETE FROM LOCATIONS WHERE LOCATION_ID = " + idLocation);
        closeConnection();
    }
    
    public boolean insertLocation(int idLocation, String streetAddress, String postalCode, String city, String stateProvince, String idCountry) {
        openConnection();
        boolean res = false;
        
        String q = "INSERT INTO LOCATIONS(LOCATION_ID,STREET_ADDRESS,POSTAL_CODE,CITY,STATE_PROVINCE,COUNTRY_ID) VALUES(" 
                + idLocation + ",'" + streetAddress + "','" + postalCode + "','" 
                + city + "','" + stateProvince+"','"+idCountry + "')";
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res=true;
            
        }
        
        return res;
        
    }
    
    public void updateLocation(int idLocation, String streetAddress, String postalCode, String city, String stateProvince, String idCountry) {
        openConnection();
        String q = "UPDATE LOCATIONS SET STREET_ADDRESS='" + streetAddress + "',POSTAL_CODE='" + postalCode + "',CITY='" + city + "',STATE_PROVINCE='" + stateProvince + "',COUNTRY_ID='" + idCountry + "' WHERE LOCATION_ID = " + idLocation;
        System.out.println(q);
        conn.execute(q);
        closeConnection();
    }
    
    //SERVICES HISTORY_JOB
    public JobHistory getJobHistory(int idEmployee, String startDate) {
        JobHistory res = null;
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM JOB_HISTORY WHERE EMPLOYEE_ID = " + idEmployee + " AND START_DATE=TO_DATE('"+startDate+"', 'YYYY-MM-DD')");
            if (rs != null && rs.next()) {
                res = new JobHistory(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public List<JobHistory> getAllJobHistory() {
        List<JobHistory> res = new ArrayList<JobHistory>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM JOB_HISTORY");
            while (rs != null && rs.next()) {
                res.add(new JobHistory(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void deleteJobHistory(int idEmployee, String startDate) {
        openConnection();
        conn.execute("DELETE FROM JOB_HISTORY WHERE EMPLOYEE_ID = " + idEmployee + " AND START_DATE=TO_DATE('"+startDate+"', 'YYYY-MM-DD')");
        closeConnection();
    }
    
    public void insertJobHistory(int idEmployee, String startDate, String endDate, String idJob, int idDepartment) {
        openConnection();
        String q = "INSERT INTO JOB_HISTORY(EMPLOYEE_ID,START_DATE,END_DATE,JOB_ID,DEPARTMENT_ID) VALUES("+idEmployee+",TO_DATE('"+startDate+"','YYYY-MM-DD'),TO_DATE('"+endDate+"','YYYY-MM-DD'),'"+idJob+"',"+idDepartment+")";
        System.out.println(q);
        conn.execute(q);
        closeConnection();
    }
    
    public void updateJobHistory(int idEmployee, String startDate, String endDate, String idJob, int idDepartment) {
        openConnection();
        String q = "UPDATE JOB_HISTORY SET EMPLOYEE_ID="+idEmployee+",START_DATE=TO_DATE('"+startDate+"','YYYY-MM-DD'),END_DATE=TO_DATE('"+endDate+"','YYYY-MM-DD'),JOB_ID='"+idJob+"',DEPARTMENT_ID="+idDepartment+" WHERE EMPLOYEE_ID = " + idEmployee + " AND START_DATE=TO_DATE('"+startDate+"', 'YYYY-MM-DD')";
        System.out.println(q);
        conn.execute(q);
        closeConnection();
    }
    
    //SERVICES EMPLOYEE
    public Employee getEmployee(int idEmployee) {
        Employee res = null;
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = " + idEmployee);
            if (rs != null && rs.next()) {
                res = new Employee(rs);
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
     
    public List<Employee> getAllEmployee() {
        List<Employee> res = new ArrayList<Employee>();
        try {
            openConnection();
            ResultSet rs = conn.query("SELECT * FROM EMPLOYEES");
            while (rs != null && rs.next()) {
                res.add(new Employee(rs));
            }
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
     
    public void deleteEmployee(int idEmployee) {
        openConnection();
        conn.execute("DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = " + idEmployee);
        closeConnection();
    }
    
    public boolean insertEmployee(int idEmployee, String firstName, String lastName,
            String email, String phoneNumber, String hireDate, String idJob, float salary,
            float commissionPct, int idManager, int idDepartment) {
        
        openConnection();
        boolean res=false;
        
        String q = "INSERT INTO EMPLOYEES(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,"
                + "PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,"
                + "DEPARTMENT_ID) VALUES("+idEmployee+",'"+firstName+"','"
                +lastName+"','"+email+"','"+phoneNumber+"',TO_DATE('"+hireDate+"','YYYY-MM-DD'),'"
                +idJob+"',"+salary+","+commissionPct+","+idManager+","+idDepartment+")";
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
         if(st != null){
            res=true;
            
        }
        
        return res;
        
        
    }

    public boolean updateEmployee(int idEmployee, String firstName, String lastName, 
            String email, String phoneNumber, String hireDate, String idJob, 
            float salary, float commissionPct, int idManager, int idDepartment) {
        
        openConnection();
        boolean res= false;
        String q = "UPDATE EMPLOYEES SET EMPLOYEE_ID="+idEmployee+",FIRST_NAME='"
                +firstName+"',LAST_NAME='"+lastName+"',EMAIL='"+email+"',PHONE_NUMBER='"
                +phoneNumber+"',HIRE_DATE=TO_DATE('"+hireDate+"','YYYY-MM-DD'),JOB_ID='"
                +idJob+"',SALARY="+salary+",COMMISSION_PCT="+commissionPct+",MANAGER_ID="
                +idManager+",DEPARTMENT_ID="+idDepartment+ " WHERE EMPLOYEE_ID = " + idEmployee;
        
        System.out.println(q);
        Statement st = conn.execute(q);
        closeConnection();
        
        if(st != null){
            res=true;
            
        }
        
        return res;
        
    }
    
    
    public static void main(String args[]) {
        Services s = new Services();
        
        
 /*     System.out.println("1.-" + s.getRegion(1));
        System.out.println("2.-" + s.getAllRegion());
        s.deleteRegion(5);
        s.insertRegion(5, "AAAAAAAAA");
        s.updateRegion(5, "BBBBBB");*/
        
        //System.out.println(s.getCountry("AR"));
        //System.out.println(s.getAllCountry());
        //s.insertCountry("EX", "FEI", 1);
        //s.deleteCountry("EX");
        //s.updateCountry("EX", null, 1);
        //s.updateCountry("EX", "FEI_3" , 3);
        
        //System.out.println(s.getDepartment(10));
        //System.out.println(s.getAllDepartment());
        
        //System.out.println(s.getJob("AD_PRES"));
        //System.out.println(s.getAllJob());
        //s.insertJob("XXX", "TEST", 100, 100);
        //s.deleteJob("XXX");
        //s.updateJob("XXX", "YYYYY", 50, 50);
        
        //s.getEmployee(100);
        
        
        
    }
}
