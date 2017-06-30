package at.tripic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import at.tripic.constants.triPicConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PictureView extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        et1 = (EditText) findViewById(R.id.spot_1_desc);
        et1.setKeyListener(null);
        et2 = (EditText) findViewById(R.id.spot_2_desc);
        et2.setKeyListener(null);
        et3 = (EditText) findViewById(R.id.spot_3_desc);
        et3.setKeyListener(null);



        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Uri.parse(triPicConstants.URI_FLICKR_REST)
                .buildUpon()
                .appendQueryParameter("api_key", triPicConstants.API_KEY_FLICKR)
                .appendQueryParameter("method", triPicConstants.METHOD_FLICKR_PHOTOS_SEARCH)
                .appendQueryParameter("long", "48")
                .appendQueryParameter("lat", "16")
                .appendQueryParameter("geo_context", "2")
                .appendQueryParameter("has_geo", "1")
                .build()
                .toString();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc;
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = null;
                        List<String> photoIdList = new ArrayList<>();

                        try {
                            builder = factory.newDocumentBuilder();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                        try {
                            doc = builder.parse(new InputSource(new StringReader(response)));
                            NodeList nodeListRsp = doc.getElementsByTagName("rsp");
                            for (int i=0; i<nodeListRsp.getLength(); i++)
                            {
                                NodeList nodeListPhotos = ((Element)nodeListRsp.item(0)).getElementsByTagName("photos");
                                NodeList nodeListPhotosChildren = nodeListPhotos.item(0).getChildNodes();
                                for (int j=1; j<nodeListPhotosChildren.getLength(); j++)
                                {
                                    if (nodeListPhotosChildren.item(j).getNodeType() == 1) {
                                        //get id from a photo and store
                                        //photoIdList.add(nodeListPhotosChildren.item(j).getAttributes().getNamedItem("id").getNodeValue());

                                        //construct static url for a photo as jpg and store it
                                        photoIdList.add("https://farm"
                                            + nodeListPhotosChildren.item(j).getAttributes().getNamedItem("farm").getNodeValue()
                                            + ".staticflickr.com/"
                                            + nodeListPhotosChildren.item(j).getAttributes().getNamedItem("server").getNodeValue() + "/"
                                            + nodeListPhotosChildren.item(j).getAttributes().getNamedItem("id").getNodeValue() + "_"
                                            + nodeListPhotosChildren.item(j).getAttributes().getNamedItem("secret").getNodeValue()
                                            + ".jpg"
                                        );
                                    }
                                }
                            }
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("An error occured");
            }
        });
        queue.add(stringRequest);
    }

    public void navigateBack(View view) {
        Intent intent = new Intent(this, MapViewOverview.class);
        startActivity(intent);
    }

    public void openPopupView(View view) {
        //Intent intent = new Intent(this, PopupView.class);
        //startActivity(intent);


        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupWindow pw = new PopupWindow(
                inflater.inflate(R.layout.activity_popup_view, null, false),
                500,
                500,
                true);
        // The code below assumes that the root container has an id called 'main'
        pw.showAtLocation(this.findViewById(R.id.spot_1), Gravity.CENTER, 0, 0);

        //Todo: this is just a quickfix in order to prevent the app from crashing when navigating to a view via a popup button
        Intent intent = new Intent(this, PopupView.class);
        startActivity(intent);
    }

}
