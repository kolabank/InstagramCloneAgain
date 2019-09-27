package playing.kolade.com.instagramcloneagain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtSpeed, edtPower, edtKickPower, edtKickSpeed;
    private Button button, btnGetAllData;
    private TextView txtGetData;
    private String allKikboxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtPower = findViewById(R.id.edtPower);
        edtSpeed = findViewById(R.id.edtSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        button = findViewById(R.id.button);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);

        button.setOnClickListener(this);

        //This block is to get a specific object or item from the parse backend

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");

                parseQuery.getInBackground( "jAqfVzlzH0", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e==null){
                        txtGetData.setText(object.get("name")+" -zsasasaaasSSS " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        // This is to get all the object of the KickBoxer class, instead of one

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKikboxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e==null){
                            if(objects.size()>0){

                                for(ParseObject kickBoxer: objects){

                                    allKikboxers = allKikboxers + kickBoxer.get("name")+ "\n";

                                }
                                FancyToast.makeText(SignUp.this, allKikboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }
                            else  FancyToast.makeText(SignUp.this, "Error", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }



        @Override
        public void onClick (View v) {

        //This block is to create a parse object and save the information to the back end
            // The try catch block is used to pick errors relating to the use of incompatible variable type

            try {
                final ParseObject boxer = new ParseObject("Boxer");
                boxer.put("name", edtName.getText().toString());
                boxer.put("speed", Integer.parseInt(edtPower.getText().toString()));
                boxer.put("power", Integer.parseInt(edtSpeed.getText().toString()));
                boxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
                boxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));


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

