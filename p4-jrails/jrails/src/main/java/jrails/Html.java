package jrails;

import java.util.*;

public class Html {
    private String text;

    public Html(String text)
    {
        this.text = text;
    }
    public String toString() {
        return this.text;
    }

    public Html seq(Html h) {
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html br() {
        this.text = this.text + "<br/>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html t(Object o) {
        // Use o.toString() to get the text for this
        return new Html(o.toString());
    }

    public Html p(Html child) {
        this.text = this.text + "<p>" + child.toString() + "</p>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html div(Html child) {
        this.text = this.text + "<div>" + child.toString() + "</div>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html strong(Html child) {
        this.text = this.text + "<strong>" + child.toString() + "</strong>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html h1(Html child) {
        this.text = this.text + "<h1>" + child.toString() + "</h1>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html tr(Html child) {
        this.text = this.text + "<tr>" + child.toString() + "</tr>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html th(Html child) {
        this.text = this.text + "<th>" + child.toString() + "</th>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html td(Html child) {
        this.text = this.text + "<td>" + child.toString() + "</td>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html table(Html child) {
        this.text = this.text + "<table>" + child.toString() + "</table>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html thead(Html child) {
        this.text = this.text + "<thead>" + child.toString() + "</thead>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html tbody(Html child) {
        this.text = this.text + "<tbody>" + child.toString() + "</tbody>";
        return new Html(this.text);
        //throw new UnsupportedOperationException();
    }

    public Html textarea(String name, Html child) {
        throw new UnsupportedOperationException();
    }

    public Html link_to(String text, String url) {
        throw new UnsupportedOperationException();
    }

    public Html form(String action, Html child) {
        throw new UnsupportedOperationException();
    }

    public Html submit(String value) {
        throw new UnsupportedOperationException();
    }
}