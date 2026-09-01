
package model;

public class Patient {
    private String id, name, gender, phone, address, username, password;
    private int age;

    public Patient(String id,String name,int age,String gender,String phone,String address,
                   String username,String password){
        this.id=id; this.name=name; this.age=age; this.gender=gender;
        this.phone=phone; this.address=address; this.username=username; this.password=password;
    }
    public String getId(){return id;} public String getName(){return name;}
    public int getAge(){return age;} public String getGender(){return gender;}
    public String getPhone(){return phone;} public String getAddress(){return address;}
    public String getUsername(){return username;} public String getPassword(){return password;}
    public void setName(String v){name=v;} public void setAge(int v){age=v;}
    public void setGender(String v){gender=v;} public void setPhone(String v){phone=v;}
    public void setAddress(String v){address=v;} public void setUsername(String v){username=v;}
    public void setPassword(String v){password=v;}

    public String toString(){
        return "ID="+id+" | Name="+name+" | Age="+age+" | Gender="+gender+
               " | Phone="+phone+" | Address="+address+" | Username="+username;
    }
}
