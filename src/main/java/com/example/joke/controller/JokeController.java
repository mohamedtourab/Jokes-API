package com.example.joke.controller;

import com.example.joke.model.Joke;
import com.example.joke.service.JokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joke")
public class JokeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JokeService jokeServiceImpl;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Joke>> getAllJokes(@RequestParam(value = "Page", defaultValue = "0") Integer pageNo,
                                                  @RequestParam(value = "JokesPerPage", defaultValue = "5") Integer jokesPerPage) {
        if (pageNo < 0 || jokesPerPage < 1) {
            logger.error("Invalid Arguments");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Joke> jokePage = jokeServiceImpl.getAllJokes(pageNo, jokesPerPage);
        if (jokePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jokePage, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Joke> createJoke(@RequestBody Joke joke) {
        if (joke.getJokeText().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jokeServiceImpl.saveJoke(joke), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{jokeID}")
    public ResponseEntity<Joke> updateJoke(@RequestBody Joke joke, @PathVariable("jokeID") Integer jokeID) {
        if (jokeID < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (joke.getJokeText().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Joke updatedJoke = jokeServiceImpl.updateJoke(joke, jokeID);
        if (updatedJoke == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedJoke, HttpStatus.OK);
    }

    @GetMapping(path = "/{jokeID}")
    public ResponseEntity<Joke> getSingleJoke(@PathVariable("jokeID") Integer jokeID) {
        if (jokeID < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Joke joke = jokeServiceImpl.getJoke(jokeID).orElse(null);
        if (joke == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(joke, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{jokeID}")
    public ResponseEntity<Joke> deleteJoke(@PathVariable("jokeID") Integer jokeID) {
        if (jokeID < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Joke joke = jokeServiceImpl.deleteJoke(jokeID);
        if (joke == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(joke, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Joke>> searchForJoke(@RequestParam("searchString") String searchString) {
        if (searchString.isBlank() || searchString.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Joke> filteredJokes = jokeServiceImpl.filterJokes(searchString);
        if (filteredJokes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(filteredJokes, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Joke> getRandomJoke() {
        Joke joke = jokeServiceImpl.getRandomJoke();
        if (joke == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(joke, HttpStatus.OK);
    }


}
