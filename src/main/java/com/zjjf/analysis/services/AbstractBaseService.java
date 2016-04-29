package com.zjjf.analysis.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.common.constants.Constants;
import com.zjjf.analysis.controller.IView;
import com.zjjf.analysis.mapper.analysis.AnaDictionaryMapper;
import com.zjjf.analysis.mapper.analysis.BaseRegionMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleMapper;
import com.zjjf.analysis.mapper.analysis.BaseSpGroupMapper;

@Service
public abstract class AbstractBaseService implements IView{
	
	@Autowired
	protected BaseRegionMapper baseRegionMapper;
	
	@Autowired
	private  BaseSpGroupMapper baseSpGroupMapper;
	
	@Autowired
	private  AnaDictionaryMapper anaDictionaryMapper;
	
	@Autowired
	private BaseRoleMapper baseRoleMapper;
	
	public Object[] sort_by_viewTitle(HashMap<String, Object> t, Object[] idColumn) {

		Object[] row = new Object[idColumn.length];
		for (int i = 0; i < idColumn.length; i++) {
			String key = (idColumn[i] + "").replace(Constants.authority_query, "");
			if (t.containsKey(key)) {
				row[i] = t.get(key);
			}
		}
		return row;
	}
	
	public Object [] getColumn(Object[][] tableView, Integer key){
		
		Object [] idColumn = new Object[tableView.length];
		for (int i = 0; i < tableView.length; i++) {
			Object[] str = tableView[i];
			idColumn[i] = str[key];
		}
		return idColumn;
	}
	
	public List<Object[]> stand_by_title(List<HashMap<String, Object>> _list, Object [] idColumn){
		
		List<Object[]> idColumnList = new ArrayList<Object[]>();
		for (HashMap<String, Object> t : _list) {
			Object[] temp = sort_by_viewTitle(t, idColumn);
			idColumnList.add(temp);
		}
		return idColumnList;
	}
	
	/**
	 * 第一步，创建一个webbook，对应一个Excel文件  
	 * 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
	 * 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	 * 第四步，创建单元格，并设置值表头 设置表头居中
	 * 
	 * @param sheetName
	 * @param titleColumn
	 * @param dataList
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public InputStream createExcel(String sheetName, Object [] titleColumn, List<Object[]> dataList) {

        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet(sheetName);  
        HSSFRow row = sheet.createRow((int) 0);  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        sheet.setDefaultRowHeightInPoints(10);
        sheet.setDefaultColumnWidth(10);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 30*256);
        sheet.setColumnWidth(6, 30*256);

        HSSFCell cell = row.createCell((short) 0); 
        for (int i = 0; i < titleColumn.length; i++) {
			String titleName = "" + titleColumn[i];
			 cell.setCellValue(titleName); 
        	 cell.setCellStyle(style);  
        	 cell = row.createCell(i);  
		}
        for (int i = 0; i < dataList.size(); i++) {
        	Object[] rowMap = dataList.get(i);
        	row = sheet.createRow(i + 1); 
        	for (int j = 0; j < rowMap.length; j++) {
				Object value = rowMap[j];
				 row.createCell(j).setCellValue(value + "");
			}
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        try{  
            wb.write(os);  
        }catch (IOException e){  
            e.printStackTrace();  
        }  
        byte[] content = os.toByteArray();  
        InputStream is = new ByteArrayInputStream(content);  
        return is; 
    }  
	
	/**
	 * 获取Region字典
	 * 
	 * @return
	 */
	public List<AnaDictionary> getRegionCodeList(Integer regionLevel, Integer pid) {
		
		HashMap<String, Object> cityMap = new HashMap<String, Object>();
		cityMap.put("regionLevel", regionLevel);
		cityMap.put("pid", pid);
		return baseRegionMapper.getRegionCodeList(cityMap);
	}
	
	/**
	 * 获取Region字典
	 * 
	 * @return
	 */
	public List<AnaDictionary> getSpGroupCodeList(HashMap<String, Object> paramMap) {
		
		return baseSpGroupMapper.getSpGroupCodeList(paramMap);
	}
	
	public List<AnaDictionary> getByDictId(String dictId) {
		
		return anaDictionaryMapper.getByDictId(dictId);
	}
	
	public List<BaseRole> getBaseRoleByUserId(String userId){
		
		return baseRoleMapper.getRoleByUserId(userId);
	}
}
