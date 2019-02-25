/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import persistence.Connect;
import pojos.Country;
import pojos.Department;
import pojos.Job;
import pojos.JobHistory;
import pojos.Location;
import pojos.Region;
import java.sql.Statement;
import pojos.Employee;

/**
 * REST Web Service
 *
 * @author samsung-pc
 */
@Path("service")
public class ServiceResource {
    private Connect conn;
    
     @Context
     private UriInfo context;
     
     private void openConnection() {
        conn = new Connect("localhost", "XE", "HR", "Nocturnal2802");
    }

    private void closeConnection() {
        conn.close();
    }
     
    //****************************************************REGION*************************************************************
    /*Crea un nuevo objeto de tipo Region*/
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setRegion")
     public boolean setRegion(@QueryParam("idRegion") int idRegion, @QueryParam("regionName") String regionName) throws SQLException{
         System.out.println("Entra a setRegion");
         openConnection();
         System.out.println(idRegion);
         Services service = new Services();
         boolean res = false;
         int id=0;
         Region r = service.getRegion(idRegion);
         
         try{
            if(r !=null){
                System.out.println("YA existe region entra a update");
                res = service.updateRegion(idRegion, regionName);
                res = true;
            }else{
                System.out.println("else Entra a insertar");
                ResultSet rs = conn.query("SELECT MAX(REGION_ID)+1 MAXID FROM REGIONS");
                if(rs !=null && rs.next()){
                    id = rs.getInt("MAXID");
                    res = service.insertRegion(id, regionName);
                    return res;
                }
            }
         }catch (SQLException ex) {
            Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return res;
     }

    /*Borra un objeto de tipo Region*/ 
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteRegion")
    public boolean deleteRegion(@QueryParam("idRegion") int idRegion){
        System.out.println("Entra a deleteRegion");
        
        Services service = new Services();
        boolean res = false;
        Region r = service.getRegion(idRegion);
        
        if(r != null){
            service.deleteRegion(idRegion);
            res= true;
        }
        
        return res;
    }
    
    /**
     * Obtiene un Lista de todos los objetos Region
     *
     * @return un Lista de objetos Region
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllRegion")
    public List<Region> getAllRegion() {
        System.out.println("Entra a getAllRegion");
        return new Services().getAllRegion();
    }
    
 
    /**
     * Obtiene un objeto Region filtrado por idRegion
     *
     * @param idRegion ID Region para filtrar
     * @return Objeto Region en caso de encontrarlo y null en caso contrario
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getRegion")
    public Region getRegion(@QueryParam("idRegion") int idRegion) {
        return new Services().getRegion(idRegion);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/searchRegion")
    public Region getRegionN(@QueryParam("nameRegion") String nameRegion) {
        return new Services().getRegionN(nameRegion);
    }
    //************************************************************************************************************************
    
    
    //****************************************************COUNTRY*************************************************************
     /**
     * Obtiene un Lista de todos los objetos Country
     *
     * @return un Lista de objetos Country
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllCountry")
    public List<Country> getAllCountry() {
        return new Services().getAllCountry();
    }
    
    /**
     * Obtiene un objeto Country filtrado por idCountry
     *
     * @param idCountry ID Country para filtrar
     * @return Objeto Country en caso de encontrarlo y null en caso contrario
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getCountry")
    public Country getCountry(@QueryParam("idCountry") String idCountry) {
        return new Services().getCountry(idCountry);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getCountryName")
    public Country getCountryName(@QueryParam("countryName") String countryName) {
        return new Services().getCountryName(countryName);
    }
    
    /**Inserta un objeto de tipo Country
    */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setCountry")
    public boolean setCountry(@QueryParam("idCountry") String idCountry, @QueryParam("countryName") String countryName,
            @QueryParam("idRegion") int idRegion){
        
        System.out.println("Entra a setCountry");
        openConnection();
        System.out.println(idCountry);
        Services service = new Services();
        boolean res = false;
         
        Country c = service.getCountry(idCountry);
         
        if(c !=null){
            System.out.println("YA existe country entra a update");
            res = service.updateCountry(idCountry, countryName, idRegion);
            res = true;
        }else{
            
            System.out.println("else Entra a insertar");
            res = service.insertCountry(idCountry, countryName, idRegion); 
            return res;
        }
         
        return res;
        
    }
    
    /**borra un objeto de tipo Country
      *mediante el idCountry
      */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteCountry")
    public boolean deleteCountry(@QueryParam("idCountry") String idCountry){
        System.out.println("Entra a deleteRegion");
        
        Services service = new Services();
        boolean res = false;
        Country c = service.getCountry(idCountry);
        
        if(c != null){
            service.deleteCountry(idCountry);
            res= true;
        }
        
        return res;
    }
    
    //************************************************************************************************************************
    
    
    //****************************************************DEPARMENT*************************************************************
    
     /**
     * Obtiene un Lista de todos los objetos Department
     *
     * @return un Lista de objetos Deparment
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllDepartment")
    public List<Department> getAllDepartment() {
        return new Services().getAllDepartment();
    }
    
    /**
     * Obtiene un objeto Department filtrado por idDeparment
     *
     * @param idDepartment ID Department para filtrar
     * @return Objeto Department en caso de encontrarlo y null en caso contrario
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getDepartment")
    public Department getDepartment(@QueryParam("idDepartment") int idDepartment) {
        return new Services().getDepartment(idDepartment);
    }
    
    /**
     * Inserta un objeto de tipo Department
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setDepartment")
    public boolean setDepartment(@QueryParam("idDepartment") int idDeparment, @QueryParam("departmentName") String departmentName,
            @QueryParam("idManager") int idManager, @QueryParam("idLocation") int idLocation)throws SQLException{
            
            openConnection();
            boolean res = false;
            int id = 0;
            Services service = new Services();
            
            Department d = service.getDepartment(idDeparment);
            
            try{
                if(d != null){
                    System.out.println("YA existe department entra a update");
                    service.updateDepartment(idDeparment, departmentName, idManager, idLocation);
                    res = true;
                }else{
                    ResultSet rs = conn.query("SELECT MAX(DEPARTMENT_ID)+10 MAXID FROM DEPARTMENTS");
                    if(rs !=null && rs.next()){
                        id = rs.getInt("MAXID");
                        res = service.insertDepartment(id, departmentName, idManager, idLocation);
                        return res;
                    }
                }
            }catch (SQLException ex) {
            Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
         }

         return res;
    }
    
    
    /**borra un objeto de tipo Department
      *mediante el idDepartment
      */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteDepartment")
    public boolean deleteDepartment(@QueryParam("idDepartment") int idDepartment){
        System.out.println("Entra a deleteDepartment");
        
        Services service = new Services();
        boolean res = false;
        Department d = service.getDepartment(idDepartment);
        
        if(d != null){
            res = service.deleteDepartment(idDepartment);
            return res;
        }
        
        return res;
    }
    
    
    
    
    //************************************************************************************************************************
    
    
    //****************************************************JOB*************************************************************
     /**
     * Obtiene un Lista de todos los objetos Job
     *
     * @return un Lista de objetos Job
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllJob")
    public List<Job> getAllJob() {
        return new Services().getAllJob();
    }
    
    /**
     * Obtiene un objeto Job filtrado por idJob
     *
     * @param idJob ID Job para filtrar
     * @return Objeto Job en caso de encontrarlo y null en caso contrario
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getJob")
    public Job getJob(@QueryParam("idJob") String idJob) {
        return new Services().getJob(idJob);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getJobName")
    public Job getJobName(@QueryParam("jobTitle") String jobTitle) {
        return new Services().getJobName(jobTitle);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setJob")
    public boolean setJob(@QueryParam("idJob") String idJob, @QueryParam("jobTitle") String jobTitle,
            @QueryParam("minSalary") int minSalary, @QueryParam("maxSalary") int maxSalary){
            
        openConnection();
        boolean res = false;
        Services service = new Services();
        
        Job j = service.getJob(idJob);
        
        if(j !=null){
            System.out.println("YA existe job entra a update");
            service.updateJob(idJob, jobTitle, minSalary, maxSalary);
            res = true;
            //return res;
        }else{
            
            System.out.println("else Entra a insertar");
            res = service.insertJob(idJob, jobTitle, minSalary, maxSalary); 
            return res;
        }
         
        return res;
        
    }
        
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteJob")
    public boolean deleteDepartment(@QueryParam("idJob") String idJob){
        System.out.println("Entra a deleteJob");
        
        Services service = new Services();
        boolean res = false;
        Job j = service.getJob(idJob);
        
        if(j != null){
            service.deleteJob(idJob);
            res = true;
        }
        
        return res;
    }
    
    
  
     //************************************************************************************************************************
    
    
    //****************************************************LOCATION*************************************************************
   
      /**
     * Obtiene un Lista de todos los objetos Location
     *
     * @return un Lista de objetos Location
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllLocation")
    public List<Location> getAllLocation() {
        return new Services().getAllLocation();
    }
    
    /**
     * Obtiene un objeto Location filtrado por idLocation
     *
     * @param idLocation ID Location para filtrar
     * @return Objeto Location en caso de encontrarlo y null en caso contrario
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getLocation")
    public Location getLocation(@QueryParam("idLocation") int idLocation) {
        return new Services().getLocation(idLocation);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setLocation")
     public boolean setLocation(@QueryParam("idLocation") int idLocation, @QueryParam("streetAddress") String streetAddress,
            @QueryParam("postalCode") String postalCode, @QueryParam("city") String city, 
            @QueryParam("stateProvince") String stateProvince, @QueryParam("idCountry") String idCountry) throws SQLException{
         openConnection();
         boolean res = false;
         int id = 0;
         Services service = new Services();
         
         Location l = service.getLocation(idLocation);
         try{
            if(l !=null){
               System.out.println("YA existe loc entra a update");
               service.updateLocation(idLocation, streetAddress, postalCode, city, 
                       stateProvince, idCountry);
               res = true;

           }else{
               System.out.println("else Entra a insertar");
                   ResultSet rs = conn.query("SELECT MAX(LOCATION_ID)+100 MAXID FROM LOCATIONS");
                   if(rs !=null && rs.next()){
                       id = rs.getInt("MAXID");
                       res = service.insertLocation(id, streetAddress, postalCode, city, stateProvince, idCountry);
                       return res;
                   }
               }
        }catch (SQLException ex) {
           Logger.getLogger(ServiceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return res;
         
         
         
     }
    
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteLocation")
    public boolean deleteLocation(@QueryParam("idLocation") int idLocation){
        System.out.println("Entra a deleteLocation");
        
        Services service = new Services();
        boolean res = false;
        Location l = service.getLocation(idLocation);
        
        if(l != null){
            service.deleteLocation(idLocation);
            res = true;
        }
        
        return res;
    }
    
    
     //************************************************************************************************************************
    
    
    //****************************************************JOBHISTORY*************************************************************
      /**
     * Obtiene un Lista de todos los objetos JobHistory
     *
     * @return un Lista de objetos JobHistory
     */
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllJobHistory")
    public List<JobHistory> getAllJobHistory() {
        return new Services().getAllJobHistory();
    }
    
    
    //************************************************************************************************************************
    
    
    //****************************************************EMPLOYEES*************************************************************
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return new Services().getAllEmployee();
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/getEmployee")
    public Employee getEmployee(@QueryParam("idEmployee") int idEmployee) {
        return new Services().getEmployee(idEmployee);
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/setEmployee")
    public boolean setEmployee(@QueryParam("idEmployee") int idEmployee, @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName, @QueryParam("email") String email, 
            @QueryParam("phoneNumber") String phoneNumber, @QueryParam("hireDate") String hireDate, 
            @QueryParam("idJob") String idJob, @QueryParam("salary") float salary, 
            @QueryParam("commissionPCT") float commissionPCT, @QueryParam("idManager") int idManager, 
            @QueryParam("idDepartment") int idDepartment) throws SQLException{
        
            openConnection();
            boolean res = false;
            Services service = new Services();
            int id= 0;
            
            Employee e = service.getEmployee(idEmployee);
            if(e != null){
                System.out.println("YA existe emp entra a update");
                service.updateEmployee(idEmployee, firstName, lastName, email,
                        phoneNumber, hireDate, idJob, salary, commissionPCT,
                        idManager, idDepartment);
                res = true;
            }else{
                ResultSet rs = conn.query("SELECT MAX(EMPLOYEE_ID)+1 MAXID FROM EMPLOYEES");
                if(rs !=null && rs.next()){
                       id = rs.getInt("MAXID");
                       res = service.insertEmployee(id, firstName, lastName, email,
                        phoneNumber, hireDate, idJob, salary, commissionPCT,
                        idManager, idDepartment);
                       return res;
                   }
            }
         
         return res;
    }
    
    @GET
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    @Path("/deleteEmployee")
    public boolean deleteEmployee(@QueryParam("idEmployee") int idEmployee){
        System.out.println("Entra a deleteEmployee");
        
        Services service = new Services();
        boolean res = false;
        Employee e = service.getEmployee(idEmployee);
        
        if(e != null){
            service.deleteEmployee(idEmployee);
            res = true;
        }
        
        return res;
    }
}
