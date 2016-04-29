package com.zjjf.analysis.controller;

public interface IView {
	
	/**
	 * key 0 为id key 1为name
	 * 
	 * @param tableView
	 * @param key
	 * @return
	 */
	Object [] getColumn(Object[][] authorityArray, Integer key_or_name);
}
