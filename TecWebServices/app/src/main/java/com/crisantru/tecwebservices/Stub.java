package com.crisantru.tecwebservices;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Stub {
    private static final String BASE_URL = "http://172.20.10.10:8080/TecWebSoap/webresources/service/";
    /**
     * Path de
     * Servicios from Netbeans
     */
    //************************************REGION****************************************************
    private static final String URL_SEARCHREGIONBYID = BASE_URL + "getRegion?idRegion=";
    private static final String URL_SETREGION = BASE_URL + "setRegion?idRegion=%s&regionName=%s";
    private static final String URL_DELETEREGION = BASE_URL + "deleteRegion?idRegion=%s";
    private static final String URL_SEARCHREGIONBYNAME = BASE_URL + "searchRegion?nameRegion=";
    //**********************************************************************************************

    //************************************COUNTRY***************************************************
    private static final String URL_SEARCHCOUNTRYBYID = BASE_URL + "getCountry?idCountry=";
    private static final String URL_SEARCHCOUNTRYBYNAME = BASE_URL + "getCountryName?countryName=";
    private static final String URL_SETCOUNTRY = BASE_URL + "setCountry?idCountry=%s&countryName=%s&idRegion=%s";
    private static final String URL_DELETECOUNTRY = BASE_URL + "deleteCountry?idCountry=%s";
    //**********************************************************************************************

    //************************************JOB*******************************************************
    private static final String URL_SEARCHJOBBYID = BASE_URL + "getJob?idJob=";
    private static final String URL_SEARCHJOBBYNAME = BASE_URL + "getJobName?jobTitle=";
    private static final String URL_SETJOB = BASE_URL + "setJob?idJob=%s&jobTitle=%s&minSalary=%s&maxSalary=%s";
    private static final String URL_DELETEJOB = BASE_URL + "deleteJob?idJob=%s";
    //**********************************************************************************************

    //************************************EMPLOYEE**************************************************
    private static final String URL_SEARCHEMPLOYEEBYID = BASE_URL + "getEmployee?idEmployee=";
    private static final String URL_SETEMPLOYEE = BASE_URL + "setEmployee?idEmployee=%s" +
            "&firstName=%s&lastName=%s&email=%s&phoneNumber=%s&hireDate=%s&idJob=%s&salary=%s" +
            "&commissionPCT=%s&idManager=%s&idDepartment=%s";
    private static final String URL_DELETEEMPLOYEE = BASE_URL + "deleteEmployee?idEmployee=%s";


    //***************************Methods for Region*************************************************
    //getRegion for id
    public static Region getRegion(int idRegion) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHREGIONBYID + idRegion);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Region model = gson.fromJson(finalObject.toString(), Region.class);
            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //getRegionN for name
    public static Region getRegionN(String nameRegion) {
        Log.d("path","Entra a bucar por nombre getRN");
        Log.d("path",URL_SEARCHREGIONBYNAME + nameRegion);
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHREGIONBYNAME + nameRegion);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Region model = gson.fromJson(finalObject.toString(), Region.class);
            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //insertRegion
    public static boolean setRegion(Integer idRegion, String name) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_SETREGION, idRegion.toString(), name);
            Log.d("url", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //deleteRegion
    public static boolean deleteRegion(Integer idRegion) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_DELETEREGION, idRegion.toString());
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //**************************Methods for Country*************************************************
    //getCountry for id
    public static Country getCountryId(String idCountry) {
        Log.d("path", "Entra a getCountryID");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHCOUNTRYBYID + idCountry);
            Log.d("url", URL_SEARCHCOUNTRYBYID + idCountry);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            Log.d("json", finalJson);
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Country model = gson.fromJson(finalObject.toString(), Country.class);

            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //getCountry for name
    public static Country getCountryName(String countryName) {
        Log.d("path", "Entra a getCountryName");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHCOUNTRYBYNAME + countryName);
            Log.d("url", URL_SEARCHCOUNTRYBYNAME + countryName);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            Log.d("json", finalJson);
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Country model = gson.fromJson(finalObject.toString(), Country.class);

            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //insertCountry
    public static boolean setCountry(String idCountry, String countryName, Integer idRegion){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_SETCOUNTRY, idCountry, countryName, idRegion.toString());
            Log.d("url", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    //deleteCountry
    public static boolean deleteCountry(String idCountry) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_DELETECOUNTRY, idCountry);
            Log.d("URL", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //**************************Methods for Job*****************************************************
    //getJob for id
    public static Job getJobId(String idJob) {
        Log.d("path", "Entra a getJobID");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHJOBBYID + idJob);
            Log.d("url", URL_SEARCHJOBBYID + idJob);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            Log.d("json", finalJson);
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Job model = gson.fromJson(finalObject.toString(), Job.class);

            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //getJob for id
    public static Job getJobName(String jobTitle) {
        Log.d("path", "Entra a getJobName");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHJOBBYNAME + jobTitle);
            Log.d("url", URL_SEARCHJOBBYNAME + jobTitle);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            Log.d("json", finalJson);
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Job model = gson.fromJson(finalObject.toString(), Job.class);

            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //insertJob
    public static boolean setJob(String idJob, String jobTitle, Integer minSalary, Integer maxSalary){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_SETJOB, idJob, jobTitle, minSalary.toString(), maxSalary.toString());
            Log.d("url", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    //deleteJob
    public static boolean deleteJob(String idJob) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_DELETEJOB, idJob);
            Log.d("URL", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //**************************Methods for Job*****************************************************
    //getEmployee for id
    public static Employee getEmployeeId(Integer idEmployee) {
        Log.d("path", "Entra a getEmployeeID");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(URL_SEARCHEMPLOYEEBYID + idEmployee);
            Log.d("url", URL_SEARCHEMPLOYEEBYID + idEmployee);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            Log.d("json", finalJson);
            JSONObject finalObject = new JSONObject(finalJson);
            Gson gson = new Gson();
            Employee model = gson.fromJson(finalObject.toString(), Employee.class);
            return model;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //insertEmployee
    public static boolean setEmployee(Integer idEmployee, String firstName, String lastName, String email,
                                      String phoneNumber, String hireDate, String idJob, Float Salary,
                                      Float commissionPCT, Integer idManager, Integer idDepartment ){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_SETEMPLOYEE, idEmployee.toString(), firstName, lastName, email,
                    phoneNumber, hireDate, idJob, Salary.toString(), commissionPCT.toString(),
                    idManager.toString(), idDepartment.toString());

            Log.d("url", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    //deleteEmployee
    public static boolean deleteEmployee(Integer idEmployee) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String str = String.format(URL_DELETEEMPLOYEE, idEmployee);
            Log.d("URL", str);
            URL url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();
            return new Boolean(finalJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
