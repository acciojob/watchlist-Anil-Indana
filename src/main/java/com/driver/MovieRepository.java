package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,List<String>> movieDirectorHashMap = new HashMap<>();
    private HashMap<String,Director> directorHashMap = new HashMap<>();
    private HashMap<String,Movie> movieHashMap = new HashMap<>();
    public String addMovie(Movie movie){
        String key = movie.getName();
        movieHashMap.put(key,movie);

        return "Movie Added";
    }
    public String  addDirector(Director director){
        String key = director.getName();
        directorHashMap.put(key,director);

        return "Director Added";
    }
    public String   addMovieDirectorPair(String movieName,String directorName){
        List<String> list;
        if(movieHashMap.containsKey(directorName)){
            list = movieDirectorHashMap.get(directorName);
        }
        else{
            list = new ArrayList<>();
        }
        list.add(movieName);
        movieDirectorHashMap.put(directorName,list);
        return "Movie and Director Paired";
    }
    public String deleteDirectorByName(String directorName){
        directorHashMap.remove(directorName);
        List<String> movies = movieDirectorHashMap.get(directorName);
        movieDirectorHashMap.remove(directorName);
        for(String movieName : movies){
            if(movieHashMap.containsKey(movieName)) movieHashMap.remove(movieName);
        }
        return "Deleted by Director name";
    }
    public String deleteAllDirectors(){
        for(String directorName : directorHashMap.keySet()){
            directorHashMap.remove(directorName);
            List<String> movies = movieDirectorHashMap.get(directorName);
            movieDirectorHashMap.remove(directorName);
            for(String movieName : movies){
                if(movieHashMap.containsKey(movieName)) movieHashMap.remove(movieName);
            }
        }
        return "Deleted";
    }
    public List<String> getMoviesByDirectorName(String directorName){
        if(movieDirectorHashMap.containsKey(directorName)) return movieDirectorHashMap.get(directorName);
        return null;
    }
    public List<String> findAllMovies(){
        List<String> movies = new ArrayList<>();
        for(String movie : movieHashMap.keySet()){
            movies.add(movie);
        }
        return movies;
    }

    public Movie getMovieByName(String name){
        if(movieHashMap.containsKey(name)) return movieHashMap.get(name);
        return null;
    }

    public Director getDirectorByName(String name){
        if(directorHashMap.containsKey(name)) return directorHashMap.get(name);
        return null;
    }
}

