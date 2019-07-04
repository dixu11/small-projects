package swing.quiz;

public abstract class Question {

    protected int id;
    protected String discription;
    protected Category category;

    public Question(int id, String discription, Category category) {
        this.id = id;
        this.discription = discription;
        this.category = category;
    }

    public abstract boolean isAnswerCorrent(String userAnswer);



    public Category getCategory(){
        return category;
    }
}
