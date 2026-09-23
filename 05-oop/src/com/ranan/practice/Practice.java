package com.ranan.practice;

public class Practice {
    public static void main(String[] args) {
        MovieSystem sys = new MovieSystem(5);
        sys.addMovie(10101,"Oppenheimer",100);
        sys.addMovie(10102,"PleasantGoatMovie",35.50);
        sys.addMovie(10103,"Odyssey",67.50);

        Movie movie1 = new Movie(10201,"Jiao Yulu",12.7);
        sys.addMovie(movie1);

        Movie movie2 = new Movie(10202,"Titanic",46.8);
        sys.addMovie(movie2);

        Movie movie3 = new Movie(10202,"Titanic",46.8);
        sys.addMovie(movie3);

        Movie movie4 = new Movie(10202,"Titanic",46.8);
        sys.addMovie(movie4);

        sys.renewSize(6);
        sys.addMovie(10301,"Niu Lai",10.50);
        sys.renewSize(3);

        System.out.println("======================================");
        sys.getAllMovies();
        System.out.println("======================================");
        sys.findById(10101);
        sys.findById(10104);
    }
}
