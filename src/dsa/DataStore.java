
package dsa;

import java.util.*;
import model.*;

public class DataStore {
    public static ArrayList<Patient> patients=new ArrayList<>();
    public static ArrayList<Doctor> doctors=new ArrayList<>();
    public static LinkedList<MedicalRecord> records=new LinkedList<>();
    public static ArrayList<Appointment> appointments=new ArrayList<>();

    public static HashMap<String,Patient> patientMap=new HashMap<>();
    public static HashMap<String,Doctor> doctorMap=new HashMap<>();
    public static HashMap<String,Patient> patientAccounts=new HashMap<>();
    public static HashMap<String,Doctor> doctorAccounts=new HashMap<>();

    public static Queue<Appointment> normalQueue=new LinkedList<>();
    public static PriorityQueue<Appointment> emergencyQueue=new PriorityQueue<>(
        Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime));
    public static Stack<String> actions=new Stack<>();

    static {
        addPatient(new Patient("P1001","Sudheer",22,"Male","9876543210","Nellore",
                              "sudheer","sudheer123"));
        addPatient(new Patient("P1002","Anjali",23,"Female","9123456780","Nellore",
                              "anjali","anjali123"));
        addDoctor(new Doctor("D1001","Arjun",38,"Male","9000000001","Cardiologist",
                            "09:00-13:00","arjun","arjun123"));
        addDoctor(new Doctor("D1002","Priya",35,"Female","9000000002","Dermatologist",
                            "14:00-18:00","priya","priya123"));
    }

    public static void addPatient(Patient p){
        patients.add(p); patientMap.put(p.getId(),p); patientAccounts.put(p.getUsername(),p);
    }
    public static void addDoctor(Doctor d){
        doctors.add(d); doctorMap.put(d.getId(),d); doctorAccounts.put(d.getUsername(),d);
    }
}
