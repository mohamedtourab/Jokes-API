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
    private JokeService jokeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Joke>> getAllJokes(@RequestParam(value = "Page", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(value = "QuestionPerPage", defaultValue = "5") Integer QuestionPerPage) {
        try {
            int pageNumber = pageNo - 1;
            int QuestionsPerPage = QuestionPerPage;
            List<Joke> jokes = jokeService.getAllJokes();
            if (pageNumber < 0 || QuestionsPerPage < 1) {
                logger.error("Invalid Arguments");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (QuestionsPerPage > jokes.size()) {
                return new ResponseEntity<>(jokes, HttpStatus.OK);
            }
            int start = pageNumber * QuestionsPerPage;
            int end = start + QuestionsPerPage;
            List<Joke> jokePage = jokes.subList(start, end);
            if (jokePage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(jokePage, HttpStatus.OK);

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.error("Failed to parse parameters\n" + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Invalid page\n" + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Joke> createJoke(@RequestBody Joke joke) {
        if (joke.getJokeText().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jokeService.saveJoke(joke), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{jokeID}")
    public ResponseEntity<Joke> updateJoke(@RequestBody Joke joke, @PathVariable Integer jokeID) {
        if (joke.getJokeText().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jokeService.updateJokes(joke, jokeID), HttpStatus.OK);
    }

}
