package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseSpGroup;

public interface BaseSpGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseSpGroup record);

    int insertSelective(BaseSpGroup record);

    BaseSpGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseSpGroup record);

    int updateByPrimaryKey(BaseSpGroup record);
    
    List<AnaDictionary> getSpGroupCodeList(HashMap<String, Object> cityMap);
}