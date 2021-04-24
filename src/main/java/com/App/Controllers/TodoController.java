package com.App.Controllers;

import com.App.Entity.Todo;
import com.App.Entity.User;
import com.App.repository.todoRepository;
import com.App.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    userRepository userRepository;
    @Autowired
    todoRepository todoRepository;
    @Autowired
    EntityManager entityManager;

    @GetMapping("/pagination/{page}")
    public String pagination(@PathVariable int page, Model model, Principal principal) {
        Pageable paging = PageRequest.of(page, 10);
        Page<Todo> byUser = this.todoRepository.findByUser(this.userRepository.findByName(principal.getName()), paging);
        System.out.println(byUser.getContent());
        model.addAttribute("todos", byUser.getContent());
        model.addAttribute("pages", byUser.getTotalPages());
        return "dashboard.html";
    }

    @GetMapping("/get-todos/{user_id}")
    public String getAllTodo(@PathVariable int user_id, Model model, Principal principal) {
        Pageable paging = PageRequest.of(0, 10);
        Page<Todo> byUser = this.todoRepository.findByUser(this.userRepository.findById(user_id).get(), paging);
        model.addAttribute("todos", byUser.getContent());
        model.addAttribute("pages", byUser.getTotalPages());
        return "dashboard.html";
    }

    @GetMapping("/today")
    public String todayTodos(Principal principal,Model model) {
        Pageable paging = PageRequest.of(0, 40);
        User user = this.userRepository.findByName(principal.getName());
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd ");
        LocalDate ld= LocalDate.now();
        String to=ld+" 00:00:00";
        String from=ld+" 23:59:00";
       List<Todo> todoList= this.todoRepository.findByCreatedAtBetween(to,from,this.userRepository.findByName(principal.getName()).getId());
        model.addAttribute("todos", todoList);
        model.addAttribute("pages",null);
        return "dashboard.html";
    }
    @GetMapping("/priority-wise")
    public String priorityFilter(Principal principal,Model model) {
        List<Todo> todoList= this.todoRepository.findByPriority(this.userRepository.findByName(principal.getName()).getId());
        model.addAttribute("todos", todoList);
        model.addAttribute("pages",null);
        return "dashboard.html";
    }

    @GetMapping("/delete/{id}")
    public String todayTodos(@PathVariable int id) {
        this.todoRepository.delete(this.todoRepository.findById(id).get());
        return "redirect:/users/dashboad";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTodo(Todo todo, Principal principal) {
        User user = this.userRepository.findByName(principal.getName());
        todo.setUser(user);
        todo.setCreated_at(new Date());
        this.todoRepository.save(todo);
        return "redirect:/todos/get-todos/" + user.getId();
    }
}
