package myl.codeio.hello.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ToDo {
    @Id
    @GeneratedValue
    long id;
    String title;
    String description;
    Boolean isCompleted;


}
