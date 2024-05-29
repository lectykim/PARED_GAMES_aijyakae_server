package com.paredgames.aijyakaeserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.paredgames.aijyakaeserver.dto.BoardDTO;
import com.paredgames.aijyakaeserver.dto.request.BoardInsertRequestDTO;
import com.paredgames.aijyakaeserver.entity.Board;
import com.paredgames.aijyakaeserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Integer DEFAULT_PAGE_SIZE=20;

    private final AmazonS3 amazonS3;
    private final String BUCKET_NAME="paredgamesaijyakaebucket";
    public ResponseEntity<BoardDTO> uploadImg(BoardInsertRequestDTO boardInsertRequestDTO){

        byte[] image = Base64.decodeBase64(boardInsertRequestDTO.getImageBase64());

        InputStream inputStream = new ByteArrayInputStream(image);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.length);
        objectMetadata.setContentType("image/jpeg");
        String fileName = boardInsertRequestDTO.getId()+".jpg";

        amazonS3.putObject(BUCKET_NAME,fileName,inputStream,objectMetadata);

        String s3Url = String.valueOf(amazonS3.getUrl(BUCKET_NAME,fileName));

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setFileId(boardInsertRequestDTO.getId());
        boardDTO.setPrompt(boardInsertRequestDTO.getPrompt());
        boardDTO.setS3Url(s3Url);
        boardDTO.setUserName(boardInsertRequestDTO.getUserName());
        boardDTO.setNegativePrompt(boardInsertRequestDTO.getNegativePrompt());
        boardDTO.setWidth(boardInsertRequestDTO.getWidth());
        boardDTO.setHeight(boardInsertRequestDTO.getHeight());

        Board board = Board.toEntity(boardDTO);
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

    public ResponseEntity<BoardDTO> getBoardItem(String id){
        Optional<Board> board = boardRepository.findById(Long.parseLong(id));
        return board.map(value -> ResponseEntity.ok(BoardDTO.toDTO(value))).orElseGet(() -> ResponseEntity.ok(null));
    }

}
