package com.mobileappdev.teamone.meetup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileappdev.teamone.meetup.EventModels.EventListItem;
import com.mobileappdev.teamone.meetup.FragmentListeners.OnViewEventDetailListener;
import com.mobileappdev.teamone.meetup.dummy.DummyContent;
import com.mobileappdev.teamone.meetup.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnViewEventDetailListener}
 * interface.
 * Activities containing this fragment MUST implement the {@link OnCreateEventFragmentInteractionListener}
 * interface.
 */
public class EventsListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnViewEventDetailListener mViewEventListener;
    private OnCreateEventFragmentInteractionListener mCreateEventListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventsListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventsListFragment newInstance(int columnCount) {
        EventsListFragment fragment = new EventsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventslist_list, container, false);

        RecyclerView eventListRecycler = view.findViewById(R.id.eventListRecyclerView);
        eventListRecycler.setAdapter(new MyEventsListRecyclerViewAdapter(mViewEventListener));
        eventListRecycler.setLayoutManager(
                new LinearLayoutManager(
                        eventListRecycler.getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        View addEventButton = view.findViewById(R.id.floating_create_event);
        addEventButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != mCreateEventListener) {
                            mCreateEventListener.onCreateEventFragmentInteraction();
                        }
                    }
                }
        );

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateEventFragmentInteractionListener) {
            mCreateEventListener = (OnCreateEventFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCreateEventFragmentInteractionListener");
        }

        if (context instanceof OnViewEventDetailListener) {
            mViewEventListener = (OnViewEventDetailListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnViewEventDetailListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mViewEventListener = null;
        mCreateEventListener = null;
    }

    public interface OnCreateEventFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCreateEventFragmentInteraction();
    }
}
