package com.paredgames.aijyakaeserver.service;

import com.paredgames.aijyakaeserver.dto.BoardDTO;
import com.paredgames.aijyakaeserver.dto.BoardInsertRequestDTO;
import com.paredgames.aijyakaeserver.entity.Board;
import com.paredgames.aijyakaeserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Integer DEFAULT_PAGE_SIZE=20;
    public ResponseEntity<BoardDTO> uploadImg(BoardInsertRequestDTO boardInsertRequestDTO){
        //TODO: 검증로직

        //TODO: aws s3에 이미지 업로드


        Board board = Board.toEntity(boardInsertRequestDTO);
        boardRepository.save(board);

        return ResponseEntity.ok(BoardDTO.toDTO(board));
    }

    public ResponseEntity<List<BoardDTO>> getBoardList(int page){
        PageRequest pageRequest = PageRequest.of(page,DEFAULT_PAGE_SIZE);
        Page<Board> res= boardRepository.findAll(pageRequest);
        List<BoardDTO> boardList = new ArrayList<>();
        for(Board board:res){
            boardList.add(BoardDTO.toDTO(board));
        }
        return ResponseEntity.ok(boardList);
    }

}
