package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.BaseAuthMenu;

public interface BaseAuthMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAuthMenu record);

    int insertSelective(BaseAuthMenu record);

    BaseAuthMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAuthMenu record);

    int updateByPrimaryKey(BaseAuthMenu record);
    
    List<HashMap<String, Object>> getAuthorityByMenuId(HashMap<String, Object> paramMap);
    
    BaseAuthMenu getAuthByMenuIdAndAuthId(HashMap<String, Object> paramMap);
}