package br.com.juliocastrodev.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        // Pegando o atributo idUser setado na filterTaskAuth
        var userId = (UUID)request.getAttribute("idUser");
        // Passando o ID do usuário logado para registrar a tarefa
        taskModel.setIdUser(userId);
        var task = this.taskRepository.save(taskModel);
        return task;
    }

}
