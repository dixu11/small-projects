package swing.quiz;

public enum Category {
    MUSIC("Music"),
    ECONOMY("Economy"),
    GENERAL_KNOWLEDGE("General knowledge"),
    COUNTRIES_CAPITALS("Countries capitals");

    private String name;

    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
