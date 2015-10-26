package edu.cqu.fly.xccms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.ResourceDao;
import edu.cqu.fly.xccms.pojo.BsResource;
import edu.cqu.fly.xccms.pojo.BsRoleResource;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("resourceService")
@Transactional
public class ResourceServiceImpl extends CommonServiceImpl implements ResourceService{

	@SuppressWarnings("restriction")
	@Resource(name="resourceDao")
	protected ResourceDao resourceDao;
	/**
	 * 更具用户查询拥有的权限 用户权限=公共资源+受保护的资源+授权的资源
	 */
	public List<BsResource> queryResourcebyuserId(String userid) {
		List<BsResource> alllist = new ArrayList<BsResource>();
		try {
			alllist.clear();
			// 查询公共资源\
			TypedQueryBuilder<BsResource> tqBuilder = new TypedQueryBuilder<BsResource>(BsResource.class,"e");
			tqBuilder.addRestriction(new TQRestriction( "resourceType", "in", Arrays.asList(Constant.RESOURCE_PUBLIC)));
		
			List<BsResource> publiclist = queryByParas(tqBuilder);
			alllist.addAll(publiclist);

			// 查询资源
			if (null != userid) {
				// 受保护的资源
				tqBuilder = new TypedQueryBuilder<BsResource>(BsResource.class,"e");
				tqBuilder.addRestriction(new TQRestriction( "resourceType", "in", Arrays.asList(Constant.RESOURCE_PROTECT)));
			
				List<BsResource> protectlist = queryByParas(tqBuilder);
				alllist.addAll(protectlist);
				// 私有资源
				TypedQueryBuilder<BsUserRole> tqBuilder_role = new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
				tqBuilder.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(userid)));
				List<BsUserRole> userrolelist = queryByParas(tqBuilder_role);
				List<Integer> roleidlist = new ArrayList<Integer>();
				roleidlist.clear();
				for (BsUserRole bsUserRole : userrolelist) {
					roleidlist.add(bsUserRole.getRoleid());
				}
				if (null != roleidlist && roleidlist.size() > 0) {
					TypedQueryBuilder<BsRoleResource> tqBuilder_resource = new TypedQueryBuilder<BsRoleResource>(BsRoleResource.class,"e");
					tqBuilder.addRestriction(new TQRestriction( "roleid", "in", roleidlist));
				
					List<BsRoleResource> bsRoleResourcelist = queryByParas(tqBuilder_resource);

					for (BsRoleResource bsRoleResource : bsRoleResourcelist) {
						BsResource bsResource = findEntityById(bsRoleResource
								.getResourceid(),BsResource.class);
						alllist.add(bsResource);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alllist;
	}
	/**
	 * 查询资源分页
	 */
	public Pager queryBsResourceByPage(BsResource bsResource, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<BsResource> tqBuilder = new TypedQueryBuilder<BsResource>(BsResource.class,"e");
		
		if (null != bsResource.getResourceUrl()
				&& !bsResource.getResourceUrl().equals("")) {
			tqBuilder.addRestriction(new TQRestriction( "resourceUrl", "like", Arrays.asList("%" + bsResource.getResourceUrl() + "%")));
		}
		int startrecord = (currentpage - 1) * pagesize;
		List<BsResource> list = resourceDao.selectBsResourceForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}

}
