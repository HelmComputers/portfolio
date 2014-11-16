/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.utils;

import android.util.Xml;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.models.Apps;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public final class AppsXmlParser {


    private AppsXmlParser() {
    }

    public static Apps parse(final InputStream inputStream) throws Exception {
        try {
            final XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return parse(parser);
        } finally {
            inputStream.close();
        }
    }

    private static Apps parse(final XmlPullParser parser) throws IOException, XmlPullParserException {
        final Apps apps = new Apps();
        parser.require(XmlPullParser.START_TAG, null, "apps");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            final String name = parser.getName();
            // Starts by looking for the entry tag
            if ("app".equals(name)) {
                apps.addApp(readApp(parser));
            } else {
                skip(parser);
            }
        }
        return apps;
    }

    private static App readApp (XmlPullParser parser) throws IOException,
            XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "app");
        String title = null;
        String status = null;
        String image = null;
        String os = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            final String element = parser.getName();
            if ("title".equals(element)) {
                title = readTitle(parser);
            } else if ("status".equals(element)) {
                status = readStatus(parser);
            } else if ("icon".equals(element)) {
                image = readIcon(parser);
            } else if ("os".equals(element)){
                os = readOs(parser);
            } else {
                skip(parser);
            }
        }
        return new App(title, image, status, os);
    }



    private static String readTitle (final XmlPullParser parser) throws IOException, XmlPullParserException {
        return readTag(parser, "title");
    }

    private static String readStatus(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return readTag(parser, "status");
    }

    private static String readIcon(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return readTag(parser, "icon");

    }
    private static String readOs(XmlPullParser parser) throws IOException, XmlPullParserException {
        return readTag(parser, "os");
    }

    private static String readTag(final XmlPullParser parser, final String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, tag);
        final String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tag);
        return title;
    }

    private static String readText(final XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(final XmlPullParser parser) {
    }

}


