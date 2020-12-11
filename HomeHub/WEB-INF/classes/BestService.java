package pojo;

import java.io.*;

public class BestService {
    String serviceName;
    String rating;


    public BestService(String serviceName, String rating) {

        this.serviceName = serviceName;
        this.rating = rating;
    }


    public String getServicename() {
        return serviceName;
    }

    public String getRating() {
        return rating;
    }
}