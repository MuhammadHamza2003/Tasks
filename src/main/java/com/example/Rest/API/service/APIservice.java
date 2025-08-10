package com.example.Rest.API.service;

import com.example.Rest.API.entity.Entity;
import com.example.Rest.API.repository.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class APIservice
{
    @Autowired
    private APIRepository repo;

    public List<Entity> getAll()
    {
        return repo.findAll();

    }

    public void saveEntity(Entity entity)
    {
        repo.save(entity);
    }

    public Entity findById(String id)
    {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(String id)
    {
        repo.deleteById(id);
    }


}
