package hrcp.account.sys.model;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import hrcp.account.sys.bean.PlatformSystem;
import hrcp.account.sys.model.QrySysDco;

public class SystemQrySpecification implements Specification<PlatformSystem>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8011518577423491021L;

	public SystemQrySpecification(){
	}
	
	public SystemQrySpecification(QrySysDco qrySysDco){
		this.qrySysDco = qrySysDco;
	}

	private QrySysDco qrySysDco;
	
	public void setQrySysDco(QrySysDco qrySysDco) {
		this.qrySysDco = qrySysDco;
	}

	@Override
	public Predicate toPredicate(Root<PlatformSystem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			Predicate predicate = cb.equal(root.get("deleted"),false);
			String param = qrySysDco.getSysName();
			if(!StringUtils.isEmpty(param)){
				predicate = cb.equal(root.get("sysName"),param);
			}
			param = qrySysDco.getSysNameFuzzy();
			if(!StringUtils.isEmpty(param)){
				predicate = cb.like(root.get("sysName"),"%" + param + "%");
			}
			param = qrySysDco.getSysPid();
			if(!StringUtils.isEmpty(param)){
				predicate = cb.equal(root.get("sysPid"),param );
			}
			if(qrySysDco.getOnLine() != null){
				predicate = cb.equal(root.get("onLine"),qrySysDco.getOnLine());
			}
			return predicate;
	}
}
