package com.tickepaymentsystem.cmov.customerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.Models.Voucher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

    /*public static final String DATABASE_NAME = "Local.db";

    // schema version 1
    private static final int SCHEMA_VERSION = 1;

    // singleton instance of the database
    private static Database INSTANCE;

    // used set to false
    private static final int AVAILABLE_FALSE = 0;

    // used set to true
    private static final int AVAILABLE_TRUE = 1;

    private static final String TICKETS_TABLE = "tickets";
    private static final String VOUCHERS_TABLE = "vouchers";

    private static final String USER_ID = "userid";

    private static final String TICKET_ID = "id";
    private static final String TICKET_NAME = "name";
    private static final String TICKET_DATE = "date";
    private static final String TICKET_SN = "seatnumber";
    private static final String TICKET_PRICE = "price";
    private static final String TICKET_AVAILABLE = "available";

    private static final String VOUCHER_ID = "id";
    private static final String VOUCHER_TYPE = "type";
    private static final String VOUCHER_DISCOUNT = "discount";
    private static final String VOUCHER_AVAILABLE = "available";


    public static synchronized Database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Database(context.getApplicationContext());
        }

        return INSTANCE;
    }

    private Database(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TICKETS_TABLE + " (" +
                TICKET_ID + " TEXT unique, " +
                USER_ID + " TEXT, " +
                TICKET_NAME + " TEXT, " +
                TICKET_DATE + " TEXT, " +
                TICKET_SN + " INTEGER, " +
                TICKET_PRICE + " DOUBLE, " +
                TICKET_AVAILABLE + " INTEGER);");

        db.execSQL("CREATE TABLE " + VOUCHERS_TABLE + " (" +
                VOUCHER_ID + " TEXT unique, " +
                USER_ID + " TEXT, " +
                VOUCHER_TYPE + " TEXT, " +
                VOUCHER_DISCOUNT + " DOUBLE, " +
                VOUCHER_AVAILABLE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void dropAllTables() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TICKETS_TABLE, null, null);
        db.delete(VOUCHERS_TABLE, null, null);
    }

    public static boolean checkDataBase(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }


    public synchronized void addTicket(Context context, Ticket ticket) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        try {
            User user = User.loadLoggedinUser(User.LOGGEDIN_USER_PATH, context);

            ContentValues values = new ContentValues();
            values.put(TICKET_ID, ticket.getId());
            values.put(USER_ID, user.getId());
            values.put(TICKET_NAME, ticket.getName());
            values.put(TICKET_DATE, ticket.getDate());
            values.put(TICKET_SN, ticket.getSeatNumber());
            values.put(TICKET_PRICE, ticket.getPrice());
            values.put(TICKET_AVAILABLE, AVAILABLE_TRUE);

            db.insert(TICKETS_TABLE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("http", "Error while trying to add ticket to database");
        } finally {
            db.endTransaction();
        }
    }


   public synchronized List<Ticket> getAllTickets(Context context) {
    }

    public synchronized void updateTicket(Ticket ticket) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            int available = -1;
            if(ticket.isAvailable()) {
                available = AVAILABLE_TRUE;
            } else {
                available = AVAILABLE_FALSE;
            }

            values.put(TICKET_AVAILABLE, available);

            String ticketId = ticket.getId();
            String[] args = {ticketId};

            db.update(TICKETS_TABLE, values,"id=?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("http", "Error while trying to update ticket on the database");
        } finally {
            db.endTransaction();
        }
    }

    public synchronized void deleteTicket(Context context, Ticket ticket) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        try {
            String[] args = {ticket.getId()};

            db.delete(TICKETS_TABLE, "id=?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("http", "Error while trying to delete ticket from the database");
        } finally {
            db.endTransaction();
        }
    }

    public synchronized void addVoucher(Context context, Voucher voucher) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures consistency of the database.
        db.beginTransaction();
        try {
            User user = User.loadLoggedinUser(User.LOGGEDIN_USER_PATH, context);

            ContentValues values = new ContentValues();
            values.put(VOUCHER_ID, voucher.getId());
            values.put(USER_ID, user.getId());
            values.put(VOUCHER_TYPE, voucher.getType());
            values.put(VOUCHER_DISCOUNT, voucher.getDiscount());
            values.put(VOUCHER_AVAILABLE, AVAILABLE_TRUE);

            db.insert(VOUCHERS_TABLE, null, values);

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("http", "Error while trying to add voucher to database");
        } finally {
            db.endTransaction();
        }
    }

    public synchronized List<Voucher> getVouchers(Context context) {
        List<Voucher> vouchers = new ArrayList<>();
        User user = User.loadLoggedinUser(User.LOGGEDIN_USER_PATH, context);

        String VOUCHERS_SELECT_QUERY = "SELECT * FROM " + VOUCHERS_TABLE +
                " WHERE " + USER_ID + " = '" + user.getId() + "'" +
                " ORDER BY " + VOUCHER_TYPE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(VOUCHERS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(VOUCHER_ID));
                    String type = cursor.getString(cursor.getColumnIndex(VOUCHER_TYPE));
                    double discount = cursor.getDouble(cursor.getColumnIndex(VOUCHER_DISCOUNT));
                    int available = cursor.getInt(cursor.getColumnIndex(VOUCHER_AVAILABLE));

                    Voucher voucher = new Voucher(id, type, discount);

                    if (available == AVAILABLE_FALSE) {
                        voucher.setAvailable(false);
                    }

                    vouchers.add(voucher);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("http", "Error while trying to get all vouchers from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return vouchers;
    }

    // TODO - Implement
    public synchronized void deleteVoucher(Context context, Voucher voucher) {

    }
}*/
