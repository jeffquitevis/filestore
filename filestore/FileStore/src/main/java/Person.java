import java.io.Serializable;

/**
 * Created by jeff on 4/27/2016.
 */
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private int id;
    private boolean delete;


        public Person( boolean delete,int id,String firstName,String lastName){

            this.delete = delete;
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
