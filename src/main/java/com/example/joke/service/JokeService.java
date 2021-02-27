package com.example.joke.service;

import com.example.joke.model.Joke;
import com.example.joke.model.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Joke saveJoke(Joke joke) {
        joke.setInsertTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(joke);
        return joke;
    }

    public Joke updateJokes(Joke joke, Integer jokeID) {
        Joke oldJoke = jokeRepository.getOne(jokeID);
        oldJoke.setJokeText(joke.getJokeText());
        joke.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(joke);
        return joke;
    }
}
