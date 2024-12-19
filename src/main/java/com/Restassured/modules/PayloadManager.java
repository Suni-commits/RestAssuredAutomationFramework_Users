package com.Restassured.modules;

import com.Restassured.pojos.Users;
import com.Restassured.pojos.UsersResponse;
import com.google.gson.Gson;

public class PayloadManager {

    private Gson gson=new Gson();

    public  String CreateUsers(){

        Users users=new Users();
        users.setName("Mohan Siva");
        users.setPassword("password");
        users.setEmail("mohan@example.com");
        users.setAvatar("https://picsum.photos/800");

        //Converting Object to JSON Object

        String userdetails=gson.toJson(users);

        System.out.println(userdetails);

        return userdetails ;
    }

    public UsersResponse usersResponse(String responsedata){

        return gson.fromJson(responsedata, UsersResponse.class);
    }

    public String UpdateUserDetails(){
        Users users=new Users();
        users.setName("Sadhasivam");
        users.setPassword("password12234");
        users.setEmail("sada1234@example.com");
        users.setAvatar("https://picsum.photos/800");

       // Convert users details to JSON string using Gson
        String userdetails=gson.toJson(users);
        System.out.println(userdetails);

        return userdetails ;
    }

}
