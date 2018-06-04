package hrcp.account.sys.service.support;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hrcp.account.sys.bean.PlatformSystem;
import hrcp.account.sys.dao.SysRepository;
import hrcp.account.sys.model.QrySysDco;
import hrcp.account.sys.model.SysOnLineVco;
import hrcp.account.sys.model.SystemQrySpecification;
import hrcp.account.sys.service.SystemService;
import hrcp.comm.constants.CommConst;
import hrcp.comm.constants.RtnMessageConst;
import hrcp.comm.exception.HrcpException;
import hrcp.comm.model.PagedData;
import hrcp.comm.utils.BeanCopier;
import hrcp.comm.utils.ExecptionStackUtil;
import hrcp.comm.utils.UUIDGenerator;

@Service("systemService")
public class DefaultSystemService implements SystemService{

	@Autowired
	private SysRepository sysRepository;

	@Transactional
	@Override
	public PlatformSystem addSystem(PlatformSystem system)  throws HrcpException{
		String sysName = system.getSysName();
		if(StringUtils.isEmpty(sysName)){
			 throw new HrcpException("系统名称为空");
		}
		if(sysName.length() > 20){
			 throw new HrcpException("系统名称超过上限[20]");
		}
		String sysDesc = system.getSysDesc();
		if(!StringUtils.isEmpty(sysDesc) && sysName.length() > 50){
			 throw new HrcpException("系统描述超过上限[59]");
		}
		String sysId = UUIDGenerator.generateUUID();
		system.setSysId(sysId);
		Integer sortIndex = system.getSortIndex();
		if(sortIndex == null){
			sortIndex = CommConst.DEFAULT_SORT_INDEX;
			system.setSortIndex(sortIndex);
		}
		sysId = system.getSysPid();
		if(sysId == null){
			system.setSysPid("");
		}
		Date date = new Date();
		system.setCreatedDate(date);
		system.setLastModifiedDate(date);
		system.setDeleted(false);
		if(system.getOnLine() == null){
			system.setOnLine(false);
		}
		//用户信息后期补上
		try {
			return sysRepository.saveAndFlush(system);
		} catch (Exception e) {
			 throw new HrcpException(ExecptionStackUtil.getStackError(e));
		}
	}

//	@Transactional
	@Override
	public PlatformSystem updateSystem(PlatformSystem system)  throws HrcpException{
		String sysId = system.getSysId();
		if(StringUtils.isEmpty(sysId)){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统ID为空");
		}
		PlatformSystem platformSystem = sysRepository.getPlatformSystemBySysId(sysId);
		if(platformSystem  == null){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_OBJECT_MISSING,"系统[" + sysId + "]不存在");
		}
		if(platformSystem.getDeleted()){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_OBJECT_MISSING,"系统[" + sysId + "]已删除，不执行此次修改操作");
		}
		String sysName = system.getSysName();
		if(StringUtils.isEmpty(sysName)){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统名称为空");
		}
		if(sysName.length() > 20){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统名称超过上限[20]");
		}
		String sysDesc = system.getSysDesc();
		if(!StringUtils.isEmpty(sysDesc) && sysName.length() > 50){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统描述超过上限[50]");
		}
		boolean changed = !sysName.equals(platformSystem.getSysName());
		Integer sortIndex = system.getSortIndex();
		if(sortIndex != null && !changed){
			changed = sortIndex != platformSystem.getSortIndex();
		}
		if(!changed && !StringUtils.isEmpty(sysDesc)){
			changed = !sysDesc.equals(platformSystem.getSysDesc());
		}
		if(!changed){
			Boolean onLine = system.getOnLine();
			changed = onLine != null && (onLine != platformSystem.getOnLine());
		}
		if(changed){
			Date date = new Date();
			
			try {
				BeanCopier.copyExcludeNull(PlatformSystem.class,system, platformSystem);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				throw new HrcpException(ExecptionStackUtil.getStackError(e));
			}
			platformSystem.setLastModifiedDate(date);
			//用户信息后期补上
			sysRepository.saveAndFlush(platformSystem);
		}else{
			throw new HrcpException(RtnMessageConst.RTN_CODE_SUCCESS_RECEIVED,"系统未变化");
		}
		
		//用户信息后期补上
		return platformSystem;
	}

	@Transactional
	@Override
	public PlatformSystem setSystemDeletedToYes(String sysId) throws HrcpException {
		if(StringUtils.isEmpty(sysId)){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统ID为空");
		}
		PlatformSystem platformSystem = sysRepository.getPlatformSystemBySysId(sysId);
		if(platformSystem  == null){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_OBJECT_MISSING,"系统[" + sysId + "]不存在");
		}
		if(platformSystem.getDeleted()){
			throw new HrcpException(RtnMessageConst.RTN_CODE_OBJECT_MISSING,"系统已被删除");
		}
		platformSystem.setDeleted(true);
		Date date = new Date();
		platformSystem.setLastModifiedDate(date);
		//用户信息后期补上
		sysRepository.setDeletedValue(sysId,date);
		return platformSystem;
	}

	@Transactional
	@Override
	public PlatformSystem updateSystemOnlineValue(SysOnLineVco sysOnLineVco)  throws HrcpException{
		String sysId = sysOnLineVco.getSysId();
		if(StringUtils.isEmpty(sysId)){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_PARAM_INVALID,"系统ID为空");
		}
		PlatformSystem platformSystem = sysRepository.getPlatformSystemBySysId(sysId);
		if(platformSystem  == null){
			 throw new HrcpException(RtnMessageConst.RTN_CODE_SUCCESS_RECEIVED,"系统[" + sysId + "]不存在");
		}
		if(platformSystem.getDeleted()){
			throw new HrcpException(RtnMessageConst.RTN_CODE_SUCCESS_RECEIVED,"系统已被删除");
		}
		Boolean onLine = sysOnLineVco.getOnLine();
		if(onLine == null){
			 throw new HrcpException("启用或者禁用状态没有传入");
		}
		if(onLine == platformSystem.getOnLine() ){
			throw new HrcpException(RtnMessageConst.RTN_CODE_SUCCESS_RECEIVED,"系统状态已是预期的状态，不继续执行修改操作");
		}
		platformSystem.setOnLine(onLine);
		Date date = new Date();
		platformSystem.setLastModifiedDate(date);
		//用户信息后期补上
		sysRepository.updateOnLineValue(sysOnLineVco.getSysId(),sysOnLineVco.getOnLine(),date);
		return platformSystem;
	}
	
	@Override
	public PlatformSystem getSystemBySysId(String sysId) throws HrcpException{
		if(StringUtils.isEmpty(sysId)){
			 return null;
		}
		PlatformSystem system = sysRepository.getPlatformSystemBySysId(sysId);
		if(system != null && system.getDeleted()){
			return null;
		}
		return system;
	}

	@Override
	public PagedData<PlatformSystem> qryPagedSystemData(QrySysDco conditon) throws HrcpException {
		PagedData<PlatformSystem> pagedData = new PagedData<PlatformSystem>();
		boolean paged = conditon.getPageIndex() != null && conditon.getPageIndex() > 0
				 && conditon.getPageSize() != null && conditon.getPageSize() > 0;
		if(paged){
//			PageRequest page = PageRequest.of((conditon.getPageIndex() - 1)*conditon.getPageSize(), conditon.getPageSize(),Direction.ASC,"sortIndex");
			PageRequest page = new PageRequest((conditon.getPageIndex() - 1)*conditon.getPageSize(), conditon.getPageSize(),Direction.ASC,"sortIndex");
			Page<PlatformSystem> pagedSys = sysRepository.findAll(new SystemQrySpecification(conditon), page);
			pagedData.setRows(pagedSys.getContent());
			pagedData.setTotal(pagedSys.getTotalElements());
		}else{
			Sort sort = new Sort(Direction.ASC,"sortIndex");	
			List<PlatformSystem> rows = sysRepository.findAll(new SystemQrySpecification(conditon), sort);
			pagedData.setRows(rows);
			pagedData.setTotal(pagedData.getRows() == null ? 0 : pagedData.getRows().size());
		}
		return pagedData;
	}

}
