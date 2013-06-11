package com.unrc.app;

import com.unrc.app.models.User;

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


public class Inmo {

    public static void main(String[] args) throws TemplateException {


        // Gets the owner resource for the provided id
        Spark.get(new Route("/users/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "");

                User user = User.findFirst("id = ?", request.params(":id"));

                Base.close();

                if (user != null) {
                    return "Name: " + user.get("first_name") + user.get("last_name") + ", email: " + user.get("email");
                } else {
                    response.status(404); // 404 Not found
                    return "User not found";
                }
            }
        });

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request rqst, Response rspns) {

                return "hola mundo cruel...";
            }
        });
    }
    // public static void main( String[] args )
    // {
    //     Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "");

    //     User e = new User();
    //     e.set("email", "user@email.com");
    //     e.set("first_name", "John");
    //     e.set("last_name", "Doe");
    //     e.saveIt();

    //     User e = User.findFirst("first_name = ?", "John");


    //     System.out.println( "Hello World!" );
    // }
}