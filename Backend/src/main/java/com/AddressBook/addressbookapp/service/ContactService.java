package com.AddressBook.addressbookapp.service;


import com.AddressBook.addressbookapp.dto.ContactsDTO;
import com.AddressBook.addressbookapp.model.Contacts;
import com.AddressBook.addressbookapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService{
    @Autowired
    private ContactRepository contactRepository;

    public List<Contacts> getAllContacts(){
        return contactRepository.findAll();
    }

    public Optional<Contacts> getContactById(Long id){
        return contactRepository.findById(id);
    }

    public String addContact(ContactsDTO dto){
        Contacts contact = new Contacts();

        contact.setFullName(dto.getFullName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZipcode(dto.getZipcode());
        contactRepository.save(contact);
        return "Contact Added successfully";
    }

    public ResponseEntity<?> updateContact(Long id, ContactsDTO dto){
        Optional<Contacts> Contact = contactRepository.findById(id);

        if(Contact.isPresent()){
            Contacts existingContact = Contact.get();
            existingContact.setFullName(dto.getFullName());
            existingContact.setPhoneNumber(dto.getPhoneNumber());
            existingContact.setAddress(dto.getAddress());
            existingContact.setCity(dto.getCity());
            existingContact.setState(dto.getState());
            existingContact.setZipcode(dto.getZipcode());
            contactRepository.save(existingContact);
            return ResponseEntity.ok("Contact updated successfully" );
        }

        return ResponseEntity.ok("Contact not found with id" + id);
    }

    public void deleteContact(Long id) {
        Contacts contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        contactRepository.delete(contact);
    }


}
