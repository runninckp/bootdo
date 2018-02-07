package com.bootdo.xcx.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 名牌
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
public class BrandDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//创始人
	private String founder;
	//公司
	private String company;
	//详细描述
	private String direction;
	//
	private String imgurl;
	//创建人id
	private Long created;
	//最近修改人id
	private Long modified;
	//创建时间
	private Date gtmCreate;
	//修改时间
	private Date gtmModified;
	//uuid
	private String uuid;
	//中文名称
	private String cname;
	//英文名字
	private String ename;
	//状态
	private Integer status;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：创始人
	 */
	public void setFounder(String founder) {
		this.founder = founder;
	}
	/**
	 * 获取：创始人
	 */
	public String getFounder() {
		return founder;
	}
	/**
	 * 设置：公司
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：公司
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：详细描述
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * 获取：详细描述
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * 设置：
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	/**
	 * 获取：
	 */
	public String getImgurl() {
		return imgurl;
	}
	/**
	 * 设置：创建人id
	 */
	public void setCreated(Long created) {
		this.created = created;
	}
	/**
	 * 获取：创建人id
	 */
	public Long getCreated() {
		return created;
	}
	/**
	 * 设置：最近修改人id
	 */
	public void setModified(Long modified) {
		this.modified = modified;
	}
	/**
	 * 获取：最近修改人id
	 */
	public Long getModified() {
		return modified;
	}
	/**
	 * 设置：创建时间
	 */
	public void setGtmCreate(Date gtmCreate) {
		this.gtmCreate = gtmCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getGtmCreate() {
		return gtmCreate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setGtmModified(Date gtmModified) {
		this.gtmModified = gtmModified;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getGtmModified() {
		return gtmModified;
	}
	/**
	 * 设置：uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 获取：uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 设置：中文名称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：中文名称
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：英文名字
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：英文名字
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
}
