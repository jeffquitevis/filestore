import java.io.Serializable;

/**
 * Created by jeff on 4/27/2016.
 */
public class Person implements Serializable {

    String firstName;
    String lastName;
    int Id;


        public Person(int Id,String firstName,String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
            this.Id = Id;
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
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Id=" + Id +
                '}';
    }
}
