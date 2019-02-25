package pojos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung-pc
 */
public class Location {
    
    private int idLocation;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private int idCountry;

    public Location() {
    }

    public Location(int idLocation, String streetAddress, String postalCode, String city, String stateProvince, int idCountry) {
        this.idLocation = idLocation;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.idCountry = idCountry;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public String toString() {
        return "Location{" + "idLocation=" + idLocation + ", streetAddress=" + streetAddress + ", postalCode=" + postalCode + ", city=" + city + ", stateProvince=" + stateProvince + ", idCountry=" + idCountry + '}';
    }
    
    public Location(ResultSet rs) {
        try {
            this.idLocation = rs.getInt("LOCATION_ID");
            this.streetAddress = rs.getString("STREET_ADDRESS");
            this.postalCode = rs.getString("POSTAL_CODE");
            this.city = rs.getString("CITY");
            this.stateProvince = rs.getString("STATE_PROVINCE");
            this.idCountry = rs.getInt("COUNTRY_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Region.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
