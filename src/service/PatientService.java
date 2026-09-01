
package service;

import dsa.DataStore;
import model.Patient;
import java.util.ArrayList;

public class PatientService {
    public static boolean add(Patient p){
        if(DataStore.patientMap.containsKey(p.getId()) ||
           DataStore.patientAccounts.containsKey(p.getUsername())) return false;
        DataStore.addPatient(p); DataStore.actions.push("Added patient "+p.getId()); return true;
    }
    public static Patient find(String id){return DataStore.patientMap.get(id);}
    public static boolean update(String id,String name,int age,String gender,String phone,
                                 String address,String username,String password){
        Patient p=find(id); if(p==null)return false;
        if(!p.getUsername().equals(username) && DataStore.patientAccounts.containsKey(username)) return false;
        DataStore.patientAccounts.remove(p.getUsername());
        p.setName(name);p.setAge(age);p.setGender(gender);p.setPhone(phone);p.setAddress(address);
        p.setUsername(username);p.setPassword(password);DataStore.patientAccounts.put(username,p);
        DataStore.actions.push("Edited patient "+id); return true;
    }
    public static boolean delete(String id){
        Patient p=find(id);if(p==null)return false;
        DataStore.patients.remove(p);DataStore.patientMap.remove(id);DataStore.patientAccounts.remove(p.getUsername());
        DataStore.actions.push("Deleted patient "+id);return true;
    }
    public static ArrayList<Patient> sortedByName(){
        ArrayList<Patient>a=new ArrayList<>(DataStore.patients);mergeSort(a,0,a.size()-1);return a;
    }
    private static void mergeSort(ArrayList<Patient>a,int l,int r){
        if(l>=r)return;int m=(l+r)/2;mergeSort(a,l,m);mergeSort(a,m+1,r);merge(a,l,m,r);
    }
    private static void merge(ArrayList<Patient>a,int l,int m,int r){
        ArrayList<Patient>t=new ArrayList<>();int i=l,j=m+1;
        while(i<=m&&j<=r)t.add(a.get(i).getName().compareToIgnoreCase(a.get(j).getName())<=0?a.get(i++):a.get(j++));
        while(i<=m)t.add(a.get(i++));while(j<=r)t.add(a.get(j++));
        for(int k=0;k<t.size();k++)a.set(l+k,t.get(k));
    }
}
