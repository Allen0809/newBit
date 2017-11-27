package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 团目实体对象：一个旅游项目可以有多个团
 * @author Administrator
 * 1.transient 关键字修饰的属性默认是否可以被序列化?
 * 		不可以，假如我们就是要对使用transition关键字的属性序列化，你如何实现？
 * 
 *	2.类中使用static修饰的属性能否被序列化？
 *		假如希望这个属性被序列化如何实现。
 *	3.Externalizable与Serializable有什么关系？什么场景下回使用Externalizable对象？
 */
public class Team implements Serializable{
	private static final long serialVersionUID = 368009064732092303L;
	
	private Integer id;
	private String name;
	private Integer projectId;
	private Integer valid;
	private String note;
	private Date createdDate;
	private Date modifiedDate;
	private String createdUser;
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
	
}
