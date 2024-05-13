package com.paredgames.aijyakaeserver;

import static org.junit.Assert.*;

import com.paredgames.aijyakaeserver.dto.BoardDTO;
import com.paredgames.aijyakaeserver.entity.Board;
import com.paredgames.aijyakaeserver.repository.BoardRepository;
import com.paredgames.aijyakaeserver.service.BoardService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@SpringBootTest
class AijyakaeServerApplicationTests {

	private BoardDTO boardDTO;

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardRepository boardRepository;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Before
    public void beforeLoad(){
		boardDTO = BoardDTO.builder()
				.prompt("prompt")
				.negativePrompt("negativePrompt")
				.s3Url("s3url")
				.userName("userName")
				.build();
	}
	@Test
	void contextLoads() {

	}

	@Test
	void setImageTest(){
		boardDTO = BoardDTO.builder()
				.prompt("prompt")
				.negativePrompt("negativePrompt")
				.s3Url("s3url")
				.userName("userName")
				.build();
		Board board = boardRepository.save(Board.toEntity(boardDTO));

	}


	@Test
	void getBoardListTest(){
		boardDTO = BoardDTO.builder()
				.prompt("prompt")
				.negativePrompt("negativePrompt")
				.s3Url("s3url")
				.userName("userName")
				.build();

		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));
		boardRepository.save(Board.toEntity(boardDTO));


		Page<Board> boardPage = boardRepository.findAll(PageRequest.of(0,5));
		logger.info(String.valueOf(boardRepository.count()));
		for(Board board:boardPage){
			logger.info(board.getId().toString());
			logger.info(board.getPrompt());
		}


	}




}
