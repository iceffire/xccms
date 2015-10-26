package edu.cqu.fly.xccms.pojo;

/**
 * 分页类
 * @author liuhaihua
 *
 */
public class Pager {
	/**
	 * 总共多少条记录
	 */
	private int total;
	/**
	 * 当前多少条
	 */
	private Object rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object list) {
		this.rows = list;
	}


}
