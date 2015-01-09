package com.epistemik.gsd.views.fragments;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epistemik.gsd.R;
import com.epistemik.gsd.models.InboxModel;
import com.epistemik.gsd.views.ImpulseActivity;
import com.epistemik.gsd.views.adapters.InboxListAdapter;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private InboxModel mInboxModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mNewImpulseButton;

    private ActionBar.TabListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InboxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InboxFragment newInstance(InboxModel inboxModel) {
        InboxFragment fragment = new InboxFragment();
        fragment.mInboxModel = inboxModel;
        /*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return fragment;
    }

    public InboxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.inbox_list);

        // Add the NewImpulse button
        mNewImpulseButton = (FloatingActionButton) rootView.findViewById(R.id.new_impulse);
        mNewImpulseButton.attachToRecyclerView(mRecyclerView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.scrollToPosition(0);

        mAdapter = new InboxListAdapter(mInboxModel, mRecyclerView, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

    }
}
