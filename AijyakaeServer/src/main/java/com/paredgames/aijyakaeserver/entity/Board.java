package com.paredgames.aijyakaeserver.entity;

import com.paredgames.aijyakaeserver.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false)
    private String negativePrompt;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String s3Url;

    @CreatedDate
    private LocalDateTime createdDateTime;


    public static Board toEntity(BoardDTO boardDTO){
        return Board.builder()
                .prompt(boardDTO.getPrompt())
                .negativePrompt(boardDTO.getNegativePrompt())
                .s3Url(boardDTO.getS3Url())
                .userName(boardDTO.getUserName())
                .build();
    }

}

