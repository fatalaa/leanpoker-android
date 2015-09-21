package org.leanpoker.leanpokerandroid.modelmapper;

import org.leanpoker.data.model.Photo;
import org.leanpoker.leanpokerandroid.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridDataMapper {
    public List<PhotoModel> transform(List<Photo> photos) {
        ArrayList<PhotoModel> photoModels = new ArrayList<>();
        for(Photo photo : photos) {
            photoModels.add(new PhotoModel(photo));
        }
        return photoModels;
    }
}
