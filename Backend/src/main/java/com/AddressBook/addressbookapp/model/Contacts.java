package com.AddressBook.addressbookapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {  // Class name changed to PascalCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;  // Changed from int to String
    private String address;
    private String city;
    private String state;
    private String zipcode;  // Changed from int to String
}
