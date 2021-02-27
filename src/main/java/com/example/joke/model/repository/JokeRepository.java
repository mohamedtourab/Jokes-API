package com.example.joke.model.repository;

import com.example.joke.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Integer> {

}
