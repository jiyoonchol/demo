package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public Board boardWrite(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Board board) {
        Board findBoard = findVerifiedBoard(board.getId());
        Optional.ofNullable(board.getTitle()).ifPresent(title -> findBoard.setTitle(title));
        Optional.ofNullable(board.getContent()).ifPresent(content -> findBoard.setContent(content));
        return boardRepository.save(findBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public Board findBoard(Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board Id: " + id));
        return boardRepository.save(findBoard);
    }

    public Board findVerifiedBoard(long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        Board findBoard = optionalBoard.orElseThrow(()-> new IllegalArgumentException("Invalid board Id : " + id));
        return findBoard;
    }

}
