
package service;

import dsa.DataStore;
import model.Patient;
import model.Doctor;

public class AuthService {
    public static boolean adminLogin(String u,String p){
        return u.equals("admin") && p.equals("admin123");
    }
    public static Patient patientLogin(String u,String p){
        Patient x=DataStore.patientAccounts.get(u);
        return x!=null && x.getPassword().equals(p) ? x : null;
    }
    public static Doctor doctorLogin(String u,String p){
        Doctor x=DataStore.doctorAccounts.get(u);
        return x!=null && x.getPassword().equals(p) ? x : null;
    }
}
