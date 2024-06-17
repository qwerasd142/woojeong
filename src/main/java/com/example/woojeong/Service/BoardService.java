package com.example.woojeong.Service;

import com.example.woojeong.DTO.BoardDTO;
import com.example.woojeong.Entity.BoardEntity;
import com.example.woojeong.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toWriteEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

//  조회수 증가
    @Transactional
    public void updateViews(Long id) {
        boardRepository.updateView(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5;  // 한 페이지에 보여줄 글 갯수
        Page<BoardEntity> boardEntities = 
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getTitle(), board.getName(), board.getViews(), board.getCreateTime()));
        return boardDTOS;
    }
}
