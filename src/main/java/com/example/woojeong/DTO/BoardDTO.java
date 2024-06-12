package com.example.woojeong.DTO;

import com.example.woojeong.Entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

// 데이터 전송시 사용하는 객체
@Getter
@Setter
@ToString
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String password;
    private int views;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setName(boardEntity.getName());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setPassword(boardEntity.getPassword());
        boardDTO.setViews(boardEntity.getViews());
        boardDTO.setCreateTime(boardEntity.getCreateTime());
        boardDTO.setUpdateTime(boardEntity.getUpdateTime());
        return boardDTO;
    }

}
