package es.uji.al435137.exceptions;

public class LikedItemNotFoundException extends RuntimeException {
    private String itemName;

    public LikedItemNotFoundException(String itemName) {
        super("Liked item not found: " + itemName);
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}