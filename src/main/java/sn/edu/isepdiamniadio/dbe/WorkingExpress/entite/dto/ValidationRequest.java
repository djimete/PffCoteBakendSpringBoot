package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationRequest {
    private String email;
    private String code;
}
