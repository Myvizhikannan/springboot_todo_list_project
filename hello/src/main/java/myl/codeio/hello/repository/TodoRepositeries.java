package myl.codeio.hello.repository;


import myl.codeio.hello.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepositeries extends JpaRepository<ToDo, Long> {

}
