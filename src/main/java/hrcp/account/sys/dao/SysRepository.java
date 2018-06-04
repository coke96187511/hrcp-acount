package hrcp.account.sys.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hrcp.account.sys.bean.PlatformSystem;

public interface SysRepository extends JpaRepository<PlatformSystem,String>,JpaSpecificationExecutor<PlatformSystem>{
	
	@Query(value="from PlatformSystem where sysId=:sysId ")
	PlatformSystem getPlatformSystemBySysId(@Param("sysId") String sysId);
	
	@Modifying
	@Query(value="update PlatformSystem set onLine=:onLine,lastModifiedDate=:lastModifiedDate where sysId=:sysId ")
	int updateOnLineValue(@Param("sysId") String sysId,@Param("onLine") Boolean onLine,
			@Param("lastModifiedDate")Date lastModifiedDate);
	
	@Modifying
	@Query(value="update PlatformSystem set deleted=true,lastModifiedDate=:lastModifiedDate where sysId=:sysId ")
	int setDeletedValue(@Param("sysId") String sysId,@Param("lastModifiedDate")Date lastModifiedDate);
}
