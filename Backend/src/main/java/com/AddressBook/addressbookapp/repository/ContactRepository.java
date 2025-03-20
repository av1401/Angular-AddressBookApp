package com.AddressBook.addressbookapp.repository;

import com.AddressBook.addressbookapp.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contacts, Long> {
}
