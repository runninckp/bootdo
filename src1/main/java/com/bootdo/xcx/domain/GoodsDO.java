package com.bootdo.xcx.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 商品
 * 
 * @author runningckp
 * @email 756495742@qq.com
 * @date 2018-01-24 22:16:10
 */
public class GoodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//uuid
	private String uuid;
	//中文名称
	private String cname;
	//英文名字
	private String ename;
	//价格
	private Integer price;
	//编号
	private String number;
	//材质
	private String quality;
	//材料
	private String material;
	//详细描述
	private String describe;
	//类型/款式
	private String type;
	//系列
	private String series;
	//特点
	private String characteristic;
	//上市时间
	private Date market;
	//颜色
	private String colour;
	//其他
	private String others;
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
	//品牌id
	private String brandUuid;
	//系列id
	private String seriesUuid;
	//图片
	private String imgurl;
	//品牌
	private String brand;

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandUuid() {
		return brandUuid;
	}

	public void setBrandUuid(String brandUuid) {
		this.brandUuid = brandUuid;
	}

	public String getSeriesUuid() {
		return seriesUuid;
	}

	public void setSeriesUuid(String seriesUuid) {
		this.seriesUuid = seriesUuid;
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
	 * 设置：价格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：编号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：编号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置：材质
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}
	/**
	 * 获取：材质
	 */
	public String getQuality() {
		return quality;
	}
	/**
	 * 设置：材料
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	/**
	 * 获取：材料
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * 设置：详细描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：详细描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：类型/款式
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型/款式
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：系列
	 */
	public void setSeries(String series) {
		this.series = series;
	}
	/**
	 * 获取：系列
	 */
	public String getSeries() {
		return series;
	}
	/**
	 * 设置：特点
	 */
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	/**
	 * 获取：特点
	 */
	public String getCharacteristic() {
		return characteristic;
	}
	/**
	 * 设置：上市时间
	 */
	public void setMarket(Date market) {
		this.market = market;
	}
	/**
	 * 获取：上市时间
	 */
	public Date getMarket() {
		return market;
	}
	/**
	 * 设置：颜色
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	/**
	 * 获取：颜色
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * 设置：其他
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * 获取：其他
	 */
	public String getOthers() {
		return others;
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
