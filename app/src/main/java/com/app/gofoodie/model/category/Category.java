package com.app.gofoodie.model.category;

import com.app.gofoodie.model.base.BaseModel;

public class Category extends BaseModel {

    public int id = 0;
    public boolean checked = true;
    public String name = "default Cuisine";

    public Category(int id, String name, boolean checked) {

        this.id = id;
        this.name = name;
        this.checked = checked;
    }
}
