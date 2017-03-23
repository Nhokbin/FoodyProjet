package dav.com.foody.Objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by binhb on 13/03/2017.
 */

public class City implements Serializable {

    Integer id;
    String name;
    List<District> districts;
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
