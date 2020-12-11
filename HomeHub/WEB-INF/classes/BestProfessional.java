package pojo;

import java.io.*;

public class BestProfessional {
    String professionalName;
    String rating;


    public BestProfessional(String professionalName, String rating) {

        this.professionalName = professionalName;
        this.rating = rating;
    }


    public String getProfessionalname() {
        return professionalName;
    }

    public String getRating() {
        return rating;
    }
}