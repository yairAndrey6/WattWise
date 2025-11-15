package wattwise.app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "wattwise.db"
        private const val DATABASE_VERSION = 1

        // Tabla USERS
        const val TABLE_USERS = "users"
        const val COL_USER_ID = "id"
        const val COL_USER_NAME = "name"
        const val COL_USER_LASTNAME = "lastname"
        const val COL_USER_PHONE = "phone"
        const val COL_USER_PASSWORD = "password"

        // Tabla DEVICES
        const val TABLE_DEVICES = "devices"
        const val COL_DEV_ID = "id"
        const val COL_DEV_NAME = "nombre"
        const val COL_DEV_BRAND = "marca"
        const val COL_DEV_WATTS = "watts"
        const val COL_DEV_TYPE = "tipo"

    }

    override fun onCreate(db: SQLiteDatabase) {

        // Crear tabla USERS
        val createUsers = """
            CREATE TABLE $TABLE_USERS (
                $COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USER_NAME TEXT NOT NULL,
                $COL_USER_LASTNAME TEXT NOT NULL,
                $COL_USER_PHONE TEXT NOT NULL,
                $COL_USER_PASSWORD TEXT NOT NULL
            );
        """
        db.execSQL(createUsers)

        // Crear tabla DEVICES
        val createDevices = """
            CREATE TABLE $TABLE_DEVICES (
                $COL_DEV_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_DEV_NAME TEXT NOT NULL,
                $COL_DEV_BRAND TEXT NOT NULL,
                $COL_DEV_WATTS INTEGER NOT NULL,
                $COL_DEV_TYPE TEXT NOT NULL
            );
        """
        db.execSQL(createDevices)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DEVICES")
        onCreate(db)
    }



    fun insertUser(name: String, lastname: String, phone: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COL_USER_NAME, name)
        values.put(COL_USER_LASTNAME, lastname)
        values.put(COL_USER_PHONE, phone)
        values.put(COL_USER_PASSWORD, password)

        val result = db.insert(TABLE_USERS, null, values)
        db.close()

        return result != -1L
    }

    fun checkUser(name: String, password: String): Boolean {
        val db = readableDatabase
        val query = """
            SELECT * FROM $TABLE_USERS
            WHERE $COL_USER_NAME = ? AND $COL_USER_PASSWORD = ?
        """
        val cursor = db.rawQuery(query, arrayOf(name, password))

        val exists = cursor.moveToFirst()

        cursor.close()
        db.close()

        return exists
    }

    //Devices

    fun insertDevice(nombre: String, marca: String, watts: Int, tipo: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COL_DEV_NAME, nombre)
        values.put(COL_DEV_BRAND, marca)
        values.put(COL_DEV_WATTS, watts)
        values.put(COL_DEV_TYPE, tipo)

        val result = db.insert(TABLE_DEVICES, null, values)
        db.close()

        return result != -1L
    }
}
