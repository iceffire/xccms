package edu.cqu.fly.xccms.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsOrg;
import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.OrgNode;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("orgService")
@Transactional
public class OrgServiceImpl extends CommonServiceImpl implements OrgService{

	public String getOrgJsonStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		List<BsOrg> dataList = findAllEntities(BsOrg.class);
		// 节点列表（散列表，用于临时存储节点对象）
				HashMap<Long, OrgNode> nodeList = new HashMap<Long, OrgNode>();
				// 根节点
				OrgNode root = null;
				// 根据结果集构造节点列表（存入散列表）
				for (BsOrg dataRecord : dataList) {
					dataRecord.setText(dataRecord.getOrgname());
					OrgNode node = new OrgNode();
					node.setId(dataRecord.getId());
					BeanUtils.copyProperties(dataRecord, node);
					nodeList.put(node.getId().longValue(), node);
				}
				// 构造无序的多叉树
				Set entrySet = nodeList.entrySet();
				for (Iterator it = entrySet.iterator(); it.hasNext();) {
					OrgNode node = (OrgNode) ((Map.Entry) it.next()).getValue();
					if (node.getParentid()== null || node.getParentid().equals("")) {
						root = node;
					} else {
						((OrgNode) nodeList.get(node.getParentid())).getChildren()
								.add(node);
					}
				}
				
				String json = JSON.toJSONString(root);
		        return "["+json+"]";
	}

	public void updateOrAddOrg(BsOrg bsOrg) {
		if (null == bsOrg.getId() || bsOrg.getId() == 0) {
			bsOrg.setId(null);
			addEntity(bsOrg);
		} else {
			saveEntity(bsOrg);
		}
		
	}

	public void deleteOrg(long id) {
		TypedQueryBuilder<BsOrg> tqBuilder = new TypedQueryBuilder<BsOrg>(BsOrg.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "parentid", "in", Arrays.asList(Long.parseLong(id+""))));
		List<BsOrg> orglist = queryByParas(tqBuilder);
		for(BsOrg org : orglist){
			removeEntityById(org.getId(),BsOrg.class);
		}
		removeEntityById(Long.parseLong(id+""),BsOrg.class);
	}

	public Integer getOrgCodeByUserId(String userId) {
		TypedQueryBuilder<BsUserOrg> tqBuilder = new TypedQueryBuilder<BsUserOrg>(BsUserOrg.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(userId)));
		List<BsUserOrg> orglist = queryByParas(tqBuilder);
		if(orglist.size() > 0){
			return orglist.get(0).getOrgid();
		}else{
			return null;
		}
	}

}
