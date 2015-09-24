package org.leanpoker;

import com.google.gson.GsonBuilder;

/**
 * Created by tmolnar on 24/09/15.
 */
public class JsonMapper {
    public static final com.google.gson.Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
