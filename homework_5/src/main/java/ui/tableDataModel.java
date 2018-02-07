package ui;

import javafx.beans.property.SimpleStringProperty;
import vo.Program;

public final class tableDataModel {
    private final SimpleStringProperty country=new SimpleStringProperty();
    private final SimpleStringProperty university=new SimpleStringProperty();
    private final SimpleStringProperty program_name=new SimpleStringProperty();
    private final SimpleStringProperty degree=new SimpleStringProperty();
    private final SimpleStringProperty school=new SimpleStringProperty();

    private Program pro;

    public tableDataModel(Program p){
        country.set(p.getCountry());
        university.set(p.getUniversity());
        program_name.set(p.getProgram_name());
        degree.set(p.getDegree());
        school.set(p.getSchool());
        pro=p;
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public SimpleStringProperty degreeProperty() {
        return degree;
    }

    public SimpleStringProperty program_nameProperty() {
        return program_name;
    }

    public SimpleStringProperty schoolProperty() {
        return school;
    }

    public SimpleStringProperty universityProperty() {
        return university;
    }

    public Program getPro() {
        return pro;
    }
}
