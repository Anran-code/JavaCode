package com.ranan.practice;

public class MovieSystem {
    private Movie[] movies;
    int size;
    int index = 0;

    public MovieSystem(int _size){
        size = _size;
        movies = new Movie[size];
    }

    public void addMovie(Movie movie){
        if(index >= size){
            System.out.println("已超出现有System容量 ，无法加入新的电影");
        } else {
            System.out.println(movie.getName() + "加入成功！");
            movies[index++] = movie;
        }
    }

    public void addMovie(int _id , String _name, double _price){
        Movie movie = new Movie(_id, _name, _price);
        addMovie(movie);
    }

    public void getAllMovies(){
        for(int i = 0;i < Math.min(index,size);++i){
            System.out.printf("%-8d%-20s%-10.2f\n",
                    movies[i].getId(),movies[i].getName(),movies[i].getPrice());
        }
    }

    public void renewSize(int new_size){
        if(new_size <= size){
            System.out.println("无效的修改");
            return;
        }
        Movie[] new_movies = new Movie[new_size];
        for(int i = 0;i < Math.min(index,size);++i){
            new_movies[i] = movies[i];
        }

        movies = new_movies;

        System.out.println("修改成功：size " + size + " -> " + new_size);
        size = new_size;
    }

    public void findById(int _id){
        for(int i = 0;i < Math.min(index,size);++i){
            if(_id == movies[i].getId()){
                System.out.printf("查找到：\n%-8d%-20s%-10.2f\n",
                        movies[i].getId(),movies[i].getName(),movies[i].getPrice());
                return;
            }
            else{
                System.out.println("未查找到id：" + _id);
                return;
            }
        }
    }
}
