
import java.util.*;
import model.*;
import dsa.DataStore;
import service.*;

public class Main {
    static Scanner sc=new Scanner(System.in);
    static Patient currentPatient;
    static Doctor currentDoctor;

    public static void main(String[] args){
        while(true){
            System.out.println("\n==========================================");
            System.out.println("       PATIENT MANAGEMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Patient Login");
            System.out.println("2. Doctor Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Patient Registration");
            System.out.println("5. Doctor Registration");
            System.out.println("6. Exit");
            int c=num("Enter choice: ");
            if(c==1)patientLogin();else if(c==2)doctorLogin();else if(c==3)adminLogin();
            else if(c==4)patientRegistration();else if(c==5)doctorRegistration();
            else if(c==6)break;else System.out.println("Invalid choice.");
        }
    }

    static void patientLogin(){
        currentPatient=AuthService.patientLogin(read("Username: "),read("Password: "));
        if(currentPatient==null){System.out.println("Invalid patient username or password.");return;}
        System.out.println("Welcome, "+currentPatient.getName()+"!");patientMenu();
    }
    static void doctorLogin(){
        currentDoctor=AuthService.doctorLogin(read("Username: "),read("Password: "));
        if(currentDoctor==null){System.out.println("Invalid doctor username or password.");return;}
        System.out.println("Welcome Dr. "+currentDoctor.getName()+"!");doctorMenu();
    }
    static void adminLogin(){
        if(!AuthService.adminLogin(read("Username: "),read("Password: "))){System.out.println("Invalid admin login.");return;}
        adminMenu();
    }

    static void patientRegistration(){
        System.out.println("\n--- PATIENT REGISTRATION ---");
        String id=read("Create Patient ID: "),name=read("Name: ");int age=num("Age: ");
        String gender=read("Gender: "),phone=read("Phone: "),address=read("Address: ");
        String user=read("Create Username: "),pass=read("Create Password: ");
        if(user.isEmpty()||pass.isEmpty()){System.out.println("Username/password cannot be empty.");return;}
        boolean ok=PatientService.add(new Patient(id,name,age,gender,phone,address,user,pass));
        System.out.println(ok?"Registration successful. You can now login.":"Patient ID or username already exists.");
    }

    static void doctorRegistration(){
        System.out.println("\n--- DOCTOR REGISTRATION ---");
        String id=read("Create Doctor ID: "),name=read("Name: ");int age=num("Age: ");
        String gender=read("Gender: "),phone=read("Phone: "),spec=read("Specialization: ");
        String avail=read("Availability: "),user=read("Create Username: "),pass=read("Create Password: ");
        if(user.isEmpty()||pass.isEmpty()){System.out.println("Username/password cannot be empty.");return;}
        boolean ok=DoctorService.add(new Doctor(id,name,age,gender,phone,spec,avail,user,pass));
        System.out.println(ok?"Registration successful. You can now login.":"Doctor ID or username already exists.");
    }

    static void patientMenu(){
        while(true){
            System.out.println("\n--- PATIENT MENU ---");
            System.out.println("1.My Profile  2.Search Doctor  3.Book Appointment");
            System.out.println("4.My Appointments  5.My Medical History  6.Change Password  7.Logout");
            int c=num("Choice: ");
            switch(c){
                case 1:System.out.println(currentPatient);break;
                case 2:searchDoctor();break;
                case 3:book(currentPatient.getId());break;
                case 4:appointmentsForPatient(currentPatient.getId());break;
                case 5:recordsForPatient(currentPatient.getId());break;
                case 6:currentPatient.setPassword(read("New password: "));System.out.println("Changed.");break;
                case 7:currentPatient=null;return;
                default:System.out.println("Invalid choice.");
            }
        }
    }

    static void doctorMenu(){
        while(true){
            System.out.println("\n--- DOCTOR MENU ---");
            System.out.println("1.My Profile  2.My Patients  3.My Appointments");
            System.out.println("4.Update Appointment Status  5.Add Medical Record  6.My Records  7.Change Password  8.Logout");
            int c=num("Choice: ");
            switch(c){
                case 1:System.out.println(currentDoctor);break;
                case 2:patientsForDoctor(currentDoctor.getId());break;
                case 3:appointmentsForDoctor(currentDoctor.getId());break;
                case 4:doctorUpdateAppointment();break;
                case 5:addRecord(currentDoctor.getId());break;
                case 6:recordsForDoctor(currentDoctor.getId());break;
                case 7:currentDoctor.setPassword(read("New password: "));System.out.println("Changed.");break;
                case 8:currentDoctor=null;return;
                default:System.out.println("Invalid choice.");
            }
        }
    }

    static void adminMenu(){
        while(true){
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Medical Records");
            System.out.println("5. System Reports");
            System.out.println("6. Recent Actions (Stack)");
            System.out.println("7. Logout");
            int c=num("Choice: ");
            switch(c){
                case 1:adminPatients();break;case 2:adminDoctors();break;case 3:adminAppointments();break;
                case 4:adminRecords();break;case 5:reports();break;case 6:recentActions();break;
                case 7:return;default:System.out.println("Invalid choice.");
            }
        }
    }

    static void adminPatients(){
        while(true){
            System.out.println("\n--- ADMIN: PATIENTS ---");
            System.out.println("1.Add 2.View 3.Search 4.Edit EVERY DETAIL 5.Delete 6.Back");
            int c=num("Choice: ");
            if(c==1)patientRegistration();
            else if(c==2)for(Patient p:PatientService.sortedByName())System.out.println(p);
            else if(c==3)searchPatient();
            else if(c==4)editPatient();
            else if(c==5){String id=read("Patient ID: ");System.out.println(PatientService.delete(id)?"Deleted.":"Not found.");}
            else if(c==6)return;else System.out.println("Invalid choice.");
        }
    }

    static void adminDoctors(){
        while(true){
            System.out.println("\n--- ADMIN: DOCTORS ---");
            System.out.println("1.Add 2.View 3.Search 4.Edit EVERY DETAIL 5.Delete 6.Back");
            int c=num("Choice: ");
            if(c==1)doctorRegistration();
            else if(c==2)for(Doctor d:DataStore.doctors)System.out.println(d);
            else if(c==3)searchDoctor();
            else if(c==4)editDoctor();
            else if(c==5){String id=read("Doctor ID: ");System.out.println(DoctorService.delete(id)?"Deleted.":"Not found.");}
            else if(c==6)return;else System.out.println("Invalid choice.");
        }
    }

    static void adminAppointments(){
        while(true){
            System.out.println("\n--- ADMIN: APPOINTMENTS ---");
            System.out.println("1.View All 2.Add 3.Edit 4.Delete 5.Back");
            int c=num("Choice: ");
            if(c==1)for(Appointment a:DataStore.appointments)System.out.println(a);
            else if(c==2)book(read("Patient ID: "));
            else if(c==3)editAppointment();
            else if(c==4){String id=read("Appointment ID: ");System.out.println(AppointmentService.delete(id)?"Deleted.":"Not found.");}
            else if(c==5)return;else System.out.println("Invalid choice.");
        }
    }

    static void adminRecords(){
        while(true){
            System.out.println("\n--- ADMIN: MEDICAL RECORDS ---");
            System.out.println("1.View All 2.Add 3.Edit EVERY DETAIL 4.Delete 5.Back");
            int c=num("Choice: ");
            if(c==1)for(MedicalRecord r:DataStore.records)System.out.println(r);
            else if(c==2)addRecord(read("Doctor ID: "));
            else if(c==3)editRecord();
            else if(c==4){String id=read("Record ID: ");System.out.println(RecordService.delete(id)?"Deleted.":"Not found.");}
            else if(c==5)return;else System.out.println("Invalid choice.");
        }
    }

    static void editPatient(){
        String id=read("Patient ID: ");Patient p=PatientService.find(id);
        if(p==null){System.out.println("Not found.");return;}
        String n=read("Name: ");int a=num("Age: ");String g=read("Gender: "),ph=read("Phone: "),ad=read("Address: ");
        String u=read("Username: "),pw=read("Password: ");
        System.out.println(PatientService.update(id,n,a,g,ph,ad,u,pw)?"Updated.":"Update failed: username may already exist.");
    }

    static void editDoctor(){
        String id=read("Doctor ID: ");Doctor d=DoctorService.find(id);
        if(d==null){System.out.println("Not found.");return;}
        String n=read("Name: ");int a=num("Age: ");String g=read("Gender: "),ph=read("Phone: ");
        String s=read("Specialization: "),av=read("Availability: "),u=read("Username: "),pw=read("Password: ");
        System.out.println(DoctorService.update(id,n,a,g,ph,s,av,u,pw)?"Updated.":"Update failed: username may already exist.");
    }

    static void editAppointment(){
        String id=read("Appointment ID: ");Appointment a=AppointmentService.find(id);
        if(a==null){System.out.println("Not found.");return;}
        String pid=read("Patient ID: "),did=read("Doctor ID: ");
        if(PatientService.find(pid)==null||DoctorService.find(did)==null){System.out.println("Invalid patient/doctor.");return;}
        String date=read("Date: "),time=read("Time: "),status=read("Status (Pending/Accepted/Completed/Cancelled): ");
        boolean em=read("Emergency? (y/n): ").equalsIgnoreCase("y");
        System.out.println(AppointmentService.update(id,pid,did,date,time,em,status)?"Updated.":"Update failed.");
    }

    static void editRecord(){
        String id=read("Record ID: ");MedicalRecord r=RecordService.find(id);
        if(r==null){System.out.println("Not found.");return;}
        String pid=read("Patient ID: "),did=read("Doctor ID: ");
        if(PatientService.find(pid)==null||DoctorService.find(did)==null){System.out.println("Invalid patient/doctor.");return;}
        String date=read("Date: "),diag=read("Diagnosis: "),pres=read("Prescription: ");
        System.out.println(RecordService.update(id,pid,did,date,diag,pres)?"Updated.":"Update failed.");
    }

    static void searchPatient(){Patient p=PatientService.find(read("Patient ID: "));System.out.println(p==null?"Not found.":p);}
    static void searchDoctor(){Doctor d=DoctorService.find(read("Doctor ID: "));System.out.println(d==null?"Not found.":d);}

    static void book(String pid){
        if(PatientService.find(pid)==null){System.out.println("Patient not found.");return;}
        String id=read("Appointment ID: "),did=read("Doctor ID: ");
        if(DoctorService.find(did)==null){System.out.println("Doctor not found.");return;}
        String date=read("Date (YYYY-MM-DD): "),time=read("Time: ");
        boolean em=read("Emergency? (y/n): ").equalsIgnoreCase("y");
        System.out.println(AppointmentService.add(new Appointment(id,pid,did,date,time,em))?"Booked.":"Appointment ID already exists.");
    }

    static void appointmentsForPatient(String pid){
        boolean found=false;for(Appointment a:DataStore.appointments)if(a.getPatientId().equals(pid)){System.out.println(a);found=true;}
        if(!found)System.out.println("No appointments.");
    }
    static void appointmentsForDoctor(String did){
        boolean found=false;for(Appointment a:DataStore.appointments)if(a.getDoctorId().equals(did)){System.out.println(a);found=true;}
        if(!found)System.out.println("No appointments.");
    }
    static void patientsForDoctor(String did){
        HashSet<String> seen=new HashSet<>();boolean found=false;
        for(Appointment a:DataStore.appointments)if(a.getDoctorId().equals(did)&&seen.add(a.getPatientId())){
            Patient p=PatientService.find(a.getPatientId());if(p!=null){System.out.println(p);found=true;}
        }
        if(!found)System.out.println("No assigned patients.");
    }
    static void recordsForPatient(String pid){
        boolean found=false;for(MedicalRecord r:DataStore.records)if(r.getPatientId().equals(pid)){System.out.println(r);found=true;}
        if(!found)System.out.println("No medical records.");
    }
    static void recordsForDoctor(String did){
        boolean found=false;for(MedicalRecord r:DataStore.records)if(r.getDoctorId().equals(did)){System.out.println(r);found=true;}
        if(!found)System.out.println("No medical records.");
    }
    static void addRecord(String did){
        String id=read("Record ID: "),pid=read("Patient ID: ");
        if(PatientService.find(pid)==null||DoctorService.find(did)==null){System.out.println("Invalid ID.");return;}
        String date=read("Date: "),diag=read("Diagnosis: "),pres=read("Prescription: ");
        System.out.println(RecordService.add(new MedicalRecord(id,pid,did,date,diag,pres))?"Added.":"Record ID already exists.");
    }
    static void doctorUpdateAppointment(){
        String id=read("Appointment ID: ");Appointment a=AppointmentService.find(id);
        if(a==null||!a.getDoctorId().equals(currentDoctor.getId())){System.out.println("Appointment not found for you.");return;}
        String status=read("New status (Accepted/Completed/Cancelled): ");a.setStatus(status);AppointmentService.rebuildQueues();
        System.out.println("Updated.");
    }
    static void reports(){
        System.out.println("Patients: "+DataStore.patients.size());
        System.out.println("Doctors: "+DataStore.doctors.size());
        System.out.println("Appointments: "+DataStore.appointments.size());
        System.out.println("Medical Records: "+DataStore.records.size());
        System.out.println("Normal Queue: "+DataStore.normalQueue.size());
        System.out.println("Emergency Queue: "+DataStore.emergencyQueue.size());
    }
    static void recentActions(){
        if(DataStore.actions.empty()){System.out.println("No actions.");return;}
        Stack<String> s=(Stack<String>)DataStore.actions.clone();
        while(!s.empty())System.out.println(s.pop());
    }
    static String read(String s){System.out.print(s);return sc.nextLine().trim();}
    static int num(String s){while(true)try{return Integer.parseInt(read(s));}catch(Exception e){System.out.println("Enter a valid number.");}}
}
