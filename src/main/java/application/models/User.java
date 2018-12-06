package application.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class User {
    private String _id = null;
    private String _rev = null;

    @NotNull
    private String username;

    @NotNull
    private String role;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    private String name;
    private String lastname;
    private String phone;
    private int cantidadActualBimestres;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCantidadActualBimestres() {
        return cantidadActualBimestres;
    }

    public void setCantidadActualBimestres(int cantidadActualBimestres) {
        this.cantidadActualBimestres = cantidadActualBimestres;
    }
}
