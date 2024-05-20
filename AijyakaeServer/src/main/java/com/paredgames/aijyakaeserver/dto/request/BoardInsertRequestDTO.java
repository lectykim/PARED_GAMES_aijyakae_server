package com.paredgames.aijyakaeserver.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardInsertRequestDTO {
    private String id;
    private String prompt;
    private String negativePrompt;
    private String userName;
    private String imageBase64;
}
