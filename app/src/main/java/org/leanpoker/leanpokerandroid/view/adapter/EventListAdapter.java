package org.leanpoker.leanpokerandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

	private       List<EventModel> mEventModels;
	private final LayoutInflater   mInflater;

	private OnEventClickListener mOnEventClickListener;

	public EventListAdapter(final Context context, final List<EventModel> eventModels) {
		validateEvents(eventModels);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mEventModels = eventModels;
	}

	public void setOnEventClickListener(final OnEventClickListener onEventClickListener) {
		if (mOnEventClickListener == null) {
			mOnEventClickListener = onEventClickListener;
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(final ViewGroup parent, final int position) {
		final View view = mInflater.inflate(R.layout.item_event, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
		final EventModel eventModel = mEventModels.get(position);
		viewHolder.update(eventModel);
	}

	@Override
	public int getItemCount() {
		return mEventModels.size();
	}

	public void setEventModels(List<EventModel> eventModels) {
		validateEvents(eventModels);
		mEventModels = eventModels;
		notifyDataSetChanged();
	}

	private void validateEvents(final List<EventModel> events) {
		if (events == null) {
			throw new IllegalArgumentException("The list cannot be null");
		}
	}

	private String getEventId(final int position) {
		return mEventModels.get(position).getEventId();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@Bind(R.id.textview_city)
		TextView mCityTextView;
		@Bind(R.id.textview_date_day)
		TextView mDateDayTextView;
		@Bind(R.id.textview_date_month)
		TextView mDateMonthView;
		@Bind(R.id.textview_date_year)
		TextView mDateYearView;
		@Bind(R.id.textview_event_name)
		TextView mEventNameView;
		@Bind(R.id.textview_faciliator)
		TextView mFacilitatorView;

		public ViewHolder(final View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		public void update(final EventModel eventModel) {
			mCityTextView.setText(eventModel.getAddress().getCity());
			mDateDayTextView.setText(eventModel.getDay());
			mDateMonthView.setText(eventModel.getMonth());
			mDateYearView.setText(eventModel.getYear());
			mEventNameView.setText(eventModel.getName());
			mFacilitatorView.setText(eventModel.getFacilitator().getName());
		}

		@Override
		public void onClick(final View view) {
			if (EventListAdapter.this.mOnEventClickListener != null) {
				EventListAdapter.this.mOnEventClickListener.onEventClick(getEventId(
						getAdapterPosition()));
			}
		}
	}

	public interface OnEventClickListener {
		void onEventClick(final String eventId);
	}
}
