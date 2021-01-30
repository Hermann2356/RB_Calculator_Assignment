package com.example.rb_calculator_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOperandBtnClick(R.id.clear_btn);
        setOperandBtnClick(R.id.add_btn);
        setOperandBtnClick(R.id.subtract_btn);
        setOperandBtnClick(R.id.percentage_btn);
        setOperandBtnClick(R.id.multiply_btn);
        setOperandBtnClick(R.id.division_btn);
        setOperandBtnClick(R.id.plus_minus_btn);
        setOperandBtnClick(R.id.equal_btn);
        
        
        setNumBtnClick(R.id.one_btn);
        setNumBtnClick(R.id.two_btn);
        setNumBtnClick(R.id.three_btn);
        setNumBtnClick(R.id.four_btn);
        setNumBtnClick(R.id.five_btn);
        setNumBtnClick(R.id.six_btn);
        setNumBtnClick(R.id.seven_btn);
        setNumBtnClick(R.id.eight_btn);
        setNumBtnClick(R.id.nine_btn);
        setNumBtnClick(R.id.zero_btn);
        setNumBtnClick(R.id.period_btn);
        
    }
    private void setOperandBtnClick(int id) {

        

        MaterialButton btn  = findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append(output, btn.getText());
            }
        });
    }
    
    private void setNumBtnClick(int id) {
        MaterialButton btn  = findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append(output, btn.getText());
            }
        });
    }

    private void append(TextView view, CharSequence text) {
        view.append(text);
    }

    private void delete(TextView view) {
        if(view.getText().toString() != null) {
            view.setText(view.toString().substring(0, view.length() - 1));
            Log.d("delete method", "true");
        }
        else {
            view.setText("0");
        }
    }
    

    class Calculator {
        private double operand1;
        private double operand2;
        private double total;

        public Calculator() {
            this(0, 0);
            total = 0;
        }

        public Calculator(double operand1, double operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        public double getOperand1() {
            return operand1;
        }

        public void setOperand1(double operand1) {
            this.operand1 = operand1;
        }

        public double getOperand2() {
            return operand2;
        }

        public void setOperand2(double operand2) {
            this.operand2 = operand2;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double add(double operand1, double operand2) {
            return total += operand1 + operand2;

        }

        public double subtract(double operand1, double operand2) {
            return total -= operand1 - operand2;
        }

        public double divide(double operand1, double operand2) {
            return total /= operand1 / operand2;
        }

        public double multiple(double operand1, double operand2) {
            return total *= operand1 * operand2;
        }

        public void calculate(double operand1, double operand2) {
            
        }

        public void clear() {

        }


    }
}