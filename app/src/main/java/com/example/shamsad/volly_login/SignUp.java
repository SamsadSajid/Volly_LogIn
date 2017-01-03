package com.example.shamsad.volly_login;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    TextView tname,temail,tpass,trepass,tuser_name;
    EditText ename,eemail,epass,erepass,euser_name;
    Button bsignup;
    String sname,semail,spass,srepass,suser_name;
    AlertDialog.Builder builder;
    String url = "http://172.18.18.25/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        tname=(TextView)findViewById(R.id.tName);
        tuser_name=(TextView)findViewById(R.id.tUserName);
        temail=(TextView)findViewById(R.id.tEmail);
        tpass=(TextView)findViewById(R.id.tPassword);
        trepass=(TextView)findViewById(R.id.tRePassword);

        ename=(EditText)findViewById(R.id.eName);
        euser_name=(EditText) findViewById(R.id.eUserName);
        eemail=(EditText)findViewById(R.id.eEmail);
        epass=(EditText)findViewById(R.id.ePassword);
        erepass=(EditText)findViewById(R.id.eRePassword);

        bsignup=(Button)findViewById(R.id.bSignUp);

        builder = new AlertDialog.Builder(SignUp.this);

        bsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sname=ename.getText().toString();
                semail=eemail.getText().toString();
                spass=epass.getText().toString();
                srepass=erepass.getText().toString();

                if(sname.equals("")||semail.equals("")||spass.equals("")||srepass.equals("")){
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Please provide all the informations.");
                    displayAlert("input_error");
                }

                else{
                    if(!(spass.equals(srepass))){
                        builder.setTitle("Something went wrong...");
                        builder.setMessage("Password do not match.");
                        displayAlert("input_error");
                    }

                    else{
                        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                                new Response.Listener<String>(){
                                    @Override
                                    public void onResponse(String reponse){
                                        try {
                                            JSONArray jsonArray = new JSONArray(reponse);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code=jsonObject.getString("code");
                                            String message=jsonObject.getString("message");
                                            builder.setTitle("Server Response...");
                                            displayAlert(code);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String,String> getParams() throws AuthFailureError{
                                Map<String,String>params = new HashMap<String, String>();
                                params.put("name",sname);
                                params.put("email",semail);
                                params.put("user_name",suser_name);
                                params.put("password",spass);

                                return params;
                            }
                        };

                        MySigleton.getmInstance(SignUp.this).addToRequestQueue(stringRequest);
                    }
                }
            }
        });
    }

    public void displayAlert(final  String code){
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                      public void onClick(DialogInterface dialog,int which) {
                    if (code.equals("input_error")) {
                        epass.setText("");
                        erepass.setText("");
                    }
                    else if(code.equals("reg_true")){
                        finish();
                    }
                    else{
                        ename.setText("");
                        euser_name.setText("");
                        eemail.setText("");
                        epass.setText("");
                        erepass.setText("");
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
