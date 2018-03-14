package org.leanpoker.leanpokerandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.view.image.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tmolnar on 21/09/15.
 */
public class EventPhotoGridAdapter extends RecyclerView.Adapter<EventPhotoGridAdapter.ViewHolder> {

    private Context                 mContext;
    private List<PhotoModel>        mPhotoModels;
    private final LayoutInflater    mInflater;

    private OnPhotoClickListener    mOnPhotoClickListener;

    public EventPhotoGridAdapter(final Context context, final List<PhotoModel> photoModels) {
        validatePhotos(photoModels);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPhotoModels = photoModels;
    }

    public void setOnPhotoClickListener(OnPhotoClickListener photoClickListener) {
        if (mOnPhotoClickListener == null) {
            mOnPhotoClickListener = photoClickListener;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = mInflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PhotoModel photoModel = mPhotoModels.get(position);
        holder.update(photoModel);
    }

    @Override
    public int getItemCount() {
        return mPhotoModels.size();
    }

    public void setPhotoModels(List<PhotoModel> photos) {
        validatePhotos(photos);
        mPhotoModels = photos;
        notifyDataSetChanged();
    }

    private String getPhotoUrl(final int position) {
        return mPhotoModels.get(position).getUrl();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageview_photo)
        ImageView mPhotoView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void update(final PhotoModel photoModel) {
            ImageLoader.getInstance().load(photoModel.getUrl(), mPhotoView, R.color.item_event_bottom_divider);
        }

        @Override
        public void onClick(final View v) {
            if (EventPhotoGridAdapter.this.mOnPhotoClickListener != null) {
                EventPhotoGridAdapter.this.mOnPhotoClickListener.onPhotoClick(getAdapterPosition());
            }
        }
    }

    private void validatePhotos(final List<PhotoModel> photos) {
        if (photos == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public interface OnPhotoClickListener {
        void onPhotoClick(final int clickedPhotoIndex);
    }
}
