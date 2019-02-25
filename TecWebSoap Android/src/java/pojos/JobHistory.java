package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung-pc
 */
public class JobHistory {
    
    private int idEmployee;
    private String startDate;
    private String endDay;
    private int idJob;
    private int idDepartment;

    public JobHistory() {
    }

    public JobHistory(int idEmployee, String startDate, String endDay, int idJob, int idDepartment) {
        this.idEmployee = idEmployee;
        this.startDate = startDate;
        this.endDay = endDay;
        this.idJob = idJob;
        this.idDepartment = idDepartment;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "JobHistory{" + "idEmployee=" + idEmployee + ", startDate=" + startDate + ", endDay=" + endDay + ", idJob=" + idJob + ", idDepartment=" + idDepartment + '}';
    }
    
    public JobHistory(ResultSet rs) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            this.idEmployee = rs.getInt("EMPLOYEE_ID");
            this.startDate = sdf.format(rs.getDate("START_DATE"));
            this.endDay = sdf.format(rs.getDate("END_DATE"));
            this.idJob = rs.getInt("JOB_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
