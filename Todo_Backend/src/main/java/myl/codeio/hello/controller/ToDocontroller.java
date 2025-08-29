package myl.codeio.hello.controller;

import myl.codeio.hello.service.TodoService;
import myl.codeio.hello.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDocontroller {
    @Autowired
    private TodoService todoservice;
//    @GetMapping("/list")
//    String list(){
//        todoservice.printTodos();
//        return "list";
//    }
    @GetMapping("/createid")
    String getIdParem(@RequestParam("todoId") long id ){
        return "todo with id"+id;

    }
    @GetMapping("/create")
    String getIdMultiParem(@RequestParam String userName,@RequestParam String Password ){
        return "Username="+userName+"password="+Password;

    }
    @PostMapping("/create")
    ResponseEntity<ToDo> setUser(@RequestBody ToDo todo)
    {
        return new ResponseEntity<>(todoservice.createtodo(todo), HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<ToDo>> getAllTodo(){
        return new ResponseEntity<>(todoservice.getAllTodos(), HttpStatus.OK);
    }
    @GetMapping("/page")
    ResponseEntity<Page<ToDo>> getAllTodoPages(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(todoservice.getAllPages(page,size),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<ToDo> getId(@PathVariable long id ){
        try {
            ToDo getTodo=todoservice.getTodoById(id);
            return new ResponseEntity<>(getTodo,HttpStatus.OK);
        }
        catch (RuntimeException exception){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }


    }
    @PostMapping
    String postId(@RequestParam long id ){
        return "post todo id"+1;
    }
    @PutMapping
    ResponseEntity<ToDo> putId(@RequestBody ToDo toDo){
        return new ResponseEntity<>(todoservice.update(toDo),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    void DeleteId(@PathVariable long id )
    {
        todoservice.deleteById(id);
    }
    @DeleteMapping
    void DeleteAll(ToDo toDo)
    {
        todoservice.deleteALl(toDo);
    }
}
