package com.AddressBook.addressbookapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsDTO {
    private String fullName;
    private String phoneNumber;  // Changed from int to String
    private String address;
    private String city;
    private String state;
    private String zipcode;  // Changed from int to String
}
