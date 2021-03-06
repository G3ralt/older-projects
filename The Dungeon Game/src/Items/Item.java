package Items;

public class Item {
    private final String name, description, type;

    public Item(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public String getType() {
        return type;
    }
}


