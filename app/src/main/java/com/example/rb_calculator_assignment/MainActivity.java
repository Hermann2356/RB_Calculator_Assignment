package com.example.rb_calculator_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    MaterialTextView inputTextView,outputTextView;
    double runningTotal, input, temp;
    char operator;
    boolean compoundExpression;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.input_tv);
        outputTextView = findViewById(R.id.output_tv);
        runningTotal = 0;
        operator = '?';
        input = 0;
        compoundExpression = true;

        int[] numBtnId = new int[] {
                R.id.zero_btn, R.id.one_btn, R.id.two_btn, R.id.three_btn,
                R.id.four_btn, R.id.five_btn, R.id.six_btn, R.id.seven_btn,
                R.id.eight_btn, R.id.nine_btn, R.id.period_btn };

        int[] operandBtnId = new int[] {
                R.id.add_btn, R.id.subtract_btn,
                R.id.multiply_btn, R.id.division_btn };

        for(int id: numBtnId)
            setNumOnClick(id);

        for(int id : operandBtnId) {
            setOperandOnClick(id);
        }

        setClearOnClick();
        setDeleteOnClick();
        setPlusNegateOnClick();
        setPercentageOnClick();
    }

    /* Convert text (CharSequence) to a double */
    private double textToDouble(CharSequence text) {
        return Double.parseDouble(String.valueOf(text));
    }

    // Initialize on click listeners for numeric btn and period btn
    private void setNumOnClick(int id) {
        MaterialButton numBtn = findViewById(id);
        numBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString = String.valueOf(inputTextView.getText());
                if(textString.equals("0"))
                    inputTextView.setText(numBtn.getText());
                else if(textString.equals("-0"))
                    inputTextView.setText("-" + numBtn.getText());
                else
                    inputTextView.append(numBtn.getText());
            }
        });
    }

    /* Initialize on click listeners for operand btn  */
    public void setOperandOnClick(int id) {
        MaterialButton operandBtn = findViewById(id);
        operandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = operandBtn.getText().charAt(0);
                input = textToDouble(inputTextView.getText());

                if(compoundExpression) {
                    switch (operator) {
                        case '+' : {
                            runningTotal += input;
                            input = 0;
                            outputTextView.setText(String.valueOf(runningTotal));
                            inputTextView.setText("0");
                            Log.d("+", String.valueOf(runningTotal));
                            break;
                        }
                        case '-' : {
                            runningTotal -= input;
                            input = 0;
                            outputTextView.setText(String.valueOf(runningTotal));
                            inputTextView.setText("0");
                            break;
                        }
                        case 'x' : {
                            runningTotal *= input;
                            input = 0;
                            outputTextView.setText(String.valueOf(runningTotal));
                            inputTextView.setText("0");
                            break;
                        }
                        case '/' : {
                            runningTotal /= input;
                            input = 0;
                            outputTextView.setText(String.valueOf(runningTotal));
                            inputTextView.setText("0");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                else {
                    switch (operator) {
                        case '+' : {break;}
                        case '-' : {break;}
                        case 'x' : {break;}
                        case '/' : {break;}
                        default: {break;}
                    }
                }

                // not compoundExpression
                // switch inputText -> runningTotal
                // input = inputText
                // runningTotal (?) input
                // operator
            }
        });
    }

    private void setClearOnClick() {
        MaterialButton clearBtn = findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningTotal = 0;
                outputTextView.setText("");
                inputTextView.setText("0");
            }
        });
    }

    private void setDeleteOnClick() {
        MaterialButton delBtn = findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = String.valueOf(inputTextView.getText());
                if(inputString.length() == 2 && inputString.charAt(0) == '-') {
                    inputTextView.setText("0");
                }
                else if (inputString.length() == 1) {
                    inputTextView.setText("0");
                } else {
                    inputTextView.setText(inputString.substring(0, inputString.length() - 1));
                }
            }
        });
    }

    private void setPlusNegateOnClick() {
        MaterialButton plusNegateBtn = findViewById(R.id.plus_negate_btn);
        plusNegateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = String.valueOf(inputTextView.getText());
                if(inputString.charAt(0) == '-')
                    inputTextView.setText(inputString.substring(1));
                else {
                    if(!(inputString.length() == 1 && inputString.charAt(0) == 0))
                        inputTextView.setText("-" + inputString);
                }


                input = textToDouble(inputTextView.getText());
            }
        });
    }

    private void setPercentageOnClick() {
        MaterialButton percentageBtn = findViewById(R.id.percentage_btn);
        percentageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double percentageNum = textToDouble(inputTextView.getText()) * .01;
                inputTextView.setText(String.valueOf(percentageNum));
            }
        });
    }









}
