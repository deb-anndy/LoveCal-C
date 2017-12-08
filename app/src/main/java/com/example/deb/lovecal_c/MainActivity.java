package com.example.deb.lovecal_c;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.spark.submitbutton.SubmitButton;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText prince, princess;
    SubmitButton submitButton;
    List<Integer> imageList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        imageList.add(R.drawable.lovefill);
        imageList.add(R.drawable.loveempty);

        initView();

    }

    private void initView()
    {
        prince = (EditText) findViewById(R.id.prince);
        princess = (EditText) findViewById(R.id.princess);
        submitButton = (SubmitButton) findViewById(R.id.test);


        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String male = prince.getText().toString();
        String female = princess.getText().toString();
        String url = "https://love-calculator.p.mashape.com/getPercentage?fname=" + male + "&sname=" + female;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            prince.setText("");
                            princess.setText("");

                            try {
                                String result = response.getString("result");
                                String fname = response.getString("fname");
                                String sname = response.getString("sname");
                                String percentage = response.getString("percentage");
                                Bundle bundle = new Bundle();
                                bundle.putString("fname", fname);
                                bundle.putString("sname", sname);
                                bundle.putString("result", result);
                                bundle.putString("percentage", percentage);
                                Intent intent = new Intent(MainActivity.this, Result.class);
                                intent.putExtras(bundle);
                                startActivity(intent);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(),"Please check your internet connection..",Toast.LENGTH_SHORT).show();

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("X-Mashape-Key", "lWQJ3W5HADmshlSddVq8topsLcUDp1XCDCzjsnNsVnac2uakwA");
                    params.put("Accept", "application/json");

                    return params;
                }
            };
               MyAppQueue.getInstance().addToRequestQueue(jsonObjectRequest);
        }




    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.about) {
            Intent about = new Intent(getApplicationContext(),About.class);
            startActivity(about);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
