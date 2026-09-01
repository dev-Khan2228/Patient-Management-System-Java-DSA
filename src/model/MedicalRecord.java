
package model;

public class MedicalRecord {
    private String id, patientId, doctorId, date, diagnosis, prescription;

    public MedicalRecord(String id,String patientId,String doctorId,String date,
                         String diagnosis,String prescription){
        this.id=id; this.patientId=patientId; this.doctorId=doctorId;
        this.date=date; this.diagnosis=diagnosis; this.prescription=prescription;
    }
    public String getId(){return id;} public String getPatientId(){return patientId;}
    public String getDoctorId(){return doctorId;} public String getDate(){return date;}
    public String getDiagnosis(){return diagnosis;} public String getPrescription(){return prescription;}
    public void setPatientId(String v){patientId=v;} public void setDoctorId(String v){doctorId=v;}
    public void setDate(String v){date=v;} public void setDiagnosis(String v){diagnosis=v;}
    public void setPrescription(String v){prescription=v;}

    public String toString(){
        return "ID="+id+" | Patient="+patientId+" | Doctor="+doctorId+" | Date="+date+
               " | Diagnosis="+diagnosis+" | Prescription="+prescription;
    }
}
