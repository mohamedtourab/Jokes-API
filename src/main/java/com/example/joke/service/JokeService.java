package com.example.joke.service;

import com.example.joke.model.Joke;
import com.example.joke.model.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }
}
