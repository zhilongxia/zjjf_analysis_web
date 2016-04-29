package com.zjjf.analysis.controller;

import java.util.List;

public interface IFilter {

	String[] getAuthorityColumn(String[] tableView, List<String> authorityFilter);
}
