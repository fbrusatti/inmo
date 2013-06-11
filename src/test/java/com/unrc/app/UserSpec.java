package com.unrc.app;

import com.unrc.app.models.User;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.*;

public class UserSpec{

    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "");
        Base.openTransaction();
    }

    @After
    public void after(){
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){

        User user = new User();

        //check errors
        the(user).shouldNotBe("valid");
        the(user.errors().get("first_name")).shouldBeEqual("value is missing");
        the(user.errors().get("last_name")).shouldBeEqual("value is missing");

        //set missing values
        user.set("first_name", "John", "last_name", "Doe");

        //all is good:
        the(user).shouldBe("valid");
    }
}
