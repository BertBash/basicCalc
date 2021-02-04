package com.example.basiccalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;
import java.util.Vector;

import android.content.ClipboardManager;

public class MainActivity extends AppCompatActivity {

    private TextView textDisplay;
    private Stack<Double> operands = new Stack<Double>();
    private Stack<String> operators = new Stack<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDisplay = findViewById(R.id.calcText);
        textDisplay.setShowSoftInputOnFocus(false);
    }

    //Add text to the screen
    private void addText(String s){
        String prevS = textDisplay.getText().toString();
        textDisplay.setText(String.format("%s%s", prevS, s));

    }

    //Number buttons write to the screen
    public void zeroButton(View view){
        addText("0");
    }

    public void oneButton(View view){
        addText("1");
    }

    public void twoButton(View view){
        addText("2");
    }

    public void threeButton(View view){
        addText("3");
    }

    public void fourButton(View view){
        addText("4");
    }

    public void fiveButton(View view){
        addText("5");
    }

    public void sixButton(View view){
        addText("6");
    }

    public void sevenButton(View view){
        addText("7");
    }

    public void eightButton(View view){
        addText("8");
    }

    public void nineButton(View view){
        addText("9");
    }

    public void decimalButton(View view){
        if(!textDisplay.getText().toString().contains(".")){
            addText(".");
        }
    }

    public void clearButton(View view){
        if(textDisplay.getText().toString().equals("")){
            operands.clear();
            operators.clear();
        }
        textDisplay.setText("");
    }

    //Operator buttons add the current number and their operators to the operation stacks
    public void addButton(View view){
        if (textDisplay.getText() != "") {
            Double o = Double.parseDouble(textDisplay.getText().toString());
            operands.add(o);
            operators.add("+");
            textDisplay.setText("");
        }
    }

    public void minusButton(View view){
        if (textDisplay.getText() != "") {
            Double o = Double.parseDouble(textDisplay.getText().toString());
            operands.add(o);
            operators.add("-");
            textDisplay.setText("");
        }
    }

    public void timesButton(View view){
        if (textDisplay.getText() != "") {
            Double o = Double.parseDouble(textDisplay.getText().toString());
            operands.add(o);
            operators.add("*");
            textDisplay.setText("");
        }
    }

    public void divideButton(View view){
        if (textDisplay.getText() != "") {
            Double o = Double.parseDouble(textDisplay.getText().toString());
            operands.add(o);
            operators.add("/");
            textDisplay.setText("");
        }
    }

    public void equalButton(View view){
        Double output = 0.0;
        String outputString = "";
        if (textDisplay.getText() !="") {
            while (operands.size() > 0) {

                //Gather operators and operands
                Double op2 = Double.parseDouble(textDisplay.getText().toString());
                Double op1 = operands.pop();
                String opra = operators.pop();

                //Parse operations
                if (opra.equals("+")) {
                    output = op1 + op2;
                } else if (opra.equals("-")) {
                    output = op1 - op2;
                } else if (opra.equals("*")) {
                    output = op1 * op2;
                } else if (opra.equals("/")) {
                    output = op1 / op2;
                }

                //Write to the screen
                outputString = String.format("%f", output);

                //Strip trailing zeroes for readability
                int i = outputString.length()-1;
                while (outputString.charAt(i) == '0'){
                    outputString = outputString.substring(0, outputString.length()-1);
                    i--;
                }
                if (outputString.charAt(outputString.length()-1) == '.'){
                    outputString = outputString.substring(0, outputString.length()-1);
                }
                textDisplay.setText(outputString);
            }
        }
    }

    public void copyButton(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService((Context.CLIPBOARD_SERVICE));
        ClipData clip = ClipData.newPlainText("simple text", textDisplay.getText().toString());
        clipboard.setPrimaryClip(clip);
    }
}