package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Contact;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;

    // Créer un contact
    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    // Récupérer tous les contacts
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    // Récupérer un contact par ID
    public Contact getContactById(Integer id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact introuvable avec ID : " + id));
    }

    // Mettre à jour un contact
    public Contact updateContact(Integer id, Contact updatedContact) {
        Contact existing = contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact introuvable avec ID : " + id));

        existing.setContenu(updatedContact.getContenu());
        existing.setDateContact(updatedContact.getDateContact());
        existing.setHeureContact(updatedContact.getHeureContact());
        existing.setMotif(updatedContact.getMotif());

        return contactRepo.save(existing);
    }

    // Supprimer un contact
    public void deleteContact(Integer id) {
        if (!contactRepo.existsById(id)) {
            throw new RuntimeException("Contact introuvable avec ID : " + id);
        }
        contactRepo.deleteById(id);
    }
}
