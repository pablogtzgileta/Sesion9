package pablogtzgileta.com.sesion9.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PabloPC on 9/21/2017.
 */

public class ItemProduct implements Parcelable {

    private int code;
    private int image;
    private String title;
    private String description;
    private Category category;
    private Store store;

    public ItemProduct(int code, int image, String title, String description, Category category, Store store) {
        this.code = code;
        this.image = image;
        this.title = title;
        this.description = description;
        this.category = category;
        this.store = store;
    }

    public ItemProduct() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.store, flags);
    }

    protected ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.image = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /*
    private int image;
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;

    public ItemProduct(Parcel in) {
        image = in.readInt();
        title = in.readString();
        store = in.readString();
        location = in.readString();
        phone = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(store);
        dest.writeString(location);
        dest.writeString(phone);
        dest.writeString(description);
    }

    public static final Creator<ItemProduct> CREATOR =
            new Creator<ItemProduct>() {
                @Override
                public ItemProduct createFromParcel(Parcel parcel) {
                    return new ItemProduct(parcel);
                }

                @Override
                public ItemProduct[] newArray(int size) {
                    return new ItemProduct[size];
                }
            };

    public ItemProduct() {
        image = 0;
        title = "";
        store = "";
        location = "";
        phone = "";
        description = "";
    }


    @Override
    public String toString() {
        return "Title: " + title + "/ Store: " + store + "/Location: " + location +
                "/Phone:" + phone + " /Description:" + description;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
*/
}

