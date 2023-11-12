package jrails;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.io.*;
import books.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JRouterTest {

    private JRouter jRouter;

    @Before
    public void setUp() throws Exception {
        jRouter = new JRouter();
    }

    @Test
    public void addRoute() {
        jRouter.addRoute("GET", "/", String.class, "index");
        assertThat(jRouter.getRoute("GET", "/"), is("java.lang.String#index"));
    }

    // @Test
    // public void addRouteTest6() {
    //     Map<String, String> params = new LinkedHashMap<String, String>();
    //     jRouter.addRoute("GET", "/", BookController.class, "index");
    //     Html test = jRouter.route("GET", "/", params);
    //     System.err.println("test: " + test);
    // }


    
    //FAIL TEST CASE
    // @Test
    // public void addRouteTest1() {
    //     jRouter.addRoute("", "/", String.class, "");
    //     assertThat(jRouter.getRoute("", "/"), is("java.lang.String#"));
    // }
    // @Test
    // public void addRouteTest2() {
    //     jRouter.addRoute("123", "/", Integer.class, "#");
    //     assertThat(jRouter.getRoute("", "/"), is("java.lang.String#"));
    // }
    // @Test
    // public void addRouteTest3() {
    //     jRouter.addRoute("#", "/", Brown.class, "#");
    //     assertThat(jRouter.getRoute("", "/"), is("java.lang.String#"));
    // }
    // @Test
    // public void addRouteTest4() {
    //     jRouter.addRoute(null, "/", Integer.class, "#");
    //     assertThat(jRouter.getRoute("", "/"), is("java.lang.String#"));
    // }
    // @Test
    // //duplicateVerbAndPathWithDiffClassAndMethod
    // public void addRouteTest4() {
    //     try
    //     {
    //         jRouter.addRoute("GET", "/", String.class, "index");
    //         jRouter.addRoute("GET", "/", Integer.class, "index");            
    //     }
    //     catch (Exception e)
    //     {
    //         System.err.println("FAILURE EXPECTED: " + e);
    //     }
    // }
    // @Test
    // public void addRouteTest5() {
    //     jRouter.addRoute("car", "DOG", String.class, "index");
    //     assertThat(jRouter.getRoute("car", "DOG"), is("java.lang.String#"));
    // }
}