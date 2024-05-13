package com.paredgames.aijyakaeserver.dto;

import com.paredgames.aijyakaeserver.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long id;
    private String prompt;
    private String negativePrompt;
    private String userName;
    private String s3Url;
    private LocalDateTime createdDateTime;

    public static BoardDTO toDTO(Board board){
        return BoardDTO.builder()
                .id(board.getId())
                .prompt(board.getPrompt())
                .negativePrompt(board.getNegativePrompt())
                .userName(board.getUserName())
                .s3Url(board.getS3Url())
                .createdDateTime(board.getCreatedDateTime())
                .build();
    }
}
