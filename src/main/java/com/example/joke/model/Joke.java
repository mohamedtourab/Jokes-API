package com.example.joke.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table
public class Joke implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "insert_time")
    private Timestamp insertTime;

    @Column(nullable = false, name = "updateTime")
    private Timestamp updateTime;

    @Column(name = "joke_text")
    private String jokeText;

    public Joke(Timestamp insertTime, Timestamp updateTime, String jokeText) {
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.jokeText = jokeText;
    }

    public Joke() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke = (Joke) o;
        return jokeText.equalsIgnoreCase(joke.jokeText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jokeText);
    }
}
