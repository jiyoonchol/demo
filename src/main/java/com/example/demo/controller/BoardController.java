package com.example.demo.controller;

import com.example.demo.dto.BoardPatchDto;
import com.example.demo.dto.BoardPostDto;
import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/b1/boards")
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    @PostMapping()
    public ResponseEntity postBoard(@RequestBody BoardPostDto boardPostDto) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto);
        Board response = boardService.boardWrite(board);
        return new ResponseEntity<>(boardMapper.boardToBoardResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long id,
                                     @RequestBody BoardPatchDto boardPatchDto) {
        boardPatchDto.setId(id);

        Board response = boardService.updateBoard(boardMapper.boardPatchDtoToBoard(boardPatchDto));
        return new ResponseEntity<>(boardMapper.boardToBoardResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long id) {

        Board response = boardService.findBoard(id);
        return new ResponseEntity<>(boardMapper.boardToBoardResponseDto(response), HttpStatus.OK);
    }

    @DeleteMapping("{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") @Positive long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
