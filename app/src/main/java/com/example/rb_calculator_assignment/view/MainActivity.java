package com.example.rb_calculator_assignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rb_calculator_assignment.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import modelview.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MaterialTextView tvOutput;
    MainViewModel viewModel;
    double operand1, operand2;
    String operator;
    MaterialButton activeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        viewModel.getResults().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double results) {
                String resultString = results.toString();
                tvOutput.setText(resultString);
            }
        });

    }

    private void initView() {
        tvOutput = findViewById(R.id.output_tv);
        operator = null;
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);
        setPercentageOnClick();
        setNumOnClick();
        setOperandClick();
        setClearOnClick();
        setDeleteOnClick();
        setEqualOnClick();
        setPlusNegate();
    }

    private void setNumOnClick() {

        int[] numBtnId = new int[]{
                R.id.zero_btn, R.id.one_btn, R.id.two_btn, R.id.three_btn,
                R.id.four_btn, R.id.five_btn, R.id.six_btn, R.id.seven_btn,
                R.id.eight_btn, R.id.nine_btn, R.id.period_btn};

        for (int id : numBtnId) {
            MaterialButton btn = findViewById(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvOutput.getText().toString().equals("0"))
                        tvOutput.setText(btn.getText());
                    else
                        tvOutput.append(btn.getText());
                }
            });
        }
    }

    private void setOperandClick() {
        int[] operandBtnId = new int[]{
                R.id.period_btn, R.id.add_btn, R.id.subtract_btn,
                R.id.multiply_btn, R.id.division_btn
        };

        for (int id : operandBtnId) {
            MaterialButton btn = findViewById(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = btn.getText().toString();
                    operand1 = viewModel.textToDouble(tvOutput.getText());
                    tvOutput.setText("0");

                    if (activeBtn != null)
                        resetActiveBtn();
                    
                    btn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    activeBtn = btn;
                }
            });

        }

    }

    private void setPercentageOnClick() {
        MaterialButton btn = findViewById(R.id.percentage_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.calculatePercentage(viewModel.textToDouble(tvOutput.getText()));
            }
        });
    }

    private void setEqualOnClick() {
        MaterialButton equalBtn = findViewById(R.id.equal_btn);
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand2 = viewModel.textToDouble(tvOutput.getText());
                viewModel.calculateExpression(operator, operand1, operand2);

                if(activeBtn != null)
                    resetActiveBtn();
            }
        });
    }

    private void setPlusNegate() {
        MaterialButton plusNegateBtn = findViewById(R.id.plus_negate_btn);
        plusNegateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double output = viewModel.textToDouble(tvOutput.getText());
                viewModel.plusNegate(output);
            }
        });
    }

    private void setClearOnClick() {
        MaterialButton equalBtn = findViewById(R.id.clear_btn);
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOutput.setText("0");
                operator = null;
                resetActiveBtn();
            }
        });
    }

    private void setDeleteOnClick() {
        MaterialButton btn = findViewById(R.id.delete_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence outputTxt = tvOutput.getText();
                if (outputTxt.length() == 1)
                    tvOutput.setText("0");
                else if (outputTxt.length() == 2 && outputTxt.toString().charAt(0) == '-')
                    tvOutput.setText("0");
                else
                    tvOutput.setText(outputTxt.toString().substring(0, outputTxt.length() - 1));
            }
        });
    }

    private void resetActiveBtn() {
        activeBtn.setBackgroundColor(getResources().
                getColor(R.color.design_default_color_on_primary));
    }
}
