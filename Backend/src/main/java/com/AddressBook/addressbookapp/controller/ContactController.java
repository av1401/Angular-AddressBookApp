package com.AddressBook.addressbookapp.controller;


import com.AddressBook.addressbookapp.dto.ContactsDTO;
import com.AddressBook.addressbookapp.model.Contacts;
import com.AddressBook.addressbookapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular to access this API
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/viewAll")
    public List<Contacts> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Optional<Contacts> contact = contactService.getContactById(id);

        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get()); // Return the contact if found
        } else {
            return ResponseEntity.ok("No contact found with ID: " + id); // Return a simple message
        }
    }

    @PostMapping("/add")
    public String addContact(@RequestBody ContactsDTO dto){
        return contactService.addContact(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody ContactsDTO dto){
        return contactService.updateContact(id,dto);
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "Contact deleted successfully";
    }

}
