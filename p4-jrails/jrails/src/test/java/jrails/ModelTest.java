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

    // @Test
    // public void id1() {
    //     Book b = new Book();
    //     b.title = "Math";
    //     b.author = "John";
    //     b.num_copies = 1;
    //     b.save();
    //     b.num_copies = 200;
    //     b.save();

    //     Book b2 = new Book();
    //     b2.title = "JAVA";
    //     b2.author = "Sam";
    //     b2.num_copies = 399;
    //     b2.save();

    //     //Model.TestHelper();
        

    //     Class<Book> test = Book.class;
    //     Model.find(test, 1);

    //     Book b3 = new Book();
    //     b3.title = "PYTHON";
    //     b3.author = "Peter";
    //     b3.num_copies = 89;
    //     b3.save();

    //     b2.destroy();
    //     b.destroy();
    //     b3.destroy();
    //     // // System.err.println("Books: " + Model.Books_to_Info_Mapping);
    //     // Book b3 = new Book();
    //     // b3.title = "C++";
    //     // b3.author = "Mark";
    //     // b3.num_copies = 75;
    //     // b3.save();
    //     // b2.num_copies = 39;
    //     // b2.save();

    //     // Model.reset();
    // }

    // @Test
    // public void SaveTest() {
    //     Book b = new Book();
    //     b.title = "FFDD";
    //     b.author = "Jack";
    //     b.num_copies = 4;
    //     b.save();

    //     Book b1 = new Book();
    //     b1.title = "COP";
    //     b1.author = "Penguin";
    //     b1.num_copies = 9;
    //     b1.save();

    //     // Book b2 = new Book();
    //     // b2.title = "CANE";
    //     // b2.author = "Parrot";
    //     // b2.num_copies = 7;
    //     // b2.save();

    //     Book b3 = Model.find(Book.class, 1);
    //     System.out.println(b3);

    //     Book b4 = Model.find(Book.class, 2);
    //     System.out.println(b4);

    //     // Book b5= Model.find(Book.class, 3);
    //     // System.out.println(b5);


    //     // b.destroy();

    //     //ab.save();
    //     //Model.reset();
    // }

    // @Test
    // public void SaveTest2() {
    //     Book b = new Book();
    //     b.title = "FFDD";
    //     b.author = "Jack";
    //     b.num_copies = 4;
    //     //b.save();

    //     Book b1 = new Book();
    //     b1.title = "COP";
    //     b1.author = "Penguin";
    //     b1.num_copies = 9;
    //     //b1.save();

    //     Book b2 = new Book();
    //     b2.title = "CONE";
    //     b2.author = "Polar Bear";
    //     b2.num_copies = 4;
    //     // b2.save();

    //     Book b3 = Model.find(Book.class, 1);
    //     System.out.println("B3 is: " + b3);

    //     Book b4 = Model.find(Book.class, 2);
    //     System.out.println("B4 is : " + b4);

    //     b4.title = "SALMON";
    //     b4.title = "Polar Bear";
    //     b4.num_copies = 5423535;
    //     // b4.save();

    //     b3.title = "Tiger";
    //     // b3.save();

    //     Book b5 = Model.find(Book.class, 4);
    //     Model.reset();
    // }

    // @Test
    // public void SaveTest2() {
    //     Book b = new Book();
    //     b.title = " ";
    //     b.author = "Jack";
    //     b.num_copies = 4;
    //     b.save();

    //     Book b1 = Model.find(Book.class, 1);
    //     //Model.reset();
    // }

    // @Test
    // public void SaveWithTrailingWhiteSpace() {
    //     Model.reset();
    //     Book b = new Book();
    //     b.title = " *(Q*(&))       ";
    //     b.author = "BOBBY";
    //     b.num_copies = 0;
    //     b.save();

    //     Book b1 = Model.find(Book.class, 1);
    //     Book b2 = new Book();
    //     b2.title = " *(Q*(&))       ";
    //     b2.author = "BOBBY";
    //     b2.num_copies = 0;
    //     b2.save();
    //     b2.title = b2.title.trim();
    //     b2.save();

    //     Book b3 = Model.find(Book.class, 2);
    //     // Integer i = Model.find(Integer.class, 2);
    //     // System.out.println("i: " + i);
    //     //Model.reset();
    // }

    @Test
    public void SaveWithNullField() {
        Model.reset();
        Book b = new Book();
        b.title = null;
        b.author = "BOBBY";
        b.num_copies = 0;
        b.save();

        Book b3 = Model.find(Book.class, 1);
        //Model.reset();
    }

    // @After
    // public void tearDown() throws Exception {
    // }
}