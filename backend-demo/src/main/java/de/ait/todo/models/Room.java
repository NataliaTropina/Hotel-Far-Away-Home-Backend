package de.ait.todo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room {

    public enum TypeOfRoom {
        STANDART, SUITE, DUPLEX;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String number;
    private boolean isBooked;

    @Enumerated(value = EnumType.STRING)
    private TypeOfRoom typeOfRoom;

    private double price;

    @ManyToMany(mappedBy = "rooms")
    private List<Booking> bookings;





}
