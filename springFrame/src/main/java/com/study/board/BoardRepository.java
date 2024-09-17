package com.study.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public int save(BoardDTO boardDTO) {
		
		return sql.insert("Board.save", boardDTO);
	}

	public List<BoardDTO> findAll() {
		return sql.selectList("Board.findAll");
	}

	public BoardDTO findById(long id) {
		return sql.selectOne("Board.findById", id);
	}

	public void updateHits(long id) {
		sql.update("Board.updateHits", id);
	}

	public void delete(Long id) {
		sql.delete("Board.delete", id);
	}

	public void update(BoardDTO boardDTO) {
		sql.update("Board.update", boardDTO);
	}

	public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
		return sql.selectList("Board.pagingList", pagingParams);
	}

	public int boardCount(SearchDTO search) {
		return sql.selectOne("Board.boardCount", search);
	}

	public int count(HashMap<String, Object> data) {
		return sql.selectOne("Board.count", data);
	}

	public List<HashMap<String, Object>> list(Criteria cri) {
		return sql.selectList("Board.list", cri);
	}

	public int listCount(Criteria cri) {
		
		
		HashMap<String, Object> list = new HashMap<String, Object>();
		
		list.put("searchType", cri.getSearchType());
		list.put("searchName", cri.getSearchName());
		
		int listCount = sql.selectOne("Board.listCount", list);
		
		return listCount;
	}


}
