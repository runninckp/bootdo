package com.bootdo.xcx.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 系列
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-27 18:42:13
 */
public class SeriesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//uuid
	private String uuid;
	//品牌id
	private String brandId;
	//名字
	private String cname;
	//英语名字
	private String ename;
	//varchar
	private String img;
	//描述
	private String describe;
	//备注
	private String remark;
	//创建人id
	private Long created;
	//最近修改人id
	private Long modified;
	//创建时间
	private Date gtmCreate;
	//修改时间
	private Date gtmModified;
	//状态
	private Integer status;

	private List<GoodsDO> goodList;

	public List<GoodsDO> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<GoodsDO> goodList) {
		this.goodList = goodList;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getModified() {
		return modified;
	}

	public void setModified(Long modified) {
		this.modified = modified;
	}

	public Date getGtmCreate() {
		return gtmCreate;
	}

	public void setGtmCreate(Date gtmCreate) {
		this.gtmCreate = gtmCreate;
	}

	public Date getGtmModified() {
		return gtmModified;
	}

	public void setGtmModified(Date gtmModified) {
		this.gtmModified = gtmModified;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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
	 * 设置：品牌id
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌id
	 */
	public String getBrandId() {
		return brandId;
	}
	/**
	 * 设置：名字
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：名字
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：英语名字
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：英语名字
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * 设置：varchar
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：varchar
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
