package com.example.joke.service;

import com.example.joke.model.Joke;
import com.example.joke.model.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public Optional<Joke> getJoke(Integer id) {
        return jokeRepository.findById(id);
    }

    public Joke deleteJoke(Integer id) {
        Joke joke = getJoke(id).orElse(null);
        if (joke == null)
            return null;
        jokeRepository.delete(joke);
        return joke;
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Joke saveJoke(Joke joke) {
        joke.setInsertTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(joke);
        return joke;
    }

    public Joke updateJoke(Joke joke, Integer jokeID) {
        Joke oldJoke = jokeRepository.getOne(jokeID);
        oldJoke.setJokeText(joke.getJokeText());
        joke.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(oldJoke);
        return joke;
    }

    public List<Joke> filterJokes(String filterString) {
        List<Joke> jokes = getAllJokes();
        return jokes.stream().filter(joke -> joke.getJokeText().contains(filterString)).collect(Collectors.toList());
    }
}
