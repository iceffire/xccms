package edu.cqu.fly.xccms.util;
/**
 * <p>Title: Constant.java</p>
 * <p>Description: </p>
 * <p>Copyright: 59et Software (c) 2011</p>
 * <p>Company: 点滴工作室</p>
 * @author Liuhh(jxausea@gmail.com)
 * @date 2012-2-19
 * @version 2.0
 *
 */
public class Constant {
	public static String ACTION_RESULT = "ACTION_RESULT"; //结果
	public static int RESULT_SUCCESS = 0; //结果为成功
	public static int RESULT_FAIL = 1; //结果为失败
	public static String USER = "USER"; //结果为失败
	public static String EMAIL = "EMAIL"; //结果为失败
	public static String PRODUCT_LIST = "PRODUCT_LIST"; //产品列表
	public static String SUPPLIERCODE = "SUPPLIERCODE";//供应商代码
	public static String TOTALPAGECOUNT = "TOTALPAGECOUNT";//总记录数
	public static String TOTALCOUNT = "TOTALCOUNT";//总记录数
	public static String BBSMESSAGE_LIST="BBSMESSAGE_LIST";//bbs帖子列表
	public static int PAGESIZE = 10;//默认分页大小
	public static String PRODUCT_OBJ = "PRODUCT_OBJ";//product对象
	public static String ORDER_OBJ = "ORDER_OBJ";//product对象
	public static String BSUSERSERVICE_OBJ = "BSUSERSERVICE_OBJ";//BSUSERSERVICE_OBJ对象
	public static int ORDER_STATUS_UNPAY = 1;//订单未支付
	public static int ORDER_STATUS_PAY = 2;//订单已支付支付
	public static String ORDER_LIST = "ORDER_LIST";//订单list
	public static String BSUSERSERVICE_LIST = "BSUSERSERVICE_LIST";
	public static String KEY = "123456789";
	public static String DEFAULT_PASSWORD="123456";
	public static String ISACTIVE_YES="yes";
	public static String ISACTIVE_NO="no";
	public static String ONE_HOUR="ONE_HOUR";
	public static String FOUR_HOUR="FOUR_HOUR";
	public static String EIGHT_HOUR="EIGHT_HOUR";
	public static String ONE_DAY="ONE_DAY";
	public static String TWO_DAY="TWO_DAY";
	public static String ONE_WEEK="ONE_WEEK";
	public static String ONE_MONTH="ONE_MONTH";
	public static String HALF_YEAR="HALF_YEAR";
	public static String ONE_YEAR="ONE_YEAR";
	/**
	 * 过期时间2个月
	 */
	public static long TOKEN_EXPIRES=2*30*24*60*60;
	/**
	 * 公司新闻
	 */
	public static String ARTICLE_TYPE_NEWS="news";
	/**
	 * 公司通知
	 */
	public static String ARTICLE_TYPE_NOTICE="notice";
	/**
	 * 行业新闻
	 */
	public static String ARTICLE_TYPE_MEDIA="media";
	/**
	 * 文章类表
	 */
	public static String ARTICLE_LIST = "ARTICLE_LIST";
	/**
	 * 文章详情
	 */
	public static String ARTICLE_DETAIL = "ARTICLE_DETAIL";
	/**
	 * 公共资源
	 */
	public static String RESOURCE_PUBLIC ="public";
	/**
	 * 私有资源
	 */
	public static String RESOURCE_PRIVATE ="private";
	/**
	 * 受保护的资源
	 */
	public static String RESOURCE_PROTECT ="protect";
	/**
	 * 购物车
	 */
	public static String SHOP_CART ="shop_cart";
}
