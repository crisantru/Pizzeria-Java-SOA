package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung-pc
 */
public class Job {
    
    private String idJob;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;

    public Job() {
    }

    public Job(String idJob, String jobTitle, int minSalary, int maxSalary) {
        this.idJob = idJob;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Job{" + "idJob=" + idJob + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
    }
    
   public Job(ResultSet rs) {
        try {
            this.idJob = rs.getString("JOB_ID");
            this.jobTitle = rs.getString("JOB_TITLE");
            this.minSalary = rs.getInt("MIN_SALARY");
            this.maxSalary = rs.getInt("MAX_SALARY");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
