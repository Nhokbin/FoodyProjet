package dav.com.foody.Objects;

import java.io.Serializable;

/**
 * Created by binhb on 13/03/2017.
 */

public class District implements Serializable {

    Integer id;
    String name;
    Integer count;
    Integer cityId;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
