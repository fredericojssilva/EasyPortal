package net.fredericosilva.easyportal;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

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

    public static void startActivity(Fragment fragment, Class target){
        startActivity(fragment, fragment.getActivity(), target);
    }
    public static void startActivity(android.app.Fragment fragment, Class target){
        startActivity(fragment, fragment.getActivity(), target);
    }
    public static void startActivity(Activity activity, Class target){
        startActivity(activity,activity,target);
    }
    public static void startActivityForResult(Fragment fragment, Class target, int resultCode){
        startActivityForResult(fragment, fragment.getActivity(), target, resultCode);
    }
    public static void startActivityForResult(android.app.Fragment fragment, Class target, int resultCode){
        startActivityForResult(fragment, fragment.getActivity(), target, resultCode);
    }
    public static void startActivityForResult(Activity activity, Class target, int resultCode){
        startActivityForResult(activity,activity,target, resultCode);
    }

    private static void startActivity(Object source, Activity _activity, Class target) {
        Intent extras = generateIntentExtras(source);

        Intent i = new Intent(_activity, target);
        i.putExtras(extras);
        _activity.startActivity(i);
    }

    private static void startActivityForResult(Object source, Activity _activity, Class target, int resultCode) {
        Intent extras = generateIntentExtras(source);

        Intent i = new Intent(_activity, target);
        i.putExtras(extras);
        _activity.startActivityForResult(i,resultCode);
    }

    private static Intent generateIntentExtras(Object source){
        Intent extras = new Intent();
        for (Field field : source.getClass().getDeclaredFields()) {
            String name = field.getName();

            Annotation annotation = field.getAnnotation(IPortalOut.class);

            if (annotation instanceof IPortalOut) {
                try {
                    extras.putExtra(name, (java.io.Serializable) field.get(source));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        return extras;
    }




}
