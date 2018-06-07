package hnditcom.libraryapp.activities.model;

public class User {
    public String userName;
    public String password;
    public String id;
    public String age;



    public User(){

    }

 /*   public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }*/

    public User(String userName, String password, String id) {
        this.userName = userName;
        this.password = password;
        this.id = id;

    }

    public User(String userName, String password, String id, String age) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.age = age;
    }

}
