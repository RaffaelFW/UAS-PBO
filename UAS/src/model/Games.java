package model;

public class Games {
 private  int id;
 private  String name;
 private  String genre;
 private  int price;

    public Games(int id, String name, String genre, int price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getPrice() {
        return price;
    }
}
