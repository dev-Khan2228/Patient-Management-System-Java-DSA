
package service;

import dsa.DataStore;
import model.MedicalRecord;

public class RecordService {
    public static boolean add(MedicalRecord r){
        if(find(r.getId())!=null)return false;
        DataStore.records.add(r);DataStore.actions.push("Added record "+r.getId());return true;
    }
    public static MedicalRecord find(String id){
        for(MedicalRecord r:DataStore.records)if(r.getId().equals(id))return r;
        return null;
    }
    public static boolean update(String id,String pid,String did,String date,String diagnosis,String prescription){
        MedicalRecord r=find(id);if(r==null)return false;
        r.setPatientId(pid);r.setDoctorId(did);r.setDate(date);r.setDiagnosis(diagnosis);r.setPrescription(prescription);
        DataStore.actions.push("Edited record "+id);return true;
    }
    public static boolean delete(String id){
        MedicalRecord r=find(id);if(r==null)return false;
        DataStore.records.remove(r);DataStore.actions.push("Deleted record "+id);return true;
    }
}
