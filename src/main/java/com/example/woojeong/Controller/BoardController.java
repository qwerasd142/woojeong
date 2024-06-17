package com.example.woojeong.Controller;

import com.example.woojeong.DTO.BoardDTO;
import com.example.woojeong.Service.BoardService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//  게시판 목록, 페이징
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
    //       pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5; // 페이징 번호 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) - 1 ) * blockLimit + 1; // 1 6 11
        int endPage = Math.min((startPage + blockLimit - 1), boardList.getTotalPages()); // 5 10 15

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/boardPaging";
    }
//    @GetMapping("")
//    public String boardList(Model model){
//        List<BoardDTO> boardDTOList = boardService.findAll();
//        model.addAttribute("boardList", boardDTOList);
//        return "board/boardList";
//    }
//  게시글 작성 페이지
    @GetMapping("/write")
    public String boardWriteForm(){
        return "board/boardWrite";
    }
    
//  게시글 작성
    @PostMapping("/write")
    public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/board/paging";
    }

//  게시글 조회, 쿠키로 조회수 중복 방지
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("views")){
                    oldCookie = cookie;
                }
            }
        }
        if(oldCookie != null) {
            if(!oldCookie.getValue().contains("[" + id.toString() + "]")){
                boardService.updateViews(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(30);   // 쿠키 유지 시간

                response.addCookie(oldCookie);
            }
        }else {
            boardService.updateViews(id);
            Cookie newCookie = new Cookie("views", "[" + id +"]");
            newCookie.setPath("/");
            newCookie.setMaxAge(30);
            response.addCookie(newCookie);
        }

        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board/boardDetail";
    }

    @GetMapping("/update/{id}")
    public String boardUpdate(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "board/boardUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
       BoardDTO board = boardService.update(boardDTO);
       model.addAttribute("board", board);
       return "board/boardDetail";
    }

//  상세 페이지
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board";
    }


}
