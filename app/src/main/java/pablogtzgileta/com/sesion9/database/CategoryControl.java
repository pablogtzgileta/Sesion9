package pablogtzgileta.com.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pablogtzgileta.com.sesion9.beans.Category;

/**
 * Created by PabloPC on 10/16/2017.
 */

public class CategoryControl {
    public ArrayList<Category> getAllCategories(DataBaseHandler dh) {
        ArrayList<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT C." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DataBaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DataBaseHandler.TABLE_CATEGORY + " C";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setIdCategory(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return categories;
    }
}
