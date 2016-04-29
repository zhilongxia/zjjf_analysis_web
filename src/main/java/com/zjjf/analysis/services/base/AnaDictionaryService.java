package com.zjjf.analysis.services.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.mapper.analysis.AnaDictionaryMapper;

@Service
public class AnaDictionaryService {

	@Autowired
	private AnaDictionaryMapper anaDictionaryMapper;

	public List<AnaDictionary> getByDictId(String dictId) {

		return anaDictionaryMapper.getByDictId(dictId);
	}

}
