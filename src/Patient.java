public class Patient extends Person {
    private String id;

    public Patient(String id) {
        super();
        this.id = id;
    }

    public Patient(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
