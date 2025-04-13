package es.uji.al435137.algorithms;

public class LikedItemNotFoundException extends RuntimeException {
    private String itemName;

    public LikedItemNotFoundException(String itemName) {
        super("Elemento marcado como favorito no encontrado: " + itemName);
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}