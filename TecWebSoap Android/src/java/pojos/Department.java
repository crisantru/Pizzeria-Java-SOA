package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung-pc
 */
public class Department {
    
    private int idDepartment;
    private String departmentName;
    private int idManager;
    private int idLocation;

    public Department() {
    }

    public Department(int idDepartmen, String departmentName, int idManager, int idLocation) {
        this.idDepartment = idDepartmen;
        this.departmentName = departmentName;
        this.idManager = idManager;
        this.idLocation = idLocation;
    }

    public int getIdDepartmen() {
        return idDepartment;
    }

    public void setIdDepartmen(int idDepartmen) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    @Override
    public String toString() {
        return "Department{" + "idDepartmen=" + idDepartment + ", departmentName=" + departmentName + ", idManager=" + idManager + ", idLocation=" + idLocation + '}';
    }
    
    public Department(ResultSet rs) {
        try {
            this.idDepartment = rs.getInt("DEPARTMENT_ID");
            this.departmentName = rs.getString("DEPARTMENT_NAME");
            this.idManager = rs.getInt("MANAGER_ID");
            this.idLocation = rs.getInt("LOCATION_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
