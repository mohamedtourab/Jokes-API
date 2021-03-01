package com.example.joke.service;

import com.example.joke.model.Joke;
import com.example.joke.model.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    @Override
    public Optional<Joke> getJoke(Integer id) {
        return jokeRepository.findById(id);
    }

    @Override
    public Joke deleteJoke(Integer id) {
        Joke joke = getJoke(id).orElse(null);
        if (joke == null)
            return null;
        jokeRepository.delete(joke);
        return joke;
    }

    @Override
    public List<Joke> getAllJokes(int pageNo, int jokesPerPage) {
        Pageable page = PageRequest.of(pageNo, jokesPerPage);
        return jokeRepository.findAll(page).toList();
    }

    @Override
    public Joke saveJoke(Joke joke) {
        joke.setInsertTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(joke);
        return joke;
    }

    @Override
    public Joke updateJoke(Joke joke, Integer jokeID) {
        Joke oldJoke = jokeRepository.findById(jokeID).orElse(null);
        if (oldJoke == null)
            return null;
        oldJoke.setJokeText(joke.getJokeText());
        oldJoke.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        jokeRepository.save(oldJoke);
        return oldJoke;
    }

    @Override
    public List<Joke> filterJokes(String filterString) {
        List<Joke> jokes = jokeRepository.findAll();
        return jokes.stream().filter(joke -> joke.getJokeText().toLowerCase().contains(filterString.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Joke getRandomJoke() {
        List<Joke> jokes = jokeRepository.findAll();
        if (jokes.isEmpty())
            return null;
        return jokes.get(new Random().nextInt(jokes.size()));
    }
}
