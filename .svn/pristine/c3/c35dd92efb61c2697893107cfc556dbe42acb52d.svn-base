package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseRegion;

public interface BaseRegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseRegion record);

    int insertSelective(BaseRegion record);

    BaseRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRegion record);

    int updateByPrimaryKey(BaseRegion record);
    
    //获取城市列表代码
    List<AnaDictionary> getRegionCodeList(HashMap<String, Object> cityMap);
    
    //获取城市列表代码
    List<AnaDictionary> getAreaByCityId(HashMap<String, Object> cityMap);
}