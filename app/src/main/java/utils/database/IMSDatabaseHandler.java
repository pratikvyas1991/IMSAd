package utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.util.Log;

import com.ims.tasol.ims.model.CustomerDetails;
import com.ims.tasol.ims.model.Users;

import java.lang.reflect.Field;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by tasol on 14/3/17.
 */

public class IMSDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="imsDataBase";
    private static final int DATABASE_VERSION=1;
    Context context;

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public void setColumnNameList(ArrayList<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public void setColumnTypeList(ArrayList<String> columnTypeList) {
        this.columnTypeList = columnTypeList;
    }

    private  String TABLE_NAME=null;
    private ArrayList<String> columnNameList=new ArrayList<>();
    private ArrayList<String> columnTypeList=new ArrayList<>();





    public IMSDatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String column="";
//        if(columnNameList!=null&&columnNameList.size()>0){
//            for (int d = 0; d < columnNameList.size(); d++) {
//                if(d==columnNameList.size()-1){
//                    column=column+columnNameList.get(d)+" "+columnTypeList.get(d);
//                }else{
//                    column=column+columnNameList.get(d)+" "+columnTypeList.get(d)+" ,";
//                }
//            }
//        }
//        String TABLE_CREATE="CREATE TABLE "+TABLE_NAME+"("+column+")";
//        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    //Add The Rows to the Taable
    public void addTableRow(String tableName,ArrayList<String>keys,ArrayList<String>values){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        for (int k = 0; k < keys.size(); k++) {
            contentValues.put(keys.get(k),values.get(k));
        }
        database.insert(tableName,null,contentValues);
        database.close();
    }

    public void addTableRowFromHash(String tableName,HashMap<String,String>rows){
        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        Log.v("@@WWE","HAsh Map Key And Value");
        Set keys=rows.keySet();
        for (Iterator i=keys.iterator();i.hasNext();){
            String key=(String)i.next();
            String value=(String)rows.get(key);
            contentValues.put(key,value);
            Log.v("@@WWE","Key : "+key+" Value : "+value);
        }
        database.insert(tableName,null,contentValues);
        Log.v("@@@WWE","Record Added");
        database.close();
    }

    public void displayAllRows(String tableName){
        String selectQuery="SELECT * FROM "+tableName;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                for (int h = 0; h < columnNameList.size(); h++) {
                    if(columnTypeList.get(h).equals("TEXT")){
                        Log.v("@@WE "," VALUE : "+cursor.getString(h));
                    }
                }

            }while (cursor.moveToNext());
        }
    }

    public boolean isLoggedIn(String tableName,String key){
        HashMap<String,String> row= new HashMap<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(tableName,new String[]{"isLoggedIn"},"userName = ?",new String[]{key},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            if(cursor.getString(0).equals("false")){
                return false;
            }else {
                return true;
            }
        }

       return false;
    }

    public void validateAutoLogin(String tablename,String userType){
//        String selectQuery="SELECT "
    }

    public void deleteRecord(String tableName,String keyValue){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(tableName,columnNameList.get(0)+" =?",new String[]{String.valueOf(keyValue)});
                db.close();
    }
    public void updateRecord(String tableName,String valueKey,String valueValue,String userName){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(valueKey,valueValue);
        int i=db.update(tableName,values,"userName = ?",new String[]{String.valueOf(userName)} );
        if(i==0){
            Log.v("@@@WE"," updation failed");
        }else{
            Log.v("@@@WE"," updated sucessfully");
        }
        db.close();
    }

    public void deleteTable(String tableName){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        Log.v("@@@WWE","Table Dropped");
    }
    public void deleteDatabase(){
        context.deleteDatabase("imsDataBase");
    }

    public void getTableList(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                Log.v("@WWE","Table Name=> "+c.getString(0));
                c.moveToNext();
            }
        }
    }

    public void addTable(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String column="";
        if(columnNameList!=null&&columnNameList.size()>0){
            for (int d = 0; d < columnNameList.size(); d++) {
                if(d==columnNameList.size()-1){
                    column=column+columnNameList.get(d)+" "+columnTypeList.get(d);
                }else{
                    column=column+columnNameList.get(d)+" "+columnTypeList.get(d)+" ,";
                }
            }
        }
        Log.v("@@TABLES"," Table Values");
        Log.v("@@TABLES"," Column  Name List "+column);
        String TABLE_CREATE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+column+")";
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    public int countAllRows(String tableName){
        int count=0;
        String selectQuery="SELECT * FROM "+tableName;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                count++;
                for (int h = 0; h < columnNameList.size(); h++) {
                    if(columnTypeList.get(h).equals("TEXT")){
                        Log.v("@@WE "," VALUE : "+cursor.getString(h));
                    }
                }

            }while (cursor.moveToNext());
        }
        return count;
    }

    public ArrayList<CustomerDetails> getAllCustomerRows(String tableName){

        ArrayList<CustomerDetails> allRec= new ArrayList<>();
        CustomerDetails details= new CustomerDetails();
        allRec.clear();
        String selectQuery="SELECT * FROM "+tableName;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        int rowSize=countAllRows(tableName);

        cursor.moveToFirst();
        CustomerDetails detailsF;
        if(cursor.moveToFirst()) {
            do {
                detailsF = new CustomerDetails();
                detailsF.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
                detailsF.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
                detailsF.setCustAge(cursor.getString(cursor.getColumnIndex("custAge")));
                detailsF.setCustGender(cursor.getString(cursor.getColumnIndex("custGender")));
                detailsF.setCustReason(cursor.getString(cursor.getColumnIndex("custReason")));
                detailsF.setCustRelativeName(cursor.getString(cursor.getColumnIndex("custRelativeName")));
                detailsF.setCustRelativeRelation(cursor.getString(cursor.getColumnIndex("custRelativeRelation")));
                detailsF.setCustRelativeMobileNumber(cursor.getString(cursor.getColumnIndex("custRelativeMobileNumber")));
                detailsF.setCustRelativeAddress(cursor.getString(cursor.getColumnIndex("custRelativeAddress")));
                detailsF.setCustTimeOfBill(cursor.getString(cursor.getColumnIndex("custTimeOfBill")));
                detailsF.setCustDateOfBill(cursor.getString(cursor.getColumnIndex("custDateOfBill")));

                allRec.add(detailsF);
            } while (cursor.moveToNext());
        }

        CustomerDetails details1= new CustomerDetails();

        for (int w = 0; w < allRec.size(); w++) {
            details1=allRec.get(w);
            Log.v("@@@CUST"," CUST ID "+details1.getCustID()+" Cust Name "+details1.getCustName());
        }


        return allRec;
    }

    public CustomerDetails getSingleCustomerRows(String tableName,String custID){

        CustomerDetails details= new CustomerDetails();
        String selectQuery="SELECT * FROM "+tableName+" WHERE custID = "+custID;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        int rowSize=countAllRows(tableName);

        cursor.moveToFirst();
        CustomerDetails detailsF=null;
        if(cursor.moveToFirst()) {
            do {
                detailsF = new CustomerDetails();
                detailsF.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
                detailsF.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
                detailsF.setCustAge(cursor.getString(cursor.getColumnIndex("custAge")));
                detailsF.setCustGender(cursor.getString(cursor.getColumnIndex("custGender")));
                detailsF.setCustReason(cursor.getString(cursor.getColumnIndex("custReason")));
                detailsF.setCustRelativeName(cursor.getString(cursor.getColumnIndex("custRelativeName")));
                detailsF.setCustRelativeRelation(cursor.getString(cursor.getColumnIndex("custRelativeRelation")));
                detailsF.setCustRelativeMobileNumber(cursor.getString(cursor.getColumnIndex("custRelativeMobileNumber")));
                detailsF.setCustRelativeAddress(cursor.getString(cursor.getColumnIndex("custRelativeAddress")));
                detailsF.setCustTimeOfBill(cursor.getString(cursor.getColumnIndex("custTimeOfBill")));
                detailsF.setCustDateOfBill(cursor.getString(cursor.getColumnIndex("custDateOfBill")));

            } while (cursor.moveToNext());
        }


        return detailsF;
    }

    //Is Table Exist
    public boolean isTableExist(String tableName){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                if(c.getString(0).equals(tableName)){
                    Log.v("@WWE","Table Name=> "+c.getString(0));
                    return true;
                }
                c.moveToNext();
            }
        }
        return false;
    }

    public void copyTableValue(String source,String destination){
        SQLiteDatabase db=this.getWritableDatabase();
        String string="INSERT INTO "+destination+" SELECT * FROM "+source+";";
        db.execSQL(string);
    }

    public void deletValuesFromTable(String tableName){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ tableName);
        Log.v("@@@TAB"," Table "+tableName+" is empty now");
    }


    public ArrayList<CustomerDetails> getAllCustomerRowsDateSorted(String tableName,String startDate,String endDate){

        ArrayList<CustomerDetails> allRec= new ArrayList<>();
        CustomerDetails details= new CustomerDetails();
        allRec.clear();
        String selectQuery="select * from " + tableName + " where custDateOfBill BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY custDateOfBill ASC,"+null+";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        int rowSize=countAllRows(tableName);

        cursor.moveToFirst();
        CustomerDetails detailsF;
        if(cursor.moveToFirst()) {
            do {
                detailsF = new CustomerDetails();
                detailsF.setCustID(cursor.getString(cursor.getColumnIndex("custID")));
                detailsF.setCustName(cursor.getString(cursor.getColumnIndex("custName")));
                detailsF.setCustAge(cursor.getString(cursor.getColumnIndex("custAge")));
                detailsF.setCustGender(cursor.getString(cursor.getColumnIndex("custGender")));
                detailsF.setCustReason(cursor.getString(cursor.getColumnIndex("custReason")));
                detailsF.setCustRelativeName(cursor.getString(cursor.getColumnIndex("custRelativeName")));
                detailsF.setCustRelativeRelation(cursor.getString(cursor.getColumnIndex("custRelativeRelation")));
                detailsF.setCustRelativeMobileNumber(cursor.getString(cursor.getColumnIndex("custRelativeMobileNumber")));
                detailsF.setCustRelativeAddress(cursor.getString(cursor.getColumnIndex("custRelativeAddress")));
                detailsF.setCustTimeOfBill(cursor.getString(cursor.getColumnIndex("custTimeOfBill")));
                detailsF.setCustDateOfBill(cursor.getString(cursor.getColumnIndex("custDateOfBill")));

                allRec.add(detailsF);
            } while (cursor.moveToNext());
        }

        CustomerDetails details1= new CustomerDetails();

        for (int w = 0; w < allRec.size(); w++) {
            details1=allRec.get(w);
            Log.v("@@@Date Sorted"," CUST ID "+details1.getCustID()+" Cust Name "+details1.getCustName());
        }


        return allRec;
    }


    public void trialCheck(){
//        SQLiteDatabase db=this.getWritableDatabase();
//        String createQuery="CREATE TABLE userscheck(" +
//                "    id INTEGER PRIMARY KEY," +
//                "    username TEXT," +
//                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
//                ");";
//        db.execSQL(createQuery);
//
//        Log.v("@@@TAB","Trial Table Created");
//
//        Log.v("@@@TAB","Trial Table Created");
//        db.execSQL("INSERT INTO userscheck(username, created_at) VALUES('vyas','datetime()'");
    }

}
