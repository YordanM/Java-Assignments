package com.company;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {

    public static String MinimumDif(JSONObject object) throws JSONException {

        JSONObject result = new JSONObject();

        //get n property
        int n = object.getInt("n");

        //checks if n is smaller than 2
        if (n < 2){
            result.put("error", "N must be larger than 1");
            return result.toString();
        }

        //getting th row string from each property
        String pntRow = String.valueOf(object.getJSONArray("pnt"));
        String pweRow = String.valueOf(object.getJSONArray("pwe"));

        //remove first and last chars '[' and ']'
        pntRow = pntRow.substring(1, pntRow.length() - 1);
        pweRow = pweRow.substring(1, pweRow.length() - 1);

        //split by ','
        String[] pntStringArr = pntRow.split(",");
        String[] pweStringArr = pweRow.split(",");


        int[] pnt = new int[pntStringArr.length];
        int[] pwe = new int[pweStringArr.length];

        //extract numbers from JSON array.
        for (int i = 0; i < pntStringArr.length; i++) {
            pnt[i] = Integer.parseInt(pntStringArr[i]);

        }
        for (int i = 0; i < pweStringArr.length; i++) {
            pwe[i] = Integer.parseInt(pweStringArr[i]);
        }

        //checks if n is larger than the given arrays
        if (pnt.length < n)
        {
            result.put("error", "length of pnt array is less than " + n);
            return result.toString();
        }

        if (pwe.length < n)
        {
            result.put("error", "length of pwe array is less than " + n);
            return result.toString();
        }

        int pntIndex = -1;
        int pweIndex = -1;

        int[] tempMinSumArr = new int[n];
        int[] pntArrN = new int[n];
        int[] pweArrN = new int[n];
        int[] minSumArr = new int[n];

        //set default value for each int
        for (int j = 0; j < minSumArr.length; j++)
        {
            minSumArr[j] = Integer.MAX_VALUE;
        }

        int currentSum = 0;
        int resultSum = Integer.MAX_VALUE;

        for (int i = 0; i < pnt.length - n; i++)
        {

            for (int j = i, y = 0; y < n; j++, y++)
            {
                pntArrN[y] = pnt[j];
            }

            for (int y = 0; y < pwe.length - n + 1; y++)
            {

                for (int j = y, r = 0; r < n; j++, r++)
                {
                    pweArrN[r] = pwe[j];
                }

                for (int k = 0; k < n; k++)
                {
                    tempMinSumArr[k] = Math.abs(pntArrN[k] - pweArrN[k]);
                }

                for (int j = 0; j < tempMinSumArr.length; j++)
                {
                    currentSum += tempMinSumArr[j];
                }

                if (resultSum > currentSum){
                    resultSum = currentSum;
                    pntIndex = i;
                    pweIndex = y;

                }

                currentSum = 0;
            }

        }

        //adding properties to JSON
        result.put("d.min", resultSum);
        result.put("s.pnt", pntIndex);
        result.put("s.pwe", pweIndex);

        return result.toString();
    }

    public static void main(String[] args) throws JSONException {

        Scanner scanner = new Scanner(System.in);

        //read JSON
        String jsonReader = scanner.nextLine();
        JSONObject object = new JSONObject(jsonReader);

        //get end JSON
        String result =  MinimumDif(object);

        //write end JSON
        System.out.println(result);
    }
}
