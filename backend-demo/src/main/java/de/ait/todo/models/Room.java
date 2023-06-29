package de.ait.todo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Enumerated(value = EnumType.STRING)
    private TypeOfRoom typeOfRoom;

    private double price;


}
