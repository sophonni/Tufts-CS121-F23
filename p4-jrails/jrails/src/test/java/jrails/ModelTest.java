package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import books.*;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    // private Model model;

    // @Before
    // public void setUp() throws Exception {
    //     model = new Model(){};
    // }

    // @Test
    // public void id() {
    //     assertThat(model.id(), notNullValue());
    // }

    @Test
    public void id1() {
        Book b = new Book();
        b.title = "Math";
        b.author = "John";
        b.num_copies = 1;
        b.save();
        b.num_copies = 200;
        b.save();

        Book b2 = new Book();
        b2.title = "JAVA";
        b2.author = "Sam";
        b2.num_copies = 399;
        b2.save();
        // // System.err.println("Books: " + Model.Books_to_Info_Mapping);
        // Book b3 = new Book();
        // b3.title = "C++";
        // b3.author = "Mark";
        // b3.num_copies = 75;
        // b3.save();
        // b2.num_copies = 39;
        // b2.save();

        // Model.reset();
    }

    @After
    public void tearDown() throws Exception {
    }
}