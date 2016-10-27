package com.example.zachcheu.carboncredit;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHelper {
    final static String fileName = "/data.txt";
    final static String path = Environment.getExternalStorageDirectory() + "/files" ;
    final static String TAG = FileHelper.class.getName();

    public static ArrayList<Integer> ReadCarbon(Context context){
        String line = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int start = 0;
        int end = 0;
        int spaceIndex = 1;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                for(int i = 0; i<spaceIndex;i++){
                    if(end == 0){
                        start = 0;
                    }else{
                        start = end+1;
                    }
                    end = line.indexOf(' ',start+1);
                }
                list.add(Integer.parseInt(line.substring(start,end)));
                start = 0;
                end = 0;
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return list;
    }
    public static ArrayList<Long> ReadDriveTime(Context context){
        String line = null;
        ArrayList<Long> list = new ArrayList<Long>();
        int start = 0;
        int end = 0;
        int spaceIndex = 2;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                for(int i = 0; i<spaceIndex;i++){
                    if(end == 0){
                        start = 0;
                    }else{
                        start = end+1;
                    }
                    end = line.indexOf(' ',start+1);
                }
                list.add(Long.valueOf(line.substring(start,end)));
                start = 0;
                end = 0;
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return list;
    }
    public static ArrayList<Long> ReadDistance(Context context){
        String line = null;
        ArrayList<Long> list = new ArrayList<Long>();
        int start = 0;
        int end = 0;
        int spaceIndex = 3;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                for(int i = 0; i<spaceIndex;i++){
                    if(end == 0){
                        start = 0;
                    }else{
                        start = end+1;
                    }
                    end = line.indexOf(' ',start+1);
                }
                list.add(Long.valueOf(line.substring(start,end)));
                start = 0;
                end = 0;
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return list;
    }
    public static ArrayList<Long> ReadTime(Context context){
        String line = null;
        ArrayList<Long> list = new ArrayList<Long>();
        int start = 0;
        int end = 0;
        int spaceIndex = 4;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
                for(int i = 0; i<spaceIndex;i++){
                    if(end == 0){
                        start = 0;
                    }else{
                        start = end+1;
                    }
                    end = line.indexOf(' ',start+1);
                }
                list.add(Long.valueOf(line.substring(start,end)));
                start = 0;
                end = 0;
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return list;
    }

    public static boolean saveToFile(String data){
        try {
            new File(path).mkdir();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;


    }
}