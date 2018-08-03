package com.easytechhub.helloeyakub.researchhub.model;

import com.easytechhub.helloeyakub.researchhub.R;

/**
 * Created by Eyakub on 11/28/2017.
 */

public class ScienceData {

    public static String[] title = new String[]{
            "Data Mining",
            "Artificial Intelligence",
            "Data Science",
            "Machine Learning",
            "Internet of Things (IoT)",
            "Computer Vision & Image processing"
    };

    public static int[] scienceStringID = new int[]{
            R.string.data_mining,
            R.string.artificial_intelligence,
            R.string.data_science,
            R.string.machine_learning,
            R.string.internet_of_things,
            R.string.image_processing
    };

    public static int[] picturePath = new int[]{
            R.drawable.datamining,
            R.drawable.ai,
            R.drawable.data_science,
            R.drawable.machine_learning,
            R.drawable.iot,
            R.drawable.image_processing,
    };

    public static String[] getTitle() {
        return title;
    }

    public static void setTitle(String[] title) {
        ScienceData.title = title;
    }

    public static int[] getPicturePath() {
        return picturePath;
    }

    public static void setPicturePath(int[] picturePath) {
        ScienceData.picturePath = picturePath;
    }

}
