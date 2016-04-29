package com.zjjf.analysis.mapper.analysis;

import java.util.List;

import com.zjjf.analysis.beans.analysis.authority.BaseAuthority;

public interface BaseAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAuthority record);

    int insertSelective(BaseAuthority record);

    BaseAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAuthority record);

    int updateByPrimaryKey(BaseAuthority record);
    
    List<BaseAuthority> getAuthorityByUserId(String userId);
}