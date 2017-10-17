package pablogtzgileta.com.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pablogtzgileta.com.sesion9.beans.Category;
import pablogtzgileta.com.sesion9.beans.City;
import pablogtzgileta.com.sesion9.beans.ItemProduct;
import pablogtzgileta.com.sesion9.beans.Store;

/**
 * Created by PabloPC on 10/16/2017.
 */

public class ItemProductControl {

    public long addItemProduct(ItemProduct product, DataBaseHandler dh) {
        long inserted;

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, "TECHNOLOGY");
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_STORE, product.getStore().getId());
        values.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());

        inserted = db.insert(DataBaseHandler.TABLE_PRODUCT, null, values);

        try {
            db.close();
        } catch (Exception e) {
        }

        return inserted;
    }

    public int updateProduct(ItemProduct product, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getIdCategory());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_STORE, product.getStore().getId());
        values.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());

        // Updating row
        int count = db.update(DataBaseHandler.TABLE_PRODUCT, values,
                DataBaseHandler.KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getCode())});
        try {
            db.close();
        } catch (Exception e) {
        }
        return count;
    }

    public void deleteProduct(int idProduct, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_PRODUCT, DataBaseHandler.KEY_PRODUCT_ID
                + " = ?", new String[]{String.valueOf(idProduct)});
        try {
            db.close();
        } catch (Exception e) {
        }
    }

    public ItemProduct getProductById(int idProduct, DataBaseHandler dh) {
        ItemProduct itemProduct = new ItemProduct();
        String selectQuery = "SELECT S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "C." + DataBaseHandler.KEY_CITY_ID + ","
                + "C." + DataBaseHandler.KEY_CITY_NAME + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_NAME + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_DESCRIPTION + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_TITLE + " FROM "
                + DataBaseHandler.TABLE_STORE + " S, "
                + DataBaseHandler.TABLE_CITY + " C, "
                + DataBaseHandler.TABLE_CATEGORY + " CA, "
                + DataBaseHandler.TABLE_PRODUCT + " P WHERE P."
                + DataBaseHandler.KEY_PRODUCT_ID + " = " + idProduct
                + " AND P." + DataBaseHandler.KEY_PRODUCT_STORE
                + " = S." + DataBaseHandler.KEY_STORE_ID
                + " AND P." + DataBaseHandler.KEY_PRODUCT_CATEGORY
                + " = CA." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND S." + DataBaseHandler.KEY_STORE_CITY
                + " = C." + DataBaseHandler.KEY_CITY_ID;


        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setLatitude(cursor.getDouble(1));
            store.setLongitude(cursor.getDouble(2));
            store.setName(cursor.getString(3));
            store.setPhone(cursor.getString(4));
            store.setThumbnail(cursor.getInt(5));

            City city = new City();
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));

            Category category = new Category();
            category.setIdCategory(cursor.getInt(8));
            category.setName(cursor.getString(9));

            itemProduct.setImage(cursor.getInt(10));
            itemProduct.setCode(cursor.getInt(11));
            itemProduct.setDescription(cursor.getString(12));
            itemProduct.setTitle(cursor.getString(13));
            itemProduct.setCategory(category);
            itemProduct.setStore(store);
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }

        return itemProduct;
    }


    public ArrayList<ItemProduct> getProductsWhere(
            String strWhere, String strOrderBy, DataBaseHandler dh) {

        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        String selectQuery = "SELECT   S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_CITY + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + ","
                + "C." + DataBaseHandler.KEY_CITY_NAME + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_NAME + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_DESCRIPTION + " FROM "
                + DataBaseHandler.TABLE_STORE + " S, "
                + DataBaseHandler.TABLE_CITY + " C, "
                + DataBaseHandler.TABLE_CATEGORY + " CA, "
                + DataBaseHandler.TABLE_PRODUCT + " P WHERE P."
                + DataBaseHandler.KEY_PRODUCT_CATEGORY
                + " = CA." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND S." + DataBaseHandler.KEY_STORE_CITY
                + " = C." + DataBaseHandler.KEY_CITY_ID
                + " AND S." + DataBaseHandler.KEY_STORE_ID + " = "
                + "P." + DataBaseHandler.KEY_PRODUCT_STORE +
                " ORDER BY " + strOrderBy;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();
                store.setId(cursor.getInt(0));
                store.setName(cursor.getString(1));
                store.setPhone(cursor.getString(2));
                City city = new City();
                city.setIdCity(cursor.getInt(3));
                store.setThumbnail(cursor.getInt(4));
                store.setLatitude(cursor.getDouble(5));
                store.setLongitude(cursor.getDouble(6));
                city.setName(cursor.getString(7));
                Category category = new Category();
                category.setIdCategory(cursor.getInt(8));
                category.setName(cursor.getString(9));
                ItemProduct itemProduct = new ItemProduct();
                itemProduct.setCode(cursor.getInt(10));
                itemProduct.setTitle(cursor.getString(11));
                itemProduct.setImage(cursor.getInt(12));
                itemProduct.setDescription(cursor.getString(13));
                itemProduct.setStore(store);
                itemProduct.setCategory(category);
                itemProducts.add(itemProduct);
            } while (cursor.moveToNext());
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        db = null;
        cursor = null;
        return itemProducts;
    }

}
