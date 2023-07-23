package com.example.demo.mapper;


import com.example.demo.dto.BoardPatchDto;
import com.example.demo.dto.BoardPostDto;
import com.example.demo.dto.BoardResponseDto;
import com.example.demo.entity.Board;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board boardPostDtoToBoard(BoardPostDto boardPostDto);

    Board boardPatchDtoToBoard(BoardPatchDto boardPatchDto);

    BoardResponseDto boardToBoardResponseDto(Board board);
}
