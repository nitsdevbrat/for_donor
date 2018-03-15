package fordonor.com.fordonor.home.model;

/**
 * Created by and-36 on 14/3/18.
 */

public class SubcategoryModel {
    public SubcategoryModel(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    private String subcategoryname;
}
