package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

//Mark this class as superclass so all other classes are going to inherit from this one
//This also means that we are not going to work with this POJO specifically we just need it for other Entities
@MappedSuperclass
public class Person extends BaseEntity {

    //Creating a column with a specific name
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;



    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
