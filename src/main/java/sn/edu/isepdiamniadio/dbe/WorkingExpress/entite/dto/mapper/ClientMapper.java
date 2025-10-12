package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.mapper;

import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.ClientDto;

public class ClientMapper {

    // Convertir Entité → DTO
    public static ClientDto toDto(Client client) {
        if (client == null) return null;

        return ClientDto.builder()
                .idClient(client.getIdClient())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .email(client.getAdresse())
                .telephone(client.getTelephone())
                .build();
    }

    // Convertir DTO → Entité
    public static Client toEntity(ClientDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setIdClient(dto.getIdClient());
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());

        return client;
    }
}
