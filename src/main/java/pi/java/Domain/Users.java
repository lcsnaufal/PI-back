package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Users {

    int id = 0;
    public String name = "";
    public String lastName = "";
    public String age = "";
    public String address = "";
    public String email = "";
    public String password = "";
    public String cpf = "";

    public Users(){
    }

    public Users(String name, String lastName, String age, String Address, String email, String password, String cpf){

        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }
    public Users(int id, String name, String lastName, String age, String Address, String email, String password, String cpf){

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getCpf(){

        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("lastName", lastName);
        json.put("age", age);
        json.put("address", address);
        json.put("email", email);
        json.put("password", password);
        json.put("cpf", cpf);

        return json;
    }

    public JSONObject arrayToJson(List<Users> usersList) {
        JSONObject json = new JSONObject();

        if (!usersList.isEmpty()) {
            var keyJson = 0;

            for (Users users : usersList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("name", users.getName());
                jsonFor.put("lastName", users.getLastName());
                jsonFor.put("phoneNumber", users.getAge());
                jsonFor.put("address", users.getAddress());
                jsonFor.put("email", users.getEmail());
                jsonFor.put("password", users.getPassword());
                jsonFor.put("cpf", users.getCpf());

                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(users.getName());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Users getUser(int index, List<Users> usersList){

        if(index >= 0 && index < usersList.size())  {

            return usersList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Users> getAllUsers(List<Users> usersList){
        return usersList;
    }
}
