package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

import javax.swing.text.html.HTML;

public class ViewTest {

    // @Test
    // public void empty() {
    //     assertThat(View.empty().toString(), isEmptyString());
    // }
    // @Test
    // public void HtmlTest1() {
    //     try
    //     {
            
    //         Html test = View.empty();
    //         Html test1 = new Html("Tune");
    //         test = test.seq(test1);

    //         System.err.println("test: " + test);
            
    //     }
    //     catch (Exception e)
    //     {
    //         System.err.println("Exception: " + e);
    //     }
    // }

    @Test
    public void HtmlTest2() {
        try
        {
            Html a = View.empty();
            Html b = new Html("b");
            Html c = new Html("c");
            Html test = a.p(b).p(c);
            
            //System.err.println("test: " + test);
            
        }
        catch (Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }
}