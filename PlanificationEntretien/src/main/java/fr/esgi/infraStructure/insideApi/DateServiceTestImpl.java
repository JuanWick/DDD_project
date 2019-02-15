package fr.esgi.infraStructure.insideApi;

import java.util.Date;

public class DateServiceTestImpl implements DateService {
    @Override
    public Date today() {
       return new Date();
    }
}
