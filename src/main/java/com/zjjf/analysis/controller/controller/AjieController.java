package com.zjjf.analysis.controller.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.producer.ajie.ISpGroupTurnoverService;
import com.zjjf.analysis.producer.ajie.ISpOrderDetailService;
import com.zjjf.analysis.producer.ajie.IStoreTurnoverService;
import com.zjjf.analysis.producer.ajie.ISupplierTurnoverService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping(value = "/ajie/api")
public class AjieController {

	@Reference(version = "1.0.0")
	private ISpGroupTurnoverService spGroupTurnoverService;

	@Reference(version = "1.0.0")
	private IStoreTurnoverService storeTurnoverService;

	@Reference(version = "1.0.0")
	private ISupplierTurnoverService supplierTurnoverService;

	@Reference(version = "1.0.0")
	private ISpOrderDetailService spOrderDetailService;

	@RequestMapping(value = "/storeData.do", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Object>> storeData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "storeCode", required = false, defaultValue = "") String storeCode,
			@RequestParam(value = "storeCodeList", required = false, defaultValue = "") List<String> storeCodeList,
			@RequestParam(value = "cityId", required = false, defaultValue = "") Integer cityId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
			@RequestParam(value = "isVisit", required = false, defaultValue = "") Integer isVisit,
			@RequestParam(value = "areaIdList", required = false, defaultValue = "") List<Integer> areaIdList,
			@RequestParam(value = "timeType", required = false, defaultValue = "") String timeType,
			@RequestParam(value = "isUnion", required = false, defaultValue = "") String isUnion,
			@RequestParam(value = "spGroupId", required = false, defaultValue = "") Integer spGroupId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo,
			@RequestParam(value = "offset", required = false, defaultValue = "") Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("cityId", cityId);
		paramMap.put("areaId", areaId);
		paramMap.put("isVisit", isVisit);
		paramMap.put("areaIdList", areaIdList.isEmpty() ? null : areaIdList);
		paramMap.put("storeCode", storeCode);
		paramMap.put("timeType", timeType);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		paramMap.put("isUnion", isUnion);
		paramMap.put("storeCodeList", storeCodeList.isEmpty() ? null : storeCodeList);
		return storeTurnoverService.getData(paramMap);
	}
	
	@RequestMapping(value = "/storeOrderData.do", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Object>> getTurnoverBySpGoupAndStore(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "storeCode", required = false, defaultValue = "") String storeCode,
			@RequestParam(value = "spGroupId", required = false, defaultValue = "") Integer spGroupId,
			@RequestParam(value = "storeCodeList", required = false, defaultValue = "") List<String> storeCodeList,
			@RequestParam(value = "isUnion", required = false, defaultValue = "") String isUnion,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo,
			@RequestParam(value = "offset", required = false, defaultValue = "") Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("dayTime", dayTime);
		paramMap.put("isUnion", isUnion);
		paramMap.put("storeCodeList", storeCodeList.isEmpty() ? null : storeCodeList);
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		return storeTurnoverService.getTurnoverBySpGoupAndStore(paramMap);
	}

	@RequestMapping(value = "/supplierData.do", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Object>> supplierData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "cityId", required = false, defaultValue = "") Integer cityId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
			@RequestParam(value = "areaIdList", required = false, defaultValue = "") List<Integer> areaIdList,
			@RequestParam(value = "supplierCode", required = false, defaultValue = "") String supplierCode,
			@RequestParam(value = "supplierId", required = false, defaultValue = "") String supplierId,
			@RequestParam(value = "timeType", required = false, defaultValue = "") String timeType,
			@RequestParam(value = "isUnion", required = false, defaultValue = "") String isUnion,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo,
			@RequestParam(value = "offset", required = false, defaultValue = "") Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("cityId", cityId);
		paramMap.put("areaId", areaId);
		paramMap.put("areaIdList", areaIdList.isEmpty() ? null : areaIdList);
		paramMap.put("supplierCode", supplierCode);
		paramMap.put("supplierId", supplierId);
		paramMap.put("timeType", timeType);
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		paramMap.put("isUnion", isUnion);
		return supplierTurnoverService.getData(paramMap);
	}

	@RequestMapping(value = "/spGroupData.do", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Object>> spGroupData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "cityId", required = false, defaultValue = "") Integer cityId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
			@RequestParam(value = "areaIdList", required = false, defaultValue = "") List<Integer> areaIdList,
			@RequestParam(value = "spGroupId", required = false, defaultValue = "") Integer spGroupId,
			@RequestParam(value = "timeType", required = false, defaultValue = "") String timeType,
			@RequestParam(value = "isUnion", required = false, defaultValue = "") String isUnion,
			@RequestParam(value = "pageNo", required = false, defaultValue = "") Integer pageNo,
			@RequestParam(value = "offset", required = false, defaultValue = "") Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("cityId", cityId);
		paramMap.put("areaId", areaId);
		paramMap.put("areaIdList", areaIdList.isEmpty() ? null : areaIdList);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("timeType", timeType);
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		paramMap.put("isUnion", isUnion);
		return spGroupTurnoverService.getData(paramMap);
	}

	@RequestMapping(value = "/updateIsVist.do", method = RequestMethod.GET)
	public @ResponseBody Integer spGroupData(
			@RequestParam(value = "storeCode", required = false, defaultValue = "") String storeCode,
			@RequestParam(value = "dayTime", required = false, defaultValue = "") Integer dayTime,
			@RequestParam(value = "isVisit", required = false, defaultValue = "") Integer isVisit) {

		return storeTurnoverService.updateIsVisitByStoreId(dayTime, storeCode, isVisit);
	}

	@RequestMapping(value = "/getOrderDetail.do", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, Object>> getOrderDetail(
			@RequestParam(value = "orderId", required = false, defaultValue = "") String orderId) {

		return spOrderDetailService.getOrderDetail(orderId);
	}
}
