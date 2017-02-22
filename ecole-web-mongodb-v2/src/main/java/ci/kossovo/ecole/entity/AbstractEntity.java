package ci.kossovo.ecole.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;



public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	protected String id;
	
	public AbstractEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractEntity(String id) {
		super();
		this.id = id;
		
	}

	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		 return hash;
	}

	public AbstractEntity build(String id) {
		 this.id = id;
		 return this;
		}
	
	@Override
	public boolean equals(Object obj) {
		String class1 = this.getClass().getName();
		String class2 = obj.getClass().getName();
		 if (!class2.equals(class1) || obj==null) {
		return false;
		 }
		 AbstractEntity other = (AbstractEntity) obj;
		 return this.id == other.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	

}
