package coliseum.jpa;

public class Association {

	private String fieldName;

	private boolean childless = false;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isChildless() {
		return childless;
	}

	public void setChildless(boolean childless) {
		this.childless = childless;
	}
}
