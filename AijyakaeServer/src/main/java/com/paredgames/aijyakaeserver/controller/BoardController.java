package com.paredgames.aijyakaeserver.controller;


import com.paredgames.aijyakaeserver.dto.BoardDTO;
import com.paredgames.aijyakaeserver.dto.BoardInsertRequestDTO;
import com.paredgames.aijyakaeserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;
    @PostMapping("/api/v1/upload-img")
    public ResponseEntity<BoardDTO> uploadImg(BoardInsertRequestDTO boardInsertRequestDTO){
        return boardService.uploadImg(boardInsertRequestDTO);
    }

    @GetMapping("/api/v1/get-board-list")
    public ResponseEntity<List<BoardDTO>> getBoardList(int page){
        return boardService.getBoardList(page);
    }

}
