package com.bootdo.yzjj.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author ckp
 * @email 756495742@qq.com
 * @date 2018-06-09 16:11:02
 */
public class SubscribeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//创建人id
	private Long created;
	//最近修改人id
	private Long modified;
	//创建时间
	private Date gtmCreate;
	//修改时间
	private Date gtmModified;
	//主键
	private Long id;
	//uuid
	private String uuid;
	//备注
	private String remark;
	//状态
	private String stutus;
	//类型
	private String type;
	//订阅时间
	private String subtime;
	//推送码
	private String pushcode;

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
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
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
	/**
	 * 设置：状态
	 */
	public void setStutus(String stutus) {
		this.stutus = stutus;
	}
	/**
	 * 获取：状态
	 */
	public String getStutus() {
		return stutus;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：订阅时间
	 */
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	/**
	 * 获取：订阅时间
	 */
	public String getSubtime() {
		return subtime;
	}
	/**
	 * 设置：推送码
	 */
	public void setPushcode(String pushcode) {
		this.pushcode = pushcode;
	}
	/**
	 * 获取：推送码
	 */
	public String getPushcode() {
		return pushcode;
	}
}
