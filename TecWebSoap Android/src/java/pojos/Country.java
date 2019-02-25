
package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gygabyte-PC
 */
public class Country {
    
    private String idCountry;
    private String countryName;
    private int idRegion;
    
    public Country(){
    }

    public Country(String idCountry, String countryName, int idRegion) {
        this.idCountry = idCountry;
        this.countryName = countryName;
        this.idRegion = idRegion;
    }
    
    public Country(ResultSet rs) {
        try {
            this.idCountry = rs.getString("COUNTRY_ID");
            this.countryName = rs.getString("COUNTRY_NAME");
            this.idRegion = rs.getInt("REGION_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
    
    @Override
    public String toString() {
        return "Country{" + "idCountry=" + idCountry + ", countryName=" + countryName + ", idRegion=" + idRegion + '}';
    }
}
