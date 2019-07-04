package streams.managing_cars_collection.exceptions;

public class MyException extends RuntimeException {
    private String exceptionMessage;

    public MyException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return "Wystąpił błąd wprowadzania danych: " +  exceptionMessage;
    }
}

