package org.example.persistencedemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "USERS")
// Disable the generation of INSERT and UPDATE SQL statements on startup, instead generate these statements
// dynamically, when needed, uses less memory, startup times are faster, and could improve performance
// on UPDATE operations on tables with a lot of columns.
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
// Immutable entities avoids hibernate dirty checking.
@org.hibernate.annotations.Immutable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR")
    private Long id;

    private String username;

    private LocalDate registrationDate;

    private String email;

    private int level;

    private boolean active;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, LocalDate registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return level == user.level
                && active == user.active
                && Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(registrationDate, user.registrationDate)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, registrationDate, email, level, active);
    }
}
