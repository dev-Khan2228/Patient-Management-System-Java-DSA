
package model;

public class Appointment {
    private String id, patientId, doctorId, date, time, status;
    private boolean emergency;

    public Appointment(String id,String patientId,String doctorId,String date,String time,boolean emergency){
        this.id=id; this.patientId=patientId; this.doctorId=doctorId;
        this.date=date; this.time=time; this.emergency=emergency; this.status="Pending";
    }
    public String getId(){return id;} public String getPatientId(){return patientId;}
    public String getDoctorId(){return doctorId;} public String getDate(){return date;}
    public String getTime(){return time;} public String getStatus(){return status;}
    public boolean isEmergency(){return emergency;}
    public void setPatientId(String v){patientId=v;} public void setDoctorId(String v){doctorId=v;}
    public void setDate(String v){date=v;} public void setTime(String v){time=v;}
    public void setEmergency(boolean v){emergency=v;} public void setStatus(String v){status=v;}

    public String toString(){
        return "ID="+id+" | Patient="+patientId+" | Doctor="+doctorId+" | "+
               date+" "+time+" | "+(emergency?"EMERGENCY":"NORMAL")+" | Status="+status;
    }
}
