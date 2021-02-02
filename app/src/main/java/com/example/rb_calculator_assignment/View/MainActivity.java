package com.example.rb_calculator_assignment.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rb_calculator_assignment.R;
import com.example.rb_calculator_assignment.Util.ConvertUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.textview.MaterialTextView;

import ModelView.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MaterialTextView tvOutput;
    MainViewModel viewModel;
    double num1, num2;
    String operator;
    MaterialButton activeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.output_tv);
        operator = "";
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);
        setNumOnClick();
        setOperandClick();
        setClearOnClick();
        setDeleteOnClick();
        setEqualOnClick();
        setPlusNegate();

        viewModel.getResults().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double results) {
                String resultString = results.toString();
                tvOutput.setText(resultString);
            }
        });

    }




    // Initialize on click listeners for numeric btn and period btn
    private void setNumOnClick() {

        int[] numBtnId = new int[] {
                R.id.zero_btn, R.id.one_btn, R.id.two_btn, R.id.three_btn,
                R.id.four_btn, R.id.five_btn, R.id.six_btn, R.id.seven_btn,
                R.id.eight_btn, R.id.nine_btn, R.id.period_btn};

        for (int id : numBtnId) {
            MaterialButton btn = findViewById(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tvOutput.getText().toString().equals("0"))
                        tvOutput.setText(btn.getText());
                    else
                        tvOutput.append(btn.getText());
                }
            });
        }
    }

    private void setOperandClick() {
        int[] operandBtnId = new int[] {
                R.id.period_btn, R.id.add_btn, R.id.subtract_btn,
                R.id.multiply_btn, R.id.division_btn
        };

        for (int id : operandBtnId) {
            MaterialButton btn = findViewById(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = btn.getText().toString();
                    num1 = ConvertUtils.getDouble(tvOutput.getText());
                    tvOutput.setText("0");

                    if(activeBtn != null)
                        activeBtn.setBackgroundColor(getResources()
                            .getColor(R.color.design_default_color_on_primary));

                    btn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    activeBtn = btn;
                }
            });

        }

    }

    private void setEqualOnClick() {
        MaterialButton equalBtn = findViewById(R.id.equal_btn);
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = ConvertUtils.getDouble(tvOutput.getText());
                viewModel.calculateExpression(operator, num1, num2);
                activeBtn.setBackgroundColor(getResources().
                        getColor(R.color.design_default_color_on_primary));
            }
        });
    }

    private void setPlusNegate() {
        MaterialButton plusNegateBtn = findViewById(R.id.plus_negate_btn);
        plusNegateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double output = ConvertUtils.getDouble(tvOutput.getText());
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
                operator = "";
                activeBtn.setBackgroundColor(getResources().
                        getColor(R.color.design_default_color_on_primary));
            }
        });
    }

    private void setDeleteOnClick() {
        MaterialButton btn = findViewById(R.id.delete_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence outputTxt = tvOutput.getText();
                if(outputTxt.length() == 1)
                    tvOutput.setText("0");
                else if(outputTxt.length() == 2 && outputTxt.toString().charAt(0) == '-')
                    tvOutput.setText("0");
                else
                    tvOutput.setText(outputTxt.toString().substring(0, outputTxt.length() - 1));
            }
        });
    }

    private void resetActiveBtn() {

    }



}
