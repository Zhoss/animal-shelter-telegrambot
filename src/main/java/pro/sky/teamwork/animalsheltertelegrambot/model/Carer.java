package pro.sky.teamwork.animalsheltertelegrambot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import java.util.Objects;
@Entity
@Table(name = "carers")
public class Carer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    private int age;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;
    @OneToOne
    @JoinColumn(name = "agreement_id", referencedColumnName = "id")
    private Agreement agreement;

    public Carer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carer carer = (Carer) o;
        return id == carer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Опекун: " +
                "id = " + id +
                ", ФИО = " + fullName +
                ", возраст = " + age +
                ", номер паспорта = " + passportNumber +
                ", контактный телефон = " + phoneNumber;
    }
}
