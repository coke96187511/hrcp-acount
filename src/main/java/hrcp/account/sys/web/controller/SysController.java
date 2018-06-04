package hrcp.account.sys.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrcp.account.sys.bean.PlatformSystem;
import hrcp.account.sys.model.QrySysDco;
import hrcp.account.sys.model.SysIdVco;
import hrcp.account.sys.model.SysOnLineVco;
import hrcp.account.sys.service.SystemService;
import hrcp.comm.exception.HrcpException;
import hrcp.comm.model.PagedData;
import hrcp.comm.model.ServiceResponseBody;

@RestController
public class SysController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("base/sys/list")
	public ServiceResponseBody qryPagedSys(@RequestBody QrySysDco conditon,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PagedData<PlatformSystem> pagedSys =  systemService.qryPagedSystemData(conditon);
			responseBody.setData(pagedSys);
		} catch (HrcpException ex) {
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
	
	@RequestMapping("base/sys/add")
	public ServiceResponseBody addSys(@RequestBody PlatformSystem platformSystem,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PlatformSystem instance =  systemService.addSystem(platformSystem);
			responseBody.setData(instance);
		} catch (HrcpException ex) {
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
	
	@RequestMapping("base/sys/update")
	public ServiceResponseBody updateSys(@RequestBody PlatformSystem platformSystem,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PlatformSystem instance =  systemService.updateSystem(platformSystem);
			responseBody.setData(instance);
		} catch (HrcpException ex) {
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
	

	@RequestMapping("base/sys/get")
	public ServiceResponseBody getSysBySysId(@RequestBody SysIdVco sysIdVco,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PlatformSystem instance = systemService.getSystemBySysId(sysIdVco.getSysId());
			responseBody.setData(instance);
		} catch (HrcpException ex) {
			ex.printStackTrace();
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
	
	@RequestMapping("base/sys/delete")
	public ServiceResponseBody delSysBySysId(@RequestBody SysIdVco sysDelVco,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PlatformSystem instance = systemService.setSystemDeletedToYes(sysDelVco.getSysId());
			responseBody.setData(instance);
		} catch (HrcpException ex) {
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getLocalizedMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
	
	@RequestMapping("base/sys/onLineOrOffLine")
	public ServiceResponseBody delSysBySysId(@RequestBody SysOnLineVco sysOnLineVco,HttpServletResponse response){
		ServiceResponseBody responseBody = new ServiceResponseBody();
		try {
			PlatformSystem instance = systemService.updateSystemOnlineValue(sysOnLineVco);
			responseBody.setData(instance);
		} catch (HrcpException ex) {
			responseBody.setRtnCode(ex.getCode());
			responseBody.setRtnMsg(ex.getMessage());
			response.setStatus(ex.getCode());
		}
		return responseBody;
	}
}
