
package model;

public class Doctor {
    private String id, name, gender, phone, specialization, username, password, availability;
    private int age;

    public Doctor(String id,String name,int age,String gender,String phone,String specialization,
                  String availability,String username,String password){
        this.id=id; this.name=name; this.age=age; this.gender=gender; this.phone=phone;
        this.specialization=specialization; this.availability=availability;
        this.username=username; this.password=password;
    }
    public String getId(){return id;} public String getName(){return name;}
    public int getAge(){return age;} public String getGender(){return gender;}
    public String getPhone(){return phone;} public String getSpecialization(){return specialization;}
    public String getAvailability(){return availability;} public String getUsername(){return username;}
    public String getPassword(){return password;}
    public void setName(String v){name=v;} public void setAge(int v){age=v;}
    public void setGender(String v){gender=v;} public void setPhone(String v){phone=v;}
    public void setSpecialization(String v){specialization=v;} public void setAvailability(String v){availability=v;}
    public void setUsername(String v){username=v;} public void setPassword(String v){password=v;}

    public String toString(){
        return "ID="+id+" | Dr. "+name+" | Age="+age+" | Gender="+gender+
               " | Specialization="+specialization+" | Phone="+phone+
               " | Availability="+availability+" | Username="+username;
    }
}
