package db.domain;

public class Good {
    int id;
    String NAME;
    int price;
    String description;
    String brand;
    String cpu_brand;
    String cpu_type;
    String memory_capacity;
    String hd_capacity;
    String card_model;
    String displaysize;
    String image;

    public Good(int id, String NAME, int price, String description, String brand, String cpu_brand, String cpu_type, String memory_capacity, String hd_capacity, String card_model, String displaysize, String image) {
        this.id = id;
        this.NAME = NAME;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.cpu_brand = cpu_brand;
        this.cpu_type = cpu_type;
        this.memory_capacity = memory_capacity;
        this.hd_capacity = hd_capacity;
        this.card_model = card_model;
        this.displaysize = displaysize;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu_brand() {
        return cpu_brand;
    }

    public void setCpu_brand(String cpu_brand) {
        this.cpu_brand = cpu_brand;
    }

    public String getCpu_type() {
        return cpu_type;
    }

    public void setCpu_type(String cpu_type) {
        this.cpu_type = cpu_type;
    }

    public String getMemory_capacity() {
        return memory_capacity;
    }

    public void setMemory_capacity(String memory_capacity) {
        this.memory_capacity = memory_capacity;
    }

    public String getHd_capacity() {
        return hd_capacity;
    }

    public void setHd_capacity(String hd_capacity) {
        this.hd_capacity = hd_capacity;
    }

    public String getCard_model() {
        return card_model;
    }

    public void setCard_model(String card_model) {
        this.card_model = card_model;
    }

    public String getDisplaysize() {
        return displaysize;
    }

    public void setDisplaysize(String displaysize) {
        this.displaysize = displaysize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
