package com.raywenderlich.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTxt = findViewById(R.id.textview);
        myTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayChallenge(new int[]{6, 0});
            }
        });

        arrayChallenge(new int[]{12, 2, 6, 7, 11});
        arrayChallenge(new int[]{6, 4});
        arrayChallenge(new int[]{8, 1, 8});
        arrayChallenge(new int[]{2, 0});
        arrayChallenge(new int[]{4, 0});
        arrayChallenge(new int[]{6, 0});
    }

    private void arrayChallenge(int[] arr) {
        if (arr.length < 2)
            return;
        ArrayList<Integer> intList = new ArrayList<>(arr.length);
        ArrayList<Integer> listInvalidDesk = new ArrayList<>(arr.length - 1);
        Set<Pair<Integer, Integer>> listPairResult = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            //inclue K
            intList.add(arr[i]);

            //without K
            if (i != 0) {
                listInvalidDesk.add(arr[i]);
            }
        }

        int numberOfDesks = intList.get(0); //K number

        for (int desk = 1; desk <= numberOfDesks; desk++) {
            if (isValidDesk(listInvalidDesk, numberOfDesks, desk)) {
                listPairResult.addAll(validNeighborSit(listInvalidDesk, numberOfDesks, desk));
            }
        }
        Log.e("mainActivity", "Input: " + intList);
        Log.e("mainActivity", "List Pair: " + listPairResult);
        Log.e("mainActivity", "Result: " + listPairResult.size());
    }

    private boolean isValidDesk(ArrayList<Integer> listDesk, int numberOfDesks, int desk) {
        if (desk <= 0 || desk > numberOfDesks) {
            return false;
        }
        for (int item : listDesk) {
            if (desk == item)
                return false;
        }
        return true;
    }

    private Set<Pair<Integer, Integer>> validNeighborSit(ArrayList<Integer> listDesk, int numberOfDesks, int desk) {
        int topDesk = desk - 2;
        int bottomDesk = desk + 2;
        int nextDesk = desk % 2 != 0 ? desk + 1 : desk - 1;

        Set<Pair<Integer, Integer>> listPairValidDesk = new HashSet<Pair<Integer, Integer>>();
        if (isValidDesk(listDesk, numberOfDesks, topDesk)) {
            listPairValidDesk.add(new Pair<Integer, Integer>(topDesk, desk));
        }

        if (isValidDesk(listDesk, numberOfDesks, bottomDesk)) {
            listPairValidDesk.add(new Pair<Integer, Integer>(desk, bottomDesk));
        }

        if (isValidDesk(listDesk, numberOfDesks, nextDesk)) {
            if (desk % 2 != 0) //odd number
                listPairValidDesk.add(new Pair<Integer, Integer>(desk, nextDesk));
            else //even number
                listPairValidDesk.add(new Pair<Integer, Integer>(nextDesk, desk));
        }
        return listPairValidDesk;
    }
}