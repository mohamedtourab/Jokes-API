package com.example.joke.service;

import com.example.joke.model.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeService {
    /**
     * This function gets a single joke from the database
     *
     * @param id ID of the request joke
     * @return Optional that might contains the joke if it exist in the database
     */
    Optional<Joke> getJoke(Integer id);

    /**
     * This functions gets all the jokes from the h2 database
     *
     * @return List of Joke object
     */
    List<Joke> getAllJokes(int pageNo, int jokesPerPage);

    /**
     * This function save a new joke to the database, it save the timestamp when joke is created
     *
     * @param joke A new joke that need to be saved
     * @return joke after persistence
     */
    Joke saveJoke(Joke joke);

    /**
     * This function deletes a certain joke from the database
     *
     * @param id Joke ID
     * @return The delete joke
     */
    Joke deleteJoke(Integer id);

    /**
     * This functions modify a certain joke. It also updates the update_time variable of the joke
     *
     * @param joke   a joke object that contains the new updates
     * @param jokeID the joke ID that need to be updated
     * @return the persisted joke after updating its fields
     */
    Joke updateJoke(Joke joke, Integer jokeID);

    /**
     * This functions gets all the jokes that have filterString in their text.
     * This functions is not case sensitive
     *
     * @param filterString The substring to be checked against the jokes
     * @return List of jokes that have filterString in its text
     */
    List<Joke> filterJokes(String filterString);

    /**
     * This function returns a random joke
     *
     * @return Random Joke
     */
    Joke getRandomJoke();
}
