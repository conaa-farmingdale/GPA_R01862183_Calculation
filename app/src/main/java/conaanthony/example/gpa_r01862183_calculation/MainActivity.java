package conaanthony.example.gpa_r01862183_calculation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    String finalGrade;
    TextView grade;
    EditText g1;
    EditText g2;
    EditText g3;
    EditText g4;
    EditText g5;
    View background;
    Button GPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grade = findViewById(R.id.ans);
        g1 = findViewById(R.id.grade1);
        g2 = findViewById(R.id.grade2);
        g3 = findViewById(R.id.grade3);
        g4 = findViewById(R.id.grade4);
        g5 = findViewById(R.id.grade5);
        GPA = findViewById(R.id.button);
        background = this.getWindow().getDecorView();
    }

    public void calculateGPA(View view) {

        //checks for
        if (GPA.getText().toString().equals(getResources().getString(R.string.clear))) {
            clearAll();
            return;
        }

        //Checks for empty field and calls error if there is an error
        if (check(g1)) {
            error(g1);
            return;
        } else if (check(g2)) {
            error(g2);
            return;
        } else if (check(g3)) {
            error(g3);
            return;
        } else if (check(g4)) {
            error(g4);
            return;
        } else if (check(g5)) {
            error(g5);
            return;
        }

        //Checks for if input is larger then 100 and calls error if there is an error
        if (check(Integer.parseInt(g1.getText().toString()))) {
            error(g1);
            return;
        } else if (check(Integer.parseInt(g2.getText().toString()))) {
            error(g2);
            return;
        } else if (check(Integer.parseInt(g3.getText().toString()))) {
            error(g3);
            return;
        } else if (check(Integer.parseInt(g4.getText().toString()))) {
            error(g4);
            return;
        } else if (check(Integer.parseInt(g5.getText().toString()))) {
            error(g5);
            return;
        }

        // calculates the finalGrade and stores value as a string
        int val = Integer.parseInt(g1.getText().toString());
        val += Integer.parseInt(g2.getText().toString());
        val += Integer.parseInt(g3.getText().toString());
        val += Integer.parseInt(g4.getText().toString());
        val += Integer.parseInt(g5.getText().toString());
        val = val / 5;
        finalGrade = Integer.toString(val);

        //Displays the grade and changes background color
        grade.setText(getResources().getString(R.string.gpa_display) + finalGrade);
        changeBackgroundColor(val);

    }
        //Resets the edit texts, colors and labels
    private void clearAll() {
        GPA.setText(getResources().getString(R.string.gpa_compute));
        g1.getText().clear();
        g1.setBackground(null);
        g2.getText().clear();
        g2.setBackground(null);
        g3.getText().clear();
        g3.setBackground(null);
        g4.getText().clear();
        g4.setBackground(null);
        g5.getText().clear();
        g5.setBackground(null);
        grade.setText(getResources().getString(R.string.gpa_display));
        background.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.background));
    }

    //returns true if TextView is empty
    public boolean check(TextView view) {
        return TextUtils.isEmpty(view.getText().toString());
    }
    //returns true if gradeCheck is larger then 100
    public boolean check(int gradeCheck) {
        return gradeCheck > 100;
    }

    //errors for empty field and larger then 100
    public void error(TextView view) {
        if (check(view)) {
            Toast.makeText(this, "Field is empty", Toast.LENGTH_LONG).show();
            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.fail));

        } else {
            Toast.makeText(this, "Incorrect value", Toast.LENGTH_LONG).show();
            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.fail));
        }

    }

    // changes background color based on finalGrade and changes button text
    public void changeBackgroundColor(int finalGrade) {
        if (finalGrade <= 60) {
            background.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.fail));
        } else if (finalGrade >= 61 && finalGrade <= 79) {
            background.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pass));
        } else if (finalGrade >= 80 && finalGrade <= 100) {
            background.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.excel));
        }

        GPA.setText(getResources().getString(R.string.clear));
    }
}
