package alekseev.pet.labs.Lab2.User;

import alekseev.pet.labs.Lab2.Annotations.MyAnnotation;

public class User {
    public String publicShowNameFirst(String arg) {
        return "This is public first method showName";
    }

    public String publicShowNameSecond(String arg) {
        return "This is public second method showName";
    }

    @MyAnnotation(val = 3)
    protected String protectedShowNameFirst(String arg) {
        return "This is protected first method showName";
    }

    protected String protectedShowNameSecond(String arg) {
        return "This is protected second method showName";
    }

    @MyAnnotation(val = 2)
    private String privateShowNameFirst(String arg) {
        return "This is private second method showName";
    }

    private String privateShowNameSecond(String arg) {
        return "This is private second method showName";
    }

    public User() {    }
}
