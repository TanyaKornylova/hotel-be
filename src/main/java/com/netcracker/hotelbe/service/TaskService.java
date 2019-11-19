package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Task;
import com.netcracker.hotelbe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(Long id){
        return taskRepository.findById(id).get();
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }


}
