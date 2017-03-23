package dav.com.foody.Objects;

import java.io.Serializable;

/**
 * Created by binhb on 04/03/2017.
 */

public class Category implements Serializable{

    private Integer id;
    private String img;
    private String name;
    private boolean isNew;
    private boolean choose;

    public Category() {
    }

    public Category(Integer id, String img, String name, boolean isNew, boolean choose) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.isNew = isNew;
        this.choose = choose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }
}
