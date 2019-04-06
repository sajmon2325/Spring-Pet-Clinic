package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//We don't use @Builder when we have @AllArgsConstructor. Because they are doing basically the same thing and Java don't allow multiple same constructors
//@Builder
//Mark this class as superclass so all other classes are going to inherit from this one
//This also means that we are not going to work with this POJO specifically we just need it for other Entities
@MappedSuperclass
public class Person extends BaseEntity {

    //Creating a column with a specific name
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;



}
