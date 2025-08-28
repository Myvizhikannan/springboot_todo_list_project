package myl.codeio.hello.service;
import myl.codeio.hello.model.ToDo;
import myl.codeio.hello.repository.TodoRepositeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TodoService {
    @Autowired
    private TodoRepositeries todoRepositeries;
    public ToDo createtodo(ToDo todo){
        return todoRepositeries.save(todo);
    }
    public ToDo getTodoById(long id){
        return todoRepositeries.findById(id).orElseThrow(()->new RuntimeException("ToDo not found"));
    }
    public List<ToDo> getAllTodos(){
        return todoRepositeries.findAll();
    }
    public ToDo update(ToDo todo){
        return todoRepositeries.save(todo);
    }
    public void deleteById(Long id){
        todoRepositeries.deleteById(id);
    }
    public void deleteALl(ToDo todo){
        todoRepositeries.delete(todo);
    }
    public Page<ToDo> getAllPages(int page, int size){
        PageRequest pageable= PageRequest.of(page,size);
        return todoRepositeries.findAll(pageable);
    }
}
