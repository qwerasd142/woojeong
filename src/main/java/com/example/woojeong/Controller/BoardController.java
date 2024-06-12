package com.example.woojeong.Controller;

import com.example.woojeong.DTO.BoardDTO;
import com.example.woojeong.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//  게시판 목록
    @GetMapping("")
    public String boardList(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardList";
    }
//  게시글 작성 페이지
    @GetMapping("/write")
    public String boardWriteForm(){
        return "boardWrite";
    }
    
//  게시글 작성
    @PostMapping("/write")
    public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/board";
    }
}
