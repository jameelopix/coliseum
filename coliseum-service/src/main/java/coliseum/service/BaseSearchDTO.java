package coliseum.service;

import java.io.Serializable;
import java.util.List;

public class BaseSearchDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
