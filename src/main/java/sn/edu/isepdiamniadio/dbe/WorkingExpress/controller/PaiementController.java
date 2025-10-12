package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.PaiementDto;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.mapper.PaiementMapper;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ClientRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.PaiementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Gestion des paiements", description = "API pour gérer les paiements (création, consultation, modification, suppression)")
public class PaiementController {

    @Autowired
    private PaiementService service;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @Operation(summary = "Récupérer tous les paiements")
    public ResponseEntity<List<PaiementDto>> getAll() {
        List<Paiement> paiements = service.findAll();
        List<PaiementDto> dtos = paiements.stream()
                .map(PaiementMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un paiement par ID")
    public ResponseEntity<PaiementDto> getOne(@PathVariable Integer id) {
        Paiement paiement = service.findById(id);
        return ResponseEntity.ok(PaiementMapper.toDto(paiement));
    }

    @PostMapping
    @Operation(summary = "Créer un paiement")
    public ResponseEntity<PaiementDto> create(@RequestBody PaiementDto dto) {
        System.out.println("#### createPaiement");
        System.out.println("paiementDto=="+dto);
        if (dto.getModePaiement() == null || dto.getMontant() <= 0 || dto.getIdClient() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Client client = clientRepository.findById(dto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Paiement paiement = PaiementMapper.toEntity(dto, client);
        Paiement saved = service.save(paiement);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PaiementMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<PaiementDto> update(@PathVariable Integer id, @RequestBody PaiementDto dto) {
        try {
            Client client = clientRepository.findById(dto.getIdClient())
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));

            Paiement paiement = PaiementMapper.toEntity(dto, client);
            Paiement updated = service.update(id, paiement);
            return ResponseEntity.ok(PaiementMapper.toDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
