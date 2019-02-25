package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Region {

    private int idRegion;
    private String regionName;

    public Region() {
    }

    public Region(int idRegion, String regionName) {
        this.idRegion = idRegion;
        this.regionName = regionName;
    }
    
    public Region(ResultSet rs) {
        try {
            this.idRegion = rs.getInt("REGION_ID");
            this.regionName = rs.getString("REGION_NAME");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Region{" + "idRegion=" + idRegion + ", regionName=" + regionName + '}';
    }
}
