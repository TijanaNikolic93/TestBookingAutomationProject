package model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class ReservationUser {

    private String firstName;
    private String lastName;
    private String email;
    private String emailConfirm;

    public ReservationUser(String firstName, String lastName, String email, String emailConfirm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailConfirm = emailConfirm;
    }

}
