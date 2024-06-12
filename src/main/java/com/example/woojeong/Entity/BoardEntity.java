package com.example.woojeong.Entity;

import com.example.woojeong.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TA_board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;    // 게시글 번호

    @Column(length = 20, nullable = false)  // 크기 20, not null
    private String name;    // 작성자

    @Column(length = 20)
    private String title;  // 제목

    @Column(length = 1000)
    private String content; // 내용

    @Column(length = 20)
    private String password;    // 비밀번호

    private int views; // 조회수

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime; // 작성 날짜

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateTime; // 수정 날짜

    public static BoardEntity toWriteEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDTO.getName());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setPassword(boardDTO.getPassword());
        boardEntity.setViews(0);
        return boardEntity;
    }
}
