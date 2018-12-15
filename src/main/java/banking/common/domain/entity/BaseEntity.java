package banking.common.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.proxy.HibernateProxyHelper;

public class BaseEntity {

	public BigDecimal id;
	public Date createdAt;
	public Date updatedAt;

	public BaseEntity() {
		super();
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	private Class<?> getRealType() {
		return HibernateProxyHelper.getClassWithoutInitializingProxy(this);

	}

	@Override
	public int hashCode() {
		return (this.getRealType().toString() + this.id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof BaseEntity)) {
			return false;
		}

		BaseEntity entity = (BaseEntity) obj;

		if (this.getRealType() != entity.getRealType()) {
			return false;
		}

		if (this.id.signum() == 0 || entity.id.signum() == 0) {
			return false;
		}

		return this.id == entity.id;
	}

//	public static void throwExceptionIfAny(Notification notification) {
//		if (notification.hasErrors()) throw new IllegalArgumentException(notification.errorMessage());
//	}

}
