package playing.kolade.com.instagramcloneagain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtSpeed, edtPower;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtPower = findViewById(R.id.edtPower);
        edtSpeed = findViewById(R.id.edtSpeed);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
    }


        @Override
        public void onClick (View v) {

            try {
                final ParseObject boxer = new ParseObject("Boxer");
                boxer.put("name", edtName.getText().toString());
                boxer.put("speed", Integer.parseInt(edtPower.getText().toString()));
                boxer.put("power", Integer.parseInt(edtSpeed.getText().toString()));
                boxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            FancyToast.makeText(SignUp.this, boxer.get("name") + " has signed up", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                        } else {
                            FancyToast.makeText(SignUp.this, boxer.get("name") + " has signed up", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }

                    }
                });
            }
            catch(Exception e){
                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

            }
        }
    }

