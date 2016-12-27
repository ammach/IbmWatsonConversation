package com.ammach.ibmwatsonconversation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    class Task extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            String msg="";
            ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
            service.setUsernameAndPassword("30f7a2f0-68a9-4ed1-b67b-a6c160e6e387", "6uXiEOgPWCMT");

            MessageRequest newMessage = new MessageRequest.Builder().inputText("Hi").build();
            MessageResponse response = service.message("1ecf0894-b1a9-4d19-846d-1a6fac2cab22", newMessage).execute();
            try {
                JSONObject jsonObject=new JSONObject(response.toString());
                JSONObject outputJsonObject=jsonObject.getJSONObject("output");
                JSONArray jsonArray=outputJsonObject.getJSONArray("text");
                msg=jsonArray.get(0).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Task().execute();
    }
}
