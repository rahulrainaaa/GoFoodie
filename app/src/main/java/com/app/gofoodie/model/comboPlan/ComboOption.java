
package com.app.gofoodie.model.comboPlan;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComboOption implements Parcelable
{

    @SerializedName("combo_item_id")
    @Expose
    private String comboItemId;
    @SerializedName("combo_id")
    @Expose
    private String comboId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("options")
    @Expose
    private List<String> options = null;
    public final static Creator<ComboOption> CREATOR = new Creator<ComboOption>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ComboOption createFromParcel(Parcel in) {
            return new ComboOption(in);
        }

        public ComboOption[] newArray(int size) {
            return (new ComboOption[size]);
        }

    }
    ;

    protected ComboOption(Parcel in) {
        this.comboItemId = ((String) in.readValue((String.class.getClassLoader())));
        this.comboId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.options, (String.class.getClassLoader()));
    }

    public ComboOption() {
    }

    public String getComboItemId() {
        return comboItemId;
    }

    public void setComboItemId(String comboItemId) {
        this.comboItemId = comboItemId;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comboItemId);
        dest.writeValue(comboId);
        dest.writeValue(name);
        dest.writeList(options);
    }

    public int describeContents() {
        return  0;
    }

}
