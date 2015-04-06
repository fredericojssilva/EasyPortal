package net.fredericosilva.easyportal;

import android.app.Activity;
import android.content.Intent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by frederico <fredericojssilva@gmail.com>
 * on 06/04/2015 .
 */
public class EasyPortal {

    public static void fill(Activity _activity) {
        for (Field field : _activity.getClass().getDeclaredFields()) {

            String name = field.getName();
            Annotation annotation = field.getAnnotation(IPortalIn.class);
            if (annotation instanceof IPortalIn) {
                String annotation_name = ((IPortalIn) annotation).name();

                fillWithIntentExtra(_activity, field, (annotation_name.equals("")) ? name : annotation_name);
            }

        }
    }

    private static void fillWithIntentExtra(Activity _activity, Field field, String name) {
        if (name == null)
            return;

        try {
            Object extra = _activity.getIntent().getExtras().get(name);

            try {
                field.set(_activity, extra);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void startActivity(Activity _activity, Class target) {

        Intent extras = new Intent();
        for (Field field : _activity.getClass().getDeclaredFields()) {
            String name = field.getName();

            Annotation annotation = field.getAnnotation(IPortalOut.class);

            if (annotation instanceof IPortalOut) {
                try {
                    extras.putExtra(name, (java.io.Serializable) field.get(_activity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        Intent i = new Intent(_activity, target);
        i.putExtras(extras);

        _activity.startActivity(i);
    }


}
