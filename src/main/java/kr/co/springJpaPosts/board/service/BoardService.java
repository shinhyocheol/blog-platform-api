package kr.co.springJpaPosts.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.springJpaPosts.board.dto.BoardDto;
import kr.co.springJpaPosts.board.entity.Board;
import kr.co.springJpaPosts.board.repository.BoardRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	
    private BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
    	return boardRepository.save(boardDto.toEntity()).getId();
    }
    
    @Transactional
    public List<BoardDto> getBoardList() {
        
    	List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    
    @Transactional
    public BoardDto getPost(Long id) {
    	
    	Board board = boardRepository.findById(id).get();
    	
    	BoardDto boardDto = BoardDto.builder()
    			.id(board.getId())
    			.author(board.getAuthor())
    			.title(board.getTitle())
    			.content(board.getContent())
    			.createdDate(board.getCreatedDate())
    			.build();
    	
    	return boardDto;
    }

	public void deletePost(long id) {
		boardRepository.deleteById(id);
	}
    
    
}