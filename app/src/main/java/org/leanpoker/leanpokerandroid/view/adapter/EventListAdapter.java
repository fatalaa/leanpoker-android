package org.leanpoker.leanpokerandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.model.EventModel;
import org.leanpoker.leanpokerandroid.model.EventModelComparator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private       List<Object>   mItemList;
	private final LayoutInflater mInflater;

	private OnEventClickListener mOnEventClickListener;

	private static final int EVENT_MODEL = 0;
	private static final int DIVIDER     = 1;

	public EventListAdapter(final Context context, final List<EventModel> eventModels) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setItemList(eventModels);
	}

	public void setOnEventClickListener(final OnEventClickListener onEventClickListener) {
		if (mOnEventClickListener == null) {
			mOnEventClickListener = onEventClickListener;
		}
	}

	@Override
	public int getItemViewType(final int position) {
		if (mItemList.get(position) instanceof EventModel) {
			return EVENT_MODEL;
		} else {
			return DIVIDER;
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		switch (viewType) {
			case EVENT_MODEL:
				return createEventModelViewHolder(parent);
			case DIVIDER:
				return createDividerViewHolder(parent);
			default:
				throw new InvalidParameterException(
						"Invalid viewType parameter:\t" + String.valueOf(viewType));
		}
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
		switch (viewHolder.getItemViewType()) {
			case EVENT_MODEL:
				updateEventModelViewHolder((EventModelViewHolder) viewHolder, position);
				break;
			case DIVIDER:
				updateDividerViewHolder((DividerDateViewHolder) viewHolder, position);
				break;
			default:
				throw new InvalidParameterException(
						"Invalid viewType parameter:\t" + String.valueOf(
								viewHolder.getItemViewType()));
		}
	}

	private EventModelViewHolder createEventModelViewHolder(final ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.item_event, parent, false);
		return new EventModelViewHolder(view);
	}

	private DividerDateViewHolder createDividerViewHolder(final ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.item_event_divider, parent, false);
		return new DividerDateViewHolder(view);
	}

	private void updateEventModelViewHolder(final EventModelViewHolder viewHolder,
	                                        final int position) {
		final EventModel eventModel = (EventModel) mItemList.get(position);
		final EventModelViewHolder eventModelViewHolder = (EventModelViewHolder) viewHolder;
		eventModelViewHolder.update(eventModel);
	}

	private void updateDividerViewHolder(final DividerDateViewHolder viewHolder,
	                                     final int position) {
		final String date = (String) mItemList.get(position);
		final DividerDateViewHolder dividerViewHolder = (DividerDateViewHolder) viewHolder;
		dividerViewHolder.update(date);
	}

	@Override
	public int getItemCount() {
		return mItemList.size();
	}

	public void setItemList(List<EventModel> eventModels) {
		validateEvents(eventModels);
		mItemList = buildItemList(eventModels);
		notifyDataSetChanged();
	}

	private List<Object> buildItemList(final List<EventModel> eventModels) {
		final List<Object> itemList = new ArrayList<>();
		final List<EventModel> sortedEventModels = sortEventModels(eventModels);
		int currentYear = -1;
		int currentMonth = -1;
		for (final EventModel eventModel : sortedEventModels) {
			if (eventModel.getDateTime().getYear() != currentYear ||
			    eventModel.getDateTime().getMonthOfYear() != currentMonth) {
				itemList.add(eventModel.getMonthAndYear());
				currentYear = eventModel.getDateTime().getYear();
				currentMonth = eventModel.getDateTime().getMonthOfYear();
			}
			itemList.add(eventModel);
		}
		return itemList;
	}

	private void validateEvents(final List<EventModel> events) {
		if (events == null) {
			throw new IllegalArgumentException("The list cannot be null");
		}
	}

	private String getEventId(final int position) {
		// TODO(tb): 18/10/15  Exception handling while casting? Theoritically only the event model view can be clicked (the date divider not)
		final EventModel eventModel = (EventModel) mItemList.get(position);
		return eventModel.getEventId();
	}

	private String getEventName(final int position) {
		// TODO(tb): 18/10/15  Exception handling while casting? Theoritically only the event model view can be clicked (the date divider not)
		final EventModel eventModel = (EventModel) mItemList.get(position);
		return eventModel.getName();
	}

	private List<EventModel> sortEventModels(final List<EventModel> eventModels) {
		Collections.sort(eventModels, new EventModelComparator());
		return eventModels;
	}

	public class EventModelViewHolder extends RecyclerView.ViewHolder implements
			View.OnClickListener {
		@Bind(R.id.textview_city)
		TextView mCityTextView;
		@Bind(R.id.textview_event_status)
		TextView mEventStatusTextView;
		@Bind(R.id.textview_event_name)
		TextView mEventNameView;
		@Bind(R.id.textview_event_date)
		TextView mEventDateView;
		@Bind(R.id.textview_event_faciliator)
		TextView mFacilitatorView;
		@Bind(R.id.textview_team_number)
		TextView mTeamNumberTextView;

		public EventModelViewHolder(final View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		public void update(final EventModel eventModel) {
			mCityTextView.setText(eventModel.getAddress().getCity());
			mEventNameView.setText(eventModel.getName());
			mEventDateView.setText(eventModel.getFormattedDateTime());
			mFacilitatorView.setText("Facilitator: " + eventModel.getFacilitator().getName());
			updateTeamNumber(eventModel.getTeamCount());
			updateEventStatus(eventModel.getEventStatus());
		}

		private void updateTeamNumber(final int teamCount) {
			if (teamCount == 0) {
				mTeamNumberTextView.setVisibility(View.GONE);
			} else {
				mTeamNumberTextView.setVisibility(View.VISIBLE);
				mTeamNumberTextView.setText(String.valueOf(teamCount) + " Teams");
			}
		}

		private void updateEventStatus(final EventStatus eventStatus) {
			if (eventStatus.equals(EventStatus.FUTURE)) {
				mEventStatusTextView.setVisibility(View.VISIBLE);
				mEventStatusTextView.setText("upcoming!");
			} else {
				if (eventStatus.equals(EventStatus.LIVE)) {
					mEventStatusTextView.setVisibility(View.VISIBLE);
					mEventStatusTextView.setText("live!");
				} else {
					mEventStatusTextView.setVisibility(View.GONE);
				}
			}
		}

		@Override
		public void onClick(final View view) {
			if (EventListAdapter.this.mOnEventClickListener != null) {
				EventListAdapter.this.mOnEventClickListener.onEventClick(getEventId(
						getAdapterPosition()), getEventName(getAdapterPosition()));
			}
		}
	}

	public class DividerDateViewHolder extends RecyclerView.ViewHolder {

		@Bind(R.id.txt_date_divider)
		TextView mDateDividerTextView;

		public DividerDateViewHolder(final View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void update(final String date) {
			mDateDividerTextView.setText(date);
		}
	}

	public interface OnEventClickListener {
		void onEventClick(final String eventId, final String eventName);
	}
}
