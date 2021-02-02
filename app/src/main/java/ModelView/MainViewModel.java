package ModelView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rb_calculator_assignment.Util.Calculator;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Double> _results = new MutableLiveData<>();
    private Calculator calculator = new Calculator();

    public LiveData<Double> getResults() {
        return _results;
    }

    public void calculateExpression(String operator, double operand1, double operand2) {
        if(operator != null) {
            double solution = 0;
            switch(operator) {
                case "+":  {
                    solution = calculator.add(operand1, operand2);
                    break;
                }
                case "-":  {
                     solution = calculator.subtract(operand1, operand2);
                    break;
                }
                case "*":  {
                    solution = calculator.multiply(operand1, operand2);
                    break;}
                case "/":  {
                    solution = calculator.divide(operand1, operand2);
                    break;
                }
                default: {

                }
            }
            _results.setValue(solution);
        }

    }

    public void calculatePercentage(double operand1) {
        _results.setValue(calculator.percentage(operand1));
    }

    public void plusNegate(double operand1) {
         _results.setValue(calculator.plusNegate(operand1));
    }

    public void clearResults() {
        _results.setValue(0d);
    }


}
