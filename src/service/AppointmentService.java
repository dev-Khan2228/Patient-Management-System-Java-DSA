
package service;

import dsa.DataStore;
import model.Appointment;

public class AppointmentService {
    public static boolean add(Appointment a){
        if(find(a.getId())!=null)return false;
        DataStore.appointments.add(a);rebuildQueues();DataStore.actions.push("Added appointment "+a.getId());return true;
    }
    public static Appointment find(String id){
        for(Appointment a:DataStore.appointments)if(a.getId().equals(id))return a;
        return null;
    }
    public static boolean delete(String id){
        Appointment a=find(id);if(a==null)return false;
        DataStore.appointments.remove(a);rebuildQueues();DataStore.actions.push("Deleted appointment "+id);return true;
    }
    public static boolean update(String id,String pid,String did,String date,String time,
                                 boolean emergency,String status){
        Appointment a=find(id);if(a==null)return false;
        a.setPatientId(pid);a.setDoctorId(did);a.setDate(date);a.setTime(time);
        a.setEmergency(emergency);a.setStatus(status);rebuildQueues();
        DataStore.actions.push("Edited appointment "+id);return true;
    }
    public static void rebuildQueues(){
        DataStore.normalQueue.clear();DataStore.emergencyQueue.clear();
        for(Appointment a:DataStore.appointments)if(a.getStatus().equals("Pending")){
            if(a.isEmergency())DataStore.emergencyQueue.add(a);else DataStore.normalQueue.add(a);
        }
    }
}
