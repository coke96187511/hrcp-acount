package hrcp.account.sys.service;

import hrcp.account.sys.bean.PlatformSystem;
import hrcp.account.sys.model.QrySysDco;
import hrcp.account.sys.model.SysOnLineVco;
import hrcp.comm.exception.HrcpException;
import hrcp.comm.model.PagedData;

/**
 * 平台系统的服务接口定义
 * @author 
 *
 */
public interface SystemService {
	/**
	 * 添加平台系统
	 * @param system 平台系统实例
	 * @return
	 */
	PlatformSystem addSystem(PlatformSystem system) throws HrcpException;
	
	/**
	 * 修改平台系统
	 * @param system 平台系统实例
	 * @return
	 */
	PlatformSystem updateSystem(PlatformSystem system) throws HrcpException;
	
	/**
	 * 更改平台系统是否删除的值为1
	 * @param sysId 平台系统ID
	 * @return 操作的平台系统信息
	 */
	PlatformSystem setSystemDeletedToYes(String sysId) throws HrcpException;
	
	/**
	 * 修改平台系统的是否启用状态的值，即启用|警用平台系统，或者上线|下线
	 * @param sysOnLineVco 平台系统上线状态修改时的参数，具体参数请查询 {@link QrySysDco}} 
	 * @return
	 */
	PlatformSystem updateSystemOnlineValue(SysOnLineVco sysOnLineVco) throws HrcpException;
	
	/**
	 * 根据系统ID查询平台系统实例[deleted字段为0的数据，即未被删除的记录]
	 * @param sysId 平台系统ID
	 * @return 平台系统数据
	 */
	PlatformSystem getSystemBySysId(String sysId) throws HrcpException;
	
	/**
	 * 根据条件查询所有平台系统[deleted字段为0的数据，即未被删除的记录]
	 * @param conditon 平台系统的查询条件,具体参数请查询 {@link QrySysDco}
	 * @return 分页数据，Page中封装了记录总数和列表数据
	 */
	PagedData<PlatformSystem> qryPagedSystemData(QrySysDco conditon) throws HrcpException;
}
