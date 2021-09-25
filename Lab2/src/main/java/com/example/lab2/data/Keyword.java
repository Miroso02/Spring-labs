package com.example.lab2.data;

import java.util.Objects;

public class Keyword {
    public String getWord() {
        return word;
    }

    final String word;

    public Keyword(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return Objects.equals(word, keyword.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
