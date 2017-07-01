package at.tripic.services;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import at.tripic.constants.triPicConstants;
import at.tripic.dto.PictureItem;
import at.tripic.interfaces.PictureHandler;
import at.tripic.interfaces.PictureService;



public class FlickrPictureService implements PictureService{
    private PictureHandler handler;
    private static RequestQueue queue;
    private static List<String> photoIdList = new ArrayList<>();
    private static at.tripic.dto.Response r = new at.tripic.dto.Response();
    private static PictureItem[] x;


    public FlickrPictureService(Context context, PictureHandler handler) {
        this.handler = handler;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void requestFlickrData() {
        //RequestQueue queue = Volley.newRequestQueue(this);

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
                        System.out.println(response);

                        Document doc;
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = null;


                        try {
                            builder = factory.newDocumentBuilder();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                        try {
                            doc = builder.parse(new InputSource(new StringReader(response)));
                            NodeList nodeListRsp = doc.getElementsByTagName("rsp");
                            for (int i = 0; i < nodeListRsp.getLength(); i++) {
                                NodeList nodeListPhotos = ((Element) nodeListRsp.item(0)).getElementsByTagName("photos");
                                NodeList nodeListPhotosChildren = nodeListPhotos.item(0).getChildNodes();
                                for (int j = 1; j < nodeListPhotosChildren.getLength(); j++) {
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

                                        if (photoIdList.size() == 5) {
                                            break;
                                        }




                                    }
                                }
                            }

                            r.setlistNew(photoIdList);

                            if (handler != null) {
                                handler.HandleResult(r.AsModel());
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
}
