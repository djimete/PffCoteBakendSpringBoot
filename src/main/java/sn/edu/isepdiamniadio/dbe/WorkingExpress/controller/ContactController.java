package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Contact;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Créer un contact
    @PostMapping("/create")
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    // Récupérer tous les contacts
    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // Récupérer un contact par ID
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Integer id) {
        return contactService.getContactById(id);
    }

    // Mettre à jour un contact
    @PutMapping("/update/{id}")
    public Contact updateContact(@PathVariable Integer id, @RequestBody Contact updatedContact) {
        return contactService.updateContact(id, updatedContact);
    }

    // Supprimer un contact
    @DeleteMapping("/delete/{id}")
    public String deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return "Contact supprimé avec succès";
    }
}
