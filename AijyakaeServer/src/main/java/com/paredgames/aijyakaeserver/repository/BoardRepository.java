package com.paredgames.aijyakaeserver.repository;

import com.paredgames.aijyakaeserver.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findAllByOrderByIdDesc(Pageable pageable);

}
