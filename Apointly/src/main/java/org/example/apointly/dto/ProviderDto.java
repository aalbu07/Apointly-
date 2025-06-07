package org.example.apointly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String otherContactDetails;
}
