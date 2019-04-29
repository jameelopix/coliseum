package coliseum.service;

import java.io.Serializable;
import java.util.List;

public class BaseSearchDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> idList;

    public static String ID = "id";

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
