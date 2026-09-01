
package service;

import dsa.DataStore;
import model.Doctor;

public class DoctorService {
    public static boolean add(Doctor d){
        if(DataStore.doctorMap.containsKey(d.getId()) ||
           DataStore.doctorAccounts.containsKey(d.getUsername())) return false;
        DataStore.addDoctor(d);DataStore.actions.push("Added doctor "+d.getId());return true;
    }
    public static Doctor find(String id){return DataStore.doctorMap.get(id);}
    public static boolean update(String id,String name,int age,String gender,String phone,
                                 String spec,String availability,String username,String password){
        Doctor d=find(id);if(d==null)return false;
        if(!d.getUsername().equals(username)&&DataStore.doctorAccounts.containsKey(username))return false;
        DataStore.doctorAccounts.remove(d.getUsername());
        d.setName(name);d.setAge(age);d.setGender(gender);d.setPhone(phone);d.setSpecialization(spec);
        d.setAvailability(availability);d.setUsername(username);d.setPassword(password);
        DataStore.doctorAccounts.put(username,d);DataStore.actions.push("Edited doctor "+id);return true;
    }
    public static boolean delete(String id){
        Doctor d=find(id);if(d==null)return false;
        DataStore.doctors.remove(d);DataStore.doctorMap.remove(id);DataStore.doctorAccounts.remove(d.getUsername());
        DataStore.actions.push("Deleted doctor "+id);return true;
    }
}
