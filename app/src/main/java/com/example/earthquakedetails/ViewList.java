package com.example.earthquakedetails;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.XMLFormatter;

public class ViewList extends AppCompatActivity {

    private static final String TAG = "ViewList";

    //creating arrays to contain the data from the RSS feed
    ListView listEq;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> link;
    ArrayList<String> pubDate;
    ArrayList<String> category;
    ArrayList<String> geoLat;
    ArrayList<String> geoLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        //defining the listview
        listEq = (ListView) findViewById(R.id.listEq);

        //defining the arrays
        title = new ArrayList<String>();
        description = new ArrayList<String>();
        link = new ArrayList<String>();
        pubDate = new ArrayList<String>();
        category = new ArrayList<String>();
        geoLat = new ArrayList<String>();
        geoLong = new ArrayList<String>();

        //when item in list is clicked, data is retrieved from the position in the array
        listEq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //when item is clicked, goes to new activity
                Intent intent = new Intent(ViewList.this, ViewListItem.class);
                intent.putExtra("Title", title.get(position));
                intent.putExtra("Description", description.get(position));
                intent.putExtra("Link", link.get(position));
                intent.putExtra("pubDate", pubDate.get(position));
                intent.putExtra("Category", category.get(position));
                intent.putExtra("GeoLat", geoLat.get(position));
                intent.putExtra("GeoLong", geoLong.get(position));

                //starts new activity
                startActivity(intent);
                Log.d(TAG, "Starting View Item List Activity");

            }
        });

        new backgroundtask().execute();
    }

    //makes new intent return data to this class
    public static Intent makeIntent(Context context) {
        return new Intent(context, ViewList.class);
    }

    //tries for connection with url with inputstream
    public InputStream getInputStream(URL url){
        try{
            return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }
    }

    //setting background tasks
    public class backgroundtask extends AsyncTask<Integer, Void, Exception>{

        //creaeting progress dialog box
        ProgressDialog progressDialog = new ProgressDialog(ViewList.this);
        Exception excep = null;

        //before the new activity is created, shows message
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog.setMessage("Retrieving Data...");
            Log.d(TAG, "Data is bring retrieved");
            progressDialog.show();
        }

        //starts retrieving data and putting them in arrays
        @Override
        protected Exception doInBackground(Integer... integers) {
            //sets URL and parses the RSS feed
            try{
                URL url = new URL("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);

                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                xmlPullParser.setInput(getInputStream(url), "UTF_8");

                //sets inside item tag to false
                boolean inItem = false;

                //gets event type of the tag
                int eventType = xmlPullParser.getEventType();

                //while event type does not equal the end of the document
                while (eventType != XmlPullParser.END_DOCUMENT){
                    //if event type is a start tag
                    if(eventType == XmlPullParser.START_TAG){
                        //if event type is item
                        if (xmlPullParser.getName().equalsIgnoreCase("item")){
                            //sets boolean to true
                            inItem = true;
                        }
                        //else if event type is equal to title
                        else if (xmlPullParser.getName().equalsIgnoreCase("title")){
                            //and is inside the item tag then adds it to array
                            if (inItem){
                                title.add(xmlPullParser.nextText());
                                Log.d(TAG, "Title added to array");
                            }
                        }
                        //else if event type is equal to description
                        else if (xmlPullParser.getName().equalsIgnoreCase("description")){
                            if (inItem){
                                description.add(xmlPullParser.nextText());
                                Log.d(TAG, "Description added to array");
                            }
                        }
                        //else if event type is equal to link
                        else if (xmlPullParser.getName().equalsIgnoreCase("link")){
                            if (inItem){
                                link.add(xmlPullParser.nextText());
                                Log.d(TAG, "Link added to array");
                            }
                        }
                        //else if event type is equal to pubDate
                        else if (xmlPullParser.getName().equalsIgnoreCase("pubDate")){
                            if (inItem){
                                pubDate.add(xmlPullParser.nextText());
                                Log.d(TAG, "pubDate added to array");
                            }
                        }
                        //else if event type is equal to category
                        else if (xmlPullParser.getName().equalsIgnoreCase("category")){
                            if (inItem){
                                category.add(xmlPullParser.nextText());
                                Log.d(TAG, "Category added to array");
                            }
                        }
                        //else if event type is equal to geo:lat
                        else if (xmlPullParser.getName().equalsIgnoreCase("geo:lat")){
                            if (inItem){
                                geoLat.add(xmlPullParser.nextText());
                                Log.d(TAG, "GeoLat added to array");
                            }
                        }
                        //else if event type is equal to geo:long
                        else if (xmlPullParser.getName().equalsIgnoreCase("geo:long")){
                            if (inItem){
                                geoLong.add(xmlPullParser.nextText());
                                Log.d(TAG, "GeoLong added to array");
                            }
                        }
                    }
                    //else if event type equals the end tag then sets the boolean to false
                    else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                        inItem = false;
                        Log.d(TAG, "Set boolean to false");
                    }
                    //goes to next event type
                    eventType = xmlPullParser.next();
                }
            }
            catch (MalformedURLException e){
                excep = e;
            }
            catch (XmlPullParserException e){
                excep = e;
            }
            catch (IOException e){
                excep = e;
            }

            return excep;
        }

        //after excute, adapts array to a simple list and dismisses dialog
        @Override
        protected void onPostExecute(Exception s){
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewList.this, android.R.layout.simple_list_item_1, title);
            listEq.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }
}
